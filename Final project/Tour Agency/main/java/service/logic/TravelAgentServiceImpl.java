package service.logic;

import dao.DaoException;
import dao.TravelAgentDao;
import dao.UserDao;
import domain.TravelAgent;
import domain.User;
import service.ServiceException;
import service.TravelAgentService;

public class TravelAgentServiceImpl extends BaseService implements TravelAgentService {
    private TravelAgentDao travelAgentDao;
    private UserDao userDao;

    @Override
    public TravelAgent findById(Integer id) throws ServiceException {
        try {
            return travelAgentDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public TravelAgent findByName(String name) throws ServiceException {
        try {
            return travelAgentDao.findByName(name);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(TravelAgent travelAgent, User user) throws ServiceException {
        try {
            if(travelAgent.getId() == null) {
                if(user.getId() == null){//если нет такого юзера то создаём?
                    Integer id = userDao.create(user);
                    travelAgent.setId(id);
                } else{
                    travelAgent.setId(user.getId());
                }
                travelAgentDao.create(travelAgent);
            } else {
                travelAgentDao.update(travelAgent);
            }
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        try{
            travelAgentDao.delete(id);
        } catch(DaoException e){
            throw new ServiceException(e);
        }
    }

    public void setTravelAgentDao(TravelAgentDao travelAgentDao) {
        this.travelAgentDao = travelAgentDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
