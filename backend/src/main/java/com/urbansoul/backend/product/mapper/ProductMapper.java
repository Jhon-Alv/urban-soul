package com.urbansoul.backend.product.mapper;

import com.urbansoul.backend.product.dto.ProductDto;
import com.urbansoul.backend.product.model.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        if (product == null) return null;

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setSize(product.getSize());
        dto.setColor(product.getColor());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setUrlImage(product.getUrlImage());
        dto.setStatus(product.getStatus());

        return dto;

    }

    public static Product toEntity(ProductDto dto){
        if (dto == null) return null;

        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setSize(dto.getSize());
        product.setColor(dto.getColor());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setUrlImage(dto.getUrlImage());
        product.setStatus(dto.getStatus());

        return product;
    }

}
