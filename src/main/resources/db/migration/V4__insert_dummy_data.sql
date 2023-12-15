INSERT INTO users(password,username,user_email,id) VALUES('$2a$10$ifp3nB7j9nokYDn/EAtTBObpjTk0CtZ2t7vgbO//QvnvpwOxvYbOS','testing','testing1@gmail.com','01HHP4GVCVN1P5FY8YYX9YXKN8');
INSERT INTO users(id,username,user_email,password) VALUES('01HHAWPJ6068YT0685RZ5AY3XF','su','test@gmail.com','$2a$10$xoRl/KKuoriNmgsoZwsrMOwm.5I02Wr/apjqn6hIpI0fhanUBMEwS');
INSERT INTO users(password,username,user_email,id) VALUES('$2a$10$ifp3nB7j9nokYDn/EAtTBObpjTk0CtZ2t7vgbO//QvnvpwOxvYbOS','testing3','testing3@gmail.com','1');

INSERT INTO status(status_name) VALUES ('pending'),('in-progress'),('aborted'),('rejected'),('completed');

INSERT INTO order_status(order_status) VALUES ('created'),('in-progress'),('shipped'),('completed');

INSERT INTO location(location_name) VALUES   ('🇺🇸 United States'),
                                             ('🇨🇳 China'),
                                             ('🇯🇵 Japan'),
                                             ('🇬🇧 United Kingdom'),
                                             ('🇫🇷 France'),
                                             ('🇩🇪 Germany '),
                                             ('🇦🇪 United Arab Emirates'),
                                             ('🇰🇷 South Korea'),
                                             ('🇮🇹 Italy '),
                                             ('🇸🇬 Singapore');

INSERT INTO provider_verification(user_id, status_id, id_document, address_document) VALUES ('1', '1', 'A123456', '123 Fake Street');

INSERT INTO provider(id,user_id,location_id,provider_verification_id) VALUES ('1', '1', '1', '1');

INSERT INTO logistic_company(company_name, company_url) VALUES ('DHL', 'https://www.dhl.com'),
                                                        ('FedEx', 'https://www.fedex.com'),
                                                        ('UPS', 'https://www.ups.com'),
                                                        ('TNT Express', 'https://www.tnt.com'),
                                                        ('SF Express', 'https://www.sf-express.com');

INSERT INTO category(category_name) VALUES ('Clothes'),('Figure');

INSERT INTO category_field(category_id, is_option,category_field_name) VALUES ('1',TRUE,'apparel_type'),
                                                                               ('1',TRUE,'gender'),
                                                                                ('1',TRUE,'size'),
                                                                                 ('1',TRUE,'color'),
                                                                                  ('1', FALSE, 'brand');



INSERT INTO category_field_option(category_field_id, category_field_option_name) VALUES ('1','T-shirt'),
                                                                                 ('1','Jeans'),
                                                                                 ('1','Dress'),
                                                                                 ('2','Men'),
                                                                                 ('2','Women'),
                                                                                 ('3','Small'),
                                                                                 ('3','Medium'),
                                                                                 ('3','Large'),
                                                                                 ('4','Red'),
                                                                                 ('4','Blue'),
                                                                                 ('4','Green'),
                                                                                 ('4','Yellow'),
                                                                                 ('4','Orange'),
                                                                                 ('4','Purple'),
                                                                                 ('4','Pink'),
                                                                                 ('4','Black'),
                                                                                 ('4','White'),
                                                                                 ('4','Gray');

