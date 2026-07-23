package model;
import java.sql.*;

public class Utilisateur {
    private int id;
    private String login;
    private String pwd;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static int verifier(String login, String pwd) {
        int result = 0;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ordinateurexam", "postgres", "postgres");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utilisateur WHERE login=? AND pwd=?");
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = 1; // 1 = VRAI
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
