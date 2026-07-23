package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Connexion;
import dao.OrdinateurDao;
import model.Ordinateur;

public class StatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.equals("/page1")) {
            afficherPage1(req, resp);
            return;
        }

        if (pathInfo != null && pathInfo.equals("/ordi_etat")) {
            afficherOrdiEtat(req, resp);
            return;
        }

        resp.sendRedirect("ordi");
    }

    private void afficherPage1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String datej = req.getParameter("datej");
        String typePanne = req.getParameter("type_panne");

        if (datej == null || datej.isEmpty()) {
            // Utiliser la date du jour par defaut
            java.time.LocalDate today = java.time.LocalDate.now();
            datej = today.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }

        // Convert date from JJ/MM/AAAA to YYYY-MM-DD for SQL query
        String dateSql = convertirDate(datej);

        java.util.List<java.util.Map<String, String>> fonctionnels = new java.util.ArrayList<>();
        java.util.List<java.util.Map<String, String>> nonFonctionnels = new java.util.ArrayList<>();

        try {
            // Query to get statistics by date and optionally by type de panne
            StringBuilder sql = new StringBuilder("""
                SELECT oe.idordi, oe.etat_libelle, oe.date_etat, oe.disfonctionnement,
                       e.etatdemarche, e.typeFonctionnel, tp.libelle as type_panne_libelle
                FROM ordinateuretat oe
                LEFT JOIN Etat e ON oe.etat_libelle = e.libelle
                LEFT JOIN type_panne tp ON oe.type_panne_id = tp.id
                WHERE oe.date_etat = ?
            """);

            if (typePanne != null && !typePanne.isEmpty()) {
                sql.append(" AND tp.libelle = ?");
            }

            sql.append(" ORDER BY oe.idordi, oe.id");

            try (Connection connection = Connexion.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql.toString())) {
                ps.setDate(1, java.sql.Date.valueOf(dateSql));
                if (typePanne != null && !typePanne.isEmpty()) {
                    ps.setString(2, typePanne);
                }
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        java.util.Map<String, String> row = new java.util.HashMap<>();
                        row.put("idordi", String.valueOf(rs.getInt("idordi")));
                        row.put("etat_libelle", rs.getString("etat_libelle"));
                        row.put("date_etat", rs.getDate("date_etat").toString());
                        row.put("disfonctionnement", rs.getString("disfonctionnement") != null ? rs.getString("disfonctionnement") : "");
                        row.put("typeFonctionnel", rs.getString("typeFonctionnel"));
                        row.put("type_panne_libelle", rs.getString("type_panne_libelle"));

                        int etatdemarche = rs.getInt("etatdemarche");
                        if (etatdemarche == 1) {
                            fonctionnels.add(row);
                        } else {
                            nonFonctionnels.add(row);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de la recuperation des statistiques: " + e.getMessage());
        }

        req.setAttribute("datej", datej);
        req.setAttribute("typePanne", typePanne);
        req.setAttribute("fonctionnels", fonctionnels);
        req.setAttribute("nonFonctionnels", nonFonctionnels);

        req.getRequestDispatcher("page1.jsp").forward(req, resp);
    }

    private void afficherOrdiEtat(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, List<Map<String, String>>> groupedData = new LinkedHashMap<>();

        try {
            String sql = """
                SELECT oe.idordi, oe.etat_libelle, oe.date_etat, oe.disfonctionnement,
                       e.etatdemarche, e.typeFonctionnel, tp.libelle as type_panne_libelle
                FROM ordinateuretat oe
                LEFT JOIN Etat e ON oe.etat_libelle = e.libelle
                LEFT JOIN type_panne tp ON oe.type_panne_id = tp.id
                ORDER BY oe.date_etat DESC, oe.idordi
            """;

            try (Connection connection = Connexion.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String disfonctionnement = rs.getString("disfonctionnement");
                    String etatLibelle = rs.getString("etat_libelle");
                    String typePanneLibelle = rs.getString("type_panne_libelle");
                    String typePanne = (typePanneLibelle != null && !typePanneLibelle.isEmpty()) ? typePanneLibelle : "Autre";

                    Map<String, String> row = new HashMap<>();
                    row.put("idordi", String.valueOf(rs.getInt("idordi")));
                    row.put("idetat", String.valueOf(rs.getInt("id")));
                    row.put("date_etat", rs.getDate("date_etat").toString());
                    row.put("etat_libelle", etatLibelle);
                    row.put("typeFonctionnel", rs.getString("typeFonctionnel"));
                    row.put("disfonctionnement", disfonctionnement != null ? disfonctionnement : "");
                    row.put("type_panne", typePanne);

                    groupedData.computeIfAbsent(typePanne, k -> new ArrayList<>()).add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Erreur lors de la recuperation des donnees: " + e.getMessage());
        }

        req.setAttribute("groupedData", groupedData);
        req.getRequestDispatcher("ordi_etat.jsp").forward(req, resp);
    }

    private String convertirDate(String datej) {
        // Format JJ/MM/AAAA to YYYY-MM-DD
        String[] parts = datej.split("/");
        if (parts.length == 3) {
            return parts[2] + "-" + parts[1] + "-" + parts[0];
        }
        return datej;
    }
}
