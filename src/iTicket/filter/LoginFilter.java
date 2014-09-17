package iTicket.filter;

import iTicket.entities.UserEntity;
import iTicket.util.StaticValues;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        UserEntity user = (UserEntity) ((HttpServletRequest) servletRequest).getSession().getAttribute(StaticValues.USER_SESSION_ATTRIBUTE);

        if (user == null) {
            String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
            ((HttpServletResponse) servletResponse).sendRedirect(contextPath + "/signin.xhtml");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}