-- CSDL SALES
create database Sales;
create table employees (
	employee_id int auto_increment primary key,
    last_name varchar(20) not null,
    first_name varchar(10) not null,
    birth_date date,
    supervisor_id int
);
create table customers (
	customer_id int auto_increment primary key,
    customer_name varchar(255),
    contact_name varchar(255),
    address varchar(255),
    city varchar(255),
    postal_code	varchar(10),
    country varchar(50)
);

create table orders (
	order_id int auto_increment primary key,
    customer_id int,
    employee_id int,
	order_date datetime default current_timestamp,
    foreign key (customer_id) references customers(customer_id),
    foreign key (employee_id) references employees(employee_id)
);
-- CSDL BIKESTORES
create database BikeStores;
create table brands (
	brand_id int auto_increment primary key,
    brand_name varchar(255) not null
);

create table categories (
	category_id int auto_increment primary key,
    category_name varchar(255) not null
);

create table customers (
	customer_id int auto_increment primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    phone varchar(25),
    email varchar(255) not null,
	street varchar(255),
    city varchar(50),
    state varchar(25),
    zip_code varchar(5)
);

create table stores (
	store_id int auto_increment primary key,
    store_name varchar(255) not null,
	phone varchar(25),
    email varchar(255),
    street varchar(255),
    city varchar(255),
    state varchar(10),
    zip_code varchar(5)
);

create table staffs (
	staff_id int auto_increment primary key, 
    first_name varchar(50) not null,
    last_name varchar(50) not null, 
    email varchar(255) not null,
    phone varchar(25),
    active tinyint not null,
    store_id int not null,
    manager_id int,
	foreign key (store_id) references stores(store_id),
    foreign key (manager_id) references staffs(staff_id)
);

create table orders (
	order_id int auto_increment primary key,
    customer_id int,
    order_status tinyint not null,
    order_date date not null,
    required_date date not null,
    shipped_date date,
    store_id int not null, 
    staff_id int not null,
    foreign key (customer_id) references customers(customer_id),
    foreign key (store_id) references stores(store_id),
    foreign key (staff_id) references staffs(staff_id)
);

create table products (
	product_id int auto_increment primary key,
    product_name varchar(255) not null,
    brand_id int not null,
    category_id int not null,
    model_year smallint not null,
    list_price decimal(10,2) not null,
    foreign key (brand_id) references brands(brand_id),
    foreign key (category_id) references categories(category_id)
);

create table stocks (
	store_id int,
    product_id int,
    quantity int,
    foreign key (store_id) references stores(store_id),
    foreign key (product_id) references products(product_id),
    primary key (store_id, product_id)
);

create table order_items (
	order_id int,
    item_id int auto_increment,
	product_id int not null,
    quantity int not null,
    list_price decimal(10,2) not null,
    discount decimal(4,2) not null,
    primary key (item_id, order_id),
	foreign key (order_id) references orders(order_id),
    foreign key (product_id) references products(product_id)
);

-- INSERT 
insert into brands(brand_name) values('Electra');
insert into brands(brand_name) values('Haro');
insert into brands(brand_name) values('Heller');
insert into brands(brand_name) values('Pure Cycles');
insert into brands(brand_name) values('Ritchey');
insert into brands(brand_name) values('Strider');
insert into brands(brand_name) values('Sun Bicycles');
insert into brands(brand_name) values('Surly');
insert into brands(brand_name) values('Trek');

insert into categories(category_name) values('Children Bicycles');
insert into categories(category_name) values('Comfort Bicycles');
insert into categories(category_name) values('Cruisers Bicycles');
insert into categories(category_name) values('Cyclocross Bicycles');
insert into categories(category_name) values('Electric Bikes');
insert into categories(category_name) values('Mountain Bikes');
insert into categories(category_name) values('Road Bikes');

insert into products(product_name, brand_id, category_id, model_year, list_price) values('Trek 820 - 2016',9,6,2016,379.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Ritchey Timberwolf Frameset - 2016',5,6,2016,749.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Surly Wednesday Frameset - 2016',8,6,2016,999.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Trek Fuel EX 8 29 - 2016',9,6,2016,2899.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Heller Shagamaw Frame - 2016',3,6,2016,1320.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Surly Ice Cream Truck Frameset - 2016',8,6,2016,469.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Trek Slash 8 27.5 - 2016',9,6,2016,3999.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Trek Remedy 29 Carbon Frameset - 2016',9,6,2016,1799.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Trek Conduit+ - 2016',9,5,2016,2999.99)
insert into products(product_name, brand_id, category_id, model_year, list_price) values('Surly Straggler - 2016',8,4,2016,1549.00);

