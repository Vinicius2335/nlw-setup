package com.viniciusvieira.server.core.util;

import com.viniciusvieira.server.api.representation.model.responsebody.ISummaryResponseBody;
import com.viniciusvieira.server.api.representation.model.responsebody.SummaryResponseBody;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public final class ConverterToSummaryListUtil {
    private ConverterToSummaryListUtil() {
    }

    public static List<SummaryResponseBody> getSummaryList(List<ISummaryResponseBody> iSummaryResponseBody){
        List<SummaryResponseBody> summarys = new ArrayList<>();
        OffsetDateTime now = OffsetDateTime.now();
        for (ISummaryResponseBody summary : iSummaryResponseBody){
            summarys.add(SummaryResponseBody.builder()
                    .id(summary.getId())
                    .date(OffsetDateTime.of(summary.getDate().toLocalDateTime(), now.getOffset()))
                    .completed(summary.getCompleted())
                    .amount(summary.getAmount())
                    .build());
        }
        return summarys;
    }
}
