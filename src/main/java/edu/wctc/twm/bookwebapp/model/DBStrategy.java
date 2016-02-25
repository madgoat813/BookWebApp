/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.twm.bookwebapp.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Taylor
 */
public interface DBStrategy {
    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException;
    
    public void closeConnection() throws SQLException ;
    
    public abstract List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;
    
    public void DeleteRecord(String tableName, String columnName, Object pk) throws SQLException;
    
    public int deleteById(String tableName, String pkColName, Object value) throws SQLException;
    
    public int updateRecordById(String tableName, List<String> colNames, List<Object> colValues, String pkColName, Object value) throws SQLException;
}
