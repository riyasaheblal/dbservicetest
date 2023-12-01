package com.sl.dbs.model;

import lombok.Builder;

@Builder
public record KeyValuePair(String key, Object value, Datatype dataType) {
}
