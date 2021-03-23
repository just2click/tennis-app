package sample;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public User authenticateLogin(String userID, String password) throws SQLException {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String passwordd = "Muhammad13";   //replace with your password for mysql server
        try {
            conn = DriverManager.getConnection(url1, user, passwordd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (conn != null) {
            String sql1 = "select *  "
                    + " from user where user.userID=? and user.passwordd=?";

            PreparedStatement myStmt1 = conn.prepareStatement(sql1);

            //setting Parameters
            myStmt1.setInt(1, Integer.parseInt(userID));
            myStmt1.setString(2, password);

            // Execute SQL query
            ResultSet rs = myStmt1.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("userID");
                String pass = rs.getString("passwordd");
                String name = rs.getString("username");
                float w = rs.getFloat("weight");
                float h = rs.getFloat("height");
                float bmi = rs.getFloat("bmi");
                myStmt1.close();


                sql1 = "select *from usertype where usertype.userID=?";
                myStmt1 = conn.prepareStatement(sql1);
                myStmt1.setInt(1, id);
                ResultSet rs2 = myStmt1.executeQuery();
                int type = 0;
                rs2.next();

                    if (rs2.getString("userType").contains("A")) {
                        type = 1;
                        System.out.println(type);
                    }
                    else {
                        type = 2;
                    }
                rs2.close();
                myStmt1.close();


                ArrayList<Workout> workouts = new ArrayList<>();
                int workoutid;
                String workoutName;
                sql1 = "select *from workout_info where workout_info.userID=?";
                myStmt1 = conn.prepareStatement(sql1);
                myStmt1.setInt(1, id);

                rs2 = myStmt1.executeQuery();
                while (rs2.next()) {
                    workoutid = rs2.getInt("workoutID");
                    String sql2 = "select *from workout where workout.workoutID=?";
                    PreparedStatement myStmt2 = conn.prepareStatement(sql2);
                    myStmt2.setInt(1, workoutid);
                    ResultSet rs3 = myStmt2.executeQuery();
                    while (rs3.next()) {
                        workoutName = rs3.getString("workoutName");
                        Workout workout = new Workout(workoutName);
                        workouts.add(workout);
                    }
                    rs3.close();
                    myStmt2.close();
                }
                rs2.close();
                myStmt1.close();

                User user1 = new User(id, type, name, workouts, w, h, passwordd);

                rs.close();
                conn.close();
                return user1;
            }

        } else {
            System.err.println("No connection established");
            return null;
        }
        return null;
    }

    public void setWorkoutList(ComboBox workoutsBox) throws SQLException {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String passwordd = "Muhammad13";   //replace with your password for mysql server
        try {
            conn = DriverManager.getConnection(url1, user, passwordd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (conn != null) {
            String sql1 = "select *  "
                    + " from workout";

            PreparedStatement myStmt1 = conn.prepareStatement(sql1);
            ResultSet rs = myStmt1.executeQuery();
            while (rs.next()) {
                workoutsBox.getItems().add(rs.getString("workoutName"));
            }
            myStmt1.close();
            rs.close();
            conn.close();
        }
    }

    public void completeWorkout(User userr, String workoutName) throws SQLException {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/db1";
        String user = "root";
        String passwordd = "Muhammad13";   //replace with your password for mysql server
        int userid=userr.userid;
        try {
            conn = DriverManager.getConnection(url1, user, passwordd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (conn != null) {
            int workoutid = 0;

            String sql1 = "select *from workout where workout.workoutName=?";
            PreparedStatement myStmt1 = conn.prepareStatement(sql1);
            myStmt1.setString(1, workoutName);
            ResultSet rs = myStmt1.executeQuery();
            rs.next();
            workoutid = rs.getInt("workoutID");
            rs.close();
            myStmt1.close();

            sql1 = "insert into workout_info(workoutID,userID)" + "values(?,?)";
            myStmt1 = conn.prepareStatement(sql1);
            myStmt1.setInt(2, userid);
            System.out.println(workoutid);
            myStmt1.setInt(1, workoutid);
            myStmt1.execute();
            rs.close();
            myStmt1.close();
            conn.close();
        }
    }

    public void getSuggestion(User user, Text suggestionsText) throws SQLException {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/db1";
        String userID = "root";
        String passwordd = "Muhammad13";   //replace with your password for mysql server
        try {
            conn = DriverManager.getConnection(url1, userID, passwordd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (conn != null) {

            String workoutName;
            String sql1 = "select *from workout";
            PreparedStatement myStmt1 = conn.prepareStatement(sql1);
            ResultSet rs = myStmt1.executeQuery();
            boolean flag=false;
            while (rs.next()) {
                workoutName = rs.getString("workoutName");
                for (Workout w : user.workoutsDone) {
                    if (w.name.equals(workoutName)) {
                       flag=true;
                    }
                }
                if(flag==false){
                    suggestionsText.setText("Suggested Workout= "+workoutName);
                    break;
                }
                else{
                    flag=false;
                }
            }

            myStmt1.close();
            conn.close();

        }
    }

    public void updateData(String height, String weight, User userr) throws SQLException {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/db1";
        String userID = "root";
        String passwordd = "Muhammad13";   //replace with your password for mysql server
        try {
            conn = DriverManager.getConnection(url1, userID, passwordd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (conn != null) {
          String sql1="update user set user.weight=?, user.height=?, user.bmi=? where user.userID=?";
          PreparedStatement myStmt1=conn.prepareStatement(sql1);
          myStmt1.setFloat(1, Float.parseFloat(weight));
          myStmt1.setFloat(2, Float.parseFloat(height));
          myStmt1.setFloat(3, Float.parseFloat(weight)/(Float.parseFloat(height)*Float.parseFloat(height)));
          myStmt1.setInt(4,userr.userid);
          myStmt1.execute();
          myStmt1.close();
          conn.close();
        }
        }

    public void setUsersList(ListView usersList) throws SQLException {
        Connection conn = null;
        String url1 = "jdbc:mysql://localhost:3306/db1";
        String userID = "root";
        String passwordd = "Muhammad13";   //replace with your password for mysql server
        try {
            conn = DriverManager.getConnection(url1, userID, passwordd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (conn != null) {
            String sql1="select*from user";
            PreparedStatement myStmt1=conn.prepareStatement(sql1);
            ResultSet rs=myStmt1.executeQuery();
            while(rs.next()){
                String s= String.valueOf(rs.getInt("userID")+". "+rs.getString("username")+", "+rs.getFloat("weight")+"kg, "+rs.getFloat("height")+"m");
                String sql2="select*from usertype where usertype.userID=?";
                PreparedStatement myStmt2=conn.prepareStatement(sql2);
                myStmt2.setInt(1,rs.getInt("userID"));
                ResultSet rs2=myStmt2.executeQuery();
                if(rs2.next()){
                    if(rs2.getString("userType").contains("A")){
                        s=s+", Adult.";
                    }
                    else{
                        s=s+", Child.";
                    }
                }
                rs2.close();
                myStmt2.close();
                usersList.getItems().add(s);
            }
            rs.close();
            myStmt1.close();
            conn.close();
        }
    }
}