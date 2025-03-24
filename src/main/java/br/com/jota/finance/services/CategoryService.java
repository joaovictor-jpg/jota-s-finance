package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.categoryDTOs.CategoryDetails;
import br.com.jota.finance.DTOs.categoryDTOs.DataCategory;
import br.com.jota.finance.entities.Category;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDetails saveCategory(DataCategory dataCategory, User user) {
        Category category = new Category(dataCategory.name(), dataCategory.type(), user);
        categoryRepository.save(category);
        return new CategoryDetails(category);
    }

    public List<CategoryDetails> listByUser(User user) {
        List<Category> categories = categoryRepository.findByUser(user);
        var categoriesDatails = categories.stream().map(CategoryDetails::new).toList();
        return categoriesDatails;
    }
}
