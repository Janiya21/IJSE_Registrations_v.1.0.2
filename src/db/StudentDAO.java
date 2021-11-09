package db;

import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;
import controller.*;

import java.sql.ResultSet;

public class StudentDAO {

    // insert students details into the database
    public static void insertStudent(String id ,String Name, String Email , String PayMethod ,int TelNo ) throws  Exception{
        String sql = "insert into student_details(Student_Id, Name, Email, PaymentMethod, Telephone) values('"+ id +"','"+ Name +"','"+ Email +"','"+ PayMethod +"','"+ TelNo + "') " ;

        try{
            // call the dbExecuteQuery method in DBUtil class
            DBUtil.dbExecuteUpdate(sql);
        }catch(Exception e){
            System.out.println(" Exception occurs while inserting data " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<Student> SearchEmployee(String id) throws  Exception{
        String sql = "Select * from student_details where Student_Id = '" + id + "' ";

        try{
            ResultSet rsSet = DBUtil.dbExecuteQuery(sql);
            ObservableList<Student> list = getStudentObjects(rsSet);
            return list;
        }catch(Exception e){
            System.out.println(" Exception occurs while Searching data " + e);
            e.printStackTrace();
            throw e;
        }
    }


    public static void updateEmployee(String id,String email,String telNo,String name, String paymentMethod) throws  Exception{
        String sql = "update student_details set Email = '" + email + "', Telephone = '" + telNo +  "', Name = '" + name + "', paymentMethod = '"
                + paymentMethod + "' where Student_Id = '" + id + "' " ;

        try{
            DBUtil.dbExecuteUpdate(sql);
        }catch(Exception e){
            System.out.println("Error occurs while updating records " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void deleteStudent(String id) throws  Exception{
        String sql = "delete from student_details where Student_Id = '" + id + "' ";

        try{
            DBUtil.dbExecuteUpdate(sql);
        }catch(Exception e){
            System.out.println("Error occurs while deleting records " + e);
            e.printStackTrace();
            throw e;
        }
    }

    //fetching data from the database and this method is return Observable List of student type
    public static ObservableList<Student> getAllRecords() throws Exception{
        String sql = "select * from student_details";

        try{
            ResultSet reSet = DBUtil.dbExecuteQuery(sql); // DBUtil.dbExecute(sql) -> returns a resultSet
            // You need to pass this resultSet provided by the database to a observableList because only that
            // can manage the task of separating specific data into specific columns in your tableView
            ObservableList<Student> stuList = getStudentObjects(reSet);
            return stuList;

        }catch(Exception e){
            System.out.println("Error occurs while fetching the data from database");
            e.printStackTrace();
            throw e;
        }
    }

    // ObservableList: A list that enables listeners to track changes when they occur.
    // ListChangeListener: An interface that receives notifications of changes to an ObservableList.
    private static ObservableList<Student> getStudentObjects(ResultSet rsSet) throws Exception{

        try{
            // you need to create observableList to add the data from the database columns
            ObservableList<Student> stuList = FXCollections.observableArrayList();

            // resultSet.next() - The next() method of the ResultSet interface moves the pointer of the current
            // (ResultSet) object to the next row, from the current position. If there is a data that will return true value or then false..
            while(rsSet.next()){

                Student stu = new Student();

                // get the columns value through column's name from the resultSet(Line 71) and pass it to the setter method of Student class
                stu.setIdProperty(rsSet.getInt("Student_Id"));
                stu.setNameProperty(rsSet.getString("Name"));
                stu.setPMethodProperty(rsSet.getString("Email"));
                stu.setEmailProperty(rsSet.getString("PaymentMethod"));
                stu.setTelNoProperty(rsSet.getInt("Telephone"));

                // Add all of passed values to the Observable List you created
                stuList.add(stu);
            }
            // return that list into getAllRecords() method
            return stuList;

        }catch(Exception e){
            System.out.println("Error occurs while fetching the data");
            e.printStackTrace();
            throw e;
        }
    }


}
