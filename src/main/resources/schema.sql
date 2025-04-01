CREATE TABLE IF NOT EXISTS extensions (
                                          id BIGSERIAL PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    active BOOLEAN NOT NULL,
    logo VARCHAR(255) NOT NULL
    );