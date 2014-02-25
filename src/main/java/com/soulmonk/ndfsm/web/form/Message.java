package com.soulmonk.ndfsm.web.form;

public class Message {

  public static final String DANGER_TYPE = "danger";
  public static final String SUCCESS_TYPE = "success";

  private String type;

  private String message;

  private static Message last;

  public Message() {
  }

  public Message(String type, String message) {
    this.type = type;
    this.message = message;
  }

  public static Message getLast() {
    return last;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
