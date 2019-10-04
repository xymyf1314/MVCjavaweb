package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String userPassword;
    private Integer userGrade;
    private String userPhone;
    private String userAddress;
    private Timestamp userRegisterDate;
    private Integer disable;

    public User(String userName, String userPassword, String userPhone, String userAddress) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public User(Integer id, String userName, String userPassword, String userPhone, String userAddress) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }
}
