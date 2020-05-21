package com.ecommerce.service;

import com.ecommerce.dao.OrderDeliveryDao;
import com.ecommerce.entity.OrderDelivery;
import java.util.List;
import javax.inject.Inject;

public class OrderDeliveryService extends AbstractService{
    
    @Inject
    OrderDeliveryDao orderDeliveryDao;
    
    public OrderDelivery createOrderDelivery(OrderDelivery orderDelivery){
        return (OrderDelivery) create(orderDelivery);
    }
    
    public OrderDelivery updateOrderDelivery(OrderDelivery orderDelivery){
        return (OrderDelivery) update(orderDelivery);
    }
    
    public OrderDelivery findOrderDeliveryById(Long id) {
        return entityManager.find(OrderDelivery.class, id);
    }

    public List<OrderDelivery> getOrderDelivery() {
        return orderDeliveryDao.getOrderDelivery(entityManager);
    }
    
}
