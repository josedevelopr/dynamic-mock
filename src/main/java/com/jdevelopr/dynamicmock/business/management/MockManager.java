package com.jdevelopr.dynamicmock.business.management;

import com.jdevelopr.dynamicmock.business.model.Mock;
import com.jdevelopr.dynamicmock.business.model.MockConfiguration;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponse;

import java.util.ArrayList;
import java.util.List;

public class MockManager {
  private List<MockConfiguration> configuredMocks = new ArrayList<>();

  public ApplicationResponse registerMockConfiguration(Mock newMock) {
    try {
      addMockConfiguration(newMock);
    } catch (Exception e) {
      return new ApplicationResponse("DM0001", "Something went wrong");
    }
    return new ApplicationResponse("DM0000", "Created successfully.");
  }

  private void addMockConfiguration(Mock newMock) {
    MockConfiguration newConfiguration = new MockConfiguration(newMock);
    configuredMocks.add(newConfiguration);
  }

  public List<MockConfiguration> listAllMockConfigurations() {
    return configuredMocks;
  }
}
