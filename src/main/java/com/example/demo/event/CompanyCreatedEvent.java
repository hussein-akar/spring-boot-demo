package com.example.demo.event;

import com.example.demo.notification.enums.NotificationType;

public record CompanyCreatedEvent(long companyId, NotificationType notificationType) {

}