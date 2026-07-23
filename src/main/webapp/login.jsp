<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Connexion</title></head>
<body>
    <h2>Connexion</h2>
    <% if(request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="login" method="post">
        Login: <input type="text" name="login" required><br><br>
        Mot de passe: <input type="password" name="pwd" required><br><br>
        <input type="submit" value="Se connecter">
    </form>
</body>
</html>
