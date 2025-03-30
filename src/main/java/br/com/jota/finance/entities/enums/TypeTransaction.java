package br.com.jota.finance.entities.enums;

public enum TypeTransaction {
    INCOME("RENDA"),
    EXPENSE("DESPESA"),
    TRANSFER("TRANSFERIR");

    private String nameType;

    TypeTransaction(String nameType) {
        this.nameType = nameType;
    }

    public String getNameType() {
        return nameType;
    }
}
