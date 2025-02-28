show tables;
drop table products;
drop table users;
select * from products_entity_image_paths peip ;
-- DROP TABLE Products;
-- DROP  table Users;
CREATE TABLE Users (
    uid CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- Should be encrypted in application logic
    role ENUM('User', 'Admin') NOT NULL,
    phoneNo CHAR(10) NOT NULL  -- Change it to phone
);

INSERT INTO Users (uid, name, email, password, role, phoneNo) VALUES
(UUID(), 'Alice Johnson', 'alice@example.com', 'hashed_password', 'User', '9876543210'),
(UUID(), 'Bob Smith', 'bob@example.com', 'hashed_password', 'Admin', '9876543211');


select * from products;

CREATE TABLE products (
    pid CHAR(36) PRIMARY KEY DEFAULT (UUID()),
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    image_paths varchar(255),  
    stock INT DEFAULT 0
);

INSERT INTO products (pid, name, description, price, category, image_paths, stock) 
VALUES (
    UUID(), 
    'Sofa Set', 
    'Comfortable 5-seater sofa', 
    15000.00, 
    'Furniture', 
    "/images/sofa1.jpg", 
    10
),(UUID(), 
    'Chair Set', 
    'Comfortable 5-seater chair', 
    1500.00, 
    'Furniture', 
    "images/chair1.jpg,images/chair2.jpg", 
    10);
INSERT INTO Products (pid, name, description, price, category, image_paths, stock) 
VALUES(UUID(), 
    'Electronic', 
    'TCL 139 cm (55 inches) 4K Ultra HD Smart QLED Google TV 55P71B Pro (Black)', 
    32990.00, 
    'Furniture', 
    '["https://m.media-amazon.com/images/I/81ZO3UviJKL._SX522_.jpg", 
    "https://m.media-amazon.com/images/I/61G9fbagi4L._SX522_.jpg", 
    "https://m.media-amazon.com/images/I/61-aX1+X7qL._SL1500_.jpg",
    "https://m.media-amazon.com/images/I/51RSVMguAfL._SL1500_.jpg",
    "https://m.media-amazon.com/images/I/71Ou8y7BEHL._SL1500_.jpg"]', 
    10),

(UUID(), 
    'Electronic', 
    'Kelly TV Unit in Walnut Finish for TVs up to 43"', 
    4749.00, 
    'Furniture', 
    '["https://ii1.pepperfry.com/media/catalog/product/k/e/1600x1760/kelly-wall-mount-tv-shelf-in-walnut-finish-by-anikaa-kelly-wall-mount-tv-shelf-in-walnut-finish-by-a-8i8huq.jpg",
    "https://ii1.pepperfry.com/media/catalog/product/k/e/1100x1210/kelly-tv-unit-in-walnut-finish-for-tvs-up-to-43--kelly-tv-unit-in-walnut-finish-for-tvs-up-to-43--jmpiv6.jpg",
    "https://ii1.pepperfry.com/media/catalog/product/k/e/1100x1210/kelly-wall-mount-tv-shelf-in-walnut-finish-by-anikaa-kelly-wall-mount-tv-shelf-in-walnut-finish-by-a-dken4x.jpg"]', 
    10),

(UUID(), 
    'Dining Table', 
    'Modern Six Seater Dining Table Set', 
    2000.00, 
    'Furniture', 
    '["https://ekbotefurniture.com/wp-content/uploads/2023/11/IMG_4209.jpg",
    "https://ekbotefurniture.com/wp-content/uploads/2023/11/MODERNISTDININGTABLESETDT12_50682aec-ad34-4f9b-a353-529b84816e77.jpg",
    "https://ekbotefurniture.com/wp-content/uploads/2023/11/1000_2e6b1584-836d-4be9-99fa-c97b39c19389.jpg"]', 
    10),

