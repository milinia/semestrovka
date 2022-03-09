package ru.itis.services;

import ru.itis.models.Subscription;

import java.util.List;

public interface SubscriptionService {

    void createNew(Subscription subscription);

    void deleteById(Long subId, Long userId);

    List<Subscription> getSubscriptionsByUserId(Long id);
}
