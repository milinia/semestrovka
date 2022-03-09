package ru.itis.repositories;

import ru.itis.models.Subscription;

import java.util.List;

public interface SubscriptionRepository {

    public void createNewSub(Subscription subscription);
    public List<Subscription> getSubscriptionsByUserId(Long userId);
    public void deleteSub(Long subId, Long userId);


}
