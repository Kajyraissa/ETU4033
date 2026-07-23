package servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import dao.EtatDao;
import model.Etat;

public class EtatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo != null && pathInfo.equals("/liste")) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            EtatDao etatDao = new EtatDao();
            Vector<Etat> etats = new Vector<>();
            try {
                etats = etatDao.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            String json = gson.toJson(etats);
            resp.getWriter().write(json);
            return;
        }

        // Default: show form
        req.getRequestDispatcher("Formulaire Etat.jsp").forward(req, resp);
    }
}
