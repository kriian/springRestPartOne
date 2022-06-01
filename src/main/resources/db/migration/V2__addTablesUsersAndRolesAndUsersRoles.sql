CREATE TABLE users
(
    id       bigserial,
    username varchar(30) NOT NULL UNIQUE,
    password varchar(80) NOT NULL,
    email    varchar(50) UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id serial,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    user_id bigint NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);