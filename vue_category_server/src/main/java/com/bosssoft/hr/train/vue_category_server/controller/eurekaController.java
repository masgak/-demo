package com.bosssoft.hr.train.vue_category_server.controller;

import com.bosssoft.hr.train.vue_category_server.entity.Category;
import com.bosssoft.hr.train.vue_category_server.result.Result;
import com.bosssoft.hr.train.vue_category_server.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class eurekaController {

    @Autowired
    CategoryService categoryService;

    @Value("${server.port}")
    String port;

    @RequestMapping("/category")
    public String category(@RequestParam String name){
        String category = null;
        int status = 1;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(name);
        List<Category> categories = categoryService.getAllLike(name,category,status);
        if (categories != null){
            return  gson.toJson(categories);
        }else {
            return "暂无该数据！";
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String name)  {
        if (name != null){
            long id = Long.valueOf(name.substring(0,1));
            System.out.println(id);
            try {
                categoryService.delete(id);
                return "删除成功";
            }catch (Exception e){
                e.printStackTrace();
                return "删除失败";
            }
        }else {
            return "删除失败";
        }
    }

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
}
