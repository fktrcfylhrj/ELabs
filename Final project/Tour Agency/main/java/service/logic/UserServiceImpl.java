package service.logic;

import dao.*;
import domain.User;
import service.*;

import java.util.List;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private String defaultPassword;
    private TourDao tourDao;

    @Override
    public void setTourDao(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if(user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin()) ||
                            userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                user.setPassword(defaultPassword);
                if(userDao.readByLogin(user.getLogin()) == null) {
                    Integer id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public void changePassword(Integer userId, String oldPassword,
                               String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.read(userId);
            if(user != null) {
                if(user.getPassword().equals(oldPassword)) {
                    if(newPassword == null) {
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public boolean canDelete(Integer id) throws ServiceException {
        try{
            return !(tourDao.ifTourByCustomerIdExists(id) ||
                    tourDao.ifTourByAgentIdExists(id));
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
