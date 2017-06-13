package com.change.hippo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * User: change.long@99bill.com
 * Date: 2017/3/27
 * Time: 下午5:57
 */
public class LocationHelper {


    public static void main(String[] args) throws Exception {
//        ipLocation();
//
//        System.out.println();
//
//        idCardLocation();
//
//
//        System.out.println();

//        id4Location15tianqi();
        id4Locationalai();
    }

    private static void ipLocation() throws Exception {
        String mobile = "13800138000";
        findLocationByMobile(mobile);
    }

    private static void idCardLocation() throws Exception {
        String idCard = "340823198810142936";
        findLocationIdCard(idCard);
    }


    private static void id4Locationalai() throws Exception {
        String idCard6Spec = "332627\n" +
                "330724\n" +
                "332626\n" +
                "133025\n" +
                "511023\n" +
                "510212\n" +
                "332623\n" +
                "430219\n" +
                "460022\n" +
                "330722\n" +
                "320919\n" +
                "120224\n" +
                "522321\n" +
                "120222\n" +
                "420523\n" +
                "140511\n" +
                "342421\n" +
                "510202\n" +
                "510721\n" +
                "511011\n" +
                "512225\n" +
                "432624\n" +
                "440921\n" +
                "440524\n" +
                "420381\n" +
                "610328\n" +
                "422201\n" +
                "350221\n" +
                "132903\n" +
                "152622\n" +
                "320524\n" +
                "232321\n" +
                "511525\n" +
                "320525\n" +
                "320522\n" +
                "513101\n" +
                "432930\n" +
                "410901\n" +
                "412923\n" +
                "513622\n" +
                "512532\n" +
                "211022\n" +
                "512533\n" +
                "513621\n" +
                "412921\n" +
                "332601\n" +
                "441522\n" +
                "412929\n" +
                "372822\n" +
                "360311\n" +
                "422723\n" +
                "612102\n" +
                "654221\n" +
                "422325\n" +
                "231022\n" +
                "422326\n" +
                "642103\n" +
                "510224\n" +
                "420704\n" +
                "510622\n" +
                "350127\n" +
                "132127\n" +
                "440702\n" +
                "642123\n" +
                "132924\n" +
                "130228\n" +
                "130226\n" +
                "612328\n" +
                "612326\n" +
                "149001\n" +
                "232625\n" +
                "410121\n" +
                "232101\n" +
                "511027\n" +
                "410123\n" +
                "232103\n" +
                "510217\n" +
                "320222\n" +
                "511223\n" +
                "511224\n" +
                "330123\n" +
                "330124\n" +
                "612325\n" +
                "522121\n" +
                "612322\n" +
                "612321\n" +
                "510128\n" +
                "342222\n" +
                "210221\n" +
                "230119\n" +
                "432321\n" +
                "432427\n" +
                "232131\n" +
                "522127\n" +
                "330511\n" +
                "222324\n" +
                "522128\n" +
                "522524\n" +
                "350781\n" +
                "522129\n" +
                "420221\n" +
                "220422\n" +
                "352601\n" +
                "362127\n" +
                "522130\n" +
                "522131\n" +
                "320326\n" +
                "450402\n" +
                "511204\n" +
                "341127\n" +
                "410426\n" +
                "370283\n" +
                "513025\n" +
                "370723\n" +
                "330222\n" +
                "370721\n" +
                "422127\n" +
                "422128\n" +
                "413026\n" +
                "342201\n" +
                "512326\n" +
                "220603\n" +
                "413028\n" +
                "512201\n" +
                "352221\n" +
                "522502\n" +
                "440223\n" +
                "142125\n" +
                "142124\n" +
                "310228\n" +
                "140102\n" +
                "320822\n" +
                "433021\n" +
                "511225\n" +
                "211222\n" +
                "433024\n" +
                "320421\n" +
                "433023" ;
        String[] array = idCard6Spec.split("\\n");
        for (String s : array) {
            Thread.sleep(500);
            findLocationId4ALai(s);
        }

    }

