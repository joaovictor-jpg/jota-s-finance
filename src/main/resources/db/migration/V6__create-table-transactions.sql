CREATE TABLE transactions(
    id_transaction BIGSERIAL PRIMARY KEY,
    description VARCHAR(150) NOT NULL,
    transaction_value NUMERIC(10,2) NOT NULL CHECK (transaction_value != 0),
    transaction_date TIMESTAMP NOT NULL CHECK (transaction_date <= CURRENT_TIMESTAMP),
    type VARCHAR(20) NOT NULL CHECK (type IN ('INCOME', 'EXPENSE', 'TRANSFER')),
    id_user BIGINT NOT NULL,
    id_category BIGINT NOT NULL,
    id_bank_account BIGINT NOT NULL,
    CONSTRAINT fk_transactions_user FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT fk_transactions_category FOREIGN KEY (id_category) REFERENCES categories(id_category),
    CONSTRAINT fk_transactions_bank_account FOREIGN KEY (id_bank_account) REFERENCES bank_accounts(id_bank_account)
);