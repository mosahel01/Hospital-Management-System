INSERT INTO patient (name, gender, birth_date, email, blood_group)
VALUES ('James McNulty', 'MALE', '1982-8-8', 'james@mcnulty.com', 'A_POSITIVE'),
       ('Frestor Leamon', 'MALE', '1984-6-5', 'frestor@leamon.com', 'B_NEGATIVE'),
       ('Kima Greggs', 'FEMALE', '2004-3-3', 'kima@greggs.com', 'O_POSITIVE'),
       ('Avon Barksdale', 'MALE', '1984-8-9', 'avon@barksdale.com', 'B_POSITIVE'),
       ('Bubbs Johnny', 'MALE', '1977-11-12', 'bubbs@johnny.com', 'O_POSITIVE'),
       ('Cedric Daniels', 'MALE', '2000-3-10', 'cedirc@daniels.com', 'AB_POSITIVE') ON CONFLICT (email) DO NOTHING;
