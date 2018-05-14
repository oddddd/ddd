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
     */
    @RequestMapping(value = "/159Cai",method = RequestMethod.GET)
    public String crawler159Cai(String url) {
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
     * 直播吧数据爬取
     * @param url
     * @return
     */
    @RequestMapping(value = "/ZhiBo8",method = RequestMethod.GET)
    public String crawlerZhiBo8(String url) {
        try{
            //https://news.zhibo8.cc/zuqiu/more.htm
            //https://news.zhibo8.cc/nba/more.htm
            OkHttp okHttp = new OkHttp();
            String html = okHttp.getStringByUrl(url);
            String[] htmlList = html.split("<div class=\"dataList\">");
            html = htmlList[1];
            htmlList = html.split("<ul class=\"articleList\">");
            html = htmlList[1];
            html = html.replace("</ul>","");
            Document doc = Jsoup.parse(html);
            Elements liList = doc.getElementsByTag("li");
            List<AllConsultModel> modelList = new ArrayList<>();
            for (Element li :liList){
                AllConsultModel model = new AllConsultModel();
                try{
                    model.setUrl("https:"+li.getElementsByTag("a").eq(0).attr("href"));
                    ConsultModel hasUrl = consultService.selectConsultByUrl(model.getUrl());
                    if(hasUrl != null)
                        continue;
                    model.setConsultName(li.getElementsByTag("a").eq(0).html());
                    model.setCreateTime(li.child(2).html());
                    String className = li.attr("data-label");
                    String[] chassNameList = className.split(",");
                    className = chassNameList[2];
                    model.setClassName(className);
                    Integer classId = crawlerFacade.addClassByClassName(className);
                    if(classId != null)
                        model.setClassId(classId);
                    model.setAuthorName(li.child(1).html());
                    Integer authorId = crawlerFacade.addAuthorByAuthorName(model.getAuthorName());
                    if(authorId != null)
                        model.setAuthorId(authorId);
                }catch (Exception e){
                    continue;
                }
                modelList.add(model);
            }
            for (AllConsultModel consult : modelList){
                try {
                    html = okHttp.getStringByUrl(consult.getUrl());
                    doc = Jsoup.parse(html);
                    Elements dom = doc.getElementsByClass("content");
                    if(dom.size() == 0)
                        continue;
                    Elements pList = dom.get(0).getElementsByTag("p");
                    String consultDetail = "";
                    ConsultModel insert = new ConsultModel();
                    for( Element p : pList ){
                        if(p.getElementsByTag("img").size()>0){
                            String imgUrl = p.getElementsByTag("img").get(0).attr("src");
                            imgUrl = "https:"+imgUrl;
                            consultDetail += "<p><img src='"+imgUrl+"'></p>";
                            if(insert.getConsultImg() == null)
                                insert.setConsultImg(imgUrl);
                            continue;
                        }
                        String text = p.html();
                        consultDetail += "<p>"+text+"</p>";
                    }
                    insert.setClassId(consult.getClassId());
                    insert.setHasExamine(0);
                    insert.setConsultName(consult.getConsultName());
                    insert.setUrl(consult.getUrl());
                    insert.setAuthorId(consult.getAuthorId());
                    insert.setUrlName("直播吧");
                    insert.setConsultDetail(consultDetail);
                    consultMapper.insertSelective(insert);
                }catch (Exception e){
                    System.out.println(e);
                    continue;
                }
            }
            return "ok";
//            return null;
        }catch (Exception e){
            System.out.println(e);
            return "error";
//            return null;
        }
    }

    /**
     * 简单洗文
     * @param text
     * @return
     * @throws IOException
     * @throws SAXException
     */
    @RequestMapping(value = "/washArticle",method = RequestMethod.POST)
    public String washArticle(String text) throws IOException, SAXException {
        return washArticleFacade.WashArticleText(text);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object test(String text){
        try{
            System.out.println("crawlerOne定时任务启动");
            CrawlerController crawlerController = new CrawlerController();
            String log1 = crawlerController.crawler159Cai("http://www.159cai.com/zc/yuce/");
            System.out.println(log1);
//            String log2 = crawlerController.crawler159Cai("http://www.159cai.com/jczq/yuce/");
//            System.out.println(log2);
//            String log3 = crawlerController.crawler159Cai("http://www.159cai.com/jclq/yuce/");
//            System.out.println(log3);
            System.out.println("crawlerOne定时任务结束");
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}

