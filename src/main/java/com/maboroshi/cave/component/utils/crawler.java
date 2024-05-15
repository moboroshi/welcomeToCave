package com.maboroshi.cave.component.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class crawler {
    public static void main(String[] args) {
        String baseurl = "https://gnn.gamer.com.tw/";
        List<String[]> datalist = getData(baseurl);
        for (String[] data : datalist) {
            System.out.println("Name: " + data[0]);
            System.out.println("Image: " + data[1]);
            System.out.println("Link: " + data[2]);
        }
    }

    public static List<String[]> getData(String baseurl) {
        List<String[]> datalist = new ArrayList<>();
        String html = askURL(baseurl);
        Pattern findall = Pattern.compile("<div class=\"GN-lbox2B\"><div class=\"GN-lbox2E\">(.*?)繼續閱讀</a></p></div>", Pattern.DOTALL);
        Matcher matches = findall.matcher(html);
        while (matches.find()) {
            String tbody = matches.group(1);
            String[] data = new String[3];
            Pattern findName = Pattern.compile("</div><a href=\"//gnn.gamer.com.tw/.*?\">(.*?)</a><img src=");
            Matcher matcher = findName.matcher(tbody);
            if (matcher.find()) {
                data[0] = matcher.group(1);
            } else {
                Pattern findName1 = Pattern.compile("</div><a href=\"//gnn.gamer.com.tw/.*?\">(.*?)</a><span>");
                matcher = findName1.matcher(tbody);
                if (matcher.find()) {
                    data[0] = matcher.group(1);
                }
            }

            Pattern findImg = Pattern.compile("<a href=\"//.*\"><img src=\"(.*?)\" srcset=");
            matcher = findImg.matcher(tbody);
            if (matcher.find()) {
                data[1] = matcher.group(1);
            } else {
                data[1] = "未知";
            }

            Pattern findLink = Pattern.compile("<a href=\"(.*?)\"><img src=\"");
            matcher = findLink.matcher(tbody);
            if (matcher.find()) {
                data[2] = matcher.group(1);
            }

            datalist.add(data);
        }
        return datalist;
    }

    public static String askURL(String urlString) {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(urlString);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}