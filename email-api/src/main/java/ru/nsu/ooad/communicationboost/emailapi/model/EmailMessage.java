package ru.nsu.ooad.communicationboost.emailapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record EmailMessage(
    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
           message = "Mail format sender is incorrect")
    String sender,

    @NotBlank
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
           message = "Mail format recipient is incorrect")
    String recipient,

    @NotEmpty
    String text) implements Serializable {
}
