package dao;

import domain.Tour;

import java.sql.Connection;
import java.util.List;

public interface TourDao extends  Dao<Tour>{
    List<Tour> readAll() throws DaoException;
    public boolean ifTourByCustomerIdExists(Integer id) throws DaoException;
    public boolean ifTourByAgentIdExists(Integer id) throws DaoException;
    public void setConnection(Connection connection);
}
