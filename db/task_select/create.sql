create table fauna(
    id             generated by default as identity primary key,
    name           text,
    avg_age        int,
    discovery_date date
);