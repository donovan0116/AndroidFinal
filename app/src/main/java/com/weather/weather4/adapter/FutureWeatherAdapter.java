package com.weather.weather4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weather.weather4.R;
import com.weather.weather4.bean.DayWeatherBean;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {

    private Context context;
    private List<DayWeatherBean> dayWeatherBeanList;

    public FutureWeatherAdapter(Context context, List<DayWeatherBean> dayWeatherBeanList) {
        this.context = context;
        this.dayWeatherBeanList = dayWeatherBeanList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_item_layout, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DayWeatherBean dayWeatherBean = dayWeatherBeanList.get(position);

        holder.tvWeather.setText(dayWeatherBean.getWea());
        holder.tvTem.setText(dayWeatherBean.getTem());
        holder.tvTemLowHigh.setText(dayWeatherBean.getTem1() + "~" + dayWeatherBean.getTem2());
        holder.tvWin.setText(dayWeatherBean.getWin()[0] + dayWeatherBean.getWinSpeed());
        holder.tvAir.setText("空气" + dayWeatherBean.getAir() + dayWeatherBean.getAirLevel());
        holder.ivWeather.setImageResource(getImgResOfWeather(dayWeatherBean.getWeaImg()));

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
    public int getItemCount() {
        if (dayWeatherBeanList == null) return 0;
        return dayWeatherBeanList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView tvWeather, tvTem, tvTemLowHigh, tvWin, tvAir;
        ImageView ivWeather;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tvWeather = itemView.findViewById(R.id.tv_weather);
            tvAir = itemView.findViewById(R.id.tv_air);
            tvTem = itemView.findViewById(R.id.tv_tem);
            tvTemLowHigh = itemView.findViewById(R.id.tv_tem_low_high);
            tvWin = itemView.findViewById(R.id.tv_win);
            ivWeather = itemView.findViewById(R.id.iv_weather);

        }




    }


}
