package com.weather.weather4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weather.weather4.BaseDao.SQLiteHelper;
import com.weather.weather4.Service.UserService;
import com.weather.weather4.bean.UserBean;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String ID = sp.getString("ID", "");
        String password = sp.getString("password", "");

        if (!(ID.equals("") || password.equals(""))) {

            Intent intent = new Intent(this, UserInfoActivity.class);

            Toast.makeText(LoginActivity.this, "您已登录", Toast.LENGTH_SHORT).show();

            startActivity(intent);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        SQLiteDatabase dbHandler = sqLiteHelper.getWritableDatabase();

        Intent intentRegister = new Intent(this, RegisterActivity.class);
        Intent intentMain = new Intent(this, UserInfoActivity.class);

        UserService service = new UserService();

        EditText et_ID = findViewById(R.id.et_ID);
        EditText et_password = findViewById(R.id.et_password);

        //注册按钮，跳转到注册页面
        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentRegister);
            }
        });


//        登录按钮
        Button loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = et_ID.getText().toString();
                String password = et_password.getText().toString();
                UserBean currentUser = service.Login(dbHandler, ID, password);
                if (currentUser != null) {
                    Log.i("MainActivity", currentUser.getId());

                    SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("ID", ID);
                    editor.putString("password", password);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    startActivity(intentMain);
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败！请重新输入！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}