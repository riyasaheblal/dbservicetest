package com.sl.dbs.service;

import com.sl.dbs.dao.MysqlDaoImpl;
import com.sl.dbs.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MysqlService implements CommonService {

    @Autowired
    MysqlDaoImpl mysqlDao;
    @Override
    public ResponseEntity<String> getData(Request request) throws Exception {
        return mysqlDao.getData(request);
    }
}