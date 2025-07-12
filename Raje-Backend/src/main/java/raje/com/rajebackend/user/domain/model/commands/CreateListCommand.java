package raje.com.rajebackend.user.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateListCommand(

        String id,


        String userId,


        String name,

        String description,


        List<String> list_content
) {}
