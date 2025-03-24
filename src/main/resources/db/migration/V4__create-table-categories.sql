CREATE TABLE categories(
    id_category BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(10) NOT NULL CHECK(type IN ('EXPENSE', 'INCOME')),
    id_user BIGINT NOT NULL,
    CONSTRAINT fk_categories_user FOREIGN KEY(id_user) REFERENCES users(id)
);