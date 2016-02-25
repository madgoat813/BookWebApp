package edu.wctc.twm.bookwebapp.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Taylor
 */
@Dependent
public class MySqlDBStrategy implements DBStrategy, Serializable{
    private Connection conn;

    public MySqlDBStrategy() {
    }
    
    @Override
    public void openConnection(String driverClass, String url, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName (driverClass);
        conn = DriverManager.getConnection(url,userName, password);
    }
    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }
    
    /**
     * Make sure you open and close connection when using this method
     * Future optimization may include change the return type as array.
     * 
     * @param tableName
     * @param maxRecords - limits records found to first maxRecords or if maxRecords 
     * is zero (0) then no limit.
     * @return
     */
    public List<Map<String,Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        String sql;
        if(maxRecords < 1) {
            sql = "select * from " + tableName;
        }else{
            sql = "select * from " + tableName + " limit " + maxRecords;
        }
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        
        int columnCount = rsmd.getColumnCount();
        List<Map<String,Object>> records = new ArrayList<>();
        
        while(rs.next()) {
            Map<String,Object> record = new HashMap<>();
            for(int colNo = 1; colNo <= columnCount; colNo++) {
                Object colData = rs.getObject(colNo);
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, colData);
            }
        records.add(record);
        }
    return records;
    }
    @Override
    public void DeleteRecord(String tableName, String columnName, Object pk) throws SQLException {
        PreparedStatement deleteRecord = null;
        String deleteString = "DELETE FROM " + tableName + " WHERE " + columnName + " =?";
        
        deleteRecord = conn.prepareStatement(deleteString);
        if (pk instanceof String) {
            deleteRecord.setString(1, pk.toString());
        }else{
            deleteRecord.setInt(1, Integer.parseInt(pk.toString()));
        }
        
        deleteRecord.executeUpdate();
        
    }
    @Override
    public int deleteById(String tableName, String pkColName, Object value) throws SQLException {
        
        String sql = "delete from " + tableName + " where " + pkColName + " = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setObject(1, value);
        int result = psmt.executeUpdate();
        
        return result;
    }
    
    @Override
    public int updateRecordById(String tableName, List<String> colNames, List<Object> colValues, String pkColName, Object value) throws SQLException{
            PreparedStatement pstmt = null;
        int recsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
                    pstmt = buildUpdateStatement(conn,tableName,colNames,pkColName);

                    final Iterator i=colValues.iterator();
                    int index = 1;
                    Object obj = null;

                    // set params for column values
                    while( i.hasNext()) {
                        obj = i.next();
                        pstmt.setObject(index++, obj);
                    }
                    // and finally set param for wehere value
                    pstmt.setObject(index, value);
                    
                    recsUpdated = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw e;
        } finally {
                    try {
                            pstmt.close();
                            conn.close();
                    } catch(SQLException e) {
                            throw e;
                    } // end try
        } // end finally

        return recsUpdated;
            
            
}
        private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName, List colDescriptors, String whereField) throws SQLException {
		StringBuffer sql = new StringBuffer("UPDATE ");
		(sql.append(tableName)).append(" SET ");
		final Iterator i=colDescriptors.iterator();
		while( i.hasNext() ) {
			(sql.append( (String)i.next() )).append(" = ?, ");
		}
		sql = new StringBuffer( (sql.toString()).substring( 0,(sql.toString()).lastIndexOf(", ") ) );
		((sql.append(" WHERE ")).append(whereField)).append(" = ?");
		final String finalSQL=sql.toString();
		return conn_loc.prepareStatement(finalSQL);
	}
            
            
            
            
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        
        List<Map<String,Object>> rawData = db.findAllRecords("author", 0);
        
        System.out.println(rawData);
        
        db.closeConnection();
        
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        
        List<String> colNames = Arrays.asList("author_name", "date_added");
        List<Object> colValues = Arrays.asList("Lucifer", "2004-12-12");
        int result = db.updateRecordById("author", colNames, colValues, "author_id", 1);
        
        db.closeConnection();
        
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        
        rawData = db.findAllRecords("author", 0);
        System.out.println(rawData);
        db.closeConnection();
        System.out.println(rawData);
}
}
