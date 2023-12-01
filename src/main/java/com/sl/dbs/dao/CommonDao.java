package com.sl.dbs.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sl.dbs.model.Request;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface CommonDao {

    public ResponseEntity<String>  getProcData(Request request) throws Exception;

}
