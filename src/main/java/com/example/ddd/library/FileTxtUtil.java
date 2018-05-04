package com.example.ddd.library;

import java.io.*;

/**
 * FileTxtUtil
 *
 * @author wjp
 * @desc
 * @date Created in 下午3:21 2018/5/4
 */
public class FileTxtUtil {

    /**
     * 读取文件
     * @param file
     * @param charset
     * @return
     */
    public static String readFile(File file, String charset){
        //设置默认编码
        if(charset == null){
            charset = "UTF-8";
        }
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
    }

    /**
     * 读取洗文文件
     * @param file
     * @param charset
     * @return
     */
    public static String readWashFile(File file, String charset){
        //设置默认编码
        if(charset == null){
            charset = "UTF-8";
        }
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charset);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String text = null;
                StringBuffer sb = new StringBuffer();
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text+",");
                }
                return sb.toString();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return null;
    }

    /**
     * 写入txt文件。
     */
    public static void writeToFile1(){

        try {
            String content = "测试使用字符串";
            File file = new File("./File/test1.txt");
            if(file.exists()){
                FileWriter fw = new FileWriter(file,false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close(); fw.close();
                System.out.println("test1 done!");
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 写入txt文件不存在时主动创建。
     */
    public static void writeToFile2(){
        try {
            String content = "测试使用字符串";
            File file = new File("./File/test2.txt");
            //文件不存在时候，主动穿件文件。
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close(); fw.close();
            System.out.println("test2 done!");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
