package controller.user;

import controller.BaseServlet;
import controller.ServletErrorSender;
import domain.Role;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ServiceException;
import service.UserService;
import util.FactoryException;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSaveServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        user.setLogin(req.getParameter("login"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {}
        if(user.getLogin() != null && user.getRole() != null) {
            try(ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();

                service.save(user);
            } catch(ServiceException e) {
                ServletErrorSender.sendError("unable to update data",
                        "Such login already exists", "users", req, resp);
                return;
            } catch(FactoryException e){
                logger.error("FactoryException at UserSaveServlet");
            } catch(Exception e){
                logger.error("Exception at UserSaveServlet");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/list.html");
    }
}

