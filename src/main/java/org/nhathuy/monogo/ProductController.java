package org.nhathuy.monogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<String> getApi(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> listProducts= productService.getAllProducts();
        return ResponseEntity.ok(listProducts);
    }
    @GetMapping("products/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable String id){
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
    // Xóa sản phẩm
    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
