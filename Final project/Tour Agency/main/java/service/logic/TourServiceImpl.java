package service.logic;

import dao.*;
import domain.Customer;
import domain.Tour;
import domain.TravelAgent;
import service.DateWrongOrderException;
import service.ServiceException;
import service.TourService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TourServiceImpl extends BaseService implements TourService {
    private TourDao tourDao;
    private CustomerDao customerDao;
    private TravelAgentDao travelAgentDao;

    @Override
    public Tour findById(Integer id) throws ServiceException {
        try{
            return tourDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Tour> findAll() throws ServiceException {
        try {
            List<Tour> tours = tourDao.readAll();
            Map<Integer, TravelAgent> agents = new HashMap<>();
            Map<Integer, Customer> customers = new HashMap<>();

            for(Tour tour : tours) {
                Integer agentId = tour.getTravelAgent().getId();
                Integer customerId = tour.getCustomer().getId();
                TravelAgent agent = agents.get(agentId);
                Customer customer = customers.get(customerId);

                if(agent == null) {
                    agent = travelAgentDao.read(agentId);
                    agents.put(agentId, agent);
                }
                if(customer == null){
                    customer = customerDao.read(customerId);

                    customers.put(customerId, customer);
                }
                tour.setCustomer(customer);
                tour.setTravelAgent(agent);
            }
            return tours;
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Tour tour) throws ServiceException {
        try {
            if(tour.getDateStart().before(tour.getDateEnd())) {
                if (tour.getId() == null) {
                    tourDao.create(tour);
                } else {
                    tourDao.update(tour);
                }
            }else{
                throw new DateWrongOrderException(tour.getDateStart(), tour.getDateEnd());
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            tourDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void setTourDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public void setTravelAgentDao(TravelAgentDao travelAgentDao) {
        this.travelAgentDao = travelAgentDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}

