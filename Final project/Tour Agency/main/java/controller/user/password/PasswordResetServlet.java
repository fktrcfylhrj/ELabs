package controller.user.password;

import controller.BaseServlet;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PasswordResetServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try(ServiceFactory factory = getFactory()) {

            HttpSession session = req.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("currentUser");
                if(user != null) {
                    UserService userService = factory.getUserService();

                    String oldPass = req.getParameter("oldPassword");
                    String newPass = req.getParameter("newPassword");
                    String newPassCopy = req.getParameter("anotherNewPassword");

                    if(oldPass.equals(user.getPassword()) && newPass != null &&
                            newPass.equals(newPassCopy)){
                        userService.changePassword(user.getId(), oldPass, newPass);
                    }else{
                        req.setAttribute("message", "impossible to reset the password: maybe you made a mistake");
                        req.getRequestDispatcher("/WEB-INF/jsp/errorMessages/passwordResetError.jsp").forward(req, resp);
                    }

                }
            }


            resp.sendRedirect(req.getContextPath() + "/profile.html");
        } catch(NumberFormatException e) {
            resp.sendError(400);
        } catch(Exception e) {
            logger.info(e.getMessage());
            throw new ServletException(e);
        }
    }
}
