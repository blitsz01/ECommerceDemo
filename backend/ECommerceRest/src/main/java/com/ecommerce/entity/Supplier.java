package com.ecommerce.entity;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@NamedQueries({
    @NamedQuery(name = Supplier.SEARCH, query = "SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE CONCAT( '%',:queryterm, '%') OR LOWER(s.details) LIKE CONCAT( '%', :queryterm, '%') ORDER BY s.name"),
    @NamedQuery(name = Supplier.FIND_ALL, query = "SELECT s from Supplier s")
})
@EqualsAndHashCode(callSuper=true, exclude = {"productList"})
@Table(name = "SUPPLIER")
@Data
public class Supplier extends AbstractEntity{
    
    private static final long serialVersionUID = 1127216190144975765L;
    
    public static final String SEARCH = "Supplier.search";
    public static final String FIND_ALL = "Supplier.findAll";   
    
    @Basic(optional = false)
    @NotEmpty(message = "Name cannot be empty")
    @Size(max = 30)
    @Column(name="NAME", length = 30, nullable = false)
    private String name;
    
    @Size(max = 255)
    @Column(name="DETAILS", length = 255)
    private String details;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> productList;
    
}
