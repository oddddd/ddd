package com.example.ddd.crawler.facade;

public interface CrawlerFacade {
    /**
     * 查询作者id 没有就添加
     * @param name
     * @return
     */
    Integer addAuthorByAuthorName(String name);
}
