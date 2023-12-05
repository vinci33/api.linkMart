BEGIN;
CREATE TABLE admins (
    id TEXT PRIMARY KEY,
    username  VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(72) NOT NULL,
    created_at TIMESTAMP default NOW()
);

COMMIT;
