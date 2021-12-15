package com.weather.weather4.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {

    public static final String BASE_URL = "https://tianqiapi.com/api?unescape=1&version=v1&appid=18477751&appsecret=57CvaYIg";

    public static String doGet(String urlStr) {
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);

            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }

            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }


    public static String getWeatherOfCity(String city){
        // https://tianqiapi.com/api?unescape=1&version=v1&appid=18477751&appsecret=57CvaYIg
        String weather = BASE_URL + " &city=" + city;

        return doGet(weather);
    }

}
