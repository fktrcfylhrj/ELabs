package service;

import java.sql.Connection;

public interface Transaction {
    void start() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

    public void setConnection(Connection connection);
}
