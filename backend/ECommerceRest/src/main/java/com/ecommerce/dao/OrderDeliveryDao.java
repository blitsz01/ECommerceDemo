package com.ecommerce.dao;

import com.ecommerce.entity.OrderDelivery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class OrderDeliveryDao {
    public List<OrderDelivery> getOrderDelivery(EntityManager entityManager){
        return entityManager.createQuery("SELECT o FROM OrderDelivery o", OrderDelivery.class).getResultList();
    }
}
