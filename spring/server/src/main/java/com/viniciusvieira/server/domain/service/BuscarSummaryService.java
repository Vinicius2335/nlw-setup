package com.viniciusvieira.server.domain.service;

import com.viniciusvieira.server.api.mapper.SummaryMapper;
import com.viniciusvieira.server.api.representation.model.responsebody.ISummaryResponseBody;
import com.viniciusvieira.server.api.representation.model.responsebody.SummaryResponseBody;
import com.viniciusvieira.server.domain.respository.DaysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BuscarSummaryService {
    private final DaysRepository daysRepository;
    private final SummaryMapper summaryMapper;

    public List<SummaryResponseBody> summaryHabit(){
        List<ISummaryResponseBody> summary = daysRepository.getSummary();
        //return ConverterToSummaryListUtil.getSummaryList(summary);
        return summaryMapper.toSummaryResponseBodyList(summary);
    }
}
