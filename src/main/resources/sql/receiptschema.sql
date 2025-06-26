-- 1. Create schema
CREATE SCHEMA IF NOT EXISTS receiptschema;

-- 2. Create store_receipt table
CREATE TABLE IF NOT EXISTS receiptschema.store_receipt (
    id SERIAL PRIMARY KEY,
    store_name VARCHAR(255) NOT NULL,
    generic_store_name VARCHAR(255) NOT NULL,
    store_address TEXT NOT NULL,
    store_category VARCHAR(255),
    total NUMERIC(10, 2),
    purchase_date TIMESTAMPTZ
);

-- 3. Create receipt_item table
CREATE TABLE IF NOT EXISTS receiptschema.receipt_item (
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    generic_item_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    subtotal NUMERIC(10, 2),
    item_category VARCHAR(255),
    receipt_id INT,
    CONSTRAINT fk_receipt
        FOREIGN KEY (receipt_id)
        REFERENCES receiptschema.store_receipt(id)
        ON DELETE CASCADE
);
