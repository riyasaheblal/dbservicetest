package com.sl.dbs.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.dbs.helper.ExtractDataHelper;
import com.sl.dbs.helper.SqlStatementHelper;
import com.sl.dbs.model.Datatype;
import com.sl.dbs.model.KeyValuePair;
import com.sl.dbs.model.Request;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sl.dbs.util.ErrorConstants.*;

@Component
public class JpaDaoImpl implements JpaDao {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<String> getProcData(Request request) throws Exception {
        Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());


        List<List<Map<String, Object>>> resultList = new ArrayList<>();


        try {
            CallableStatement callableStatement = conn.prepareCall(SqlStatementHelper.createSqlStatement(request));
            List<KeyValuePair> paramKeyValues = request.keyValuePairs();
            if (paramKeyValues != null && paramKeyValues.size() > 0) {
                callableStatement = SqlStatementHelper.setParameterForProcedure(callableStatement, paramKeyValues);
            }
            boolean hasResults = callableStatement.execute();

            while (hasResults) {
                ResultSet rs = callableStatement.getResultSet();
                List<Map<String, Object>> listMap = ExtractDataHelper.extractData(rs);
                resultList.add(listMap);
                hasResults = callableStatement.getMoreResults();
            }

        } catch (Exception e) {
            System.out.println("Issue in the connection while executing procedure");
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } finally {
            conn.close();
        }
        return ResponseEntity.ok(new ObjectMapper().writeValueAsString(resultList));
    }
}
