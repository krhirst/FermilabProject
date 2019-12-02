package login;

import application.FermiConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login {
    private int id;
    private String username;
    private String password;
    private boolean isAdmin;

    public Login(int id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public static ArrayList<Login> getLogins(FermiConnector dbConnection) {
        ResultSet resultSet = dbConnection.getData("logins");
        return parseLoginData(resultSet);
    }

    private static ArrayList<Login> parseLoginData(ResultSet results) {
        ArrayList<Login> data = new ArrayList<>();
        try {
            while (results.next()) {
                data.add(new Login(results.getInt(1), results.getString(2), results.getString(3),
                        results.getBoolean(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
