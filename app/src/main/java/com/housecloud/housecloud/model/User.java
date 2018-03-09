package com.housecloud.housecloud.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jozet on 08/03/2018.
 */

public class User{

    private String name;
    private String email;
    private String phone;
    private String cp;
    private String online;
    private String image;
    private HashMap<String, Post> posts;

    public User() {
    }


    public User(String name, String email, String phone, String cp, String online, String image, HashMap<String, Post> posts) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cp = cp;
        this.online = online;
        this.image = image;
        this.posts = posts;
    }

    public User(String name, String email, String phone, String cp, String online, String image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cp = cp;
        this.online = online;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public HashMap<String, Post> getPosts() {
        return posts;
    }

    public void setPosts(HashMap<String, Post> posts) {
        this.posts = posts;
    }
}
