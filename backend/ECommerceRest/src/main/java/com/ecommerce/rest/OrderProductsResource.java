package com.ecommerce.rest;

import com.ecommerce.entity.OrderProducts;
import com.ecommerce.service.OrderProductsService;
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

@Path("orderProduct")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderProductsResource {
    @Inject
    OrderProductsService orderProductsService;
    
    @Path("new")
    @POST
    public Response createOrderProducts(OrderProducts orderProducts){
        //api/v1/orderProduct/new
        orderProductsService.createOrderProducts(orderProducts);
        return Response.ok(orderProducts).build();
    }
    
    @Path("update")
    @PUT
    public Response updateOrderProducts(OrderProducts orderProducts){
        //api/v1/orderProduct/update
        orderProductsService.updateOrderProducts(orderProducts);
        return Response.ok(orderProducts).build();
    }
    
    @Path("{id}")
    @GET
    public OrderProducts getOrderProducts(@PathParam("id") Long id){
        //api/v1/orderProduct/{id}
        return orderProductsService.findOrderProductsById(id);
    }
    
    @Path("list")
    @GET
    public List<OrderProducts> getOrderProducts(){
        //api/v1/orderProduct/list
        return orderProductsService.getOrderProducts();
    }
}
