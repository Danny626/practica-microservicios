INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('andres', '$2a$10$Ngq1l81DgpF2mwEekIeqE.0oVqaiQXYTgohH5S4eEZwIGnEjyrLgm', true, 'Andres', 'Guzman', 'profesor@bolsadeideas.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('admin', '$2a$10$7Uhtmkm5ZDTrg2UroXjWT.y/dMEmnBSppTmH7e.xi7FlTgZek31Eu', true, 'Jhon', 'Doe', 'jhon.doe@bolsadeideas.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);