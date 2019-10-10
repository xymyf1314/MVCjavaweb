package com.neuedu.service.serviceimpl;

import com.neuedu.entity.Category;
import com.neuedu.mapper.CategoryMapper;
import com.neuedu.mapper.ProductMapper;
import com.neuedu.service.ICategoryService;

import java.util.List;

/**
 * @program: servletTest03
 * @description:
 * @author: LinLuo
 * @create: 2019-09-16 16:05
 **/
public class CategoryServiceImpl implements ICategoryService {
    private CategoryMapper categoryMapper;
    private ProductMapper productMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public CategoryServiceImpl(CategoryMapper categoryMapper, ProductMapper productMapper) {
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<Category> selectCategoryChildrenByPid(int id) {
        return categoryMapper.selectCategoryChildrenByPid(id);
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
    public void del(int id) {
        Category category = categoryMapper.findById(id).get(0);
        if (category.getLeaf() == 1) {
            productMapper.delFromCategoryId(id);
            List<Category> categories = categoryMapper.selectCategoryChildrenByPid(category.getCategoryParentId());
            if (categories.size() == 1) {
                Category category1 = categoryMapper.findById(category.getCategoryParentId()).get(0);
                category1.setLeaf(1);
                update(category1);
                del(category1.getId());
            }
            categoryMapper.del(id);
        } else {
            List<Category> kidCategories = categoryMapper.selectCategoryChildrenByPid(category.getId());
            for (Category kidCategory : kidCategories) {
                del(kidCategory.getId());
            }
        }
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