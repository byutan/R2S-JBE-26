create database if not exists sms;
use sms;
create table Customer (
	customer_id int auto_increment primary key,
    customer_name varchar(255) not null
);

create table Employee (
	employee_id int auto_increment primary key,
    employee_name varchar(255) not null,
    salary decimal(10,2) not null,
    supervisor_id int,
    foreign key (supervisor_id) references Employee(employee_id)
);

create table Product (
	product_id int auto_increment primary key,
    product_name varchar(255) not null,
    list_price decimal(10,2) not null
);

create table Orders (
	order_id int auto_increment primary key,
    order_date datetime not null,
    customer_id int not null,
    employee_id int not null,
	total decimal(10,2),
    foreign key (customer_id) references Customer(customer_id),
    foreign key (employee_id) references Employee(employee_id)
);

create table LineItem (
	order_id int not null,
    product_id int not null,
    quantity int not null,
    price decimal(10,2),
    foreign key (order_id) references Orders(order_id),
    foreign key (product_id) references Product(product_id),
    primary key (order_id, product_id)
);

insert into Customer(customer_name) values 
('Nguyễn Văn A'),
('Trần Thị B'),
('Lê Hoàng C');

insert into Employee(employee_name, salary, supervisor_id) values
('Quản lý cấp cao', 50000.00, null),
('Nhân viên kinh doanh 1', 20000.00, 1),
('Nhân viên kinh doanh 2', 22000.00, 1);

insert into Product(product_name, list_price) values 
('Laptop Dell XPS', 1500.00),
('Chuột Logitech', 50.00),
('Màn hình LG', 300.00);

insert into Orders(order_date, customer_id, employee_id, total) values 
('2026-06-04 09:00:00', 1, 2, 1500.00),
('2026-06-04 10:00:00', 2, 3, 300.00);

insert into LineItem(order_id, product_id, quantity, price) values 
(1, 1, 1, 1500.00),
(1, 2, 1, 50.00),
(2, 3, 1, 300.00);

DELIMITER //
create procedure get_all_customer_with_order()
begin 
	select o.customer_id,  c.customer_name
    from Customer c inner join Orders o on c.customer_id = o.customer_id;
end //
DELIMITER ; 
call get_all_customer_with_order();

delimiter //
create procedure get_order_info_for_customer(in customer_id int)
begin
	select order_id, order_date, customer_id, employee_id, total
    from Orders
    where Orders.customer_id = customer_id;
end //
delimiter ;
call get_order_info_for_customer(1);

delimiter //
create procedure get_line_item_for_an_order(in order_id int) 
begin 
	select *
    from LineItem
    where LineItem.order_id = order_id;
end //
delimiter ;
call get_line_item_for_an_order(1);

delimiter //
create function get_order_total_of_line_item_for_an_order(order_id int)
returns decimal(10,2) reads sql data
begin 
	declare order_total decimal(10,2);
    select sum(quantity * price)
    into order_total
	from LineItem
    where LineItem.order_id = order_id;
    return order_total;
end //
delimiter ;
select get_order_total_of_line_item_for_an_order(1);

delimiter //
create procedure add_customer(in customer_name varchar(255))
begin
	insert into Customer(customer_name) values (customer_name);
end // 
delimiter ;
call add_customer('Nguyễn Hữu Thắng');

delimiter //
create procedure delete_customer(in d_customer_id int)
begin
	delete from LineItem where order_id in (select order_id from Orders where customer_id = d_customer_id);
    delete from Orders where customer_id = d_customer_id;
    delete from Customer where customer_id = d_customer_id;
end //
delimiter ;
call delete_customer(1);
select * from Customer;

delimiter //
create procedure update_customer(in u_customer_name varchar(255), in u_customer_id int)
begin
	update Customer set customer_name = u_customer_name where customer_id = u_customer_id;
end // 
delimiter ;
call update_customer('Đỗ Mạnh Thắng', 4);
select * from Customer where customer_id=4;

delimiter //
create procedure create_order(in i_order_date datetime, in i_customer_id int, in i_employee_id int, in i_total decimal(10,2))
begin
	insert into Orders(order_date, customer_id, employee_id, total) values 
    (i_order_date, i_customer_id, i_employee_id, i_total);
end //
delimiter ; 
call create_order('2026-06-04 17:30:00', 2, 2, 500.00);

delimiter //
create procedure create_item(in order_id int, product_id int, in quantity int,in price decimal(10,2))
begin
	insert into LineItem(order_id, product_id, quantity, price) values (order_id, product_id, quantity, price);
end //
delimiter ;
call create_item(2, 1, 2, 1500.00);

delimiter //
create procedure update_order_total(in p_order_id int)
begin
	update Orders set total = (select sum(quantity * total) from LineItem where order_id = p_order_id)
    where order_id = p_order_id;
end//
delimiter ;
call update_order_total(2);