package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutsDoneController implements Initializable {
    public ImageView bgImageView;
    public Text userName;
    public ListView workoutsList;
    User user=null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File bgFile=new File("Images/bgLOGIN.jpg");
        Image bgImage = new Image(bgFile.toURI().toString());
        bgImageView.setImage(bgImage);
    }

    public void getUserObject(User u){
        user=u;
        userName.setText(user.name);
        for(Workout w:user.workoutsDone){
            workoutsList.getItems().add(w.name);
        }
    }
}
