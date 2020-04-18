package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author Eryn-mk
 * @Date 2020/04/17
 * @Version 1.0.0
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Search sub category list by parentId
     * @param pid
     * @return
     */
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }
}
