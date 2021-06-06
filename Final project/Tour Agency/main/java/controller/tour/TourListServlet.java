package controller.tour;

import controller.BaseServlet;
import domain.Tour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.TourService;
import util.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TourListServlet extends BaseServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try(ServiceFactory factory = getFactory()) {
            TourService tourService = factory.getTourService();
            List<Tour> tours = tourService.findAll();

            req.setAttribute("tours", tours);
            req.getRequestDispatcher("/WEB-INF/jsp/tour/tourList.jsp").forward(req, resp);
        } catch(Exception e) {
            logger.info(e.getMessage());
            throw new ServletException(e);
        }
    }
}
