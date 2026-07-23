<%@ page import="model.*" %>
<%@ page import="java.util.Vector" %>
<%
    Vector<Ordinateur> ordinateurs = (Vector<Ordinateur>) request.getAttribute("ordinateurs");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Ordinateurs</title>
</head>
<body>
    <h1>LISTE DES ORDINATEURS</h1>
    <h2>ETU004033</h2>

    <p>
        <a href="showFormOrdi">Ajouter un ordinateur</a> |
        <a href="Formulaire%20Etat.jsp">Ajouter un etat</a> |
        <a href="ordi/liste" target="_blank">Voir JSON</a>
    </p>

    <table border="1" style="border-collapse: collapse;">
        <tr>
            <th>ID</th>
            <th>ID_Modele</th>
            <th>Ram</th>
            <th>Processeur</th>
            <th>Disque dur</th>
            <th>Etat</th>
            <th>Action</th>
        </tr>
        <% for(Ordinateur ordinateur : ordinateurs){
            Model model = new Model();
            Etat etat = null;
            try {
                etat = ordinateur.getLastEtat();
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>

            <tr>
                <td><%= ordinateur.getId_ordinateur() %></td>
                <td><%= ordinateur.getId_modele() %></td>
                <td><%= ordinateur.getRam() %></td>
                <td><%= ordinateur.getProcesseur() %></td>
                <td><%= ordinateur.getDisque_dur() %></td>
                <td>
                    <% if (etat != null) { %>
                        <%= etat.getLibelle() %>
                    <% } else { %>
                        Non defini
                    <% } %>
                </td>
                <td>
                    <a href="showFormOrdi?id=<%= ordinateur.getId_ordinateur() %>">Modifier</a>
                    <a href="ordi?action=delete&id=<%= ordinateur.getId_ordinateur() %>" onclick="return confirm('Voulez-vous vraiment supprimer cet ordinateur ?');">Supprimer</a>
                </td>
            </tr>
        <% } %>
    </table>

    <br>
    <a href="login.jsp">Deconnexion</a>
</body>
</html>
