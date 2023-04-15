package com.example.demo.notification.service.impl;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationServiceImpl extends AbstractNotificationService {

    @Override
    protected String getContact(long companyId) {
        return "+49 0484830284";
    }

    @Override
    protected String getTitle() {
        return "SMS Title";
    }

    @Override
    protected String getMessage() {
        return "Company created (SMS Notification)";
    }
}