package com.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "ORDER_PRODUCTS")
@Data
public class OrderProducts extends AbstractEntity{
    
    private static final long serialVersionUID = -8689631386945097022L;
    
    @Size(max = 255)
    @Column(name="COMMENTS")
    private String comments;
    
    @NotNull
    @NotEmpty(message = "Quantity cannot be empty")
    @Column(name="QUANTITY", nullable=false)
    private int quantity;
    
    @NotNull
    @NotEmpty(message = "Quantity cannot be empty")
    @Column(name="TOTAL_PRICE", nullable=false)
    private double totalPrice;
    
    @NotNull
    @JoinColumn(name="CUST_ORDER_ID", nullable=false)
    @ManyToOne(optional = false)
    private CustOrder custOrder;
    
    @NotNull
    @JoinColumn(name="PRODUCT_ID", nullable=false)
    @ManyToOne(optional = false)
    private Product product;
    
}
