package com.urbansoul.backend.service;

import com.urbansoul.backend.dto.ProductDto;
import com.urbansoul.backend.mapper.ProductMapper;
import com.urbansoul.backend.model.Product;
import com.urbansoul.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public ProductDto getProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            Product found = product.get();
            return ProductMapper.toDto(found);
        }else {
            throw new EntityNotFoundException("Product with ID "+ id + "not found");
        }

    }

    public List<ProductDto> getProductList(){
        return productRepository.findAll().stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public ProductDto createProduct(ProductDto productDto){
        Product product = ProductMapper.toEntity(productDto);
        Product saved = productRepository.save(product);
        return ProductMapper.toDto(saved);
    }

    public ProductDto updateProduct(ProductDto productDto, Long id){
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()){
            Product found = optional.get();
            found.setName(productDto.getName());
            found.setDescription(productDto.getDescription());
            found.setSize(productDto.getSize());
            found.setColor(productDto.getColor());
            found.setPrice(productDto.getPrice());
            found.setStock(productDto.getStock());
            found.setUrlImage(productDto.getUrlImage());
            found.setStatus(productDto.getStatus());

            Product saved = productRepository.save(found);
            return ProductMapper.toDto(saved);
        } else {
            throw new EntityNotFoundException("Product with ID "+ id + "not found");
        }
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
