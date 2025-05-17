package com.jpabased.allaboutjpa.entities.repository;

import com.jpabased.allaboutjpa.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<Product> findByTitleOrderByPrice(String title);

    List<Product> findByOrderByPrice();

    List<Product> findByCreatedAtAfter(LocalDateTime time);

    List<Product> findByQuantityAndPrice(int quantity, BigDecimal price);

    List<Product> findByTitleLike(String choco);

    @Query("SELECT e FROM Product e WHERE e.title = :title AND e.price = :price")
    Optional<Product> findByTitleAndPrice(
            @Param("title") String title,
            @Param("price") BigDecimal price
    );
}
