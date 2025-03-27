package br.com.jota.finance.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "Budget")
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBudget;
    @Column(name = "value_budget")
    private BigDecimal valueBudget;
    @Column(name = "month_year")
    private LocalDate monthYear;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Budget() {
    }

    public Budget(BigDecimal valueBudget, LocalDate monthYear, Category category, User user) {
        this.valueBudget = valueBudget;
        this.monthYear = monthYear;
        this.category = category;
        this.user = user;
    }

    public Long getIdBudget() {
        return idBudget;
    }

    public BigDecimal getValueBudget() {
        return valueBudget;
    }

    public void setValueBudget(BigDecimal valueBudget) {
        this.valueBudget = valueBudget;
    }

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public Category getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(idBudget, budget.idBudget);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idBudget);
    }
}
