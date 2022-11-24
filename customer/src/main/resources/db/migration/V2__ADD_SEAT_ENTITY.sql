CREATE TABLE IF NOT EXISTS seat
(
    id                 VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    seat_row           VARCHAR(255) NOT NULL,
    seat_column        INTEGER      NOT NULL,
    reserved           BOOLEAN      NOT NULL,
    CONSTRAINT pk_seat PRIMARY KEY (id),
    CONSTRAINT unique_seat_natural_id UNIQUE (seat_row, seat_column)
);

