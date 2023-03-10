package com.jdevelopr.dynamicmock.web.expose;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockManagementControllerTest {
  private static int MOCK_PORT = 8081;
  private static String MOCK_HOST = "localhost";
  private static WireMockServer wireMockServer =
    new WireMockServer(WireMockConfiguration.wireMockConfig().port(MOCK_PORT));

  @BeforeAll
  public static void setup() {
    wireMockServer.start();
    configureFor(MOCK_HOST, MOCK_PORT);
    stubFor(
      post(urlEqualTo("/dynamic-mock/manager/create"))
        .willReturn(
          aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\"code\":\"DM0000\", \"message\":\"Created successfully.\"}")
        )
    );
  }

  @Karate.Test
  Karate testController() {
    return Karate.run("src/test/resources/mock.feature");
  }

  @AfterAll
  public static void tearDown() {
    wireMockServer.stop();
  }
}
