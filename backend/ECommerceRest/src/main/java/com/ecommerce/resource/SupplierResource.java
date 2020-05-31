package com.ecommerce.resource;

import com.ecommerce.config.BusinessLogicException;
import com.ecommerce.config.Secure;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Supplier;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SupplierService;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("supplier")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SupplierResource {
    
    @Inject
    SupplierService supplierService;
    
    @Inject
    ProductService productService;
    
    /**
     * Resource path =  //api/v1/supplier/new
     *
     * @param supplier
     * @return supplier
     */
    @Path("new")
    @POST
    @Secure
    public Response createSupplier(Supplier supplier){
        supplierService.createSupplier(supplier);
        return Response.ok(supplier).build();
    }   
    
     /**
     * Resource path =  //api/v1/supplier/update
     *
     * @param supplier
     * @return supplier
     */
    @Path("update")
    @PUT
    @Secure
    public Response updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return Response.ok(supplier).build();
    }
    
    /**
     * Resource path =  //api/v1/supplier/{id}
     *
     * @param id
     * @return supplier
     * @throws com.ecommerce.config.BusinessLogicException
     */
    @Path("{id}")
    @GET
    @Secure
    public Response getSupplier(@PathParam("id") Long id){
        Supplier supplier = new Supplier();
 
        supplier = supplierService.findSupplierById(id);
        
        List<Product> productList= productService.getProductsBySupplier(id);
        
        if(!productList.isEmpty()){
            supplier.setProductList(productList);
        }
        
        if(supplier.getId() != null){
            return Response.ok(supplier).status(Response.Status.FOUND).build();
        }
        return Response.ok(supplier).status(Response.Status.NOT_FOUND).build();
    }
    
    /**
     * Resource path =  //api/v1/supplier/list
     *
     * @return supplierList
     */
    @Path("list")
    @GET
    @Secure
    public List<Supplier> getSuppliers(){
        return supplierService.getSuppliers();
    }
    
    /**
     * Resource path =  //api/v1/supplier/list
     *
     * @param str
     * @return supplierList
     */
    @Path("search")
    @GET
    @Secure
    public List<Supplier> searchSuppliers(@QueryParam("search") String search){
        return supplierService.searchSupplierList(search);
    }
    
}
