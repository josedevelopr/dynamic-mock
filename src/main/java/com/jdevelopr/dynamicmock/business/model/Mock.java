package com.jdevelopr.dynamicmock.business.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@JsonSerialize
public class Mock implements Serializable {
  private String path;
  private String response;
}