insert into customers(first_name, last_name, phone, email, street, city, state, zip_code) values ('Debra','Burks',null,'debra.burks@yahoo.com','9273 Thorne Ave.','Orchard Park','NY','14127')
insert into customers(first_name, last_name, phone, email, street, city, state, zip_code) values ('Kasha','Todd',null,'kasha.todd@yahoo.com','910 Vine Street','Campbell','CA','95008')
insert into customers(first_name, last_name, phone, email, street, city, state, zip_code) values ('Tameka','Fisher',null,'tameka.fisher@aol.com','769C Honey Creek St.','Redondo Beach','CA','90278')
insert into customers(first_name, last_name, phone, email, street, city, state, zip_code) values ('Daryl','Spence',null,'daryl.spence@aol.com','988 Pearl Lane','Uniondale','NY','11553')
insert into customers(first_name, last_name, phone, email, street, city, state, zip_code) values ('Charolette','Rice','(916) 381-6003','charolette.rice@msn.com','107 River Dr.','Sacramento','CA','95820');

insert into stores(store_name, phone, email, street, city, state, zip_code) values ('Santa Cruz Bikes','(831) 476-4321','santacruz@bikes.shop','3700 Portola Drive','Santa Cruz','CA','95060')
insert into stores(store_name, phone, email, street, city, state, zip_code) values ('Baldwin Bikes','(516) 379-8888','baldwin@bikes.shop','4200 Chestnut Lane','Baldwin','NY','11432')
insert into stores(store_name, phone, email, street, city, state, zip_code) values ('Rowlett Bikes','(972) 530-5555','rowlett@bikes.shop','8000 Fairway Avenue','Rowlett','TX','75088');

insert into stocks(store_id, product_id, quantity) values (1, 1, 27)
insert into stocks(store_id, product_id, quantity) values (1, 2, 5)
insert into stocks(store_id, product_id, quantity) values (1, 3, 6)
insert into stocks(store_id, product_id, quantity) values (1, 4, 23)
insert into stocks(store_id, product_id, quantity) values (1, 5, 22)
insert into stocks(store_id, product_id, quantity) values (1, 6, 0)
insert into stocks(store_id, product_id, quantity) values (1, 7, 8)
insert into stocks(store_id, product_id, quantity) values (1, 8, 0)
insert into stocks(store_id, product_id, quantity) values (1, 9, 11)
insert into stocks(store_id, product_id, quantity) values (1, 10, 15);

insert into staffs(first_name, last_name, email, phone, active, store_id, manager_id) values ('Fabiola', 'Jackson', 'fabiola.jackson@bikes.shop', '(831) 555-5554', 1, 1, null)
insert into staffs(first_name, last_name, email, phone, active, store_id, manager_id) values ('Mireya', 'Copeland', 'mireya.copeland@bikes.shop', '(831) 555-5555', 1, 1, 1)
insert into staffs(first_name, last_name, email, phone, active, store_id, manager_id) values ('Genna', 'Serrano', 'genna.serrano@bikes.shop', '(831) 555-5556', 1, 1, 2)
insert into staffs(first_name, last_name, email, phone, active, store_id, manager_id) values ('Virgie', 'Wiggins', 'virgie.wiggins@bikes.shop', '(831) 555-5557', 1, 1, 2)
insert into staffs(first_name, last_name, email, phone, active, store_id, manager_id) values ('Jannette', 'David', 'jannette.david@bikes.shop', '(516) 379-4444', 1, 2, 1);

insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) values (1,4,'2016-01-01','2016-01-03','2016-01-03',1,2)
insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) values (2,4,'2016-01-01','2016-01-04','2016-01-03',2,5)
insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) values (3,4,'2016-01-02','2016-01-05','2016-01-03',2,5)
insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) values (4,4,'2016-01-03','2016-01-04','2016-01-05',1,3)
insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) values (5,4,'2016-01-03','2016-01-06','2016-01-06',2,4);

insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (1,1,10,1,599.99,0.20)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (1,2,8,2,1799.99,0.07)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (1,3,10,2,1549.00,0.05)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (1,4,10,2,599.99,0.05)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (1,5,4,1,2899.99,0.20)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (2,1,10,1,599.99,0.07)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (2,2,10,2,599.99,0.05)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (3,1,3,1,999.99,0.05)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (3,2,10,1,599.99,0.05)
insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (4,1,2,2,749.99,0.10);

select * from brands;
select * from categories;
select * from products;
select * from customers;
select * from stores;
select * from stocks;
select * from orders;
select * from order_items order by order_id asc;
