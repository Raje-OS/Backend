package raje.com.rajebackend.library.domain.model.commands;

public record AddLibraryLocationCommand(
        String libraryId,
        double lat,
        double lng,
        String direccion
) {}
