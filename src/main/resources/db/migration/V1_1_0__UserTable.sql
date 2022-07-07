CREATE TABLE users(
    id serial NOT NULL PRIMARY key,
    user_id UUID NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(100)
)