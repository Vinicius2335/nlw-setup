package com.viniciusvieira.server.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
public class Habits {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @EqualsAndHashCode.Include
    private UUID idHabit;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    private List<HabitWeekDays> habitWeekDays = new ArrayList<>();

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    private List<DayHabits> dayHabits = new ArrayList<>();
}
