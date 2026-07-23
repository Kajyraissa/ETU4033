package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Marque;

public class MarqueDao {
    
    public Vector<Marque> findAll() throws Exception{
        Vector <Marque> resulat = new Vector<>();
        String sql = """
                SELECT id_marque, libelle FROM marque
                """;
        try(Connection connection = Connexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ){
            while (rs.next()) {
                Marque marque = new Marque();
                marque.setId_marque(rs.getInt("id_marque"));
                marque.setLibelle(rs.getString("libelle"));
                resulat.add(marque);
            }
        }
        return resulat;
    }
}
