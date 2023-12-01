package com.sl.dbs.util;

import com.sl.dbs.model.DbParams;

import static com.sl.dbs.util.Constants.*;
import static com.sl.dbs.util.Constants.MAXPOOLSIZE;
import static com.sl.dbs.util.ErrorConstants.*;

public class CommonUtility {

    public static DbParams getDbParam(String connectionString) throws Exception {

        DbParams dbParams = new DbParams();
        String[] dbStrings = connectionString.split(";");

        for (String str : dbStrings) {
            if (str.toLowerCase().contains(SERVER))
                try {
                    dbParams.setServerHost(getParamValue(str));
                } catch (Exception e) {
                    throw new Exception(SERVERCONNECTION);
                }

            if (str.toLowerCase().contains(PORT))
                try{
                dbParams.setPort(getParamValue(str));}
                catch (Exception e) {
                    throw new Exception(PORTCONNECTION);
                }
            if (str.toLowerCase().contains(UID))
                try{
                dbParams.setUsername(getParamValue(str));}
                catch (Exception e) {
                    throw new Exception(UIDCONNECTION);
                }
            if (str.toLowerCase().contains(PWD))
                try{
                dbParams.setPassword(getParamValue(str));}
                catch (Exception e) {
                    throw new Exception(PWDCONNECTION);
                }
            if (str.toLowerCase().contains(DATABASE))
                try{
                dbParams.setDbName(getParamValue(str));}
                catch (Exception e) {
                    throw new Exception(DATABASECONNECTION);
                }
            if (str.toLowerCase().contains(MAXPOOLSIZE))
                try{
                dbParams.setMaximumPoolSize(getParamValue(str));}
                catch (Exception e) {
                    throw new Exception(MAXIMUMCONNECTION + connectionString, e);
                }
        }

        return dbParams;
    }

    public static String getParamValue(String paramaString) throws Exception {
        if (paramaString == null || paramaString.isEmpty()) {
            throw new Exception("check");
        }

        String[] paramValue = paramaString.split("=");
        return paramValue[1];
    }


}
