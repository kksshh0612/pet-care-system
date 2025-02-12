package org.example.petsystem.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private long totalSaleQuantity;

    private long viewCount;

    private ProductCategory productCategory;

    @Builder
    public Product(String name, long totalSaleQuantity, long viewCount, ProductCategory productCategory) {
        this.name = name;
        this.totalSaleQuantity = totalSaleQuantity;
        this.viewCount = viewCount;
        this.productCategory = productCategory;
    }
}
