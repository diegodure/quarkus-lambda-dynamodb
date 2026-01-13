package org.acme.Service;

import org.acme.Entity.UserEntity;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import java.util.Map;

@ApplicationScoped // Hace que Quarkus maneje esta clase como un Singleton
public class UserService {

    @Inject
    DynamoDbClient dynamoDb;

    public void saveUser(String id, String name) {
        String timestamp = Instant.now().toString();

        Map<String, AttributeValue> item = Map.of(
            "id", AttributeValue.builder().s(id).build(),
            "nombre", AttributeValue.builder().s(name).build(),
            "fechaRegistro", AttributeValue.builder().s(timestamp).build()
        );

        dynamoDb.putItem(PutItemRequest.builder()
                .tableName("Usuarios")
                .item(item)
                .build());
    }
}