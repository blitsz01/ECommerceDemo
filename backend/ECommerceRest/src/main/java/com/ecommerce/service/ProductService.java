package com.ecommerce.service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class ProductService {
    @PersistenceContext
    EntityManager entityManager;
    
    @Inject
    ProductDao productDao;
    
    public Product createProduct(Product product){
       // System.out.print(product.getSupplier().getId());
        entityManager.persist(product);
        return product;      
    }
    
    public Product updateProduct(Product product){
        entityManager.merge(product);
        return product;
    }
    
    public Product findProductById(Long id){
        return entityManager.find(Product.class, id);
    }
    
    
    public List<Product> getProducts(){
        return productDao.getProducts(entityManager);
    }
}
