package br.com.jota.finance.entities.enums;

public enum TypeCategoryEnum {
    EXPENSE("DESPESA"),
    INCOME("RENDA");

    private String nameCategory;

    TypeCategoryEnum(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getTypeCategory() {
        return nameCategory;
    }
}
