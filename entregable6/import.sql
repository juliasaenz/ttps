use buffet;
SET FOREIGN_KEY_CHECKS = 0;

-- Truncate all relevant tables
TRUNCATE TABLE usuarios;
TRUNCATE TABLE clientes;
TRUNCATE TABLE responsables;
TRUNCATE TABLE administradores;
TRUNCATE TABLE cartas;
TRUNCATE TABLE menu;
TRUNCATE TABLE comidas;
TRUNCATE TABLE menu_comida;

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;

-- Insert data into comidas table
INSERT INTO comidas (id, nombre, tipo, vegetariano) VALUES 
(1, 'Pizza', 'PLATO_PRINCIPAL', true),
(2, 'Hamburguesa', 'PLATO_PRINCIPAL', false),
(3, 'Ensalada', 'ENTRADA', true),
(4, 'Gaseosa', 'BEBIDA', false),
(5, 'Fruta', 'POSTRE', true);

-- Insert data into menus table
INSERT INTO menu (id, precio) VALUES 
(1, 500), 
(2, 500), 
(3, 500);

-- Insert data into menu_comida (join table for menu-comida relationship)
INSERT INTO menu_comida (menu_id, comida_id) VALUES 
-- Menu 1
(1, 1), -- Pizza
(1, 3), -- Ensalada
(1, 4), -- Gaseosa
-- Menu 2 (Veggie menu)
(2, 2), -- Hamburguesa
(2, 5), -- Fruta
-- Menu 3
(3, 1), -- Pizza
(3, 3), -- Ensalada
(3, 4), -- Gaseosa
(3, 5); -- Fruta

-- Insert data into cartas table
INSERT INTO cartas (id, dia, menu_id, menu_veggie_id) VALUES 
(1, CURRENT_DATE(), 1, 2);
