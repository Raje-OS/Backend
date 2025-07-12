package raje.com.rajebackend.user.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateUserDetailsResource(

        @NotBlank(message = "El id es obligatorio")
        String id,


        String userId,


        List<String> favorites,


        List<String> viewed

) {}
