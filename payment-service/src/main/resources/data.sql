-- -----------------------------------------------------
-- Data for table clients
-- -----------------------------------------------------
INSERT INTO clients (first_name, last_name, email)
VALUES ('John', 'Doe', 'johndoe@example.com'),
       ('Jane', 'Smith', 'janesmith@example.com');

-- -----------------------------------------------------
-- Data for table credit_cards
-- -----------------------------------------------------

INSERT INTO credit_cards (number, expiry_date, cvv, client_id)
VALUES ('1234567890123456', '2025-01-01', '123', 1),
       ('9876543210987654', '2024-01-01', '456', 2);

-- -----------------------------------------------------
-- Data for table accounts
-- -----------------------------------------------------

INSERT INTO accounts (balance, credit_card_id, blocked)
VALUES (1000.00, 1, false),
       (500.00, 2, false);

-- -----------------------------------------------------
-- Data for table payments
-- -----------------------------------------------------

INSERT INTO payments (amount, date, account_id)
VALUES (50.00, '2023-04-30', 1),
       (100.00, '2023-04-28', 2);

-- -----------------------------------------------------
-- Data for table admins
-- -----------------------------------------------------

INSERT INTO admins (username, password)
VALUES ('admin1', 'password123'),
       ('admin2', 'password123');