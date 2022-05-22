package com.example.demo.mail;

public interface MailFormatter<T> {
        String getText(T params);
        String getSubject();
}
