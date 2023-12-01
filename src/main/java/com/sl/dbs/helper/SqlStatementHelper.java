package com.sl.dbs.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sl.dbs.model.Datatype;
import com.sl.dbs.model.KeyValuePair;
import com.sl.dbs.model.Request;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import static com.sl.dbs.util.Constants.*;
import static com.sl.dbs.util.Constants.DEFAULT_PROC_INITIAL_STRING;
import static com.sl.dbs.util.ErrorConstants.*;

public class SqlStatementHelper {

    public static String createSqlStatement(Request request) throws Exception {
        if (request.procedureName() == null || request.procedureName().isEmpty()) {
            throw new Exception(PROCEDURENAMEERROR);
        }

        StringBuilder stringBuilder = new StringBuilder(getInitialProcString(request.providerType(), request.ConnectionString()).concat(" "));

        List<KeyValuePair> paramKeyValues = request.keyValuePairs();


        if (paramKeyValues == null || paramKeyValues.size() == 0) {
            stringBuilder.append(request.procedureName() + "(");
        } else {
            stringBuilder.append(request.procedureName() + "(?");
        }
        if (paramKeyValues != null && paramKeyValues.size() > 1) {
            for (int i = 1; i < paramKeyValues.size(); i++) {
                stringBuilder.append(", ?");
            }
        }
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static CallableStatement setParameterForProcedure(CallableStatement callableStatement, List<KeyValuePair> keyValuePairs) throws Exception {
        int i = 1;
        for (KeyValuePair keyValuePair : keyValuePairs) {
            if (keyValuePair.key() == null || keyValuePair.key().isEmpty()) {
                throw new Exception(KEYERROR);
            } else if (keyValuePair.value() == null) {
                throw new Exception(VALUEERROR);
            } else if (keyValuePair.dataType() == null) {
                throw new Exception(DATATYPEERROR);
            }
            if (keyValuePair.dataType().getDataTypeClass().equals(Datatype.INT.getDataTypeClass())) {
                callableStatement.setObject(i, keyValuePair.value());          //postgres

            } else if (keyValuePair.dataType().getDataTypeClass().equals(Datatype.STR.getDataTypeClass())) {
                callableStatement.setString(i, (String) keyValuePair.value());
            } else if (keyValuePair.dataType().getDataTypeClass().equals(Datatype.DOU.getDataTypeClass())) {
                callableStatement.setDouble(i, (Double) keyValuePair.value());
            } else if (keyValuePair.dataType().getDataTypeClass().equals(Datatype.BOOL.getDataTypeClass())) {
                callableStatement.setBoolean(i, (Boolean) keyValuePair.value());
            } else if (keyValuePair.dataType().getDataTypeClass().equals(Datatype.OBJ.getDataTypeClass())) {
                callableStatement.setObject(i, new ObjectMapper().writeValueAsString(keyValuePair.value()));
            } else {
                callableStatement.setObject(i, keyValuePair.value());
            }
            i++;
        }
        return callableStatement;
    }

    private static String getInitialProcString(String providerType, String ConnectionString) throws Exception {
        if (!ConnectionString.isEmpty() && ConnectionString != null) {
            if (providerType.equals(PROVIDER_TYPE_POSTGRESQL)) {
                return POSTGRES_PROC_INITIAL_STRING;
            } else if (providerType.equals(PROVIDER_TYPE_MYSQL)) {
                return DEFAULT_PROC_INITIAL_STRING;
            } else if (providerType.equals(PROVIDER_TYPE_SQLSERVER)) {
                return DEFAULT_PROC_INITIAL_STRING;
            } else if (providerType.equals(PROVIDER_TYPE_ORACLE)) {
                return DEFAULT_PROC_INITIAL_STRING;
            } else {
                return DEFAULT_PROC_INITIAL_STRING;
            }
        } else {
            return DEFAULT_PROC_INITIAL_STRING;
        }
    }
}
