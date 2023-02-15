package com.webtutsplus.ecommerce.controller;

import com.webtutsplus.ecommerce.common.ApiResponse;
import com.webtutsplus.ecommerce.model.Category;
import com.webtutsplus.ecommerce.service.CategoryService;
import com.webtutsplus.ecommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
		if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))) {
			return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
		}
		categoryService.createCategory(category);
		return new ResponseEntity<>(new ApiResponse(true, "created the category"), HttpStatus.CREATED);
	}

    @PostMapping("/update/{categoryID}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryID") Integer categoryID, @Valid @RequestBody Category category) {
        if (Helper.notNull(categoryService.readCategory(categoryID))) {
            categoryService.updateCategory(categoryID, category);
            return new ResponseEntity<>(new ApiResponse(true, "updated the category"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}
