create database mana_product_db;

use mana_product_db;

create table category (
	id int primary key auto_increment not null,
    category_name varchar(50) not null
);

create table brand (
	id int auto_increment primary key not null,
    brand_name varchar(100) not null
);

create table product (
	id int primary key auto_increment not null,
    product_name varchar(255) not null,
	price float not null,
    content text not null,
    description text not null,
    href_param varchar(255) not null,
    category_id int not null,
    brand_id int not null,
    foreign key (category_id) references category(id),
    foreign key (brand_id) references brand(id)
);

create table promotional (
	id int primary key auto_increment not null,
    product_id int not null,
    promotional int not null,
    promotional_start datetime,
    promotional_end datetime,
    foreign key (product_id) references product(id)
);

INSERT INTO category(category_name) VALUES 
("Phone"),
("Laptop");

INSERT INTO brand(brand_name) VALUES
("Apple"),
("Samsung"),
("MSI");

INSERT INTO product (product_name, price, content, description, href_param, category_id, brand_id) VALUES
(
    "Samsung Galaxy S23", 
    20000000, 
    "<div><h3>Điện thoại Samsung Galaxy S23</h3><p>Trải nghiệm công nghệ đỉnh cao với:</p><ul><li><strong>Màn hình:</strong> AMOLED 6.1 inch, Full HD+</li><li><strong>Chip:</strong> Snapdragon 8 Gen 2</li><li><strong>Camera:</strong> 50MP, chụp ảnh sắc nét</li><li><strong>Pin:</strong> 3900mAh, sạc nhanh 25W</li></ul><p>Phù hợp cho cả giải trí và công việc.</p></div>", 
    "<p><strong>Galaxy S23</strong> - Smartphone cao cấp với thiết kế sang trọng, hiệu năng mạnh mẽ và camera chất lượng cao.</p>", 
    "samsung-galaxy-s23", 
    1, 
    2
),
(
    "Apple MacBook Pro 14", 
    35000000, 
    "<div><h3>MacBook Pro 14 inch 2023</h3><p>Laptop mạnh mẽ cho mọi nhu cầu sáng tạo:</p><ul><li><strong>Chip:</strong> M2 Pro, hiệu năng vượt trội</li><li><strong>RAM:</strong> 16GB, đa nhiệm mượt mà</li><li><strong>SSD:</strong> 512GB, lưu trữ nhanh chóng</li><li><strong>Màn hình:</strong> Liquid Retina XDR, màu sắc chân thực</li><li><strong>Pin:</strong> Lên đến 18 giờ</li></ul><p>Hoàn hảo cho nhà thiết kế, lập trình viên và biên tập video.</p></div>", 
    "<p><strong>MacBook Pro 14</strong> - Laptop chuyên nghiệp với hiệu năng cao, màn hình tuyệt đẹp.</p>", 
    "macbook-pro-14-2023", 
    2, 
    1
),
(
    "MSI Gaming Katana GF66", 
    25000000, 
    "<div><h3>Laptop Gaming MSI Katana GF66</h3><p>Chiến mọi tựa game với:</p><ul><li><strong>CPU:</strong> Intel Core i7-12700H</li><li><strong>GPU:</strong> NVIDIA RTX 3060</li><li><strong>RAM:</strong> 16GB, tốc độ cao</li><li><strong>Màn hình:</strong> 15.6 inch, tần số quét 144Hz</li></ul><p>Thiết kế mạnh mẽ, tản nhiệt tối ưu, dành riêng cho game thủ.</p></div>", 
    "<p><strong>MSI Katana GF66</strong> - Laptop gaming hiệu năng cao, trải nghiệm game mượt mà.</p>", 
    "msi-katana-gf66", 
    2, 
    3
);

INSERT INTO promotional (product_id, promotional, promotional_start, promotional_end) VALUES
(1, 10, "2025-04-20 00:00:00", "2025-05-20 23:59:59"),
(2, 5, "2025-04-25 00:00:00", "2025-05-10 23:59:59"),
(3, 15, "2025-04-22 00:00:00", "2025-04-30 23:59:59");
