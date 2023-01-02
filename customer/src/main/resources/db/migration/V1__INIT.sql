CREATE TABLE IF NOT EXISTS customer
(
    id                 VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    email              VARCHAR(255),
    password           VARCHAR(255),
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT unique_customer_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS branch
(
    id                 VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255) NOT NULL,
    zip_code           VARCHAR(255),
    CONSTRAINT pk_branch PRIMARY KEY (id),
    CONSTRAINT unique_branch_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS movie_topic
(
    movie_id VARCHAR(255) NOT NULL,
    topic_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_movie_topic PRIMARY KEY (movie_id, topic_id)
);

CREATE TABLE IF NOT EXISTS topic
(
    id                 VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255) NOT NULL,
    CONSTRAINT pk_topic PRIMARY KEY (id),
    CONSTRAINT unique_topic_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS movie
(
    id                  VARCHAR(255)     NOT NULL,
    creation_timestamp  TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp    TIMESTAMP WITHOUT TIME ZONE,
    title               VARCHAR(255)     NOT NULL,
    rating              DOUBLE PRECISION NOT NULL,
    synopsis            VARCHAR(255),
    duration_in_minutes BIGINT,
    classification      VARCHAR(255),
    CONSTRAINT pk_movie PRIMARY KEY (id),
    CONSTRAINT unique_movie_title UNIQUE (title)
);


CREATE TABLE IF NOT EXISTS movie_topic
(
    movie_id VARCHAR(255) NOT NULL,
    topic_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_movie_topic PRIMARY KEY (movie_id, topic_id),
    CONSTRAINT fk_movie_movie_topic FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT fk_topic_movie_topic FOREIGN KEY (topic_id) REFERENCES topic (id)
);

CREATE TABLE IF NOT EXISTS screen_format
(
    id                 VARCHAR(255)     NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255)     NOT NULL,
    screen_height      DOUBLE PRECISION NOT NULL,
    screen_width       DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_screenformat PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS room
(
    id                 VARCHAR(255) NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    name               VARCHAR(255) NOT NULL,
    branch_id          VARCHAR(255) NOT NULL,
    screen_format_id   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_room PRIMARY KEY (id),
    CONSTRAINT fk_branch_room_branch FOREIGN KEY (branch_id) REFERENCES branch (id),
    CONSTRAINT fk_screenformat_room_screenformat FOREIGN KEY (screen_format_id) REFERENCES screen_format (id)
);


CREATE TABLE IF NOT EXISTS show
(
    id                 VARCHAR(255)           NOT NULL,
    creation_timestamp TIMESTAMP WITHOUT TIME ZONE,
    update_timestamp   TIMESTAMP WITHOUT TIME ZONE,
    date               DATE                   NOT NULL,
    begin_time         TIME WITHOUT TIME ZONE NOT NULL,
    end_time           TIME WITHOUT TIME ZONE NOT NULL,
    movie_id           VARCHAR(255)           NOT NULL,
    room_id            VARCHAR(255)           NOT NULL,
    CONSTRAINT pk_show PRIMARY KEY (id),
    CONSTRAINT fk_movie_movie_show FOREIGN KEY (movie_id) REFERENCES movie (id),
    CONSTRAINT fk_room_movie_room FOREIGN KEY (room_id) REFERENCES room (id)
);
