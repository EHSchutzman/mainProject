package UserAccounts;

import DatabaseSearch.QueryBuilder;
import DatabaseSearch.TTB_database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authentication {
    private String username;
    private String password;
    private Boolean isValid;
    private Boolean isAuthentic;
    private int authenticationLevel = 0;
    private String realName;
    //list of applicaitons


    public Authentication() {

    }

    protected Authentication(String username, String password) {
        this.username = username;
        this.password = password;
        this.isValid = false;
        this.isAuthentic = false;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Boolean getAuthentic() {
        return isAuthentic;
    }

    public void setAuthentic(Boolean authentic) {
        isAuthentic = authentic;
    }

    public int getAuthenticationLevel() {
        return authenticationLevel;
    }

    public void setAuthenticationLevel(int authenticationLevel) {
        this.authenticationLevel = authenticationLevel;
    }

    public void createUser(String userID, String email, String loginName, String password, String fullName){
        String queryString = "Hi";
        queryString = "Insert into USERS Values(" + userID + "," +email + "," + loginName + "," + password + ","+ fullName + ")";
        System.out.println(queryString);
        ResultSet rs = queryDB(queryString);

    }
    /**
     * Function checks username and password against database
     *
     * @return Function returns true if the user is authentic, false if not
     */
    public Boolean authenticate() {
        //query db for username
        try {

            QueryBuilder qb = new QueryBuilder("Users", "Users.LOGIN_NAME", "");
            ResultSet rs = queryDB(qb.getQuery());
            System.out.println(qb.getQuery());

            while (rs.next()) {
                String login = rs.getString("LOGIN_NAME");
                if (login.equals(this.getUsername())) {
                    this.isAuthentic = true;
                }
            }
            if (this.isAuthentic == true) {
                //check password
            }
            //
            //check username against database -- username is loginname field
            //check password against database
            //Set up a query to User table
            //check if both username and password are valid
            //if they are valid, set authentication to True, return true, else nope.
            this.isAuthentic = true;
            //gets fields and fills them in
            //realname is name field
            //get authentication level
            //get list

            return this.isAuthentic;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.isAuthentic;
    }

    protected Connection DBConnect() throws SQLException {
        return TTB_database.connect();
    }

    // Function will query the DB
    protected ResultSet queryDB(String query) {
        Connection c;
        Statement stmt;
        ResultSet rs = null;
        try {
            c = DBConnect();
            stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            stmt = null;
        }
        return rs;
    }
}
