CREATE DATABASE IF NOT EXISTS bilabonnementdb;
USE bilabonnementdb;

DROP TABLE IF EXISTS damages;
DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       firstName VARCHAR(100) NOT NULL,
                       lastName VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cars (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      image_url VARCHAR(255) NOT NULL,
                      vehicle_number VARCHAR(50) UNIQUE NOT NULL,
                      vin VARCHAR(50) UNIQUE NOT NULL,
                      brand VARCHAR(50) NOT NULL,
                      model VARCHAR(50) NOT NULL,
                      equipment_level INT NOT NULL,
                      steel_price DECIMAL(10, 2) NOT NULL,
                      registration_fee DECIMAL(10, 2) NOT NULL,
                      co2_emissions INT NOT NULL,
                      status VARCHAR(50) NOT NULL DEFAULT 'TILGÆNGELIG',
                      subscription_price DECIMAL(10, 2) NOT NULL,
                      purchase_price DECIMAL(10, 2) NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bookings (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          car_id BIGINT NOT NULL,
                          user_id BIGINT,
                          customer_name VARCHAR(100) NOT NULL,
                          start_date DATE NOT NULL,
                          end_date DATE,
                          monthly_price DECIMAL(10, 2),
                          status VARCHAR(50) DEFAULT 'AKTIV',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                          CONSTRAINT fk_booking_car
                              FOREIGN KEY (car_id)
                                  REFERENCES cars(id)
                                  ON DELETE CASCADE,

                          CONSTRAINT fk_booking_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE SET NULL
);

CREATE TABLE damages (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         booking_id BIGINT NOT NULL,
                         description TEXT NOT NULL,
                         price DECIMAL(6, 2) NOT NULL,
                         approved BOOLEAN DEFAULT FALSE,
                         reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_damage_booking
                             FOREIGN KEY (booking_id)
                                 REFERENCES bookings(id)
                                 ON DELETE CASCADE
);

CREATE TABLE customers (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           email VARCHAR(150) UNIQUE NOT NULL,
                           phone_number VARCHAR(30) NOT NULL,
                           address VARCHAR(255) NOT NULL,
                           city VARCHAR(100) NOT NULL,
                           zip_code VARCHAR(20) NOT NULL,
                           drivers_license VARCHAR(50) UNIQUE NOT NULL,
                           cpr VARCHAR(20) UNIQUE NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);