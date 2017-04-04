package UserAccounts;

import Initialization.AcitonController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;

public class AuthenticationController extends AcitonController{
    Authentication isAuthentic;
    User user;



    @FXML
    TextField username;
    TextField password;
    Label errorMessage;

    @FXML
    public User loginAction(){
        String name = username.getText();
        String pass = password.getText();

        isAuthentic = new Authentication(name,pass);
        if(isAuthentic.authenticate()) {

            //make a user
            user = new User(isAuthentic.getUsername(), isAuthentic.getRealName(), isAuthentic.getAuthenticationLevel());

        }
        else{
            //reset fields and wait
            //@TODO make extra checks for if name exists, and just that password is wrong
            password.setText(null);
            username.setText(null);

            errorMessage.setText("Incorrect username or password, please try again! 8^)");
            //
        }

        return user;
    }
}
