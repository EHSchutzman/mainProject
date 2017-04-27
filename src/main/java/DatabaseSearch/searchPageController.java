package DatabaseSearch;

import DBManager.DBManager;
import Form.Form;
import Initialization.Main;
import UserAccounts.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The searchPageController will read and store data from the UI search page and pass it to the query builder in order to form an SQL query.
 * Note: all attributes are stored as strings in the database except for IDs.
 */
public class searchPageController {

    private Main main;
    // Database information
    public DBManager db = new DBManager();
    private ResultSet apprs;
    private ResultSet rs;
    public ObservableList<AppRecord> aR = FXCollections.observableArrayList();

    private String query;
    //VARIABLES FOR SEARCH CRITERIA:
    //Date info
    protected String from;
    protected String to;
    //Name info
    protected String brand;
    protected String product; //also known as fanciful search
    protected String name;
    //Type info
    protected boolean isMalt;
    protected boolean isSpirit;
    protected boolean isWine;

    //location code, also known as origin code
    protected String origin;
    //Application ID
    protected String appID;

    //VARIABLES FOR JAVAFX OBJECTS:
    @FXML
    private DatePicker dpDateRangeStart;
    @FXML
    private DatePicker dpDateRangeEnd;
    @FXML
    private TextField brand_name_text;
    @FXML
    private TextField product_name_text;
    @FXML
    private CheckBox malt_beverage_checkbox;
    @FXML
    private CheckBox distilled_spirit_checkbox;
    @FXML
    private CheckBox wine_checkbox;
    @FXML
    private TextField state_text;
    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private TextField txtAppID;

    // For the search results page
    @FXML
    TextField searchBox;

    @FXML
    // Handle a search - effectively a "main" function for our program
    protected void handleSearch() throws SQLException {

        System.out.println("Handles search!");

        // Handle search criteria
        System.out.println("in handle search" + searchCriteria());

        // Display our new data in the TableView
        displayData(searchCriteria());
        //displayApplication();

    }

    @FXML
    protected void handleInlineSearch() throws SQLException {

        //
        searchInlineCriteria();
    }

