package com.ecommerce.entity;

import com.ecommerce.entity.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@NamedQueries({
    @NamedQuery(name = CustOrder.SEARCH_BY_ORDER_STATUS, query = "SELECT c FROM CustOrder c WHERE c.orderStatus = :queryterm"),
    @NamedQuery(name = CustOrder.SEARCH_BY_ORDER_DATE, query = "SELECT c FROM CustOrder c WHERE c.dateOrderPlaced BETWEEN :startDate AND :endDate"),
    @NamedQuery(name = CustOrder.FIND_ALL, query = "SELECT c from CustOrder c")
})
@Table(name = "CUST_ORDER")
@Data
public class CustOrder extends AbstractEntity{

    private static final long serialVersionUID = 8016025793039855094L;
    
    public static final String SEARCH_BY_ORDER_STATUS = "CustOrder.searchByStatus";
    public static final String SEARCH_BY_ORDER_DATE = "CustOrder.searchByOrderDate";
    public static final String FIND_ALL = "CustOrder.findAll";
    
    @Enumerated(EnumType.STRING)
    @Column(name="ORDER_STATUS")
    private OrderStatus orderStatus;
    
    @Column(name="DATA_ORDER_PLACED")
    private LocalDateTime dateOrderPlaced;
    
    @Column(name="DATE_ORDER_PAID")
    private LocalDateTime dateOrderPaid;
    
    @NotNull
    @Column(name="TOTAL_PRICE")
    private Double totalPrice;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    
    @OneToOne(mappedBy = "custOrder", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DELIVERY_ID")
    private OrderDelivery orderDelivery;
    
    @OneToOne(mappedBy = "custOrder", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custOrder")
    private List<OrderProducts> orderProductList;
    
}
