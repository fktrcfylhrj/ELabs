package dao;

import domain.TravelAgent;

import java.sql.Connection;

public interface TravelAgentDao extends Dao<TravelAgent>{
    public void setConnection(Connection connection);

    boolean agentExists(Integer id) throws DaoException;

    TravelAgent findByName(String name) throws DaoException;
}
