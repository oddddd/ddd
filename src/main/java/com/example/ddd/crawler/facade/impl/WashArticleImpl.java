package com.example.ddd.crawler.facade.impl;

import com.example.ddd.crawler.facade.WashArticleFacade;
import com.example.ddd.library.FileTxtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private static String [] synonymList;
    @Autowired
    private Environment env;
    @Override
    public String WashArticleText(String text) {
        if(synonymList == null){
            String synonym = FileTxtUtil.readWashFile(new File(env.getProperty("wash.article.filelink1")),"UTF-8");
            synonymList = synonym.split(",");
        }
        for(int i = 0;i < synonymList.length; i+=2 ){
            try{
                text = text.replaceAll( synonymList[i], synonymList[i+1] );
            }catch (Exception e){
                System.out.println(e);
                continue;
            }
        }
        return text;
    }
}
