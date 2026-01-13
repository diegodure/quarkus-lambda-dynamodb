package org.acme;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.acme.Service.UserService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Map;

@Named("greeting")
public class GreetingLambda implements RequestHandler<Map<String, String>, String> {

    @Inject
    UserService userService; 
    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        String name = input.getOrDefault("name", "desconocido");
        String requestId = context.getAwsRequestId();

        userService.saveUser(requestId, name);

        return String.format("Usuario %s procesado con éxito. ID: %s", name, requestId);
    }
}