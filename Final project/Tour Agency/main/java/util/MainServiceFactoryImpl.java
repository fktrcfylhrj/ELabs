package util;

import connection_pool.ConnectionPool;
import connection_pool.PoolException;
import dao.CustomerDao;
import dao.TourDao;
import dao.TravelAgentDao;
import dao.UserDao;
import dao.mysql.CustomerDaoImpl;
import dao.mysql.TourDaoImpl;
import dao.mysql.TravelAgentDaoImpl;
import dao.mysql.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.*;
import service.logic.*;

import java.sql.Connection;

public class MainServiceFactoryImpl implements ServiceFactory {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    @Override
    public Connection getConnection() throws FactoryException {
        if(connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (PoolException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();

        userService.setTransaction(getTransaction());
        userService.setDefaultPassword("12345");
        userService.setUserDao(getUserDao());
        userService.setTourDao(getTourDao());

        return userService;
    }

    @Override
    public TourService getTourService() throws FactoryException {
        TourServiceImpl tourService = new TourServiceImpl();

        tourService.setTransaction(getTransaction());
        tourService.setTourDao(getTourDao());
        tourService.setTravelAgentDao(getTravelAgentDao());
        tourService.setCustomerDao(getCustomerDao());
        return tourService;
    }

    @Override
    public CustomerService getCustomerService() throws FactoryException{
        CustomerServiceImpl customerService = new CustomerServiceImpl();

        customerService.setTransaction(getTransaction());
        customerService.setCustomerDao(getCustomerDao());

        return customerService;
    }

    @Override
    public TravelAgentService getTravelAgentService() throws FactoryException {
        TravelAgentServiceImpl travServ = new TravelAgentServiceImpl();

        travServ.setTransaction(getTransaction());
        travServ.setTravelAgentDao(new TravelAgentDaoImpl());

        return travServ;
    }

    @Override
    public Transaction getTransaction() throws FactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    public TourDao getTourDao() throws FactoryException {
        TourDaoImpl tourDao = new TourDaoImpl();
        tourDao.setConnection(getConnection());
        return tourDao;
    }

    @Override
    public TravelAgentDao getTravelAgentDao() throws FactoryException {
        TravelAgentDaoImpl agentDao = new TravelAgentDaoImpl();
        agentDao.setConnection(getConnection());
        return agentDao;
    }

    @Override
    public CustomerDao getCustomerDao() throws FactoryException {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        customerDao.setConnection(getConnection());
        return customerDao;
    }

    @Override
    public void close() {
        try {
            connection.close();
            connection = null;
        } catch(Exception e) {
            logger.info("database access error occured when connection was closing at MainServiceFactoryImpl");
        }
    }
}
