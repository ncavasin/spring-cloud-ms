CREATE TABLE IF NOT EXISTS seat
(
    id                 VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    seat_row           VARCHAR(255) NOT NULL,
    seat_column        INTEGER      NOT NULL,
    room_id            VARCHAR(255) NOT NULL,
    CONSTRAINT pk_seat PRIMARY KEY (id),
    CONSTRAINT fk_room_seat FOREIGN KEY (room_id) REFERENCES room (id),
    CONSTRAINT unique_seat_natural_id UNIQUE (room_id, seat_row, seat_column)
);

CREATE TABLE IF NOT EXISTS reservation
(
    id                 VARCHAR(255) NOT NULL,
    customer_id        VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_reservation PRIMARY KEY (id),
    CONSTRAINT fk_customer_seat FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS reservation_seats
(
    reservation_id VARCHAR(255) NOT NULL,
    seat_id        VARCHAR(255) NOT NULL,
    CONSTRAINT uk_reservation_seats_seat UNIQUE (seat_id),
    CONSTRAINT fk_reservation_seat_reservation FOREIGN KEY (reservation_id) REFERENCES reservation (id),
    CONSTRAINT fk_reservation_seat_seat FOREIGN KEY (seat_id) REFERENCES seat (id),
    CONSTRAINT pk_reservation_seat PRIMARY KEY (reservation_id, seat_id)
)
