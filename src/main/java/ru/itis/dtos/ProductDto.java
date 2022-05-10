package ru.itis.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private Long productId;
    private Integer amount; //in grams
}
