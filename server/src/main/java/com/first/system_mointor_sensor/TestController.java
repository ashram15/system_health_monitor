// Make sure this package name matches your other file
package com.first.system_mointor_sensor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Tells Spring this is an API controller
public class TestController {

    @GetMapping("/hello") // This maps the URL /hello to this method
    public String sayHello() {
        return "Hello, Spring Boot! Your server is running.";
    }
}