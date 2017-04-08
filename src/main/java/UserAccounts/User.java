package UserAccounts;


import Form.Form;

public class User {
    private int uid;
    private Form form;
    private String username;
    private String realName;
    private String email;
    private String phoneNumber;
    private int authenticationLevel;

    public User(int uid, String username, String realName, String email, String phoneNumber, int authenticationLevel) {
        this.uid = uid;
        this.username = username;
        this.realName = realName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authenticationLevel = authenticationLevel;
    }

    public User() {
        this.uid = -1;
        this.username = null;
        this.realName = null;
        this.email = null;
        this.phoneNumber = null;
        this.authenticationLevel = 0;
        this.form = new Form();
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
