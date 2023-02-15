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
        List<Habits> possibleHabitsByDate = habitsRepository.findPossibleHabitsByDate(date);
        List<String> completedHabitsByDate = habitsRepository.findCompletedHabitsByDate(date);

        return DetailOfTheDayResponseBody.builder()
                .possigleHabits(possibleHabitsByDate)
                .completedHabits(completedHabitsByDate)
                .build();
    }

    // TODO excluir depois
    public List<Habits> getAllHabits(){
        OffsetDateTime data = DataUtil.converterStringEmOffsetDateTime("2023-01-03T03:00:00");
        List<String> completedHabits = habitsRepository.findCompletedHabitsByDate(data);
        //List<Habits> possibleHabits = habitsRepository.findPossibleHabitsByDate2(data);
        System.out.println("Completed Habits");
        System.out.println(completedHabits);

        //System.out.println("Possible Habits");
        //System.out.println(possibleHabits);
        return habitsRepository.findAll();
    }
}