(
    UUID(), 
    'Sofa Set', 
    'L shape Comfortable 5-seater sofa', 
    15000.00, 
    'Furniture', 
    '["https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcRP9ZCnVyGwrZ9RkBbk6d4rB_4UJRY32aj_2wFskwxY-QIORf7cPOIkxg3nSdbgWrn8qJmE0t986YIobfZZJTM1sA_XeJXc0-vCGUTqWDA", 
    "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcSkaMYK6a64cTfDoOWZNcOSAEle0Ycn2PistvNKL2xyFcHMMsVe9gRLicEEI_esokOgwJEp2166lOBV7ey3QbZu-7DPTrkkhDAMUzejlRt7iShcVc5cKWJ2xQ",
    "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcT6-TNGC2d_u61xQ2aRPBUQaW92tCT56sxQBZxZzMSgicdQaXnivNazpdM-g1KbLlLQphJkULUZvJJ9jfwRrz6-TOjTCUQ0XbcfBuzzkyNo9g8RGE50azka",
    "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcS8bq11Fh23VvUmMRjYvlPoRVyX5NpzvRiLgixbUuSKTDFGv7XhV-zIRUAcgHvYu6d84DA245Wb_Kog6DwSDvSWIZaB8n0it3jDk0qKNkyF-P52ia1IvmPf"]', 
    10
),
(
    UUID(), 
    'Watson 3 Seater Sofa In Premium Brown Suede Fabric', 
    'Indulge in luxury with the Watson 3 Seater Sofa. Made with premium brown suede fabric, this sofa offers both comfort and style. Perfect for any living space, its sleek design and durable materials make it a must-have for any home. Upgrade your living room today with the Watson 3 Seater Sofa!',
    25000.00, 
    'Furniture', 
    '["https://www.getmycouch.com/cdn/shop/files/Untitleddesign_82.png?v=1704190597", 
      "https://www.getmycouch.com/cdn/shop/products/gmc_62e757a4-d2ce-4979-b7e8-3c7d4cfd177a.jpg?v=1704190501", 
      "https://www.getmycouch.com/cdn/shop/products/gmc-6_97ab445f-1dcb-44bf-b9c7-165f49d3227f.jpg?v=1704190501",
      "https://www.getmycouch.com/cdn/shop/products/gmc-3-Copy_94709a21-6be6-4e4f-aa6e-5d208278fa7f.jpg?v=1704190501"]', 
    10
);


select * from Products;

- - Insert Products
INSERT INTO Products (pid, name, description, price, category, image_paths, stock)
VALUES (
UUID(),
'Sofa Set',
'Comfortable 5-seater sofa',
15000.00,
'Furniture',
'["/images/sofa1.jpg", "/images/sofa2.jpg", "/images/sofa3.jpg"]',
10
),(UUID(),
'Chair Set',
'Comfortable 5-seater chair',
1500.00,
'Furniture',
'["/images/chair1.jpg", "/images/chair2.jpg", "/images/chair3.jpg"]',
10);

select * from Products;

- 
-- 
-- INSERT INTO Cart (product_name, product_price, cid, user_id, product_id, quantity)
-- VALUES
-- ('Sofa Set', 15000, UUID(),
-- (SELECT uid FROM Users WHERE name = 'Alice Johnson'),
-- (SELECT pid FROM Products WHERE name = 'Sofa Set'),
-- 1
-- ),
-- ('Dining Table', 12000, UUID(),
-- (SELECT uid FROM Users WHERE name = 'Bob Smith'),
-- (SELECT pid FROM Products WHERE name = 'Dining Table'),
-- 2
-- ),
-- ('LED TV', 40000, UUID(),
-- (SELECT uid FROM Users WHERE name = 'Charlie Brown'),
-- (SELECT pid FROM Products WHERE name = 'LED TV'),
-- 1
-- ),
-- ('Refrigerator', 35000, UUID(),
-- (SELECT uid FROM Users WHERE name = 'Alice Johnson'),
-- (SELECT pid FROM Products WHERE name = 'Refrigerator'),
-- 1
-- ),
-- ('Microwave Oven', 15000, UUID(),
-- (SELECT uid FROM Users WHERE name = 'David Williams'),
-- (SELECT pid FROM Products WHERE name = 'Microwave Oven'),
-- 1
-- );
-- 
-- select * from Cart;
-- 
-- - 
-- - - Insert Orders
-- INSERT INTO Orders (oid, user_id, total_price, status) VALUES
-- (UUID(), (SELECT uid FROM Users WHERE name = 'Alice Johnson'), 15000.00, 'Pending');
-- 
-- select * from Orders;
-- 
-- INSERT INTO Order_Items (oiid, order_id, product_id, quantity, price) VALUES
-- (UUID(),
-- (SELECT oid FROM Orders WHERE user_id = (SELECT uid FROM Users WHERE name = 'Alice Johnson') LIMIT 1),
-- (SELECT pid FROM Products WHERE name = 'Sofa Set'),
-- 1,
-- 15000.00
-- ),
-- (UUID(),
-- (SELECT oid FROM Orders WHERE user_id = (SELECT uid FROM Users WHERE name = 'Alice Johnson') LIMIT 1),
-- (SELECT pid FROM Products WHERE name = 'Chair Set'),
-- 2,
-- 3000.00
-- );
-- 
-- select * from Order_Items;
-- 
-- - -- Insert Discount Coupons
-- INSERT INTO DiscountCoupons (discount_id, code, discount, user_id, expiry_date) VALUES
-- (UUID(), 'NEWYEAR20', 20.00, (SELECT uid FROM Users WHERE name = 'Alice Johnson'), '2025-12-31');
-- - -- Insert Reviews
-- INSERT INTO Review (rid, user_id, product_id, rating, review_text) VALUES
-- (UUID(), (SELECT uid FROM Users WHERE name = 'Alice Johnson'), (SELECT pid FROM Products WHERE name = 'Sofa Set'), 5, 'Great quality and comfort!');

