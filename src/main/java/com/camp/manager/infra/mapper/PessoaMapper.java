package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.EnderecoEntityDomain;
import com.camp.manager.domain.entity.FamiliarEntityDomain;
import com.camp.manager.domain.entity.PessoaEntityDomain;
import com.camp.manager.domain.enums.Resposta;
import com.camp.manager.domain.enums.Sexo;
import com.camp.manager.infra.persistence.entity.EnderecoEntityJpa;
import com.camp.manager.infra.persistence.entity.FamiliarEntityJpa;
import com.camp.manager.infra.persistence.entity.PessoaEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper implements Mapper<PessoaEntityJpa, PessoaEntityDomain>{

    private final Mapper<EnderecoEntityJpa, EnderecoEntityDomain> mapperEndereco;
    private final Mapper<FamiliarEntityJpa, FamiliarEntityDomain> mapperFamiliar;

    @Autowired
    public PessoaMapper(@Lazy Mapper<EnderecoEntityJpa, EnderecoEntityDomain> mapperEndereco,
                        @Lazy Mapper<FamiliarEntityJpa, FamiliarEntityDomain> mapperFamiliar) {
        this.mapperEndereco = mapperEndereco;
        this.mapperFamiliar = mapperFamiliar;
    }

    @Override
    public PessoaEntityDomain toDomain(PessoaEntityJpa pessoaEntityJpa) {
        return new PessoaEntityDomain(
                pessoaEntityJpa.getId(),
                pessoaEntityJpa.getNomeCompleto().split(" ", 2)[0],
                pessoaEntityJpa.getNomeCompleto().split(" ", 2)[1],
                pessoaEntityJpa.getCpf(),
                LocalDateConverterAPP.converterStringParaLocalDate(pessoaEntityJpa.getDataNascimento()),
                pessoaEntityJpa.getTelefone(),
                pessoaEntityJpa.getSexo().getDescricao(),
                pessoaEntityJpa.getPeso(),
                pessoaEntityJpa.getAltura(),
                pessoaEntityJpa.getAlimentoPredileto(),
                pessoaEntityJpa.getFoiBatizado() == Resposta.SIM ? Boolean.TRUE : Boolean.FALSE,
                pessoaEntityJpa.getTemPrimeiraComunhao() == Resposta.SIM ? Boolean.TRUE : Boolean.FALSE,
                this.mapperEndereco.toDomain(pessoaEntityJpa.getEndereco()),
                this.mapperFamiliar.toDomain(pessoaEntityJpa.getFamiliar())
        );
    }

    @Override
    public PessoaEntityJpa toEntity(PessoaEntityDomain pessoaEntityDomain) {
        return new PessoaEntityJpa(
                pessoaEntityDomain.id(),
                pessoaEntityDomain.nome() + " " + pessoaEntityDomain.sobrenome(),
                pessoaEntityDomain.cpf(),
                LocalDateConverterAPP.converterLocalDateParaString(pessoaEntityDomain.dataNascimento()),
                pessoaEntityDomain.telefone(),
                Sexo.fromDescricao(pessoaEntityDomain.sexo()),
                pessoaEntityDomain.peso(),
                pessoaEntityDomain.altura(),
                pessoaEntityDomain.alimentoPredileto(),
                Resposta.fromBoolean(pessoaEntityDomain.foiBatizado()),
                Resposta.fromBoolean(pessoaEntityDomain.temPrimeiraComunhao()),
                this.mapperEndereco.toEntity(pessoaEntityDomain.endereco()),
                this.mapperFamiliar.toEntity(pessoaEntityDomain.familiar())
        );
    }
}
