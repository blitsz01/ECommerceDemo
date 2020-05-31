package com.ecommerce.resource;

import com.ecommerce.entity.Address;
import com.ecommerce.service.AddressService;
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

@Path("address")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
     @Inject
    AddressService addressService;
    
     /**
     * Resource path = //api/v1/address/new
     *
     * @param address
     * @return address
     */
    @Path("new")
    @POST
    public Response createAddress(Address address){
        addressService.createAddress(address);
        return Response.ok(address).build();
    }
    
    /**
     * Resource path = //api/v1/address/update
     *
     * @param address
     * @return address
     */
    @Path("update")
    @PUT
    public Response updateAddress(Address address){
        addressService.updateAddress(address);
        return Response.ok(address).build();
    }
    
    /**
     * Resource path = //api/v1/address/{id}
     *
     * @param id
     * @return address
     */
    @Path("{id}")
    @GET
    public Address getAddress(@PathParam("id") Long id){
        return addressService.findAddressById(id);
    }
    
    /**
     * Resource path = //api/v1/address/list
     *
     * @return addressList
     */
    @Path("list")
    @GET
    public List<Address> getAddressList(){
        return addressService.getAddressList();
    }
}
