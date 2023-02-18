package com.viniciusvieira.server.api.representation.model.requestbody;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HabitsRequestBody {
    @NotEmpty
    private String title;

    @Size(max = 7)
    private List<@Max(6) Integer> weekDays;
}
