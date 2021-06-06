package service;

import domain.Tour;

import java.util.List;

public interface TourService {
    Tour findById(Integer id) throws ServiceException;

    List<Tour> findAll() throws ServiceException;

    public void save(Tour tour) throws ServiceException;

    public void delete(Integer id) throws ServiceException;
}
