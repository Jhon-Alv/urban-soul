package com.urbansoul.backend.controller;

import com.urbansoul.backend.dto.ProductDto;
import com.urbansoul.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        ProductDto dto = productService.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public List<ProductDto> getProducts(){
        return productService.getProductList();
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto dto = productService.createProduct(productDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id){
        ProductDto dto = productService.updateProduct(productDto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
