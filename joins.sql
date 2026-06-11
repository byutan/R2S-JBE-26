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
    postal_code varchar(10),
    country varchar(50)
);

create table orders (
	order_id int auto_increment primary key,
    customer_id int,
    employee_id int,
    order_date datetime,
    foreign key (customer_id) references customers(customer_id),
    foreign key (employee_id) references employees(employee_id)
);

insert into customers(customer_name, contact_name, address, city, postal_code, country) 
values 
	('Trần Bình', 'Trọng', 'Quận 8', 'HCM', 70000, 'VN'),
	('Tran Bao', 'An', 'Bình Thạnh', 'HCM', 70000, 'VN'),
	('Tasty', 'Finn', 'Streetroad 19B', 'Liverpool', 'L1 0AA', 'UK');

insert into employees (last_name, first_name, birth_date, supervisor_id) values ('Trần Văn An','Nguyễn', '2005-12-28',null),
('Bao An', 'Tran', '2006-12-28',1);
insert into orders (customer_id, employee_id, order_date) values (2,1,'2026-06-03 10:25:30');
update orders 
set customer_id=2 
where order_id=1;

select o.order_id, o.customer_id, c.customer_name
from orders o inner join customers c on o.customer_id = c.customer_id;

select c.customer_id, c.customer_name, o.order_id
from customers c left join orders o on c.customer_id = o.customer_id; 

select e1.employee_id, concat(e1.first_name,' ',e1.last_name) employee_name, e1.supervisor_id, concat(e2.first_name,'',e2.last_name) supervisor_name
from employees e1 inner join employees e2 on e1.supervisor_id = e2.employee_id;

select c1.customer_id, c1.customer_name, c2.country
from customers c1 inner join customers c2 on c1.customer_id <> c2.customer_id and c1.country = c2.country;

select o.order_id, c.customer_name, concat(e.first_name,'',e.last_name) employee_name, o.order_date
from (orders o inner join customers c on o.customer_id = c.customer_id) inner join employees e on o.employee_id = e.employee_id; 

create database BikeStores;
create table brands (
	brand_id int auto_increment primary key,
    brand_name varchar(255) not null
);

create table categories (
	category_id int auto_increment primary key,
    category_name varchar(255) not null
);

create table stores (
	store_id int auto_increment primary key,
    store_name varchar(255) not null,
    phone varchar(255),
    email varchar(255),
    street varchar(255),
    city varchar(255),
    state varchar(10),
    zip_code varchar(5)
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
	store_id int not null,
    product_id int not null,
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

insert into brands(brand_name) values('Electra')
,('Haro')
,('Heller')
,('Pure Cycles')
,('Ritchey')
,('Strider')
,('Sun Bicycles')
,('Surly')
,('Trek');

insert into categories(category_name) values('Children Bicycles')
,('Comfort Bicycles')
,('Cruisers Bicycles')
,('Cyclocross Bicycles')
,('Electric Bikes')
,('Mountain Bikes')
,('Road Bikes');

insert into products(product_name, brand_id, category_id, model_year, list_price) values('Trek 820 - 2016',9,6,2016,379.99)
,('Ritchey Timberwolf Frameset - 2016',5,6,2016,749.99)
,('Surly Wednesday Frameset - 2016',8,6,2016,999.99)
,('Trek Fuel EX 8 29 - 2016',9,6,2016,2899.99)
,('Heller Shagamaw Frame - 2016',3,6,2016,1320.99)
,('Surly Ice Cream Truck Frameset - 2016',8,6,2016,469.99)
,('Trek Slash 8 27.5 - 2016',9,6,2016,3999.99)
,('Trek Remedy 29 Carbon Frameset - 2016',9,6,2016,1799.99)
,('Trek Conduit+ - 2016',9,5,2016,2999.99)
,('Surly Straggler - 2016',8,4,2016,1549.00);

insert into customers(first_name, last_name, phone, email, street, city, state, zip_code) values ('Debra','Burks',null,'debra.burks@yahoo.com','9273 Thorne Ave.','Orchard Park','NY','14127')
,('Kasha','Todd',null,'kasha.todd@yahoo.com','910 Vine Street','Campbell','CA','95008')
,('Tameka','Fisher',null,'tameka.fisher@aol.com','769C Honey Creek St.','Redondo Beach','CA','90278')
,('Daryl','Spence',null,'daryl.spence@aol.com','988 Pearl Lane','Uniondale','NY','11553')
,('Charolette','Rice','(916) 381-6003','charolette.rice@msn.com','107 River Dr.','Sacramento','CA','95820');

insert into stores(store_name, phone, email, street, city, state, zip_code) values ('Santa Cruz Bikes','(831) 476-4321','santacruz@bikes.shop','3700 Portola Drive','Santa Cruz','CA','95060')
,('Baldwin Bikes','(516) 379-8888','baldwin@bikes.shop','4200 Chestnut Lane','Baldwin','NY','11432')
,('Rowlett Bikes','(972) 530-5555','rowlett@bikes.shop','8000 Fairway Avenue','Rowlett','TX','75088');

insert into stocks(store_id, product_id, quantity) values (1, 1, 27)
,(1, 2, 5)
,(1, 3, 6)
,(1, 4, 23)
,(1, 5, 22)
,(1, 6, 0)
,(1, 7, 8)
,(1, 8, 0)
,(1, 9, 11)
,(1, 10, 15);

insert into staffs(first_name, last_name, email, phone, active, store_id, manager_id) values ('Fabiola', 'Jackson', 'fabiola.jackson@bikes.shop', '(831) 555-5554', 1, 1, null)
,('Mireya', 'Copeland', 'mireya.copeland@bikes.shop', '(831) 555-5555', 1, 1, 1)
,('Genna', 'Serrano', 'genna.serrano@bikes.shop', '(831) 555-5556', 1, 1, 2)
,('Virgie', 'Wiggins', 'virgie.wiggins@bikes.shop', '(831) 555-5557', 1, 1, 2)
,('Jannette', 'David', 'jannette.david@bikes.shop', '(516) 379-4444', 1, 2, 1);

insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) values (1,4,'2016-01-01','2016-01-03','2016-01-03',1,2)
,(2,4,'2016-01-01','2016-01-04','2016-01-03',2,5)
,(3,4,'2016-01-02','2016-01-05','2016-01-03',2,5)
,(4,4,'2016-01-03','2016-01-04','2016-01-05',1,3)
,(5,4,'2016-01-03','2016-01-06','2016-01-06',2,4);

