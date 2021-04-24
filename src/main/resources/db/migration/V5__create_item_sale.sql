CREATE TABLE public.item_sale (
	id bigserial NOT NULL,
	cashback_percentage numeric(19,2) DEFAULT NULL,
	cashback_value numeric(19,2) DEFAULT NULL,
	price numeric(19,2) NOT NULL,
	product_id serial NOT NULL,
	sale_id serial NOT NULL,
	created_at            TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at            TIMESTAMP,
	CONSTRAINT item_sale_pk PRIMARY KEY (id),
	CONSTRAINT item_sale_product_fk FOREIGN KEY (product_id) REFERENCES public.product(id),
	CONSTRAINT item_sale_sale_fk FOREIGN KEY (sale_id) REFERENCES public.sale(id)
);