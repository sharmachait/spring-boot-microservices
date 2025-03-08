-- liquibase formatted sql

-- changeset accounts:1
-- Create BaseEntity sequence if needed (assuming it's used for ID generation)
CREATE SEQUENCE IF NOT EXISTS base_entity_seq START 1;

-- Create Customer Table
CREATE TABLE IF NOT EXISTS customer (
      customer_id BIGINT PRIMARY KEY DEFAULT nextval('base_entity_seq'),
      name VARCHAR(255),
      email VARCHAR(255) UNIQUE,
      mobile_number VARCHAR(255) UNIQUE,
      created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
      updated_at TIMESTAMP WITHOUT TIME ZONE,
      created_by VARCHAR(255) NOT NULL,
      updated_by VARCHAR(255)
);

-- Create Accounts Table
CREATE TABLE IF NOT EXISTS accounts (
      customer_id BIGINT,
      account_number BIGINT PRIMARY KEY,
      account_type VARCHAR(255),
      branch_address VARCHAR(255),
      created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
      updated_at TIMESTAMP WITHOUT TIME ZONE,
      created_by VARCHAR(255) NOT NULL,
      updated_by VARCHAR(255),

      CONSTRAINT fk_customer
          FOREIGN KEY (customer_id)
              REFERENCES customer(customer_id)
);