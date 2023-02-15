package com.viniciusvieira.server.domain.service;

import com.viniciusvieira.server.core.util.DataUtil;
import com.viniciusvieira.server.domain.model.DayHabits;
import com.viniciusvieira.server.domain.model.Days;
import com.viniciusvieira.server.domain.model.Habits;
import com.viniciusvieira.server.domain.respository.DayHabitsRepository;
import com.viniciusvieira.server.domain.respository.DaysRepository;
import com.viniciusvieira.server.domain.respository.HabitsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ToggleHabitService {
    private final HabitsRepository habitsRepository;
    private final DaysRepository daysRepository;
    private final DayHabitsRepository dayHabitsRepository;

    // TODO criar a Exception HabiNotFoundException
    public Habits getHabitByIdOrThrowsHabitNotFoundException(UUID idHabit){
        return habitsRepository.findById(idHabit)
                .orElseThrow(() -> new RuntimeException("Habi nao foi encontrado"));
    }

    @Transactional
    public void toggleHabit(UUID idHabit){
        Habits habit = getHabitByIdOrThrowsHabitNotFoundException(idHabit);
        OffsetDateTime dateNow = OffsetDateTime.now();

        OffsetDateTime dataZerada = DataUtil.zerarHorasOffsetDateTime(dateNow);
        OffsetDateTime dataMaxima = DataUtil.beteewnHorasOffsetDateTime(dateNow);

        Optional<Days> day = daysRepository.findByDate2(dataZerada, dataMaxima);
        Days newDay = new Days();

        if (day.isEmpty()){
             newDay = daysRepository.save(Days.builder().date(dateNow).build());
        }

        Optional<DayHabits> dayHabit = dayHabitsRepository.findByHabitIdAndIdDay(habit.getIdHabit(),
                newDay.getIdDay());

        if (dayHabit.isPresent()){
            dayHabitsRepository.deleteById(dayHabit.get().getIdDayHabit());
        } else {
            dayHabitsRepository.save(DayHabits.builder().habit(habit).day(newDay).build());
        }
    }


}
