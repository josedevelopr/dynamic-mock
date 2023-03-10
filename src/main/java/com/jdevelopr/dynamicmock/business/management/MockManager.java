package com.jdevelopr.dynamicmock.business.management;

import com.jdevelopr.dynamicmock.business.config.exception.DuplicatedMockException;
import com.jdevelopr.dynamicmock.business.model.DynamicMock;
import com.jdevelopr.dynamicmock.business.model.MockConfiguration;
import com.jdevelopr.dynamicmock.business.repository.MockRepository;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.jdevelopr.dynamicmock.web.model.ApplicationResponseTemplate.*;

@RequiredArgsConstructor
@Service
public class MockManager {

  @Autowired
  private final MockRepository mockRepository;
  @Value("${application.endpoint.mock}")
  private String dynamicMockPath;

  public ApplicationResponse registerMockConfiguration(DynamicMock newMock) {
    try {
      registerMock(newMock);
    } catch (Exception e) {
      return MOCK_NOT_CREATED.getResponse();
    }
    return MOCK_CREATED_SUCCESSFULLY.getResponse();
  }

  private void registerMock(DynamicMock newMock) {
    validateMockToRegister(newMock);
    mockRepository.createMockConfiguration(newMock);
  }

  private void validateMockToRegister(DynamicMock newMock) {
    checkIfAnyFieldIsNull(newMock);
    checkIfMockIsAlreadyRegistered(newMock);
  }

  private void checkIfMockIsAlreadyRegistered(DynamicMock newMock) {
    boolean mockIsRegistered = mockRepository.checkIfPathExists(newMock.getPath());
    if (mockIsRegistered) {
      throw new DuplicatedMockException(newMock);
    }
  }

  private void checkIfAnyFieldIsNull(DynamicMock newMock) {
    if (newMock.getPath() == null || newMock.getResponse() == null) {
      throw new IllegalArgumentException("Dynamic mock has null values and cannot be registered.");
    }
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
