package com.ecommerce.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
//@NamedQuery(name = Supplier.FIND_BY_NAME, query = "select s. from Supplier s where  s.name = :name")
@Table(name = "SUPPLIER")
@Data
public class Supplier extends AbstractEntity{
    
    private static final long serialVersionUID = 1127216190144975765L;
    
    public static final String FIND_BY_NAME = "Supplier.FindByName";
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 30)
    @Column(name="NAME", length = 30, nullable = false)
    private String name;
    
    @Size(max = 255)
    @Column(name="DETAILS", length = 255)
    private String details;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> productList;
    
}
