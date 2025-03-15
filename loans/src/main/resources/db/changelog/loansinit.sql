-- liquibase formatted sql

-- changeset loans:5
-- Create BaseEntity sequence if needed (assuming it's used for ID generation)
CREATE SEQUENCE IF NOT EXISTS loan_entity_seq START 1;

-- Create Cards Table
CREATE TABLE IF NOT EXISTS loans (
      loan_id BIGINT PRIMARY KEY DEFAULT nextval('loan_entity_seq'),
      mobile_number VARCHAR(255),
      loan_number VARCHAR(255),
      loan_type VARCHAR(255),
      total_loan INTEGER,
      amount_paid INTEGER,
      outstanding_amount INTEGER,
      created_at TIMESTAMP,
      updated_at TIMESTAMP,
      created_by VARCHAR(255),
      updated_by VARCHAR(255)
);