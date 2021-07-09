package com.codegym.casestudym4.model.orderDTO;

import com.codegym.casestudym4.model.Orders;
import com.codegym.casestudym4.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private Orders orders;

    private List<Product> product;

    private List<Long> quantity;

    private Double totalSpent;

}
