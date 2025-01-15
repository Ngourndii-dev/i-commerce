--create database
sudo -u postgres createdb i_commerce;

--connection to the database
\c i_commerce;

--create all tables
create table store(
   id_store serial primary key,
   title varchar (50) not null,
   location varchar (50),
   pub varchar(50)
);
create table category(
   id_category serial primary key,
   store int references store(id_store),
   category_name varchar (50) not null unique,
   description varchar (50),
   unity varchar (50)
);

create table product(
   id_product serial primary key,
   category int references category(id_category),
   price DOUBLE PRECISION,
   sizes varchar (50),
   color varchar (20)
);


create table client(
  id_client serial primary key,
  full_name varchar(100) not null,
  username varchar (50) not null,
  password varchar (50)
);