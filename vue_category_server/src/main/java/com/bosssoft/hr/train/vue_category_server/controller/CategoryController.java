package com.bosssoft.hr.train.vue_category_server.controller;

import com.bosssoft.hr.train.vue_category_server.entity.Category;
import com.bosssoft.hr.train.vue_category_server.entity.User;
import com.bosssoft.hr.train.vue_category_server.result.Result;
import com.bosssoft.hr.train.vue_category_server.service.CategoryService;
import com.bosssoft.hr.train.vue_category_server.tool.ExcelUtils;
import com.bosssoft.hr.train.vue_category_server.tool.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @CrossOrigin
    @GetMapping(value = "api/categories")
    public List<Category> list()  {
        return categoryService.getAll();
    }

    @CrossOrigin
    @PostMapping(value = "api/findbyid")
    public Category updateInfo(@RequestBody String ids)  {
        if (ids != null){
            System.out.println(ids);
            try {
                long id = Long.valueOf(ids.substring(0,1));
                return categoryService.getCategoryByID(id);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }

    }

    @CrossOrigin
    @PostMapping("/api/add")
    public Category add(@RequestBody Category category)  {
        if (category != null){
            //由雪花算法生成相关id
            SnowFlake snowFlake = new SnowFlake(2,3);
            category.setCategory_id(snowFlake.nextId());

            //获取创建时间以及更新时间,默认创建时两者相同
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String updateTime = sdf.format(new Date());
            String createTime = updateTime;
            category.setUpdated_time(updateTime);
            category.setCreated_time(createTime);

            //创建一些默认参数,版本号、机构id、创建更新者id
            category.setVersion(1);
            category.setOrg_id(1);
            category.setCreated_by(2001);
            category.setUpdated_by(2001);
            try {
                categoryService.newp(category);
                return category;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping("/api/update")
    public Category update(@RequestBody Category category)  {
        if (category != null){
            System.out.println(category.getCategory_id());
            System.out.println(category.getCategory());
            //获取更新时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String updateTime = sdf.format(new Date());
            category.setUpdated_time(updateTime);

            //创建一些默认参数,版本号、机构id、创建更新者id
            category.setVersion(1);
            category.setOrg_id(1);
            category.setCreated_by(2001);
            category.setUpdated_by(2001);
            try {
                categoryService.update(category);
                return category;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    public Result delete(@RequestBody String ids)  {
        if (ids != null){
            long id = Long.valueOf(ids.substring(0,1));
            System.out.println(id);
            try {
                categoryService.delete(id);
                return new Result(400);
            }catch (Exception e){
                e.printStackTrace();
                return new Result(200);
            }
        }else {
            return new Result(200);
        }
    }

    @CrossOrigin
    @PostMapping("/api/search")
    public List<Category> search(@RequestBody Category requestUser){
        if (requestUser != null){
            String name = requestUser.getName();
            System.out.println(name);
            name = HtmlUtils.htmlEscape(name);
            String category = requestUser.getCategory();
            System.out.println(category);
            category = HtmlUtils.htmlEscape(category);
            Integer status = requestUser.getStatus();
            System.out.println(status);
            try {
                return categoryService.getAllLike(name,category,status);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }else {
            return null;
        }
    }

    @CrossOrigin
    @RequestMapping("/api/execel")
    public void export(HttpServletResponse response){
        List<Category> all = categoryService.getAll();
        ExcelUtils.exportExcel(all,"测试导出","导出",Category.class,
                "测试.xls",response);
    }

//    @CrossOrigin
//    @RequestMapping("/api/execel2")
//    public void export2(HttpServletResponse response){
//        List<User> all = userService.getAll();
//        ExcelUtils.exportExcel(all,"测试导出","导出", User.class,
//                "测试用户.xls",response);
//    }

}

