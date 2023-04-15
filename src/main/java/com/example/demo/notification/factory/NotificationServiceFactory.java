package com.example.demo.notification.factory;

import com.example.demo.notification.enums.NotificationType;
import com.example.demo.notification.service.impl.AbstractNotificationService;
import com.example.demo.notification.service.impl.EmailNotificationService;
import com.example.demo.notification.service.impl.SmsNotificationServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public AbstractNotificationService create(NotificationType notificationType) {
        switch (notificationType) {
            case SMS -> {
                return applicationContext.getBean(SmsNotificationServiceImpl.class);
            }
            case EMAIL -> {
                return applicationContext.getBean(EmailNotificationService.class);
            }
            default -> throw new IllegalArgumentException("Invalid notification type");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
