package br.com.jota.finance.repositories;

import br.com.jota.finance.entities.Category;
import br.com.jota.finance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
}
