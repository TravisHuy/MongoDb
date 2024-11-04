package org.nhathuy.monogo.service;

import org.nhathuy.monogo.model.Product;
import org.nhathuy.monogo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id){
        return productRepository.findById(id);
    }

    public Product updateProduct(String id, Product productDetails){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Product existProduct = product.get();
            existProduct.setName(productDetails.getName());
            existProduct.setDescription(productDetails.getDescription());
            existProduct.setPrice(productDetails.getPrice());

            return productRepository.save(existProduct);
        }
        return null;
    }

    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }

}
