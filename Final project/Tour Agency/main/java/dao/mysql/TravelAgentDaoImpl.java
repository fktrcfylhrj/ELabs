package dao.mysql;

import dao.DaoException;
import dao.TravelAgentDao;
import domain.TravelAgent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravelAgentDaoImpl extends BaseDaoImpl implements TravelAgentDao {

    @Override
    public TravelAgent read(Integer id) throws DaoException {
        String sql = "SELECT `surname_name` FROM `travel_agent` WHERE `agent_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            TravelAgent travelAgent = null;
            if (resultSet.next()) {
                travelAgent = new TravelAgent();
                travelAgent.setId(id);
                travelAgent.setSurnameAndName(resultSet.getString("surname_name"));
            }
            return travelAgent;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {}
            try {
                resultSet.close();
            } catch (Exception e) {}
        }
    }

    @Override
    public TravelAgent findByName(String name) throws DaoException {
        String sql = "SELECT `agent_id` FROM `travel_agent` WHERE `surname_name` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            TravelAgent travelAgent = null;
            if (resultSet.next()) {
                travelAgent = new TravelAgent();
                travelAgent.setId(resultSet.getInt("agent_id"));
                travelAgent.setSurnameAndName(name);
            }
            return travelAgent;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean agentExists(Integer id) throws DaoException{
        String sql = "SELECT `name` FROM `travel_agent` WHERE `agent_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exist = false;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exist = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {}
            try {
                resultSet.close();
            } catch (Exception e) {}
            return exist;
        }
    }

    @Override
    public Integer create(TravelAgent travelAgent) throws DaoException {
        String sql = "INSERT INTO `travel_agent` (`agent_id`, `surname_name`) VALUES (?, ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, travelAgent.getId());
            statement.setString(2, travelAgent.getSurnameAndName());
            statement.executeUpdate();
            return travelAgent.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(TravelAgent travelAgent) throws DaoException {
        String sql = "UPDATE `travel_agent` SET `surname_name` = ? WHERE `agent_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, travelAgent.getSurnameAndName());
            statement.setInt(2, travelAgent.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `travel_agent` WHERE `agent_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }


}
