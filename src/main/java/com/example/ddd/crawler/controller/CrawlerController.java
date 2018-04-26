package com.example.ddd.crawler.controller;

import com.example.ddd.crawler.facade.OkHttp;
import com.example.ddd.crawler.facade.Sax;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(String url) throws IOException {
        OkHttp okHttp = new OkHttp();
        String html = okHttp.getStringByUrl(url);
        html = "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "<poem xmlns=\"http://www.megginson.com/ns/exp/poetry\">\n" +
                "<title>Roses are Red</title>\n" +
                "<l>Roses are red,</l>\n" +
                "<l>Violets are blue;</l>\n" +
                "<l>Sugar is sweet,</l>\n" +
                "<l>And I love you.</l>\n" +
                "</poem>";
        Sax sax = new Sax();

        return "";
    }
}

