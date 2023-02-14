package com.viniciusvieira.server.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Days {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    @EqualsAndHashCode.Include
    private UUID idDay;

    @Column(nullable = false, unique = true)
    private OffsetDateTime date;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
    private List<DayHabits> dayHabits = new ArrayList<>();
}