    // Function that reads the input entered into the search page and passes it to DBManager
    protected ObservableList<AppRecord> searchCriteria() {
        String params = "";
        try {
            //Set all variables equal to input data
            if(dpDateRangeStart.getValue() != null) {
                from = (dpDateRangeStart.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
            if (dpDateRangeEnd.getValue() != null) {
                to = (dpDateRangeEnd.getValue()).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
            if (brand_name_text.getText() != null) {
                brand = brand_name_text.getText();
            }
            if (product_name_text.getText() != null) {
                product = product_name_text.getText();
            }
            isMalt = malt_beverage_checkbox.isSelected();
            isSpirit = distilled_spirit_checkbox.isSelected();
            isWine = wine_checkbox.isSelected();
            if (state_text.getText() != null) {
                origin = state_text.getText();
            }


            params += " STATUS = \'Accepted\' ";

            if (dpDateRangeStart.getValue() != null && dpDateRangeEnd.getValue() != null) {
                params = "AND APPROVED_DATE BETWEEN '" + from + "' AND '" + to + "'";
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
            ArrayList<String> productArray = new ArrayList<>();
            ArrayList<String> typeArray = new ArrayList<>();
            ArrayList<String> originArray = new ArrayList<>();
            ArrayList<String> statusArray = new ArrayList<>();

            if(brand_name_text != null) {
                brandArray.add("BRAND_NAME");
                brandArray.add(brand);
                searchParams.add(brandArray);
            }
            if(product_name_text != null) {
                productArray.add("FANCIFUL_NAME");
                productArray.add(product);
                searchParams.add(productArray);
            }
            if(state_text != null) {
                originArray.add("SOURCE");
                originArray.add(origin);
                searchParams.add(originArray);
            }

            searchParams.add(statusArray);

            searchParams.add(typeArray);

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            System.out.println("OBSERVABLE LIST IS " + arr);
            main.userData.setObservableList(arr);
            System.out.println("MAIN HAS" + main.userData.getObservableList());
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

    protected ObservableList<AppRecord> searchInlineCriteria() {
        try {
            //Set all variables equal to input data
            name = searchBox.getText();
            isMalt = malt_beverage_checkbox.isSelected();
            isSpirit = distilled_spirit_checkbox.isSelected();
            isWine = wine_checkbox.isSelected();

            boolean firstCheck = false;

            String params = " WHERE STATUS = 'Accepted' AND";

            if (isMalt || isSpirit || isWine) {

                params += " (ALCOHOL_TYPE = ";

                if (isWine) {
                    params += "'Wine'";
                    firstCheck = true;
                }

                if (isSpirit && !firstCheck) {
                    params += "'Distilled Spirits'";
                    firstCheck = true;
                } else if (isSpirit && firstCheck) {
                    params += " OR ALCOHOL_TYPE = 'Distilled Spirits'";
                }

                if (isMalt && !firstCheck) {
                    params += "'Malt Beverages'";
                    firstCheck = true;
                } else if (isMalt && firstCheck) {
                    params += " OR ALCOHOL_TYPE = 'Malt Beverages'";
                }
                params += ")";
            }

            if (firstCheck) {
                params += " AND (UPPER(BRAND_NAME) LIKE UPPER('%" + name + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + name + "%'))";
            } else {
                params += " (UPPER(BRAND_NAME) LIKE UPPER('%" + name + "%') OR UPPER(FANCIFUL_NAME) LIKE UPPER('%" + name + "%'))";
            }


            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();

            ObservableList<AppRecord> arr = db.findLabels(searchParams, params);
            System.out.println("ARR IS " + arr);
            main.userData.setObservableList(arr);
            System.out.println("USERDATA IS " + main.userData.getObservableList());
            resultsTable.setItems(arr);
            resultsTable.refresh();

            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            return null;
        }
    }

    // Display DB data into a TableView
    protected void displayData(ObservableList<AppRecord> list) {

        try {
            main.displaySearchResultsPage(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Generate a CSV file of the current ResultSet
    // http://stackoverflow.com/questions/22439776/how-to-convert-resultset-to-csv

    //@TODO: Display application on click
    // Displays individual application information when user selects an application from the TableView (I don't think this will currently display any additional information, but it should work)
    protected void displayApplication(ObservableList<AppRecord> list) {

        // This block monitors the user's interaction with the tableview,
        //  determining when they double-click a row
        resultsTable.setItems(list);
        resultsTable.setRowFactory(tv -> {
            TableRow<AppRecord> row = new TableRow<>();

            // Open application if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    AppRecord rowData = row.getItem();

                    ArrayList<String> fieldList = new ArrayList<>();
                    fieldList.add("*");

                    // Get form form DB using selected row's ID
                    try {
                        Form viewForm = db.findSingleForm(rowData.getFormID(), fieldList);
                        // Open selected form in new window
                        main.userData.setForm(viewForm);
                        main.displayApprovedLabel(viewForm);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


    // Save a CSV of the results locally
    @FXML
    protected void generateCSV() {
        try {
            main.displayCSVOptionsPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setDisplay(Main main) {
        this.main = main;
    }

    public void setDisplay2(Main main, ObservableList<AppRecord> list) {
        this.main = main;
        displayApplication(list);
    }


    public void setDisplayUsers(Main main) {
        this.main = main;
        this.displayResults(); // Display TableView results
    }
    @FXML
    public void returnToMain(){
        try{
            if (main.userData.getUserInformation().getAuthenticationLevel() == 0) {
                main.setDisplayToDefaultMain();
            } else if (main.userData.getUserInformation().getAuthenticationLevel() == 1) {
                main.setDisplayToApplicantMain();
            } else if (main.userData.getUserInformation().getAuthenticationLevel() == 2) {
                main.setDisplayToAgentMain();
            } else if (main.userData.getUserInformation().getAuthenticationLevel() == 3) {
                main.setDisplayToSuperAgentMain();
            } else {
                main.setDisplayToMain();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void returnToSearch() {
        try {
            main.setDisplayToSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public TextField search_users_text;
    @FXML
    public CheckBox username_filter;
    @FXML
    public CheckBox email_filter;
    @FXML
    public CheckBox first_name_filter;
    @FXML
    public CheckBox last_name_filter;
    @FXML
    public ChoiceBox<String> authentication_filter;
    @FXML
    public TableColumn UserIDCol;
    @FXML
    public TableColumn UsernameCol;
    @FXML
    public TableColumn firstNameCol;
    @FXML
    public TableColumn lastNameCol;
    @FXML
    public TableColumn middleInitialCol;
    @FXML
    public TableColumn emailCol;
    @FXML
    public TableColumn phoneNumberCol;
    @FXML
    public TableColumn authenticationCol;
    @FXML
    public TableView<UserRecord> resultsTableUsers;
    public Button comma_separated_button;
    public Button tab_separated_button;
    public Button user_specified_button;
    public TextField user_specified_value_text;
    public Button close_button;

    public void initUserAuthenticationChoiceBox() {
        authentication_filter.getItems().addAll("0", "1", "2", "3");
        authentication_filter.setValue("0");
        authentication_filter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchUsers();
            }
        });
    }

    @FXML
    public void searchUsers() {
        // refresh the table view
        resultsTableUsers.refresh();
        resultsTableUsers.setItems(null);
        // build a query
        String searchText = search_users_text.getText();
        boolean usernameFilter = username_filter.isSelected();
        boolean emailFilter = email_filter.isSelected();
        boolean firstNameFilter = first_name_filter.isSelected();
        boolean lastNameFilter = last_name_filter.isSelected();
        String authenticationFilter = authentication_filter.getValue();
        String options = "";
        if (searchText != null && !searchText.isEmpty()) {
            if (usernameFilter) {
                if (!options.isEmpty()) {
                    options = options.concat(" and ");
                }
                options = options.concat("username like '%" + searchText + "%'");
            }
            if (emailFilter) {
                if (!options.isEmpty()) {
                    options = options.concat(" and ");
                }
                options = options.concat("email like '%" + searchText + "%'");
            }
            if (firstNameFilter) {
                if (!options.isEmpty()) {
                    options = options.concat(" and ");
                }
                options = options.concat("first_name like '%" + searchText + "%'");
            }
            if (lastNameFilter) {
                if (!options.isEmpty()) {
                    options = options.concat(" and ");
                }
                options = options.concat("last_name like '%" + searchText + "%'");
            }
        }
        if (authenticationFilter != null && !authenticationFilter.isEmpty()) {
            if (!options.isEmpty()) {
                options = options.concat(" and ");
            }
            options = options.concat("authentication=" + authenticationFilter);
        }
        ObservableList<UserRecord> userList = FXCollections.observableArrayList();
        userList.clear();
        userList = db.searchUsers(options);
        System.out.println(userList.size());
        // display users in the table view
        if (!userList.isEmpty()) {
            resultsTableUsers.setItems(userList);
            //resultsTableUsers.getItems().addAll(userList);
        }
    }

    @FXML
    public void displayResults() {
        resultsTableUsers.setRowFactory(tv -> {
            TableRow<UserRecord> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((event.getClickCount() == 2) && (!row.isEmpty())) {
                    UserRecord record = row.getItem();
                    // Get user from db using selected row
                    try {
                        String options = "user_id='" + record.getUserID() + "'";
                        User user = db.findUser(options);
                        resultsTableUsers.refresh();
                        main.displayEditUser(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML
    public void makeCSV() {
        DBManager manager = new DBManager();
        System.out.println("MAIN HAS NOW" + main.userData.getObservableList());

        manager.generateCSV(main.userData.getObservableList(), ",", ".csv");
    }

    @FXML
    public void makeTab() {
        DBManager manager = new DBManager();
        manager.generateCSV(main.userData.getObservableList(), "\t", ".txt");
    }

    @FXML
    public void makeUserSpecified() {
        DBManager manager = new DBManager();
        String separator = user_specified_value_text.getText();
        manager.generateCSV(main.userData.getObservableList(), separator, ".txt");
    }

    @FXML
    public void closeApplication() {

        // Close the window
        Stage stage = (Stage) close_button.getScene().getWindow();
        stage.close();

        // Display confirmation message
        try {
            main.displayConfirmationMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
