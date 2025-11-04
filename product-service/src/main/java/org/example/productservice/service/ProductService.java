package org.example.productservice.service;

import lombok.RequiredArgsConstructor;
import org.example.productservice.models.Product;
import org.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    public Product updateProduct(Integer id, Product product) {

        Product existingProduct = getProductById(id);


        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setWeight(product.getWeight());


        return productRepository.save(existingProduct);
    }


}
