package org.acme.Entity;


import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection 
public record UserEntity(String id, String nombre, String fechaRegistro) {
}