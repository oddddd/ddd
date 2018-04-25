package com.example.ddd.crawler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){

        return "ok";
    }
}
