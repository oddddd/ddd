package com.example.ddd.task;

import com.example.ddd.crawler.controller.CrawlerController;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.xml.sax.SAXException;

import java.io.IOException;

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
    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void crawlerOne() throws IOException, SAXException {
        System.out.println("crawlerOne定时任务启动");
        CrawlerController crawlerController = new CrawlerController();
        crawlerController.crawler159Cai("http://www.159cai.com/zc/yuce/");
        crawlerController.crawler159Cai("http://www.159cai.com/jczq/yuce/");
        crawlerController.crawler159Cai("http://www.159cai.com/jclq/yuce/");
        System.out.println("crawlerOne定时任务结束");
    }
}
