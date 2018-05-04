package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.WashArticleFacade;
import com.example.ddd.library.FileTxtUtil;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * WashArticleImpl
 *
 * @author wjp
 * @desc
 * @date Created in 下午2:53 2018/5/4
 */
@Service
public class WashArticleImpl implements WashArticleFacade{
    @Override
    public String WashArticleText(String text) {
        String synonym = FileTxtUtil.readWashFile(new File("/Users/dddd/wash01.txt"),"UTF-8");
        String [] synonymList = synonym.split(",");
        for(int i = 0;i < synonymList.length; i+=2 ){
            try{
                System.out.println(i);
                text = text.replaceAll( synonymList[i], synonymList[i+1] );
            }catch (Exception e){
                System.out.println(e);
                continue;
            }
        }
        return text;
    }
}
