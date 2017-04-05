package Form;
import Initialization.ActionController;
import Initialization.Main;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.util.Collection;

public class FormController{
    private Main main;
    public void setDisplay(Main main) {
        this.main = main;
    }

    private Collection<Form> listOfForms;

    @FXML
    private TextField repIDText;

    @FXML
    private TextField registryText;

    @FXML
    private CheckBox domestic;

    @FXML
    private CheckBox beer;

    @FXML
    private TextField brandName;

    @FXML
    private TextField address;

    @FXML
    private TextField mailAddress;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField email;

    @FXML
    private TextField alcoholPercentage;

    @FXML
    private TextField vintageYear;

    @FXML
    private TextField pHLevel;

    @FXML
    private DatePicker appDate;

    @FXML
    private TextField applicantName;

    @FXML
    private TextField alterVintageDate;

    @FXML
    private TextField alterpHLevel;

    @FXML
    private TextField alterWineAlcoholContent;

    @FXML
    private TextField alterBeerAlcoholContent;
}
