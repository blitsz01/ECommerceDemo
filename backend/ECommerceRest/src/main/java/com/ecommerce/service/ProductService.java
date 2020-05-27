package com.ecommerce.service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;
import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;

public class ProductService extends AbstractService{
    
    @Inject
    ProductDao productDao;
    
    public Product createProduct(Product product){
        return (Product) create(product);      
    }
    
    public Product updateProduct(Product product){
        product.setUpdatedOn(LocalDateTime.now());
        return (Product) update(product);
    }
    
    public Product findProductById(Long id){
        return entityManager.find(Product.class, id);
    }
    
    public List<Product> getProducts(){
        return productDao.getProducts(entityManager);
    }
    
    public List<Product> getProductsBySupplier(Long supplierId){
        return productDao.findProductsBySupplier(entityManager, supplierId);
    }

    public List<Product> searchProducts(String search) {
        return productDao.searchProducts(entityManager, search);
    }
    
    public List<Product> getProductsByCategory(String search){
        return productDao.findProductsByCategory(entityManager, search);
    }

}
