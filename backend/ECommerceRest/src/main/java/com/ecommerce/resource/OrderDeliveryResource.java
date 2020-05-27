package com.ecommerce.resource;

import com.ecommerce.entity.OrderDelivery;
import com.ecommerce.service.OrderDeliveryService;
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

@Path("orderDelivery")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderDeliveryResource {
    
    @Inject
    OrderDeliveryService orderDeliveryService;
    
    /**
     * Resource path =  //api/v1/orderDelivery/new
     *
     * @param orderDelivery
     * @return orderDelivery
     */
    @Path("new")
    @POST
    public Response createOrderDelivery(OrderDelivery orderDelivery){
        orderDeliveryService.createOrderDelivery(orderDelivery);
        return Response.ok(orderDelivery).build();
    }
    
    /**
     * Resource path =  //api/v1/orderDelivery/update
     *
     * @param orderDelivery
     * @return orderDelivery
     */
    @Path("update")
    @PUT
    public Response updateOrderDelivery(OrderDelivery orderDelivery){
        orderDeliveryService.updateOrderDelivery(orderDelivery);
        return Response.ok(orderDelivery).build();
    }
    
    /**
     * Resource path =  //api/v1/orderDelivery/{id}
     *
     * @param id
     * @return orderDelivery
     */
    @Path("{id}")
    @GET
    public OrderDelivery getOrderDelivery(@PathParam("id") Long id){
        return orderDeliveryService.findOrderDeliveryById(id);
    }
    
     /**
     * Resource path =  //api/v1/orderDelivery/list
     *
     * @return orderDeliveryList
     */
    @Path("list")
    @GET
    public List<OrderDelivery> getOrderDeliveryList(){
        return orderDeliveryService.getOrderDelivery();
    }
    
}
