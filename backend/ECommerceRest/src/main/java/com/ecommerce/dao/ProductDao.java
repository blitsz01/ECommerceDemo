package com.ecommerce.dao;

import com.ecommerce.entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Stateless
public class ProductDao {  

    public List<Product> getProducts(EntityManager entityManager){
        return entityManager.createNamedQuery(Product.FIND_ALL, Product.class).getResultList();
    }
    
    public List<Product> findProductsBySupplier(EntityManager entityManager, Long supplierId){
        TypedQuery<Product> find = entityManager.createNamedQuery(Product.FIND_BY_SUPPLIER, Product.class);
        find.setParameter("supplierId", supplierId);
        return find.getResultList();
    }
    
    public List<Product> findProductsByCategory(EntityManager entityManager, String category){
        TypedQuery<Product> find = entityManager.createNamedQuery(Product.FIND_BY_CATEGORY, Product.class);
        find.setParameter("queryterm", category);
        return find.getResultList();
    }
    
    public List<Product> searchProducts(EntityManager entityManager, String strParam){
        TypedQuery<Product> find = entityManager.createNamedQuery(Product.SEARCH, Product.class);
        find.setParameter("queryterm", strParam);
        return find.getResultList();
    }
}
