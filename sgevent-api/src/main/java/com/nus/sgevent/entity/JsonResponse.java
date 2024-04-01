package com.nus.sgevent.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonResponse {

  private boolean success;

  private String message;

}
