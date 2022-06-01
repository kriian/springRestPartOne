INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('user', '$2a$12$G0256CAEE4/K9cvcxdXEu.87jJmWhyJ11inmJ9jVx3H62BJfidC.W', 'user@mail.ru');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);