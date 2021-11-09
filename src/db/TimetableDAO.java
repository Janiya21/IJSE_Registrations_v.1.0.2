package db;

import util.DBUtil;

import java.sql.SQLException;

public class TimetableDAO {

    public static void insertTimetable(String week, String month, String m1,String m2,String m3,String m4,String w1,String w2,String w3,String w4,String f1,String f2,
                                       String f3,String f4) throws SQLException, ClassNotFoundException {

        String query = "insert into timetable(time_slot, Monday,Wednesday,Friday) values" +
                "('"+week + " weekOf " + month + " at 8.30 - 10.00','"+m1 + "','"+ w1+ "','"+ f1 + "')," +
                "('"+week + " weekOf " + month + " at 10.30 - 12.00','"+m2 + "','"+ w2+ "','"+ f2 + "')," +
                "('"+week + " weekOf " + month + " at 1.30 - 3.00','"+m3 + "','"+ w3+ "','"+ f3 + "')," +
                "('"+week + " weekOf " + month + " at 4.00 - 5.00','"+m4 + "','"+ w4+ "','"+ f4 + "');";

        System.out.println(query);

        try {
            DBUtil.dbExecuteUpdate(query);
            System.out.println("New timetable adding done !!!!");
        } catch (Exception e) {
            System.out.println("Exception occurs while inserting data into time table " + e);
            e.printStackTrace();
            throw e;
        }
        finally {
            System.out.println("hahahaa");
        }
    }
}
