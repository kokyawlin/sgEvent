package com.nus.sgevent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "event_user")
public class EventUser {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID UserId;

  @Column(name = "user_name")
  private String UserName;

  @Column(name = "password")
  private String Password;

  @Column(name = "email_address")
  private String EmailAddress;

  @Column(name = "active_status")
  private int ActiveStatus;

  @Column(name = "role_id")
  private int RoleId;

  @Column(name = "create_time")
  private Date CreateTime;

  public UUID getUserId() {
    return UserId;
  }

  public void setUserId(UUID userId) {
    UserId = userId;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String getEmailAddress() {
    return EmailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    EmailAddress = emailAddress;
  }

  public int getActiveStatus() {
    return ActiveStatus;
  }

  public void setActiveStatus(int activeStatus) {
    ActiveStatus = activeStatus;
  }

  public Date getCreateTime() {
    return CreateTime;
  }

  public void setCreateTime(Date createTime) {
    CreateTime = createTime;
  }

  public int getRoleId() {
    return RoleId;
  }

  public void setRoleId(int roleId) {
    RoleId = roleId;
  }
}
