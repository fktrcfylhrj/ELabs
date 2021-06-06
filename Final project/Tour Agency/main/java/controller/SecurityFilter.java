package controller;

import domain.Role;
import domain.User;

import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class SecurityFilter extends BaseServlet implements Filter {
    private static final Map<String, Set<Role>> permissions = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>();
        all.addAll(Arrays.asList(Role.values()));

        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMINISTRATOR);

        Set<Role> employees = new HashSet<>();
        employees.add(Role.ADMINISTRATOR);
        employees.add(Role.TRAVELAGENT);

        permissions.put("/index", all);
        permissions.put("/profile", all);
        permissions.put("/reset", all);
        permissions.put("/profileSave", all);
        permissions.put("/list", employees);
        permissions.put("/edit", employees);
        permissions.put("/save", employees);
        permissions.put("/delete", employees);
        permissions.put("/tourList", all);
        permissions.put("/editTour", employees);
        permissions.put("/saveTour", employees);
        permissions.put("/tourDelete", employees);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        if(!url.equals("/index")) {
            Set<Role> roles = permissions.get(url);
            if (roles != null) {
                HttpSession session = httpReq.getSession(false);
                if (session != null) {
                    User user = (User) session.getAttribute("currentUser");

                    if (user != null) {
                        if (roles.contains(user.getRole())) {
                            chain.doFilter(req, resp);
                            return;
                        } else {
                            req.setAttribute("message", "access denied");
                        }
                    } else {
                        req.setAttribute("message", "user does not exist");
                    }
                }
            } else {
                chain.doFilter(req, resp);
                return;
            }
            req.getRequestDispatcher("/WEB-INF/jsp/errorMessages/loginError.jsp").forward(req, resp);
        }else{
            (new MainPageServlet()).doGet(httpReq, httpResp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void destroy() {}
}
