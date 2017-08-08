package ycweixin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

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

}
