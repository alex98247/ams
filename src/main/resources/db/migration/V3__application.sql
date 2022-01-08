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
    id    integer primary key,
    name  varchar,
    count integer
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
    good_id        integer primary key,
    count          integer
);

create table good_application
(
    id             serial primary key,
    good_id        bigint REFERENCES good (id),
    application_id bigint REFERENCES application (id),
    count          integer
);