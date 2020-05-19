package com.ecommerce.service;

import com.ecommerce.dao.SupplierDao;
import com.ecommerce.entity.Supplier;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class SupplierService {
    @PersistenceContext
    EntityManager entityManager;
    
    @Inject
    SupplierDao supplierDao;

    public Supplier createSupplier(Supplier supplier) {
        entityManager.persist(supplier);
        return supplier;    
    }

    public Supplier updateSupplier(Supplier supplier) {
        entityManager.merge(supplier);
        return supplier;
    }

    public Supplier findSupplierById(Long id) {
        return entityManager.find(Supplier.class, id);
    }

    public List<Supplier> getSuppliers() {
        return supplierDao.getSuppliers(entityManager);
    }
       
}
