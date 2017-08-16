package ycweixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yc.weixin.model.ButtonModel;
import com.yc.weixin.model.MenuModel;
import com.yc.weixin.utils.AccessTokenUtil;
import com.yc.weixin.utils.CommonUtil;
import com.yc.weixin.utils.MenuUtil;

public class Testone {

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
		List<ButtonModel> list1 = new ArrayList<ButtonModel>();
		List<ButtonModel> list2 = new ArrayList<ButtonModel>();
		list1.add(firstbutton1);
		list1.add(firstbutton2);
		list2.add(secondbutton);
		mm.setButton(list1);
		firstbutton1.setSub_button(list2);
		
		System.out.println(CommonUtil.gson.toJson(mm));
		System.out.println(AccessTokenUtil.access_token);
//		MenuUtil.createMenu(mm, AccessTokenUtil.access_token);
		
	}
	
	public void test4(){
	}
}
