package com.jdevelopr.dynamicmock.web.expose;

import com.jdevelopr.dynamicmock.business.management.MockManager;
import com.jdevelopr.dynamicmock.business.model.Mock;
import com.jdevelopr.dynamicmock.web.model.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamic-mock/manager")
@Log4j2
@RequiredArgsConstructor
public class MockManagementController {

  private final MockManager mockManager = new MockManager();

  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public ApplicationResponse registerNewMockConfiguration(@RequestBody Mock mock) {
    log.info("1. Configurations before the new register:");
    log.info(mockManager.listAllMockConfigurations().toString());
    ApplicationResponse response = mockManager.registerMockConfiguration(mock);
    log.info("2. Configurations after the new register:");
    log.info(mockManager.listAllMockConfigurations().toString());
    return response;
  }
}
