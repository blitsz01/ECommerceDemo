package com.ecommerce.entity;

import com.ecommerce.entity.enums.AddressType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "ADDRESS")
@Data
public class Address extends AbstractEntity{

    private static final long serialVersionUID = -1101091931023846908L;
      
    @NotNull
    @Column(name="ORDER_CODE")
    private String orderCode;
    
    @Enumerated(EnumType.STRING)
    @Column(name="ADDRESS_TYPE")
    private AddressType addressType;
    
    @Column(name="LINE1", length = 30)
    private String line1;
    
    @Column(name="LINE2", length = 30)
    private String line2;
    
    @Column(name="LINE3", length = 30)
    private String line3;
    
    @Basic(optional = false)
    @Column(name="CITY", length = 30, nullable = false)
    private String city;
    
    @Basic(optional = false)
    @Column(name="POST_CODE", length = 10, nullable = false)
    private String postCode;
    
    @Basic(optional = false)
    @Column(name="COUNTRY", length = 30, nullable = false)
    private String country;
    
    @OneToOne
    @JoinColumn(name = "CUST_ORDER_ID")
    private CustOrder custOrder;
    
}
