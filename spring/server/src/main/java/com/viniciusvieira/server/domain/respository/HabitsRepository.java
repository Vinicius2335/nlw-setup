package com.viniciusvieira.server.domain.respository;

import com.viniciusvieira.server.domain.model.Habits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface  HabitsRepository extends JpaRepository<Habits, UUID> {
    @Query("select h from Habits h where h.createdAt <= ?1")
    List<Habits> findPossibleHabitsByDate(OffsetDateTime createdAt);

    //@Query("select h.idHabit from Habits h inner join h.dayHabits dayHabits where dayHabits.day.date = ?1")
    //List<String> findCompletedHabitsByDate(OffsetDateTime date);

    @Query("select h.idHabit from Habits h " +
            "inner join h.dayHabits dayHabits " +
            "where dayHabits.day.date between ?1 and ?2")
    List<String> findCompletedHabitsByBetWeenDate(OffsetDateTime dateStart, OffsetDateTime dateEnd);



}
