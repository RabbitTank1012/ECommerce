create database onlineshopping
use onlineshopping

SET FOREIGN_KEY_CHECKS=0;

drop table if exists user;
create table user
(
    user_id  int auto_increment primary key,
    username varchar(50)  not null,
    password varchar(50)  not null,
    email varchar(50)  not null,
    role     varchar(20)
);

INSERT INTO user (username, email, password, role) VALUES ( 'user1', 'user1@gamil.com','pass1',  'user');
INSERT INTO user (username, email, password, role) VALUES ( 'user2', 'user2@gamil.com','pass2',  'admin');
INSERT INTO user (username, email, password, role) VALUES ( 'user3', 'user3@gamil.com','pass3',  'user');
INSERT INTO user (username, email, password, role) VALUES ( 'user4', 'user4@gamil.com','pass4',  'user');
INSERT INTO user (username, email, password, role) VALUES ( 'user5', 'user5@gamil.com','pass5',  'user');



drop table if exists product;
create table product
(
    product_id  int auto_increment primary key,
    description varchar(255)  not null,
    name varchar(255)  not null,
    quantity     int   not null,
    retail_price double,
    whole_price double
);

INSERT INTO  product ( description, name,  quantity, retail_price,whole_price) VALUES ( 'This is a product1', 'product1', 100, 5.5,3.5);
INSERT INTO  product ( description, name,  quantity, retail_price,whole_price) VALUES ( 'This is a product2', 'product2', 200, 6.5,4.5);
INSERT INTO  product ( description, name,  quantity, retail_price,whole_price) VALUES ( 'This is a product3', 'product3', 300, 7.5,5.5);
INSERT INTO  product ( description, name,  quantity, retail_price,whole_price) VALUES ( 'This is a product4', 'product4', 400, 8.5,6.5);
INSERT INTO  product ( description, name,  quantity, retail_price,whole_price) VALUES ( 'This is a product5', 'product5', 500, 9.5,7.5);
INSERT INTO  product ( description, name,  quantity, retail_price,whole_price) VALUES ( 'This is a product6', 'product6', 500, 11.5,13.5);

drop table if exists orders;
CREATE TABLE orders (
                        order_id int NOT NULL AUTO_INCREMENT  primary key,
                        date_created varchar(50) not null,
                        order_status varchar(255) not null,
                        user_id int not null,
                        CONSTRAINT `FK_userorder_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

INSERT INTO  orders(date_created,order_status,user_id) values('2024-01-04 22:31:34','cancel',1);
INSERT INTO  orders(date_created,order_status,user_id) values('2024-01-04 23:31:34','proceeding',1);
INSERT INTO  orders(date_created,order_status,user_id) values('2024-01-05 20:31:34','proceeding',1);
INSERT INTO  orders(date_created,order_status,user_id) values('2024-01-05 20:39:34','completed',3);
INSERT INTO  orders(date_created,order_status,user_id) values('2024-01-05 20:55:34','completed',3);
INSERT INTO  orders(date_created,order_status,user_id) values('2024-01-05 21:39:34','completed',3);

drop table if exists order_item;
CREATE TABLE order_item (
                            item_id int NOT NULL AUTO_INCREMENT  primary key,
                            purchased_price double not null,
                            quantity int not null,
                            wholesale_price double not null,
                            order_id int not null,
                            product_id int not null,
                            CONSTRAINT `FK_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
                            CONSTRAINT `FK_productorderitem_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(5.5,2,3.5,1,1);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(6.5,5,4.5,1,2);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(7.5,4,5.5,1,3);

INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(5.5,10,3.5,2,1);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(6.5,7,4.5,2,2);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(7.5,9,5.5,2,3);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(8.5,9,6.5,2,4);


INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(7.5,2,5.5,3,3);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(8.5,9,6.5,3,5);

INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(7.5,12,5.5,4,3);
INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(9.5,19,7.5,4,5);

INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(9.5,19,7.5,5,5);

INSERT INTO  order_item(purchased_price,quantity, wholesale_price,order_id,product_id) values(13.5,3,11.5,6,6);


drop table if exists watchlist;
create table watchlist
(
    watch_id int auto_increment primary key,
    user_id  int ,
    product_id  int ,
    CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
    CONSTRAINT `FK_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
);

INSERT INTO watchlist (user_id, product_id) VALUES(1,1);
INSERT INTO watchlist (user_id, product_id) VALUES(1,2);
INSERT INTO watchlist (user_id, product_id) VALUES(1,3);
INSERT INTO watchlist (user_id, product_id) VALUES(2,1);
