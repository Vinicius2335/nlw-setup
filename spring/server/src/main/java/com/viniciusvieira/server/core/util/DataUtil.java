package com.viniciusvieira.server.core.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class DataUtil {
    private DataUtil() {
    }

    public static OffsetDateTime converterStringEmOffsetDateTime(String data){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZoneOffset offset = offsetDateTime.getOffset();
        String dataFormatada = data + offset;
        return OffsetDateTime.parse(dataFormatada);
    }

    public static OffsetDateTime zerarHorasOffsetDateTime(OffsetDateTime offsetDateTime){
        String dia = String.valueOf(offsetDateTime.getDayOfMonth());
        String mes;
        if (offsetDateTime.getMonth().getValue() < 10){
            mes = "0" + offsetDateTime.getMonth().getValue();
        } else {
            mes = String.valueOf(offsetDateTime.getMonth().getValue());
        }
        String ano = String.valueOf(offsetDateTime.getYear());
        ZoneOffset offset = offsetDateTime.getOffset();
        System.out.println(offset);
        // 2023-02-15T12:41:05.134081700-03:00
        String dataFormatada = ano + "-" + mes + "-" + dia + "T" + "00:00:00" + offset;
        return OffsetDateTime.parse(dataFormatada);
    }

    public static OffsetDateTime beteewnHorasOffsetDateTime(OffsetDateTime offsetDateTime){
        String dia = String.valueOf(offsetDateTime.getDayOfMonth());
        String mes;
        if (offsetDateTime.getMonth().getValue() < 10){
            mes = "0" + offsetDateTime.getMonth().getValue();
        } else {
            mes = String.valueOf(offsetDateTime.getMonth().getValue());
        }
        String ano = String.valueOf(offsetDateTime.getYear());
        ZoneOffset offset = offsetDateTime.getOffset();
        // 2023-02-15T12:41:05.134081700-03:00
        String dataFormatada = ano + "-" + mes + "-" + dia + "T" + "23:59:59" + offset;
        return OffsetDateTime.parse(dataFormatada);
    }
}
