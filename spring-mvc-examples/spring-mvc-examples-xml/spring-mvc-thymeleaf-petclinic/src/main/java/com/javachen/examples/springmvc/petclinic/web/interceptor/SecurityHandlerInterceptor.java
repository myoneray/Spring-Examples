package com.javachen.examples.springmvc.petclinic.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Account account = (Account) WebUtils.getSessionAttribute(request, LoginController.ACCOUNT_ATTRIBUTE);
//        if (account == null) {
//            //Retrieve and store the original URL.
//            String url = request.getRequestURL().toString();
//            WebUtils.setSessionAttribute(request, LoginController.REQUESTED_URL, url);
//            throw new AuthenticationException("Authentication required.", "authentication.required");
//        }
        return true;
    }

}
