package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private Integer id;
    private String categoryName;
    private String categoryDescription;
    private Integer categoryParentId;
    private Integer leaf;
    private Integer grade;
    // 创建存放递归后结果的容器
    private List<Category> children;

    public Category(Integer id, String categoryName, String categoryDescription, Integer categoryParentId, Integer leaf, Integer grade) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryParentId = categoryParentId;
        this.leaf = leaf;
        this.grade = grade;
    }
}
