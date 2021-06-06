package controller.user;

import controller.BaseServlet;
import controller.ServletErrorSender;
import domain.Customer;
import domain.Role;
import domain.TravelAgent;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import util.FactoryException;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileSaveServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                try(ServiceFactory factory = getFactory()) {
                    UserService service = factory.getUserService();
                    user.setLogin(req.getParameter("login"));
                    service.save(user);

                    Role role = user.getRole();
                    if(role == Role.TRAVELAGENT){
                        TravelAgentService agentService = factory.getTravelAgentService();
                        TravelAgent agent = new TravelAgent();
                        agent.setId(user.getId());
                        agent.setSurnameAndName(req.getParameter("surnameAndName"));
                        agentService.save(agent, user);
                    } else if(role == Role.CUSTOMER){
                        CustomerService customerService = factory.getCustomerService();
                        Customer customer = new Customer();
                        customer.setId(user.getId());
                        customer.setName(req.getParameter("name"));
                        customer.setSurname(req.getParameter("surname"));
                        customer.setPatronymic(req.getParameter("patronymic"));
                        customerService.save(customer, user);
                    }
                } catch(ServiceException e) {
                    ServletErrorSender.sendError("unable to update data",
                            "it is possible that such a login already exists",
                            "main", req, resp);
                    return;
                } catch(FactoryException e){
                    logger.info(e.getMessage());
                    throw new ServletException(e);
                } catch(Exception e){
                    logger.info(e.getMessage());
                }
                resp.sendRedirect(req.getContextPath() + "/profile.html");
            }
        }
    }
}
