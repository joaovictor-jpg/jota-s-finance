package br.com.jota.finance.controller;

import br.com.jota.finance.DTOs.categoryDTOs.CategoryDetails;
import br.com.jota.finance.DTOs.categoryDTOs.DataCategory;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDetails> save(@Valid @RequestBody DataCategory dataCategory, @AuthenticationPrincipal User user, UriComponentsBuilder uriComponentsBuilder) {
        var category = categoryService.saveCategory(dataCategory, user);
        var uri = uriComponentsBuilder.path("/categories/id").buildAndExpand(category.id()).toUri();
        return ResponseEntity.created(uri).body(category);
    }
}
