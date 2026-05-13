USE bilabonnementdb;

-- =========================
-- INSERT USERS
-- =========================
INSERT INTO users (email, password, firstName, lastName, role)
VALUES ('admin@admin.com', 'Admin1234', 'Lucas', 'Hoffmann Christensen', 'Admin');

-- =========================
-- INSERT CARS
-- =========================
INSERT INTO cars (
    image_url,
    vehicle_number,
    vin,
    brand,
    model,
    equipment_level,
    steel_price,
    registration_fee,
    co2_emissions,
    subscription_price,
    purchase_price
)
VALUES
    ('https://res.cloudinary.com/digital-interdan/image/upload/q_80/v1/cars/Peugeot_new208_active_0MM00N9V.png',
     '83 28 64-44 062-1', 'WDB0373631B123456', 'Peugeot', '208 Active Pack 75 HK', 2,
     117.00, 166.00, 125, 3299.00, 165310.00),

    ('https://res.cloudinary.com/digital-interdan/image/upload/q_80/v1/cars/Citroen_newc3_VTRSport_0MP00NWP.png',
     '37 99 26-12 078-6', 'WDB0847654C123456', 'Citroen', 'C3 VTR Sport 83 HK', 2,
     120.00, 1520.00, 124, 3299.00, 145310.00);

-- =========================
-- INSERT BOOKINGS
-- =========================
INSERT INTO bookings (
    car_id,
    user_id,
    customer_name,
    start_date,
    end_date,
    monthly_price
)
VALUES
    (2, 1, 'Niels Nielsen', '2026-04-05', '2026-07-05', 3299.00),

    (5, 1, 'John Doe', '2026-04-01', '2026-07-01', 3899.00);

-- =========================
-- INSERT DAMAGES
-- =========================
INSERT INTO damages (
    booking_id,
    description,
    price
)
VALUES
    (2, 'Ridset fælg', 3500),

    (3, 'Ny forrude', 2850);

-- =========================
-- INSERT CUSTOMERS
-- =========================
INSERT INTO customers (
    first_name,
    last_name,
    email,
    phone_number,
    address,
    city,
    zip_code,
    drivers_license,
    cpr
)
VALUES
    ('John', 'Doe', 'john.doe@email.com', '28112233',
     'Nørrebrogade 12', 'København', '2200',
     'JD458721', '120390-1234');

-- =========================
-- UPDATE STATEMENTS
-- =========================
UPDATE cars
SET status = 'SKADET'
WHERE id = 5;

UPDATE cars
SET status = 'UDLEJET'
WHERE id = 7;

UPDATE cars
SET status = 'SOLGT'
WHERE id = 2;

UPDATE users
SET lastName = 'Divsalar'
WHERE id = 7;

-- =========================
-- DELETE STATEMENTS
-- =========================
DELETE FROM bookings
WHERE id = 7;

-- =========================
-- SELECT STATEMENTS
-- =========================
SELECT * FROM cars;
SELECT * FROM customers;
SELECT * FROM bookings;
SELECT * FROM damages;
SELECT * FROM users;