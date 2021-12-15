package com.weather.weather4.bean;

public class UserBean {
    private String id;
    private String name;
    private String password;
    private Integer age;
    private String gender;

    public UserBean(String name, String id, String password, Integer age, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
//    private String Name;
//    private String ID;
//    private String password;
//    private int age;
//    private String gender;
//
//    public UserBean(String name, String ID, String password, int age, String gender) {
//        Name = name;
//        this.ID = ID;
//        this.password = password;
//        this.age = age;
//        this.gender = gender;
//    }
//
//    public UserBean() {
//    }
//
//    public UserBean(String toString, String toString1) {
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
//
//    public String getID() {
//        return ID;
//    }
//
//    public void setID(String ID) {
//        this.ID = ID;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
}
