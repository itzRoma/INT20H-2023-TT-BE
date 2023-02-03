package org.sevenorganization.int20h2023ttbe.domain.dto;

public record UserCreationRequest(String firstName, String lastName, String email, String password) {
}
