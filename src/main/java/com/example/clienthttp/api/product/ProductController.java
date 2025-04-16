package com.example.clienthttp.api.product;

import com.example.clienthttp.entity.Product;
import com.example.clienthttp.api.product.ApiProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ApiProductService apiProductService;

    public ProductController(ApiProductService apiProductService) {
        this.apiProductService = apiProductService;
    }

    @GetMapping("/newProduct/{categoryId}")
    public String showAddProductForm(@PathVariable Long categoryId, Model model) {
        Product product = new Product();
        product.setCategory_id(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("categoryId", categoryId);
        return "add-product";
    }

    @PostMapping("/add/{categoryId}")
    public String createProduct(@PathVariable Long categoryId, @ModelAttribute Product product) {
        apiProductService.createProduct(product, categoryId);
        return "redirect:/categories/" + categoryId;
    }

    @DeleteMapping("/delete/{id}") // Schimbă din @PostMapping
    public String deleteProduct(@PathVariable Long id) {
        Product product = apiProductService.getProductById(id);
        apiProductService.deleteProduct(id);
        return "redirect:/categories/" + product.getCategory_id();
    }
}