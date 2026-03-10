package com.example.lab7_recyclerview;

public class Student {

    private int id;
    private String avatar;
    private String name;
    private String email;

    public Student(int id, String avatar, String name, String email) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.email = email;
    }

    public Student(String avatar, String name, String email) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
