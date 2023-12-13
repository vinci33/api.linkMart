BEGIN;
CREATE TABLE users (
    id TEXT PRIMARY KEY,
    username VARCHAR(255) not null UNIQUE,
    password VARCHAR(255) not null,
    user_email VARCHAR(255) not null UNIQUE,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE user_address (
    id SERIAL PRIMARY KEY,
    user_id TEXT not null REFERENCES users(id),
    address VARCHAR(255) not null,
    is_primary BOOLEAN not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE user_payment_method (
    id SERIAL PRIMARY KEY,
    user_id TEXT not null REFERENCES users(id),
    payment_method VARCHAR(255) ,
    card_no VARCHAR (16) ,
    card_holder_name VARCHAR(255) ,
    expiry_date VARCHAR(5)  CHECK (expiry_date ~ '^[0-9]{2}/[0-9]{2}$'),
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE location (
    id SERIAL PRIMARY KEY,
    location_name VARCHAR(255) not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    status_name VARCHAR(255) not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
 );

 CREATE TABLE provider_verification (
    id SERIAL PRIMARY KEY,
    user_id TEXT not null REFERENCES users(id),
    status_id INTEGER not null REFERENCES status(id),
    id_document VARCHAR(255) not null,
    address_document VARCHAR(255) not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
 );

 CREATE TABLE provider(
     id TEXT PRIMARY KEY,
     user_id TEXT not null REFERENCES users(id),
     location_id INTEGER not null REFERENCES location(id),
     provider_verification_id INTEGER not null REFERENCES provider_verification(id),
     created_at TIMESTAMP default NOW(),
     updated_at TIMESTAMP default NOW()
 );

 ALTER TABLE provider_verification ADD COLUMN provider_id VARCHAR(255);
 ALTER TABLE provider_verification ADD CONSTRAINT fk_provider FOREIGN KEY (provider_id) REFERENCES provider(id);

COMMIT;