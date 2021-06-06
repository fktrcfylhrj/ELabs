package dao.mysql;

import dao.CustomerDao;
import dao.DaoException;
import domain.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CustomerDaoImpl extends BaseDaoImpl implements CustomerDao {

    @Override
    public Customer read(Integer id) throws DaoException {
        String sql = "SELECT `name`, `surname`, `patronymic`, `is_regular_customer` FROM `customer` WHERE `customer_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Customer customer = null;
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(id);

                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setPatronymic(resultSet.getString("patronymic"));
                customer.setRegularCustomer(resultSet.getBoolean("is_regular_customer"));
            }
            return customer;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {}
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Customer findByName(String name) throws DaoException {
        String sql = "SELECT `customer_id`, `surname`, `patronymic`, `is_regular_customer` FROM `customer` WHERE `name` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Customer customer = null;
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("customer_id"));
                customer.setName(name);
                customer.setSurname(resultSet.getString("surname"));
                customer.setPatronymic(resultSet.getString("patronymic"));
                customer.setRegularCustomer(resultSet.getBoolean("is_regular_customer"));
            }
            return customer;
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
    public Integer create(Customer customer) throws DaoException {
        String sql = "INSERT INTO `customer` (`customer_id`, `is_regular_customer`, `surname`, `name`, `patronymic`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, customer.getId());
            statement.setBoolean(2, customer.isRegularCustomer());
            statement.setString(3, customer.getSurname());
            statement.setString(4, customer.getName());
            if (customer.getPatronymic() != null) {
                statement.setString(5, customer.getPatronymic());
            } else {
                statement.setNull(5, Types.INTEGER);
            }
            statement.executeUpdate();
            return customer.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {}
        }
    }

    @Override
    public void update(Customer customer) throws DaoException {
        String sql = "UPDATE `customer` SET `is_regular_customer` = ?, `surname` = ?, `name` = ?, `patronymic` = ? WHERE `customer_id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setBoolean(1, customer.isRegularCustomer());
            statement.setString(2, customer.getSurname());
            statement.setString(3, customer.getName());
            if(customer.getPatronymic() != null){
                statement.setString(4, customer.getPatronymic());
            } else {
                statement.setNull(4, Types.INTEGER);
            }
            statement.setInt(5, customer.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `customer` WHERE `customer_id` = ?";
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
