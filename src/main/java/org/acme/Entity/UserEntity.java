package org.acme.Entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@RegisterForReflection
@DynamoDbBean // Indica que esta clase se puede mapear a una tabla
public class UserEntity {
    private String id;
    private String nombre;
    private String fechaRegistro;

    // Getters y Setters son obligatorios para el Enhanced Client
    @DynamoDbPartitionKey // Define la Clave Primaria
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}