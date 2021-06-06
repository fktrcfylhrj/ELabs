package dao;

public interface Dao<T> {
    T read(Integer id) throws DaoException;

    Integer create(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(Integer id) throws DaoException;
}
