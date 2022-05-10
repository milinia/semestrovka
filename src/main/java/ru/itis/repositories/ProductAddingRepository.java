package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.ProductAdding;

public interface ProductAddingRepository extends JpaRepository<ProductAdding, Long> {
}
