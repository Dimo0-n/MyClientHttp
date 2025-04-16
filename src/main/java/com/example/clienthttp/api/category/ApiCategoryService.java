package com.example.clienthttp.api.category;

import com.example.clienthttp.entity.Category;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiCategoryService {
    private final String SERVER_URL = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public ApiCategoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Category> getAllCategories() {
        String url = SERVER_URL + "/categories";
        ResponseEntity<List<Category>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Category>>() {}
        );
        return response.getBody();
    }

    public Category getCategoryById(Long id) {
        String url = SERVER_URL + "/categories/" + id;
        return restTemplate.getForObject(url, Category.class);
    }

    public Category createCategory(Category category) {
        String url = SERVER_URL + "/categories/newCategory";
        return restTemplate.postForObject(url, category, Category.class);
    }

    public Category updateCategory(int id, Category category) {
        String url = SERVER_URL + "/categories/edit/" + id;
        HttpEntity<Category> requestEntity = new HttpEntity<>(category);
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                Category.class
        ).getBody();
    }

    public void deleteCategory(Long id) {
        String url = SERVER_URL + "/categories/delete/" + id;
        restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
