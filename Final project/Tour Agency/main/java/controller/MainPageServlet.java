package controller;

import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPageServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            try(ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();
                User user = service.findByLoginAndPassword(login, password);
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);
                resp.sendRedirect(req.getContextPath() + "/main.html");

            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new ServletException(e);
            }
        } else {
            try {
                req.setAttribute("message", "fields must be filled");
                req.getRequestDispatcher("/WEB-INF/jsp/errorMessages/loginError.jsp").forward(req, resp);
            }catch(Exception e){
                logger.info(e.getMessage());
            }
        }

    }
}
