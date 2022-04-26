create schema if not exists chat00;
drop table if exists chat00.messages, chat00.users, chat00.rooms, chat00.users_in_rooms CASCADE;

create table if not exists chat00.users (
    id SERIAL,
    login varchar(128) NOT NULL,
    password varchar(128) NOT NULL,
    PRIMARY KEY (id)
);

create table if not exists chat00.rooms (
    id SERIAL,
    name VARCHAR(128) NOT NULL,
    owner_id INT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES chat00.users(id) ON DELETE CASCADE
);

create table if not exists chat00.messages (
    id SERIAL,
    author_id INT NOT NULL,
    room_id INT NOT NULL,
    text VARCHAR(128) NOT NULL,
    time TIMESTAMP NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES chat00.users(id) ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES chat00.rooms(id) ON DELETE CASCADE
);

create table if not exists chat00.users_in_rooms (
    room_id int NOT NULL,
    user_id int NOT NULL,

    PRIMARY KEY (room_id, user_id),
    FOREIGN KEY (room_id) REFERENCES chat00.users(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES chat00.rooms(id) ON UPDATE CASCADE
);