drop database if exists ticket;
drop user if exists 'ticket'@'localhost';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database ticket default character set utf8mb4 collate utf8mb4_unicode_ci;
use ticket;
create user 'ticket'@'localhost' identified by 'ticket';
grant all privileges on ticket.* to 'ticket'@'localhost';
flush privileges;