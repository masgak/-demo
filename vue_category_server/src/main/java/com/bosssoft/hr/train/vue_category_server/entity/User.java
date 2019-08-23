package com.bosssoft.hr.train.vue_category_server.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "user")
public class User {
    int id;
    @Excel(name = "用户姓名",width = 15)
    String username;
    @Excel(name = "用户密码",width = 15)
    String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

