package com.camp.manager.infra.http.request.pessoa;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class CriarCampistaRequest{

    @NotNull(message = "Usa Medicamento não pode ser nulo!")
    private Boolean usaMedicamento;

    @NotNull(message = "Tem Alergia não pode ser nulo!")
    private Boolean temAlergia;

    private List<String> alergias;

    @NotNull(message = "Já fez Acampamento não pode ser nulo!")
    private Boolean jaFezAcampamento;

    private List<String> acampamentosFeitos;

    @NotNull(message = "Tem Barraca não pode ser nulo!")
    private Boolean temBarraca;

    private List<Long> idsDeMedicamentos;

    private String tamanhoCamisa;
    private PessoaRequest pessoa;

    @Setter
    private String codigoRegistro;
}
