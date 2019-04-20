package com.olrox.filter;

import com.olrox.account.AuthorizationBean;
import com.olrox.account.domain.Role;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns = AdminFilter.FILTER + "*", filterName = "AdminFilter")
public class AdminFilter implements Filter {
    public final static String FILTER = "/admin/";

    @Inject
    private AuthorizationBean authorizationBean;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(authorizationBean.getRole() != null){

            String uri = request.getRequestURI();
            String beginOfAdminUri = request.getContextPath() + FILTER;

            if(uri.startsWith(beginOfAdminUri) && authorizationBean.getRole() != Role.ADMIN){
                response.sendRedirect(request.getContextPath() + "/errors.xhtml");
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        authorizationBean.setRequestedPage(request.getRequestURI());
        response.sendRedirect(request.getContextPath() + "/signIn.xhtml");
    }

    @Override
    public void destroy() {

    }
}