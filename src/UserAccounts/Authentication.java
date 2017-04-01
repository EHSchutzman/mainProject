package UserAccounts;

/**
 * Created by eschutzman on 4/1/17.
 */
public class Authentication {
    private String username;
    private String password;
    private Boolean isValid;
    private Boolean isAuthentic;

    public Authentication(String username, String password, Boolean isValid, Boolean isAuthentic) {
        this.username = username;
        this.password = password;
        this.isValid = isValid;
        this.isAuthentic = isAuthentic;
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
}
