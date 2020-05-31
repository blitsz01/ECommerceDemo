package com.ecommerce.service;

import com.ecommerce.dao.CustOrderDao;
import com.ecommerce.entity.Address;
import com.ecommerce.entity.ApplicationUser;
import com.ecommerce.entity.CustOrder;
import com.ecommerce.entity.OrderDelivery;
import com.ecommerce.entity.OrderProducts;
import com.ecommerce.entity.PaymentMethod;
import com.ecommerce.entity.enums.AddressType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class CustOrderService  extends AbstractService{
    
    @Inject
    CustOrderDao custOrderDao;
    
    @Inject
    UserService userService;
    
    public CustOrder createCustOrder(CustOrder custOrder){
        return (CustOrder) create(custOrder);
    }
    
    public CustOrder updateCustOrder(CustOrder custOrder){
        custOrder.setUpdatedOn(LocalDateTime.now());
        return (CustOrder) update(custOrder);
    }
    
    public CustOrder findCustOrderById(Long id) {
        return entityManager.find(CustOrder.class, id);
    }

    public List<CustOrder> getCustOrderList() {
        return custOrderDao.getCustOrder(entityManager);
    }
    
    public CustOrder createOrder(CustOrder custOrder){
        //Generate OrderCode
        UUID uuid = UUID.randomUUID();
        String orderCode = uuid.toString();
        
        //Set data for customer
        ApplicationUser customer = new ApplicationUser();
        customer = userService.findUserByEmail(custOrder.getApplicationUser().getEmail());
        custOrder.setApplicationUser(customer);

        //Set data for delivery
        OrderDelivery orderDelivery = custOrder.getOrderDelivery();
        orderDelivery.setDateReported(LocalDateTime.now());
        orderDelivery.setOrderCode(orderCode);
        custOrder.setOrderDelivery(orderDelivery);
         
        //Set data for address
        Address address  = custOrder.getAddress();
        address.setAddressType(AddressType.HOME);
        address.setOrderCode(orderCode);
        custOrder.setAddress(address);
        
        //Set data for payment
        PaymentMethod paymentMethod = custOrder.getPaymentMethod();
        paymentMethod.setOrderCode(orderCode);
        custOrder.setPaymentMethod(paymentMethod);
        
        //Set data for order items
        List<OrderProducts> orderProducts = custOrder.getOrderProductList(); 
        for(OrderProducts orderItem : orderProducts){
            OrderProducts orderProduct = orderItem;
            orderProduct.setOrderCode(orderCode);
        }
        
        //create order to database
        custOrder.setDateOrderPlaced(LocalDateTime.now());
        custOrder.setOrderCode(orderCode);
        custOrder.setOrderProductList(orderProducts);
        return (CustOrder) create(custOrder);
    }
    
}
