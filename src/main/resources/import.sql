
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1985-02-14","+34600123456","Madrid","Carlos","Manager","Gomez","TechCorp","28001","Calle Mayor, 10");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1990-07-22","+34600234567","Barcelona","Ana","Developer","Martinez","Innovatech","08002","Carrer de Balmes, 20");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1978-11-30","+34600345678","Valencia","Jose","Analyst","Perez","DataSol","46001","Avenida del Puerto, 15");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1988-03-25","+34600456789","Sevilla","Maria","Designer","Lopez","Creativa","41001","Calle Sierpes, 30");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1995-09-18","+34600567890","Bilbao","Luis","Sales","Hernandez","MarketInc","48001","Gran Via, 50");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1982-05-09","+34600678901","Zaragoza","Laura","Consultant","Sanchez","ConsultPlus","50001","Paseo de la Independencia, 5");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1992-10-10","+34600789012","Malaga","David","Engineer","Fernandez","BuildTech","29001","Calle Larios, 25");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1975-06-27","+34600890123","Alicante","Isabel","HR","Gonzalez","PeopleCorp","03001","Avenida de la Estacion, 12");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1989-12-05","+34600901234","Granada","Javier","Marketing","Torres","PromoStar","18001","Plaza Nueva, 7");
INSERT INTO client(bdate, client_phone, city, client_name, client_position, client_surname, company_name, zip_code, client_address) VALUES ("1993-04-16","+34600123457","Santander","Elena","Accountant","Romero","FinancePro","39001","Calle del Medio, 3");

INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-01-10", 29.99, 150, "Wireless Mouse", "Electronics", "TechCorp", "Ergonomic wireless mouse with adjustable DPI.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-01-15", 59.99, 100, "Bluetooth Headphones", "Electronics", "SoundWave", "Noise-cancelling over-ear Bluetooth headphones.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-02-05", 15.99, 300, "Water Bottle", "Accessories", "HydrateCo", "Stainless steel water bottle with insulation.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-02-20", 199.99, 50, "Smart Watch", "Electronics", "WearTech", "Fitness tracking and notifications with heart rate monitor.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-03-01", 89.99, 200, "Mechanical Keyboard", "Electronics", "KeyMaster", "RGB backlit mechanical keyboard with customizable keys.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-03-10", 24.99, 500, "Yoga Mat", "Fitness", "FitLife", "Non-slip yoga mat with extra cushioning.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-03-25", 39.99, 120, "Portable Charger", "Electronics", "PowerBoost", "High-capacity portable charger with dual USB ports.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-04-05", 99.99, 80, "Digital Camera", "Electronics", "CapturePro", "Compact digital camera with high-resolution sensor.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-04-20", 14.99, 400, "Notebook", "Stationery", "NoteWorks", "Hardcover notebook with premium paper.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-05-01", 249.99, 40, "Gaming Console", "Electronics", "Gamerz", "Next-gen gaming console with 4K graphics.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-05-15", 49.99, 250, "Wireless Charger", "Electronics", "ChargeEase", "Fast wireless charger compatible with multiple devices.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-06-01", 9.99, 600, "Phone Case", "Accessories", "ProtectIt", "Durable phone case with shock absorption.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-06-20", 79.99, 110, "Noise-Cancelling Earbuds", "Electronics", "SoundWave", "In-ear noise-cancelling Bluetooth earbuds.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-07-01", 89.99, 130, "Smart Thermostat", "Home", "EcoHome", "Programmable smart thermostat with remote control.");
INSERT INTO product(create_date, price_unit, prod_stock, prod_name, prod_type, provider_name, prod_description) VALUES ("2024-07-15", 199.99, 70, "4K Monitor", "Electronics", "ViewPlus", "27-inch 4K UHD monitor with HDR.");

INSERT INTO role(name) VALUES ("ADMIN");
INSERT INTO role(name) VALUES ("USER");

INSERT INTO user(username, password) VALUES ("pg@gmail.com", "$2a$10$7QvHkQjZyB6z2O/5xGvQFeKlbJJ58sf.UO0lhb4DpZT6XNZa5f9GS"); -- Contraseña cifrada "password"

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1);

INSERT INTO purchase(pquantity, ptotal, purchase_date, total_vat, vat, client_id, product_id) VALUES (3, 749.97, "2023-04-06", 157.49, 0.21, 1, 10);
INSERT INTO purchase(pquantity, ptotal, purchase_date, total_vat, vat, client_id, product_id) VALUES (5, 49.95, "2023-04-06", 10.49, 0.21, 3, 12);
INSERT INTO purchase(pquantity, ptotal, purchase_date, total_vat, vat, client_id, product_id) VALUES (2, 179.98, "2023-04-06", 37.80, 0.21, 7, 5);
INSERT INTO purchase(pquantity, ptotal, purchase_date, total_vat, vat, client_id, product_id) VALUES (4, 399.96, "2023-04-06", 83.99, 0.21, 9, 8);
INSERT INTO purchase(pquantity, ptotal, purchase_date, total_vat, vat, client_id, product_id) VALUES (1, 199.99, "2023-04-06", 42.00, 0.21, 4, 15);





