package com.javachen.examples.springmvc.petclinic.web.security;

import com.javachen.examples.springmvc.petclinic.model.User;
import com.javachen.examples.springmvc.petclinic.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class ExtAuthenticationProcessingFilter extends
        AbstractAuthenticationProcessingFilter {
    private Logger logger = LoggerFactory
            .getLogger(ExtAuthenticationProcessingFilter.class);

    @Autowired
    protected UserService userService;

    @Autowired
    protected MessageDigestPasswordEncoder passwordEncoder;

    protected static final String DEFAULT_FILTER_PROCESSES_URL = "/j_spring_security_check";

    private static final String DEFAULT_USERNAME_PROPERTY = "j_username";

    private static final String DEFAULT_PASSWORD_PROPERTY = "k_password";

    private static final String DEFAULT_VALIDATECODE_PROPERTY = "validateCode";

    private String usernameProperty;

    private String passwordProperty;

    private String validateCodeProperty;

    private boolean checkValidateCode = false;

    public ExtAuthenticationProcessingFilter() {
        super(DEFAULT_FILTER_PROCESSES_URL);
    }

    public ExtAuthenticationProcessingFilter(String processurl) {
        super(processurl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        HttpSession session = request.getSession();
        session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (!request.getMethod().equals("POST")) {
            logger.warn("Authentication method not supported:{}", request.getMethod());
            throw new AuthenticationServiceException("登录不支持: " + request.getMethod()
                    + "方法");
        }

        if (checkValidateCode) {
            // 检测验证码
            checkValidateCode(request);
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        request.getSession().setAttribute(getUsernameProperty(), username);

        List<User> users = userService.findByName(username);
        if (users == null || users.size() == 0) {
            throw new AuthenticationServiceException("用户名不存在");
        }
        User user = users.get(0);
        if (!passwordEncoder.isPasswordValid(user.getPassword(), password, user.getName())){
            throw new AuthenticationServiceException("用户名或密码错误");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, user.getPassword());

        Authentication authentication = this.getAuthenticationManager()
                .authenticate(authRequest);

        SecurityContextSupport.clearAuthenticationAttributes(request);

        logger.info("current login user:{}", user.getName());

        return authentication;
    }

    protected void checkValidateCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionValidateCode = obtainSessionValidateCode(session);
        // 让上一次的验证码失效
        session.setAttribute(getValidateCodeProperty(), null);
        String validateCodeParameter = obtainValidateCodeParameter(request);
        if (!sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            logger.warn("User failed to login. Reason: {}", "验证码错误");
            throw new AuthenticationServiceException("验证码错误");
        }
    }

    private String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(getValidateCodeProperty());
        return null == obj ? "" : obj.toString();
    }

    protected String obtainSessionValidateCode(HttpSession session) {
        Object obj = session.getAttribute(getValidateCodeProperty());
        return null == obj ? "" : obj.toString();
    }

    public String getValidateCodeProperty() {

        if (validateCodeProperty == null) {
            validateCodeProperty = DEFAULT_VALIDATECODE_PROPERTY;
        }
        return validateCodeProperty;
    }

    public void setValidateCodeProperty(String validateCodeProperty) {

        this.validateCodeProperty = validateCodeProperty;
    }

    public String getUsernameProperty() {
        if (usernameProperty == null) {
            usernameProperty = DEFAULT_USERNAME_PROPERTY;
        }
        return usernameProperty;
    }

    public void setUsernameProperty(String usernameProperty) {
        this.usernameProperty = usernameProperty;
    }

    public String getPasswordProperty() {
        if (passwordProperty == null) {
            passwordProperty = DEFAULT_PASSWORD_PROPERTY;
        }
        return passwordProperty;
    }

    public void setPasswordProperty(String passwordProperty) {
        this.passwordProperty = passwordProperty;
    }

    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(getUsernameProperty());
        return null == obj ? "" : obj.toString().trim();
    }

    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(getPasswordProperty());
        return null == obj ? "" : obj.toString().trim();
    }

}
