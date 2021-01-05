package com.forger.fofa;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

class getGetDateByDomain {
    public static void main(String[] args) throws Exception {

        String email = "admin@gh0st.cn";
        String key = "dce587488b2b4217955fedbf8f7fde7d";
        String b64domain = "ZG9tYWluPSJiYWlkdS5jb20i";
        String size = "100";


        getDataByUrl(email, key, b64domain, size);
    }


    /**
     * get请求  发起fofa请求并获取网页数据
     * @param email  邮箱
     * @param key    api key
     * @param b64domain  base64编码后的搜索规则
     * @param size   查询数量
     * @return
     * @throws Exception
     */
    public static String getDataByUrl(String email, String key, String b64domain, String size) throws Exception {
        String url = "https://fofa.so/api/v1/search/all?email=";
        String full = "false";
        String fields = "host,port,header,title,ip,server";
        String content = null;
        String re_url = url + email + "&key=" + key + "&qbase64=" + b64domain + "&size=" + size + "&full=" + full + "&fields=" + fields;
        System.out.println(re_url);
        URLConnection urlConnection = new URL(re_url).openConnection();
        HttpURLConnection connection = (HttpURLConnection) urlConnection;
        connection.setRequestMethod("GET");
        //连接
        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                    (connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder bs = new StringBuilder();
            String l;
            while ((l = bufferedReader.readLine()) != null) {
                bs.append(l).append("\n");
            }
            content = bs.toString();
        }
        return(content);
    }
}
