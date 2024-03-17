CREATE TABLE APP_USER(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);


CREATE TABLE WORKSPACE (
    id VARCHAR(255) PRIMARY KEY,
    workspace_name VARCHAR(255) UNIQUE,
    capacity INTEGER
);

CREATE TABLE WORKSPACE_BOOKINGS (
    id SERIAL PRIMARY KEY,
    workspace_id VARCHAR(255),
    user_id BIGINT,
    start_date DATE,
    start_time TIME,
    end_date DATE,
    end_time TIME,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE'
);


