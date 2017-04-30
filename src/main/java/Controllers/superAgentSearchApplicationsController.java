package Controllers;

import DBManager.DBManager;
import DatabaseSearch.SuperAgentAppRecord;
import UserAccounts.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Status: incomplete.
 */
public class superAgentSearchApplicationsController extends UIController{

    @FXML
    public TableView<SuperAgentAppRecord> resultsTable;
    @FXML
    private TextField searchBox;
    @FXML
    private CheckBox fancifulNameCheckbox, brandNameCheckbox, applicantNameCheckbox, agentNameCheckbox;

    private DBManager dbManager = new DBManager();
    private ObservableList<SuperAgentAppRecord> observableList;

    @FXML
    ObservableList<SuperAgentAppRecord> getSearchResults() {
        try {
            //Set all variables equal to input data
            String search = searchBox.getText();
            boolean fancifulNameCheckboxSelected = fancifulNameCheckbox.isSelected();
            boolean brandNameCheckboxSelected = brandNameCheckbox.isSelected();
            boolean applicantNameCheckboxSelected = applicantNameCheckbox.isSelected();
            boolean agentNameCheckboxSelected = agentNameCheckbox.isSelected();
            System.out.println(fancifulNameCheckboxSelected);
            System.out.println(brandNameCheckboxSelected);
            System.out.println(applicantNameCheckboxSelected);
            System.out.println(agentNameCheckboxSelected);
            ArrayList<ArrayList<String>> searchParams = new ArrayList<>();
            if(search != null && !search.isEmpty()) {
                if(fancifulNameCheckboxSelected) {
                    ArrayList<String> param = new ArrayList<>();
                    param.add("like");
                    param.add("fanciful_name");
                    param.add(search);
                    searchParams.add(param);
                }
                if(brandNameCheckboxSelected) {
                    ArrayList<String> param = new ArrayList<>();
                    param.add("like");
                    param.add("brand_name");
                    param.add(search);
                    searchParams.add(param);
                }
                if(applicantNameCheckboxSelected) {
                    ArrayList<String> param = new ArrayList<>();
                    param.add("in");
                    param.add("applicant_id");
                    ArrayList<User> users = dbManager.findUsers("username like '%" + search + "%'");
                    if(users != null && !users.isEmpty()) {
                        String unames = "";
                        for(int i = 0; i < users.size(); i++) {
                            unames = unames.concat("'" + users.get(i).getUid() + "'");
                            if(i < users.size() - 1) {
                                unames = unames.concat(",");
                            }
                        }
                        param.add(unames);
                        searchParams.add(param);
                    } else {
                        param.add("'NULL'");
                        searchParams.add(param);
                    }
                }
                if(agentNameCheckboxSelected) {
                    ArrayList<String> param = new ArrayList<>();
                    param.add("in");
                    param.add("agent_id");
                    ArrayList<User> users = dbManager.findUsers("username like '%" + search + "%'");
                    if(users != null && !users.isEmpty()) {
                        String unames = "";
                        for(int i = 0; i < users.size(); i++) {
                            unames = unames.concat("'" + users.get(i).getUid() + "'");
                            if(i < users.size() - 1) {
                                unames = unames.concat(",");
                            }
                        }
                        param.add(unames);
                        searchParams.add(param);
                    } else {
                        param.add("'NULL'");
                        searchParams.add(param);
                    }
                }
                if(!fancifulNameCheckboxSelected && !brandNameCheckboxSelected &&
                        !applicantNameCheckboxSelected && !agentNameCheckboxSelected) {
                    ArrayList<String> param1 = new ArrayList<>();
                    param1.add("fanciful_name");
                    param1.add(search);
                    searchParams.add(param1);
                    ArrayList<String> param2 = new ArrayList<>();
                    param2.add("brand_name");
                    param2.add(search);
                    searchParams.add(param2);
                }
            }
            System.out.println("Search Params: " + searchParams);
            ObservableList<SuperAgentAppRecord> array = dbManager.findLabelsForSuperAgent(searchParams);
            resultsTable.setItems(array);
            resultsTable.refresh();
            observableList = array;
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not build a query from search criteria.");
            observableList = null;
            return null;
        }
    }

    private void displayInspectApplications(SuperAgentAppRecord data) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("inspectApplications.fxml"));
            ScrollPane newWindow = loader.load();
            Scene scene = new Scene(newWindow, 1500, 1000);
            scene.getStylesheets().add(getClass().getResource("general.css").toExternalForm());
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.getScene().setRoot(newWindow);
            stage.show();
            inspectApplicationsController controller = loader.getController();
            controller.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Monitors user double click on results table
     */
    void initsuperAgentApplicationTableView() {
        resultsTable.setItems(null);
        resultsTable.setRowFactory(tv -> {
            TableRow<SuperAgentAppRecord> row = new TableRow<>();
            // Open window if row double-clicked
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    SuperAgentAppRecord rowData = row.getItem();
                    try {
                        // Open selected row in new window
                        displayInspectApplications(rowData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

}
