package com.jeiel.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class Delete {
	private static String postUrl = "http://myoffer.cn/external/api/courses";
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		for(int id=1;id<=21;id++){//id为网页上显示的id号
			System.out.println("delete "+id);
			delete(postUrl,id);
		}
	   
	}
	
	public static HttpURLConnection getConnection(String postUrl) throws IOException{
		URL url = new URL(postUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setRequestMethod("POST");
	    connection.setUseCaches(false);
	    connection.setInstanceFollowRedirects(true);
	    //connection.setRequestProperty("Host", "myoffer.cn");
	    //connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:40.0) Gecko/20100101 Firefox/40.0");
	    connection.setRequestProperty("Accept", "application/json, text/plain, */*");
	    connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
	    connection.setRequestProperty("Referer", "http://myoffer.cn/external/course");
	    connection.setRequestProperty("Cookie", "CNZZDATA1256122972=436580706-1440482499-http%253A%252F%252Fmyoffer.cn%252F%7C1441087693; connect.sid=s%3AkmPA2lJJjDsGR4Ag60QDFLl21VbxrP7_.oEtXldNCyVcbQsodvVe%2FsCXE7X%2BEJ7zxr3pxcZgmFlQ");
	    connection.setRequestProperty("Connection", "keep-alive");
	    connection.setRequestProperty("Pragma", "no-cache");
	    connection.setRequestProperty("Cache-Control", "no-cache");
	    connection.connect();
	    return connection;
	}
	
	public static void delete(String postUrl,int id) throws IOException{
	    
		HttpURLConnection connection=getConnection(postUrl);
		DataOutputStream out= new DataOutputStream(connection.getOutputStream());
		
	    //固定值
	    JSONObject entry=new JSONObject();
	    entry.put("target", "course");
	    entry.put("action", "remove");
	    
	    //自定义值
	   	JSONObject value=new JSONObject();
	    value.put("university", "saos");
	    value.put("id", id);
	   	entry.put("value", value);
	    
	    /*{"target":"course","action":"remove",
	     		"value":{"university":"saos","id":65}}*/
	    
	    
	    //System.out.println(entry.toString());
	    out.writeBytes(entry.toString());
	    out.flush();
	    
	    //读取响应

	    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String lines;
	    StringBuffer sb = new StringBuffer("");
	    while ((lines = reader.readLine()) != null) {
	    	lines = new String(lines.getBytes(), "utf-8");
	    	sb.append(lines);
	    }
	    
	    System.out.println(sb);
	    
	    out.close();
	    connection.disconnect();
	    reader.close();
	}
	
}
