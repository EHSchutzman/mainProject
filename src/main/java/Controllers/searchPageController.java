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
 * Status: needs work.
 * TODO: this is currently not used anywhere. May need to move this to searchPageController
 * TODO: - once advanced options are created in UI
 */
public class searchPageController extends UIController {

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
    private CheckBox advMaltBeverageCheckbox;
    @FXML
    private CheckBox advOtherCheckbox;
    @FXML
    private CheckBox advWineCheckbox;
    @FXML
    private TextField state;
    @FXML
    private TextField country;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private TextField userSpecifiedValueText;
    @FXML
    private RadioButton commaSeparated;
    @FXML
    private RadioButton tabSeparated;
    @FXML
    private RadioButton userSpecified;
    @FXML
    private RadioButton colonSeparated;

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

    @FXML
    public void initialize() {
        initApplicationTableView();
    }

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
    private void simpleSearch() {
        displayData(mbc.simpleSearch(simpleMaltBeverageCheckbox.isSelected(), simpleWineCheckbox.isSelected(), simpleOtherCheckbox.isSelected()));
    }

    @FXML
    protected ObservableList<AppRecord> advancedSearch() {
        try {
            String params = "";

            //Set all variables equal to input data
            if (dpDateRangeStart.getValue() != null) {
                from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
            if (dpDateRangeEnd.getValue() != null) {
                to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
            if (brandName.getText() != null) {
                brand = brandName.getText();
            }
            if (fancifulName.getText() != null) {
                fanciful = fancifulName.getText();
            }
            isMalt = advMaltBeverageCheckbox.isSelected();
            isSpirit = advOtherCheckbox.isSelected();
            isWine = advWineCheckbox.isSelected();
            if (state.getText() != null) {
                stateInfo = state.getText();
            }
            if (country.getText() != null) {
                countryInfo = country.getText();
            }

            params += " STATUS = \'Accepted\' ";

            if (dpDateRangeStart.getValue() != null && dpDateRangeEnd.getValue() != null) {
                params += "AND APPROVED_DATE BETWEEN '" + from + "' AND '" + to + "'";
            }

            if (dpDateRangeStart.getValue() != null && dpDateRangeEnd.getValue() == null) {
                params += "AND APPROVED_DATE BETWEEN '" + from + "' AND '01/01/3000'";
            }

            if (dpDateRangeStart.getValue() == null && dpDateRangeEnd.getValue() != null) {
                params += "AND APPROVED_DATE BETWEEN '01/01/0001' AND '" + to + "'";
            }

            boolean firstCheck = false;

            if (isMalt || isSpirit || isWine) {
                params += " AND (ALCOHOL_TYPE = ";

                if (isWine) {
                    params += "\'Wine\'";
                    firstCheck = true;
                }
                if (isSpirit && !firstCheck) {
                    params += "\'Distilled Spirits\'";
                    firstCheck = true;
                } else if (isSpirit && firstCheck) {
                    params += " OR ALCOHOL_TYPE = \'Distilled Spirit\'";
                }
                if (isMalt && !firstCheck) {
                    params += "\'Malt Beverages\'";
                    firstCheck = true;
                } else if (isMalt && firstCheck) {
                    params += " OR ALCOHOL_TYPE = \'Malt Beverages\'";
                }
                params += ")";

            }

            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
            ArrayList<String> brandArray = new ArrayList<>();
            ArrayList<String> fancifulArray = new ArrayList<>();
            ArrayList<String> typeArray = new ArrayList<>();
            ArrayList<String> stateArray = new ArrayList<>();
            ArrayList<String> countryArray = new ArrayList<>();
            ArrayList<String> statusArray = new ArrayList<>();

            if (brand != null) {
                brandArray.add("BRAND_NAME");
                brandArray.add(brand);
                searchParams.add(brandArray);
            }
            if (fanciful != null) {
                fancifulArray.add("FANCIFUL_NAME");
                fancifulArray.add(fanciful);
                searchParams.add(fancifulArray);
            }
            if (stateInfo != null) {
                stateArray.add("APPLICANT_STATE");
                stateArray.add(stateInfo);
                searchParams.add(stateArray);
            }
            if (countryInfo != null) {
                countryArray.add("APPLICANT_COUNTRY");
                countryArray.add(countryInfo);
                searchParams.add(countryArray);
            }

            searchParams.add(statusArray);

            searchParams.add(typeArray);

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            System.out.println("OBSERVABLE LIST IS " + arr);
            main.userData.setObservableListApp(arr);
            System.out.println("MAIN HAS" + main.userData.getObservableListApp());
            displayData(arr);
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

    private void displayApprovedLabel(Form form) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("viewLabel.fxml"));
            ScrollPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1500, 900);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            viewLabelController controller = loader.getController();
            System.out.println("Controller is " + controller);
            controller.setReviewForm(form);
            controller.createIncompleteForm();
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
                        System.out.println("FORM IS " + viewForm.getttb_id());
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

    @FXML
    private void onCommaSeparatedClick(){
        if(tabSeparated.isSelected() || userSpecified.isSelected() || colonSeparated.isSelected()){
            tabSeparated.setSelected(false);
            userSpecified.setSelected(false);
            colonSeparated.setSelected(false);
        }
    }

    @FXML
    private void onTabSeparatedClick(){
        if(userSpecified.isSelected() || commaSeparated.isSelected() || colonSeparated.isSelected()){
            commaSeparated.setSelected(false);
            userSpecified.setSelected(false);
            colonSeparated.setSelected(false);
        }
    }

    @FXML
    private void onUserSpecifiedClick(){
        if(tabSeparated.isSelected() || commaSeparated.isSelected() || colonSeparated.isSelected()){
            tabSeparated.setSelected(false);
            commaSeparated.setSelected(false);
            colonSeparated.setSelected(false);
        }
    }
    @FXML
    private void onColonSeparatedClick(){
        if(tabSeparated.isSelected() || commaSeparated.isSelected() || userSpecified.isSelected()){
            tabSeparated.setSelected(false);
            commaSeparated.setSelected(false);
            userSpecified.setSelected(false);
        }

    }


    //CSV OPTIONS:

    public void generateCSV(){
        if (commaSeparated.isSelected()){
            makeCSV();
        } else if(tabSeparated.isSelected()){
            makeTab();
        } else if(userSpecified.isSelected()){
            makeUserSpecified();
        }else if(colonSeparated.isSelected()){
            makeColon();
        }
    }

    void passListOfForms(ObservableList<AppRecord> listOfForms) {
        this.observableList = listOfForms;
    }

    /**
     * Function makes a csv file out of observable list in this controller.
     */
    @FXML
    public void makeCSV() {
        db.generateCSV(menuBarSingleton.getInstance().getGlobalData().getObservableListApp(), ",", ".csv");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void makeColon(){
        db.generateCSV(menuBarSingleton.getInstance().getGlobalData().getObservableListApp(), ":", ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function makes a tab delimited format text file out of observable list in this controller.
     */
    @FXML
    public void makeTab() {
        db.generateCSV(menuBarSingleton.getInstance().getGlobalData().getObservableListApp(), "\t", ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function passes a parameter from the fxml file and sets the delimiter to this character, then exports text file.
     */
    @FXML
    public void makeUserSpecified() {
        String separator = userSpecifiedValueText.getText();
        db.generateCSV(menuBarSingleton.getInstance().getGlobalData().getObservableListApp(), separator, ".txt");
        try {
            displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshView(){
        System.out.println("Refreshing");
        resultsTable.refresh();
    }
}
