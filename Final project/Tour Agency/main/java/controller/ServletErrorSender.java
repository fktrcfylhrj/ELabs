package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletErrorSender {
    private static final String TOPIC = "topic";
    private static final String MESSAGE = "message";
    private static final String WHERE = "where";
    private static final String ERROR_PATH = "/WEB-INF/jsp/errorMessages/error.jsp";

    public static void sendError(String topic, String message,
                                 String whereRedirect, HttpServletRequest req,
                                 HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute(TOPIC, topic);
        req.setAttribute(MESSAGE, message);
        req.setAttribute(WHERE, whereRedirect);
        req.getRequestDispatcher(ERROR_PATH).forward(req, resp);
    }
}
