package com.example.ddd.crawler.controller;
import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.crawler.facade.WashArticleFacade;
import com.example.ddd.library.OkHttp;
import com.example.ddd.crawler.model.AllConsultModel;
import com.example.ddd.ddd.Controller;
import com.example.ddd.library.RedisDao;
import com.example.ddd.mybatis.mapper.ConsultMapper;
import com.example.ddd.mybatis.model.ConsultModel;
import com.example.ddd.mybatis.service.ConsultService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crawler")
public class CrawlerController extends Controller{
    @Autowired
    ConsultService consultService;
    @Autowired
    CrawlerFacade crawlerFacade;
    @Autowired
    ConsultMapper consultMapper;
    @Autowired
    WashArticleFacade washArticleFacade;
    @Autowired
    RedisDao redisDao;
    /**
     * 159彩票网站咨询爬取
     * @param url
     * @return
     * @throws IOException
     * @throws SAXException
     */
    @RequestMapping(value = "/159Cai",method = RequestMethod.GET)
    public String crawler159Cai(String url) throws IOException, SAXException {
        try{
            OkHttp okHttp = new OkHttp();
            String html = okHttp.getStringByUrl(url);
            String[] htmlList = html.split("<div class=\"news_left news_list\">");
            html = htmlList[1];
            htmlList = html.split("<div class=\"pagination\">");
            html = htmlList[0];
            Document doc = Jsoup.parse(html);
            Elements trList = doc.getElementsByTag("tr");
            List<AllConsultModel> modelList = new ArrayList<>();
            for (Element tr :trList){
                AllConsultModel model = new AllConsultModel();
                try{
                    model.setUrl("http://www.159cai.com"+tr.child(1).child(0).attr("href"));
                    ConsultModel hasUrl = consultService.selectConsultByUrl(model.getUrl());
                    if(hasUrl != null)
                        continue;
                    model.setConsultName(tr.child(1).child(0).html());
                    model.setCreateTime(tr.child(2).ownText());

                }catch (Exception e){
                    continue;
                }
                modelList.add(model);
            }
            for(AllConsultModel consult : modelList){
                try{
                    html = okHttp.getStringByUrl(consult.getUrl());
                    htmlList = html.split("<div class=\"news_left\" id=\"left\">");
                    if(htmlList.length == 1)
                        htmlList = html.split("<div class=\"news_left\">");
                    html = htmlList[1];
                    htmlList = html.split("<p  class=\"pbtp\">");
                    html = htmlList[0];
                    doc = Jsoup.parse(html);
                    Elements pList = doc.getElementsByTag("p");
                    String consultDetail = "";
                    ConsultModel insert = new ConsultModel();
                    for(Element p :pList){
                        if(p.getElementsByTag("img").size() > 0){
                            p.getElementsByTag("img").eq(0).attr("src");
                            consultDetail += "<p><img src=\"" + "http://www.159cai.com" + p.getElementsByTag("img").eq(0).attr("src") + "\"></p>";
                            if(insert.getConsultImg() == null)
                                insert.setConsultImg("http://www.159cai.com" + p.getElementsByTag("img").eq(0).attr("src"));
                        }
                        else
                            consultDetail += "<p>" + p.html() + "</p>";
                    }
                    try{
                        consult.setAuthorName(doc.getElementsByTag("a").eq(0).html());
                        Integer authorId = crawlerFacade.addAuthorByAuthorName(consult.getAuthorName());
                        if(authorId != null)
                            insert.setAuthorId(authorId);
                    }catch (Throwable e){
                        System.out.println(e);
                    }
                    insert.setConsultDetail(washArticleFacade.WashArticleText(consultDetail));
                    insert.setHasExamine(0);
                    insert.setUrl(consult.getUrl());
                    insert.setConsultName(consult.getConsultName());
                    insert.setCreateTime(consult.getCreateTime());
                    insert.setUrlName("159彩票");
                    consultMapper.insertSelective(insert);
                }catch (Exception e){
                    continue;
                }
            }
            return "ok";
        }catch (Exception e){
            System.out.println(e);
            return "error";
        }
    }

    /**
     * 简单洗文
     * @param text
     * @return
     * @throws IOException
     * @throws SAXException
     */
    @RequestMapping(value = "/washArticle",method = RequestMethod.GET)
    public String washArticle(String text) throws IOException, SAXException {
        return washArticleFacade.WashArticleText(text);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object test(String text) throws IOException, SAXException {
        try{
            ConsultModel setModel = consultService.selectConsultById(762);
            redisDao.setObjectKeyValueTime("model",setModel,60);
            Object getModel = redisDao.getObjectKeyValue("model");
            return  getModel;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}

