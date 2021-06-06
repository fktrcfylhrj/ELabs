package controller;

import util.MainServiceFactoryImpl;
import util.ServiceFactory;

import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    public ServiceFactory getFactory() {
        return new MainServiceFactoryImpl();
    }
}
