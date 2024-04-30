CREATE TABLE card (
    payment_id  BIGSERIAL PRIMARY KEY NOT NULL,
    cardholder_name VARCHAR NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    type VARCHAR NOT NULL,
    card_number CHAR(16) NOT NULL,
    expiry_date CHAR(6) NOT NULL ,
    cvv CHAR(3) NOT NULL,
    order_id BIGINT NOT NULL,
        status varchar(20) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_date timestamp NOT NULL,
    version integer NOT NULL
);
