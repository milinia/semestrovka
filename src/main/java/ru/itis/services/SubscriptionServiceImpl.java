package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Subscription;
import ru.itis.repositories.SubscriptionRepository;

import java.util.List;

@Service("subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void createNew(Subscription subscription) { subscriptionRepository.createNewSub(subscription); }

    public void deleteById(Long subId, Long userId) { subscriptionRepository.deleteSub(subId, userId); }

    public List<Subscription> getSubscriptionsByUserId(Long id) {
        return subscriptionRepository.getSubscriptionsByUserId(id);
    }
}
