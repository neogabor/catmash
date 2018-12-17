package com.latelier.database;

public interface IConnection {
    static final String DRIVER="oracle.jdbc.driver.OracleDriver";
    static final String URL="jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER="CATMASH";
    static final String PASSWORD="catmash";
}
