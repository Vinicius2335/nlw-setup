package com.viniciusvieira.server.core.util;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TestOffsetDateTime {
    public static void main(String[] args) {
        String data = "2023-01-19T14:00:00.00";
        String data2 = "2023-02-15T14:00:00.00";
        OffsetDateTime offsetDateTime = OffsetDateTime.now(); // 2023-02-15T12:41:05.134081700-03:00
        ZoneOffset offset = offsetDateTime.getOffset();
        String dataFormatada = data + offset;
        String dataFormatada2 = data2 + offset;
        OffsetDateTime offsetDateTimeFormatada = OffsetDateTime.parse(dataFormatada);
        OffsetDateTime offsetDateTimeFormatada2 = OffsetDateTime.parse(dataFormatada2);
        //System.out.println(offsetDateTimeFormatada);

        OffsetDateTime offsetDateTime1 = DataUtil.zerarHorasOffsetDateTime(offsetDateTimeFormatada2);
        OffsetDateTime offsetDateTime2 = DataUtil.beteewnHorasOffsetDateTime(offsetDateTimeFormatada2);
        System.out.println(offsetDateTime1);
        System.out.println(offsetDateTime2);

        System.out.println(ChronoUnit.DAYS.between(offsetDateTimeFormatada2, offsetDateTime));

        LocalDateTime aniversario = LocalDateTime.of(1988, Month.AUGUST, 6, 12, 0, 0);
        LocalDateTime now = LocalDateTime.now();

        System.out.println(ChronoUnit.DAYS.between(aniversario, now));
    }
}
