DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.tb_adresses_id_seq;

CREATE SEQUENCE public.tb_adresses_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.tb_clients_id_seq;

CREATE SEQUENCE public.tb_clients_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.tb_emails_id_seq;

CREATE SEQUENCE public.tb_emails_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.tb_phones_id_seq;

CREATE SEQUENCE public.tb_phones_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.tb_users_id_seq;

CREATE SEQUENCE public.tb_users_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- public.tb_adresses definition

-- Drop table

-- DROP TABLE public.tb_adresses;

CREATE TABLE public.tb_adresses (
	uf int2 NOT NULL,
	id bigserial NOT NULL,
	cep varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	complement varchar(255) NULL,
	neighborhood varchar(255) NOT NULL,
	public_place varchar(255) NOT NULL,
	CONSTRAINT tb_adresses_pkey PRIMARY KEY (id),
	CONSTRAINT tb_adresses_uf_check CHECK (((uf >= 0) AND (uf <= 26)))
);


-- public.tb_clients definition

-- Drop table

-- DROP TABLE public.tb_clients;

CREATE TABLE public.tb_clients (
	address_id int8 NULL,
	id bigserial NOT NULL,
	cpf varchar(11) NOT NULL,
	"name" varchar(100) NOT NULL,
	CONSTRAINT tb_clients_address_id_key UNIQUE (address_id),
	CONSTRAINT tb_clients_pkey PRIMARY KEY (id)
);


-- public.tb_clients_emails definition

-- Drop table

-- DROP TABLE public.tb_clients_emails;

CREATE TABLE public.tb_clients_emails (
	client_id int8 NOT NULL,
	emails_id int8 NOT NULL,
	CONSTRAINT tb_clients_emails_emails_id_key UNIQUE (emails_id)
);


-- public.tb_clients_phones definition

-- Drop table

-- DROP TABLE public.tb_clients_phones;

CREATE TABLE public.tb_clients_phones (
	client_id int8 NOT NULL,
	phones_id int8 NOT NULL,
	CONSTRAINT tb_clients_phones_phones_id_key UNIQUE (phones_id)
);


-- public.tb_emails definition

-- Drop table

-- DROP TABLE public.tb_emails;

CREATE TABLE public.tb_emails (
	id bigserial NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT tb_emails_email_key UNIQUE (email),
	CONSTRAINT tb_emails_pkey PRIMARY KEY (id)
);


-- public.tb_phones definition

-- Drop table

-- DROP TABLE public.tb_phones;

CREATE TABLE public.tb_phones (
	id bigserial NOT NULL,
	phone varchar(11) NOT NULL,
	phone_type varchar(255) NOT NULL,
	CONSTRAINT tb_phones_phone_type_check CHECK (((phone_type)::text = ANY ((ARRAY['RESIDENTIAL'::character varying, 'CELLPHONE'::character varying, 'COMMERCIAL'::character varying])::text[]))),
	CONSTRAINT tb_phones_pkey PRIMARY KEY (id)
);


-- public.tb_users definition

-- Drop table

-- DROP TABLE public.tb_users;

CREATE TABLE public.tb_users (
	"role" int2 NULL,
	id bigserial NOT NULL,
	user_name varchar(64) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT tb_users_pkey null,
	CONSTRAINT tb_users_role_check null,
	CONSTRAINT tb_users_user_name_key null
);
