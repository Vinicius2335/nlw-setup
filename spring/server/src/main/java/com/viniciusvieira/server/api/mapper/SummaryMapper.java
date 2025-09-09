package com.viniciusvieira.server.api.mapper;

import com.viniciusvieira.server.api.representation.model.responsebody.ISummaryResponseBody;
import com.viniciusvieira.server.api.representation.model.responsebody.SummaryResponseBody;

import java.time.OffsetDateTime;
import java.util.List;


public class SummaryMapper {

    public SummaryResponseBody toSummaryResponseBody(ISummaryResponseBody iSummaryResponseBody){
        OffsetDateTime now = OffsetDateTime.now();
        return SummaryResponseBody.builder()
                .id(iSummaryResponseBody.getId())
                .date(OffsetDateTime.of(iSummaryResponseBody.getDate().toLocalDateTime(), now.getOffset()))
                .completed(iSummaryResponseBody.getCompleted())
                .amount(iSummaryResponseBody.getAmount())
                .build();
    }

    public List<SummaryResponseBody> toSummaryResponseBodyList(List<ISummaryResponseBody> iSummaryResponseBodyList){
        return iSummaryResponseBodyList.stream()
                .map(this::toSummaryResponseBody)
                .toList();
    }
}
