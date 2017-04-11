package UserAccounts;

public class User {
    private int uid;
    private String username;
    private String password;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String email;
    private String phoneNo;
    private int authenticationLevel;

    public User(int uid, String username, String password, String firstName, String middleInitial, String lastName, String email, String phoneNo, int authenticationLevel) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.authenticationLevel = authenticationLevel;
    }

    public User() {
        this.uid = -1;
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.middleInitial = null;
        this.lastName = null;
        this.email = null;
        this.phoneNo = null;
        this.authenticationLevel = 0;
    }

    public int getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public int getAuthenticationLevel() {
        return authenticationLevel;
    }
}
