package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.domain.entity.MethodResponse;
import com.camp.manager.domain.exception.custom.FileProcessingException;
import com.camp.manager.domain.exception.custom.FileTypeException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.imagens.InserirImagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class AdicionarImagensUC implements UseCase<InserirImagemRequest, MethodResponse<Void>> {

    private final ImagemGateway imagemGateway;
    private final AcampamentoGateway acampamentoGateway;

    private final List<String> tiposDeArquivosAceitos = List.of(".jpeg",".png",".jpg",".svg");

    @Autowired
    public AdicionarImagensUC(ImagemGateway imagemGateway, AcampamentoGateway acampamentoGateway) {
        this.imagemGateway = imagemGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(InserirImagemRequest imagensParaSerInserido) {
        boolean acampamentoEhExistente = this.acampamentoGateway.existsAcampamentoById(imagensParaSerInserido.idAcampamento());
        if(acampamentoEhExistente) {throw new NotFoundException("Acampamento não encontrado!");}

        this.validarArquivoInformado(imagensParaSerInserido.zipComAsImagens());

        Set<byte[]> imagensExtraidas = this.extrairImagens(imagensParaSerInserido.zipComAsImagens());

        imagensExtraidas.stream().forEach(imagemExtraida -> {
            //imagemGateway.inserirImagem(imagemExtraida, imagensParaSerInserido.idAcampamento());
        });


        return null;
    }

    private void validarArquivoInformado(MultipartFile arquivo) {
        if(arquivo == null) {
            throw new NotFoundException(("Arquivo zip precisa ser enviado!"));
        }

        if(!Objects.requireNonNull(arquivo.getOriginalFilename()).endsWith(".zip")) {
            throw new FileTypeException("Arquivo informado não é zip!");
        }
    }
    private Set<byte[]> extrairImagens(MultipartFile zipComImagens){
        Set<byte[]> imagens = new HashSet<>();
        try(ZipInputStream zipInput = new ZipInputStream(zipComImagens.getInputStream())){
            ZipEntry zipEntry;
            while ((zipEntry = zipInput.getNextEntry()) != null) {
                String fileName = zipEntry.getName();

                if(this.tiposDeArquivosAceitos.stream().noneMatch(fileName::endsWith)){
                    zipInput.closeEntry();
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;

                while ((len = zipInput.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }

                imagens.add(outputStream.toByteArray());

                zipInput.closeEntry();
            }
        } catch (Exception exception){
            throw new FileProcessingException("Erro ao processar imagens!");
        }
        return imagens;
    }
}
