CREATE TABLE public.tb_adresses (
	id bigserial NOT NULL,
	cep varchar(255) NOT NULL,
	city varchar(255) NOT NULL,
	complement varchar(255) NULL,
	neighborhood varchar(255) NOT NULL,
	public_place varchar(255) NOT NULL,
	uf varchar(255) NOT NULL,
	CONSTRAINT tb_adresses_pkey PRIMARY KEY (id),
	CONSTRAINT tb_adresses_uf_check CHECK (((uf)::text = ANY ((ARRAY['AC'::character varying, 'AL'::character varying, 'AP'::character varying, 'AM'::character varying, 'BA'::character varying, 'CE'::character varying, 'DF'::character varying, 'ES'::character varying, 'GO'::character varying, 'MA'::character varying, 'MS'::character varying, 'MT'::character varying, 'MG'::character varying, 'PA'::character varying, 'PB'::character varying, 'PR'::character varying, 'PE'::character varying, 'PI'::character varying, 'RJ'::character varying, 'RN'::character varying, 'RS'::character varying, 'RO'::character varying, 'RR'::character varying, 'SC'::character varying, 'SP'::character varying, 'SE'::character varying, 'TO'::character varying])::text[])))
);

CREATE TABLE public.tb_emails (
	id bigserial NOT NULL,
	email varchar(255) NOT NULL,
	CONSTRAINT tb_emails_email_key UNIQUE (email),
	CONSTRAINT tb_emails_pkey PRIMARY KEY (id)
);

CREATE TABLE public.tb_phones (
	id bigserial NOT NULL,
	phone varchar(11) NOT NULL,
	phone_type varchar(255) NOT NULL,
	CONSTRAINT tb_phones_phone_type_check CHECK (((phone_type)::text = ANY ((ARRAY['RESIDENTIAL'::character varying, 'CELLPHONE'::character varying, 'COMMERCIAL'::character varying])::text[]))),
	CONSTRAINT tb_phones_pkey PRIMARY KEY (id)
);

CREATE TABLE public.tb_users (
	id bigserial NOT NULL,
	user_name varchar(64) NOT NULL,
	password varchar(255) NOT NULL,
	role varchar(255) NULL,
	CONSTRAINT tb_users_pkey PRIMARY KEY (id),
	CONSTRAINT tb_users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'DEFAULT'::character varying])::text[]))),
	CONSTRAINT tb_users_user_name_key UNIQUE (user_name)
);

CREATE TABLE public.tb_clients (
	address_id int8 NULL,
	id bigserial NOT NULL,
	cpf varchar(11) NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT tb_clients_address_id_key UNIQUE (address_id),
	CONSTRAINT tb_clients_cpf_key UNIQUE (cpf),
	CONSTRAINT tb_clients_pkey PRIMARY KEY (id),
	CONSTRAINT fkauxawsqweyv4i2v2tbgkby9s7 FOREIGN KEY (address_id) REFERENCES public.tb_adresses(id)
);

CREATE TABLE public.tb_clients_emails (
	client_id int8 NOT NULL,
	emails_id int8 NOT NULL,
	CONSTRAINT tb_clients_emails_emails_id_key UNIQUE (emails_id),
	CONSTRAINT fk52doq4wsqirbplshbq1yfv4o0 FOREIGN KEY (client_id) REFERENCES public.tb_clients(id),
	CONSTRAINT fkj9t7n18imn0qdfxjn0h76rjg5 FOREIGN KEY (emails_id) REFERENCES public.tb_emails(id)
);

CREATE TABLE public.tb_clients_phones (
	client_id int8 NOT NULL,
	phones_id int8 NOT NULL,
	CONSTRAINT tb_clients_phones_phones_id_key UNIQUE (phones_id),
	CONSTRAINT fkojvhwhrl7ss5nb8cs8f5yook4 FOREIGN KEY (client_id) REFERENCES public.tb_clients(id),
	CONSTRAINT fkpodpyrm75yuvr1yi67ep1i5ki FOREIGN KEY (phones_id) REFERENCES public.tb_phones(id)
);