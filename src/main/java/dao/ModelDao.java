package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Model;

public class ModelDao {

    public Vector<Model> findAll() throws Exception{
        Vector <Model> resulat = new Vector<>();
        String sql = """
                SELECT id_modele, libelle FROM modele
                """;
        try(Connection connection = Connexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ){
            while (rs.next()) {
                Model modele = new Model();
                modele.setId_model(rs.getInt("id_modele"));
                modele.setLibelle(rs.getString("libelle"));
                resulat.add(modele);
            }
        }
        return resulat;
    }

    public Model getModelById(Integer id) throws Exception{
        
        Model resulat = new Model();
        String sql = """
                SELECT id_modele, libelle FROM modele WHERE id_modele = ?
                """;
        try(Connection connection = Connexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Model modele = new Model();
                    modele.setId_model(rs.getInt("id_modele"));
                    modele.setLibelle(rs.getString("libelle"));
                }
            }
            
        }

        return resulat;
    }

    public String getMarqueLibelle_fromId(Integer id) throws Exception{
        String resultat = new String();
        String sql = """
                    SELECT libelle_marque
                    FROM v_modele
                    WHERE id_modele = ?
                """;
        try( Connection connection = Connexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    resultat = rs.getString("libelle_marque");
                }
            }

        }
        return resultat;
    }

    public String getModelLibelle_fromId(Integer id) throws Exception{
        String resultat = new String();
        String sql = """
                    SELECT libelle
                    FROM v_modele
                    WHERE id_modele = ?
                """;
        try( Connection connection = Connexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)
        ){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    resultat = rs.getString("libelle");
                }
            }

        }
        return resultat;
    }
}
