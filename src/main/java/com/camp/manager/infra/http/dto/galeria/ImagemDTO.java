package com.camp.manager.infra.http.dto.galeria;

import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.domain.exception.custom.FileProcessingException;
import lombok.Getter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Getter
public class ImagemDTO {
    private final String nomeDoArquivo;
    private final Path pathImagem;
    private final String dataDeCriacao;
    private final Long tamanhoDoArquivo;

    public ImagemDTO(ImagemEntityDomain imagemEntityDomain){
        this.nomeDoArquivo = imagemEntityDomain.nomeAcampamento();
        this.pathImagem = imagemEntityDomain.pathLocalizacao();
        this.dataDeCriacao = imagemEntityDomain.data().toString();
        this.tamanhoDoArquivo = this.calcularTamanhoDaImagem(pathImagem);
    }

    private Long calcularTamanhoDaImagem(Path pathImagem){
        long tamanhoDaImagem;
        try{
            tamanhoDaImagem = Files.size(pathImagem);
        } catch (Exception e) {
            throw new FileProcessingException("Erro ao buscar imagem no path: " + pathImagem);
        }
        return tamanhoDaImagem;
    }

    public static List<ImagemDTO> converter(List<ImagemEntityDomain> imagens) {
        return imagens.stream()
                .map(ImagemDTO::new)
                .toList();
    }
}
