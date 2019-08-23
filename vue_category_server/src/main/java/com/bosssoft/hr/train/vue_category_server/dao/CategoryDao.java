package com.bosssoft.hr.train.vue_category_server.dao;

import com.bosssoft.hr.train.vue_category_server.entity.Category;
import com.bosssoft.hr.train.vue_category_server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryDao {
    /**
     * 用户登录请求
     * @param name
     * @param password
     * @return
     */
    User getUser(String name, String password);
    /*
    查所有
    return List<Person>
     */
    List<Category> getAll();

    /**
     * 根据名称、类型等模糊查询
     * @param name
     * @param category
     * @param status
     * @return
     */
    List<Category> getAllLike(String name,String category,Integer status);
    /*
    根据ID查询
    {id} 要查询人员的 id
     */
    Category getCategoryByID(long id);


    /*
    删除
    {id} 要删除人员的 id
     */
    void delete(long id);

    /*
    更新
    {p} 要更新的Person实例
     */
    void update(Category p);

    /*
    增加
    {p} 要新增的Person实例
     */
    void newp(Category p);
}
