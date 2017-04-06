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
    private User foundUser;
    //list of applicaitons


    public Authentication(String username, String password, Boolean isValid, Boolean isAuthentic, int authenticationLevel, String realName) {
        this.username = username;
        this.password = password;
        this.isValid = isValid;
        this.isAuthentic = isAuthentic;
        this.authenticationLevel = authenticationLevel;
        this.realName = realName;
        this.foundUser = new User();
    }

    public Authentication() {
    }

    public User getFoundUser() {
        return foundUser;
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

    public void createUser(String userID, String fullName, String username, String password, String email, String phone) {
        String queryString;
        queryString = "Insert into USERS Values(" + userID + ",\'" + email + "\',\'" + username + "\',\'" + password + "\',\'" + fullName + "\',\'" + phone + "\', 0)";
        try {
            Connection c = DBConnect();
            Statement s = c.createStatement();
            s.executeUpdate(queryString);
            s.close();
            c.close();
            System.out.println(queryString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(queryString);

    }

    /**
     * Function checks username and password against database
     * returns true if it can successfully check a username (and password in furture)
     *
     * @return Function returns true if the user is authentic, false if not
     */
    public Boolean authenticate() {
        String query = "";
        int uid = 0;
        String username = null;
        String email = null;
        String phoneNum = null;
        String realName = null;
        int authenticationLevel = 0;

        query = "SELECT * FROM USERS WHERE USERS.USERNAME = " + "\'"+this.username+"\'";
        System.out.println(query);
        try {
            Connection c = DBConnect();
            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery(query);
            if(!rs.next()){
                System.out.println("No user found");
                this.foundUser = new User();


            }else {
                rs.beforeFirst();
                while (rs.next()) {
                    uid = rs.getInt(1);
                    realName = rs.getString(2);
                    username = rs.getString(3);
                    email = rs.getString(5);
                    phoneNum = rs.getString(6);
                    authenticationLevel = rs.getInt(7);

                }
                if (username != null) { //this means we found a user
                    this.foundUser = new User(uid, username, realName, email, phoneNum, authenticationLevel);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
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
