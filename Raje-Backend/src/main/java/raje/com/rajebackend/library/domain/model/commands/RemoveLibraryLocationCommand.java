package raje.com.rajebackend.library.domain.model.commands;

public record RemoveLibraryLocationCommand(
        String libraryId,
        String direccion
) {}
