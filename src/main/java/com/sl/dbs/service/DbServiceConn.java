package com.sl.dbs.service;

import com.sl.dbs.model.DbParams;
import com.sl.dbs.model.Request;
import com.sl.dbs.dao.CommonDaoImpl;
import com.sl.dbs.dao.JpaDaoImpl;
import com.sl.dbs.util.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DbServiceConn {

    @Autowired
    private CommonDaoImpl commonDaoImpl;

    @Autowired
    private JpaDaoImpl jpaDaoImpl;

    public ResponseEntity<String> getData(Request request) throws Exception {
        return request.ConnectionString().trim() != null
                && !request.ConnectionString().isBlank() ? commonDaoImpl.getProcData(request)
                : jpaDaoImpl.getProcData(request);
    }


    public boolean checkProvidedConnectionString(Request request) throws Exception {
        return request.ConnectionString().trim() != null
                && validateConnectionString(request.ConnectionString()) ? true : false;
    }

    private boolean validateConnectionString(String connectionString) throws Exception {
        DbParams dbParams = CommonUtility.getDbParam(connectionString);
        return dbParams.getDbName() != null && dbParams.getPort() != null && dbParams.getUsername() != null
                && dbParams.getPassword() != null && dbParams.getServerHost() != null ? true : false;
    }


}
