create table supplier (
    id serial primary key,
    name varchar(32) not null,
    supplier_number varchar(32) not null,
    created_on timestamp not null,
    changed_on timestamp
)