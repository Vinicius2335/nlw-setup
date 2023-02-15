package com.viniciusvieira.server.domain.service;

import com.viniciusvieira.server.api.mapper.HabitsMapper;
import com.viniciusvieira.server.api.representation.model.requestbody.HabitsRequestBody;
import com.viniciusvieira.server.api.representation.model.responsebody.HabitResponseBody;
import com.viniciusvieira.server.domain.model.Habits;
import com.viniciusvieira.server.domain.respository.HabitsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class RegistrarHabitService {
    private final HabitsRepository habitsRepository;
    private final HabitsMapper habitsMapper;

    @Transactional
    public HabitResponseBody createNewHabit(HabitsRequestBody habitRequest){
        Habits newHabit = Habits.builder()
                .title(habitRequest.getTitle())
                .createdAt(OffsetDateTime.now())
                .habitWeekDays(new ArrayList<>())
                .build();

        newHabit.adicionarWeekDays(habitRequest.getWeekDays());

        return habitsMapper.toHabitsResponseBody(habitsRepository.save(newHabit));
    }
}
