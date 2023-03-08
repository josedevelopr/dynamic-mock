package com.jdevelopr.dynamicmock.business.repository;

import com.jdevelopr.dynamicmock.business.model.Mock;
import com.jdevelopr.dynamicmock.business.model.MockConfiguration;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MockRepository {
  private List<MockConfiguration> configuredMocks;

  public void addMockConfiguration(Mock newMock) {
    MockConfiguration newConfiguration = new MockConfiguration(newMock);
    configuredMocks.add(newConfiguration);
  }

  public List<MockConfiguration> listAllMockConfigurations() {
    return configuredMocks;
  }
}
