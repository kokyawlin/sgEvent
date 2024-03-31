package com.nus.sgevent.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "event_user")
@Data // Lombok注解，自动生成getters, setters, equals, hashCode和toString方法
public class EventUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 注意UUID的生成策略
    @Column(name = "user_id")
    private UUID userId;

    @NotBlank // 确保用户名不为空
    @Column(name = "user_name")
    private String userName;

    @NotBlank // 确保密码不为空
    @Column(name = "password")
    private String password;

    @NotBlank // 确保邮箱地址不为空
    @Email // 验证邮箱地址格式
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "active_status")
    private int activeStatus;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "create_time")
    private Date createTime;
}
