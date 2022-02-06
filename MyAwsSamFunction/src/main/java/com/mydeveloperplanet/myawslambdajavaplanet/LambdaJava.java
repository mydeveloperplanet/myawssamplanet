package com.mydeveloperplanet.myawslambdajavaplanet;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LambdaJava implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        LambdaLogger logger = context.getLogger();

        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + GSON.toJson(System.getenv()) + System.lineSeparator());
        logger.log("CONTEXT: " + GSON.toJson(context) + System.lineSeparator());
        // process event
        logger.log("EVENT: " + input.getBody() + System.lineSeparator());
        logger.log("EVENT TYPE: " + input.getClass() + System.lineSeparator());

        if (input.getBody() != null) {
            // Parse JSON into an object
            Car car = GSON.fromJson(input.getBody(), Car.class);
            logger.log("Car brand: " + car.getBrand() + System.lineSeparator());
            logger.log("Car type: " + car.getType() + System.lineSeparator());
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/text");
        headers.put("X-Custom-Header", "application/text");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        return response
                .withStatusCode(200)
                .withBody("Version 1");

    }
}
