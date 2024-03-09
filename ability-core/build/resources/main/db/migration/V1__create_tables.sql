create sequence commands_seq start with 1 increment by 50;
create sequence contexts_seq start with 1 increment by 50;
create sequence messages_seq start with 1 increment by 50;
create sequence product_requests_seq start with 1 increment by 50;
create sequence products_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;

create table commands
(
    command_id    bigint not null,
    command_info  varchar(255),
    command_url   varchar(255),
    required_role varchar(255) check (required_role in ('ROLE_CLIENT', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')),
    primary key (command_id)
);

create table contexts
(
    context_id bigint not null,
    email      varchar(255) unique not null,
    user_name  varchar(255) unique not null,
    is_active  boolean default true,
    attempts   int default 0,
    primary key (context_id)
);

create table messages
(
    message_id      bigint not null,
    time_of_sending timestamp(6),
    user_id         bigint,
    message_body    varchar(255),
    primary key (message_id)
);

create table product_requests
(
    accept_status      boolean,
    count              integer,
    date_of_creation   timestamp(6),
    product_id         bigint,
    product_request_id bigint not null,
    user_id            bigint,
    primary key (product_request_id)
);

create table products
(
    product_id   bigint not null,
    description  varchar(255),
    product_name varchar(255),
    primary key (product_id)
);

create table users
(
    is_active  boolean,
    created_at timestamp(6),
    updated_at timestamp(6),
    user_id    bigint not null,
    email      varchar(255) unique,
    role       varchar(255) check (role in ('ROLE_CLIENT', 'ROLE_EMPLOYEE', 'ROLE_ADMIN')),
    username   varchar(255) unique,
    primary key (user_id)
);

alter table if exists messages add constraint messages_users_fk foreign key (user_id) references users;
alter table if exists product_requests add constraint product_requests_products_fk foreign key (product_id) references products;
alter table if exists product_requests add constraint product_requests_users_fk foreign key (user_id) references users;
