DROP TABLE users;

CREATE TABLE users
(
    id        serial       NOT NULL PRIMARY key,
    user_id   UUID         NOT NULL,
    username  VARCHAR(100) NOT NULL,
    password  VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email     VARCHAR(100) NOT NULL,
    phone     VARCHAR(100)
);