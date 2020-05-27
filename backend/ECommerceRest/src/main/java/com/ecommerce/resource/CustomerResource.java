package com.ecommerce.resource;

import com.ecommerce.config.Secure;
import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
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
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {
    
    @Inject
    CustomerService customerService;
    
    /**
     * Resource path =  //api/v1/customer/new
     *
     * @param customer
     * @return customer
     */
    @Path("new")
    @POST
    public Response createCustomer(Customer customer){
        customerService.createCustomer(customer);
        return Response.ok(customer).build();
    }
    
    /**
     * Resource path =  //api/v1/customer/update
     *
     * @param customer
     * @return customer
     */
    @Path("update")
    @PUT
    public Response updateCustomer(Customer customer){
        customerService.updateCustomer(customer);
        return Response.ok(customer).build();
    }
    
    /**
     * Resource path =  //api/v1/customer/{id}
     *
     * @param id
     * @return customer
     */
    @Path("{id}")
    @GET
    public Customer getCustomer(@PathParam("id") Long id){
        return customerService.findCustomerById(id);
    }
    
    /**
     * Resource path =  //api/v1/customer/list
     *
     * @return customerList
     */
    @Path("list")
    @GET
    public List<Customer> findCustomerList(){
        return customerService.getCustomerList();
    }
    
    
}
