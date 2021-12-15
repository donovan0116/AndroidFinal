package com.weather.weather4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.weather4.BaseDao.SQLiteHelper;
import com.weather.weather4.Service.UserService;
import com.weather.weather4.bean.UserBean;

public class UserInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        SQLiteDatabase dbHandler = sqLiteHelper.getWritableDatabase();

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentRefreshInfo = new Intent(this, refreshInfo.class);
        Intent intentRefreshPassword = new Intent(this, refreshPassword.class);
        Intent intentBackToMain = new Intent(this, MainActivity.class);

        TextView userName = findViewById(R.id.userName);
        TextView mainName = findViewById(R.id.mainName);
        TextView mainID = findViewById(R.id.mainID);
        TextView mainAge = findViewById(R.id.mainAge);
        TextView mainGender = findViewById(R.id.mainGender);


        SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String ID = sp.getString("ID", "");
        String password = sp.getString("password", "");

        if (ID.equals("") || password.equals("")) {
            Toast.makeText(UserInfoActivity.this, "登录信息过期，请重新登录", Toast.LENGTH_SHORT).show();

            startActivity(intentLogin);
        } else {
            UserBean currentUser = new UserService().Login(dbHandler, ID, password);
            userName.setText(currentUser.getName());
            mainName.setText(currentUser.getName());
            mainID.setText(currentUser.getId());
            mainAge.setText(String.valueOf(currentUser.getAge()));
            mainGender.setText(currentUser.getGender());
        }

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("ID", "");
                editor.putString("password", "");
                editor.apply();
                finish();
                startActivity(intentLogin);
            }
        });

        Button refreshInfo = findViewById(R.id.refreshInfo);
        refreshInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRefreshInfo);
            }
        });

        Button refreshPassword = findViewById(R.id.refreshPassword);
        refreshPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRefreshPassword);
            }
        });

        Button backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentBackToMain);
            }
        });

    }
}