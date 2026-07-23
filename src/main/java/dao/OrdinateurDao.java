package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Etat;
import model.Ordinateur;

public class OrdinateurDao {

    public void saveOrdinateur(Ordinateur ordinateur) throws Exception {
        String sql = """
                    INSERT INTO ordinateur(id_modele, ram, processeur, disque_dur)
                    VALUES(?,?,?,?)
                """;
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ordinateur.getId_modele());
            ps.setInt(2, ordinateur.getRam());
            ps.setString(3, ordinateur.getProcesseur());
            ps.setInt(4, ordinateur.getDisque_dur());
            ps.executeUpdate();
        }
    }

    public void updateOrdinateur(Ordinateur ordinateur) throws Exception {
        String sql = """
                    UPDATE ordinateur
                    SET id_modele = ?, ram = ?, processeur = ?, disque_dur = ?
                    WHERE id_ordinateur = ?
                """;
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ordinateur.getId_modele());
            ps.setInt(2, ordinateur.getRam());
            ps.setString(3, ordinateur.getProcesseur());
            ps.setInt(4, ordinateur.getDisque_dur());
            ps.setInt(5, ordinateur.getId_ordinateur());
            ps.executeUpdate();
        }
    }

    public Ordinateur getById(Integer id) throws Exception {
        Ordinateur ordinateur = null;
        String sql = "SELECT * FROM ordinateur WHERE id_ordinateur = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ordinateur = new Ordinateur();
                    ordinateur.setId_ordinateur(rs.getInt("id_ordinateur"));
                    ordinateur.setId_modele(rs.getInt("id_modele"));
                    ordinateur.setRam(rs.getInt("ram"));
                    ordinateur.setProcesseur(rs.getString("processeur"));
                    ordinateur.setDisque_dur(rs.getInt("disque_dur"));
                }
            }
        }
        return ordinateur;
    }

    public void deleteOrdinateur(Integer id) throws Exception {
        String sql = "DELETE FROM ordinateur WHERE id_ordinateur = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Vector<Ordinateur> findAll() throws Exception {
        Vector<Ordinateur> resultat = new Vector<>();
        String sql = "SELECT * FROM ordinateur";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ordinateur ordinateur = new Ordinateur();
                ordinateur.setId_ordinateur(rs.getInt("id_ordinateur"));
                ordinateur.setId_modele(rs.getInt("id_modele"));
                ordinateur.setRam(rs.getInt("ram"));
                ordinateur.setProcesseur(rs.getString("processeur"));
                ordinateur.setDisque_dur(rs.getInt("disque_dur"));
                resultat.add(ordinateur);
            }
        }
        return resultat;
    }

    public void saveOrdinateurEtat(Integer idordi, String etat_libelle, String date_etat, String disfonctionnement, Integer typePanneId) throws Exception {
        String sql = """
                    INSERT INTO ordinateuretat(idordi, etat_libelle, date_etat, disfonctionnement, type_panne_id)
                    VALUES(?,?,?,?,?)
                """;
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idordi);
            ps.setString(2, etat_libelle);
            ps.setDate(3, java.sql.Date.valueOf(date_etat));
            ps.setString(4, disfonctionnement);
            if (typePanneId != null) {
                ps.setInt(5, typePanneId);
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();
        }
    }

    public Etat getLastEtatByOrdiId(Integer idordi) throws Exception {
        Etat etat = null;
        String sql = """
                    SELECT oe.etat_libelle, e.typeFonctionnel, e.etatdemarche, e.id
                    FROM ordinateuretat oe
                    LEFT JOIN Etat e ON oe.etat_libelle = e.libelle
                    WHERE oe.idordi = ?
                    ORDER BY oe.date_etat DESC, oe.id DESC
                    LIMIT 1
                """;
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idordi);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    etat = new Etat();
                    etat.setId(rs.getInt("id"));
                    etat.setLibelle(rs.getString("etat_libelle"));
                    etat.setTypeFonctionnel(rs.getString("typeFonctionnel"));
                    etat.setEtatdemarche(rs.getInt("etatdemarche"));
                }
            }
        }
        return etat;
    }

    public Vector<Ordinateur> findAllOrdinateurEtat() throws Exception {
        Vector<Ordinateur> resultat = new Vector<>();
        String sql = """
                    SELECT o.id_ordinateur, o.id_modele, o.ram, o.processeur, o.disque_dur,
                           oe.etat_libelle, oe.date_etat, oe.disfonctionnement
                    FROM ordinateur o
                    LEFT JOIN ordinateuretat oe ON o.id_ordinateur = oe.idordi
                    ORDER BY o.id_ordinateur, oe.date_etat DESC
                """;
        try (Connection connection = Connexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ordinateur ordinateur = new Ordinateur();
                ordinateur.setId_ordinateur(rs.getInt("id_ordinateur"));
                ordinateur.setId_modele(rs.getInt("id_modele"));
                ordinateur.setRam(rs.getInt("ram"));
                ordinateur.setProcesseur(rs.getString("processeur"));
                ordinateur.setDisque_dur(rs.getInt("disque_dur"));
                resultat.add(ordinateur);
            }
        }
        return resultat;
    }
}
