package com.example.demo.notification.listener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.event.CompanyCreatedEvent;
import com.example.demo.notification.enums.NotificationType;
import com.example.demo.notification.factory.NotificationServiceFactory;
import com.example.demo.notification.service.impl.EmailNotificationService;
import com.example.demo.notification.service.impl.SmsNotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

@ExtendWith(MockitoExtension.class)
class CompanyCreatedEventListenerTest {

    @Mock
    private NotificationServiceFactory notificationServiceFactory;

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    void shouldDelegateToSmsNotificationService() {
        when(notificationServiceFactory.create(NotificationType.SMS)).thenReturn(new SmsNotificationServiceImpl());

        contextRunner
            .withBean(
                CompanyCreatedEventListener.class,
                notificationServiceFactory
            )
            .run(context -> {
                context.publishEvent(new CompanyCreatedEvent(1L, NotificationType.SMS));

                verify(notificationServiceFactory).create(NotificationType.SMS);
            });
    }

    @Test
    void shouldDelegateToEmailNotificationService() {
        when(notificationServiceFactory.create(NotificationType.EMAIL)).thenReturn(new EmailNotificationService());

        contextRunner
            .withBean(
                CompanyCreatedEventListener.class,
                notificationServiceFactory
            )
            .run(context -> {
                context.publishEvent(new CompanyCreatedEvent(1L, NotificationType.EMAIL));

                verify(notificationServiceFactory).create(NotificationType.EMAIL);
            });
    }
}