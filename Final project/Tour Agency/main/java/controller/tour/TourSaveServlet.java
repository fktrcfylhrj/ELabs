package controller.tour;

import controller.BaseServlet;
import controller.ServletErrorSender;
import dao.CustomerDao;
import dao.DaoException;
import dao.TravelAgentDao;
import domain.Customer;

import domain.Tour;
import domain.TravelAgent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class TourSaveServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Tour tour = new Tour();
        CustomerDao customerDao;
        TravelAgentDao travelAgentDao;
        try {
            tour.setId(Integer.parseInt(req.getParameter("id")));
        } catch(NumberFormatException e) {}
        try(ServiceFactory factory = getFactory()) {
            customerDao = factory.getCustomerDao();
            travelAgentDao = factory.getTravelAgentDao();
            String cusName = req.getParameter("cusName");
            String agentName = req.getParameter("agentName");
            try{
                if(cusName != null && agentName != null) {
                    Customer customer = customerDao.findByName(cusName);
                    tour.setCustomer(customer);
                    TravelAgent travelAgent = travelAgentDao.findByName(agentName);
                    tour.setTravelAgent(travelAgent);

                    tour.setShoppingCost(Float.parseFloat(req.getParameter("shopCost")));
                    tour.setOtherExpenses(Float.parseFloat(req.getParameter("otherCost")));
                    tour.setExcursionCost(Float.parseFloat(req.getParameter("excCost")));
                    tour.setRecreationCost(Float.parseFloat(req.getParameter("recCost")));
                }else{
                    throw new ServletException();
                }
            }catch(DaoException e){
                logger.info(e.getMessage());
                throw new ServletException(e);
            }catch(ServletException | NumberFormatException e){
                ServletErrorSender.sendError("Wrong data",
                        "You entered incorrect data, please try again", "tour",
                        req, resp);
                return;
            }
        } catch(Exception e) {
            logger.info("Exception at TourSaveServlet");
        }
        try {
            tour.setDateStart(Date.valueOf(req.getParameter("dateStart")));
            tour.setDateEnd(Date.valueOf(req.getParameter("dateEnd")));
        }catch(IllegalArgumentException e){
            ServletErrorSender.sendError("date is incorrect",
                    "you should input date in format yyyy-mm-dd", "tour", req,
                    resp);
            return;
        }

        tour.setBurning(req.getParameter("burning") != null);

        if(tour.getDateStart() != null && tour.getDateEnd() != null &&
                tour.getCustomer() != null && tour.getTravelAgent() != null) {
            try(ServiceFactory factory = getFactory()) {
                TourService service = factory.getTourService();

                service.save(tour);
            } catch(ServiceException e) {
                ServletErrorSender.sendError("unable to update or add data",
                        "it is possible that you entered wrong data (for example, check that the entered dates are correct)\nIf your data is correct, we have some server problems, please wait",
                        "tour", req, resp);
                return;
            }catch(Exception e){
                logger.error("Exception at TourSaveServlet");
                ServletErrorSender.sendError("server problems",
                        "we have some server problems, please wait", "tour",
                        req, resp);
                return;
            }
        }else{
            ServletErrorSender.sendError("Wrong data",
                    "Input fields must not be empty ", "tour", req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/tourList.html");
    }
}
