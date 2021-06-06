package service.logic;

import dao.CustomerDao;
import dao.DaoException;
import dao.UserDao;
import domain.Customer;
import domain.User;
import service.CustomerService;
import service.ServiceException;

public class CustomerServiceImpl extends BaseService implements CustomerService {
    private CustomerDao customerDao;
    private UserDao userDao;

    @Override
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Customer findById(Integer id) throws ServiceException {
        try {
            return customerDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer findByName(String name) throws ServiceException {
        try {
            return customerDao.findByName(name);
        }catch(DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Customer customer, User user) throws ServiceException {
        try {
            if(customer.getId() == null) {
                if(user.getId() == null){
                    Integer id = userDao.create(user);
                    customer.setId(id);
                } else {
                    customer.setId(user.getId());
                }
                customerDao.create(customer);
            } else {
                customerDao.update(customer);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try{
            customerDao.delete(id);
        } catch(DaoException e){
            throw new ServiceException(e);
        }
    }
}
