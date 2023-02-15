package com.viniciusvieira.server.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Habits {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    @EqualsAndHashCode.Include
    private UUID idHabit;

    @Column(nullable = false)
    private String title;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false)
    private OffsetDateTime createdAt;

    // Relacionamentos
    @JsonIgnore
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HabitWeekDays> habitWeekDays = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DayHabits> dayHabits = new ArrayList<>();

    public void adicionarWeekDays(List<Integer> weekDays){
        for (Integer weekDay : weekDays){
            HabitWeekDays habitWeekDay = HabitWeekDays.builder()
                    .weekDay(weekDay)
                    .habit(this).build();

            this.habitWeekDays.add(habitWeekDay);
        }
    }
}
