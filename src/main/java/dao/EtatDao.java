package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Etat;

public class EtatDao {

    public Vector<Etat> findAll() throws Exception {
        Vector<Etat> resultat = new Vector<>();
        String sql = "SELECT * FROM Etat";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Etat etat = new Etat();
                etat.setId(rs.getInt("id"));
                etat.setLibelle(rs.getString("libelle"));
                etat.setTypeFonctionnel(rs.getString("typeFonctionnel"));
                etat.setEtatdemarche(rs.getInt("etatdemarche"));
                resultat.add(etat);
            }
        }
        return resultat;
    }
}