insert into order_items(order_id, item_id, product_id, quantity, list_price, discount) values (1,1,10,1,599.99,0.20)
,(1,2,8,2,1799.99,0.07)
,(1,3,10,2,1549.00,0.05)
,(1,4,10,2,599.99,0.05)
,(1,5,4,1,2899.99,0.20)
,(2,1,10,1,599.99,0.07)
,(2,2,10,2,599.99,0.05)
,(3,1,3,1,999.99,0.05)
,(3,2,10,1,599.99,0.05)
,(4,1,2,2,749.99,0.10);

select p.product_name, p.list_price, b.brand_name
from products p inner join brands b on p.brand_id = b.brand_id 
where p.list_price > 1000;

select c.customer_id, c.first_name, c.last_name, o.order_id, o.order_status
from customers c inner join orders o on c.customer_id = o.customer_id and o.order_status = 4;

select 
e.first_name employee_first_name, 
e.last_name employee_last_name, 
e.email employee_email, 
m.first_name manager_first_name, 
m.last_name manager_last_name, 
m.email manager_email
from staffs e inner join staffs m where e.manager_id = m.staff_id;

select p.product_name, b.brand_name
from products p left join brands b on p.brand_id = b.brand_id;

select product_name, model_year, brand_name
from products p inner join brands b on p.brand_id = b.brand_id
where model_year >= 2016;

select o.order_id, p.product_name, oi.quantity
from orders o 
inner join order_items oi on o.order_id = oi.order_id
inner join products p on oi.product_id = p.product_id;

select product_name, category_name
from products p inner join categories c on p.category_id = c.category_id
where c.category_name = 'Mountain Bikes';

select product_name, list_price, category_name, brand_name
from products p 
inner join brands b on p.brand_id = b.brand_id
inner join categories c on p.category_id = c.category_id
where p.list_price > 500 and c.category_name = 'Electric Bikes';

select c.customer_id, c.first_name, c.last_name, o.order_id, o.shipped_date
from customers c left join orders o on c.customer_id = o.customer_id and o.shipped_date is null;

select s.store_name, count(o.order_id) order_count
from stores s left join orders o on s.store_id = o.store_id
group by s.store_id;

select o.order_id, st.first_name, st.last_name, o.order_date
from orders o inner join staffs st on o.staff_id = st.staff_id
inner join stores s on o.store_id = s.store_id
where s.store_id = 1;

select concat(c.first_name,' ',c.last_name) customer_name, o.order_id, o.order_date
from customers c inner join orders o on c.customer_id = o.customer_id
where o.order_date like '2016%';

insert into customers(first_name, last_name, phone, email, street, city, state, zip_code)
values ('Fabiola', 'Jackson',null,'fabiola.jackson@bikes.shop',null,null,null,null);

insert into orders(customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id) 
values (6,4,'2025-07-01','2025-07-03','2025-07-03',1,2);

select c.first_name, c.last_name, o.order_id, o.order_date
from customers c inner join orders o on c.customer_id = o.customer_id
inner join staffs s on c.first_name = s.first_name and c.last_name = s.last_name
where o.order_date >= curdate() - interval 12 month; 
