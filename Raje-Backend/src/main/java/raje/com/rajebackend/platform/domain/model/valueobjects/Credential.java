package raje.com.rajebackend.platform.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Credential(String email, String password) {}
