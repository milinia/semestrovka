package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Day;
import ru.itis.models.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
//    void addProductToDay(Day day);
//    List<Product> getAllAvailableProducts();
    Product findByName(String name);
}
