package raje.com.rajebackend.platform.domain.services;

import raje.com.rajebackend.platform.domain.model.aggregates.Platform;
import raje.com.rajebackend.platform.domain.model.commands.SignInPlatformCommand;
import raje.com.rajebackend.platform.domain.model.commands.SignUpPlatformCommand;

public interface PlatformCommandService {
    Platform handle(SignInPlatformCommand command);
    Platform handle(SignUpPlatformCommand command);


}
