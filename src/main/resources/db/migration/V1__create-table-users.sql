CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(75) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT true,
    role VARCHAR(20) NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP,
    delete_date TIMESTAMP
);