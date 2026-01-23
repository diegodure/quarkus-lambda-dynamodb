package org.acme.Service;

import org.acme.Entity.UserEntity;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    DynamoDbEnhancedClient dynamoDb; // Mantener el nombre que inyectaste

    // El esquema para Records se define con StaticTableSchema
    private static final TableSchema<UserEntity> USER_SCHEMA = 
        TableSchema.builder(UserEntity.class)
            .newItemSupplier(() -> new UserEntity(null, null, null)) 
            .addAttribute(String.class, a -> a.name("id")
                .getter(UserEntity::id)
                .setter((obj, val) -> {}) 
                .tags(StaticAttributeTags.primaryPartitionKey()))
            .addAttribute(String.class, a -> a.name("nombre")
                .getter(UserEntity::nombre)
                .setter((obj, val) -> {}))
            .addAttribute(String.class, a -> a.name("fechaRegistro")
                .getter(UserEntity::fechaRegistro)
                .setter((obj, val) -> {}))
            .build();

    public void saveUser(String id, String name) {
        DynamoDbTable<UserEntity> table = dynamoDb.table("Usuarios", USER_SCHEMA);
        
        UserEntity user = new UserEntity(id, name, java.time.Instant.now().toString());
        table.putItem(user);
    }
}