create sequence hibernate_sequence start 1 increment 1;

create table atm (
    id int8 not null,
    createddate timestamp,
    is_deleted boolean not null,
    updated_date timestamp,
    bank_id int8,
    cash int8,
    max_cash int8,
    card_id int8,
    is_insered_card boolean not null,
    primary key (id)
);

create table bank(
    id int8 not null,
    createddate timestamp,
    is_deleted boolean not null,
    updated_date timestamp,
    bank_name varchar(255),
    global_balance int8,
    atm_id int8,
    bank_account_id int8,
    primary key (id)
);

create table bank_account(
    id int8 not null,
    createddate timestamp,
    is_deleted boolean not null,
    updated_date timestamp,
    bank_id int8,
    bank_card_id int8,
    bank_user_id int8,
    balance int8,
    primary key(id)
);

create table bank_card (
    id int8 not null,
    createddate timestamp,
    is_deleted boolean not null,
    updated_date timestamp,
    bank_account_id int8,
    pin varchar(255),
    card_numder varchar(255),
    card_user varchar(255),
    is_blocked boolean not null,
    usage_time date,
    primary key (id)
);

create table bank_user (
    id int8 not null,
    createddate timestamp,
    is_deleted boolean not null,
    updated_date timestamp,
    bank_account_id int8,
    first_name varchar(255),
    surname varchar(255),
    patronymic_name varchar(255),
    email varchar(255),
    description varchar(255),
    primary key (id)
);

alter table if exists atm add constraint atm_bank foreign key (bank_id) references bank;
alter table if exists bank add constraint bank_atm foreign key (atm_id) references atm;
alter table if exists bank add constraint bank_account foreign key (bank_account_id) references bank_account;
alter table if exists bank_account add constraint account_bank foreign key (bank_id) references bank;
alter table if exists bank_account add constraint bank_account_card foreign key (bank_card_id) references bank_card;
alter table if exists bank_account add constraint bank_account_user foreign key (bank_user_id) references bank_user;
alter table if exists bank_card add constraint card_bank_account foreign key (bank_account_id) references bank_account;
alter table if exists bank_user add constraint user_bank_account foreign key (bank_Account_id) references bank_account;