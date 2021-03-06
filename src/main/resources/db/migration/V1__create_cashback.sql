CREATE TABLE public.cashback (
	id bigserial NOT NULL,
	day_of_week varchar(255) NOT NULL,
	genre varchar(255) NOT NULL,
	value numeric(19,2) NOT NULL,
	created_at            TIMESTAMP DEFAULT NOW() NOT NULL,
    updated_at            TIMESTAMP,
	CONSTRAINT cashback_pk PRIMARY KEY (id)
);

INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'POP', 25.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'POP', 7.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'POP', 6.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'POP', 2.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'POP', 10.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'POP', 15.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'POP', 20.00);

INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'MPB', 30.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'MPB', 5.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'MPB', 10.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'MPB', 15.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'MPB', 20.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'MPB', 25.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'MPB', 30.00);

INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'CLASSIC', 35.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'CLASSIC', 3.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'CLASSIC', 5.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'CLASSIC', 8.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'CLASSIC', 13.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'CLASSIC', 18.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'CLASSIC', 25.00);

INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SUNDAY', 'ROCK', 40.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('MONDAY', 'ROCK', 10.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('TUESDAY', 'ROCK', 15.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('WEDNESDAY', 'ROCK', 15.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('THURSDAY', 'ROCK', 15.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('FRIDAY', 'ROCK', 20.00);
INSERT INTO cashback (DAY_OF_WEEK, GENRE, VALUE) VALUES ('SATURDAY', 'ROCK', 40.00);
