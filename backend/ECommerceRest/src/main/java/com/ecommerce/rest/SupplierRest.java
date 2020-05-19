package com.ecommerce.rest;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
import com.ecommerce.service.SupplierService;
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

@Path("supplier")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SupplierRest {
    
    @Inject
    SupplierService supplierService;
    
    @Path("new")
    @POST
    public Response createProduct(Supplier supplier){
        //api/v1/supplier/new
        supplierService.createSupplier(supplier);
        return Response.ok(supplier).build();
    }
    
    @Path("update")
    @PUT
    public Response updateProduct(Supplier supplier){
        //api/v1/supplier/update
        supplierService.updateSupplier(supplier);
        return Response.ok(supplier).build();
    }
    
    @Path("{id}")
    @GET
    public Supplier getSupplier(@PathParam("id") Long id){
        //api/v1/supplier/{id}
        return supplierService.findSupplierById(id);
    }
    
    @Path("list")
    @GET
    public List<Supplier> getProducts(){
        //api/v1/supplier/list
        return supplierService.getSuppliers();
    }
    
}
