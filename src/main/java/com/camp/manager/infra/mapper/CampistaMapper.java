package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.domain.enums.Resposta;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CampistaMapper implements Mapper<CampistaEntityJpa, CampistaEntityDomain>{

    private final CamisetaMapper camisetaMapper;
    private final PessoaMapper pessoaMapper;
    private final EquipeMapper equipeMapper;

    @Autowired
    public CampistaMapper(@Lazy CamisetaMapper camisetaMapper,
                          @Lazy PessoaMapper pessoaMapper,
                          @Lazy EquipeMapper equipeMapper) {
        this.camisetaMapper = camisetaMapper;
        this.pessoaMapper = pessoaMapper;
        this.equipeMapper = equipeMapper;
    }

    @Override
    public CampistaEntityDomain toDomain(CampistaEntityJpa campistaEntityJpa) {
        return new CampistaEntityDomain(
                campistaEntityJpa.getId(),
                campistaEntityJpa.getUsaMedicamento() == Resposta.SIM ? Boolean.TRUE : Boolean.FALSE,
                campistaEntityJpa.getTemAlergia() == Resposta.SIM ? Boolean.TRUE : Boolean.FALSE,
                List.of(campistaEntityJpa.getAlergias().split(",")),
                campistaEntityJpa.getCodigoRegistro(),
                campistaEntityJpa.getJaFezAcampamento() == Resposta.SIM ? Boolean.TRUE : Boolean.FALSE,
                List.of(campistaEntityJpa.getAcampamentosFeitos().split(",")),
                campistaEntityJpa.getTemBarraca() == Resposta.SIM ? Boolean.TRUE : Boolean.FALSE,
                campistaEntityJpa.getCamiseta() != null ? this.camisetaMapper.toDomain(campistaEntityJpa.getCamiseta()) : null,
                this.pessoaMapper.toDomain(campistaEntityJpa.getPessoa()),
                campistaEntityJpa.getEquipe() != null ? this.equipeMapper.toDomain(campistaEntityJpa.getEquipe()) : null
        );
    }

    @Override
    public CampistaEntityJpa toEntity(CampistaEntityDomain campistaEntityDomain) {
        return new CampistaEntityJpa(
                campistaEntityDomain.id(),
                Resposta.fromBoolean(campistaEntityDomain.usaMedicamento()),
                Resposta.fromBoolean(campistaEntityDomain.temAlergia()),
                String.join(",", campistaEntityDomain.alergias()),
                campistaEntityDomain.codigoRegistro(),
                Resposta.fromBoolean(campistaEntityDomain.jaFezAcampamento()),
                String.join(",", campistaEntityDomain.acampamentosFeitos()),
                Resposta.fromBoolean(campistaEntityDomain.temBarraca()),
                campistaEntityDomain.camiseta() != null ? this.camisetaMapper.toEntity(campistaEntityDomain.camiseta()) : null,
                this.pessoaMapper.toEntity(campistaEntityDomain.pessoa()),
                campistaEntityDomain.equipe() != null ? this.equipeMapper.toEntity(campistaEntityDomain.equipe()) : null
        );
    }




    public CampistaEntityDomain toDomainWithoutEquipe(CampistaEntityJpa campistaEntityJpa) {
        if (campistaEntityJpa == null) {
            return null;
        }
        return new CampistaEntityDomain(
                campistaEntityJpa.getId(),
                campistaEntityJpa.getUsaMedicamento() == Resposta.SIM,
                campistaEntityJpa.getTemAlergia() == Resposta.SIM,
                List.of(campistaEntityJpa.getAlergias().split(",")),
                campistaEntityJpa.getCodigoRegistro(),
                campistaEntityJpa.getJaFezAcampamento() == Resposta.SIM,
                List.of(campistaEntityJpa.getAcampamentosFeitos().split(",")),
                campistaEntityJpa.getTemBarraca() == Resposta.SIM,
                campistaEntityJpa.getCamiseta() != null ? this.camisetaMapper.toDomain(campistaEntityJpa.getCamiseta()) : null,
                this.pessoaMapper.toDomain(campistaEntityJpa.getPessoa()),
                null
        );
    }
}
