package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.Product;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductService service;
    @PostMapping("/add")
    public void addProduct(@RequestBody Product product){
        service.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        if(service.updateProduct(product)){
            return "Updated";
        }
        return "Product doesn't exist";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (service.deleteProduct(id)) {
            return "Deleted";
        }
        return "Product doesn't exist";
    }

    @GetMapping("/get/all")
    public List<Product> getAllProduct(){
        return service.getAllProduct();
    }

    @GetMapping("/search/{id}")
    public Product searchProduct(@PathVariable Long id){
        return service.searchProductById(id);
    }
}
