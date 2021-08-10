create database if not exists goaltivity;

use goaltivity;

drop table if exists journal;
drop table if exists reminders;
drop table if exists events;
drop table if exists user;

create table user (
	id int auto_increment not null,
    firstname varchar(20) not null,
    lastname varchar(30) not null,
    address varchar(100) not null,
    username varchar(20) not null,
    password varchar(20) not null,
    primary key (username)
);

create table journal (
	id int auto_increment not null,
    subject varchar(100) not null,
    content text not null,
    username varchar(20) not null,
    primary key (id),
    foreign key (username) references user(username)
);

create table events (
	event_id int auto_increment not null,
    username varchar(20) not null,
	event varchar(100) not null,
    location varchar(100),
    start_time???
    end_time???
    primary key (event_id)
    foreign key (username) references user (username)

);

create table reminders (
	id int auto_increment not null,
    username varchar(20) not null,
    event_id int not null,
    time 
    
    primary key (id),
    foreign key (username) references user (username), 
    foreign key (event_id) references events(event_id)
);

