package com.lcc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

public class UpLoadUtil {
	private static final int BUFFER_SIZE =16*1024;
	private static void copy(File src,File dst){
		try {
			InputStream inputStream  = null;
			OutputStream outputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(src));
				outputStream = new BufferedOutputStream(new FileOutputStream(dst));
				byte [] buffer = new byte[BUFFER_SIZE];
				while(inputStream.read(buffer)>0){
					outputStream.write(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (null != inputStream) {
					inputStream.close();
				}
				
				if (null != outputStream) {
					outputStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getExtention(String fileName){
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	
	public static String upload(File src){
		if (src == null) {
			return "";
		}
		
		String tempName = new Date().getTime() + getExtention(src.getName());
		File imageFile = new File(ServletActionContext
				.getServletContext()
				.getRealPath("UploadImages")+"/"+tempName);
		
		copy(src, imageFile);
		return tempName;
	}

}
