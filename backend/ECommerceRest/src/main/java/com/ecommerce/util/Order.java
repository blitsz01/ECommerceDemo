
package com.ecommerce.util;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    
    private List<OrderItems> orderitems;
    private String payment;
    private Shipping shipping;
    private Double itemsPrice;
    private Double shippingPrice;
    private Double taxPrice;
    private Double totalPrice;
    private UserInfo userInfo;
    
}
