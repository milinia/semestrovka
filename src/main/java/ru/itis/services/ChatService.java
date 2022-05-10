package ru.itis.services;

import ru.itis.models.Chat;
import ru.itis.models.User;

import java.util.List;

public interface ChatService {
    void addParticipant(Long chatId, User user);
    boolean isParticipant(Long chatId, User user);
    Chat getChatById(Long id);
    List<Chat> getAll();
}
