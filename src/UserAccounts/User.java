package UserAccounts;

public class User {
    private String username;
    private String realName;
    private int authenticationLevel;

    public User(String username, String realName, int authenticationLevel) {
        this.username = username;
        this.realName = realName;
        this.authenticationLevel = authenticationLevel;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAuthenticationLevel() {
        return authenticationLevel;
    }

    public void setAuthenticationLevel(int authenticationLevel) {
        this.authenticationLevel = authenticationLevel;
    }

}
