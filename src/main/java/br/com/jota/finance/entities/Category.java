package br.com.jota.finance.entities;

import br.com.jota.finance.entities.enums.TypeCategoryEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Category")
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String name;
    @Enumerated(EnumType.STRING)
    private TypeCategoryEnum type;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Category() {
    }

    public Category(String nome, TypeCategoryEnum type, User user) {
        this.name = nome;
        this.type = type;
        this.user = user;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    public TypeCategoryEnum getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(idCategory, category.idCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCategory);
    }
}
