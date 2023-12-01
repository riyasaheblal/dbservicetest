package com.sl.dbs.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.dbs.helper.DbConnectionHelper;
import com.sl.dbs.helper.SqlStatementHelper;
import com.sl.dbs.model.KeyValuePair;
import com.sl.dbs.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sl.dbs.helper.ExtractDataHelper.extractData;

@Service
public class PostgresDaoImpl implements PostgresDao{

    @Autowired
    DbConnectionHelper dbConnectionHelper;
    @Override
    public ResponseEntity<String > getData(Request request) throws Exception {
        Connection conn = dbConnectionHelper.getDbConnection(request);
        try {
            CallableStatement callableStatement = conn.prepareCall(SqlStatementHelper.createSqlStatement(request));
            List<KeyValuePair> paramKeyValues = request.keyValuePairs();

            if (paramKeyValues != null && paramKeyValues.size() > 0) {
                callableStatement = SqlStatementHelper.setParameterForProcedure(callableStatement, paramKeyValues);
            }
            boolean demo = callableStatement.execute();
            List<List<Map<String, Object>>> resultSets = new ArrayList<>();

            while (demo) {
                ResultSet rs = callableStatement.getResultSet();
                List<Map<String, Object>> listMap = extractData(rs);
                System.out.println(listMap);
                resultSets.add(listMap);

                demo = callableStatement.getMoreResults();
                System.out.println(demo);
            }

            System.out.println(resultSets);

            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.writeValueAsString(resultSets);
            return ResponseEntity.ok(responseJson);
        }
        catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        finally {
            conn.close();
        }

    }}
