BEGIN;

CREATE TABLE order_status (
    id SERIAL PRIMARY KEY,
    order_status VARCHAR(16) not null,
    created_at TIMESTAMP default NOW()
);

CREATE TABLE logistic_company (
    id SERIAL PRIMARY KEY,
    company_name VARCHAR(255) not null,
    company_url VARCHAR(255) not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    category_name TEXT not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE request (
    id TEXT PRIMARY KEY,
    created_by TEXT not null REFERENCES users(id),
    location_id INTEGER not null REFERENCES location(id),
    category_id INTEGER not null REFERENCES category(id),
    item TEXT not null,
    url VARCHAR(255) not null,
    quantity INTEGER not null,
    request_remark TEXT,
    offer_price INTEGER not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE category_field (
    id SERIAL PRIMARY KEY,
    category_id INTEGER not null REFERENCES category(id),
    category_field_name TEXT not null,
    created_at TIMESTAMP default NOW()
);

CREATE TABLE category_field_option (
    id SERIAL PRIMARY KEY,
    category_field_id INTEGER not null REFERENCES category_field(id),
    category_field_option TEXT not null,
    created_at TIMESTAMP default NOW()
);

CREATE TABLE category_result (
    id SERIAL PRIMARY KEY,
    request_id TEXT not null REFERENCES request(id),
    category_id INTEGER not null REFERENCES category(id),
    category_field_option_id INTEGER not null REFERENCES category_field_option(id),
    created_at TIMESTAMP default NOW()
);

CREATE TABLE offer (
    id TEXT PRIMARY KEY,
    request_id TEXT not null REFERENCES request(id),
    provider_id TEXT not null REFERENCES provider(id),
    status_id INTEGER not null REFERENCES status(id),
    estimated_process_time INTEGER not null,
    offer_price INTEGER not null,
    offer_remark TEXT not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE orders (
    id TEXT PRIMARY KEY,
    offer_id TEXT not null REFERENCES offer(id),
    order_status_id INTEGER not null REFERENCES order_status(id),
    logistic_company_id INTEGER not null REFERENCES logistic_company(id),
    shipping_order_no INTEGER not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    orders_id TEXT not null REFERENCES orders(id),
    provider_id TEXT not null REFERENCES provider(id),
    company_url VARCHAR(255) not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE report_case (
    id TEXT PRIMARY KEY,
    orders_id TEXT not null REFERENCES orders(id),
    admins_id TEXT not null REFERENCES admins(id),
    status_id INTEGER not null REFERENCES status(id),
    report_description TEXT not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

CREATE TABLE image (
    id TEXT PRIMARY KEY,
    request_id TEXT not null REFERENCES request(id),
    image_path TEXT,
    is_primary BOOLEAN not null,
    created_at TIMESTAMP default NOW(),
    updated_at TIMESTAMP default NOW()
);

COMMIT;