SELECT uid FROM users WHERE uid = '7FwBfBkrCtbcmjvzUVR2iTuGkty2';
SELECT uid FROM review WHERE uid = '7FwBfBkrCtbcmjvzUVR2iTuGkty2';

SHOW CREATE TABLE review;

SELECT re1_0.rid 
FROM review re1_0 
LEFT JOIN users ui1_0 ON ui1_0.uid = re1_0.uid 
WHERE ui1_0.uid = '7FwBfBkrCtbcmjvzUVR2iTuGkty2';

ALTER TABLE review DROP FOREIGN KEY fk_review_product;

ALTER TABLE review 
ADD CONSTRAINT fk_review_product FOREIGN KEY (product_id) 
REFERENCES products(pid) ON DELETE CASCADE;

SELECT * FROM products;
SELECT  * from review;

INSERT INTO review (product_id, uid, rating, review_text, created_at)
VALUES ('5e7d4824-d3ed-42b4-a341-e9029cbd88ff', '7FwBfBkrCtbcmjvzUVR2iTuGkty2', 4.5, 'Great product! Highly recommended.', '2025-02-26 06:04:12');



SELECT VERSION();
SHOW CREATE TABLE review;

SELECT * FROM products WHERE pid = '5e7d4824-d3ed-42b4-a341-e9029cbd88ff';
SELECT * FROM users WHERE uid = '7FwBfBkrCtbcmjvzUVR2iTuGkty2';



ALTER TABLE review
DROP FOREIGN KEY fk_review_product, -- Drop the foreign key constraint on product_id
DROP COLUMN product_id; -- Remove the redundant column

select * from review r;

SELECT * from products p ;

SELECT  * from products p  where pid="5e7d4824-d3ed-42b4-a341-e9029cbd88ff";
SELECT  * from users u  where uid="7FwBfBkrCtbcmjvzUVR2iTuGkty2";

INSERT INTO review (rid, created_at, rating, review_text, uid, pid) 
VALUES 
('R123', '2025-02-26 10:00:00.000000', 5, 'Great product!', '7FwBfBkrCtbcmjvzUVR2iTuGkty2', '5e7d4824-d3ed-42b4-a341-e9029cbd88ff');


SELECT  * from review r ;

show CREATE  table products ;

ALTER Table products modify column image_paths varchar(255);

show create table discount_coupons;

ALTER Table discount_coupons drop column user_id;


INSERT INTO orders (oid,uid,total_price,status,created_at)
VALUes
('1248','ezyU2bJBKUM2M9VBL2nbtJ0bia82','11.00','PENDING','2025-02-26 10:00:00.000000')

INSERT into cart(cid,name,product_price,quantity,uid,pid)
values
('124','sofa',8900.00,10,'ohTnwwMePgOhpKPddyTvWmdFNJ22','032833dc-6c09-41da-af62-b85d6253c976');

show CREATE  table cart ;
ALTER table cart drop column product_name;
ALTER table cart drop column user_id;
ALTER  table cart drop column product_id;

show CREATE  table discount_coupons ;
show tables;

ALTER table discount_coupons drop column is_applied;


ALTER TABLE `discount_coupons` 
MODIFY COLUMN `applied` BOOLE	AN NOT NULL DEFAULT FALSE;

SELECT  * from products p where pid='a83d5709-7b35-46c2-a7d6-05fa50283fba';
SELECT * from users where uid='ezyU2bJBKUM2M9VBL2nbtJ0bia82';

show create table discount_coupons_entity;