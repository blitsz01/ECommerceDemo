package com.ecommerce.dao;

import com.ecommerce.entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class ProductDao {  

    public List<Product> getProducts(EntityManager entityManager){
        return entityManager.createQuery("SELECT p from Product p", Product.class).getResultList();
    }
}
