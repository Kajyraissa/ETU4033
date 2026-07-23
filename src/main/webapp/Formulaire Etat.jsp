<%@ page import="dao.*" %>
<%@ page import="java.util.Vector" %>
<%@ page import="model.*" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    OrdinateurDao daoOrdi = new OrdinateurDao();
    EtatDao daoEtat = new EtatDao();
    TypePanneDao daoTypePanne = new TypePanneDao();
    Vector<Ordinateur> ordinateurs = new Vector();
    Vector<Etat> etats = new Vector();
    Vector<TypePanne> typesPanne = new Vector();

    try {
        ordinateurs = daoOrdi.findAll();
        etats = daoEtat.findAll();
        typesPanne = daoTypePanne.findAll();
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Date du jour pour le lien statistiques
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    // Afficher le resultat JSON si disponible
    String jsonResult = (String) request.getAttribute("jsonResult");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter Etat Ordinateur</title>
</head>
<body>
    <h2>Ajouter un etat a un ordinateur</h2>

    <form action="ordi" method="post">
        <input type="hidden" name="action" value="saveEtat">

        Ordinateur: <select name="id_ordinateur" required>
            <option value="">Selectionner un ordinateur</option>
            <% for (Ordinateur ordi : ordinateurs) { %>
                <option value="<%= ordi.getId_ordinateur() %>">
                    ID: <%= ordi.getId_ordinateur() %> - Ram: <%= ordi.getRam() %> - Proc: <%= ordi.getProcesseur() %>
                </option>
            <% } %>
        </select><br><br>

        Etat: <select name="etat_libelle" required>
            <option value="">Selectionner un etat</option>
            <% for (Etat etat : etats) { %>
                <option value="<%= etat.getLibelle() %>">
                    <%= etat.getLibelle() %> - <%= etat.getTypeFonctionnel() %>
                </option>
            <% } %>
        </select><br><br>

        Date: <input type="date" name="date_etat" required><br><br>

        Observation/Disfonctionnement: <input type="text" name="disfonctionnement"><br><br>

        Type de panne: <select name="type_panne_id" required>
            <option value="">Selectionner un type de panne</option>
            <% for (TypePanne typePanne : typesPanne) { %>
                <option value="<%= typePanne.getId() %>"><%= typePanne.getLibelle() %></option>
            <% } %>
        </select><br><br>

        <input type="submit" value="OK">
        <a href="ordi">Voir liste ordinateurs</a> |
        <a href="stat/page1?datej=<%= today %>">Statistiques</a> |
        <a href="stat/ordi_etat">Detail etat</a>
    </form>

    <% if (jsonResult != null) { %>
        <h3>Resultat JSON</h3>
        <pre><%= jsonResult %></pre>
    <% } %>
</body>
</html>
