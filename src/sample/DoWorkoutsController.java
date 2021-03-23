package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoWorkoutsController implements Initializable {
    Database db=new Database();
    User user=null;
    public ImageView bgImageView;
    public Text userName;
    public ComboBox workoutsBox;
    public Button confirmButton;
    public Text confirmText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File bgFile=new File("Images/bgLOGIN.jpg");
        Image bgImage = new Image(bgFile.toURI().toString());
        bgImageView.setImage(bgImage);
    }

    public void getUserObject(User u) throws SQLException {
        user=u;
        userName.setText(u.name);
        db.setWorkoutList(workoutsBox);
    }

    @FXML
    public void confirmButtonOnAction() throws SQLException {
        db.completeWorkout(user,workoutsBox.getValue().toString());
        confirmText.setFill(Color.GREEN);
        confirmText.setText("WORKOUT COMPLETE");
    }
}
