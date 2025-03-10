-- liquibase formatted sql

-- changeset accounts:1
-- Create BaseEntity sequence if needed (assuming it's used for ID generation)
CREATE SEQUENCE IF NOT EXISTS card_entity_seq START 1;

-- Create Cards Table
CREATE TABLE IF NOT EXISTS cards (
      card_id BIGINT PRIMARY KEY DEFAULT nextval('card_entity_seq'),
      mobile_number VARCHAR(255),
      card_number VARCHAR(255),
      card_type VARCHAR(255),
      total_limit INTEGER,
      amount_used INTEGER,
      available_amount INTEGER,
      created_at TIMESTAMP,
      updated_at TIMESTAMP,
      created_by VARCHAR(255),
      updated_by VARCHAR(255)
);