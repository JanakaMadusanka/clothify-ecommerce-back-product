package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.Product;
import org.example.entity.ProductEntity;
import org.example.reopsitory.ProductRepository;
import org.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    final ProductRepository repository;
    ModelMapper mapper;

    @Bean
    public void setup(){
        this.mapper = new ModelMapper();
    }

    @Override
    public void addProduct(Product product) {
        ProductEntity entity = mapper.map(product, ProductEntity.class);
        repository.save(entity);
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity existingUser = repository.findById(product.getId()).orElse(null);
        if(existingUser !=null){
            repository.save(mapper.map(product,ProductEntity.class));
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Product> getAllProduct() {
        List<ProductEntity> entityList = (List<ProductEntity>) repository.findAll();
        List<Product> productList = new ArrayList<>();

        for(ProductEntity entity : entityList){
            productList.add(mapper.map(entity,Product.class));
        }
        return productList;
    }

    @Override
    public Product searchProductById(Long id) {
        return mapper.map(repository.findById(id),Product.class);
    }
}
