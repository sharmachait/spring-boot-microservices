-- liquibase formatted sql

-- changeset accounts:2
ALTER TABLE customer
    ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE customer
    ADD CONSTRAINT unique_mobile_number UNIQUE (mobile_number);
