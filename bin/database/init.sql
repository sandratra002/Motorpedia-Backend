CREATE TABLE Brand (
    id VARCHAR(10) DEFAULT ('BRA') || LPAD(nextval('brand_sequence')::TEXT,4,'0') PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    logo VARCHAR(255) NOT NULL,
    origin_country VARCHAR(150) NOT NULL
);

CREATE TABLE Category (
    id VARCHAR(10) DEFAULT ('CAT') || LPAD(nextval('category_sequence')::TEXT,4,'0') PRIMARY KEY,
    name VARCHAR(200) NOT NULL, 
    description VARCHAR(255) NOT NULL
);

CREATE TABLE Transmission_Type (
    id VARCHAR(10) DEFAULT ('TRT') || LPAD(nextval('transmission_type_sequence')::TEXT,4,'0') PRIMARY KEY,
    name VARCHAR(200) NOT NULL, 
    description VARCHAR(255) NOT NULL
);

CREATE TABLE Engine_Type (
    id VARCHAR(10) DEFAULT ('ENT') || LPAD(nextval('engine_type_sequence')::TEXT,4,'0') PRIMARY KEY,
    name VARCHAR(200) NOT NULL, 
    description VARCHAR(255) NOT NULL
);

CREATE TABLE Car (
    id VARCHAR(10) DEFAULT ('CAR') || LPAD(nextval('car_sequence')::TEXT,4,'0') PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    year INT NOT NULL, 
    price DECIMAL(10, 2) NOT NULL,
    seating_capacity INT NOT NULL, 
    image VARCHAR(255) NOT NULL,
    brand_id VARCHAR(10),
    transmission_type_id VARCHAR(10),
    category_id VARCHAR(10),
    engine_type_id VARCHAR(10),
    CHECK (year > 0 AND price > 0 AND seating_capacity > 0),
    FOREIGN KEY (brand_id) REFERENCES Brand(id),
    FOREIGN KEY (transmission_type_id) REFERENCES Transmission_Type(id),
    FOREIGN KEY (category_id) REFERENCES Category(id),
    FOREIGN KEY (engine_type_id) REFERENCES Engine_Type(id)
);

CREATE TABLE UserInfo (
    id VARCHAR(10) DEFAULT ('USR') || LPAD(nextval('user_sequence')::TEXT, 4, '0') PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (email)
);

INSERT INTO UserInfo (name, first_name, email, password) VALUES
    ('admin', 'admin', 'admin@gmail.com', encode(digest('admin', 'sha256'), 'hex'));

CREATE TABLE Review (
    id VARCHAR(10) DEFAULT ('REV') || LPAD(nextval('review_sequence')::TEXT, 4, '0') PRIMARY KEY,
    car_id VARCHAR(10),
    user_id VARCHAR(10),
    review TEXT,
    rating INT,
    FOREIGN KEY (car_id) REFERENCES Car(id),
    FOREIGN KEY (user_id) REFERENCES UserInfo(id)
);

CREATE TABLE Event (
    id VARCHAR(10) DEFAULT ('EVE') || LPAD(nextval('event_sequence')::TEXT, 4, '0') PRIMARY KEY,
    brand_id VARCHAR(10),
    name VARCHAR(100) NOT NULL,
    description TEXT,
    hashtag VARCHAR(100) DEFAULT NULL,
    event_date DATE NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES Brand(id)
);

CREATE OR REPLACE VIEW v_Car_Info AS (
    SELECT 
        c.id,
        c.name,
        c.image,
        b.name brand, 
        cat.name category,
        t.name transmission_type,
        e.name engine_type
    FROM Car c
    JOIN Brand b ON b.id = c.brand_id
    JOIN Category cat ON cat.id = c.category_id
    JOIN Transmission_Type t ON t.id = c.transmission_type_id
    JOIN Engine_Type e ON e.id = c.engine_type_id
);

-- Entities : 
-- Brand (id, name, logo, origin_country)
-- Category (id, name, description) -> sport, mini-van, Sport Utility Vehicule, Sedan,...
-- Transmission_Type (id, name, description) -> Manual, automatic, Continuously Variable Transmission, Dual-Clutch trasmission, Automated Manual Transmission
-- Enging_Type (id, name, description)
-- Car (id, name, year, price, seating_capacity, ,image, brand_id, transmission_type_id, category_id. enging_type_id)