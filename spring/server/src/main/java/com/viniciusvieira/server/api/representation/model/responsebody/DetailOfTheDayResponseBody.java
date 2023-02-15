package com.viniciusvieira.server.api.representation.model.responsebody;

import com.viniciusvieira.server.domain.model.Habits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetailOfTheDayResponseBody {
    private List<Habits> possigleHabits;
    private List<String> completedHabits;
}
