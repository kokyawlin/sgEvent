package com.nus.sgevent.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.Data;


@Entity
@Table(name = "user_role") // 明确指定表名
@Data // 使用Lombok自动生成getter、setter方法
public class UserRole {

    @Id
    private int roleId; // 使用int作为主键类型

    private String roleName;
    private String permission;

    
    // 由于使用了Lombok的@Data注解，手动定义的getter和setter方法不再需要
}
