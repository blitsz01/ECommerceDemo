package com.ecommerce.dao;

import com.ecommerce.entity.OrderProducts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class OrderProductsDao {
    public List<OrderProducts> getOrderProducts(EntityManager entityManager){
        return entityManager.createQuery("SELECT o FROM OrderProducts o", OrderProducts.class).getResultList();
    }
}
