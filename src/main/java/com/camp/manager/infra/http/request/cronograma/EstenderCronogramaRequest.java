package com.camp.manager.infra.http.request.cronograma;

public record EstenderCronogramaRequest(
        Long idCronograma,
        Long diasParaEstender) {
}
