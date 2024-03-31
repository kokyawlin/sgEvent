package com.nus.sgevent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonResponse {

  private boolean success;

  private String message;

  // 由于使用了Lombok的@Data注解，自动生成所有的getter和setter方法
  // 同时使用了@AllArgsConstructor注解自动生成包含所有参数的构造函数，所以不需要手动定义构造函数
}
