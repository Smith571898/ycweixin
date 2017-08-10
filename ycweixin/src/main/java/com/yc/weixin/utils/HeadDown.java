package com.yc.weixin.utils;

import java.io.*;
import java.net.URL;
import java.util.*;


public class HeadDown {


   public  void HeadPicDown(String headUrl){
        try {
         HeadDown cm=new HeadDown();
     
         List<String> imgSrc=new ArrayList<String>();
         imgSrc.add(headUrl);
            //下载图片
            cm.Download(imgSrc);

        }catch (Exception e){
            System.out.println("发生错误!");
        }
   }
    

  

  

    //下载图片
    private void Download(List<String> listImgSrc) {
        try {
            for (String url : listImgSrc) {
                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                File file = new File("src/images");
                if(!file.exists()){
                	file.mkdirs();
                }else{
	                FileOutputStream fo = new FileOutputStream(new File("src/images/"+imageName));
	                byte[] buf = new byte[1024];
	                int length = 0;
	                System.out.println("开始下载:" + url);
	                while ((length = in.read(buf, 0, buf.length)) != -1) {
	                    fo.write(buf, 0, length);
	                }
	                in.close();
	                fo.close();
	                System.out.println(imageName + "下载完成");
                }
            }
            System.out.println("下载完毕！");
        } catch (Exception e) {
            System.out.println("下载失败!");
        }
    }
}