package com.bosssoft.hr.train.vue_category_server.controller;

import com.bosssoft.hr.train.vue_category_server.entity.Category;
import com.bosssoft.hr.train.vue_category_server.entity.User;
import com.bosssoft.hr.train.vue_category_server.result.Result;
import com.bosssoft.hr.train.vue_category_server.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    CategoryService categoryService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = categoryService.getUser(username,requestUser.getPassword());
        if (user == null){
            return new Result(400);
        }else {
            session.setAttribute("user",user);
            return new Result(200);
        }
    }

}
