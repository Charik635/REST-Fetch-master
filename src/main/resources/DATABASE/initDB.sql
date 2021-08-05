CREATE DATABASE IF NOT EXISTS bootstrap312;
use bootstrap312;
# CREATE TABLE users
# (
#     id         BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
#     login VARCHAR(100)                      NOT NULL unique,
#     first_name VARCHAR(100)                      NOT NULL ,
#     last_name  VARCHAR(100)                      NOT NULL,
#     age        int(3)                            not null,
#     email      VARCHAR(100)                      NOT NULL unique,
#     password   VARCHAR(100)                      NOT NULL
# )
#     COLLATE = 'utf8_general_ci';
#
# -- Table: roles
# CREATE TABLE roles
# (
#     id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
#     role VARCHAR(255) NOT NULL
# )
#     ENGINE = InnoDB;
#
#
# -- Table for mapping user and roles: user_roles
# CREATE TABLE user_roles
# (
#     user_id BIGINT NOT NULL,
#     role_id BIGINT NOT NULL,
#
#     FOREIGN KEY (user_id) REFERENCES users (id),
#     FOREIGN KEY (role_id) REFERENCES roles (id),
#
#     UNIQUE (user_id, role_id)
# )
#     ENGINE = InnoDB;


-- Insert data
INSERT INTO users
VALUES (1, 25, 'admin@ya.ru', 'Neo', 'Smith', 'admin', '111'),
       (2, 48, 'user@ya.ru', 'James', 'Bond', 'user', '111'),
       (3, 39, 'cap@ya.ru', 'Java', 'Developer', 'useradmin', '111');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO users_role
VALUES (1, 2);
INSERT INTO users_role
VALUES (2, 1);
INSERT INTO users_role
VALUES (3, 1);
INSERT INTO users_role
VALUES (3, 2);
