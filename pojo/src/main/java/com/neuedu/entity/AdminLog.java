package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLog implements Serializable {
    private Integer id;
    private String aName;
    private String operation;
    private Integer jurisdiction;
    private String operationTime;

    public AdminLog(Integer id, String aName, String operation, Integer jurisdiction) {
        this.id = id;
        this.aName = aName;
        this.operation = operation;
        this.jurisdiction = jurisdiction;
    }
}