<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
    <title>Détail Ordinateurs/États</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 100%; margin: 10px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .type-section { margin: 20px 0; padding: 15px; border: 1px solid #ccc; border-radius: 5px; }
        .type-alimentation { background-color: #f8d7da; }
        .type-disque-dur { background-color: #fff3cd; }
        .type-carte-mere { background-color: #d1ecf1; }
        .type-fonctionnel { background-color: #d4edda; }
        .type-autre { background-color: #e2e3e5; }
        a { margin: 0 10px; }
    </style>
</head>
<body>
    <h1>Détail des Ordinateurs et États</h1>

    <%
        Map<String, List<Map<String, String>>> groupedData =
            (Map<String, List<Map<String, String>>>) request.getAttribute("groupedData");

        if (groupedData != null && !groupedData.isEmpty()) {
            for (Map.Entry<String, List<Map<String, String>>> entry : groupedData.entrySet()) {
                String typePanne = entry.getKey();
                List<Map<String, String>> rows = entry.getValue();

                // Déterminer la classe CSS selon le type
                String cssClass = "type-autre";
                if (typePanne.equalsIgnoreCase("Fonctionnel")) {
                    cssClass = "type-fonctionnel";
                } else if (typePanne.equalsIgnoreCase("Alimentation")) {
                    cssClass = "type-alimentation";
                } else if (typePanne.equalsIgnoreCase("Disque dur")) {
                    cssClass = "type-disque-dur";
                } else if (typePanne.equalsIgnoreCase("Carte mère")) {
                    cssClass = "type-carte-mere";
                }
    %>
        <div class="type-section <%= cssClass %>">
            <h2><%= typePanne %> (<%= rows.size() %>)</h2>
            <table>
                <tr>
                    <th>ID Ordinateur</th>
                    <th>ID État</th>
                    <th>Date</th>
                    <th>État</th>
                    <th>Type Fonctionnel</th>
                    <th>Disfonctionnement</th>
                </tr>
                <%
                    for (Map<String, String> row : rows) {
                %>
                    <tr>
                        <td><%= row.get("idordi") %></td>
                        <td><%= row.get("idetat") %></td>
                        <td><%= row.get("date_etat") %></td>
                        <td><%= row.get("etat_libelle") %></td>
                        <td><%= row.get("typeFonctionnel") %></td>
                        <td><%= row.get("disfonctionnement") %></td>
                    </tr>
                <%
                    }
                %>
            </table>
        </div>
    <%
            }
        } else {
    %>
        <p>Aucune donnée disponible.</p>
    <%
        }
    %>

    <br><br>
    <a href="stat/page1">Voir statistiques par date</a> |
    <a href="ordi">Voir liste ordinateurs</a> |
    <a href="Formulaire Etat.jsp">Ajouter un état</a> |
    <a href="login.jsp">Déconnexion</a>
</body>
</html>
