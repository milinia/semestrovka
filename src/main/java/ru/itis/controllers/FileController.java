package ru.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.services.UserService;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;

@MultipartConfig(location = "/Users/milinia/Downloads/storage/", maxFileSize = 1024*1024*5)
@Controller
public class FileController {

    @Autowired
    private UserService userService;

    @Value("${custom.file.storage}")
    private String filePath;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/auth_to_chat/{userId}")
    public String uploadingPhotoAndAddingNickname(@RequestParam MultipartFile photo, @RequestParam String nickname,
                                      @PathVariable Long userId) {

        userService.addUserNicknameById(userId, nickname);
        String fileName = userId.toString();
        try {
            photo.transferTo(new File(fileName + ".jpg"));
        } catch (IOException ex) {
            logger.error("Error while loading file");
            //добавить страницу ошибки
        }
        logger.info("File successfully loaded");
        return "redirect:/chats";
    }
}
