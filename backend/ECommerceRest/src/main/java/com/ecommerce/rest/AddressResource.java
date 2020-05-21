package com.ecommerce.rest;

import com.ecommerce.entity.Address;
import com.ecommerce.service.AddressService;
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

@Path("address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressResource {
     @Inject
    AddressService addressService;
    
    @Path("new")
    @POST
    public Response createAddress(Address address){
        //api/v1/address/new
        System.out.print(address);
        addressService.createAddress(address);
        return Response.ok(address).build();
    }
    
    @Path("update")
    @PUT
    public Response updateAddress(Address address){
        //api/v1/address/update
        addressService.updateAddress(address);
        return Response.ok(address).build();
    }
    
    @Path("{id}")
    @GET
    public Address getAddress(@PathParam("id") Long id){
        //api/v1/address/{id}
        return addressService.findAddressById(id);
    }
    
    @Path("list")
    @GET
    public List<Address> getAddressList(){
        //api/v1/address/list
        return addressService.getAddressList();
    }
}
