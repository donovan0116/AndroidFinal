package com.weather.weather4.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.weather.weather4.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public boolean addUserRecord(SQLiteDatabase dbHandler, UserBean user) {

        List<UserBean> users = findUserByUsername(dbHandler);

        for (UserBean userBean : users) {
            if (userBean.getId().equals(user.getId())) {
                Log.i("UserDao", "冲突，插入失败");
                return false;
            }
        }

        ContentValues values = new ContentValues();

        values.put("Name", user.getName());
        values.put("ID", user.getId());
        values.put("password", user.getPassword());
        values.put("age", user.getAge());
        values.put("gender", user.getGender());

        dbHandler.insert("users", null, values);

        Log.i("UserDao", "插入一条数据......");

        return true;

    }

    public List<UserBean> findUserByUsername(SQLiteDatabase dbHandler) {

        List<UserBean> users = new ArrayList<>();

        Cursor cursor = dbHandler.query("users", new String[]{"Name", "ID", "password", "age", "gender"}, null, null, null, null, null);

        UserBean getUser;

        while (cursor.moveToNext()) {
            getUser = new UserBean(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4));
            users.add(getUser);
        }
        cursor.close();

        return users;
    }

    public void deleteUser(SQLiteDatabase dbHandler, UserBean user) {
        String ID = user.getId();
        dbHandler.delete("users", "ID=?", new String[]{ID});
        Log.i("UserDao", "删除一条数据");
    }

    public boolean updateUser(SQLiteDatabase dbHandler, UserBean user){
        deleteUser(dbHandler, user);
        return addUserRecord(dbHandler, user);
    }

}