package util;

import com.sun.rowset.CachedRowSetImpl;
import db.DbConnection;

import java.sql.*;

public class DBUtil {

    private static Connection connection = null;

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException,ClassNotFoundException{

        PreparedStatement stmt = null;

        try{
            connection= DbConnection.getInstance().getConnection();
            stmt = connection.prepareStatement(sqlStmt);
            stmt.executeUpdate();

        }catch(SQLException e){
            System.out.println("Problem occurs at dbExecuteQuery " + e);
            throw e;
        }
        finally {
            if(stmt != null){
                stmt.close();
            }
        }
    }

    public static ResultSet dbExecuteQuery(String sqlQuery) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs;

        try{
            connection= DbConnection.getInstance().getConnection();
            stmt = connection.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            crs = new CachedRowSetImpl();
            crs.populate(rs);

        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Problem occurs in dbExecution " + e);
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
        }
        return crs;
    }

}
