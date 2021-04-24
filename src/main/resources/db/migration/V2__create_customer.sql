CREATE TABLE public.customer (
	id bigserial NOT NULL,
	birth_date date NOT NULL,
	email varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	created_at            TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at            TIMESTAMP,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

INSERT INTO customer (birth_date, email, name) VALUES ('1985/08/22', 'reginaldolribeiro@gmail.com', 'Reginaldo Luiz Ribeiro Filho');
INSERT INTO customer (birth_date, email, name) VALUES ('1965/10/20', 'fulanodasilva@gmail.com', 'Fulano da Silva');
INSERT INTO customer (birth_date, email, name) VALUES ('1970/01/10', 'beltranosouza@gmail.com', 'Beltrano Souza');
