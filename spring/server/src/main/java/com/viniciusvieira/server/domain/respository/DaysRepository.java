package com.viniciusvieira.server.domain.respository;

import com.viniciusvieira.server.domain.model.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DaysRepository extends JpaRepository<Days, UUID> {
}
