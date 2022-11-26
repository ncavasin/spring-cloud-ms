CREATE TABLE IF NOT EXISTS seat
(
    id                   VARCHAR(255) NOT NULL,
    creation_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp     TIMESTAMP WITHOUT TIME ZONE,
    seat_row             VARCHAR(255) NOT NULL,
    seat_column          INTEGER      NOT NULL,
    room_id              VARCHAR(255) NOT NULL,
    confirmation         TIMESTAMP WITHOUT TIME ZONE,
    selection            TIMESTAMP WITHOUT TIME ZONE,
    selection_expiration TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_seat PRIMARY KEY (id, seat_row, seat_column),
    CONSTRAINT fk_room_seat FOREIGN KEY (room_id) REFERENCES room (id)
);
