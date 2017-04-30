package com.granda.moviecard;

import java.io.FileWriter;
import java.io.Writer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class Crawler {
	public static String homePage = "http://www.bttt.la"; 
	@Test
	public void test() throws Exception{
		Document doc = Jsoup.connect(homePage).get();
		
		//创建字节输出流
		Writer fw = new FileWriter("/home/granda/Desktop/data.csv");
		StringBuffer alias = new StringBuffer();
		StringBuffer tags = new StringBuffer();
		StringBuffer region = new StringBuffer();
		StringBuffer year = new StringBuffer();
		StringBuffer director = new StringBuffer();
		StringBuffer screenWriter = new StringBuffer();
		StringBuffer actor  = new StringBuffer();
		String douBanId = "";
		
		
		Elements url = doc.getElementsByClass("item cl");
		for (int i=0;i<url.size();i++){
			String childUrl = homePage + url.get(i).select("a").attr("href");
			Document childDoc = Jsoup.connect(childUrl).get();
			String title = childDoc.getElementsByClass("moviedteail_tt").select("h1").text();
			System.out.println(title);
			Elements attrsDoc = childDoc.getElementsByClass("moviedteail_list").select("li");
			int num = attrsDoc.size();
			System.out.println(num);
			if(attrsDoc.size() == 9){
				//别名
				alias = new StringBuffer();
				for(int f=0; f < attrsDoc.get(0).select("a").size();f++){
					alias.append(attrsDoc.get(0).select("a").get(f).attr("title")+"!");
				}
				System.out.println(alias);
				
				//标签
				tags = new StringBuffer();
				for(int f=0; f < attrsDoc.get(1).select("a").size();f++){
					tags.append(attrsDoc.get(1).select("a").get(f).attr("title")+"!");
				}
				System.out.println(tags);
				
				//地区
				region = new StringBuffer();
				for(int f=0; f < attrsDoc.get(2).select("a").size();f++){
					region.append(attrsDoc.get(2).select("a").get(f).attr("title")+"!");
				}
				System.out.println(region);
				
				//年份
				year = new StringBuffer();
				for(int f=0; f < attrsDoc.get(3).select("a").size();f++){
					year.append(attrsDoc.get(3).select("a").get(f).attr("title")+"!");
				}
				System.out.println(year);
				
				//导演
				director = new StringBuffer();
				for(int f=0; f < attrsDoc.get(4).select("a").size();f++){
					director.append(attrsDoc.get(4).select("a").get(f).attr("title")+"!");
				}
				System.out.println(director);
				
				//编剧
			    screenWriter = new StringBuffer();
				for(int f=0; f < attrsDoc.get(5).select("a").size();f++){
					screenWriter.append(attrsDoc.get(5).select("a").get(f).attr("title")+"!");
				}
				System.out.println(screenWriter);
				
				//主演
				actor  = new StringBuffer();
				for(int f=0; f < attrsDoc.get(6).select("a").size();f++){
					actor .append(attrsDoc.get(6).select("a").get(f).attr("title")+"!");
				}
				System.out.println(actor );
				
				//豆瓣ID
				String douBanUrl  = homePage + attrsDoc.get(8).select("a").attr("href");
			
				Document douBanDoc = Jsoup.connect(douBanUrl).get();
				String[] temp = douBanDoc.getElementsByTag("script").get(0).data().toString().split("\"");
				String[] temp2 = temp[1].split("/");
				douBanId = temp2[4];
				//格式http://movie.douban.com/subject/25765735/
				System.out.println(douBanId);
				System.out.println(temp[1]);
				
			}
			
			if(attrsDoc.size() == 8){
				//别名
//				StringBuffer alias = new StringBuffer();
//				for(int f=0; f < attrsDoc.get(0).select("a").size();f++){
//					alias.append(attrsDoc.get(0).select("a").get(f).attr("title")+"!");
//				}
//				System.out.println(alias);
				
				//标签
				tags = new StringBuffer();
				for(int f=0; f < attrsDoc.get(0).select("a").size();f++){
					tags.append(attrsDoc.get(0).select("a").get(f).attr("title")+"!");
				}
				System.out.println(tags);
				
				//地区
				region = new StringBuffer();
				for(int f=0; f < attrsDoc.get(1).select("a").size();f++){
					region.append(attrsDoc.get(1).select("a").get(f).attr("title")+"!");
				}
				System.out.println(region);
				
				//年份
				year = new StringBuffer();
				for(int f=0; f < attrsDoc.get(2).select("a").size();f++){
					year.append(attrsDoc.get(2).select("a").get(f).attr("title")+"!");
				}
				System.out.println(year);
				
				//导演
				director = new StringBuffer();
				for(int f=0; f < attrsDoc.get(3).select("a").size();f++){
					director.append(attrsDoc.get(3).select("a").get(f).attr("title")+"!");
				}
				System.out.println(director);
				
				//编剧
				screenWriter = new StringBuffer();
				for(int f=0; f < attrsDoc.get(4).select("a").size();f++){
					screenWriter.append(attrsDoc.get(4).select("a").get(f).attr("title")+"!");
				}
				System.out.println(screenWriter);
				
				//主演
				actor  = new StringBuffer();
				for(int f=0; f < attrsDoc.get(5).select("a").size();f++){
					actor .append(attrsDoc.get(5).select("a").get(f).attr("title")+"!");
				}
				System.out.println(actor );
				
				//豆瓣ID
				String douBanUrl  = homePage + attrsDoc.get(7).select("a").attr("href");
			
				Document douBanDoc = Jsoup.connect(douBanUrl).get();
				String[] temp = douBanDoc.getElementsByTag("script").get(0).data().toString().split("\"");
				String[] temp2 = temp[1].split("/");
			    douBanId = temp2[4];				//格式http://movie.douban.com/subject/25765735/
				System.out.println(douBanId);
				System.out.println(temp[1]);
				
			}
			
			fw.write(alias + "," + tags + "," + region + "," + year 
					+ "," + director + "," + screenWriter + "," + actor + "," + douBanId + "/n");  
			fw.flush();
		}
//	    System.out.println("url:" + url.get(1).select("a").attr("href"));
//	    System.out.println("url:" + url.get(2).select("a").attr("href"));
		fw.close();
	}
	
}
