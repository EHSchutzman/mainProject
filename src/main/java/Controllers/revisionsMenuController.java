package Controllers;

import AgentWorkflow.AgentRecord;
import DBManager.DBManager;
import Form.Form;
import Initialization.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by eschutzman on 4/20/17.
 */
public class revisionsMenuController extends UIController{

    public Main main;
    public Form form;

    public DBManager DBManager = new DBManager();
    public TableView<AgentRecord> resultsTable;
    public ArrayList<Boolean> boolArray = new ArrayList<Boolean>();

    @FXML
    public CheckBox revision1_checkbox;
    @FXML
    public CheckBox revision2_checkbox;
    @FXML
    public CheckBox revision3_checkbox;
    @FXML
    public CheckBox revision4_checkbox;
    @FXML
    public CheckBox revision5_checkbox;
    @FXML
    public CheckBox revision6_checkbox;
    @FXML
    public CheckBox revision7_checkbox;
    @FXML
    public CheckBox revision8_checkbox;
    @FXML
    public CheckBox revision9_checkbox;
    @FXML
    public CheckBox revision10_checkbox;
    @FXML
    public CheckBox revision11_checkbox;
    @FXML
    public CheckBox revision12_checkbox;

    @FXML
    public Button close_button;
    @FXML
    public Button revise_button;
    @FXML
    public Button view_button;

    @FXML
    public void setDisplayToApplicantRevisionForm() throws IOException {
        Stage stage;
        stage=(Stage) revise_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reviseApplication.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        reviseApplicationController controller = loader.getController();
        controller.init(super.main);
        controller.createReviseForm(form, boolArray);
    }

    @FXML
    public void setDisplayToApplicantViewForm() throws IOException {
        try {
            Stage stage;
            stage=(Stage) view_button.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("inspectApprovedLabel.fxml"));
            AnchorPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1500, 1000);
            scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            inspectApprovedLabelController controller = loader.getController();
            controller.setForm(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow() {

        // Close the window
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();

    }

    public void createRevisionsMenu(Form form, Main main) {
        //TODO get checkboxes to work
        for (int i = 0; i < 12; i++) {
            boolArray.add(false);
        }

        System.out.println("b4" + boolArray);

        revision1_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(0, true);
            }
        });

        revision2_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(1, true);
            }
        });

        revision3_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(2, true);
            }
        });

        revision4_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(3, true);
            }
        });

        revision5_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(4, true);
            }
        });

        revision6_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(5, true);
            }
        });

        revision7_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(6, true);
            }
        });

        revision8_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(7, true);
            }
        });

        revision9_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(8, true);
            }
        });

        revision10_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(9, true);
            }
        });

        revision11_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(10, true);
            }
        });

        revision12_checkbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                boolArray.set(11, true);
            }
        });

        System.out.println("after" + boolArray);
        System.out.println("in form controller" + form);

        this.form = form;
        this.main = main;
    }
}
