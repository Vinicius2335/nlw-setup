package com.viniciusvieira.server.api.representation.model.responsebody;

import java.sql.Timestamp;

public interface ISummaryResponseBody {
    String getId();
    Timestamp getDate();
    Integer getCompleted();
    Integer getAmount();
}
