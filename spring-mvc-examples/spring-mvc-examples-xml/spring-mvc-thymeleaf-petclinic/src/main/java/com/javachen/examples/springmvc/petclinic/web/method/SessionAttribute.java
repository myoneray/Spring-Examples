package com.javachen.examples.springmvc.petclinic.web.method;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionAttribute {

    /**
     * The name of the session attribute to bind to.
     */
    String value() default "";

    /**
     * Whether the parameter is required.
     */
    boolean required() default true;

    /**
     * Wheter attribute needs to be exposed as model attribtue.
     */
    boolean exposeAsModelAttribute() default false;
}
