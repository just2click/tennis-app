package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class mainScreenController implements Initializable {
    public Button updateButton;
    public Text userIDText;
    public Text userTypeText;
    public ImageView tickImage;
    public Button workoutDoneButton;
    public Button showUsersButton;
    public Button bmiButton;
    Database db=new Database();
    public ImageView bgImageView;
    public Text userName;
    public Button doWorkoutButton;
    public Button suggestionsButton;
    public Text suggestionsText;
    public Text weightText;
    public Text heightText;
    public Text bmiText;
    User user=null;

    @Override
    public void initialize(URL uri, ResourceBundle resourceBundle) {
        File bgFile=new File("Images/bgLOGIN.jpg");
        Image bgImage = new Image(bgFile.toURI().toString());
        bgImageView.setImage(bgImage);

    }

    public void getUserObject(User u){
        user=u;
        userName.setText(user.name);
        weightText.setText(String.valueOf("Weight: "+user.weight));
        heightText.setText(String.valueOf("Height: "+user.height));
        bmiText.setText(String.valueOf("BMI: "+user.BMI));
        userIDText.setText(String.valueOf(user.userid));
        System.out.println(user.type);
        if(user.type==1) {
            userTypeText.setText("Adult");
        }
        else
        userTypeText.setText("Child");

        if(user.workoutsDone.isEmpty()==false){
            File tickFile=new File("Images/tick.jpg");
            Image tickkImage = new Image(tickFile.toURI().toString());
            tickImage.setImage(tickkImage);
        }
    }

    @FXML
    public void doWorkoutButtonOnAction(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("doWorkouts.fxml"));
        try {
            Parent root=loader.load();
            DoWorkoutsController con=loader.getController();
            con.getUserObject(user);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void getSuggestionButtonOnAction() throws SQLException {
      db.getSuggestion(user,suggestionsText);
    }

    @FXML
    public void updateButtonOnAction(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("updateData.fxml"));
        try {
            Parent root=loader.load();
            updateDataController con=loader.getController();
            con.getUserObject(user);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showUsersButtonOnAction(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("showUsers.fxml"));
        try {
            Parent root=loader.load();
            ShowUsersController con=loader.getController();
            con.getUserObject(user);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void workoutsDoneButtonOnAction(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("workoutsDone.fxml"));
        try {
            Parent root=loader.load();
            WorkoutsDoneController con=loader.getController();
            con.getUserObject(user);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bmiButtonOnAction(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("bmi.fxml"));
        try {
            Parent root=loader.load();
            bmiController con=loader.getController();
            con.getUserObject(user);
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
