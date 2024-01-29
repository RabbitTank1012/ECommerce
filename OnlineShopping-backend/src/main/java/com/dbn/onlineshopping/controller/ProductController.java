package com.dbn.onlineshopping.controller;
import com.dbn.onlineshopping.domain.entity.Product;
import com.dbn.onlineshopping.domain.entity.ProductResult;
import com.dbn.onlineshopping.domain.response.*;
import com.dbn.onlineshopping.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    private String role = "user";
    private String username;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(("/all"))
    public List<ProductResult> getAllProducts() {

        System.out.println("request product");
        return productService.getAllProduct();
    }

    @GetMapping(("/{id}"))
    public ProductResult GetProductDetailById(@PathVariable int id){
        ProductResult product = productService.GetProductDetailById(id);
        return product;

    }


    @PostMapping()
    public ProductResponse saveProductSuccess(@RequestBody Product product){
        System.out.println("Create a product:");
        productService.addProduct(product);
        return ProductResponse.builder().product(product)
                .build();
    }


    @CrossOrigin("http://localhost:4200")
    @PatchMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable int id , @RequestBody Product product) {
        System.out.println("Update a product:"+ id);
        productService.updateProductById(id,product);
        Product updateProduct = productService.GetProductDetailById2(id);
        return ProductResponse.builder()
                .product(updateProduct)
                .build();
    }

    @GetMapping(("/frequent/{id}"))
    public List<ProductFrequentResponse> MostFrequentlyPurchasedProduct(@PathVariable int id){

        List<ProductFrequentResponse> products = productService.GetMostFrequentlyPurchasedProduct(id);

        return products;

    }

    @GetMapping(("/recent/{id}"))
    public List<ProductRecentResponse> MostRecentlyPurchasedProduct(@PathVariable int id){

        List<ProductRecentResponse> products = productService.GetMostRecentlyPurchasedProduct(id);

        return products;

    }

    @GetMapping(("/profit/{id}"))
    public List<ProductProfitRespose> MostProfitPurchasedProduct(@PathVariable int id){

        List<ProductProfitRespose> products = productService.GetMostProfitableProduct(id);
        return products;
    }

    @GetMapping(("/popular/{id}"))
    public List<ProductPopularResponse> MostPopularPurchasedProduct(@PathVariable int id){

        List<ProductPopularResponse> products = productService.GetMostPopularPurchasedProduct(id);
        return products;
    }

    public void getUserinfo(){
        Object[] obj = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray();
        for (int i =0  ;i <  obj.length; i++){
            this.role =  obj[i].toString();
            System.out.println("role =" + obj[i]);
        }
        this.username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("username=" + username);
        System.out.println("role=" + role);

    }


}
