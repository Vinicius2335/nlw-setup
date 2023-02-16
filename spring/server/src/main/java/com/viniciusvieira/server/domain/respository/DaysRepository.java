package com.viniciusvieira.server.domain.respository;

import com.viniciusvieira.server.domain.model.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DaysRepository extends JpaRepository<Days, UUID> {
    @Query("select d from Days d inner join d.dayHabits dayHabits where dayHabits.habit.idHabit = ?1")
    Optional<Days> findByHabitId(UUID idHabit);
}
