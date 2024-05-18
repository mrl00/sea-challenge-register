-- DROP SEQUENCE public.tb_adresses_id_seq;

CREATE SEQUENCE tb_adresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

-- DROP SEQUENCE public.tb_clients_id_seq;

CREATE SEQUENCE tb_clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

-- DROP SEQUENCE public.tb_emails_id_seq;

CREATE SEQUENCE tb_emails_id_seq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

-- DROP SEQUENCE public.tb_phones_id_seq;

CREATE SEQUENCE tb_phones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

-- DROP SEQUENCE public.tb_users_id_seq;

CREATE SEQUENCE tb_users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO CYCLE;

-- public.tb_adresses definition

-- DROP TABLE public.tb_adresses;

CREATE TABLE tb_adresses (
    uf SMALLINT NOT NULL,
    id BIGINT AUTO_INCREMENT NOT NULL,
    cep VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    complement VARCHAR(255),
    neighborhood VARCHAR(255) NOT NULL,
    public_place VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CHECK (uf BETWEEN 0 AND 26)
);

-- public.tb_clients definition

-- DROP TABLE public.tb_clients;

CREATE TABLE tb_clients (
    address_id BIGINT,
    id BIGINT AUTO_INCREMENT NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    name VARCHAR(100) NOT NULL,
    UNIQUE (address_id),
    PRIMARY KEY (id),
    UNIQUE (cpf)
);

-- public.tb_clients_emails definition

-- DROP TABLE public.tb_clients_emails;

CREATE TABLE tb_clients_emails (
    client_id BIGINT NOT NULL,
    emails_id BIGINT NOT NULL,
    UNIQUE (emails_id)
);

-- public.tb_clients_phones definition

-- DROP TABLE public.tb_clients_phones;

CREATE TABLE tb_clients_phones (
    client_id BIGINT NOT NULL,
    phones_id BIGINT NOT NULL,
    UNIQUE (phones_id)
);

-- public.tb_emails definition

-- DROP TABLE public.tb_emails;

CREATE TABLE tb_emails (
    id BIGINT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) NOT NULL,
    UNIQUE (email),
    PRIMARY KEY (id)
);

-- public.tb_phones definition

-- DROP TABLE public.tb_phones;

CREATE TABLE tb_phones (
    id BIGINT AUTO_INCREMENT NOT NULL,
    phone VARCHAR(11) NOT NULL,
    phone_type VARCHAR(255) NOT NULL,
    CHECK (phone_type IN ('RESIDENTIAL', 'CELLPHONE', 'COMMERCIAL')),
    PRIMARY KEY (id)
);

-- public.tb_users definition

-- DROP TABLE public.tb_users;

CREATE TABLE tb_users (
    role VARCHAR(24),
    id BIGINT AUTO_INCREMENT NOT NULL,
    user_name VARCHAR(64),
    password VARCHAR(255),
    PRIMARY KEY (id),
    CHECK (role IN ('ADMIN', 'DEFAULT')),
    UNIQUE (user_name)
);
