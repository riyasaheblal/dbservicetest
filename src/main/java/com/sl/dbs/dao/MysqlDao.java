package com.sl.dbs.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sl.dbs.model.Request;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

public interface MysqlDao {

   ResponseEntity<String>  getData(Request request) throws Exception;
}