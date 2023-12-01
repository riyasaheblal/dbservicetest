package com.sl.dbs.model;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbParams {
    
    private String serverHost;
    private String port;
    private String username;
    private String password;
    private String dbName;
    private String maximumPoolSize;
}
