package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Chat;
import ru.itis.models.User;
import ru.itis.services.ChatService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Transactional
    @GetMapping("/chats")
    public String getChatsPage(Model model, Authentication authentication) {

        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            if (user.getNickname() == null || Objects.equals(user.getNickname(), "")) {
                model.addAttribute("user", user);
                return "chatAuthPage";
            } else {
                List<Chat> chats = chatService.getAll();
                model.addAttribute("chatList", chats);
                return "chatsPage";
            }
        }else
            return "loginPage";
    }

    @GetMapping("/chat/{chatId}")
    public ModelAndView getChatPage(@PathVariable String chatId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            if (user != null) {
                Chat chat = chatService.getChatById(Long.parseLong(chatId));
                modelAndView.addObject("id", user.getNickname());
                modelAndView.addObject("chat", chat);
                modelAndView.setViewName("chatPage");
                return modelAndView;
            }else{
                modelAndView.setViewName("loginPage");
                return modelAndView;
            }
        }else{
            modelAndView.setViewName("loginPage");
            return modelAndView;
        }
    }
}
