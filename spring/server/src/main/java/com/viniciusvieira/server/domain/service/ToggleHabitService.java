package com.viniciusvieira.server.domain.service;

import com.viniciusvieira.server.domain.exception.HabitNotFoundException;
import com.viniciusvieira.server.domain.model.DayHabits;
import com.viniciusvieira.server.domain.model.Days;
import com.viniciusvieira.server.domain.model.Habits;
import com.viniciusvieira.server.domain.respository.DayHabitsRepository;
import com.viniciusvieira.server.domain.respository.DaysRepository;
import com.viniciusvieira.server.domain.respository.HabitsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public Habits getHabitByIdOrThrowsHabitNotFoundException(UUID idHabit){
        return habitsRepository.findById(idHabit)
                .orElseThrow(() -> new HabitNotFoundException("Habi nao foi encontrado"));
    }

    @Transactional
    public void toggleHabit(UUID idHabit){
        Habits habit = getHabitByIdOrThrowsHabitNotFoundException(idHabit);
        OffsetDateTime dateNow = OffsetDateTime.now();

        Optional<Days> day = daysRepository.findByHabitId(idHabit);

        if (day.isEmpty()){
            day = Optional.of(daysRepository.save(Days.builder().date(dateNow).build()));
        }

        Optional<DayHabits> dayHabit = dayHabitsRepository.findByHabitIdAndIdDay(habit.getIdHabit(),
                day.get().getIdDay());

        if (dayHabit.isPresent()){
            dayHabitsRepository.deleteById(dayHabit.get().getIdDayHabit());
            daysRepository.deleteById(day.get().getIdDay());
        } else {
            dayHabitsRepository.save(DayHabits.builder().habit(habit).day(day.get()).build());
        }
    }
}
