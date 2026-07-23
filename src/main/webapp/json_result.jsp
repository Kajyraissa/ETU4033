<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="model.Etat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Resultat JSON</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        pre { background-color: #f4f4f4; padding: 15px; border-radius: 5px; overflow-x: auto; }
        .success { color: green; font-weight: bold; }
        .error { color: red; font-weight: bold; }
        a { margin: 0 10px; }
    </style>
</head>
<body>
    <h1>Resultat de l'enregistrement (JSON)</h1>

    <%
        String successMessage = (String) session.getAttribute("successMessage");
        String errorMessage = (String) session.getAttribute("errorMessage");
        String jsonResult = (String) session.getAttribute("jsonResult");

        // Supprimer les messages de la session apres affichage
        if (successMessage != null) {
            session.removeAttribute("successMessage");
    %>
        <p class="success"><%= successMessage %></p>
    <%
        }
        if (errorMessage != null) {
            session.removeAttribute("errorMessage");
    %>
        <p class="error"><%= errorMessage %></p>
    <%
        }
        if (jsonResult != null) {
            session.removeAttribute("jsonResult");
    %>
        <pre><%= jsonResult %></pre>
    <%
        } else if (errorMessage == null) {
    %>
        <p>Aucun resultat JSON disponible.</p>
    <%
        }
    %>

    <br><br>
    <a href="Formulaire Etat.jsp">Retour au formulaire</a> |
    <a href="ordi">Voir liste ordinateurs</a> |
    <a href="stat/page1">Statistiques</a>
</body>
</html>
