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

INSERT INTO product (product_name, price, content, description, href_param, category_id, brand_id) VALUES
(
    "iPhone 15",
    30000000,
    "<div><h3>iPhone 15</h3><p>Tận hưởng công nghệ hàng đầu với:</p><ul><li><strong>Màn hình:</strong> Super Retina XDR 6.1 inch</li><li><strong>Chip:</strong> A16 Bionic</li><li><strong>Camera:</strong> 48MP, quay video 4K</li><li><strong>Pin:</strong> 3279mAh, sạc nhanh 20W</li></ul><p>Thiết kế tinh tế, hiệu năng mạnh mẽ.</p></div>",
    "<p><strong>iPhone 15</strong> - Smartphone cao cấp với hiệu năng vượt trội và camera chất lượng.</p>",
    "iphone-15",
    1,
    1
),
(
    "iPhone 15 Pro",
    35000000,
    "<div><h3>iPhone 15 Pro</h3><p>Đỉnh cao sáng tạo cùng:</p><ul><li><strong>Chip:</strong> A17 Pro</li><li><strong>RAM:</strong> 8GB, đa nhiệm siêu mượt</li><li><strong>Camera:</strong> 48MP + 12MP tele, zoom quang 3x</li><li><strong>Pin:</strong> 3323mAh, hỗ trợ MagSafe</li></ul><p>Hoàn hảo cho nhiếp ảnh và làm việc chuyên nghiệp.</p></div>",
    "<p><strong>iPhone 15 Pro</strong> - Smartphone chuyên nghiệp với thiết lập camera mạnh mẽ và hiệu năng vượt trội.</p>",
    "iphone-15-pro",
    1,
    1
),
(
    "iPhone 14",
    25000000,
    "<div><h3>iPhone 14</h3><p>Trải nghiệm mượt mà với:</p><ul><li><strong>Chip:</strong> A15 Bionic</li><li><strong>Màn hình:</strong> Super Retina XDR 6.1 inch</li><li><strong>Camera:</strong> 12MP kép, chụp ban đêm chi tiết</li><li><strong>Pin:</strong> 3279mAh, sạc nhanh 20W</li></ul><p>Giải pháp cân bằng giữa hiệu năng và giá cả.</p></div>",
    "<p><strong>iPhone 14</strong> - Smartphone mạnh mẽ, thiết kế bền bỉ, giá hợp lý.</p>",
    "iphone-14",
    1,
    1
),
(
    "iPhone SE 2022",
    12000000,
    "<div><h3>iPhone SE (2022)</h3><p>Hiệu năng cao trong thiết kế nhỏ gọn:</p><ul><li><strong>Chip:</strong> A15 Bionic</li><li><strong>Màn hình:</strong> Retina HD 4.7 inch</li><li><strong>Camera:</strong> 12MP đơn, Portrait Mode</li><li><strong>Pin:</strong> 2018mAh, sạc nhanh 18W</li></ul><p>Lựa chọn lý tưởng cho người thích kích thước gọn.</p></div>",
    "<p><strong>iPhone SE 2022</strong> - Smartphone nhỏ gọn, hiệu năng flagship.</p>",
    "iphone-se-2022",
    1,
    1
),
(
    "Samsung Galaxy S23 Ultra",
    28000000,
    "<div><h3>Samsung Galaxy S23 Ultra</h3><p>Đỉnh cao công nghệ Galaxy:</p><ul><li><strong>Màn hình:</strong> Dynamic AMOLED 6.8 inch, 120Hz</li><li><strong>Chip:</strong> Snapdragon 8 Gen 2</li><li><strong>Camera:</strong> 200MP, zoom 10x</li><li><strong>Pin:</strong> 5000mAh, sạc nhanh 45W</li></ul><p>Hoàn hảo cho chụp ảnh và giải trí.</p></div>",
    "<p><strong>Galaxy S23 Ultra</strong> - Smartphone cao cấp với camera đỉnh và hiệu năng bất bại.</p>",
    "samsung-galaxy-s23-ultra",
    1,
    2
),
(
    "Samsung Galaxy A54",
    10000000,
    "<div><h3>Samsung Galaxy A54</h3><p>Sang trọng trong tầm giá:</p><ul><li><strong>Màn hình:</strong> Super AMOLED 6.4 inch</li><li><strong>Camera:</strong> 50MP + 12MP + 5MP</li><li><strong>Pin:</strong> 5000mAh, sạc nhanh 25W</li><li><strong>Chip:</strong> Exynos 1380</li></ul><p>Phù hợp sử dụng hàng ngày, giá phải chăng.</p></div>",
    "<p><strong>Galaxy A54</strong> - Smartphone tầm trung nổi bật với camera sắc nét.</p>",
    "samsung-galaxy-a54",
    1,
    2
),
(
    "Samsung Galaxy Z Flip5",
    20000000,
    "<div><h3>Samsung Galaxy Z Flip5</h3><p>Độc đáo với màn hình gập:</p><ul><li><strong>Màn hình:</strong> Dynamic AMOLED 6.7 inch, gập dọc</li><li><strong>Chip:</strong> Snapdragon 8 Gen 2</li><li><strong>Camera:</strong> 12MP kép</li><li><strong>Pin:</strong> 3700mAh, sạc nhanh 25W</li></ul><p>Phong cách và tiện lợi.</p></div>",
    "<p><strong>Galaxy Z Flip5</strong> - Smartphone gập linh hoạt, dễ dàng mang theo.</p>",
    "samsung-galaxy-z-flip5",
    1,
    2
),
(
    "Samsung Galaxy Z Fold5",
    45000000,
    "<div><h3>Samsung Galaxy Z Fold5</h3><p>Trải nghiệm máy tính bảng gập:</p><ul><li><strong>Màn hình chính:</strong> Dynamic AMOLED 7.6 inch</li><li><strong>Chip:</strong> Snapdragon 8 Gen 2</li><li><strong>Camera:</strong> 50MP + 12MP + 10MP</li><li><strong>Pin:</strong> 4400mAh, sạc nhanh 25W</li></ul><p>Kết hợp điện thoại và tablet trong một.</p></div>",
    "<p><strong>Galaxy Z Fold5</strong> - Đỉnh cao công nghệ gập mở, đa nhiệm hiệu quả.</p>",
    "samsung-galaxy-z-fold5",
    1,
    2
),
(
    "Apple MacBook Air M2",
    28000000,
    "<div><h3>MacBook Air M2</h3><p>Siêu mỏng nhẹ với chip M2:</p><ul><li><strong>Chip:</strong> Apple M2</li><li><strong>RAM:</strong> 8GB</li><li><strong>SSD:</strong> 256GB</li><li><strong>Màn hình:</strong> Retina 13.6 inch</li><li><strong>Pin:</strong> Lên đến 18 giờ</li></ul><p>Phù hợp học tập và văn phòng.</p></div>",
    "<p><strong>MacBook Air M2</strong> - Laptop mỏng nhẹ, hiệu năng ổn định cho công việc hàng ngày.</p>",
    "macbook-air-m2",
    2,
    1
),
(
    "Apple MacBook Pro 16",
    50000000,
    "<div><h3>MacBook Pro 16 inch 2023</h3><p>Laptop đỉnh cao cho chuyên gia:</p><ul><li><strong>Chip:</strong> M2 Max</li><li><strong>RAM:</strong> 32GB</li><li><strong>SSD:</strong> 1TB</li><li><strong>Màn hình:</strong> Liquid Retina XDR</li><li><strong>Pin:</strong> Lên đến 22 giờ</li></ul><p>Lý tưởng cho dựng phim, đồ họa cao cấp.</p></div>",
    "<p><strong>MacBook Pro 16</strong> - Laptop mạnh mẽ, màn hình pro-level, pin trâu.</p>",
    "macbook-pro-16-2023",
    2,
    1
),
(
    "Samsung Galaxy Book3",
    30000000,
    "<div><h3>Samsung Galaxy Book3</h3><p>Laptop gọn nhẹ hiệu năng cao:</p><ul><li><strong>Chip:</strong> Intel Core i7-1360P</li><li><strong>RAM:</strong> 16GB</li><li><strong>SSD:</strong> 512GB</li><li><strong>Màn hình:</strong> 15.6 inch FHD</li><li><strong>Pin:</strong> 54Wh</li></ul><p>Phù hợp học tập và làm việc di động.</p></div>",
    "<p><strong>Galaxy Book3</strong> - Laptop Samsung mỏng nhẹ, hiệu năng ổn định.</p>",
    "samsung-galaxy-book3",
    2,
    2
),
(
    "Samsung Notebook 9",
    20000000,
    "<div><h3>Samsung Notebook 9</h3><p>Siêu di động, bền bỉ:</p><ul><li><strong>Chip:</strong> Intel Core i5-1135G7</li><li><strong>RAM:</strong> 8GB</li><li><strong>SSD:</strong> 256GB</li><li><strong>Màn hình:</strong> 13.3 inch FHD</li><li><strong>Pin:</strong> 60Wh</li></ul><p>Thiết kế kim loại chắc chắn, trọng lượng nhẹ.</p></div>",
    "<p><strong>Notebook 9</strong> - Laptop mỏng nhẹ, di chuyển thuận tiện.</p>",
    "samsung-notebook-9",
    2,
    2
),
(
    "MSI Stealth 15M",
    40000000,
    "<div><h3>MSI Stealth 15M</h3><p>Laptop gaming mỏng nhẹ:</p><ul><li><strong>CPU:</strong> Intel Core i7-11375H</li><li><strong>GPU:</strong> NVIDIA RTX 3060</li><li><strong>RAM:</strong> 16GB</li><li><strong>Màn hình:</strong> 15.6 inch 144Hz</li></ul><p>Thân máy mỏng 17.9mm, di động cao.</p></div>",
    "<p><strong>Stealth 15M</strong> - Laptop gaming mỏng nhẹ, hiệu năng ổn định.</p>",
    "msi-stealth-15m",
    2,
    3
),
(
    "MSI Raider GE78",
    60000000,
    "<div><h3>MSI Raider GE78</h3><p>Máy trạm gaming đỉnh cao:</p><ul><li><strong>CPU:</strong> Intel Core i9-13980HX</li><li><strong>GPU:</strong> NVIDIA RTX 4090</li><li><strong>RAM:</strong> 32GB</li><li><strong>Màn hình:</strong> 17.3 inch 240Hz</li></ul><p>Chiến mọi tựa game nặng nhất.</p></div>",
    "<p><strong>Raider GE78</strong> - Laptop gaming cao cấp với cấu hình “khủng”.</p>",
    "msi-raider-ge78",
    2,
    3
),
(
    "MSI Modern 14",
    18000000,
    "<div><h3>MSI Modern 14</h3><p>Laptop văn phòng thanh lịch:</p><ul><li><strong>CPU:</strong> Intel Core i5-1135G7</li><li><strong>RAM:</strong> 8GB</li><li><strong>SSD:</strong> 512GB</li><li><strong>Màn hình:</strong> 14 inch FHD</li></ul><p>Thiết kế mỏng, phù hợp di chuyển.</p></div>",
    "<p><strong>Modern 14</strong> - Laptop văn phòng gọn nhẹ, hiệu quả.</p>",
    "msi-modern-14",
    2,
    3
),
(
    "MSI Creator Z16",
    45000000,
    "<div><h3>MSI Creator Z16</h3><p>Laptop cho sáng tạo nội dung:</p><ul><li><strong>CPU:</strong> Intel Core i9-12900H</li><li><strong>GPU:</strong> NVIDIA RTX 3070 Ti</li><li><strong>RAM:</strong> 32GB</li><li><strong>Màn hình:</strong> 16 inch QHD+ Touch</li></ul><p>Hiển thị chính xác, phục vụ đồ họa chuyên sâu.</p></div>",
    "<p><strong>Creator Z16</strong> - Laptop cho designer và biên tập video chuyên nghiệp.</p>",
    "msi-creator-z16",
    2,
    3
),
(
    "MSI Prestige 14",
    30000000,
    "<div><h3>MSI Prestige 14</h3><p>Laptop doanh nhân cao cấp:</p><ul><li><strong>CPU:</strong> Intel Core i7-12710U</li><li><strong>RAM:</strong> 16GB</li><li><strong>SSD:</strong> 1TB</li><li><strong>Màn hình:</strong> 14 inch UHD</li></ul><p>Thiết kế sang trọng, hiệu năng ổn định.</p></div>",
    "<p><strong>Prestige 14</strong> - Laptop mỏng nhẹ, phù hợp doanh nhân và nhà sáng tạo.</p>",
    "msi-prestige-14",
    2,
    3
);
