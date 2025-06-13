package com.camp.manager.infra.http.request.cronograma;

import com.camp.manager.infra.http.request.atividade.EquipeDiaFuncaoRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CriarCronogramCampistasRequest {
    private Long idDoCampamento;
    private List<Long> idsDasEquipes;
    private List<EquipeDiaFuncaoRequest> equipesDiaFuncaoRequests;
}
