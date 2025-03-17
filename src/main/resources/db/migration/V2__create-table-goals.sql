CREATE TABLE goals (
    id_goals BIGSERIAL PRIMARY KEY,
    description VARCHAR(150) NOT NULL,
    target_value NUMERIC(10, 2) NOT NULL DEFAULT 0,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    id_user BIGINT NOT NULL,
    CONSTRAINT fk_goal_user FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT check_dates CHECK (end_date >= start_date)
);