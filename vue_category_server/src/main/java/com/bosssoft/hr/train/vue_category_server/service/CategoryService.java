package com.bosssoft.hr.train.vue_category_server.service;

import com.bosssoft.hr.train.vue_category_server.dao.CategoryDao;
import com.bosssoft.hr.train.vue_category_server.entity.Category;
import com.bosssoft.hr.train.vue_category_server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> getAll(){
        return categoryDao.getAll();
    }

    public List<Category> getAllLike(String name,String category,Integer status){
        return categoryDao.getAllLike(name,category,status);
    }

    public Category getCategoryByID(long id){
        return categoryDao.getCategoryByID(id);
    }

    public User getUser(String username , String password){
        return categoryDao.getUser(username,password);
    }

    public void  delete(long id){ categoryDao.delete(id); }

    public void update(Category p){ categoryDao.update(p); }

    public void newp(Category p){
        categoryDao.newp(p);
    }
}
