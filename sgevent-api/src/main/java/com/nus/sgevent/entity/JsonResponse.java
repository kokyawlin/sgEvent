package com.nus.sgevent.entity;

public class JsonResponse {

  private boolean Success;

  private String Message;

  public JsonResponse(boolean success,String message){
    this.setSuccess(success);
    this.setMessage(message);
  }

  public boolean getSuccess() {
    return Success;
  }

  public void setSuccess(boolean success) {
    Success = success;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

}
