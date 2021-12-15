package com.weather.weather4.Service;

import android.database.sqlite.SQLiteDatabase;

import com.weather.weather4.Dao.UserDao;
import com.weather.weather4.bean.UserBean;

import java.util.List;

public class UserService {

    UserDao userDao = new UserDao();

    public UserBean Login(SQLiteDatabase dbHandler, String ID, String password) {

        List<UserBean> users = userDao.findUserByUsername(dbHandler);

        for (UserBean user : users) {
            if (user.getId().equals(ID) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public boolean Register(SQLiteDatabase dbHandler, UserBean user){
        return userDao.addUserRecord(dbHandler, user);
    }

    public boolean refresh(SQLiteDatabase dbHandler, UserBean user){
        return userDao.updateUser(dbHandler, user);
    }

}
