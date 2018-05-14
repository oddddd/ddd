package com.example.ddd.task;

import com.example.ddd.crawler.controller.CrawlerController;
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
    @Autowired
    CrawlerController crawlerController;
    @Scheduled(cron = "0 0/10 * * * ?") // 每10分钟执行一次
    public void crawlerOne() {
        System.out.println("crawlerOne定时任务启动");
        String log1 = crawlerController.crawler159Cai("http://www.159cai.com/zc/yuce/");
        System.out.println(log1);
        String log2 = crawlerController.crawler159Cai("http://www.159cai.com/jczq/yuce/");
        System.out.println(log2);
        String log3 = crawlerController.crawler159Cai("http://www.159cai.com/jclq/yuce/");
        System.out.println(log3);
        String log4 = crawlerController.crawlerZhiBo8("https://news.zhibo8.cc/zuqiu/more.htm");
        System.out.println(log4);
        String log5 = crawlerController.crawlerZhiBo8("https://news.zhibo8.cc/nba/more.htm");
        System.out.println(log5);
        System.out.println("crawlerOne定时任务结束");
    }
}
