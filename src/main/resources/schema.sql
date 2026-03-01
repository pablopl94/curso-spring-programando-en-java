CREATE TABLE IF NOT EXISTS product (
    id        INTEGER  PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    price     DECIMAL(10,2) NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders (
    id               INTEGER  PRIMARY KEY AUTO_INCREMENT,
    total_amount     DECIMAL(10,2) NOT NULL,
    processor_name   VARCHAR(50),
    customer_name    VARCHAR(100),
    customer_email   VARCHAR(100),
    created_at       DATE DEFAULT CURRENT_DATE
    );

CREATE TABLE IF NOT EXISTS order_item (
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    order_id   INTEGER  NOT NULL REFERENCES orders(id),
    product_id INTEGER  NOT NULL REFERENCES product(id),
    quantity   INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL
    );

-- Índices para optimizar búsquedas
CREATE INDEX IF NOT EXISTS idx_orders_created_at ON orders(created_at);
CREATE INDEX IF NOT EXISTS idx_orders_total_amount ON orders(total_amount);
CREATE INDEX IF NOT EXISTS idx_product_name ON product(name);
CREATE INDEX IF NOT EXISTS idx_order_item_order_id ON order_item(order_id);
CREATE INDEX IF NOT EXISTS idx_order_item_product_id ON order_item(product_id);