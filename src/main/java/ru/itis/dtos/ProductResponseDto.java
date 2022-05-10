package ru.itis.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    private String product;
    private Integer amount;
    private Float calories;
    private Float protein;
    private Float fiber;
    private Float carbs;
}
