package com.camp.manager.utils.converter.localDate;

import com.camp.manager.domain.exception.custom.LocalDateConverterException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateConverterAPP {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate converterStringParaLocalDate(String data){
        if (data == null || data.isEmpty()) throw new LocalDateConverterException("Data informada não pode ser nula ou vazia!");

        try{
            return LocalDate.parse(data, formatter);
        } catch (Exception exception){
            throw new LocalDateConverterException("Erro ao converter data informada!");
        }
    }

    public static String converterLocalDateParaString(LocalDate data){
        if (data == null) throw new LocalDateConverterException("Data informada não pode ser nula!");
        try{
            return data.format(formatter);
        } catch (Exception exception) {
            throw new LocalDateConverterException("Erro ao converter data informada!");
        }
    }

}
