package com.jdevelopr.dynamicmock.business.management;

import com.jdevelopr.dynamicmock.business.model.Mock;
import com.jdevelopr.dynamicmock.business.model.MockConfiguration;
import com.jdevelopr.dynamicmock.business.repository.MockRepository;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.jdevelopr.dynamicmock.web.response.ApplicationResponseTemplate.*;

@Service
public class MockManager {

  private MockRepository mockRepository = new MockRepository(new ArrayList<>());
  @Value("${application.endpoint.mock}")
  private String dynamicMockPath;

  public ApplicationResponse registerMockConfiguration(Mock newMock) {
    try {
      mockRepository.addMockConfiguration(newMock);
    } catch (Exception e) {
      return MOCK_NOT_CREATED.getResponse();
    }
    return MOCK_CREATED_SUCCESSFULLY.getResponse();
  }

  public String getMockResponseByUrl(String requestURI) {
    String path = getRequestedPathFromURI(requestURI);
    return getMockResponseByPath(path);
  }

  private String getRequestedPathFromURI(String requestURI) {
    int startIndex = dynamicMockPath.length();
    int endIndex = requestURI.length();
    return requestURI.substring(startIndex, endIndex);
  }

  private String getMockResponseByPath(String path) {
    for (MockConfiguration configuredMock:  mockRepository.listAllMockConfigurations()) {
      boolean configuredPathMatchesWithPathRequest = configuredMock.getPath().equals(path);
      if (configuredPathMatchesWithPathRequest) {
        return configuredMock.getResponse();
      }
    }
    return MOCK_NOT_FOUND.getResponse().toString();
  }
}
