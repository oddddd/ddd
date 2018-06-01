package com.example.ddd.task;

import com.example.ddd.crawler.controller.CrawlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * CrawlerTask
 *
 * @author wjp
 * @desc
 * @date Created in 下午7:40 2018/5/4
 */
@Configuration
@EnableScheduling
public class CrawlerTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CrawlerController crawlerController;
    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void crawlerOne() {
        logger.info("crawlerOne - start");
        String log1 = crawlerController.crawler159Cai("http://www.159cai.com/zc/yuce/");
        logger.info("crawlerOne - log1 : "+log1);
        String log2 = crawlerController.crawler159Cai("http://www.159cai.com/jczq/yuce/");
        logger.info("crawlerOne - log2 : "+log2);
        String log3 = crawlerController.crawler159Cai("http://www.159cai.com/jclq/yuce/");
        logger.info("crawlerOne - log3 : "+log3);
        String log4 = crawlerController.crawlerZhiBo8("https://news.zhibo8.cc/zuqiu/more.htm");
        logger.info("crawlerOne - log4 : "+log4);
        String log5 = crawlerController.crawlerZhiBo8("https://news.zhibo8.cc/nba/more.htm");
        logger.info("crawlerOne - log5 : "+log5);
        String log6 = crawlerController.crawler9to5("http://www.9to5.me/");
        logger.info("crawlerOne - log6 : "+log6);
        String log7 = crawlerController.crawlerTTZhiBo("http://www.tiantianzhibo.com/");
        logger.info("crawlerOne - log7 : "+log7);
        logger.info("crawlerOne - end");
    }

    @Scheduled(cron = "0 0 0/1 * * ?") // 每1小时执行一次
    public void crawlerTwo() {
        logger.info("crawlerTwo - start");
        String log1 = crawlerController.crawler500("http://live.500.com/2h1.php");
        logger.info("crawlerTwo - log1 : "+log1);
        logger.info("crawlerTwo - end");
    }
}
