package com.nus.sgevent.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "event_user")
@Data
@NoArgsConstructor
public class EventUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "user_id")
    private UUID userId;

    @NotBlank 
    @Column(name = "user_name")
    private String userName;

    @NotBlank 
    @Column(name = "password")
    private String password;

    @NotBlank 
    @Email 
    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "active_status")
    private int activeStatus;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "create_time")
    private Date createTime;

}
