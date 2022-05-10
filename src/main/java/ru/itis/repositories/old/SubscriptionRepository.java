package ru.itis.repositories.old;

import ru.itis.models.old.Subscription;

import java.util.List;

public interface SubscriptionRepository {

    public void createNewSub(Subscription subscription);
    public List<Subscription> getSubscriptionsByUserId(Long userId);
    public void deleteSub(Long subId, Long userId);


}
