package com.nus.sgevent.entity;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class userObj {
    
    @Id
    @GeneratedValue
    private UUID userId;
    
    @Column(nullable = false, unique = true)
    private String userName;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String emailAddress;
    
    private int activeStatus;
    
    @Column(nullable = false)
    private int roleId;
    
    private Date createTime;
    
    private String roleName;
    
    private String permission;

    // Lombok @Data annotation generates all getters and setters, so manual definitions are not needed.
}
