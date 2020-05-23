package com.ecommerce.service;

import com.ecommerce.dao.SupplierDao;
import com.ecommerce.entity.Supplier;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;

public class SupplierService extends AbstractService{
    
    @Inject
    SupplierDao supplierDao;

    public Supplier createSupplier(Supplier supplier) {
        return  (Supplier) create(supplier);    
    }

    public Supplier updateSupplier(Supplier supplier) {
        supplier.setUpdatedOn(LocalDateTime.now());
        return (Supplier) update(supplier);
    }

    public Supplier findSupplierById(Long id) {
        return entityManager.find(Supplier.class, id);
    }

    public List<Supplier> getSuppliers() {
        return supplierDao.getSuppliers(entityManager);
    }
    
    public List<Supplier> searchSupplierList(String strParam) {
        return supplierDao.searchSuppliers(entityManager, strParam);
    }
}
