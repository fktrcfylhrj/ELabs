package service;

import domain.TravelAgent;
import domain.User;

public interface TravelAgentService {
    TravelAgent findById(Integer id) throws ServiceException;
    TravelAgent findByName(String name) throws ServiceException;
    void save(TravelAgent travelAgent, User user) throws ServiceException;
    void delete(Integer id) throws ServiceException;
}
