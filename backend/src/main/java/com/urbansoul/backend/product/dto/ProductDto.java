package com.urbansoul.backend.product.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    @NotBlank(message = "The name is obligatory")
    private String name;
    @NotBlank(message = "The description is obligatory")
    @Size(max = 255, message = "The description cannot exceed 255 characters")
    private String description;
    @NotBlank(message = "The size is obligatory")
    private String size;
    @NotBlank(message = "Color is obligatory")
    private String color;
    @NotNull(message = "The price is obligatory")
    @DecimalMin(value = "0.1", message = "The price must be greater than 0")
    private Double price;
    @NotBlank(message = "Stock is obligatory")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
    @NotBlank(message = "The status is obligatory")
    @Pattern(regexp = "^(active|disabled)$", message = "The status must be ‘active’ or ‘disabled’")
    private String status;
    private String urlImage;


}
