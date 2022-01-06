create table legal_entity
(
    id   serial primary key,
    name varchar,
    inn  varchar(12),
    ogrn varchar(13),
    kpp  varchar(9)
);

create table good
(
    item_number integer primary key,
    name        varchar,
    count       integer
);


create table application
(
    id               serial primary key,
    customer_id      bigint REFERENCES legal_entity (id),
    finished         boolean,
    manager_username varchar
);

create table good_reserve
(
    item_number    integer primary key,
    application_id bigint REFERENCES application (id),
    count          integer
);

create table good_application
(
    id             serial primary key,
    good_id        bigint REFERENCES good (item_number),
    application_id bigint REFERENCES application (id)
);