package com.example.demo.notification.listener;

import com.example.demo.event.CompanyCreatedEvent;
import com.example.demo.notification.factory.NotificationServiceFactory;
import com.example.demo.notification.service.impl.AbstractNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CompanyCreatedEventListener {

    private final NotificationServiceFactory notificationServiceFactory;

    @Async
    @TransactionalEventListener
    public void handle(CompanyCreatedEvent event) {
        AbstractNotificationService notificationService = notificationServiceFactory.create(event.notificationType());
        notificationService.sendNotification(event.companyId());
    }
}