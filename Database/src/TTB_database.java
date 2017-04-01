/**
 * Created by WilsonWong on 3/19/2017.
 */
import java.sql.*;
public class TTB_database {
    public static void main(String[] args) {
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
            return;
        }

        System.out.println(" DB driver registered!");
        Connection connection = null;
        String myDB = "C:/Users/DanielKim/Desktop/WPI/CS/CS3733/Database/Database/myApps"; //CHANGE AS NECESSARY

        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:C:/Users/DanielKim/Desktop/WPI/CS/CS3733/Database/Database/myApps;create=true");
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("select * from APP.USERS, APP.AGENT, APP.HASAUTHENTICATION where USERS.USER_ID = HASAUTHENTICATION.USER_ID and AGENT.AGENT_ID = HASAUTHENTICATION.AGENT_ID and HASAUTHENTICATION.AUTHENTICATION = 0");

            System.out.println("user_id\temail\tlogin_name\tpassword\tname\tphone_number\taddress");

            int count = 0;
            while(set.next()){
                String user_id = set.getString("user_id");
                String email = set.getString("email");
                String login_name = set.getString("login_name");
                String password = set.getString("password");
                String name = set.getString("name");
                String phone_number = set.getString("phone_number");
                String address = set.getString("address");
                System.out.println(user_id + "\t" + email + "\t" + login_name + "\t" + password
                    + "\t" + name + "\t" + phone_number + "\t" + address);
                count++;
            }

            System.out.println("Rows: " + count);
            set.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }
        System.out.println(" DB connection established!");
    }
}
