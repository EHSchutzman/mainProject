package UserAccounts;

import DBManager.DBManager;
import DatabaseSearch.TTB_database;

import java.sql.Connection;
import java.sql.SQLException;

public class Authentication {
    DBManager manager = new DBManager();
    private String username;
    private String password;
    private Boolean isValid;
    private Boolean isAuthentic;
    private int authenticationLevel = 0;
    private String realName;
    private User foundUser;
    //list of applicaitons


    public Authentication(String username, String password, Boolean isValid, Boolean isAuthentic,
                          int authenticationLevel, String realName) {
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

    public void createUser(String firstName, String middleIn, String lastName, String username, String password, String email, String phone, int authenticationLevel) {
        DBManager manager = new DBManager();
        User user = new User(username, password, firstName, middleIn, lastName, email, phone, authenticationLevel);
        manager.persistUser(user);

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


        this.foundUser = manager.findUser("username = \'" + this.getUsername()+"\'");
        return true;
    }

    protected Connection DBConnect() throws SQLException {
        return TTB_database.connect();
    }

}
