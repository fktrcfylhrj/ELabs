package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import connection_pool.ConnectionPool;
import connection_pool.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationStartListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent event) {
            ServletContext context = event.getServletContext();
            String jdbcDriver = context.getInitParameter("jdbc-driver");
            String jdbcUrl = context.getInitParameter("jdbc-url");
            String jdbcUsername = context.getInitParameter("jdbc-username");
            String jdbcPassword = context.getInitParameter("jdbc-password");
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            try {
                connectionPool.init(jdbcDriver, jdbcUrl, jdbcUsername,
                        jdbcPassword,10, 100);
                logger.info("Connector was initialized,\njdbc-driver = {},\njdbc-url = {},\njdbc-username = {}\njdbc-password = {}",
                        jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
            } catch (PoolException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
        logger.info("Connector was destroyed\n");
    }
}
