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

    public static  OffsetDateTime dateStart(OffsetDateTime dateStart){
        return OffsetDateTime.of(dateStart.getYear(),
                dateStart.getMonth().getValue(),
                dateStart.getDayOfMonth(), 0, 0, 0, 0,
                dateStart.getOffset());
    }

    public static OffsetDateTime dateEnd(OffsetDateTime dateEnd){
        return OffsetDateTime.of(dateEnd.getYear(),
                dateEnd.getMonth().getValue(),
                dateEnd.getDayOfMonth(), 23, 59, 59, 0,
                dateEnd.getOffset());
    }
}
