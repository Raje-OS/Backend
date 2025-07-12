package raje.com.rajebackend.iam.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.iam.domain.model.queries.GetAllUsersQuery;
import raje.com.rajebackend.iam.domain.model.queries.GetUserByIdQuery;
import raje.com.rajebackend.iam.domain.services.UserCommandService;
import raje.com.rajebackend.iam.domain.services.UserQueryService;
import raje.com.rajebackend.iam.interfaces.rest.resource.UpdateUserResource;
import raje.com.rajebackend.iam.interfaces.rest.resource.UserResource;
import raje.com.rajebackend.iam.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import raje.com.rajebackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.handle(new GetAllUsersQuery());
        var resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable String id) {
        var user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(value ->
                ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(value))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable String id, @RequestBody UpdateUserResource resource) {
        var command = UpdateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var updatedUser = userCommandService.handle(id, command);
        var updatedResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser);
        return ResponseEntity.ok(updatedResource);
    }



}
