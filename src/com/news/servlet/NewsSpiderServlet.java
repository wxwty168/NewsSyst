package com.news.servlet;
import java.awt.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.*;
import com.news.dao.UserDaoInter;
import com.news.dao.Userdao;
import com.news.pojo.NewsData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.HttpClients;

import com.gargoylesoftware.htmlunit.html.*;
import org.apache.http.client.methods.HttpGet;
import org.eclipse.jetty.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NewsSpiderServlet")
public class NewsSpiderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        //webClient.getOptions().setJavaScriptEnabled(false);//不需要解析Js ps：某些网站是静态HTML页面
        webClient.getOptions().setJavaScriptEnabled(true);  //需要解析js
        webClient.setJavaScriptTimeout(10000);
        webClient.getOptions().setThrowExceptionOnScriptError(false);  //解析js出错时不抛异常
        webClient.getOptions().setTimeout(1000);  //超时时间  ms
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        UserDaoInter userDao = new Userdao();
        NewsData newsData = new NewsData();
        int flag=0;

        try{
            String getnewsurl = "";
            for (int pagenum=1;pagenum<20;pagenum++) {
                if(flag==1){break;}
                if (pagenum == 1) {
                   getnewsurl="http://news.hitwh.edu.cn/1059/list.htm";
                } else {
                    getnewsurl="http://news.hitwh.edu.cn/1059/list"+pagenum+".htm";
                }
                HtmlPage page = webClient.getPage(getnewsurl);
                List<HtmlElement> spanList = page.getByXPath("//div[@id=\"news_list\"]/ul/li/a");
                if (spanList.size() != 0) {
                    HtmlPage newspage = null;
                    DomElement newscontent = null;
                    List<HtmlElement> newauthor = null;
                    List<HtmlElement> newspic = null;
                    for (int i = 0; (i < spanList.size()); i++) {
                       // System.out.println(i + 1 + "、" + spanList.get(i).getAttribute("href"));
                        try{
                        String NewTilte =spanList.get(i).asText();
                        newsData.setTitle(NewTilte);
                       // if(userDao.ifTheNewsExistsByTitle(NewTilte)){flag=1;break;}  //判断是否已经录入数据库;
                        String newsurl = "http://news.hitwh.edu.cn" + spanList.get(i).getAttribute("href");
                       // System.out.println(newsurl);
                        newspage = webClient.getPage(newsurl);
                        //System.out.println(newspage.asXml());
                        newscontent = newspage.getElementById("newsContnet");
                        newscontent.setAttribute("class","news-content");
                        //System.out.println(newscontent.asXml());
                        newauthor = newspage.getByXPath("//div[@class=\"newsNav\"]"); //不会
                        //System.out.println(newauthor.get(0).asText());

                        //String NewsDateTmp = StringUtils.substringAfter(newauthor.get(0).asText(),"录入时间：");
                       // String NewsDate = StringUtils.substringBefore(NewsDateTmp,"日");
                        //System.out.println(NewsDate);
                        newspic = newscontent.getByXPath("//img[@data-layer=\"photo\"]");
                        for(int picnum = 0 ;picnum < newspic.size(); picnum++){
                            try{
                            newspic.get(picnum).setAttribute("src", "http://news.hitwh.edu.cn" + newspic.get(picnum).getAttribute("src"));
                            }finally { continue;
                            }
                        }
                        //System.out.println(newspic.get(0).getAttribute("src"));
                       //System.out.println(newscontent.asXml());
                         newsData.setContent(newscontent.asXml());
                        if(newspic.size()!=0){
                        newsData.setImamgeurl(newspic.get(0).getAttribute("src"));
                        }else newsData.setImamgeurl("");
                        newsData.setNewstype(5);
                            String aut = StringUtils.substringAfter(newauthor.get(0).asText(), "：");
                            String aut2 = StringUtils.substringBefore(aut, "来");
                            if (aut2!=null && aut2!=" ") {
                                try {
                                    newsData.setAuthor(aut2);
                                }finally {
                                }
                            }else { newsData.setAuthor("佚名");}
                        }finally {userDao.addNews(newsData);
                            continue;
                        }
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (FailingHttpStatusCodeException e){
            e.printStackTrace();
        }finally {
            webClient.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
