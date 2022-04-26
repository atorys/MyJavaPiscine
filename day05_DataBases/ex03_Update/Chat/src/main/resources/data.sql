INSERT INTO Chat.users (login, password) VALUES ('atory', 'pass');
INSERT INTO Chat.users (login, password) VALUES ('admin', 'admin');

INSERT INTO Chat.rooms (owner_id, name) VALUES (1, 'chatik');

INSERT INTO Chat.messages (author_id, room_id, text)
VALUES (1, 1, 'hello');
INSERT INTO Chat.messages (author_id, room_id, text)
VALUES (2, 1, 'darkness');
INSERT INTO Chat.messages (author_id, room_id, text)
VALUES (2, 1, 'my');
INSERT INTO Chat.messages (author_id, room_id, text)
VALUES (2, 1, 'dear');
INSERT INTO Chat.messages (author_id, room_id, text)
VALUES (1, 1, 'friend');


INSERT INTO Chat.users_in_rooms SELECT Chat.rooms.id, Chat.rooms.owner_id FROM Chat.rooms;