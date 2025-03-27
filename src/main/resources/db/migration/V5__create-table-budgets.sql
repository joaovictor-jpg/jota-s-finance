CREATE TABLE budgets(
    id_budget BIGSERIAL PRIMARY KEY,
    value_budget NUMERIC(10,2) NOT NULL CHECK (value_budget >= 0),
    month_year DATE NOT NULL,
    id_category BIGINT NOT NULL,
    id_user BIGINT NOT NULL,
    CONSTRAINT fk_budgets_categories FOREIGN KEY (id_category) REFERENCES categories(id_category),
    CONSTRAINT fk_budgets_users FOREIGN KEY (id_user) REFERENCES  users(id),
    CONSTRAINT uk_budgets_user_categories_month UNIQUE (id_user, id_category, month_year)
);