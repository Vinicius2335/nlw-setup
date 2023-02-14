package com.viniciusvieira.server.domain.model;

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
public class DayHabits {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    @EqualsAndHashCode.Include
    private UUID idDayHabit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "habit_id", unique = true)
    private Habits habit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "day_id", unique = true)
    private Days day;
}
