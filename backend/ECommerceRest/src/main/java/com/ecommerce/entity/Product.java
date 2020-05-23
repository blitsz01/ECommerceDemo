package com.ecommerce.entity;

import com.ecommerce.entity.enums.ProductCategory;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@NamedQueries({
    @NamedQuery(name = Product.SEARCH, query = "SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT( '%',:queryterm, '%') OR LOWER(p.productCategory) LIKE CONCAT( '%', :queryterm, '%') OR LOWER(p.brand) LIKE CONCAT( '%', :queryterm, '%') ORDER BY p.name"),
    @NamedQuery(name = Product.FIND_ALL, query = "SELECT p from Product p"),
    @NamedQuery(name = Product.FIND_BY_CATEGORY, query = "SELECT p FROM Product p WHERE p.productCategory = :queryterm"),
    @NamedQuery(name = Product.FIND_BY_SUPPLIER, query = "SELECT p from Product p WHERE p.supplier.id = :supplierId")
})
@Table(name = "PRODUCT")
@Data
public class Product extends AbstractEntity {

    private static final long serialVersionUID = 6311954255716720577L;
    
    public static final String SEARCH = "Product.search";
    public static final String FIND_ALL = "Product.findAll";
    public static final String FIND_BY_SUPPLIER = "Product.findBySupplier";
    public static final String FIND_BY_CATEGORY = "Product.findByCategory";
      
    @Basic(optional = false)
    @NotNull
    @Size(max = 30)
    @Column(name="NAME", length = 30, nullable = false)
    private String name;
    
    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="PRODUCT_CATEGORY")
    private ProductCategory productCategory;
    
    @Lob
    @Column(name="IMAGE")
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    
    @Column(name="PRICE")
    private double price;
    
    @Size(max = 30)
    @Column(name="BRAND")
    private String brand;
    
    @Column(name="RATING")
    private double rating;
    
    @Basic(optional = false)
    @Column(name="NUM_REVIEWS", nullable = false)
    private int numReviews;
    
    @Basic(optional = false)
    @Column(name="COUNT_IN_STOCK", nullable = false)
    private int countInStock;
      
    @NotNull
    @JoinColumn(name="SUPPLIER_ID", nullable=false)
    @ManyToOne(optional = false)
    private Supplier supplier;
  
}
