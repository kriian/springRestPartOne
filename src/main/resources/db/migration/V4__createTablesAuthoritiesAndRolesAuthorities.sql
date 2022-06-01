CREATE TABLE authorities
(
    id serial,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles_authorities
(
    role_id bigint NOT NULL,
    authority_id int NOT NULL,
    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles (id),
    FOREIGN KEY (authority_id) REFERENCES authorities (id)
);