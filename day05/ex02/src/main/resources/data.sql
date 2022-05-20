insert into chat.users(login, passwd) values ('MLIZZIE','123456');
insert into chat.users(login, passwd) values ('MLffIZZIE','4');
insert into chat.users(login, passwd) values ('ttt','14234');
insert into chat.users(login, passwd) values ('sdgf','sdg');
insert into chat.users(login, passwd) values ('sgf','sfg');

insert into chat.room(chat_owner,chat_name) values (1,'chat1');
insert into chat.room(chat_owner,chat_name) values (2,'chat2');
insert into chat.room(chat_owner,chat_name) values (3,'chat3');
insert into chat.room(chat_owner,chat_name) values (4,'chat4');
insert into chat.room(chat_owner,chat_name) values (5,'chat5');


insert into  chat.msgs(room_id,sender, message, time_msg) values (1,2,'hello','2020-05-29 10:23:54');
insert into  chat.msgs(room_id,sender, message, time_msg) values (2,2,'hello','2020-06-23 10:23:54');
insert into  chat.msgs(room_id,sender, message, time_msg) values (1,5,'hello','2020-06-20 10:23:54');
insert into  chat.msgs(room_id,sender, message, time_msg) values (1,2,'hello','2020-06-29 12:23:54');
insert into  chat.msgs(room_id,sender, message, time_msg) values (2,2,'hello','2020-06-29 11:23:54');