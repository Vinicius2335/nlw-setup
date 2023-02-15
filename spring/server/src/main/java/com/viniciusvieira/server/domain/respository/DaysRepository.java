package com.viniciusvieira.server.domain.respository;

import com.viniciusvieira.server.domain.model.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DaysRepository extends JpaRepository<Days, UUID> {
    Optional<Days> findByDate(OffsetDateTime date);

    @Query(value = "SELECT * FROM days D\n" +
            "WHERE D.date between (:to) and (:from)", nativeQuery = true)
    Optional<Days> findByDate2(@Param("from") OffsetDateTime fromDate, @Param("to") OffsetDateTime toDate);
}
