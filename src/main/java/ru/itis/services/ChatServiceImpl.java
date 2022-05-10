package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Chat;
import ru.itis.models.User;
import ru.itis.repositories.ChatRepository;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public void addParticipant(Long chatId, User user) {
        Chat chat = chatRepository.getById(chatId);
        List<User> participants = chat.getParticipants();
        participants.add(user);
        chat.setParticipants(participants);
        chatRepository.save(chat);
    }

    @Override
    public boolean isParticipant(Long chatId, User user) {
        Chat chat = chatRepository.getById(chatId);
        List<User> participants = chat.getParticipants();
        return participants.contains(user);
    }

    @Override
    public Chat getChatById(Long id) {
        return chatRepository.getById(id);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }
}
