package com.viniciusvieira.server.domain.respository;

import com.viniciusvieira.server.api.representation.model.responsebody.ISummaryResponseBody;
import com.viniciusvieira.server.domain.model.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DaysRepository extends JpaRepository<Days, UUID> {
    @Query("select d from Days d inner join d.dayHabits dayHabits where dayHabits.habit.idHabit = ?1")
    Optional<Days> findByHabitId(UUID idHabit);

    @Query(value = """ 
              SELECT D.id as id,
              D.date as date,
              (SELECT COUNT(*) FROM day_habits DH WHERE DH.day_id = D.id) AS completed,
              (SELECT COUNT(*) FROM habit_week_days HWD JOIN habits H ON HWD.habit_id = H.id
              WHERE HWD.week_Day = WEEKDAY(D.date) AND H.created_at <= D.date) AS amount
              FROM days D
            """, nativeQuery = true)
    List<ISummaryResponseBody> getSummary();

}
