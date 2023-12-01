package com.sl.dbs.model;

import lombok.Builder;

import java.util.List;

@Builder
public record Request(String procedureName, List<KeyValuePair> keyValuePairs,String outKeyValuePairs, String OutputParam, String providerType, String ConnectionString) {
}
