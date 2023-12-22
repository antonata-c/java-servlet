CREATE TABLE IF NOT EXISTS users
(
    id         serial primary key,
    first_name varchar,
    last_name  varchar,
    login      varchar not null,
    password   varchar not null
);

CREATE TABLE IF NOT EXISTS category
(
    id   serial primary key,
    name varchar not null
);

CREATE TABLE IF NOT EXISTS book
(
    id          serial primary key,
    title       varchar not null,
    category_id integer references category (id),
    author_id   integer references users (id)
);
