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
public class SummaryResponseBody {
    private String id;
    private OffsetDateTime date;
    private long completed;
    private long amount;
}
