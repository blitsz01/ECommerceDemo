package com.ecommerce.resource;

import com.ecommerce.config.Secure;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
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

@Path("product")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    @Inject
    ProductService productService;
    
    @Inject
    private Logger logger;
    /**
     * Resource path =  //api/v1/product/new
     *
     * @param product
     * @return product
     */
    @Path("new")
    @POST
    @Secure
    public Response createProduct(Product product){
        productService.createProduct(product);
        return Response.ok(product).build();
    }
    
    /**
     * Resource path =  //api/v1/product/update
     *
     * @param product
     * @return product
     */
    @Path("update")
    @PUT
    @Secure
    public Response updateProduct(Product product){
        Product productCurrent = new Product();
        productCurrent = productService.findProductById(product.getId());
        product.setImage(productCurrent.getImage());
        productService.updateProduct(product);
        return Response.ok(product).build();
    }
    
    /**
     * Resource path =  //api/v1/product/{id}
     *
     * @param id
     * @return product
     */
    @Path("{id}")
    @GET
    public Response getProduct(@PathParam("id") Long id){
        Product product = new Product();
        product = productService.findProductById(id);
        if(product != null){
            product.setImage(null);
            return Response.ok(product).status(Response.Status.OK).build();
        }    
        return Response.ok(product).status(Response.Status.NOT_FOUND).build();
    }
    
    /**
     * Resource path =  //api/v1/product/list
     *
     * @return productList
     */
    @Path("list")
    @GET
    public Response getProductList(){
        List<Product> products= productService.getProducts();
        List<Product> productToSend = new ArrayList<>();
        for(Product product : products){
            product.setImage(null);
            productToSend.add(product);
        }
        return Response.ok(productToSend).build();
    }
    
    /**
     * Resource path =  //api/v1/product/search?search
     *
     * @return productList
     */
    @Path("search")
    @GET
    public Response searchProductList(@QueryParam("search") @NotNull String search){
        List<Product> products= productService.searchProducts(search);
        List<Product> productToSend = new ArrayList<>();
        for(Product product : products){
            product.setImage(null);
            productToSend.add(product);
        }
        return Response.ok(productToSend).build();
    }
    
    /**
     * Resource path =  //api/v1/product/search?search
     *
     * @return productList
     */
    @Path("category")
    @GET
    public Response searchProductByCategory(@QueryParam("search") @NotNull String search){
        List<Product> products= productService.searchProducts(search);
        List<Product> productToSend = new ArrayList<>();
        for(Product product : products){
            product.setImage(null);
            productToSend.add(product);
        }
        return Response.ok(productToSend).build();
    }
    
    @POST
    @Path("upload")
    @Consumes({MediaType.APPLICATION_OCTET_STREAM, "image/png", "image/jpeg", "image/jpg"})
    @Produces(MediaType.TEXT_PLAIN)
    @Secure
    public Response uploadImage(File picture, @QueryParam("id") @NotNull Long id) {

        Product product = productService.findProductById(id);

        try (Reader reader = new FileReader(picture)) {
            if (product != null) {
                product.setImage(Files.readAllBytes(Paths.get(picture.toURI())));
                productService.updateProduct(product);
           int totalsize = 0;
            int count = 0;
            final char[] buffer = new char[256];
            while ((count = reader.read(buffer)) != -1) {
                totalsize += count;
            } 
            return Response.ok(totalsize).build(); 
           
            }
        } catch (IOException e) {
            return Response.serverError().build();
        }
        return Response.serverError().build();
    }
    
    @GET
    @Path("download") //employees/download?id=9
    @Produces({MediaType.TEXT_PLAIN})
    public Response getProductImage(@QueryParam("id") @NotNull Long id) throws IOException {

        Product product = productService.findProductById(id);
        if (product != null) {
            return Response.ok().entity(product.getImage()).build();
        }

        return Response.noContent().build();
    }

}
