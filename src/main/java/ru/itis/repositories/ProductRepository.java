package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Day;
import ru.itis.models.Product;

import java.util.List;

@Repository
interface ProductRepository extends JpaRepository<Product,Long> {
//    void addProductToDay(Day day);
//    List<Product> getAllAvailableProducts();
}
