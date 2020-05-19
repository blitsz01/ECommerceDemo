/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecommerce.entity;

import com.ecommerce.entity.enums.PaymentType;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "PAYMENT_METHOD")
@Data
public class PaymentMethod extends AbstractEntity{
    private static final long serialVersionUID = -1562951960358978248L;
    
    @Enumerated(EnumType.STRING)
    @Column(name="PAYMENT_TYPE")
    private PaymentType paymentType;

    @Size(max = 30)
    @Column(name="CARD_NUMBER", length = 30)
    private String cardNumber;
    
    @Column(name="DATE_FROM")
    private LocalDate dateFrom;
    
    @Column(name="DATE_TO")
    private LocalDate dateTo;
    
    @OneToOne
    @JoinColumn(name = "CUST_ORDER_ID")
    private CustOrder custOrder;
}
