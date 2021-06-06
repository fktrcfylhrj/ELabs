package util;

import java.sql.Connection;

import dao.CustomerDao;
import dao.TourDao;
import dao.TravelAgentDao;
import dao.UserDao;
import service.*;

public interface ServiceFactory extends AutoCloseable {
    Transaction getTransaction() throws FactoryException;

    CustomerService getCustomerService() throws FactoryException;

    UserService getUserService() throws FactoryException;

    TourService getTourService() throws FactoryException;

    TravelAgentService getTravelAgentService() throws FactoryException;

    TourDao getTourDao() throws FactoryException;

    UserDao getUserDao() throws FactoryException;

    CustomerDao getCustomerDao() throws FactoryException;

    TravelAgentDao getTravelAgentDao() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
