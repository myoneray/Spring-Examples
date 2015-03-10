package com.javachen.examples.springmvc.petclinic.web.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-04 11:45.
 */
public class SecurityContextSupport {
    public static PetclinicUserDetails getUserDetails() {
        return (PetclinicUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
