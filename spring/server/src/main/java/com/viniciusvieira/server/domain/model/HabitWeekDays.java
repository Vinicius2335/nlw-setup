package com.viniciusvieira.server.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class HabitWeekDays {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    @EqualsAndHashCode.Include
    @JsonProperty("id")
    private UUID idHabitWeekDay;

    @Column(nullable = false, unique = true)
    private int weekDay;

    @OneToMany(mappedBy = "habitWeekDays", fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", unique = true)
    private Habits habit;
}
