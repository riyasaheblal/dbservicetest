package com.sl.dbs.model;

import java.util.Date;

public enum Datatype {

    STR(String.class),
    INT(Integer.class),
    DOU(Double.class),
    BOOL(Boolean.class),
    DATE(Date.class),
    OBJ(Object.class);

    private Class<?> dataTypeClass;

    Datatype(Class<?> dataTypeClass) {
        this.dataTypeClass = dataTypeClass;
    }

    public Class<?> getDataTypeClass() {
        return dataTypeClass;
    }
}
