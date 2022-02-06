package com.mydeveloperplanet.myawslambdajavaplanet;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AppTest {
  @Test
  public void successfulResponse() {
    LambdaJava app = new LambdaJava();
    APIGatewayProxyRequestEvent input = new APIGatewayProxyRequestEvent();
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    input.setHeaders(headers);
    input.setBody("{\"brand\": \"FORD\",\"type\": \"Kuga\"}");

    APIGatewayProxyResponseEvent result = app.handleRequest(input, new Context() {
      @Override
      public String getAwsRequestId() {
        return null;
      }

      @Override
      public String getLogGroupName() {
        return null;
      }

      @Override
      public String getLogStreamName() {
        return null;
      }

      @Override
      public String getFunctionName() {
        return null;
      }

      @Override
      public String getFunctionVersion() {
        return null;
      }

      @Override
      public String getInvokedFunctionArn() {
        return null;
      }

      @Override
      public CognitoIdentity getIdentity() {
        return null;
      }

      @Override
      public ClientContext getClientContext() {
        return null;
      }

      @Override
      public int getRemainingTimeInMillis() {
        return 0;
      }

      @Override
      public int getMemoryLimitInMB() {
        return 0;
      }

      @Override
      public LambdaLogger getLogger() {
        return new LambdaLogger() {
          @Override
          public void log(String s) {

          }

          @Override
          public void log(byte[] bytes) {

          }
        };
      }
    });
    assertEquals(200, result.getStatusCode().intValue());
    assertEquals("application/text", result.getHeaders().get("Content-Type"));
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("Version 1"));
  }
}
