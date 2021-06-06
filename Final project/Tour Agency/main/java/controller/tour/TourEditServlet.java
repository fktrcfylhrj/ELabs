package controller.tour;

import controller.BaseServlet;
import domain.*;
import domain.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TourService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TourEditServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch(NumberFormatException e) {}

        if(id != null) {
            try(ServiceFactory factory = getFactory()) {
                TourService tourService = factory.getTourService();
                Tour tour = tourService.findById(id);
                req.setAttribute("tour", tour);
            } catch(Exception e) {
                logger.info(e.getMessage());
                throw new ServletException(e);
            }
        }
        Boolean burningValues[] = {false, true};
        req.setAttribute("burnVal", burningValues);
        req.getRequestDispatcher("/WEB-INF/jsp/tour/tourEdit.jsp").forward(req, resp);
    }
}
