<%@ page import="dao.*" %>
<%@ page import="java.util.Vector" %>
<%@ page import="model.*" %>
<%
    ModelDao daoModel = new ModelDao();
    Vector<Model> models = new Vector();

    try {
        models = daoModel.findAll();
    } catch (Exception e) {
        e.printStackTrace();
    }

    Ordinateur ordiToEdit = (Ordinateur) request.getAttribute("ordiToEdit");

    String idOrdi = (ordiToEdit != null) ? String.valueOf(ordiToEdit.getId_ordinateur()) : "";
    int currentModelId = (ordiToEdit != null) ? ordiToEdit.getId_modele() : -1;
    String ramVal = (ordiToEdit != null) ? String.valueOf(ordiToEdit.getRam()) : "";
    String procVal = (ordiToEdit != null) ? ordiToEdit.getProcesseur() : "";
    String ddVal = (ordiToEdit != null) ? String.valueOf(ordiToEdit.getDisque_dur()) : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire Ordinateur</title>
</head>
<body>
    <h2>Ajouter/Modifier un Ordinateur</h2>

    <form action="ordi" method="post">
        <input type="hidden" name="id_ordinateur" value="<%= idOrdi %>">

        ID Modele: <select name="model" required>
            <option value="">Selectionner un modele</option>
            <% for (Model modele : models) {
                String selected = (modele.getId_model() == currentModelId) ? "selected" : "";
            %>
                <option value="<%= modele.getId_model() %>" <%= selected %>><%= modele.getLibelle()%></option>
            <% } %>
        </select><br><br>

        Ram: <input type="number" name="ram" value="<%= ramVal %>" required><br><br>

        Processeur: <input type="text" name="processeur" value="<%= procVal %>" required><br><br>

        Disque dur: <input type="number" name="disque_dur" value="<%= ddVal %>" required><br><br>

        <input type="submit" value="<%= (ordiToEdit != null) ? "Modifier" : "Soumettre" %>">
        <a href="ordi">Voir liste</a>
    </form>
</body>
</html>