INSERT INTO request(id, created_by, location_id, category_id, item, item_detail, url, quantity, request_remark, offer_price, has_offer, is_active, created_at, updated_at, primary_image)
VALUES
  ('01HHKFYXSSTKGTSGJ4TPMC096E','1', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.uniqlo.com.hk/zh_HK/index.html', 1, '男裝 無縫羽絨連帽外套', 900, FALSE, true, '2023-12-12 20:20:17.367665', '2023-12-12 20:20:17.367665', 'http://dummyimage.com/225x100.png/5fa2dd/ffffff'),
  ('01HHKFYXST6C48MQ886K9EARM5','1', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.dior.com/zh_hk/fashion/products', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:21:05.972918', '2023-12-12 20:21:05.972918','http://dummyimage.com/210x100.png/cc0000/ffffff'),
  ('01HHKFYXSTSBKPZX0HYDZ85KDE','1', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.ssense.com/en-hk/men/product/wooyoungmi/off-white-crewneck-sweater', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:21:59.936233', '2023-12-12 20:21:59.936233','http://dummyimage.com/165x100.png/dddddd/000000'),
  ('01HHKFYXSVSNSZ8BJ14YSCR16H', '1',1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.lululemon.com.hk/en-hk/c/sale/specials', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:22:39.631992', '2023-12-12 20:22:39.631992', 'http://dummyimage.com/135x100.png/cc0000/ffffff'),
  ('01HHKFYXSV27CNWMTV5QDDFWCB', '1',1, 2, 'Figure', null, 'https://tamashiiweb.com/item/13885/?wovn=en#', 1, '', 1900, FALSE, true, '2023-12-12 20:22:54.04832', '2023-12-12 20:22:54.04832', 'https://tamashiiweb.com/images/item/item_0000013885_IclqbkLn_01.jpg');

 INSERT INTO user_address(id, user_id, address, is_primary) VALUES
 ('1','01HHMV7DKG4Z9JNT1P8DESHW8X','13/f, 11 kennedy Road',false),
 ('2','01HHP4GVCVN1P5FY8YYX9YXKN8','flat a,19/f, 8 Star Street',true),
 ('3','01HHMV7DKG4Z9JNT1P8DESHW8X','Rm B, house A, 17 Bowen Rd',false),
 ('5','01HHP4GVCVN1P5FY8YYX9YXKN8','Rm B ,5/f, Eva Court ,36 Macdonnell Rd',false),
 ('6','01HHMV7DKG4Z9JNT1P8DESHW8X','Rm C, Manly Mansion, Robinson Rd',false);

 INSERT INTO image (request_id, image_path, is_active) VALUES
 ('01HHKFYXSSTKGTSGJ4TPMC096E','http://dummyimage.com/225x100.png/5fa2dd/ffffff',true),
 ('01HHKFYXST6C48MQ886K9EARM5','http://dummyimage.com/210x100.png/cc0000/ffffff',true),
 ('01HHKFYXSTSBKPZX0HYDZ85KDE','http://dummyimage.com/165x100.png/dddddd/000000',true),
 ('01HHKFYXSVSNSZ8BJ14YSCR16H','http://dummyimage.com/135x100.png/cc0000/ffffff',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://tamashiiweb.com/images/item/item_0000013885_IclqbkLn_01.jpg',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://tamashiiweb.com/images/item/item_0000013885_22mSOSGZ_04.jpg',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://tamashiiweb.com/images/item/item_0000013885_22mSOSGZ_03.jpg',true);

INSERT INTO provider_verification (user_id, status_id, id_document, address_document) VALUES
 ('01HHMV7DKG4Z9JNT1P8DESHW8X', '4', 'A654321', '321 HAHA Street');

INSERT INTO provider (id, user_id, location_id, provider_verification_id) VALUES
('01HHMV7DKG4Z9JNT1P8DESHW8R', '01HHMV7DKG4Z9JNT1P8DESHW8X', '1', '2');


 INSERT INTO user_payment_method (id, user_id, payment_method, card_no, card_holder_name, expiry_date) VALUES
 ('1','01HHMV7DKG4Z9JNT1P8DESHW8X','Visa','1234567890123456','testing','12/22'),
 ('2','01HHMV7DKG4Z9JNT1P8DESHW8X','Master','1234567890123456','testing','12/22'),
 ('3','01HHMV7DKG4Z9JNT1P8DESHW8X','American Express','1234567890123456','testing','12/22'),
 ('4','01HHMV7DKG4Z9JNT1P8DESHW8X','Union Pay','1234567890123456','testing','12/22'),
 ('5','01HHMV7DKG4Z9JNT1P8DESHW8X','Paypal','1234567890123456','testing','12/22');