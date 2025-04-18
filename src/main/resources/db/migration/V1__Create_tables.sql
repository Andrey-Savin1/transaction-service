create table  wallet_types
(
    uid           bigint generated by default as identity primary key,
    created_at    timestamp default now() not null,
    modified_at   timestamp,
    name          varchar(32)             not null,
    currency_code varchar(3)              not null,
    status        varchar(18)             not null,
    archived_at   timestamp,
    user_type     varchar(15),
    creator       varchar(255),
    modifier      varchar(255)
);

create table wallets
(
    uid             bigint generated by default as identity primary key,
    created_at      timestamp default now() not null,
    modified_at     timestamp,
    name            varchar(32)             not null,
    wallet_type_uid bigint                  not null
        constraint fk_wallets_wallet_types references wallet_types,
    user_uid        bigint                  not null,
    status          varchar(30)             not null,
    balance         decimal   default 0.0   not null,
    archived_at     timestamp
);

create table payment_requests
(
    uid               bigint generated by default as identity primary key,
    created_at        timestamp default now() not null,
    modified_at       timestamp,
    user_uid          bigint                  not null,
    wallet_uid        bigint                  not null references wallets (uid),
    amount            decimal   default 0.0   not null,
    status            varchar,
    comment           varchar(256),
    payment_method_id bigint
);

create table transactions
(
    uid                 bigint generated by default as identity primary key,
    created_at          timestamp default now() not null,
    modified_at         timestamp,
    user_uid            bigint                  not null,
    wallet_uid          bigint                  not null references wallets (uid),
    wallet_name         varchar(32)             not null,
    amount              decimal   default 0.0   not null,
    type                varchar(32)             not null,
    state               varchar(32)             not null,
    payment_request_uid bigint                  not null references payment_requests on delete cascade
);

create table top_up_requests
(
    uid                 bigint generated by default as identity primary key,
    created_at          timestamp default now() not null,
    provider            varchar                 not null,
    payment_request_uid bigint                  not null references payment_requests on delete cascade
);

create table withdrawal_requests
(
    uid                 bigint generated by default as identity primary key,
    created_at          timestamp default now() not null,
    payment_request_uid bigint                  not null references payment_requests on delete cascade
);

create table transfer_requests
(
    uid                      bigint generated by default as identity primary key,
    created_at               timestamp default now() not null,
    system_rate              varchar                 not null,
    payment_request_uid_from bigint                  not null references payment_requests on delete cascade,
    payment_request_uid_to   bigint                  not null references payment_requests on delete cascade
);