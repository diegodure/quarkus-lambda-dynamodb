package org.acme;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Map;

@Named("greeting") // Este nombre debe coincidir con quarkus.lambda.handler
public class GreetingLambda implements RequestHandler<Map<String, String>, String> {

    @Inject
    DynamoDbClient dynamoDb; // Quarkus lo configura solo

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        String name = input.getOrDefault("name", "desconocido");
        
        // Ejemplo simple de guardado en Dynamo
        dynamoDb.putItem(PutItemRequest.builder()
                .tableName("Usuarios")
                .item(Map.of("id", AttributeValue.builder().s(context.getAwsRequestId()).build(),
                             "nombre", AttributeValue.builder().s(name).build()))
                .build());

        return "Hola " + name + ", guardado en DynamoDB!";
    }
}