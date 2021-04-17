CREATE TABLE public.product (
	id bigserial NOT NULL,
	artist_name varchar(255) NOT NULL,
	genre varchar(255) DEFAULT NULL,
	name varchar(255) NOT NULL,
	price numeric(19,2) NOT NULL,
	created_at            TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at            TIMESTAMP,
    deleted_at            TIMESTAMP,
	CONSTRAINT product_pk PRIMARY KEY (id)
);