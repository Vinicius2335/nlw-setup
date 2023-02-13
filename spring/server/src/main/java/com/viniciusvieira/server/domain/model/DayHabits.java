package com.viniciusvieira.server.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @Column(columnDefinition = "VARCHAR(32)")
    @EqualsAndHashCode.Include
    private UUID idDayHabit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "habit_id", unique = true)
    private Habits habit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "day_id", unique = true)
    private Days day;
}
