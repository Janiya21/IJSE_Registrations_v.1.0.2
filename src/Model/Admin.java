package Model;

public class Admin {
    private String username;
    private String password;
    private String branch;

    public Admin(String username, String password, String branch) {
        this.username = username;
        this.password = password;
        this.branch = branch;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
