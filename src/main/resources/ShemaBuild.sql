create schema atm;
use atm;
create table if not exists atm_amount
(
    available_amount int not null
);

create table if not exists card_data
(
    card_number       varchar(20)  not null,
    balance           double       not null,
    attempts_to_login int unsigned not null,
    constraint card_data_card_number_uindex
        unique (card_number)
);

alter table card_data
    add primary key (card_number);

create table if not exists credit_cards
(
    card_number varchar(20) not null,
    pin_code    varchar(5)  not null,
    constraint credit_cards_card_number_uindex
        unique (card_number)
);

alter table credit_cards
    add primary key (card_number);

