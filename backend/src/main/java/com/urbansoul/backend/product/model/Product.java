package com.urbansoul.backend.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String size;
    private String color;
    private Double price;
    private Integer stock;
    private String urlImage;
    private String status;



}
