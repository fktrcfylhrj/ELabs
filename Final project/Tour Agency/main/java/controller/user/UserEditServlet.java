package controller.user;

import controller.BaseServlet;
import domain.Role;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserEditServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try(ServiceFactory factory = getFactory()) {
                UserService userService = factory.getUserService();
                User user = userService.findById(id);
                boolean userCanBeDeleted = userService.canDelete(id);

                req.setAttribute("user", user);
                req.setAttribute("userCanBeDeleted", userCanBeDeleted);
            } catch(Exception e) {
                logger.info(e.getMessage());
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
    }
}
