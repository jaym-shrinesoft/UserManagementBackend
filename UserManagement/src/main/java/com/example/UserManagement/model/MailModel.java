package com.example.UserManagement.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MailModel {
    private String to;
    private String subject;
    private String body;
}