package com.nus.sgevent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

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

  public userObj(EventUser user) {
    this.setUserName(user.getUserName());
    this.setActiveStatus(user.getActiveStatus());
    this.setCreateTime(user.getCreateTime());
    this.setEmailAddress(user.getEmailAddress());
    this.setPassword(user.getPassword());
    this.setRoleId(user.getRoleId());
    this.setUserId(user.getUserId());
  }
}
