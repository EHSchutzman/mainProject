package DBManager; /**
 * Created by WilsonWong on 3/19/2017.
 */

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TTB_database {
    public static Connection connect() {
        System.out.println("-------Embedded  DB Connection Testing --------");

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(" DB Driver not found. Add the classpath to your module.");
            System.out.println("For IntelliJ do the following:");
            System.out.println("File | Project Structure, Modules, Dependency tab");
            System.out.println("Add by clicking on the green plus icon on the right of the window");
            System.out.println("Select JARs or directories. Go to the folder where the  JDK is installed");
            System.out.println("Select the folder java/jdk1.8.xxx/db/lib where xxx is the version.");
            System.out.println(" Click OK, compile the code and run it.");
            e.printStackTrace();
            return null;
        }

        System.out.println(" DB driver registered!");
        Connection connection = null;

        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:appDB;create=true");
            String makeFormTable = "CREATE TABLE FORM " + "(" +
                    "TTB_ID varchar(255) PRIMARY KEY NOT NULL, " +
                    "REP_ID varchar(255), "
                    + "PERMIT_NO varchar(255) DEFAULT '' NOT NULL, "
                    + "SOURCE varchar(8) DEFAULT '' NOT NULL," +
                    "SERIAL_NO varchar(255) DEFAULT '' NOT NULL," +
                    "ALCOHOL_TYPE varchar(255) DEFAULT '' NOT NULL," +
                    "BRAND_NAME varchar(255) DEFAULT '' NOT NULL," +
                    "FANCIFUL_NAME varchar(255)," +
                    "ALCOHOL_CONTENT float(52) DEFAULT 0 NOT NULL, " +
                    "APPLICANT_ADDRESS varchar(255) DEFAULT '' NOT NULL, "+
                    "MAILING_ADDRESS varchar(255), " + "FORMULA varchar(255), " +
                    "PHONE_NO varchar(20), " + "EMAIL varchar(255), " +
                    "LABEL_TEXT varchar(511), " + "SUBMIT_DATE date DEFAULT '' NOT NULL, " +
                    "SIGNATURE varchar(255) DEFAULT '' NOT NULL, "
                    + "APPLICANT_NAME varchar(255) DEFAULT '' NOT NULL, "
                    + "STATUS varchar(30) DEFAULT '' NOT NULL, " + "    AGENT_ID varchar(255), "
                    + "APPLICANT_ID varchar(255) DEFAULT '' NOT NULL " + "); "
                    + "CREATE UNIQUE INDEX FORM_APPLICANT_ID_UINDEX ON FORM (APPLICANT_ID)";
            Statement s = connection.createStatement();
            s.executeUpdate(makeFormTable);
            
            return connection;


        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        try {
            DBManager manager = new DBManager();
//           launch(args);
            Connection c = TTB_database.connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
