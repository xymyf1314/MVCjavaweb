package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    private Integer id;
    private String aName;
    private String aPwd;
    private Integer jurisdiction;

    public Admin(Integer id, String aName, String aPwd) {
        this.id = id;
        this.aName = aName;
        this.aPwd = aPwd;
    }
}