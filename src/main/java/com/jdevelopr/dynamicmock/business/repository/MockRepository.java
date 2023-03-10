package com.jdevelopr.dynamicmock.business.repository;

import com.jdevelopr.dynamicmock.business.model.DynamicMock;
import com.jdevelopr.dynamicmock.business.model.MockConfiguration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockRepository {
  private List<MockConfiguration> configuredMocks = new ArrayList<>();

  public void createMockConfiguration(DynamicMock newMock) {
    MockConfiguration newConfiguration = new MockConfiguration(newMock);
    configuredMocks.add(newConfiguration);
  }

  public List<MockConfiguration> listAllMockConfigurations() {
    return configuredMocks;
  }
}
