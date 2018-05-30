package com.example.ddd.crawler.controller;
import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.crawler.facade.WashArticleFacade;
import com.example.ddd.crawler.model.AllLiveModel;
import com.example.ddd.crawler.model.AllMatchModel;
import com.example.ddd.crawler.model.LiveModel;
import com.example.ddd.library.OkHttp;
import com.example.ddd.crawler.model.AllConsultModel;
import com.example.ddd.ddd.Controller;
import com.example.ddd.library.RedisDao;
import com.example.ddd.mybatis.mapper.ConsultMapper;
import com.example.ddd.mybatis.model.ConsultModel;
import com.example.ddd.mybatis.model.MatchModel;
import com.example.ddd.mybatis.service.ConsultService;
import com.example.ddd.mybatis.service.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    MatchService matchService;
    @Autowired
    Environment env;

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
                            if(!imgUrl.contains("https")){
                                imgUrl = "https:"+imgUrl;
                            }
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
        }catch (Exception e){
            System.out.println(e);
            return "error";
        }
    }

    /**
     * 无插件直播网数据爬取
     * @param url
     * @return
     */
    @RequestMapping(value = "/9to5",method = RequestMethod.GET)
    public String crawler9to5(String url){
        try{
            OkHttp okHttp = new OkHttp();
            String html = okHttp.getStringByUrl(url);
            String[] htmlList = html.split("<tbody>");
            html = htmlList[1];
            htmlList = html.split("</tbody>");
            html = htmlList[0];
            html = "<table>"+html+"</table>";
            Document doc = Jsoup.parse(html);
            Elements trList = doc.getElementsByClass("against");
            List<AllMatchModel> modelList = new ArrayList<>();
            for(Element tr :trList){
                try{
                    AllMatchModel newMatchModel = new AllMatchModel();
                    tr.getElementsByTag("td").eq(4).attr("title");
                    newMatchModel.setLeagueName(tr.getElementsByTag("td").eq(0).attr("title"));
                    if(tr.getElementsByTag("td").eq(4).hasClass("teama")){
                        try{
                            newMatchModel.setHomeName(tr.getElementsByTag("td").eq(4).first().getElementsByTag("strong").html());
                            newMatchModel.setAwayName(tr.getElementsByTag("td").eq(6).first().getElementsByTag("strong").html());
                        }catch (Exception e){
                            newMatchModel.setHomeName(tr.getElementsByTag("td").eq(4).html());
                            newMatchModel.setAwayName(tr.getElementsByTag("td").eq(6).html());
                        }
                        newMatchModel.setKickoffTime(tr.getElementsByTag("td").eq(9).attr("t"));
                    }else{
                        newMatchModel.setMatchName(tr.getElementsByTag("td").eq(4).html());
                        newMatchModel.setKickoffTime(tr.getElementsByTag("td").eq(7).attr("t"));

                    }
                    List<AllLiveModel> liveList = new ArrayList<>();
                    for( Element a : tr.getElementsByClass("live_link").first().getElementsByTag("a")){
                        try{
                            String otherUrl = a.attr("href");
                            if(otherUrl.contains("../")){
                                otherUrl = a.attr("href").replace("../","http://www.9to5.me/");
                            }else if(otherUrl.subSequence(0,1).equals("/")){
                                otherUrl = "http://www.9to5.me"+a.attr("href");
                            }else if(otherUrl.subSequence(0,1).equals("h")){
                                otherUrl = a.attr("href");
                            }else{
                                continue;
                            }
                            AllLiveModel live = new AllLiveModel();
//                            String liveHtml = okHttp.getStringByUrl(otherUrl);
//                            Document liveDoc = Jsoup.parse(liveHtml);
//                            try{
//                                String liveUrl = liveDoc.getElementsByTag("iframe").first().attr("src");
//                                live.setLiveUrl(liveUrl);
//                            }catch (Exception e){
//                                continue;
//                            }
                            live.setLiveUrl(otherUrl);
                            live.setType(2);
                            live.setUrl(otherUrl);
                            live.setLiveName(a.html());
                            liveList.add(live);
                            newMatchModel.setLiveList(liveList);
                        }catch (Exception e){
                            continue;
                        }
                    }
                    modelList.add(newMatchModel);
                }catch (Exception e){
                    continue;
                }
            }
            crawlerFacade.matchBindLiveStreamList(modelList);
            return "ok";
        }catch (Exception e){
            return "error";
        }
    }

    @RequestMapping(value = "/TTZhiBo",method = RequestMethod.GET)
    public String crawlerTTZhiBo(String url){
        try{
            OkHttp okHttp = new OkHttp();
            String html = okHttp.getStringByUrl(url);
            Document doc = Jsoup.parse(html);
            Elements dateList = doc.getElementsByClass("datelist");
            List<AllMatchModel> modelList = new ArrayList<>();
            for(Element date : dateList){
                try{
                    String day = date.getElementsByClass("dateheader").html();
                    String[] dayList = day.split(" ");
                    day = dayList[0];
                    day = day.replace("年","-");
                    day = day.replace("月","-");
                    day = day.replace("日","");
                    Elements ulList = date.getElementsByTag("ul");
                    for (Element ul : ulList){
                        try{
                            AllMatchModel match = new AllMatchModel();
                            String matchDate = ul.getElementsByTag("li").eq(0).html();
                            String matchDetail = ul.getElementsByTag("li").get(3).getElementsByTag("a").html();
                            match.setKickoffTime(day+" "+matchDate+":00");
                            match.setMatchName(matchDetail);
                            String[] matchNameList = matchDetail.split("VS");
                            if(matchNameList.length>1){
                                match.setHomeName(matchNameList[0].trim());
                                match.setAwayName(matchNameList[1].trim());
                            }
                            Elements aList = ul.getElementsByTag("li").get(4).getElementsByTag("a");
                            List<AllLiveModel> liveList = new ArrayList<>();
                            for(Element a : aList){
                                try{
                                    AllLiveModel live = new AllLiveModel();
                                    String liveUrl = a.attr("href");
                                    if(liveUrl.substring(0,2).equals("//")){
                                        liveUrl = liveUrl.replace("//","https://");
                                    }
                                    if(!liveUrl.contains("http")){
                                        liveUrl = "http://www.tiantianzhibo.com/"+liveUrl;
                                    }
                                    live.setLiveUrl(liveUrl);
                                    live.setUrl(liveUrl);
                                    live.setLiveName(a.html());
                                    live.setType(2);
                                    liveList.add(live);
                                }catch (Exception e){
                                    continue;
                                }
                            }
                            match.setLiveList(liveList);
                            modelList.add(match);
                        }catch (Exception e){
                            continue;
                        }
                    }
                }catch (Exception e){
                    continue;
                }
            }
            crawlerFacade.matchBindLiveStreamList(modelList);
            return "ok";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * 获取直播比赛列表
     * @return
     */
    @RequestMapping(value = "/crawlerOkoooSoccerList",method = RequestMethod.GET)
    public String crawlerOkoooSoccerList(String url){
        try{
            OkHttp okHttp = new OkHttp();
            byte[] b = okHttp.getStringByUrlTwo(url);
            String html = new String(b, "GB2312");
            Document doc = Jsoup.parse(html);
            Elements tableborderList = doc.getElementsByClass("tableborder");
            List<String> roomList = new ArrayList<>();
            for(Element tableborder : tableborderList){
                try{
                    Elements trList = tableborder.getElementsByTag("tr");
                    for (Element tr : trList){
                        try{
                            String status = tr.getElementsByTag("td").get(3).child(0).html();
                            status = status.trim();
//                            if(status.equals("未")||status.equals("完")||status.equals("腰斩")||status.equals("延期")){
//                                continue;
//                            }
                            String matchId = tr.attr("matchid");
                            if(matchId!=null){
                                List<MatchModel> has = matchService.selectMatchByMatchId(matchId);
                                if(has.size()>0){
                                    roomList.add(matchId);
                                }
                            }
                        }catch (Exception e){
                            continue;
                        }
                    }
                }catch (Exception e){
                    continue;
                }
            }
            env.getProperty("redis.matchLiveListKey");
            String matchLiveListKey = env.getProperty("redis.matchLiveListKey");
            Integer matchLiveListTime = 7200;
            redisDao.setObjectKeyValueTime(matchLiveListKey,roomList,matchLiveListTime);
            return "ok";
        }catch (Exception e){
            return "error";
        }
    }

    /**
     * 获取直播比赛内容
     * @return
     */
    @RequestMapping(value = "/crawlerOkoooSoccerDetail",method = RequestMethod.GET)
    public String crawlerOkoooSoccerDetail(){
        try{
            String matchLiveListKey = env.getProperty("redis.matchLiveListKey");
            List<String> roomList = redisDao.getObjectKeyValue(matchLiveListKey);
            for(String room : roomList){
                try{
                    String matchRoomSocketDetailKey = env.getProperty("redis.matchRoomSocketDetail");
                    Integer matchRoomSocketDetailTime = 7200;
                    String url = "http://www.okooo.com/soccer/match/" + room;
                    OkHttp okHttp = new OkHttp();
                    byte[] b = okHttp.getStringByUrlThree(url);
                    String html = new String(b, "GB2312");
                    Document doc = Jsoup.parse(html);
                    Elements divList = doc.getElementsByClass("livelistbox").get(0).getElementsByTag("div");
                    List<LiveModel> liveModelsList = new ArrayList<>();
                    List<MatchModel> has = matchService.selectMatchByMatchId(room);
                    if(has.size()>0) {
                        for (Element div : divList) {
                            String homePoint = div.getElementsByClass("livelistconbifen").get(0).getElementsByTag("b").get(0).html();
                            String awayPoint = div.getElementsByClass("livelistconbifen").get(0).getElementsByTag("b").get(2).html();
                            String time = div.getElementsByClass("livelistcontime").get(0).html();
                            String content = div.getElementsByClass("livelistcontext").get(0).html();
                            String type = div.firstElementSibling().attr("class");
                            String[] typeList = type.split("_");
                            try{
                                type = typeList[2];
                            }catch (Exception e){
                                type = "0";
                            }
                            homePoint = homePoint.trim().equals("") ? "0" : homePoint.trim();
                            awayPoint = awayPoint.trim().equals("") ? "0" : awayPoint.trim();
                            LiveModel liveModel = new LiveModel();
                            liveModel.setHomePoint(homePoint);
                            liveModel.setAwayPoint(awayPoint);
                            liveModel.setTime(time);
                            liveModel.setContent(content);
                            liveModel.setType(type);
                            liveModel.setMatchId(room);
                            MatchModel oldMatch = has.get(0);
                            Integer intAwayPoint = Integer.parseInt(awayPoint);
                            Integer intHomePoint = Integer.parseInt(homePoint);
                            if (!oldMatch.getAwayPoint().equals(intAwayPoint) || !oldMatch.getHomePoint().equals(intHomePoint)) {
                                matchService.updateMatchPointByMatchId(room, intHomePoint, intAwayPoint);
                            }
                            liveModelsList.add(liveModel);
                        }
                    }
                    Collections.reverse(liveModelsList);
                    matchRoomSocketDetailKey = matchRoomSocketDetailKey+room;
                    redisDao.setObjectKeyValueTime(matchRoomSocketDetailKey,liveModelsList,matchRoomSocketDetailTime);
                }catch (Exception e){
                    continue;
                }
            }
            return "ok";
        }catch (Exception e){
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

