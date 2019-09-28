package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private Integer id;
    private String categoryName;
    private String categoryDescription;
    private String categoryParentId;
    private Boolean leaf;
    private Integer grade;
}
