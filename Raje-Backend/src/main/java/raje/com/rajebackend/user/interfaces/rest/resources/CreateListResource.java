package raje.com.rajebackend.user.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateListResource(

        String id,


        String userId,


        String name,

        String description,


        List<String> list_content
) {}
