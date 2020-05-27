package com.ecommerce.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 301802839719186427L;

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    @Basic(optional = false)
    @Column(name="ID")
    protected Long id;

    @Column(name="CREATED_ON")
    protected LocalDateTime createdOn;
    
    @Column(name="UPDATED_ON")
    protected LocalDateTime updatedOn;
    
    @Column(name = "DELETE_FLAG")
    private Boolean deleteFlag;
      
    @PrePersist
    private void init(){
        setCreatedOn(LocalDateTime.now());
        setUpdatedOn(LocalDateTime.now());
        setDeleteFlag(Boolean.FALSE);
    }
  
}
