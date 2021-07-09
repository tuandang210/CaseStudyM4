package com.codegym.casestudym4.model.dtoCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long user_id;

    private String date;

    private Double totalSpent;

    private List<ProductDto> products;
}
