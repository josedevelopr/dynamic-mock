package com.jdevelopr.dynamicmock.web.expose;

import com.jdevelopr.dynamicmock.business.management.MockManager;
import com.jdevelopr.dynamicmock.business.model.DynamicMock;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamic-mock/manager")
@Log4j2
public class MockManagementController {

  @Autowired
  private MockManager mockManager;

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity registerNewMockConfiguration(@RequestBody DynamicMock mock) {
    ApplicationResponse response = mockManager.registerMockConfiguration(mock);
    return ResponseEntity.ok(response);
  }

  @RequestMapping(value = "/mock/**", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity consumeMock(HttpServletRequest request) {
    return ResponseEntity.ok(mockManager.getMockResponseByUrl(request.getRequestURI()));
  }
}
