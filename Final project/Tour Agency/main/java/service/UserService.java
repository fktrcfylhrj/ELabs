package service;

import dao.TourDao;
import domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id) throws ServiceException;

    User findByLoginAndPassword(String login,
                                String password) throws ServiceException;

    List<User> findAll() throws ServiceException;

    void save(User user) throws ServiceException;

    void changePassword(Integer userId,
                        String oldPassword,
                        String newPassword) throws ServiceException;

    boolean canDelete(Integer id) throws ServiceException;

    void delete(Integer id) throws ServiceException;

    public void setTourDao(TourDao tourDao);
}
