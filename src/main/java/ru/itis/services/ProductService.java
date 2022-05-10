package ru.itis.services;

import ru.itis.models.Product;

public interface ProductService {

    Product findByName(String name);
    void save(Product product);
}
