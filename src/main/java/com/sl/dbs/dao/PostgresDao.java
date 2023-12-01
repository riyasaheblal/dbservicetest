package com.sl.dbs.dao;

import com.sl.dbs.model.Request;
import org.springframework.http.ResponseEntity;


public interface PostgresDao {

  ResponseEntity<String> getData(Request request) throws Exception;

}