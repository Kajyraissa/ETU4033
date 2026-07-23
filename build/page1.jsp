<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Statistiques par date</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .stat-box { border: 1px solid #ccc; padding: 15px; margin: 10px 0; border-radius: 5px; }
        .fonctionnel { background-color: #d4edda; }
        .non-fonctionnel { background-color: #f8d7da; }
        table { border-collapse: collapse; width: 100%; margin-top: 10px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .error { color: red; }
        a { margin: 0 10px; }
    </style>
</head>
<body>
    <h1>Statistiques par date</h1>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

    <form method="get" action="stat/page1">
        Date (JJ/MM/AAAA): <input type="text" name="datej" placeholder="06/07/2026" required>
        Type de panne:
        <select name="type_panne">
            <option value="">Tous</option>
            <option value="Alimentation">Alimentation</option>
            <option value="Disque dur">Disque dur</option>
            <option value="Carte mere">Carte mère</option>
        </select>
        <input type="submit" value="OK">
    </form>

    <br>

    <%
        String datej = (String) request.getAttribute("datej");
        String typePanne = (String) request.getAttribute("typePanne");
        List<Map<String, String>> fonctionnels = (List<Map<String, String>>) request.getAttribute("fonctionnels");
        List<Map<String, String>> nonFonctionnels = (List<Map<String, String>>) request.getAttribute("nonFonctionnels");

        if (datej != null) {
            String subtitle = datej;
            if (typePanne != null && !typePanne.isEmpty()) {
                subtitle += " - Type de panne: " + typePanne;
            }
    %>
        <h2>Resultats pour le <%= subtitle %></h2>

        <div class="stat-box fonctionnel">
            <h3>Fonctionnel (<%= fonctionnels != null ? fonctionnels.size() : 0 %>)</h3>
            <table>
                <tr>
                    <th>ID Ordinateur</th>
                    <th>Etat</th>
                    <th>Date</th>
                    <th>Disfonctionnement</th>
                    <th>Type Fonctionnel</th>
                </tr>
                <%
                    if (fonctionnels != null && !fonctionnels.isEmpty()) {
                        for (Map<String, String> row : fonctionnels) {
                %>
                    <tr>
                        <td><%= row.get("idordi") %></td>
                        <td><%= row.get("etat_libelle") %></td>
                        <td><%= row.get("date_etat") %></td>
                        <td><%= row.get("disfonctionnement") %></td>
                        <td><%= row.get("typeFonctionnel") %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="5">Aucun ordinateur fonctionnel pour cette date.</td></tr>
                <%
                    }
                %>
            </table>
        </div>

        <div class="stat-box non-fonctionnel">
            <h3>Non fonctionnel (<%= nonFonctionnels != null ? nonFonctionnels.size() : 0 %>)</h3>
            <table>
                <tr>
                    <th>ID Ordinateur</th>
                    <th>Etat</th>
                    <th>Date</th>
                    <th>Disfonctionnement</th>
                    <th>Type Fonctionnel</th>
                    <th>Type de panne</th>
                </tr>
                <%
                    if (nonFonctionnels != null && !nonFonctionnels.isEmpty()) {
                        for (Map<String, String> row : nonFonctionnels) {
                %>
                    <tr>
                        <td><%= row.get("idordi") %></td>
                        <td><%= row.get("etat_libelle") %></td>
                        <td><%= row.get("date_etat") %></td>
                        <td><%= row.get("disfonctionnement") %></td>
                        <td><%= row.get("typeFonctionnel") %></td>
                        <td><%= row.get("type_panne_libelle") %></td>
                    </tr>
                <%
                        }
                    } else {
                %>
                    <tr><td colspan="6">Aucun ordinateur non fonctionnel pour cette date.</td></tr>
                <%
                    }
                %>
            </table>
        </div>
    <%
        }
    %>

    <br><br>
    <a href="ordi">Voir liste ordinateurs</a> |
    <a href="stat/ordi_etat">Voir detail ordinateurs/etats</a> |
    <a href="Formulaire Etat.jsp">Ajouter un etat</a> |
    <a href="login.jsp">Deconnexion</a>
</body>
</html>
