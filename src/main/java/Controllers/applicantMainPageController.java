package Controllers;

import DBManager.DBManager;
import Form.Form;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by DanielKim on 4/16/2017.
 */
public class applicantMainPageController extends UIController{
    ArrayList<ImageView> imageArrayList = new ArrayList<>();
    int count = 0;
    int i = 0;
    int index = 0;

    private DBManager dbManager = new DBManager();

    public slideshowController slideshow = new slideshowController();

    @FXML
    public HBox imagebox;
    @FXML
    private Button viewFormsButton;
    @FXML
    private Button submissionButton;
    @FXML
    private Button submitCSVButton;

    @FXML
    public void setDisplayToSearchPage() throws IOException {
        Stage stage;
        stage = (Stage) searchButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchPage.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        slideshow.stopAnimation();

    }

    @FXML
    public void submitCSV() {
        ArrayList<String> inputs = new ArrayList<>();
        FileChooser fc = new FileChooser();
        String currentDir = System.getProperty("user.dir");

        fc.setInitialDirectory(new File(currentDir));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files (.csv)", "*.csv"));
        File selectedFile = fc.showOpenDialog(null);

        Path path = FileSystems.getDefault().getPath(selectedFile.getPath());

        if (selectedFile != null) {
            try {
                //String path = selectedFile.getPath();
                Scanner inputStream = new Scanner(selectedFile);
                List<String> lines = Files.readAllLines(path, Charset.defaultCharset());
                for (String line : lines) {
                    i = 0;
                    String[] tokens = line.split(",");
                    System.out.println("tokens: " + tokens);
                    int pH_level;

                    String rep_id = tokens[0];
                    String permit_no = tokens[1];
                    String source = tokens[2];
                    System.out.println("this is source: " + source);
                    String serial_no = tokens[3];
                    String alcohol_type = tokens[4];
                    String brand_name = tokens[5];
                    String fanciful_name = tokens[6];
                    Double alcohol_content = Double.parseDouble(tokens[7]);
                    String applicant_street = tokens[8];
                    String applicant_city = tokens[9];
                    String applicant_state = tokens[10];
                    String applicant_zip = tokens[11];
                    String applicant_country = tokens[12];

                    String mailing_address;
                    if (tokens[13].equals("NA") || tokens[13].equals(" ")) {
                        mailing_address = applicant_street + "\n" + applicant_city + " " + applicant_state
                                + "," + applicant_zip + "\n" + applicant_country;
                    } else {
                        mailing_address = tokens[13];
                    }

                    String formula = tokens[14];
                    String phone_no = tokens[15];
                    String email = tokens[16];
                    String labeltext = tokens[17];
                    //label image here
                    Date submitdate = new Date(System.currentTimeMillis()); //wil not be overwritten probably
                    String signature = tokens[18];

                    // Wines only
                    String vintage_year = null;
                    String grape_varietals = null;
                    String wine_appellation = null;
                    if (alcohol_type.equals("Wine")) {
                        System.out.println("we here");
                        vintage_year = tokens[19];
                        pH_level = (Integer.parseInt(tokens[20]));
                        grape_varietals = tokens[21];
                        wine_appellation = tokens[22];
                    } else {
                        pH_level = -1;
                    }

                    // Determine which checkboxes were selected
                    // Make a temporary array to store the boolean values set them to the Form object, same with string array
                    ArrayList<Boolean> application_type = new ArrayList<Boolean>();
                    for (int i = 0; i < 5; i++) {
                        application_type.add(false);
                    }
                    ArrayList<String> application_type_text = new ArrayList<String>();
                    for (int i = 0; i < 5; i++) {
                        application_type_text.add(null);
                    }

                    if (tokens[23].equals("1")) {//Certificate of Label Approval
                        application_type.set(0, true);
                    }

                    if (tokens[23].equals("2")) {//Certificate of Exemption from Label Approval
                        application_type_text.set(1, tokens[24]); //For Sale in: (State abbrv.)
                        application_type.set(1, true);
                    }

                    if (tokens[23].equals("3")) {//Distinctive Bottle Approval, Total Bottle
                        application_type_text.set(2, tokens[24]); //Capacity Before Closure
                        application_type.set(2, true);
                    }

                    if (tokens[23].equals("4")) {//Resubmission After Rejection
                        application_type_text.set(3, tokens[24]); //TTB ID
                        application_type.set(3, true);
                    }

                    Form form = new Form(rep_id, permit_no, source, serial_no, alcohol_type,
                            brand_name, fanciful_name, alcohol_content, applicant_street, applicant_city, applicant_state,
                            applicant_zip, applicant_country, mailing_address, formula, phone_no, email,
                            labeltext, null, submitdate, signature, "Pending", null, menuBarSingleton.getInstance().getGlobalData().getUserInformation().getUid(),
                            null, null, vintage_year, pH_level, grape_varietals, wine_appellation, application_type, application_type_text,
                            null);

                    System.out.println("app type: " + form.getapplication_type());
                    System.out.println("app type text: " + form.getapplication_type_text());
                    System.out.println("vintage year: " + vintage_year);
                    System.out.println("mailing address: " + form.getmailing_address());
                    System.out.println("alcohol type: " + alcohol_type);
                    dbManager.persistForm(form);
                }
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid file or no file chosen.");
        }
    }

    /**
     * Redirects to applicationStatusForApplicant.fxml
     * @throws IOException - throws exception
     */
    @FXML
    public void initialize(){
        super.init(main);
    }

    /**
     * Redirects to applicationStatusForApplicant.fxml
     *
     * @throws IOException - throws exception
     */
    @FXML
    public void setDisplayToApplicationStatusForApplicant() throws IOException {
        BorderPane pane = main.getBorderPane();
        Stage stage;
        stage = (Stage) viewFormsButton.getScene().getWindow();
        pane.setTop(menuBarSingleton.getInstance().getBar());
        pane.setLeft(menuBarSingleton.getInstance().getApplicationsForApplicantPane());
        menuBarSingleton.getInstance().getMenuBarController().menuTitle.setText("Submitted Applications");
        applicationStatusForApplicantController controller = menuBarSingleton.getInstance().getApplicationStatusForApplicantController();
        menuBarController menuBarController = menuBarSingleton.getInstance().getMenuBarController();
        menuBarController.setOnSearchPage(true);
        menuBarController.setSearchType(1);
        controller.initApplicationStatusTableView();
    }

    @FXML
    public void setDisplayToIter2application() throws IOException {
        BorderPane borderPane = main.getBorderPane();
        borderPane.setBottom(null);
        borderPane.setLeft(null);
        FXMLLoader loader = new FXMLLoader();
        URL iter2URL = getClass().getResource("iter2application.fxml");
        menuBarSingleton.getInstance().getMenuBarController().menuTitle.setText("Submit New Label Application");
        loader.setLocation(iter2URL);
        ScrollPane pane = loader.load();
        borderPane.setLeft(pane);
        slideshow.stopAnimation();


    }

}
