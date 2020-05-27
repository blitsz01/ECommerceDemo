package com.ecommerce.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItems {
    private Long id;
    private int countInStock;
    private String name;
    private Double price;
    private int qty;
}