    private static void findLocationId4ALai(String idCard6) throws Exception {
        String url = String.format("http://hm.alai.net/index.php/Idcard/index/nb/%s/", idCard6);
        Document doc = Jsoup.parse(new URL(url), 3000);
        if (doc != null) {
            //从class=tdc样式下面抓取
            Elements tdcs = doc.getElementsByTag("table");
            for (Element td : tdcs) {
                //从class=tdc2样式下面抓取
                Elements tdc2s = td.getElementsByTag("td");
                for (Element tdc : tdc2s) {
                    //System.out.println(tdc);
                    //<[^>]+>去掉html标签,去掉&nbsp;html标签的空格
                    String mobileInfo = tdc.select("td").html().replaceAll("<[^>]+>", "").replaceAll("&nbsp;", "").replaceAll("-->", "");
                    if (mobileInfo.startsWith("地 区 码")){
                        System.out.print(mobileInfo.replaceAll("地 区 码：","").trim());
                    }else if (mobileInfo.startsWith("籍贯所在地区")){
                        System.out.println("," + mobileInfo.replaceAll("籍贯所在地区： ","").trim());
                    }
                }
            }
        } else {
            System.err.println("网络异常~~");
        }
    }

    private static void id4Location15tianqi() throws Exception {
//            findLocationId4(s);
    }

    private static void findLocationId4(String id4) throws Exception {
        String url = String.format("http://15tianqi.cn/sfz/%s/", id4);
        Document doc = Jsoup.parse(new URL(url), 3000);
        if (doc != null) {
            //从class=tdc样式下面抓取
            Elements tdcs = doc.getElementsByAttributeValue("class", "buletable");
            for (Element td : tdcs) {
                //从class=tdc2样式下面抓取
                Elements tdc2s = td.getElementsByTag("tbody");
                for (Element tdc : tdc2s) {
                    //System.out.println(tdc);
                    //<[^>]+>去掉html标签,去掉&nbsp;html标签的空格
                    String mobileInfo = tdc.select("td").html().replaceAll("<[^>]+>", "").replaceAll("&nbsp;", "").replaceAll("-->", "");
                    String[] array = mobileInfo.split("\\n");

                    int length = array.length;
                    if (array.length > 0) {
                        for (int i = 0, len = length; i < len; i += 2) {
                            System.out.println(array[i] + " " + array[i + 1]);
                        }
                    }

                }
            }
        } else {
            System.err.println("网络异常~~");
        }
    }

    private static void findLocationIdCard(String idCard) throws IOException {
        String url = String.format("http://qq.ip138.com/idsearch/index.asp?action=idcard&userid=%s", idCard);
        Document doc = Jsoup.parse(new URL(url), 3000);
        if (doc != null) {
            //从class=tdc样式下面抓取
            Elements tdcs = doc.getElementsByAttributeValue("class", "tdc2");
            for (Element td : tdcs) {
                //从class=tdc2样式下面抓取
                Elements tdc2s = td.getElementsByAttributeValue("class", "tdc2");
                for (Element tdc : tdc2s) {
                    //System.out.println(tdc);
                    //<[^>]+>去掉html标签,去掉&nbsp;html标签的空格
                    String mobileInfo = tdc.select("td").html().replaceAll("<[^>]+>", "").replaceAll("&nbsp;", "").replaceAll("-->", "");
                    System.out.println(mobileInfo);
                }
            }
        } else {
            System.err.println("网络异常~~");
        }
    }

    private static void findLocationByMobile(String mobile) throws Exception {
        StringBuffer buffer = new StringBuffer();
        String url = "http://www.ip138.com";
        buffer.append(url);
        buffer.append(":8080");//端口
        buffer.append("/");
        buffer.append("search.asp?");
        buffer.append("mobile=").append(mobile);
        buffer.append("&action=mobile");

        String basePath = buffer.toString();

        Document doc = Jsoup.parse(new URL(basePath), 3000);
        if (doc != null) {
            //从class=tdc样式下面抓取
            Elements tdcs = doc.getElementsByAttributeValue("class", "tdc");
            for (Element td : tdcs) {
                //从class=tdc2样式下面抓取
                Elements tdc2s = td.getElementsByAttributeValue("class", "tdc2");
                for (Element tdc : tdc2s) {
                    //System.out.println(tdc);
                    //<[^>]+>去掉html标签,去掉&nbsp;html标签的空格
                    String mobileInfo = tdc.select("td").html().replaceAll("<[^>]+>", "").replaceAll("&nbsp;", "").replaceAll("-->", "");
                    System.out.println(mobileInfo);
                }
            }
        } else {
            System.err.println("网络异常~~");
        }
    }


}
