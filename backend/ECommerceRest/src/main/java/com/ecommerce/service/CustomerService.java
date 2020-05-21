package com.ecommerce.service;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.entity.Customer;
import java.util.List;
import javax.inject.Inject;

public class CustomerService extends AbstractService{
    
    @Inject
    CustomerDao customerDao;
    
    public Customer createCustomer(Customer customer){
        return (Customer) create(customer);
    }
    
    public Customer updateCustomer(Customer customer){
        return (Customer) update(customer);
    }
    
    public Customer findCustomerById(Long id) {
        return entityManager.find(Customer.class, id);
    }
    
    public List<Customer> getCustomerList() {
        return customerDao.getCustomer(entityManager);
    }
    
}
