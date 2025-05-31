create or replace database distilla_database;

use distilla_database;

create or replace table Product (
       id int not null unique auto_increment,
       name varchar(20) not null,
       alcohol_percentage decimal(2, 2),
       available bit not null default(0),
       quantity int not null default(0), -- quantità disponibile al momento
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

create or replace table User (
       id int not null unique auto_increment,
       username varchar(20) not null,
       email varchar(254) not null, -- per RFC 2821 and RFC 3696
       creation time not null,
       primary key(id)       
);
create or replace table Cart (
       id int not null unique auto_increment,
       user int not null,
),
create or replace table CartItem (
       id int not null unique auto_increment,
       cart int not null unique,
       product int not null,
       quantity int not null default(1), -- quantità che l'utente vuole acquistare del prodotto
       primary key(id),
       foreign key(cart) references Cart(id),
       foreign key(product) references ProductPrices(id)
);
