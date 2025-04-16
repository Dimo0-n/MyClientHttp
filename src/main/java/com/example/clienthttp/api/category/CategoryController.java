package com.example.clienthttp.api.category;

import com.example.clienthttp.entity.Category;
import com.example.clienthttp.api.category.ApiCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final ApiCategoryService apiCategoryService;

    public CategoryController(ApiCategoryService apiCategoryService) {
        this.apiCategoryService = apiCategoryService;
    }

    @GetMapping("")
    public String listCategories(Model model) {
        model.addAttribute("categories", apiCategoryService.getAllCategories());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "add-category";
    }

    @PostMapping("/newCategory")
    public String createCategory(@ModelAttribute Category category) {
        apiCategoryService.createCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String viewCategory(@PathVariable Long id, Model model) {
        Category category = apiCategoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "view-category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = apiCategoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit-category";
    }

    @PutMapping("/edit/{id}")
    public String updateCategory(@PathVariable int id, @ModelAttribute Category category) {
        apiCategoryService.updateCategory(id, category);
        return "redirect:/categories/" + id;
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        apiCategoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}