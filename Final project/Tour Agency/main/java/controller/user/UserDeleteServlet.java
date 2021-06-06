package controller.user;
import controller.BaseServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}
        if(id != null) {
            try(ServiceFactory factory = getFactory()) {
                UserService service = factory.getUserService();

                service.delete(id);
            } catch(Exception e) {
                logger.info(e.getMessage());
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/list.html");
    }
}
