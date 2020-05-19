package com.ecommerce.dao;

import com.ecommerce.entity.Supplier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class SupplierDao {
    
     public List<Supplier> getSuppliers(EntityManager entityManager){
        return entityManager.createQuery("SELECT s from Supplier s", Supplier.class).getResultList();
    }
}
