package com.weather.weather4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.weather.weather4.BaseDao.SQLiteHelper;
import com.weather.weather4.Service.UserService;
import com.weather.weather4.bean.UserBean;

public class refreshInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_info);
        UserService service = new UserService();
        Intent intentMain = new Intent(this, MainActivity.class);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        SQLiteDatabase dbHandler = sqLiteHelper.getWritableDatabase();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String ID = sp.getString("ID", "");
        String password = sp.getString("password", "");

        UserBean currentUser = new UserService().Login(dbHandler, ID, password);

        EditText et_Name = findViewById(R.id.refresh_Name);
        et_Name.setHint(currentUser.getName());

        EditText et_Age = findViewById(R.id.refresh_age);
        et_Age.setHint(String.valueOf(currentUser.getAge()));

        final String[] gender = new String[1];

        RadioGroup radioGroup = findViewById(R.id.refreshRadioGroup);
        Log.i("Reg","Radio group 创建成功");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_refresh_male) {
                    Toast.makeText(refreshInfo.this, "您的性别是男", Toast.LENGTH_SHORT).show();
                    gender[0] = "男";
                }else if(checkedId == R.id.rb_refresh_female) {
                    Toast.makeText(refreshInfo.this, "您的性别是女", Toast.LENGTH_SHORT).show();
                    gender[0] = "女";
                }
            }
        });

        Button submitRefresh = findViewById(R.id.submitRefresh);
        submitRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = et_Name.getText().toString();
                int age = Integer.parseInt(et_Age.getText().toString());

                UserBean user = new UserBean(Name, ID, password, age, gender[0]);

                if(service.refresh(dbHandler, user)){
                    Log.i("refreshInfo", "更新成功！");
                    SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("ID", "");
                    editor.putString("password", "");
                    editor.apply();
                    startActivity(intentMain);
                }
            }
        });
    }
}