package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Utilisateur;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pwd = req.getParameter("pwd");

        int result = Utilisateur.verifier(login, pwd);

        if (result == 1) {
            // TRUE - create session variable
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("role", "admin");
            resp.sendRedirect("ordi");
        } else {
            // FALSE - return to login.jsp with error
            req.setAttribute("error", "Login ou mot de passe incorrect");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
