package com.ecommerce.service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.entity.Product;
import java.util.List;
import javax.inject.Inject;

public class ProductService extends AbstractService{
    
    @Inject
    ProductDao productDao;
    
    public Product createProduct(Product product){
        return (Product) create(product);      
    }
    
    public Product updateProduct(Product product){
        return (Product) update(product);
    }
    
    public Product findProductById(Long id){
        return entityManager.find(Product.class, id);
    }
    
    public List<Product> getProducts(){
        return productDao.getProducts(entityManager);
    }

}
