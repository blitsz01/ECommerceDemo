package com.ecommerce.resource;

import com.ecommerce.entity.OrderProducts;
import com.ecommerce.service.OrderProductsService;
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

@Path("orderProduct")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderProductsResource {
    @Inject
    OrderProductsService orderProductsService;
    
    /**
     * Resource path =  //api/v1/orderProduct/new
     *
     * @param orderProducts
     * @return orderProducts
     */
    @Path("new")
    @POST
    public Response createOrderProducts(OrderProducts orderProducts){
        orderProductsService.createOrderProducts(orderProducts);
        return Response.ok(orderProducts).build();
    }
    
    /**
     * Resource path =  //api/v1/orderProduct/update
     *
     * @param orderProducts
     * @return orderProducts
     */
    @Path("update")
    @PUT
    public Response updateOrderProducts(OrderProducts orderProducts){
        orderProductsService.updateOrderProducts(orderProducts);
        return Response.ok(orderProducts).build();
    }
    
     /**
     * Resource path =  //api/v1/orderProduct/{id}
     *
     * @param id
     * @return orderProducts
     */
    @Path("{id}")
    @GET
    public OrderProducts getOrderProducts(@PathParam("id") Long id){
        return orderProductsService.findOrderProductsById(id);
    }
    
     /**
     * Resource path =  //api/v1/orderProduct/{id}
     *
     * @return orderProductsList
     */
    @Path("list")
    @GET
    public List<OrderProducts> getOrderProducts(){
        //api/v1/orderProduct/list
        return orderProductsService.getOrderProducts();
    }
}
