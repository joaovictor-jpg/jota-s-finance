CREATE TABLE bank_accounts (
    id_bank_account BIGSERIAL PRIMARY KEY,
    name_account VARCHAR(50) NOT NULL,
    type_account VARCHAR(100) NOT NULL,
    opening_balance NUMERIC(10,2) NOT NULL DEFAULT 0,
    id_user BIGINT NOT NULL,
    CONSTRAINT fk_bank_accounts_user FOREIGN KEY(id_user) REFERENCES users(id)
);