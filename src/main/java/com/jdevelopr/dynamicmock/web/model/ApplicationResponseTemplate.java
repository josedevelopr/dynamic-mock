package com.jdevelopr.dynamicmock.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationResponseTemplate {
  MOCK_CREATED_SUCCESSFULLY(new ApplicationResponse("DM0000", "Created successfully.")),
  MOCK_NOT_CREATED(new ApplicationResponse("DM0001", "Something went wrong")),
  MOCK_NOT_FOUND(new ApplicationResponse("DM0003", "Mock not found"));

  private ApplicationResponse response;
}
