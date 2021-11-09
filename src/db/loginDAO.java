package db;

import util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDAO {

    public static String password_fromDB = null;
    public static String branch_fromDB = null;

    public static void checkValidation(String username) throws SQLException {
        String query = "Select * from admin where userName = '" + username + "' ";

        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);

            if(rs.next()){
                password_fromDB = rs.getString(2);
                branch_fromDB = rs.getString(3);
            }else {
                System.out.println("username invalid");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurs while getting UName and PWord from DB");
        }
    }

    public static boolean userAvailability(String username) throws SQLException {
        String query = "Select * from admin where userName = '" + username + "' ";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(query);
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurs while getting UName and PWord from DB");
        }
        return false;
    }

    public static boolean insertNewAdmin(String username,String password,String branch) throws SQLException, ClassNotFoundException {
        String query = "insert into admin(userName, password, branch) values('"+username + "','"+ password+ "','"+ branch + "') ";

            try {
                DBUtil.dbExecuteUpdate(query);
                System.out.println("New user adding done !!!!");
            } catch (Exception e) {
                System.out.println(" Exception occurs while inserting data into admin table " + e);
                e.printStackTrace();
                throw e;
            }
        return true;
    }

}
