package com.example.ddd.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dd")
public class MaDa {

    @RequestMapping(value = "/dd",method = RequestMethod.GET)
    public String test(String dd) {
        return "hello >>" + dd;
    }
}
