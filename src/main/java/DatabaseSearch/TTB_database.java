package DatabaseSearch; /**
 * Created by WilsonWong on 3/19/2017.
 */
import java.sql.*;
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
            System.out.println("Click OK, compile the code and run it.");
            e.printStackTrace();
            return null;
        }

        System.out.println(" DB driver registered!");
        Connection connection = null;

        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:mainProject/Database/appDB;create=true");
            /*Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select APP.ORIGIN.ORIGIN_CODE, APP.ORIGIN.DESCRIPTION from APP.ORIGIN where ORIGIN_CODE = '0' OR ORIGIN_CODE = '11'");

            System.out.println("origin_code\tdescription");

            int count = 0;
            while(set.next()){
                String origin_code = set.getString("origin_code");
                String description = set.getString("description");
                System.out.println(origin_code+" "+description);
                count++;
            }

            System.out.println("Rows: " + count);
            set.close();
            statement.close();
            connection.close();*/
            return connection;

        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return null;
        }
    }
}
