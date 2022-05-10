package ru.itis.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private String product;
    private Integer amount; //in grams
}
