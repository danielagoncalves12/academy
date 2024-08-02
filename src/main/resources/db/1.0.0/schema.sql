CREATE TABLE T_TEAM
(
    id               BIGINT      NOT NULL PRIMARY KEY,
    name             VARCHAR(20) NOT NULL,
    product          VARCHAR(20) NOT NULL,
    created_at       TIMESTAMP   NOT NULL DEFAULT now(),
    modified_at      TIMESTAMP   NOT NULL DEFAULT now(),
    default_location VARCHAR(10) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_ID;

CREATE TABLE T_RACK
(
    id               BIGINT      NOT NULL PRIMARY KEY,
    serial_number    TEXT        NOT NULL,
    team_id          BIGINT      NOT NULL REFERENCES T_TEAM (id),
    default_location VARCHAR(10) NOT NULL,
    created_at       TIMESTAMP   NOT NULL DEFAULT now(),
    modified_at      TIMESTAMP   NOT NULL DEFAULT now(),
    status           VARCHAR(20) NOT NULL CHECK ( status = 'AVAILABLE' OR status = 'BOOKED' OR status = 'UNAVAILABLE' )
);

CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ID;

CREATE TABLE T_RACK_ASSET
(
    id        BIGINT      NOT NULL PRIMARY KEY,
    asset_tag VARCHAR(10) NOT NULL,
    rack_id   BIGINT      NOT NULL REFERENCES T_RACK (id)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_RACK_ASSET_ID;

CREATE TABLE T_TEAM_MEMBER
(
    id          BIGINT      NOT NULL PRIMARY KEY,
    team_id     BIGINT      NOT NULL REFERENCES T_TEAM (id),
    ctw_id      VARCHAR(10) NOT NULL,
    name        VARCHAR(20) NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT now(),
    modified_at TIMESTAMP   NOT NULL DEFAULT now()
);

CREATE SEQUENCE IF NOT EXISTS SEQ_TEAM_MEMBER_ID;

CREATE TABLE T_BOOKING
(
    id           BIGINT    NOT NULL PRIMARY KEY,
    rack_id      BIGINT    NOT NULL REFERENCES T_RACK (id),
    requester_id BIGINT    NOT NULL REFERENCES T_TEAM_MEMBER (id),
    book_from    TIMESTAMP NOT NULL,
    book_to      TIMESTAMP NOT NULL,
    created_at   TIMESTAMP NOT NULL DEFAULT now(),
    modified_at  TIMESTAMP NOT NULL DEFAULT now()
);

CREATE SEQUENCE IF NOT EXISTS SEQ_BOOKING_ID;