package br.com.jota.finance.entities;

import br.com.jota.finance.entities.enums.TypeAccount;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "BankAccount")
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBankAccount;
    private String nameAccount;
    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;
    private BigDecimal openingBalance;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public BankAccount() {
    }

    public BankAccount(String nameAccount, TypeAccount typeAccount, BigDecimal openingBalance, User user) {
        this.nameAccount = nameAccount;
        this.typeAccount = typeAccount;
        this.openingBalance = openingBalance;
        this.user = user;
    }

    public Long getIdBankAccount() {
        return idBankAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    public BigDecimal getOpeningBalance() {
        return openingBalance;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(idBankAccount, that.idBankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idBankAccount);
    }
}
