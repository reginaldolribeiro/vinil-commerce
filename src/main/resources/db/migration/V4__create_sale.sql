CREATE TABLE public.sale (
	id bigserial NOT NULL,
	customer_id serial NOT NULL,
	total_value numeric(19,2) NOT NULL,
	total_cashback numeric(19,2) NOT NULL,
	created_at            TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at            TIMESTAMP,
	CONSTRAINT sale_pk PRIMARY KEY (id),
	CONSTRAINT sale_customer_fk FOREIGN KEY (customer_id) REFERENCES public.customer(id)
);