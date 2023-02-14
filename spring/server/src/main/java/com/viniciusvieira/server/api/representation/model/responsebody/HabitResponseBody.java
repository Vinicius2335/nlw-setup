package com.viniciusvieira.server.api.representation.model.responsebody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HabitResponseBody {
    private String title;
    private OffsetDateTime createdAt;
}
