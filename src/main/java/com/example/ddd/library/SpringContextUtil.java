package com.example.ddd.library;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * SpringContextUtil
 *
 * @author wjp
 * @desc
 * @date Created in 下午2:09 2018/5/7
 */
public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //设置上下文
    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }


    public static Object getBean(String name) throws BeansException {
        try {
            return applicationContext.getBean(name);
        } catch (Exception e) {
            throw new RuntimeException("获取的Bean不存在！");
        }
    }

    //通过类型获取上下文中的bean
    public static Object getBean(Class<?> requiredType){
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType)
            throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<? extends Object> getType(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    /**
     * gb2312转Utf8
     * @param str
     * @return
     */
    public static String gb2312ToUtf8(String str) {
        String urlEncode = "" ;
        try {
            urlEncode = URLEncoder.encode (str, "UTF-8" );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncode;
    }

    /**
     * 查编码格式
     * @param str
     * @return
     */
    public static String getEncoding(String str) {
        String encode = "GB2312";
//        try {
//            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GB2312
//                String s = encode;
//                return s;      //是的话，返回“GB2312“，以下代码同理
//            }
//        } catch (Exception exception) {
//        }
//        encode = "ISO-8859-1";
//        try {
//            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是ISO-8859-1
//                String s1 = encode;
//                return s1;
//            }
//        } catch (Exception exception1) {
//        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {   //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {      //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";        //如果都不是，说明输入的内容不属于常见的编码格式。
    }
}
