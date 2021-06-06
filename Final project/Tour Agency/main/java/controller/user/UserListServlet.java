package controller.user;

import controller.BaseServlet;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class UserListServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException {
        try(ServiceFactory factory = getFactory()) {
            UserService service = factory.getUserService();
            List<User> users = service.findAll();

            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
        } catch(Exception e) {
            logger.info(e.getMessage());
            throw new ServletException(e);
        }
    }
}