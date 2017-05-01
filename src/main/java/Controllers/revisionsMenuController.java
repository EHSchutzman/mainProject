package Controllers;

import Form.Form;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Status: incomplete, needs review.
 * TODO: make sure this works!
 */
public class revisionsMenuController extends UIController{

    @FXML
    private CheckBox revision1_checkbox, revision2_checkbox, revision3_checkbox, revision4_checkbox,
            revision5_checkbox, revision6_checkbox, revision7_checkbox, revision8_checkbox,
            revision9_checkbox, revision10_checkbox, revision11_checkbox, revision12_checkbox;
    @FXML
    private Button reviseButton, viewButton;

    private Form form = new Form();
    private ArrayList<Boolean> boolArray = new ArrayList<>();

    @FXML
    public void setDisplayToApplicantRevisionForm() throws IOException {
        Stage stage;
        stage=(Stage) reviseButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reviseApplication.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        reviseApplicationController controller = loader.getController();
        controller.init(super.main);
        controller.createReviseForm(form, boolArray);
    }

    /**
     * Redirects to inspectApprovedLabel.fxml - pop up
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicantViewForm() throws IOException {
        try {
            Stage stage;
            stage=(Stage) viewButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("viewLabel.fxml"));
            ScrollPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1000, 1000);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            inspectApprovedLabelController controller = loader.getController();
            controller.setForm(form);
            controller.createReviseForm(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createRevisionsMenu(Form form) {
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
    }

}