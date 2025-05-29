create or replace database distilla_database;

use distilla_database;

create or replace table Product (
       id int not null unique auto_increment,
       name varchar(20) not null,
       alcohol_percentage decimal(2, 2),
       available bit not null default(0),
       primary key(id)
);
create or replace table ProductPrices (
       id int not null unique auto_increment,
       product int not null,
       price decimal(4, 2) not null,
       validity_start time,
       validity_end time,
       constraint valid_price_check check
       ((validity_start is null and validity_end is null) or
        ((validity_start is null and validity_end is not null) and
         (validity_start < validity_end))),
       primary key (id),
       foreign key (product) references Product(id),
);
create or replace table ProductTypes (
       id int not null unique auto_increment,
       name varchar(20),
       primary key (id)
);
create or replace table ProductTags (
       id int not null unique auto_increment,
       product int not null,
       type int null,
       primary key(id),
       foreign key(product) references Product(id),
       foreign key(type) references ProductTypes(id)
);
