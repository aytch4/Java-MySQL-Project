create database if not exists goaltivity;
use goaltivity;

drop table if exists journal_tags;
drop table if exists user;
drop table if exists journal;
drop table if exists tags;
-- drop table if exists reminders;
-- drop table if exists events;

create table user (
	  id int auto_increment not null,
    firstname varchar(20) not null,
    lastname varchar(30) not null,
    emailaddress varchar(100) not null,
    primary key (id)
);

create table journal (
	  id int auto_increment not null,
    date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    title varchar(256) not null,
    content text not null,
    user int not null,
    primary key (id),
    foreign key (user) references user(id)
);

-- A journal can have multiple tags associated with it.
create table journal_tags(
  journal int NOT NULL,
  tag int NOT NULL,
  primary key(journal, tag),
  foreign key(journal) references journal(id),
  foreign key(tag) references tags(id)
);

-- Diary, Travel, Work, Computer, Photo, Lifestyle, General
create table tags (
	id int auto_increment not null,
  name varchar(20) NOT NULL,
  primary key (id)
)

/*
create table events (
	event_id int auto_increment not null,
    username varchar(20) not null,
	  event varchar(100) not null,
    location varchar(100),
    start_time ???
    end_time ???
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
-- */
