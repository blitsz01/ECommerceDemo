package com.ecommerce.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer extends AbstractEntity {
    private static final long serialVersionUID = -6606157211844304071L;
    
    @Basic(optional = false)
    @Column(name="LAST_NAME", length = 30, nullable = false)
    private String lastName;
    
    @Basic(optional = false)
    @Column(name="FIRST_NAME", length = 30, nullable = false)
    private String firstName;
    
    @Column(name="MIDDLE_NAME", length = 30)
    private String middleName;
    
    @Column(name="PHONE", length = 15, nullable = false)
    private String phone;
    
    @Column(name="EMAIL", length = 30, nullable = false)
    private String email;
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "customer")
    private List<CustOrder> custOrderList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Address> addressList;
    
    
}
