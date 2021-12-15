package com.weather.weather4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.weather.weather4.adapter.FutureWeatherAdapter;
import com.weather.weather4.bean.DayWeatherBean;
import com.weather.weather4.bean.WeatherBean;
import com.weather.weather4.util.NetUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private String[] cities;

    private TextView tvWeather, tvTem, tvTemLowHigh, tvWin, tvAir;
    private ImageView ivWeather;
    private RecyclerView rlvFuture;
    private FutureWeatherAdapter futureWeatherAdapter;

    private DayWeatherBean today;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                String weather = (String) msg.obj;

                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);

                updateUIOfWeather(weatherBean);

            }
        }
    };

    private void updateUIOfWeather(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        List<DayWeatherBean> dayWeatherBeanList = weatherBean.getDayWeatherBeanList();
        today = dayWeatherBeanList.get(0);

        tvTem.setText(today.getTem());
        tvWeather.setText(today.getWea() + "(" + today.getDate() + today.getWeek() + ")");
        tvTemLowHigh.setText(today.getTem1() + "~" + today.getTem2());
        tvWin.setText(today.getWin()[0] + today.getWinSpeed());
        tvAir.setText("空气：" + today.getAir() + today.getAirLevel() + "\n" + today.getAirTips());
        ivWeather.setImageResource(getImgResOfWeather(today.getWeaImg()));

        dayWeatherBeanList.remove(0);

        futureWeatherAdapter = new FutureWeatherAdapter(this, dayWeatherBeanList);
        rlvFuture.setAdapter(futureWeatherAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rlvFuture.setLayoutManager(linearLayoutManager);

    }

    private int getImgResOfWeather(String weaStr) {

        int result = 0;
        switch (weaStr) {
            case "qing":
                result = R.drawable.biz_plugin_weather_qing;
                break;
            case "yin":
                result = R.drawable.biz_plugin_weather_yin;
                break;
            case "yu":
                result = R.drawable.biz_plugin_weather_dayu;
                break;
            case "yun":
                result = R.drawable.biz_plugin_weather_duoyun;
                break;
            case "bingbao":
                result = R.drawable.biz_plugin_weather_leizhenyubingbao;
                break;
            case "wu":
                result = R.drawable.biz_plugin_weather_wu;
                break;
            case "shachen":
                result = R.drawable.biz_plugin_weather_shachenbao;
                break;
            case "lei":
                result = R.drawable.biz_plugin_weather_leizhenyu;
                break;
            case "xue":
                result = R.drawable.biz_plugin_weather_daxue;
                break;
            default:
                result = R.drawable.biz_plugin_weather_qing;
                break;
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.sp_city);
        cities = getResources().getStringArray(R.array.cities);
        spinnerAdapter = new ArrayAdapter<>(this, R.layout.sp_item_layout, cities);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = cities[position];

                getWeatherOfCity(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        tvWin = findViewById(R.id.tv_win);

        ivWeather = findViewById(R.id.iv_weather);
        rlvFuture = findViewById(R.id.rlv_future_weather);

        tvAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TipsActivity.class);
                intent.putExtra("tips", today);


                startActivity(intent);
            }
        });
    }

    private void getWeatherOfCity(String selectedCity) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String weatherOfCity = NetUtil.getWeatherOfCity(selectedCity);
                Message message = Message.obtain();
                message.what = 0;
                message.obj = weatherOfCity;

                handler.sendMessage(message);

            }
        }).start();

    }
}