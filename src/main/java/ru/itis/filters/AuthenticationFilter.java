package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if ((session == null || session.getAttribute("id") == null) && !request.getServletPath().equals("/signup")
                && !request.getServletPath().equals("/main") && !request.getServletPath().equals("/login")) {
            response.sendRedirect(request.getContextPath() + "/signup");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() { }
}
