package com.jdevelopr.dynamicmock.web.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@JsonSerialize
@ToString
@Getter
public class ApplicationResponse implements Serializable {
  private String code;
  private String message;
}
