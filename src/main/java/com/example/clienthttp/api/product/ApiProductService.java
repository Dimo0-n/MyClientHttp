package com.example.clienthttp.api.product;

import com.example.clienthttp.entity.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiProductService {
    private final String SERVER_URL = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public ApiProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProductById(Long id) {
        String url = SERVER_URL + "/products/" + id;
        return restTemplate.getForObject(url, Product.class);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        String url = SERVER_URL + "/products/getProducts/" + categoryId;
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {}
        );
        return response.getBody();
    }

    public Product createProduct(Product product, Long categoryId) {
        String url = SERVER_URL + "/products";
        product.setCategory_id(categoryId);
        return restTemplate.postForObject(url, product, Product.class);
    }

    public void deleteProduct(Long id) {
        String url = SERVER_URL + "/products/delete/" + id;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}