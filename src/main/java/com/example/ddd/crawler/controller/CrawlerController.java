package com.example.ddd.crawler.controller;

import com.example.ddd.crawler.facade.OkHttp;
import com.example.ddd.crawler.facade.Sax;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

@RestController
@RequestMapping("/crawler")
public class CrawlerController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(String url) throws IOException, SAXException {
        OkHttp okHttp = new OkHttp();
        String html = okHttp.getStringByUrl(url);
//        html = "<?xml version=\"1.0\"?>\n" +
//                "\n" +
//                "<poem xmlns=\"http://www.megginson.com/ns/exp/poetry\">\n" +
//                "<title>Roses are Red</title>\n" +
//                "<l>Roses are red,</l>\n" +
//                "<l>Violets are blue;</l>\n" +
//                "<l>Sugar is sweet,</l>\n" +
//                "<l>And I love you.</l>\n" +
//                "</poem>";
//        StringReader srReader=new StringReader(html);
//        InputSource source=new InputSource(srReader);
//        XMLReader xr = XMLReaderFactory.createXMLReader();
//        Sax handler = new Sax();
//        xr.setContentHandler(handler);
//        xr.setErrorHandler(handler);
//        xr.parse(source);
        return "";
    }
}

