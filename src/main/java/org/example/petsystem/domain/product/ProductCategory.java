package org.example.petsystem.domain.product;

import lombok.Getter;

@Getter
public enum ProductCategory {

    FEED("사료"), SANITARY("위생용품"), CLOTHES("의류"), BOWL("식기/그릇");

    private String name;

    ProductCategory(String name) {
        this.name = name;
    }
}
