package br.com.jota.finance.entities;

import br.com.jota.finance.entities.enums.TypeTransaction;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Transaction")
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;
    private String description;
    private BigDecimal transactionValue;
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private TypeTransaction type;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "id_bank_account")
    private BankAccount bankAccount;

    public Transaction() {
    }

    public Transaction(String description, BigDecimal transactionValue, LocalDateTime transactionDate, TypeTransaction type, User user, Category category, BankAccount bankAccount) {
        this.description = description;
        this.transactionValue = transactionValue;
        this.transactionDate = transactionDate;
        this.type = type;
        this.user = user;
        this.category = category;
        this.bankAccount = bankAccount;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public TypeTransaction getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public Category getCategory() {
        return category;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Transaction that = (Transaction) object;
        return Objects.equals(idTransaction, that.idTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idTransaction);
    }
}
