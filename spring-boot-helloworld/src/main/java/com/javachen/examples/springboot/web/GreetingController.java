package com.javachen.examples.springboot.web;

import java.util.concurrent.atomic.AtomicLong;

import com.javachen.examples.springboot.domain.Greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/index")
    public @ResponseBody String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/greeting")
    public @ResponseBody Greeting greeting(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
        System.out.println("==== in greeting ====");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
