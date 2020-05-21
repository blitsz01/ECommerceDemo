package com.ecommerce.rest;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
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

@Path("product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    @Inject
    ProductService productService;
    
    @Path("new")
    @POST
    public Response createProduct(Product product){
        //api/v1/product/new
        System.out.print(product);
        productService.createProduct(product);
        return Response.ok(product).build();
    }
    
    @Path("update")
    @PUT
    public Response updateProduct(Product product){
        //api/v1/product/update
        productService.updateProduct(product);
        return Response.ok(product).build();
    }
    
    @Path("{id}")
    @GET
    public Product getProduct(@PathParam("id") Long id){
        //api/v1/product/{id}
        return productService.findProductById(id);
    }
    
    @Path("list")
    @GET
    public List<Product> getProductList(){
        //api/v1/product/list
        return productService.getProducts();
    }
    
    
/*  @Path("status")
    @POST
    public Response markAsComplete(@QueryParam("id") Long id){
        Todo todo = todoService.findTodoById(id);
        todo.setIsCompaleted(true);
        todoService.updateTodo(todo);
        return Response.ok(todo).build();
    } 
 */
}
