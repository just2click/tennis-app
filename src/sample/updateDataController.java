package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateDataController implements Initializable {
    Database db=new Database();
    User user=null;
    public ImageView bgImageView;
    public Text userName;
    public TextField weightField;
    public TextField heightField;
    public Button confirmButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File bgFile=new File("Images/bgLOGIN.jpg");
        Image bgImage = new Image(bgFile.toURI().toString());
        bgImageView.setImage(bgImage);

    }

    public void getUserObject(User u){
        user=u;
        userName.setText(user.name);
    }

    @FXML
    public void confirmButtonOnAction() throws SQLException {
        if(heightField.toString()!=null && weightField.toString()!=null) {
            db.updateData(heightField.getText(), weightField.getText(),user);
        }
    }

}
