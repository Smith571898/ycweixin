package ycweixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpUtils;

import org.apache.catalina.ha.backend.Sender;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.weixin.bean.Menu;
import com.yc.weixin.bean.TwoMenu;
import com.yc.weixin.biz.MenuBiz;
import com.yc.weixin.model.ButtonModel;
import com.yc.weixin.model.MenuModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.CommonUtil;
import com.yc.weixin.utils.HttpUtil;
import com.yc.weixin.utils.MenuUtil;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath*:beans.xml")
public class Testone {
	@Autowired
	 ApplicationContext ac;
	
	@Test
	public void test() throws  Exception {
		URL url = new URL("http://wx.qlogo.cn/mmopen/pNznictSiag1AwrDd4wN5QsIKiaOiaibw0en6s6xcGS2Hib2VdlNdGXOibjWMwHqXMiaBH0ROgDNHYoe7fsdic0fyyYzXiaT5kw9nelPlr/0");
		URLConnection urlconn = url.openConnection();

		InputStream is = urlconn.getInputStream();

		byte[] buf = new byte[10*1024];
		int length = 0;

		File file=new File("d:\\k.jpg");
		if(!file.exists()){
			file.createNewFile();
		}
		FileOutputStream out=new FileOutputStream(file);
		
		while ((length = is.read(buf, 0, buf.length)) != -1) {
			out.write(buf, 0, length);
		}
		
		out.flush();
		out.close();
	}

	@Test
	public void test2(){
		System.out.println(String.format("Content-Disposition:form-data;name=\"media\";filename=\"file1%s\"\r\n", ".jpg"));
		String str = "ACCESS_TOKEN\r\nTYPE";
		str = str.replace("ACCESS_TOKEN", "l7gZG4j1ZdItEf9mbpjQGoRnvAtZ1PZ2Xc8OJMd5Qsn-8644O5pmFnX7q5UwhD2dRf-rahiGcY9LCCE1ANQlSGT-wSvDDQdPS-eo5157d6Tl90TY13A8abcVCrgj7wOVQMUcAFACYG");
		str = str.replace("TYPE", "image");
		System.out.println(str);
		System.out.println("------------------------------------");
		String uploadMediaUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
		uploadMediaUrl = uploadMediaUrl.replace("TYPE","image");
		uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN","l7gZG4j1ZdItEf9mbpjQGoRnvAtZ1PZ2Xc8OJMd5Qsn-8644O5pmFnX7q5UwhD2dRf-rahiGcY9LCCE1ANQlSGT-wSvDDQdPS-eo5157d6Tl90TY13A8abcVCrgj7wOVQMUcAFACYG");
		System.out.println(uploadMediaUrl);

	}
	
	@Test
	public void test3() throws IOException{
		MenuModel mm = new MenuModel();
		ButtonModel firstbutton1 = new ButtonModel();
		firstbutton1.setName("源辰信息");
		ButtonModel firstbutton2 = new ButtonModel();
		firstbutton2.setName("学员中心");
		firstbutton2.setKey("V1002_TODAY_MUSIC");
		firstbutton2.setType("click");
		
		
		ButtonModel secondbutton = new ButtonModel();
		secondbutton.setName("源辰Info信息");
		secondbutton.setType("click");
		secondbutton.setKey("V1001_TODAY_MUSIC");
		List<ButtonModel> list1 = new ArrayList<ButtonModel>();//一级菜单 
		List<ButtonModel> list2 = new ArrayList<ButtonModel>();//单个一级菜单里的子菜单
		list1.add(firstbutton1);
		list1.add(firstbutton2);
		list2.add(secondbutton);
		mm.setButton(list1);
		firstbutton1.setSub_button(list2);
		
		System.out.println(CommonUtil.gson.toJson(mm));
		System.out.println(AccessTokenUtil.access_token);
//		MenuUtil.createMenu(mm, AccessTokenUtil.access_token);
		
	}
	@Test
	public void test4(){
		MenuModel mm = new MenuModel();
	MenuBiz menuBiz=	(MenuBiz) ac.getBean("menuBizImpl");
		List<Menu> Onelist=new ArrayList<Menu>();
		Map<String,String> map=new HashMap<String,String>();
		Onelist=menuBiz.findAllOneMenu(map);
		List<ButtonModel> list1 = new ArrayList<ButtonModel>();//一级菜单 
		List<ButtonModel> list2 = new ArrayList<ButtonModel>();//单个一级菜单里的子菜单
		for(Menu me:Onelist){
			ButtonModel firstbutton = new ButtonModel();//一级菜单model
			
			firstbutton.setName(me.getName());
			if(me.getMenutype().equals("click")){//遍历一个一级菜单出来就把他存ButtonModel
				firstbutton.setType(me.getMenutype());
				firstbutton.setKey(me.getMenukey());
			}else if(me.getMenutype().equals("view")){
				firstbutton.setType(me.getMenutype());
				firstbutton.setUrl(me.getUrl());
			}
			
			Map<String,Integer> OneBidmap=new HashMap<String,Integer>();
			OneBidmap.put("bid",me.getBid());
			List<TwoMenu> TwoList=menuBiz.findTwoMenuByOneName(OneBidmap);
			for(TwoMenu tm:TwoList){
				ButtonModel secondbutton = new ButtonModel();
				secondbutton.setName(tm.getName());
				if(tm.getMenutype().equals("click")){
					secondbutton.setType(tm.getMenutype());
					secondbutton.setKey(tm.getMenukey());
				}else if(tm.getMenutype().equals("view")){
					secondbutton.setType(tm.getMenutype());
					secondbutton.setUrl(tm.getUrl());
				}
				
				
				list2.add(secondbutton);
			}
			firstbutton.setSub_button(list2);//把一个一级子菜单里面的5个二级菜单存进去
			list1.add(firstbutton);//  5   10   15
			
		}
			mm.setButton(list1);
		System.out.println(mm);
	}
	//股票查询
	@Test
	public void test5(){
		  String host = "https://ali-stock.showapi.com";
		    String path = "/everytrade";
		    String method = "GET";
		    String appcode = "b90973c99d2643d081b28c6fe6646368";//设置成静态变量
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("code", "399001");//600887为股票代码参数  //匹配到"上证指数"  用000001替换   匹配到"深证成指" 用399001替换


		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
		    	System.out.println(response.toString());
		    	//获取response的body
		    	System.out.println(EntityUtils.toString(response.getEntity()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		
		
	}
	/**快递查询
	 * 	@Test
	 */
	public void test6(){
		   String host = "http://jisutqybmf.market.alicloudapi.com";
		    String path = "/weather/query";
		    String method = "GET";
		    String appcode = "b90973c99d2643d081b28c6fe6646368";//设置成静态变量
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    Map<String, String> querys = new HashMap<String, String>();
		    querys.put("city", "衡阳");//衡阳为参数
		    querys.put("citycode", "citycode");
		    querys.put("cityid", "cityid");
		    querys.put("ip", "ip");
		    querys.put("location", "location");


		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
		    	System.out.println(response.toString());
		    	//获取response的body
		    	System.out.println(EntityUtils.toString(response.getEntity()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	/**
	 * 快递查询
	 */
	@Test
	public void test7(){
		String host = "https://ali-deliver.showapi.com";
	    String path = "/showapi_expInfo";
	    String method = "GET";
	    String appcode = "b90973c99d2643d081b28c6fe6646368";//静态变量
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("com", "auto");//不知道快递名就用  auto代替
	    querys.put("nu", "DD1589615654");//快递单号为参数


	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		
		
	}
	
}
