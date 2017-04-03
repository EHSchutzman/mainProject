package UserAccounts;

public class User {
    private String username;
    private String user;
    private int authenticationLevel;

    public User(String username, String user, int authenticationLevel) {
        this.username = username;
        this.user = user;
        this.authenticationLevel = authenticationLevel;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getAuthenticationLevel() {
        return authenticationLevel;
    }

    public void setAuthenticationLevel(int authenticationLevel) {
        this.authenticationLevel = authenticationLevel;
    }

}
