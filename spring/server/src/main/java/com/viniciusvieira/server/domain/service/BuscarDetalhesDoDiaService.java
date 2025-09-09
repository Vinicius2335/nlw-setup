package com.viniciusvieira.server.domain.service;

import com.viniciusvieira.server.api.representation.model.responsebody.DetailOfTheDayResponseBody;
import com.viniciusvieira.server.core.util.DataUtil;
import com.viniciusvieira.server.domain.model.Habits;
import com.viniciusvieira.server.domain.respository.HabitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BuscarDetalhesDoDiaService {
    private final HabitsRepository habitsRepository;

    public DetailOfTheDayResponseBody findDetailsOfTheDay(OffsetDateTime date){
        OffsetDateTime dateStart = DataUtil.dateStart(date);
        OffsetDateTime dateEnd = DataUtil.dateEnd(date);

        List<String> completedHabitsByDate2 = habitsRepository.findCompletedHabitsByBetWeenDate(dateStart, dateEnd);
        List<Habits> possibleHabitsByDate = habitsRepository.findPossibleHabitsByDate(date);

        System.out.println(possibleHabitsByDate);

        return DetailOfTheDayResponseBody.builder()
                .possigleHabits(possibleHabitsByDate)
                .completedHabits(completedHabitsByDate2)
                .build();
    }
}
