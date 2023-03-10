package com.jdevelopr.dynamicmock.business.config.exception;

import com.jdevelopr.dynamicmock.business.model.DynamicMock;

public class DuplicatedMockException extends RuntimeException {
  private DynamicMock duplicatedMock;

  public DuplicatedMockException(DynamicMock duplicatedMock) {
    super("The mock " + duplicatedMock.getPath() + "is already registered");
    this.duplicatedMock = duplicatedMock;
  }
}
