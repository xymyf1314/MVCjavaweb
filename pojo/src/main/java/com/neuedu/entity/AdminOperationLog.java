package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOperationLog {
    private Integer id;
    private String aName;
    private String operation;
    private Integer uid;
    private String userName;
    private String userPassword;
    private String userPhone;
    private String userAddress;
    private Timestamp userRegisterDate;
    private Timestamp operationTime;

    public AdminOperationLog(Integer id, String aName, String operation, Integer uid, String userName, String userPassword, String userPhone, String userAddress, Timestamp userRegisterDate) {
        this.id = id;
        this.aName = aName;
        this.operation = operation;
        this.uid = uid;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userRegisterDate = userRegisterDate;
    }
}
