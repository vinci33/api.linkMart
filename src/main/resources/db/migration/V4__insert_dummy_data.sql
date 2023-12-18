INSERT INTO users(id,username,user_email,password) VALUES('01HHP4GVCVN1P5FY8YYX9YXKN8','testing1234','testing1234@gmail.com', '$2a$10$IfXGNeuWa.ksBZaqjcMCjOyOLNjLnsAnL.UysbSXoj.mqef86hFNa');
INSERT INTO users(id,username,user_email,password) VALUES('01HHAWPJ6068YT0685RZ5AY3XF','su','test@gmail.com','$2a$10$xoRl/KKuoriNmgsoZwsrMOwm.5I02Wr/apjqn6hIpI0fhanUBMEwS');
INSERT INTO users(id,username,user_email,password) VALUES('01HHQ61P480SRGXG5130R22B53','testing','testingforuser@gmail.com','$2a$10$TuQH2OXFRTrxTvFBK3E5x.NSRGe1IZNPGU/2Ij7kCJH6xJj5Zp10e');


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

--INSERT INTO provider_verification(user_id, status_id,provider_id, id_document, address_document) VALUES ('1', '1', '1','A123456', '123 Fake Street');

--INSERT INTO provider(id,user_id,location_id,star_of_efficiency,star_of_attitude ) VALUES ('1', '1', '1', '5','5');

INSERT INTO logistic_company(company_name, company_url) VALUES ('DHL', 'https://www.dhl.com'),
                                                        ('FedEx', 'https://www.fedex.com'),
                                                        ('UPS', 'https://www.ups.com'),
                                                        ('TNT Express', 'https://www.tnt.com'),
                                                        ('SF Express', 'https://www.sf-express.com');

INSERT INTO category(category_name) VALUES ('Clothes'),('Figure');

INSERT INTO category_field(category_id, is_option,category_field_name) VALUES ('1',TRUE,'apparelType'),
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
  ('01HHKFYXSSTKGTSGJ4TPMC096E','01HHQ61P480SRGXG5130R22B53', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.uniqlo.com.hk/zh_HK/index.html', 1, '男裝 無縫羽絨連帽外套', 900, FALSE, true, '2023-12-12 20:20:17.367665', '2023-12-12 20:20:17.367665', 'http://dummyimage.com/225x100.png/5fa2dd/ffffff'),
  ('01HHKFYXST6C48MQ886K9EARM5','01HHQ61P480SRGXG5130R22B53', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.dior.com/zh_hk/fashion/products', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:21:05.972918', '2023-12-12 20:21:05.972918','http://dummyimage.com/210x100.png/cc0000/ffffff'),
  ('01HHKFYXSTSBKPZX0HYDZ85KDE','01HHP4GVCVN1P5FY8YYX9YXKN8', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.ssense.com/en-hk/men/product/wooyoungmi/off-white-crewneck-sweater', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:21:59.936233', '2023-12-12 20:21:59.936233','http://dummyimage.com/165x100.png/dddddd/000000'),
  ('01HHKFYXSVSNSZ8BJ14YSCR16H', '01HHP4GVCVN1P5FY8YYX9YXKN8',1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.lululemon.com.hk/en-hk/c/sale/specials', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:22:39.631992', '2023-12-12 20:22:39.631992', 'http://dummyimage.com/135x100.png/cc0000/ffffff'),
  ('01HHKFYXSV27CNWMTV5QDDFWCB', '01HHQ61P480SRGXG5130R22B53',1, 2, 'Figure', null, 'https://tamashiiweb.com/item/13885/?wovn=en#', 1, NULL, 1900, FALSE, true, '2023-12-12 20:22:54.04832', '2023-12-12 20:22:54.04832', 'https://tamashiiweb.com/images/item/item_0000013885_IclqbkLn_01.jpg');

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

--INSERT INTO provider_verification (user_id, status_id,provider_id, id_document, address_document) VALUES
-- ('01HHMV7DKG4Z9JNT1P8DESHW8X', '4','1', 'A654321', '321 HAHA Street');

INSERT INTO provider (id, user_id, location_id) VALUES
('01HHMV7DKG4Z9JNT1P8DESHW8R', '01HHMV7DKG4Z9JNT1P8DESHW8X', '1');


 INSERT INTO user_payment_method (id, user_id, payment_method, card_no, card_holder_name, expiry_date) VALUES
 ('1','01HHMV7DKG4Z9JNT1P8DESHW8X','Visa','1234567890123456','testing','12/22'),
 ('2','01HHMV7DKG4Z9JNT1P8DESHW8X','Master','1234567890123456','testing','12/22'),
 ('3','01HHMV7DKG4Z9JNT1P8DESHW8X','American Express','1234567890123456','testing','12/22'),
 ('4','01HHMV7DKG4Z9JNT1P8DESHW8X','Union Pay','1234567890123456','testing','12/22'),
 ('5','01HHMV7DKG4Z9JNT1P8DESHW8X','Paypal','1234567890123456','testing','12/22');


--testing provider
INSERT INTO users (id, username, user_email, password) VALUES
 ('01HHQRCV54C81AAERBR58E2DAN', 'kdl@gmail.com', 'kdl@gmail.com', '$2a$10$JScQcGKhFIvXLkPzLdE5/.NXPstdQnmj/csKoMrDuTmoRskfTx3V6');
--user_id:01HHQRCV54C81AAERBR58E2DAN
--password: "1234"
--JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhRUkNWNTRDODFBQUVSQlI1OEUyREFOIiwiaWF0IjoxNzAyNjc4MTI5LCJleHAiOjE3MDM5OTQyNjR9.M4Fr-2Esjo3odBf5qGrwKakcaZECuwLKIaWQDFKuYoQ
 INSERT INTO provider (id, user_id, location_id, star_of_efficiency, star_of_attitude) VALUES
 ('01HHQVX80K4HAD2YMTHW6970VG', '01HHQRCV54C81AAERBR58E2DAN', '1','5','5');

INSERT INTO request (id, created_by, location_id, category_id, item, item_detail, url, primary_image, quantity, request_remark, offer_price, has_offer) VALUES
('01HHV1H3RCJYR25XJWX37NPCQJ', '01HHP4GVCVN1P5FY8YYX9YXKN8', 3, 2, 'Figure', NULL, 'https://shorturl.at/jryAM','https://shorturl.at/jryAM', 1, 'Luffy Figure', 3000, true);

INSERT INTO offer (id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark) VALUES
('01HHV1JK9C6RE53WM98WWZWEYX', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HHMV7DKG4Z9JNT1P8DESHW8R', 1, 20, 4000, 'Dec 17');
