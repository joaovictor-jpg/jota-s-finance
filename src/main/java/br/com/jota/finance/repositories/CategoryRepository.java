package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
