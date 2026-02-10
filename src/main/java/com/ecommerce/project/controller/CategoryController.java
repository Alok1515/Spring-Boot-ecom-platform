package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    //@RequestMapping(value = "/api/public/categories", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

   @PostMapping("/api/public/categories")
   //@RequestMapping(value = "/api/public/categories", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
            CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
            return  new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long categoryId) {
            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
       return  new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
