package br.com.jota.finance.entities.enums;

public enum TypeAccount {
    CURRENT_ACCOUNT("Conta Corrente"),
    SAVINGS_ACCOUNT("Conta Poupança"),
    SALARY_ACCOUNT("Conta Salário"),
    UNIVERSITY_ACCOUNT("Conta Universitária"),
    JOINT_ACCOUNT("Conta Conjunta"),
    DIGITAL_ACCOUNT("Conta Digital"),
    PAYMENT_ACCOUNT("Conta de Pagamento"),
    INVESTMENT_ACCOUNT("Conta de Investimento"),
    INTERNATIONAL_ACCOUNT("Conta Internacional"),
    PRIVATE_PENSION_ACCOUNT("Conta de Previdência Privada"),
    FOREIGN_EXCHANGE_ACCOUNT("Conta de Câmbio"),
    PREPAID_PAYMENT_ACCOUNT("Conta de Pagamento Pré-paga"),
    TERM_DEPOSIT_ACCOUNT("Conta de Depósito a Prazo"),
    BUSINESS_ACCOUNT("Conta de Negócios"),
    BENEFITS_ACCOUNT("Conta de Benefícios"),
    SERVICE_PAYMENT_ACCOUNT("Conta de Pagamento de Serviços"),
    INVESTMENT_FOUND_ACCOUNT("Conta de Fundos de Investimento"),
    TREASURY_DIRECT_ACCOUNT("Conta de Tesouro Direto"),
    CRYPTOCURRENCY_ACCOUNT("Conta de Criptomoedas"),
    DONATIONS_ACCOUNT("Conta de Doações");

    private String nameAccount;

    TypeAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getNameAccount() {
        return nameAccount;
    }
}
