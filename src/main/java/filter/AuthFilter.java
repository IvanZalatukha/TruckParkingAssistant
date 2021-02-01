package filter;


import entity.Role;
import entity.User;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/cafe", "/rest"},
        initParams = {@WebInitParam(name = "COMMAND", value = "to-access-blocked")})
public class AuthFilter implements Filter {

    private String blockedAccessPageCommand;

    @Override
    public void init(FilterConfig filterConfig) {
        blockedAccessPageCommand =
                filterConfig.getServletContext().getContextPath() + filterConfig.getInitParameter("COMMAND");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (hasAccess(request, user)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getServletPath() + "?command=" + blockedAccessPageCommand);
        }
    }

    private boolean hasAccess(HttpServletRequest request, User user) {
        boolean result = true;
        String command = request.getParameter("command");
        if (command != null) {
            if (user == null && (command.contains("user") || command.contains("admin"))) {
                result = false;
            }
            if (user != null && !user.getRole().equals(Role.ADMIN) && command.contains("admin")) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public void destroy() {
        blockedAccessPageCommand = null;
    }
}