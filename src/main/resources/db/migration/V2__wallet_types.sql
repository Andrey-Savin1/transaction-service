INSERT INTO wallet_types(currency_code, name, status, user_type, creator)
VALUES ('RUB', 'Russian Rouble', 'ACTIVE', 'INDIVIDUAL', 'ADMIN'),
       ('EUR', 'Euro', 'ACTIVE', 'INDIVIDUAL', 'ADMIN'),
       ('USD', 'United States Dollar', 'ACTIVE', 'INDIVIDUAL', 'ADMIN') ON CONFLICT DO NOTHING;