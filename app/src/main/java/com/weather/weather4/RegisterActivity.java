package com.weather.weather4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        SQLiteDatabase dbHandler = sqLiteHelper.getWritableDatabase();

        Intent intentLogin = new Intent(this, LoginActivity.class);

        UserService service = new UserService();

        EditText et_reg_Name = findViewById(R.id.reg_Name);
        EditText et_reg_ID = findViewById(R.id.reg_ID);
        EditText et_reg_password = findViewById(R.id.reg_password);
        EditText et_reg_password2 = findViewById(R.id.reg_password2);
        EditText et_age = findViewById(R.id.age);

        final String[] gender = new String[1];

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Log.i("Reg","Radio group 创建成功");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_male) {
                    Toast.makeText(RegisterActivity.this, "您的性别是男", Toast.LENGTH_SHORT).show();
                    gender[0] = "男";
                }else if(checkedId == R.id.rb_female) {
                    Toast.makeText(RegisterActivity.this, "您的性别是女", Toast.LENGTH_SHORT).show();
                    gender[0] = "女";
                }
            }
        });


        Button reg = findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = et_reg_Name.getText().toString();
                String ID = et_reg_ID.getText().toString();
                String password = et_reg_password.getText().toString();
                String password2 = et_reg_password2.getText().toString();
                Integer age = Integer.valueOf(et_age.getText().toString());

                if(password.equals(password2)){
                    UserBean user = new UserBean(Name, ID, password, age, gender[0]);
                    if(service.Register(dbHandler, user)){
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        startActivity(intentLogin);
                    }else {
                        Toast.makeText(RegisterActivity.this, "注册失败！您已经注册过了", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}