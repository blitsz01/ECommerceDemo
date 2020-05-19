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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product extends AbstractEntity {

    private static final long serialVersionUID = 6311954255716720577L;
      
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
      
    @NotNull
    @JoinColumn(name="SUPPLIER_ID", nullable=false)
    @ManyToOne(optional = false)
    private Supplier supplier;
  
}
