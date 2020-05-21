package com.ecommerce.rest;

import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    @Inject
    CustomerService customerService;
    
    @Path("new")
    @POST
    public Response createCustomer(Customer customer){
        //api/v1/customer/new
        customerService.createCustomer(customer);
        return Response.ok(customer).build();
    }
    
    @Path("update")
    @PUT
    public Response updateCustomer(Customer customer){
        //api/v1/customer/update
        customerService.updateCustomer(customer);
        return Response.ok(customer).build();
    }
    
    @Path("{id}")
    @GET
    public Customer getCustomer(@PathParam("id") Long id){
        //api/v1/customer/{id}
        return customerService.findCustomerById(id);
    }
    
    @Path("list")
    @GET
    public List<Customer> findCustomerList(){
        //api/v1/customer/list
        return customerService.getCustomerList();
    }
    
    
}
