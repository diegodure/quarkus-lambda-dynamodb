package org.acme.Service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import org.acme.Entity.UserEntity;

@ApplicationScoped
public class UserService {

    @Inject
    DynamoDbEnhancedClient enhancedClient; // Quarkus inyecta la versión "mejorada"

    private DynamoDbTable<UserEntity> userTable;

    @PostConstruct // Se ejecuta una sola vez al arrancar la Lambda
    void setup() {
        userTable = enhancedClient.table("Usuarios", TableSchema.fromBean(UserEntity.class));
    }

    public void saveUser(String id, String name) {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setNombre(name);
        user.setFechaRegistro(java.time.Instant.now().toString());

        userTable.putItem(user);
    }
}