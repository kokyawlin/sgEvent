package com.nus.sgevent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private Integer activeStatus;
    @Temporal(TemporalType.DATE)
    private Date createDt;

    // // Default constructor
    // public User() {}

    // // Constructor with fields
    // public User(String userName, String password, String email, Integer activeStatus, Date createDt) {
    //     this.userName = userName;
    //     this.password = password;
    //     this.email = email;
    //     this.activeStatus = activeStatus;
    //     this.createDt = createDt;
    // }

    // // Getters
    // public Long getUserId() { return userId; }
    // public String getUserName() { return userName; }
    // public String getPassword() { return password; }
    // public String getEmail() { return email; }
    // public Integer getActiveStatus() { return activeStatus; }
    // public Date getCreateDt() { return createDt; }

    // // Setters
    // public void setUserId(Long userId) { this.userId = userId; }
    // public void setUserName(String userName) { this.userName = userName; }
    // public void setPassword(String password) { this.password = password; }
    // public void setEmail(String email) { this.email = email; }
    // public void setActiveStatus(Integer activeStatus) { this.activeStatus = activeStatus; }
    // public void setCreateDt(Date createDt) { this.createDt = createDt; }

}
