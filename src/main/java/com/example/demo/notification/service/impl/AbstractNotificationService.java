package com.example.demo.notification.service.impl;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class AbstractNotificationService {

    public void sendNotification(long companyId) {
        String contact = this.getContact(companyId);
        String title = this.getTitle();
        String message = this.getMessage();

        this.send(contact, title, message);
    }

    protected abstract String getContact(long companyId);

    protected abstract String getTitle();

    protected String getMessage() {
        return "Welcome to our company";
    }

    private void send(String contact, String title, String message) {
        System.out.printf("Notification sent to %s \n%s \nMessage: %s%n", contact, title, message);
    }
}