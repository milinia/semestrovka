package ru.itis.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private String from;
    private String text;
    private String linkToImage;
}

