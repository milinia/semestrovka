package ru.itis.services.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.old.Subscription;
import ru.itis.repositories.old.SubscriptionRepository;
import ru.itis.services.old.SubscriptionService;

import java.util.List;

@Service("subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void createNew(Subscription subscription) { subscriptionRepository.createNewSub(subscription); }

    public void deleteById(Long subId, Long userId) { subscriptionRepository.deleteSub(subId, userId); }

    public List<Subscription> getSubscriptionsByUserId(Long id) {
        return subscriptionRepository.getSubscriptionsByUserId(id);
    }
}
