package com.sl.dbs.helper;

import com.sl.dbs.config.DatasourceConfig;
import com.sl.dbs.model.DbParams;
import com.sl.dbs.model.Request;
import com.sl.dbs.util.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import static com.sl.dbs.util.Constants.*;
import static com.sl.dbs.util.ErrorConstants.PROCEDURETYPEERROR;

@Service
public class DbConnectionHelper {

    @Autowired
    private DatasourceConfig datasourceConfig;

    public Connection getDbConnection(Request request) throws Exception {
        Connection conn;
        if (request.ConnectionString() != null || request.ConnectionString().trim().length() == 0) {

            DbParams dbParams = CommonUtility.getDbParam(request.ConnectionString());

            conn = datasourceConfig.connect(getUrlForProvider(request.providerType(), dbParams),
                    dbParams.getUsername(), dbParams.getPassword());

        } else {
            conn = null;
        }

        return conn;
    }

    private String getUrlForProvider(String provider, DbParams dbParams) throws Exception {
        if(provider.isEmpty() || provider==null){
            throw new Exception(PROCEDURETYPEERROR);
        }
        StringBuilder url = new StringBuilder();

        if (provider.toLowerCase().equals(PROVIDER_TYPE_POSTGRESQL))
            url.append(PROVIDER_URL_POSTGRESQL);
        if (provider.toLowerCase().equals(PROVIDER_TYPE_MYSQL))
            url.append(PROVIDER_URL_MYSQL);
        if (provider.toLowerCase().equals(PROVIDER_TYPE_SQLSERVER))
            url.append(PROVIDER_URL_SQLSERVER);
        if (provider.toLowerCase().equals(PROVIDER_TYPE_ORACLE))
            url.append(PROVIDER_URL_ORACLE);

        url.append("//").append(dbParams.getServerHost())
                .append(":").append(dbParams.getPort()).append("/")
                .append(dbParams.getDbName());

        System.out.println("URL :: "+url);

        return url.toString();
    }


}
