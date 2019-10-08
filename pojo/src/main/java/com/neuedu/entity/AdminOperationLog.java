package com.neuedu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author fan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOperationLog implements Serializable {
    private Integer id;
    private String aName;
    private String operation;
    private Integer uid;
    private String userName;
    private String userPassword;
    private Integer userGrade;
    private String userPhone;
    private String userAddress;
    private Timestamp userRegisterDate;
    private Integer userDisable;
    private Timestamp operationTime;

    public AdminOperationLog(Integer id, String aName, String operation, Integer uid, String userName, String userPassword, Integer userGrade, String userPhone, String userAddress, Timestamp userRegisterDate, Integer userDisable) {
        this.id = id;
        this.aName = aName;
        this.operation = operation;
        this.uid = uid;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userGrade = userGrade;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userRegisterDate = userRegisterDate;
        this.userDisable = userDisable;
    }
}
