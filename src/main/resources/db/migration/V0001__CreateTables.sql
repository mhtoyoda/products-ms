CREATE TABLE brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    enabled BOOLEAN NOT NULL,
    brand_id INT REFERENCES brand(id),
    category_id INT REFERENCES category(id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ
);

INSERT INTO brand (name, enabled) VALUES
    ('Sony', true),
    ('Microsoft', true);

INSERT INTO category (name, enabled) VALUES
    ('Vídeo game', true),
    ('Fone de ouvido', true);

INSERT INTO product (name, quantity, price, enabled, brand_id, category_id) VALUES
    ('Playstation', 100, 19.99, true, 1, 1),
    ('Fone Gamer', 50, 29.99, true, 1, 2),
    ('Xbox', 50, 29.99, true, 2, 1),
    ('Mouse gamer', 75, 9.99, true, 2, 2);
