package com.ecommerce.entity;

import com.ecommerce.entity.enums.DeliveryStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "ORDER_DELIVERY")
@Data
public class OrderDelivery extends AbstractEntity {
    private static final long serialVersionUID = -2889217265568322995L;
    
    @NotNull
    @Column(name="ORDER_CODE")
    private String orderCode;
    
    @Enumerated(EnumType.STRING)
    @Column(name="DELIVERY_STATUS")
    private DeliveryStatus deliveryStatus;
    
    @Column(name="DATE_REPORTED")
    private LocalDateTime dateReported;
    
    @OneToOne
    @JoinColumn(name = "CUST_ORDER_ID")
    private CustOrder custOrder;

}
