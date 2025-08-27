CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    age INTEGER
);
INSERT INTO users (first_name, last_name, email, password, age) VALUES
('Ivan', 'Ivanov','ivanov@example.com', 'password', 30)
ON CONFLICT DO NOTHING
