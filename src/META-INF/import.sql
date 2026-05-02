CREATE TABLE IF NOT EXISTS role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    role_name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS user (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    role_id INTEGER,
    patient_id INTEGER,
    physician_id INTEGER,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS patient (
    patient_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT
);

CREATE TABLE IF NOT EXISTS physician (
    physician_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT
);

CREATE TABLE IF NOT EXISTS laboratory_order (
    order_id INTEGER PRIMARY KEY AUTOINCREMENT,
    patient_id INTEGER,
    physician_id INTEGER,
    FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
    FOREIGN KEY (physician_id) REFERENCES physician(physician_id)
);


INSERT INTO User (username, password) VALUES ('admin', '1234');