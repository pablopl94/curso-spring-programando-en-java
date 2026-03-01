-- PRODUCTOS (ids del 1 al 20)
INSERT INTO product (name, price) VALUES ('Teclado', 29.99);
INSERT INTO product (name, price) VALUES ('Ratón', 15.5);
INSERT INTO product (name, price) VALUES ('Monitor', 199.99);
INSERT INTO product (name, price) VALUES ('Auriculares', 49.99);
INSERT INTO product (name, price) VALUES ('Webcam', 89.99);
INSERT INTO product (name, price) VALUES ('Silla Gaming', 299.99);
INSERT INTO product (name, price) VALUES ('Alfombrilla', 12.99);
INSERT INTO product (name, price) VALUES ('Hub USB', 24.99);
INSERT INTO product (name, price) VALUES ('Micrófono', 79.99);
INSERT INTO product (name, price) VALUES ('Cámara Web', 59.99);
INSERT INTO product (name, price) VALUES ('Disco SSD', 149.99);
INSERT INTO product (name, price) VALUES ('Memoria RAM', 89.99);
INSERT INTO product (name, price) VALUES ('Tarjeta Gráfica', 599.99);
INSERT INTO product (name, price) VALUES ('Procesador', 399.99);
INSERT INTO product (name, price) VALUES ('Placa Base', 249.99);
INSERT INTO product (name, price) VALUES ('Fuente Alimentación', 99.99);
INSERT INTO product (name, price) VALUES ('Caja PC', 129.99);
INSERT INTO product (name, price) VALUES ('Refrigeración', 49.99);
INSERT INTO product (name, price) VALUES ('Pasta Térmica', 9.99);
INSERT INTO product (name, price) VALUES ('Cable HDMI', 14.99);

-- ÓRDENES (ids del 1 al 20)
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (258.47, 'Stripe', 'Carlos García', 'carlos@gmail.com', '2026-01-05');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (459.95, 'PayPal', 'María López', 'maria@gmail.com', '2026-01-08');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (774.96, 'PayPal', 'Pedro Martínez', 'pedro@gmail.com', '2026-01-10');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (862.97, 'Stripe', 'Ana Rodríguez', 'ana@gmail.com', '2026-01-12');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (314.97, 'PayPal', 'Luis Sánchez', 'luis@gmail.com', '2026-01-15');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (78.48, 'PayPal', 'Laura Gómez', 'laura@gmail.com', '2026-01-18');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (308.46, 'Stripe', 'Jorge Díaz', 'jorge@gmail.com', '2026-01-20');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (559.97, 'PayPal', 'Sara Fernández', 'sara@gmail.com', '2026-01-22');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (664.95, 'PayPal', 'Miguel Torres', 'miguel@gmail.com', '2026-01-25');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (849.98, 'Stripe', 'Elena Ruiz', 'elena@gmail.com', '2026-01-28');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (717.95, 'PayPal', 'Pablo Moreno', 'pablo@gmail.com', '2026-02-01');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (849.97, 'PayPal', 'Carmen Jiménez', 'carmen@gmail.com', '2026-02-03');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (414.96, 'Stripe', 'Antonio Álvarez', 'antonio@gmail.com', '2026-02-05');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (824.95, 'PayPal', 'Isabel Romero', 'isabel@gmail.com', '2026-02-08');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (105.49, 'PayPal', 'Francisco Navarro', 'francisco@gmail.com', '2026-02-10');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (429.96, 'Stripe', 'Lucía Serrano', 'lucia@gmail.com', '2026-02-12');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (139.98, 'PayPal', 'Manuel Molina', 'manuel@gmail.com', '2026-02-15');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (254.96, 'PayPal', 'Pilar Castro', 'pilar@gmail.com', '2026-02-18');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (404.95, 'Stripe', 'Javier Ortega', 'javier@gmail.com', '2026-02-20');
INSERT INTO orders (total_amount, processor_name, customer_name, customer_email, created_at) VALUES (265.47, 'PayPal', 'Rosa Delgado', 'rosa@gmail.com', '2026-02-25');

-- ORDER ITEMS
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (1, 9, 1, 79.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (1, 2, 1, 15.5);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (1, 11, 1, 149.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (1, 7, 3, 12.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (2, 5, 2, 89.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (2, 3, 3, 199.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (2, 1, 2, 29.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (2, 4, 1, 49.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (2, 12, 3, 89.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (3, 6, 1, 299.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (3, 20, 3, 14.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (3, 10, 1, 59.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (3, 14, 1, 399.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (4, 13, 1, 599.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (4, 15, 3, 249.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (4, 7, 3, 12.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (5, 3, 3, 199.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (5, 12, 2, 89.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (5, 8, 2, 24.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (6, 4, 1, 49.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (6, 2, 3, 15.5);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (6, 7, 3, 12.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (7, 7, 2, 12.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (7, 2, 3, 15.5);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (7, 12, 3, 89.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (7, 10, 1, 59.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (7, 17, 2, 129.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (8, 10, 1, 59.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (8, 16, 3, 99.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (8, 14, 1, 399.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (9, 15, 2, 249.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (9, 17, 3, 129.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (9, 3, 3, 199.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (9, 8, 1, 24.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (9, 10, 2, 59.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (10, 15, 3, 249.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (10, 13, 3, 599.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (11, 8, 1, 24.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (11, 6, 2, 299.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (11, 7, 3, 12.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (11, 17, 2, 129.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (11, 15, 1, 249.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (12, 3, 3, 199.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (12, 15, 3, 249.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (12, 14, 1, 399.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (13, 15, 3, 249.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (13, 4, 2, 49.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (13, 20, 2, 14.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (13, 16, 2, 99.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (14, 10, 3, 59.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (14, 8, 3, 24.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (14, 14, 2, 399.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (14, 15, 2, 249.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (14, 5, 1, 89.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (15, 2, 1, 15.5);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (15, 5, 2, 89.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (16, 20, 3, 14.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (16, 6, 3, 299.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (16, 8, 1, 24.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (16, 12, 1, 89.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (17, 5, 1, 89.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (17, 18, 2, 49.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (18, 20, 3, 14.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (18, 16, 2, 99.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (18, 18, 3, 49.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (18, 12, 2, 89.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (19, 12, 2, 89.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (19, 8, 1, 24.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (19, 18, 2, 49.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (19, 11, 3, 149.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (19, 5, 3, 89.99);

INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (20, 19, 2, 9.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (20, 5, 3, 89.99);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (20, 2, 1, 15.5);
INSERT INTO order_item (order_id, product_id, quantity, unit_price) VALUES (20, 11, 1, 149.99);