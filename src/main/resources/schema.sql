CREATE TABLE IF NOT EXISTS cart_event ( 
   aggregate_id VARCHAR(255) NOT NULL,
   version int not null,
   time_created TIMESTAMP NOT NULL,
   data VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS cart_aggregate (
    aggregate_id VARCHAR(255) NOT NULL,
    version int not null
);
