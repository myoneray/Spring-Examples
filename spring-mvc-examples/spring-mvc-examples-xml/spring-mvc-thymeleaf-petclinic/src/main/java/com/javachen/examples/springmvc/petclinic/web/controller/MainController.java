package com.javachen.examples.springmvc.petclinic.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-05 11:38.
 */
@Controller
public class MainController {
    @RequestMapping(value = { "/", "/index**" }, method = RequestMethod.GET)
    public String defaultPage() {
        return "index";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String logingPage() {
        return "login";
    }

    @RequestMapping(value = { "/sessionExpired" }, method = RequestMethod.GET)
    public String sessionExpired() {
        return "sessionExpired";
    }

    // for 403 access denied page
    @RequestMapping(value = "/302", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("302");
        return model;

    }

}
