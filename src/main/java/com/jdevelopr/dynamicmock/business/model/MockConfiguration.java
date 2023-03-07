package com.jdevelopr.dynamicmock.business.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class MockConfiguration extends Mock {
  private final Date creationDate;

  public MockConfiguration(Mock mock) {
    super(mock.getPath(), mock.getResponse());
    this.creationDate = new Date();
  }

  @Override public String toString() {
    return "{\"path\":" + this.getPath() + ",\"response\":" + this.getResponse()
      + ",\"creationDate\":" + creationDate + "}";
  }
}
