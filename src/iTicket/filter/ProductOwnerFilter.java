package iTicket.filter;

import iTicket.util.StaticValues;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductOwnerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String userType = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute(StaticValues.USER_TYPE_SESSION_ATTRIBUTE);

        if (userType == null || !userType.equals("PRODUCT_OWNER")) {
            String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
            ((HttpServletResponse) servletResponse).sendRedirect(contextPath + "/index.xhtml");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}