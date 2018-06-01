package com.example.ddd.crawler.controller;

import com.example.ddd.crawler.facade.CrawlerFacade;
import com.example.ddd.ddd.Controller;
import com.example.ddd.res.ResModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * OpenController
 *
 * @author wjp
 * @desc
 * @date Created in 下午4:11 2018/6/1
 */
@RestController
@RequestMapping("/open")
public class OpenController extends Controller{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CrawlerFacade crawlerFacade;
    /**
     * 获取盘口页面地址
     * @return
     */
    @RequestMapping(value = "/crawler500Detail",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String crawler500Detail(Integer id){
        try{
            Object data = crawlerFacade.getMatchOddsById(id);
            return Controller.response(ResModel.CODE_SUCCESS,ResModel.CONTEXT_SUCCESS,data);
        }catch (Exception e){
            logger.error("crawler500Detail -log : big error : " + e);
            return Controller.response(ResModel.CODE_ERROR,ResModel.CONTEXT_ERROR,null);
        }
    }

}
