package com.ecommerce.service;

import com.ecommerce.dao.OrderProductsDao;
import com.ecommerce.entity.OrderProducts;
import java.util.List;
import javax.inject.Inject;

public class OrderProductsService extends AbstractService {
    
    @Inject
    OrderProductsDao orderProductsDao;
    
    public OrderProducts createOrderProducts(OrderProducts orderProducts){
        return (OrderProducts) create(orderProducts);
    }
    
    public OrderProducts updateOrderProducts(OrderProducts orderProducts){
        return (OrderProducts) update(orderProducts);
    }
    
    public OrderProducts findOrderProductsById(Long id) {
        return entityManager.find(OrderProducts.class, id);
    }

    public List<OrderProducts> getOrderProducts() {
        return orderProductsDao.getOrderProducts(entityManager);
    }
    
}
