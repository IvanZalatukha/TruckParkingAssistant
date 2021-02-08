drop schema if exists tpa;

create schema if not exists tpa;

use tpa;
create table if not exists parking
(
    id int auto_increment
    primary key,
    name varchar(60) not null,
    spots_total int not null,
    coordinate_latitude double not null,
    coordinate_longitude double not null
    );

create table if not exists services
(
    id int auto_increment
    primary key,
    fence tinyint(1) default 0 null,
    security_cameras tinyint(1) default 0 null,
    wc tinyint(1) default 0 null,
    shower tinyint(1) default 0 null,
    guarded_parking tinyint(1) default 0 null,
    lighting tinyint(1) default 0 null,
    electricity tinyint(1) default 0 null,
    water tinyint(1) default 0 null,
    gas_station tinyint(1) default 0 null,
    wifi tinyint(1) default 0 null,
    lodging tinyint(1) default 0 null,
    truck_service tinyint(1) default 0 null,
    truck_wash tinyint(1) default 0 null,
    store tinyint(1) default 0 null,
    food tinyint(1) default 0 null,
    constraint services_ibfk_1
    foreign key (id) references parking (id)
    on update cascade
    );

create table if not exists user_role
(
    id int auto_increment
    primary key,
    role_name varchar(20) not null
    );

create table if not exists app_user
(
    id int auto_increment
    primary key,
    login varchar(100) not null,
    password varchar(100) not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(20) not null,
    is_banned tinyint(1) default 0 null,
    role_id int not null,
    phone_number int null,
    constraint login
    unique (login),
    constraint app_user_ibfk_1
    foreign key (role_id) references user_role (id)
    on update cascade
    );
create table if not exists messages_from_users
(
    id int auto_increment
    primary key,
    name varchar(100) not null,
    email varchar(30) not null,
    topic varchar(40) not null,
    text varchar(650) not null
    );

create table if not exists user_bank_account
(
    iban varchar(20) not null
    primary key,
    id int not null,
    constraint user_bank_account_ibfk_1
    foreign key (id) references app_user (id)
    on update cascade
    );
