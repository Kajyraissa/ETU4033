package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ordinateur;

public class ShowFormOrdiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Ordinateur ordiToEdit = null;

        if (idStr != null && !idStr.isEmpty()) {
            try {
                Integer id = Integer.parseInt(idStr);
                Ordinateur dao = new Ordinateur();
                ordiToEdit = dao.getById(id); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("ordiToEdit", ordiToEdit);
        RequestDispatcher dispatcher = req.getRequestDispatcher("form.jsp");
        dispatcher.forward(req, resp);
    }
}