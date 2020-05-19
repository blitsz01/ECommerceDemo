package com.ecommerce.entity;

import com.ecommerce.entity.enums.DeliveryStatus;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ORDER_DELIVERY")
@Data
public class OrderDelivery extends AbstractEntity {
    private static final long serialVersionUID = -2889217265568322995L;
    
    @Enumerated(EnumType.STRING)
    @Column(name="DELIVERY_STATUS")
    private DeliveryStatus deliveryStatus;
    
    @Column(name="DATE_REPORTED")
    private LocalDateTime dateReported;
    
    @OneToOne
    @JoinColumn(name = "CUST_ORDER_ID")
    private CustOrder custOrder;
    
    @PrePersist
    private void init(){
        setDateReported(LocalDateTime.now());
    }
}
