package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.TypePanne;

public class TypePanneDao {

    public Vector<TypePanne> findAll() throws Exception {
        Vector<TypePanne> resultat = new Vector<>();
        String sql = "SELECT * FROM type_panne";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TypePanne typePanne = new TypePanne();
                typePanne.setId(rs.getInt("id"));
                typePanne.setLibelle(rs.getString("libelle"));
                resultat.add(typePanne);
            }
        }
        return resultat;
    }
}
