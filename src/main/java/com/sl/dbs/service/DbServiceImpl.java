package com.sl.dbs.service;

import com.sl.dbs.model.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DbServiceImpl implements DBService{
    @Autowired
    PostgresService postgresService;

    @Autowired
    MysqlService mysqlService;

    @Override
    public ResponseEntity<String> getData(Request request) throws Exception {
        if(request.providerType().toLowerCase().equals("postgresql")){
            return postgresService.getData(request);
        }
        if(request.providerType().toLowerCase().equals("mysql")){
            return mysqlService.getData(request);
        }
      return null;
    }
}
