package org.launchcode.PlatePlanner.event;

import org.launchcode.PlatePlanner.model.User;
import org.launchcode.PlatePlanner.service.EmailService;
import org.launchcode.PlatePlanner.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private VerificationTokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = String.valueOf(user.getId());
        user.setVerificationToken(token);
        tokenService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Welcome Email";
        String confirmationUrl = "http://localhost:5173/";
        String message = "Welcome to PlatePlanner! We are thrilled to have you on board and can't wait for you to explore delicious recipes. " + confirmationUrl;

        emailService.sendEmail(recipientAddress, subject, message);
    }
}