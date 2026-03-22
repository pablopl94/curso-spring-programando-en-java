-- Índices para optimizar búsquedas
CREATE INDEX IF NOT EXISTS idx_orders_created_at ON ecommerce.orders (created_at);
CREATE INDEX IF NOT EXISTS idx_orders_total_amount ON ecommerce.orders (total_amount);
CREATE INDEX IF NOT EXISTS idx_product_name ON ecommerce.product (name);
CREATE INDEX IF NOT EXISTS idx_order_item_order_id ON ecommerce.order_item (order_id);
CREATE INDEX IF NOT EXISTS idx_order_item_product_id ON ecommerce.order_item (product_id);
CREATE INDEX IF NOT EXISTS idx_users_roles_id ON ecommerce.users (role_id);