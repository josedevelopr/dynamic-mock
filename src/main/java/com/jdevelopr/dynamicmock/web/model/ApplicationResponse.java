package com.jdevelopr.dynamicmock.web.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@JsonSerialize
@Getter
public class ApplicationResponse implements Serializable {
  private String code;
  private String message;

  @Override public String toString() {
    return "{\"code\":\"" + code + "\", \"message\":\"" + message + "\"}";
  }
}
