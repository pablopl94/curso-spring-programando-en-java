CREATE SCHEMA IF NOT EXISTS ecommerce;

-- Tabla de roles
CREATE TABLE IF NOT EXISTS ecommerce.roles
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS ecommerce.product
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100)   NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER        NOT NULL
);

-- Tabla de pedidos
CREATE TABLE IF NOT EXISTS ecommerce.orders
(
    id             SERIAL PRIMARY KEY,
    total_amount   DECIMAL(10, 2) NOT NULL,
    processor_name VARCHAR(50),
    customer_name  VARCHAR(100),
    customer_email VARCHAR(100),
    created_at     DATE                    DEFAULT CURRENT_DATE,
    version        INTEGER        NOT NULL DEFAULT 1
);

-- Tabla de items de pedido
CREATE TABLE IF NOT EXISTS ecommerce.order_item
(
    id         SERIAL PRIMARY KEY,
    quantity   INT            NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    order_id   INTEGER        NOT NULL,
    product_id INTEGER        NOT NULL,
    FOREIGN KEY (order_id) REFERENCES ecommerce.orders (id),
    FOREIGN KEY (product_id) REFERENCES ecommerce.product (id)
);

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS ecommerce.users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(254) UNIQUE,
    name     VARCHAR(50) NOT NULL,
    password VARCHAR(60) NOT NULL,
    role_id  INTEGER     NOT NULL,
    FOREIGN KEY (role_id) REFERENCES ecommerce.roles (id)
);