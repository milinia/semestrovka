package ru.itis.services.old;

import models.Subscription;
import ru.itis.models.Subscription;

public interface EmailService {

    void sendMessage(Subscription subscription, String email);
}
