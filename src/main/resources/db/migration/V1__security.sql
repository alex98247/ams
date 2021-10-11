create table t_right
(
    id   serial primary key,
    name varchar(20)
);

create table t_role
(
    id   serial primary key,
    name varchar(20) UNIQUE
);

create table role_right
(
    id       serial primary key,
    role_id  bigint REFERENCES t_role (id),
    right_id bigint REFERENCES t_right (id)
);

create table employee
(
    id         serial primary key,
    name       varchar(50),
    patronymic varchar(50),
    surname    varchar(50),
    phone      varchar(12),
    position   varchar(70)
);

create table t_user
(
    id                  serial primary key,
    employee_id         bigint REFERENCES employee (id),
    username            varchar(20),
    password            varchar(50),
    is_locked           boolean,
    expiration_date     timestamp,
    pwd_expiration_date timestamp,
    is_system           boolean
);

create table role_user
(
    id      serial primary key,
    role_id bigint REFERENCES t_role (id),
    user_id bigint REFERENCES t_user (id)
);