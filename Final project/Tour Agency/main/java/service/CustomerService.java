package service;

import dao.CustomerDao;
import dao.UserDao;
import domain.Customer;
import domain.User;

public interface CustomerService {
    Customer findById(Integer id) throws ServiceException;
    Customer findByName(String name) throws ServiceException;
    void save(Customer customer, User user) throws ServiceException;
    void delete(Integer id) throws ServiceException;
    public void setCustomerDao(CustomerDao customerDao);
    public void setUserDao(UserDao userDao);
    public void setTransaction(Transaction transaction);
}
