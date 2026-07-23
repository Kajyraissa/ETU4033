package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    public static java.sql.Connection getConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/ordinateurexam";
            String user = "postgres";
            String password = "postgres";

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion reussie a la base de donnees E_Kandra !");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL introuvable.");
            throw e;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion a la base de donnees: " + e.getMessage());
            throw e;
        }
        return con;
    }
}
