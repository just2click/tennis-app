package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ShowUsersController implements Initializable {
    public ListView usersList;
    public Text userName;
    public ImageView bgImageView;
    Database db=new Database();
    User user=null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File bgFile=new File("Images/bgLOGIN.jpg");
        Image bgImage = new Image(bgFile.toURI().toString());
        bgImageView.setImage(bgImage);
    }

    public void getUserObject(User u) throws SQLException {
        user=u;
        userName.setText(user.name);
        db.setUsersList(usersList);
    }
}
