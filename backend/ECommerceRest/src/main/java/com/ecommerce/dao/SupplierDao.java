package com.ecommerce.dao;

import com.ecommerce.entity.Supplier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Stateless
public class SupplierDao {
    
    /**
     * Get all supplier
     *
     * @param entityManager
     * @return 
     */
     public List<Supplier> getSuppliers(EntityManager entityManager){
        TypedQuery<Supplier> find = entityManager.createNamedQuery(Supplier.FIND_ALL, Supplier.class);
        return find.getResultList();
    }
     
    /**
     * Get all supplier
     *
     * @param entityManager
     * @param strParam
     * @return 
     */
     public List<Supplier> searchSuppliers(EntityManager entityManager, String strParam){
       TypedQuery<Supplier> find = entityManager.createNamedQuery(Supplier.SEARCH, Supplier.class);
       find.setParameter("queryterm", strParam.toLowerCase());
       return find.getResultList();
    }
}
