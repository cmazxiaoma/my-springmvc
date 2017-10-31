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

import com.augmentum.exam.model.User;

public class SessionFilter implements Filter {
    private String[] notneedFilterPages;

    @Override
    public void destroy() { }

    private boolean isIgoredUrl(String requestUrl){
        for(String notneedFilterPage : notneedFilterPages){
            System.out.println(notneedFilterPage);
            if(requestUrl.contains(notneedFilterPage)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        String url = request.getRequestURI().toString().substring(request.getContextPath().length() + 1);
        System.out.println("url=" + url);
        User user = (User)request.getSession().getAttribute("USER");
        if(!isIgoredUrl(url)){
            if (user == null) {
                String queryparams = request.getQueryString();
                String lastpage = "";

                if (queryparams == null || queryparams.equals("")) {
                    lastpage = url.trim();
                } else {
                    lastpage = url.trim() + "?" + queryparams;
                }

                if (lastpage.equals("page/user/logout")) {
                    response.sendRedirect(request.getContextPath() + "/page/user/login");
                } else if (lastpage.equals("page/dashboard/index")){
                    response.sendRedirect(request.getContextPath() + "/page/user/login?lastpage=dashboard/index");
                } else {
                    response.sendRedirect(request.getContextPath() + "/page/user/login?lastpage=" + lastpage.substring(5));
                }
                System.out.println("session is null");
                return ;
            }
        }

        arg2.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        String str = arg0.getInitParameter("notneedFilterPages");
        notneedFilterPages = str.split(",");
    }
}
