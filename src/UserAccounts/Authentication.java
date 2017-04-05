package UserAccounts;

public class Authentication {
    private String username;
    private String password;
    private Boolean isValid;
    private Boolean isAuthentic;
    private int authenticationLevel = 0;
    private String realName;
    //list of applicaitons


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

    /**
     * Function checks username and password against database
     *
     * @return Function returns true if the user is authentic, false if not
     */
    public Boolean authenticate() {
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
    }


}
