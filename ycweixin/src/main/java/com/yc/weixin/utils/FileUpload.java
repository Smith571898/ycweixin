package com.yc.weixin.utils;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class FileUpload extends HttpServlet {
	
	private long singleSize=10*1024*1024;
	private String allowedFilesList="jpg,png,bmp,gif";
	private String deniedFilesList="bat,sh,exe,class,html,js,css";
	private long totalFileSize=4*singleSize;
	
	
	public Map<String , String>uploadFiles(PageContext pageContext,HttpServletRequest request) throws ServletException, IOException, SQLException, SmartUploadException{
		Map<String, String>map=new HashMap<String,String>();
		SmartUpload su=new SmartUpload();
		su.initialize(pageContext); //��ʼ��
		su.setCharset("utf-8");
		
		//���������ϴ����ļ�����
		su.setAllowedFilesList(allowedFilesList);
		//������
		su.setDeniedFilesList(deniedFilesList);
		//�����ļ��������
		su.setMaxFileSize(singleSize);
		//�����ϴ��ļ�������������
		su.setTotalMaxFileSize(totalFileSize);
		
		su.upload();
		
		//ȡ���� Request��smartupload��request-->HttpServletRequest
		Request re=su.getRequest();
		Enumeration<String> enu=re.getParameterNames();
		while(enu.hasMoreElements()){
			String pn=enu.nextElement();
			map.put(pn, re.getParameter(pn));
		}
		
		//ȡ���ϴ����ļ�
		Files files=su.getFiles();
		int count=files.getCount();
		if(files!=null&&count>0){
			for(int i=0;i<count;i++){
				//ȥTomcat·��
				Calendar c=Calendar.getInstance();
				String tomcatdir=request.getRealPath("/");
				File tomcatFile=new File( tomcatdir );//ȡnews�ĸ�·��
				File webapppath=tomcatFile.getParentFile();
				
				File picpath=new File(webapppath,"pic"+File.separator+c.get(Calendar.YEAR)+File.separator
						+(c.get(Calendar.MONTH)+1)+File.separator);
				//����·����
				String weburl="../pic/"+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/";
				
				//�ж�Ŀ¼�Ƿ����,�������򴴽�
				if(picpath.exists()==false){
					picpath.mkdirs();
				}
				//ֻȡ�б��еĵ�һ���ļ�,дȫ·��,��ֹϵͳ��Ϊ��java.io.File lei
				com.jspsmart.upload.File file=files.getFile(i);
				
				String filePrefixName=genNewFilePrefixName();
				//ȡ��ԭ�ļ���
				String extName=file.getFileExt();
				//ƴ���µ��ļ���
				String fileName=filePrefixName+"."+extName;
				
				weburl+=fileName;
				//����·��
				String destFilePathName=picpath+"/"+fileName;
				//��
				file.saveAs(destFilePathName,SmartUpload.SAVE_PHYSICAL);
				System.out.println(destFilePathName);
				
				request.setAttribute("destFilePathName", destFilePathName);
				String fieldName=file.getFieldName();
				String oldFileName=file.getFileName();
				
				map.put(fieldName+"_weburl", weburl);
				map.put(fieldName+"_destFilePathName", destFilePathName);
				map.put(fieldName+"_oldFileName", oldFileName);
			}
		
		}
		return map;
	}

				
	




	private String genNewFilePrefixName() {
		//�����µ��ļ���
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("SSyyyyMMddHHmmss");
		String filePrefixName=sdf.format(date);
		return filePrefixName;
	}
	
	public long getSingleSize(){
		return singleSize;
	}







	public String getAllowedFilesList() {
		return allowedFilesList;
	}


	public void setAllowedFilesList(String allowedFilesList) {
		this.allowedFilesList = allowedFilesList;
	}


	public String getDeniedFilesList() {
		return deniedFilesList;
	}


	public void setDeniedFilesList(String deniedFilesList) {
		this.deniedFilesList = deniedFilesList;
	}







	public long getTotalFileSize() {
		return totalFileSize;
	}







	public void setTotalFileSize(long totalFileSize) {
		this.totalFileSize = totalFileSize;
	}







	public void setSingleSize(long singleSize) {
		this.singleSize = singleSize;
	}
	
	

}
