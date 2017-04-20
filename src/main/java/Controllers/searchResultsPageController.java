package Controllers;

import DatabaseSearch.AppRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Anthony on 4/20/2017.
 */
public class searchResultsPageController {

    private ResultSet rs;

    @FXML
    public TableView<AppRecord> resultsTable;

    @FXML
    private Button returnToMainButton;

    @FXML
    private Button generateCSVButton;

    @FXML
    protected void returnToMain() throws IOException {
        Stage stage;
        stage=(Stage) returnToMainButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected boolean saveCSV() {
        try {
            generateCSV();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error generating CSV!");
            return false;
        }

    }

    // Generate a CSV file of the current ResultSet
    // http://stackoverflow.com/questions/22439776/how-to-convert-resultset-to-csv
    protected void generateCSV() throws SQLException, FileNotFoundException {

        // Initialize file
        PrintWriter csvWriter = new PrintWriter(new File("TTB_Search_Results.csv"));

        while(rs.previous());

        ResultSet searchResults = rs;

        // Determine CSV size and headers
        ResultSetMetaData meta = rs.getMetaData();
        int numberOfColumns = meta.getColumnCount();
        System.out.println(numberOfColumns);
        String dataHeaders = "\"" + meta.getColumnName(1) + "\"";
        for (int i = 2; i < numberOfColumns + 1; i++) {
            dataHeaders += ",\"" + meta.getColumnName(i).replaceAll("\"", "\\\"") + "\"";
        }

        // Print headers to CSV
        csvWriter.println(dataHeaders);

        // Print data to CSV
        while (searchResults.next()) {
            String row = "\"" + searchResults.getString(1).replaceAll("\"", "\\\"") + "\"";
            for (int i = 2; i < numberOfColumns + 1; i++) {
                row += ",\"" + searchResults.getString(i).replaceAll("\"", "\\\"") + "\"";
            }
            csvWriter.println(row);
        }
        csvWriter.close();

    }
}
