-- File: V1__Add_Workspaces.sql

-- Add Workspaces
INSERT INTO Workspace (id, workspace_name,capacity)
VALUES ('seatid1', 'CLUJ_5_beta_1.1', null);

INSERT INTO Workspace (id, workspace_name, capacity)
VALUES ('seatid2', 'CLUJ_5_beta_1.2', null);

INSERT INTO Workspace (id, workspace_name, capacity)
VALUES ('seatid3', 'CLUJ_5_beta_1.3', null);

INSERT INTO Workspace (id, workspace_name, capacity)
VALUES ('seatid4', 'CLUJ_5_beta_1.4', null);


-- Enable pgcrypto extension if not already enabled
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Insert user with hashed password
INSERT INTO seat_reservation.app_user (email, password, role)
VALUES ('mirunalupas21@gmail.com', crypt('password', gen_salt('bf')), 'ADMIN');

INSERT INTO WORKSPACE_BOOKINGS (workspace_id, user_id, start_date, end_date, start_time, end_time, status)
VALUES (
    (SELECT id FROM Workspace WHERE workspace_name = 'CLUJ_5_beta_1.1'),
    (SELECT id FROM app_user WHERE email = 'mirunalupas21@gmail.com'),
    '2024-03-16',
    '2024-03-17',
    '09:00:00',
    '17:00:00',
    'ACTIVE'
);