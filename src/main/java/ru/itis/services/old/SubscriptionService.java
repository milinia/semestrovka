package ru.itis.services.old;

import ru.itis.models.old.Subscription;

import java.util.List;

public interface SubscriptionService {

    void createNew(Subscription subscription);

    void deleteById(Long subId, Long userId);

    List<Subscription> getSubscriptionsByUserId(Long id);
}
