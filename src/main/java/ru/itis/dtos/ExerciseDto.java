package ru.itis.dtos;

import lombok.Data;

@Data
public class ExerciseDto {
    private String kind;
    private Integer duration;
    private String dayId;
}
