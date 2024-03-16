CREATE TABLE OFFICE_DESK (
    id SERIAL PRIMARY KEY,
    desk_code VARCHAR(255) NOT NULL UNIQUE,
    is_available_for_bookings BOOLEAN NOT NULL DEFAULT TRUE

);

CREATE TABLE MEETING_ROOM (
    id SERIAL PRIMARY KEY,
    meeting_room_name VARCHAR(255) UNIQUE,
    capacity INTEGER NOT NULL,
    is_available_for_bookings BOOLEAN NOT NULL DEFAULT TRUE

);


CREATE TABLE APP_USER(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL
);


CREATE TABLE MEETING_ROOM_BOOKINGS (
    id SERIAL PRIMARY KEY,
    meeting_room_id bigint,
    user_id BIGINT,
    start_date DATE,
    start_time TIME,
    end_time TIME,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE OFFICE_DESK_BOOKINGS (
    id SERIAL PRIMARY KEY,
    desk_id BIGINT,
    user_id BIGINT,
    start_date DATE,
    end_date DATE,
    start_time TIME,
    end_time TIME,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE'
);


