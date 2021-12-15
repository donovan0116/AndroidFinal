package com.weather.weather4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.weather.weather4.BaseDao.SQLiteHelper;
import com.weather.weather4.Service.UserService;
import com.weather.weather4.bean.UserBean;

public class refreshPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_password);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        SQLiteDatabase dbHandler = sqLiteHelper.getWritableDatabase();

        EditText et_oldPassword = findViewById(R.id.oldPassword);
        EditText et_newPasssword1 = findViewById(R.id.newPassword1);
        EditText et_newPasssword2 = findViewById(R.id.newPassword2);

        UserService service = new UserService();
        Intent intentLogin = new Intent(this, LoginActivity.class);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String ID = sp.getString("ID", "");
        String password = sp.getString("password", "");

        UserBean currentUser = new UserService().Login(dbHandler, ID, password);

        Button submitRefreshPassword = findViewById(R.id.submitRefreshPassword);
        submitRefreshPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = et_oldPassword.getText().toString();
                String newPassword1 = et_newPasssword1.getText().toString();
                String newPassword2 = et_newPasssword2.getText().toString();

                if (!oldPassword.equals(password)) {
                    Toast.makeText(refreshPassword.this, "请输入正确的原密码！", Toast.LENGTH_SHORT).show();
                } else if (!newPassword1.equals(newPassword2)) {
                    Toast.makeText(refreshPassword.this, "两次密码请保持一致", Toast.LENGTH_SHORT).show();
                } else {
                    currentUser.setPassword(newPassword1);
                    service.refresh(dbHandler, currentUser);
                    Toast.makeText(refreshPassword.this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("ID", "");
                    editor.putString("password", "");
                    editor.apply();
                    startActivity(intentLogin);
                }
            }
        });
    }
}