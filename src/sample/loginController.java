package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    Database db=new Database();
    public User user=null;
    public Text loginMessage;
    public TextField enterUserIDField;
    public TextField enterPasswordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;

    @FXML
    private ImageView bgImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private ImageView userImageView;

    @Override
    public void initialize(URL uri, ResourceBundle resourceBundle) {
        loginMessage.setText(" ");
        File bgFile=new File("Images/bgLOGIN.jpg");
        Image bgImage = new Image(bgFile.toURI().toString());
        bgImageView.setImage(bgImage);

        File lockFile = new File("Images/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

        File userFile = new File("Images/dumbell.png");
        Image userImage = new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);


    }

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        if(enterUserIDField.toString()!= null && enterPasswordField.toString()!= null){
           User user=db.authenticateLogin(enterUserIDField.getText(),enterPasswordField.getText());
           if(user!=null){
               FXMLLoader loader=new FXMLLoader(getClass().getResource("mainScreen.fxml"));
               try {
                   Parent root=loader.load();
                   mainScreenController con=loader.getController();
                   con.getUserObject(user);
                   Stage stage=new Stage();
                   stage.setScene(new Scene(root));
                   stage.show();
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
           else{
               loginMessage.setFill(Color.RED);
               loginMessage.setText("Invalid login!");
           }

        }
        else{
            loginMessage.setFill(Color.RED);
            loginMessage.setText("All fields required");
        }
    }


    @FXML
    public void cancelButtonOnAction(ActionEvent event) throws IOException {
        Scene quizScene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        primaryStage.setScene(quizScene);
        primaryStage.show();
    }
}
