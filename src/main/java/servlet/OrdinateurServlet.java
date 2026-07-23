package servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import model.Ordinateur;

public class OrdinateurServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("saveEtat".equals(action)) {
            // Handle saveEtat
            String idOrdiStr = req.getParameter("id_ordinateur");
            String etatLibelle = req.getParameter("etat_libelle");
            String dateEtat = req.getParameter("date_etat");
            String disfonctionnement = req.getParameter("disfonctionnement");
            String typePanneIdStr = req.getParameter("type_panne_id");

            if (idOrdiStr != null && etatLibelle != null && dateEtat != null) {
                try {
                    Ordinateur ordinateur = new Ordinateur();
                    ordinateur.setId_ordinateur(Integer.parseInt(idOrdiStr));
                    Integer typePanneId = (typePanneIdStr != null && !typePanneIdStr.isEmpty()) ? Integer.parseInt(typePanneIdStr) : null;
                    ordinateur.saveEtat(etatLibelle, dateEtat, disfonctionnement != null ? disfonctionnement : "", typePanneId);

                    // Recuperer le dernier etat enregistre pour l'afficher en JSON
                    model.Etat dernierEtat = ordinateur.getLastEtat();
                    if (dernierEtat != null) {
                        Gson gson = new Gson();
                        String jsonResult = gson.toJson(dernierEtat);

                        // Stocker dans la session pour le redirect
                        HttpSession session = req.getSession();
                        session.setAttribute("jsonResult", jsonResult);
                        session.setAttribute("successMessage", "Etat enregistre avec succes !");
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("errorMessage", "L'etat a ete enregistre mais impossible de recuperer les details.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    HttpSession session = req.getSession();
                    session.setAttribute("errorMessage", "Erreur lors de l'enregistrement: " + e.getMessage());
                }
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("errorMessage", "Veuillez remplir tous les champs obligatoires.");
            }

            // Rediriger vers la page de resultat JSON
            resp.sendRedirect("json_result.jsp");
            return;
        }

        // Default: save/update ordinateur
        String idOrdiStr = req.getParameter("id_ordinateur");
        String id_model_str = req.getParameter("model");
        String id_marque_str = req.getParameter("marque");
        String ram = req.getParameter("ram");
        String processeur = req.getParameter("processeur");
        String disque_dur = req.getParameter("disque_dur");

        Ordinateur ordinateur = new Ordinateur();
        ordinateur.setId_modele(Integer.parseInt(id_model_str));
        ordinateur.setRam(Integer.parseInt(ram));
        ordinateur.setProcesseur(processeur);
        ordinateur.setDisque_dur(Integer.parseInt(disque_dur));

        try {
            if (idOrdiStr != null && !idOrdiStr.isEmpty()) {
                ordinateur.setId_ordinateur(Integer.parseInt(idOrdiStr));
                ordinateur.update();
            } else {
                ordinateur.save();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("ordi");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    Ordinateur ordinateur = new Ordinateur();
                    ordinateur.setId_ordinateur(Integer.parseInt(idStr));
                    ordinateur.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            resp.sendRedirect("ordi");
            return;
        }

        // Route for /ordi/liste or /ordi/liste/etat - return JSON
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && (pathInfo.equals("/liste") || pathInfo.equals("/liste/etat"))) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Ordinateur ordinateur = new Ordinateur();
            Vector<Ordinateur> ordinateurs = new Vector<>();
            try {
                ordinateurs = ordinateur.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            String json = gson.toJson(ordinateurs);
            resp.getWriter().write(json);
            return;
        }

        // Default: display list in JSP
        Ordinateur ordinateur = new Ordinateur();
        Vector<Ordinateur> ordinateurs = new Vector<>();
        try {
            ordinateurs = ordinateur.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("ordinateurs", ordinateurs);

        RequestDispatcher dispatcher = req.getRequestDispatcher("liste.jsp");

        dispatcher.forward(req, resp);
    }
}
