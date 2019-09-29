package com.neuedu.service.serviceimpl;

import com.neuedu.entity.Category;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.service.ICategoryService;

import java.util.List;

/**
 * @program: servletTest03
 * @description:
 * @author: LinLuo
 * @create: 2019-09-16 16:05
 **/
public class CategoryImpl implements ICategoryService {
    CategoryMapper categoryMapper;

    public CategoryImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> findById(int id) {
        return categoryMapper.findById(id);
    }

    @Override
    public boolean update(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public boolean insertRoot(String categoryName, String categoryDescription) {
        Category category = new Category(null, categoryName, categoryDescription, 0, 1, 1);
        return categoryMapper.insertRoot(category);
    }

    @Override
    public boolean insertChild(String categoryName, String categoryDescription, int categoryParentId) {
        List<Category> byId = categoryMapper.findById(categoryParentId);
        Category category = byId.get(0);
        if (category.getLeaf() == 1) {
            category.setLeaf(0);
        }
        categoryMapper.update(category);
        return categoryMapper.insertChild(new Category(null, categoryName, categoryDescription, categoryParentId, 1, category.getGrade() + 1));
    }

    @Override
    public List<Category> findToTree() {
        return categoryMapper.findToTree();
    }


}