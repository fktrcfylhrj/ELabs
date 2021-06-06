package controller.user;

import controller.BaseServlet;
import domain.Customer;
import domain.Role;
import domain.TravelAgent;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.CustomerService;
import service.TravelAgentService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            try(ServiceFactory factory = getFactory()) {
                if (user != null) {
                    if (user.getRole() == Role.CUSTOMER) {
                        req.setAttribute("role", "customer");
                        CustomerService customerService = factory.getCustomerService();
                        Customer customer = customerService.findById(user.getId());
                        req.setAttribute("cus", customer);
                    } else if (user.getRole() == Role.TRAVELAGENT) {
                        req.setAttribute("role", "travelAgent");
                        TravelAgentService agentService = factory.getTravelAgentService();
                        TravelAgent agent = agentService.findById(user.getId());
                        req.setAttribute("agent", agent);
                    } else {
                        req.setAttribute("role", "admin");
                    }
                }
            }catch(Exception e){
                logger.info(e.getMessage());
            }
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/jsp/user/profile.jsp").forward(req, resp);
        }else{
            req.setAttribute("message", "Something wrong with session.\nAre you logged in?");
            req.getRequestDispatcher("/WEB-INF/jsp/errorMessages/loginError.jsp").forward(req, resp);
        }
    }
}
