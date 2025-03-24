package br.com.jota.finance.DTOs.categoryDTOs;

import br.com.jota.finance.entities.Category;
import org.springframework.security.core.userdetails.UserDetails;

public record CategoryDetails(
        Long id,
        String name,
        String type
) {
    public CategoryDetails(Category category) {
        this(category.getIdCategory(), category.getName(), category.getType().name());
    }
}
