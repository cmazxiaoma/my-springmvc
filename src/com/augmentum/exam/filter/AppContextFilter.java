package com.augmentum.exam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.augmentum.exam.common.AppContext;
import com.augmentum.exam.constant.JsonCodeConstant;
import com.augmentum.exam.constant.ServletConstant;

public class AppContextFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        AppContext appContext = AppContext.getContext();
        appContext.addObject(ServletConstant.APP_CONTEXT_REQUEST, request);
        appContext.addObject(ServletConstant.APP_CONTEXT_RESPONSE, response);
        appContext.setContextPath(request.getContextPath());
        appContext.addObject(ServletConstant.APP_CONTEXT_SESSION, request.getSession());
        HttpSession session = request.getSession();
        if (session.getAttribute(JsonCodeConstant.USER) != null) {
            appContext.addObject(ServletConstant.APP_CONTEXT_USER, session.getAttribute(JsonCodeConstant.USER));
        }
        try {
            chain.doFilter(request, response);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        } catch (ServletException servletException) {
            servletException.printStackTrace();
            throw servletException;
        } catch (RuntimeException runtimeException) {
            runtimeException.printStackTrace();
            throw runtimeException;
        } finally {
            appContext.clear();
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}
}
