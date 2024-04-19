DROP DATABASE IF EXISTS life_pay_app;
CREATE DATABASE life_pay_app;

USE life_pay_app;

-- 创建城市表
drop table if exists city;
create table `city`(
   `id` int auto_increment,
   `name` varchar(100) not null unique,
   `letter` varchar(20) not null,
   primary key( `id` )
);

-- 创建业务表
drop table if exists service;
create table `service`(
	`id` int auto_increment,
    `name` varchar(100) not null unique,
    `img_url` varchar(100) not null unique,
    primary key(`id`)
);

-- 创建公司表
drop table if exists company;
create table `company` (
	`id` int auto_increment,
    `name` varchar(100) not null unique,
	`s_id` int, 
    `c_id` int, 
    primary key(`id`),
    foreign key(`s_id`) references service(`id`),
    foreign key(`c_id`) references city(`id`)
);

-- 创建城市-业务表
drop table if exists cs;
create table `cs`(
	`c_id` int,
	`s_id` int, 
    primary key(`c_id`, `s_id`),
    foreign key(`c_id`) references city(`id`),
    foreign key(`s_id`) references service(`id`)
);

-- 创建用户表
drop table if exists user;
create table `user`(
	id int auto_increment,
    phonenumber varchar(100) not null,
    username varchar(100) not null,
    img_url varchar(100) not null,
    primary key(`id`)
);
