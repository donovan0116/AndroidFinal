package com.weather.weather4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.weather.weather4.R;
import com.weather.weather4.adapter.TipsAdapter;
import com.weather.weather4.bean.DayWeatherBean;

public class TipsActivity extends AppCompatActivity {

    private RecyclerView rlvTips;
    private TipsAdapter tipsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        rlvTips = findViewById(R.id.rlv_tips);

        Intent intent = getIntent();
        DayWeatherBean dayWeatherBean = (DayWeatherBean) intent.getSerializableExtra("tips");

        if (dayWeatherBean == null) return;

        tipsAdapter = new TipsAdapter(this, dayWeatherBean.getOtherTipsBeanList());
        rlvTips.setAdapter(tipsAdapter);
        rlvTips.setLayoutManager(new LinearLayoutManager(this));

    }
}