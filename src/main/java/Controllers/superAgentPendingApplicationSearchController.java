package Controllers;

import DBManager.DBManager;
import DatabaseSearch.AppRecord;
import Form.Form;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by eschutzman on 4/29/17.
 */
public class superAgentPendingApplicationSearchController extends UIController {
    //VARIABLES FOR SEARCH CRITERIA:
    //Search bar info
    protected String searchBarContent;
    //Date info
    protected String from;
    protected String to;
    //Name info
    protected String brand;
    protected String fanciful; //also known as fanciful search
    //Type info
    protected boolean isMalt;
    protected boolean isSpirit;
    protected boolean isWine;

    //variables for storing location info
    protected String stateInfo;
    protected String countryInfo;

    //For exporting to CSV
    private ObservableList<AppRecord> observableList;

    //Create DBManager object to perform database operations
    private DBManager db = new DBManager();

    private menuBarController mbc = menuBarSingleton.getInstance().getMenuBarController();

    //Variables for JavaFX buttons
    @FXML
    private TextField searchBar;
    @FXML
    private DatePicker dpDateRangeStart;
    @FXML
    private DatePicker dpDateRangeEnd;
    @FXML
    private TextField brandName;
    @FXML
    private TextField fancifulName;
    @FXML
    private CheckBox simpleMaltBeverageCheckbox;
    @FXML
    private CheckBox simpleOtherCheckbox;
    @FXML
    private CheckBox simpleWineCheckbox;
    @FXML
    private TextField state;
    @FXML
    private TextField country;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button return_to_main_button;
    @FXML
    private TextField userSpecifiedValueText;
    private DBManager dbManager = new DBManager();

/*
    //Function that returns the user to the main page
    @FXML
    protected void returnToMain() throws IOException{
        Stage stage;
        //@TODO use function from a menu bar controller to access buttons
        stage=(Stage) return_to_main_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    */

    //Populates the results table with data from the database
    protected void displayData(ObservableList<AppRecord> list) {
        try {
            resultsTable.refresh();
            resultsTable.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void simpleSearch() {
        ObservableList<AppRecord> list = dbManager.filterFormsSA(simpleMaltBeverageCheckbox.isSelected(), simpleWineCheckbox.isSelected(), simpleOtherCheckbox.isSelected());
        menuBarSingleton.getInstance().getGlobalData().setObservableListApp(list);
        resultsTable.setItems(list);
        resultsTable.refresh();
    }

    private void displayApprovedLabel(Form form) {
        try {
            Stage stage = new Stage();
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

    void initApplicationTableView() {
        resultsTable.setItems(null);
        resultsTable.setRowFactory(tv -> {
            TableRow<AppRecord> row = new TableRow<>();
            // Open window if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AppRecord rowData = row.getItem();
                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");
                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = db.findSingleForm(rowData.getFormID(), fieldList);
                        // Open selected form in new window
                        displayApprovedLabel(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }
    public void firstSearch(){

        ObservableList<AppRecord> list = db.findPendingApplications();
        menuBarSingleton.getInstance().getGlobalData().setObservableListApp(list);
        resultsTable.setItems(list);
        resultsTable.refresh();
    }

    @FXML
    public void refreshView(){
        System.out.println("super agent refresh");
        resultsTable.refresh();
    }
}
