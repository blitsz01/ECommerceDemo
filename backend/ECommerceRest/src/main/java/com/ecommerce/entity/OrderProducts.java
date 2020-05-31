package com.ecommerce.entity;

import java.util.UUID;
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
    
    @NotNull
    @Column(name="ORDER_CODE")
    private String orderCode;
    
    @NotNull
    @Column(name="PRODUCT_ID")
    private Long productId;
        
    @Size(max = 255)
    @Column(name="COMMENTS")
    private String comments;
    
    @NotNull
    @Column(name="QTY", nullable=false)
    private int qty;
    
    @NotNull
    @Column(name="PRICE", nullable=false)
    private double price;
    
    @JoinColumn(name="CUST_ORDER_ID")
    @ManyToOne
    private CustOrder custOrder;    
}
