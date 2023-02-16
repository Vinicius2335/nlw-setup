package com.viniciusvieira.server.core.util;

import java.time.*;
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

        OffsetDateTime offsetDateTime1 = DataUtil.dateStart(offsetDateTimeFormatada2);
        OffsetDateTime offsetDateTime2 = DataUtil.dateEnd(offsetDateTimeFormatada2);
        //System.out.println(offsetDateTime1);
        //System.out.println(offsetDateTime2);

        //System.out.println(ChronoUnit.DAYS.between(offsetDateTimeFormatada2, offsetDateTime));

        LocalDateTime aniversario = LocalDateTime.of(1988, Month.AUGUST, 6, 12, 0, 0);
        LocalDateTime ontem = LocalDateTime.of(2023, Month.FEBRUARY, 9, 14, 0, 0);
        LocalDateTime hoje = LocalDateTime.of(2023, Month.FEBRUARY, 16, 22, 0, 0);

        OffsetDateTime dateAniversario = OffsetDateTime.of(aniversario, offset);
        OffsetDateTime dateOntem = OffsetDateTime.of(ontem, offset);
        OffsetDateTime dateHoje = OffsetDateTime.of(hoje, offset);

        String s = String.valueOf(offsetDateTime);
        String instantAsString = Instant.parse(s).truncatedTo(ChronoUnit.DAYS).toString();
        OffsetDateTime ofInstant = OffsetDateTime.parse(instantAsString);
        System.out.println("ofInstant " + ofInstant);

        OffsetDateTime dale2 = OffsetDateTime.of(offsetDateTime.getYear(), offsetDateTime.getMonth().getValue(),
                offsetDateTime.getDayOfMonth(), 23, 59, 59, 0,
                offsetDateTime.getOffset());
        System.out.println(dale2);

        // Menor Negativo, Maior Positivo
        System.out.println(offsetDateTime.compareTo(dateAniversario)); // 35
        System.out.println(offsetDateTime.compareTo(dateOntem)); // 1
        System.out.println(dateOntem.compareTo(offsetDateTime)); // -1
        System.out.println(offsetDateTime.compareTo(dateHoje)); // -1

        if (offsetDateTime.getDayOfMonth() == dateHoje.getDayOfMonth()
                && offsetDateTime.getMonth().getValue() == dateHoje.getMonth().getValue()
                && offsetDateTime.getYear() == dateHoje.getYear()) {
            System.out.println("Igual");
        } else {
            System.out.println("diferente");
        }

        //System.out.println(ChronoUnit.DAYS.between(aniversario, now));
    }
}
