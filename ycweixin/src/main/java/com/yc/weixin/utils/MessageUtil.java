package com.yc.weixin.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.yc.weixin.resp.message.TextMessage;

public class MessageUtil {
	// 请求消息类型:文本
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	// 请求消息类型:图片
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	// 请求消息类型:语言
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	// 请求消息类型:视频
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	// 请求消息类型:地理位置
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	// 请求消息类型:链接
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	// 请求消息类型:事件
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	// 事件类型:subscribe（订阅）
	public static final String REQ_EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型:unsubscribe（取消订阅）
	public static final String REQ_EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型:scan（关注用户扫描带参数二维码）
	public static final String REQ_EVENT_TYPE_SCAN = "scan";
	// 事件类型:location（上报地理位置）
	public static final String REQ_EVENT_TYPE_LOCATION = "location";
	// 事件类型:click（自定义菜单）
	public static final String REQ_EVENT_TYPE_CLICK = "click";

	// 响应消息类型:文本
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	// 响应消息类型:图片
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息类型:语音
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息类型:视频
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息类型:音乐
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息类型:图文
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	//解析发送过来的xml对象,并将其转存到一个map中
	public static Map<String, String> parseXml(HttpServletRequest req) throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();

		InputStream in = req.getInputStream();

		SAXReader reader = new SAXReader();

		Document document = reader.read(in);

		Element root = document.getRootElement();

		List<Element> elementList = root.elements();

		StringBuffer sb = new StringBuffer("");

		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
			sb.append(e.getName() + ":" + e.getText() + "\n");
		}

		saveToXml(req, sb.toString());

		in.close();

		in = null;

		return map;
	}

	//将对象转成xml的插件对象
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有Xml节点的转换都增加CDATA标记
				boolean cdata = true;

				public void startNode(String name, Class clazz) {
					//当回送的消息是createTime时不加CDATA标记
					if (name.equals("CreateTime")) {
						cdata = false;
					} else {
						cdata = true;
					}
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};

		}
	});

	//将对象转换成xml
	public static <T> String messageToXml(T t) {
		xstream.alias("xml", t.getClass());
		return xstream.toXML(t);
	}

	//把解析的xml对象存到webapps/logs/xml.txt中
	public static void saveToXml(HttpServletRequest req, String str) throws IOException {
		File root = new File(req.getSession().getServletContext().getRealPath("/"));
		File rootfile = root.getParentFile();
		String filepath = rootfile + "/logs/";
		File filedir = new File(filepath);
		if (!filedir.exists()) {
			filedir.mkdirs();
		}
		File file = new File(filedir + "/xml.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file, true);
		int length = str.getBytes().length;
		byte[] buf = str.getBytes();
		fos.write(buf, 0, length);
		fos.write(new String("\r\n").getBytes());
		fos.close();
	}

}
