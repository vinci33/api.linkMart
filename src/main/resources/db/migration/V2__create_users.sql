BEGIN;
CREATE TABLE users (
    id TEXT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE user_address (
    id SERIAL PRIMARY KEY,
    user_id TEXT NOT NULL REFERENCES users(id),
    address VARCHAR(255) NOT NULL,
    is_primary BOOLEAN NOT NULL,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE user_payment_method (
    id SERIAL PRIMARY KEY,
    user_id TEXT NOT NULL REFERENCES users(id),
    payment_method VARCHAR(255) NOT NULL,
    card_no VARCHAR (16) NOT NULL,
    card_holder_name VARCHAR(255) NOT NULL,
    expiry_date VARCHAR(5) NOT NULL CHECK (expiry_date ~ '^[0-9]{2}/[0-9]{2}$'),
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);
CREATE TABLE location (
    id SERIAL PRIMARY KEY,
    location_name VARCHAR(255) NOT NULL,
    location_address VARCHAR(255) NOT NULL,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE provider(
    id TEXT PRIMARY KEY,
    user_id TEXT NOT NULL REFERENCES users(id),
    location_id INTEGER NOT NULL REFERENCES location(id),
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    status_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
 );

 CREATE TABLE provider_verification (
    id SERIAL PRIMARY KEY,
    user_id TEXT NOT NULL REFERENCES users(id),
    provider_id TEXT NOT NULL REFERENCES provider(id),
    status_id INTEGER NOT NULL REFERENCES status(id),
    id_document VARCHAR(255) NOT NULL,
    address_document VARCHAR(255) NOT NULL,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
 );



COMMIT;