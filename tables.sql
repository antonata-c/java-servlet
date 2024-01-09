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

CREATE TABLE IF NOT EXISTS library_card
(
    id          serial primary key,
    userid      integer references users (id),
    issue_date  date default current_date,
    expiry_date date default current_date + interval '1 year'
    );

CREATE TABLE IF NOT EXISTS follow
(
    id           serial primary key,
    follower_id  integer references users (id),
    following_id integer references users (id),
    start_date   date default current_date
    );