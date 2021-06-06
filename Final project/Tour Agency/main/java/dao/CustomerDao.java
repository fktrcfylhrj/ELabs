package dao;

import domain.Customer;

public interface CustomerDao extends Dao<Customer>{
    Customer findByName(String name) throws DaoException;
}
