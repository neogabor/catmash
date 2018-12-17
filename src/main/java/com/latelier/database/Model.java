package com.latelier.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Model implements IConnection{

    private Connection connection=null;
    
    private void open()throws SQLException, ClassNotFoundException{
        try {
            Class.forName(DRIVER);
            connection=DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(e.getMessage());
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
    
    public void close() throws SQLException{
        try {
            connection.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public Map<String,Integer> getScoreFromDB() throws SQLException, ClassNotFoundException {
        open();
        Map<String,Integer> returnMap = new HashMap<>();
        String SQL = "SELECT ID, SCORE FROM T_SCORE";
        Statement request=connection.createStatement();
        ResultSet result=request.executeQuery(SQL);
        while(result.next()){
            returnMap.put(result.getString("ID"), result.getInt("SCORE"));
        }
        return returnMap;
    }
    
    public void updateScore(String id, Integer score) throws SQLException, ClassNotFoundException {
        String SQLUpdate = "UPDATE T_SCORE SET SCORE = ? WHERE ID = ?";
        PreparedStatement request = connection.prepareStatement(SQLUpdate);
        request.setInt(1, score);
        request.setString(2, id);
        if(request.executeUpdate() == 0) {
            insertScore(id, score);
        }
    }

    private void insertScore(String id, Integer score) throws SQLException {
        String SQLINSERT = "INSERT INTO T_SCORE(ID,SCORE)"
                + "VALUES(?,?)";
        PreparedStatement request = connection.prepareStatement(SQLINSERT);
        request.setString(1, id);
        request.setInt(2, score);
        request.execute();
    }
}
