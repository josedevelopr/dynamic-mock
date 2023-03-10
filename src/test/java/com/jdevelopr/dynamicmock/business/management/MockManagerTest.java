package com.jdevelopr.dynamicmock.business.management;

import com.jdevelopr.dynamicmock.business.model.DynamicMock;
import com.jdevelopr.dynamicmock.business.model.MockConfiguration;
import com.jdevelopr.dynamicmock.business.repository.MockRepository;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponse;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponseTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class MockManagerTest {

  @Mock
  private MockRepository mockRepository;

  @InjectMocks
  private MockManager mockManager;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockManager = new MockManager(mockRepository);
  }

  @Test
  void registerMockConfigurationWhenMockIsCorrectlyConfigured() {
    DynamicMock newMock = new DynamicMock("/path",  "{\"response\":\"ok\"}");
    ApplicationResponse actualResult = mockManager.registerMockConfiguration(newMock);

    ApplicationResponse expectedResult =
      ApplicationResponseTemplate.MOCK_CREATED_SUCCESSFULLY.getResponse();

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void registerMockConfigurationWhenRepositoryThrowsException() {
    DynamicMock newMock = new DynamicMock(null, null);
    doThrow(new IllegalArgumentException()).when(mockRepository).createMockConfiguration(newMock);

    ApplicationResponse actualResult = mockManager.registerMockConfiguration(newMock);
    ApplicationResponse expectedResult =
      ApplicationResponseTemplate.MOCK_NOT_CREATED.getResponse();

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getMockResponseByUrlWhenMockIsRegistered() {
    DynamicMock registeredMock1 = new DynamicMock("/path",  "{\"response\":\"ok\"}");
    DynamicMock registeredMock2 =
      new DynamicMock("/claro",  "{\"response\":\"okClaro\"}");
    List<MockConfiguration> registeredMocks =
      List.of(new MockConfiguration(registeredMock1), new MockConfiguration(registeredMock2));
    when(mockRepository.listAllMockConfigurations()).thenReturn(registeredMocks);
    ReflectionTestUtils.setField(mockManager, "dynamicMockPath", "/dynamic-mock/manager/mock");

    String mockPath = "/dynamic-mock/manager/mock/path";
    String actualResult = mockManager.getMockResponseByUrl(mockPath);
    String expectedResult = "{\"response\":\"ok\"}";

    assertEquals(expectedResult, actualResult);
  }
}
