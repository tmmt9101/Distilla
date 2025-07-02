create database distilla_database;

use distilla_database;

create table Product (
       id int not null unique auto_increment,
       name varchar(20) not null,
       description text default "",
       alcohol_percentage decimal(2, 2),
       available bit not null default(0),
       quantity int not null default(0), -- quantità disponibile al momento
       primary key(id)
);
create table ProductPrices (
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
create table ProductTypes (
       id int not null unique auto_increment,
       name varchar(20),
       primary key (id)
);
create table ProductTags (
       id int not null unique auto_increment,
       product int not null,
       type int null,
       primary key(id),
       foreign key(product) references Product(id),
       foreign key(type) references ProductTypes(id)
);

create table User (
       id int not null unique auto_increment,
       username varchar(20) not null,
       email varchar(254) not null, -- per RFC 2821 and RFC 3696
       creation time not null,
       primary key(id)       
);

create table Cart (
       id int not null unique auto_increment,
       user int not null,
);

create table CartItem (
       id int not null unique auto_increment,
       cart int not null unique,
       product int not null,
       quantity int not null default(1), -- quantità che l'utente vuole acquistare del prodotto
       primary key(id),
       foreign key(cart) references Cart(id),
       foreign key(product) references ProductPrices(id)
);

create table Comment (
       id int not null unique auto_increment,
       commenter User not null,
       comment text not null,
       publication time not null,
       stars int not null,
       primary key (id),
       foreign key (commenter) references User(id),
       constraint valid_stars_check check ((stars >= 0) or (stars <= 5))       
);

create table CommentResponse (
       id int not null unique auto_increment,
       comment text not null,
       response_to int not null,
       publication time not null,
       primary key (id),
       foreign key (response_to) references Comment(id),
);
