package com.goruslan.socialgeeking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ RestController is a combination of @Controller and @ResponseBody
 * Controller - to create a Map of model object and find a view
 * RestController - simply return the object and object data is directly written into HTTP response as JSON or XML.
 * RequestMapping - to map web requests to Spring Controller methods.
 */


@RestController
public class HomeController {

    //@RequestMapping(path = "/", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    @GetMapping("/")
    public String home(){
        return "Hello, Spring Boot 2!";
    }

}
