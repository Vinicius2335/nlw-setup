package com.viniciusvieira.server.domain.respository;

import com.viniciusvieira.server.domain.model.DayHabits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayHabitsRepository extends JpaRepository<DayHabits, UUID> {
    @Query("select d from DayHabits d where d.habit.idHabit = ?1 and d.day.idDay = ?2")
    Optional<DayHabits> findByHabitIdAndIdDay(UUID idHabit, UUID idDay);

}
