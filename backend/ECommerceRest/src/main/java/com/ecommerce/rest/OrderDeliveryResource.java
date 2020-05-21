package com.ecommerce.rest;

import com.ecommerce.entity.OrderDelivery;
import com.ecommerce.service.OrderDeliveryService;
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

@Path("orderDelivery")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderDeliveryResource {
    
    @Inject
    OrderDeliveryService orderDeliveryService;
    
    @Path("new")
    @POST
    public Response createOrderDelivery(OrderDelivery orderDelivery){
        //api/v1/orderDelivery/new
        orderDeliveryService.createOrderDelivery(orderDelivery);
        return Response.ok(orderDelivery).build();
    }
    
    @Path("update")
    @PUT
    public Response updateOrderDelivery(OrderDelivery orderDelivery){
        //api/v1/orderDelivery/update
        orderDeliveryService.updateOrderDelivery(orderDelivery);
        return Response.ok(orderDelivery).build();
    }
    
    @Path("{id}")
    @GET
    public OrderDelivery getOrderDelivery(@PathParam("id") Long id){
        //api/v1/orderDelivery/{id}
        return orderDeliveryService.findOrderDeliveryById(id);
    }
    
    @Path("list")
    @GET
    public List<OrderDelivery> getOrderDeliveryList(){
        //api/v1/orderDelivery/list
        return orderDeliveryService.getOrderDelivery();
    }
    
}
