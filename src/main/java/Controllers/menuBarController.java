package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Created by Leo on 4/26/2017.
 */
public class menuBarController extends UIController {

    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button aboutButtion;
    @FXML
    private Button backButton;

    public TextField getSearchBar(){
        return this.searchBar;
    }
}
