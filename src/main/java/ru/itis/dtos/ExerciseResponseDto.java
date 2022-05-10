package ru.itis.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseResponseDto {
    private String kind;
    private Integer duration;
}
