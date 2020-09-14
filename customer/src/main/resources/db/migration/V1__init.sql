create table app.customer (
    id serial primary key,
    name varchar(32) not null,
    customer_number varchar(32) not null,
    created_on timestamp not null,
    changed_on timestamp
)