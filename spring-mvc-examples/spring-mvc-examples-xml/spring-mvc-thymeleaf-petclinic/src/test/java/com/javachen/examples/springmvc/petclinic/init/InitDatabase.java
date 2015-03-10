package com.javachen.examples.springmvc.petclinic.init;

import com.javachen.examples.springmvc.petclinic.service.ClinicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-04 10:13.
 */
@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("spring-data-jpa")
public class InitDatabase {
    @Autowired
    protected ClinicService clinicService;

    @Test
    public void init(){
        Md5PasswordEncoder passwordEncoder=new Md5PasswordEncoder();
        System.out.println(passwordEncoder.encodePassword("admin", "admin"));
    }
}
