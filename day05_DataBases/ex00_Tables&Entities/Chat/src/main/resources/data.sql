INSERT INTO chat00.users (login, password) VALUES ('atory', 'pass');
INSERT INTO chat00.users (login, password) VALUES ('atoryClone', '123');
INSERT INTO chat00.users (login, password) VALUES ('atoryForgotPass', 'whoispass');
INSERT INTO chat00.users (login, password) VALUES ('itsme', '123');
INSERT INTO chat00.users (login, password) VALUES ('admin', 'admin');

INSERT INTO chat00.rooms (owner_id, name) VALUES (1, '1room');
INSERT INTO chat00.rooms (owner_id, name) VALUES (1, '2room');
INSERT INTO chat00.rooms (owner_id, name) VALUES (3, '3room');
INSERT INTO chat00.rooms (owner_id, name) VALUES (4, '4room');
INSERT INTO chat00.rooms (owner_id, name) VALUES (5, '5room');

INSERT INTO chat00.messages (author_id, room_id, text)
VALUES (1, 1, 'its 1st room');
INSERT INTO chat00.messages (author_id, room_id, text)
VALUES (1, 1, 'its 1st room and 2nd message');
INSERT INTO chat00.messages (author_id, room_id, text)
VALUES (2, 2, 'its 2nd room');
INSERT INTO chat00.messages (author_id, room_id, text)
VALUES (3, 3, 'its 3rd room');
INSERT INTO chat00.messages (author_id, room_id, text)
VALUES (4, 4, 'its 4th room');


INSERT INTO chat00.users_in_rooms SELECT chat00.rooms.id, chat00.rooms.owner_id FROM chat00.rooms;