CREATE DATABASE ecom;
use ecom;

CREATE TABLE if NOT EXISTS type (
    name char(32) PRIMARY KEY
);

CREATE TABLE if NOT EXISTS product (
    id int AUTO_INCREMENT PRIMARY KEY,
    type char(32) NOT NULL,
    name varchar(128) NOT NULL,
    price float NOT NULL,
    quantity int NOT NULL,
    image varchar(128),
    FOREIGN KEY (type) REFERENCES type(name)
);

CREATE TABLE if NOT EXISTS porder (
    id char(16) PRIMARY KEY,
    description varchar(512) NOT NULL,
    total float NOT NULL,
    date_time timestamp NOT NULL
);

CREATE TABLE if NOT EXISTS orderDetails (
    oid char(16) NOT NULL,
    pid int NOT NULL,
    quantity int NOT NULL,
    PRIMARY KEY(oid, pid),
    FOREIGN KEY (oid) REFERENCES porder(id),
    FOREIGN KEY (pid) REFERENCES product(id)
);

INSERT INTO type VALUES("Computer");
INSERT INTO type VALUES("Component");

INSERT INTO product(type, name, price, quantity, image) VALUES("Computer", "Default Computer" ,700.0, 10, "images/computer.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "Ben's Picture" ,4000.0, 10, "images/keyboard.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "GPU 3080 Ti", 1949.86, 10, "images/gpu.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "Keyboard", 89.99, 10, "images/keyboard.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "Microphone", 231.18, 10, "images/microphone.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "Monitor", 325.2, 10, "images/monitor.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "Mouse", 38.99, 10, "images/mouse.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "Speaker", 109.31, 10, "images/speaker.jpg");
INSERT INTO product(type, name, price, quantity, image) VALUES("Component", "USB HD camera", 205.49, 10, "images/camera.jpg");

ALTER TABLE product ADD type char(32) NOT NULL;
UPDATE product SET type="Component" 
INSERT INTO product(name, type, price, quantity, image) VALUES("Default Computer", "Computer", 700, 100, "images/computer.jpg");

