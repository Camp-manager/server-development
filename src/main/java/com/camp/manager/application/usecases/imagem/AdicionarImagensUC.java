package com.camp.manager.application.usecases.imagem;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.ImagemDescription;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.DirectoryException;
import com.camp.manager.domain.exception.custom.FileProcessingException;
import com.camp.manager.domain.exception.custom.FileTypeException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.imagens.InserirImagemRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class AdicionarImagensUC implements UseCase<InserirImagemRequest, MethodResponse<Void>> {

    private final ImagemGateway imagemGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Value("${imagem.pasta-raiz}")
    private String pathDaPastaRaiz;

    private final List<String> tiposDeArquivosAceitos = List.of(".jpeg",".png",".jpg",".svg");

    @Autowired
    public AdicionarImagensUC(ImagemGateway imagemGateway,
                              AcampamentoGateway acampamentoGateway) {
        this.imagemGateway = imagemGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(InserirImagemRequest imagensParaSerInserido) {
        AcampamentoEntityDomain acampamentoEncontrado = this.buscarAcampamentoInformado(imagensParaSerInserido.idAcampamento());

        this.validarZipInformado(imagensParaSerInserido.zipComAsImagens());

        this.inserirImagens(imagensParaSerInserido, acampamentoEncontrado);

        return new MethodResponse<>(201, "Imagens inseridas com sucesso!", null);
    }

    private AcampamentoEntityDomain buscarAcampamentoInformado(Long idAcampamento) {
        boolean acampamentoEhExistente = this.acampamentoGateway.existsAcampamentoById(idAcampamento);
        if(!acampamentoEhExistente) {throw new NotFoundException("Acampamento não encontrado com id [" +idAcampamento+"]!" );}

        return this.acampamentoGateway.buscarAcampamentoPorId(idAcampamento);
    }

    private void validarZipInformado(MultipartFile arquivo) {
        if(arquivo == null) {
            throw new NotFoundException(("Arquivo zip precisa ser enviado!"));
        }

        if(!Objects.requireNonNull(arquivo.getOriginalFilename()).endsWith(".zip")) {
            throw new FileTypeException("Arquivo informado não é zip!");
        }
    }

    private void validarDiretorio(Path diretorioDasPastas) {
        if(!Files.exists(diretorioDasPastas)) {
            try {
                Files.createDirectories(diretorioDasPastas);
            } catch (IOException exception){
                throw new DirectoryException("Erro ao criar diretório para armazenar imagens!");
            }
        }
    }

    private Set<ImagemDescription> extrairImagens(MultipartFile zipComImagens) {
        Set<ImagemDescription> imagens = new HashSet<>();

        try (ZipInputStream arquivoZipDeEntrada = new ZipInputStream(zipComImagens.getInputStream())) {
            ZipEntry zipEntry;

            while ((zipEntry = arquivoZipDeEntrada.getNextEntry()) != null) {
                String nomeDoArquivo = Paths.get(zipEntry.getName()).getFileName().toString(); // sanitiza nome

                String nomeDoArquivoLowerCase = nomeDoArquivo.toLowerCase();
                if (this.tiposDeArquivosAceitos.stream().noneMatch(nomeDoArquivoLowerCase::endsWith)) {
                    arquivoZipDeEntrada.closeEntry();
                    continue;
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = arquivoZipDeEntrada.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }

                byte[] imagemBytes = outputStream.toByteArray();

                String extensao = "";
                int index = nomeDoArquivo.lastIndexOf('.');
                if (index != -1 && index < nomeDoArquivo.length() - 1) {
                    extensao = nomeDoArquivo.substring(index + 1).toLowerCase();
                }

                imagens.add(new ImagemDescription(nomeDoArquivo, imagemBytes, extensao));

                arquivoZipDeEntrada.closeEntry();
            }
        } catch (IOException e) {
            throw new FileProcessingException("Erro ao processar imagens!");
        }

        return imagens;
    }

    private void inserirImagens(InserirImagemRequest inserirImagemRequest, AcampamentoEntityDomain acampamentoEncontrado) {
        Set<ImagemDescription> imagensExtraidas = this.extrairImagens(inserirImagemRequest.zipComAsImagens());
        String pathPastaAcampamento = acampamentoEncontrado.tipoAcampamento().descricao() +" " +inserirImagemRequest.anoDasImagens();

        Path basePath = Paths.get(this.pathDaPastaRaiz);
        Path diretorioDasPastas = basePath.resolve(pathPastaAcampamento);

        this.validarDiretorio(diretorioDasPastas);

        imagensExtraidas.forEach(imagemExtraida -> {
            Path diretorioCompletoDaImagem = diretorioDasPastas.resolve(imagemExtraida.nomeDoArquivo());
            try {
                Files.write(diretorioCompletoDaImagem, imagemExtraida.bytesDaImagem());
            } catch (IOException e) {
                throw new FileProcessingException("Erro ao processar imagens!");
            }

            this.imagemGateway.inserirNovaImagem(diretorioCompletoDaImagem.toString(), LocalDateConverterAPP.converterLocalDateParaString(LocalDate.now()), acampamentoEncontrado);
        });
    }
}
