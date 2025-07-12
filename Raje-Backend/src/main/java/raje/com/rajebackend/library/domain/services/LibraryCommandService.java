package raje.com.rajebackend.library.domain.services;

import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.commands.SignInLibraryCommand;
import raje.com.rajebackend.library.domain.model.commands.SignUpLibraryCommand;

public interface LibraryCommandService {
    Library handle(SignUpLibraryCommand command);
    Library handle(SignInLibraryCommand command);

    Object generateTokenForLibrary(Library library);
}
