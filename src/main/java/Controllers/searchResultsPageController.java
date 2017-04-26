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

import java.io.*;
import java.util.ArrayList;

/**
 * Status: incomplete.
 * TODO: clean code, make sure there are no WARNINGS, rename Buttons, add Doxygen comments
 */
public class searchResultsPageController extends UIController{

    private DBManager db = new DBManager();
    protected String search;

    @FXML
    public TableView<AppRecord> resultsTable;
    @FXML
    private Button return_to_search;
    @FXML
    private Button generate_csv_button; //TODO: make sure this works! naming convention!
    @FXML
    private TextField search_box;
    @FXML
    private CheckBox malt_beverage_checkbox, wine_checkbox, distilled_spirit_checkbox;

    //@FXML
    //protected void handleInlineSearch() throws SQLException {searchInlineCriteria();}

    //TODO Find out what people want for the goofy wacky and zany COLA search page


    /**
     * This function opens a pop up for the CSV display
     * @throws Exception
     */


    /**
     * Function handles all the searching functionality
     * @return
     */



    /**
     * Monitors user double click on results table
     */


}






