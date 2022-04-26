create schema if not exists Chat;
drop table if exists Chat.messages, Chat.users, Chat.rooms, Chat.users_in_rooms CASCADE;

create table if not exists Chat.users (
    id SERIAL,
    login varchar(128) NOT NULL,
    password varchar(128) NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists Chat.rooms (
    id SERIAL,
    name VARCHAR(128) NOT NULL,
    owner_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES Chat.users(id) ON DELETE CASCADE
);

create table if not exists Chat.messages (
    id SERIAL,
    author_id INT NOT NULL,
    room_id INT NOT NULL,
    text VARCHAR(128) NOT NULL,
    time TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES Chat.users(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES Chat.rooms(id) ON DELETE CASCADE
);

create table if not exists Chat.users_in_rooms (
    room_id int NOT NULL,
    user_id int NOT NULL,

    PRIMARY KEY (room_id, user_id),
    FOREIGN KEY (room_id) REFERENCES Chat.users(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Chat.rooms(id) ON UPDATE CASCADE
);