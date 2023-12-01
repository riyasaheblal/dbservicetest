package com.sl.dbs.service;

import com.sl.dbs.dao.PostgresDaoImpl;
import com.sl.dbs.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostgresService implements CommonService {

    @Autowired
    PostgresDaoImpl postgresDao;


    @Override
    public ResponseEntity<String> getData(Request request) throws Exception {
        return postgresDao.getData(request);
    }
}