package com.dbn.onlineshopping.service;
import com.dbn.onlineshopping.domain.response.ProductFrequentResponse;
import com.dbn.onlineshopping.domain.response.ProductPopularResponse;
import com.dbn.onlineshopping.domain.response.ProductProfitRespose;
import com.dbn.onlineshopping.domain.response.ProductRecentResponse;
import com.dbn.onlineshopping.dao.ProductDao;

import com.dbn.onlineshopping.domain.entity.Product;
import com.dbn.onlineshopping.domain.entity.ProductResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {

        this.productDao =productDao;

    }
    public List<ProductResult> getAllInStockProducts0(){
        return productDao.getAllInStockProducts0();
    }
    public ProductResult GetProductDetailById(int id){
        return  productDao.GetProductDetailById(id);
    }
    public Product GetProductDetailById2(int id){
        return productDao.GetProductDetailById2(id);
    }
    public List<String> getAllProducts(){
        return productDao.getAllProducts();
    }
    public List<ProductResult> getAllProduct(){
        return productDao.getAllProduct();
    }
    public void addProduct(Product product){
        productDao.addProduct(product);
    }

    public void updateProductById(int id,Product updateProduct){
        productDao.updateProductById(id,updateProduct);
    }

    public List<ProductFrequentResponse> GetMostFrequentlyPurchasedProduct(int num){
       return productDao.GetMostFrequentlyPurchasedProduct(num);
    }
    public List<ProductRecentResponse> GetMostRecentlyPurchasedProduct(int num){
        return productDao.GetMostRecentlyPurchasedProduct(num);
    }

    public List<ProductProfitRespose> GetMostProfitableProduct(int num){
        return productDao.GetMostProfitableProduct(num);
    }

    public List<ProductPopularResponse> GetMostPopularPurchasedProduct(int num){
        return productDao.GetMostPopularPurchasedProduct(num);
    }
}
