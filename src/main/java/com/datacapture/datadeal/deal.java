package com.datacapture.datadeal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @ClassName: deal
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2018/8/10 16:04
 * @Version 1.0
 */
public class deal {
    public static void main(String[] args) throws IOException {
        //1.获取阿里银行合作伙伴页面
        Document doc = Jsoup.connect("http://ab.alipay.com/i/yinhang.htm").get();
        Element body = doc.body();
        //2.获取对应数据的标签
        Elements es = body.select("li#ap-a-cnt-bank>ul>li>a>span");
        //3.遍历标签中的数据
        for (Element e:es){
            String name = e.attr("title");
            String eName = e.attr("class");
            //4.获取银行icon地址
            String url = "https://apimg.alipay.com/combo.png?d=cashier&t=";
            if (eName!=null && eName!="") eName = eName.substring(eName.indexOf(" ")+1);
            url = url+eName;
            //5.打印sql
            System.out.println("insert into bank_list(name,ename,icon,cts,uts) values('"+name+"','"+eName+"','"+url+"',now(),now()) ON DUPLICATE KEY UPDATE uts=now();");
    }}
}
