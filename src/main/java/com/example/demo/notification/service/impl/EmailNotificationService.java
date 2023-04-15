package com.example.demo.notification.service.impl;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService extends AbstractNotificationService {

    @Override
    protected String getContact(long companyId) {
        return "company@mail.com";
    }

    @Override
    protected String getTitle() {
        return "Email Title";
    }
}
