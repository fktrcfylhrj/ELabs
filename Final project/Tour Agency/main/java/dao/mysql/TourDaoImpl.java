package dao.mysql;

import dao.DaoException;
import dao.TourDao;
import domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TourDaoImpl extends BaseDaoImpl implements TourDao {

    @Override
    public Tour read(Integer id) throws DaoException {
        String sql = "SELECT `date_start`, `date_end`, `burning`, `tour_customer_id`, `tour_agent_id`, `recreation_cost`, `excursion_cost`, `shopping_cost`, `other_expenses` FROM `tour` WHERE `tour_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Tour tour = null;
            if (resultSet.next()) {
                Integer customerId;
                Integer agentId;
                tour = new Tour();

                tour.setId(id);
                tour.setDateStart(resultSet.getDate("date_start"));
                tour.setDateEnd(resultSet.getDate("date_end"));
                tour.setBurning(resultSet.getBoolean("burning"));

                customerId = resultSet.getInt("tour_customer_id");
                agentId = resultSet.getInt("tour_agent_id");

                CustomerDaoImpl customerDao = new CustomerDaoImpl();
                customerDao.setConnection(getConnection());
                TravelAgentDaoImpl travelAgentDao = new TravelAgentDaoImpl();
                travelAgentDao.setConnection(getConnection());

                tour.setCustomer(customerDao.read(customerId));
                tour.setTravelAgent(travelAgentDao.read(agentId));

                tour.setRecreationCost(resultSet.getFloat("recreation_cost"));
                tour.setExcursionCost(resultSet.getFloat("excursion_cost"));
                tour.setShoppingCost(resultSet.getFloat("shopping_cost"));
                tour.setOtherExpenses(resultSet.getFloat("other_expenses"));
            }
            return tour;
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
    public List<Tour> readAll() throws DaoException {
        String sql = "SELECT `tour_id`, `date_start`, `date_end`, `burning`, `tour_customer_id`, `tour_agent_id`, `recreation_cost`, `excursion_cost`, `shopping_cost`, `other_expenses` FROM `tour`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Tour> tours = new ArrayList<>();
            while(resultSet.next()) {
                Tour tour = new Tour();
                tour.setId(resultSet.getInt("tour_id"));
                tour.setDateStart(resultSet.getDate("date_start"));
                tour.setDateEnd(resultSet.getDate("date_end"));
                tour.setBurning(resultSet.getBoolean("burning"));
                tour.setRecreationCost(resultSet.getFloat("recreation_cost"));
                tour.setExcursionCost(resultSet.getFloat("excursion_cost"));
                tour.setShoppingCost(resultSet.getFloat("shopping_cost"));
                tour.setOtherExpenses(resultSet.getFloat("other_expenses"));
                tour.setTravelAgent(new TravelAgent());
                tour.getTravelAgent().setId(resultSet.getInt("tour_agent_id"));
                tour.setCustomer(new Customer());
                tour.getCustomer().setId(resultSet.getInt("tour_customer_id"));
                tour.setId(resultSet.getInt("tour_id"));
                tours.add(tour);
            }
            return tours;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }
    @Override
    public Integer create(Tour tour) throws DaoException {//убрать tour_id
        String sql = "INSERT INTO `tour` (`date_start`, `date_end`, `burning`, `tour_customer_id`, `tour_agent_id`, `recreation_cost`, `excursion_cost`, `shopping_cost`, `other_expenses`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, tour.getDateStart());
            statement.setDate(2, tour.getDateEnd());
            statement.setBoolean(3, tour.isBurning());
            statement.setInt(4, tour.getCustomer().getId());
            statement.setInt(5, tour.getTravelAgent().getId());
            statement.setFloat(6, tour.getRecreationCost());
            statement.setFloat(7, tour.getExcursionCost());
            statement.setFloat(8, tour.getShoppingCost());
            statement.setFloat(9, tour.getOtherExpenses());
            statement.executeUpdate();
            Integer id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(Tour tour) throws DaoException {
        String sql = "UPDATE `tour` SET `date_start` = ?, `date_end` = ?, `burning` = ?, `tour_customer_id` = ?, `tour_agent_id` = ?, `recreation_cost` = ?, `excursion_cost` = ?, `shopping_cost` = ?, `other_expenses` = ? WHERE `tour_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setDate(1, tour.getDateStart());
            statement.setDate(2, tour.getDateEnd());
            statement.setBoolean(3, tour.isBurning());
            statement.setInt(4, tour.getCustomer().getId());
            statement.setInt(5, tour.getTravelAgent().getId());
            statement.setFloat(6, tour.getRecreationCost());
            statement.setFloat(7, tour.getExcursionCost());
            statement.setFloat(8, tour.getShoppingCost());
            statement.setFloat(9, tour.getOtherExpenses());
            statement.setInt(10, tour.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `tour` WHERE `tour_id` = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean ifTourByCustomerIdExists(Integer id) throws DaoException{
        String sql = "SELECT `tour_id` FROM `tour` WHERE `tour_customer_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            boolean exists = false;
            if (resultSet.next()) {
                exists = true;
            }
            return exists;
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
    public boolean ifTourByAgentIdExists(Integer id) throws DaoException {
        String sql = "SELECT `tour_id` FROM `tour` WHERE `tour_agent_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            boolean exists = false;
            if (resultSet.next()) {
                exists = true;
            }
            return exists;
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
}
