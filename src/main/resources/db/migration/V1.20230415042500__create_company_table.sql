CREATE TABLE T_COMPANY
(
    id              BIGINT auto_increment,
    country_id      BIGINT NOT NULL,
    name            VARCHAR(50) NOT NULL,
    acronym         VARCHAR(10) NOT NULL
);