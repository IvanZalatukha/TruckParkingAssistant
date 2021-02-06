package by.zalatukha.tpa.filter;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/jsp/newAdminPage.jsp")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (hasAccess(request)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/jsp/error.jsp");
        }
    }

    private boolean hasAccess(HttpServletRequest request) {
        boolean result = true;
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("isAdmin") == null) {
            return false;
        }
        return result;
    }

}