INSERT INTO users(id,username,user_email,password) VALUES('01HHP4GVCVN1P5FY8YYX9YXKN8','testing1234','testing1234@gmail.com', '$2a$10$IfXGNeuWa.ksBZaqjcMCjOyOLNjLnsAnL.UysbSXoj.mqef86hFNa');
INSERT INTO users(id,username,user_email,password) VALUES('01HHAWPJ6068YT0685RZ5AY3XF','su','test@gmail.com','$2a$10$xoRl/KKuoriNmgsoZwsrMOwm.5I02Wr/apjqn6hIpI0fhanUBMEwS');
INSERT INTO users(id,username,user_email,password) VALUES('01HHQ61P480SRGXG5130R22B53','testing','testingforuser@gmail.com','$2a$10$TuQH2OXFRTrxTvFBK3E5x.NSRGe1IZNPGU/2Ij7kCJH6xJj5Zp10e');


INSERT INTO status(status_name) VALUES ('pending'),('in-progress'),('aborted'),('rejected'),('completed'),('open'),('closed');
--added open and closed

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
  ('01HHKFYXSSTKGTSGJ4TPMC096E','01HHQ61P480SRGXG5130R22B53', 1, 1, 'clothes', '{"size": "M", "color": "Black", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.uniqlo.com.hk/zh_HK/product-detail.html?productCode=u0000000028366', 1, 'MEN 2WAY SINGLE BREASTED COAT', 900, FALSE, true, '2023-12-12 20:20:17.367665', '2023-12-12 20:20:17.367665', 'https://www.uniqlo.com.hk/hmall/test/u0000000028366/main/first/561/1.jpg'),
  ('01HHKFYXST6C48MQ886K9EARM5','01HHQ61P480SRGXG5130R22B53', 1, 1, 'clothes', '{"size": "L", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.uniqlo.com.hk/zh_HK/product-detail.html?productCode=u0000000027647', 1, '男裝 孖襟大衣', 3000, FALSE, true, '2023-12-12 20:21:05.972918', '2023-12-12 20:21:05.972918','https://www.uniqlo.com.hk/hmall/test/u0000000027647/main/first/561/1.jpg'),
  ('01HHKFYXSTSBKPZX0HYDZ85KDE','01HHP4GVCVN1P5FY8YYX9YXKN8', 1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.uniqlo.com.hk/zh_HK/product-detail.html?productCode=u0000000027999', 1, '男女通用 PEANUTS You Can Be Anything!', 300, FALSE, true, '2023-12-12 20:21:59.936233', '2023-12-12 20:21:59.936233','https://www.uniqlo.com.hk/hmall/test/u0000000027999/main/first/561/1.jpg'),
  ('01HHKFYXSVSNSZ8BJ14YSCR16H', '01HHP4GVCVN1P5FY8YYX9YXKN8',1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.uniqlo.com.hk/zh_HK/product-detail.html?productCode=u0000000028005', 1, '男女通用 PEANUTS You Can Be Anything!', 1900, FALSE, true, '2023-12-12 20:22:39.631992', '2023-12-12 20:22:39.631992', 'https://www.uniqlo.com.hk/hmall/test/u0000000028005/main/first/561/1.jpg'),
  ('01HHKFYXSV27CNWMTV5QDDFWCB', '01HHQ61P480SRGXG5130R22B53',1, 2, 'Figure', null, 'https://www.amazon.com/YOUNAI-Action-Figure-Decoration-Figurine/dp/B0BR9YDRPS/ref=sr_1_1_sspa?keywords=luffy+figure&qid=1702895497&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1', 1, NULL, 1900, FALSE, true, '2023-12-12 20:22:54.04832', '2023-12-12 20:22:54.04832', 'https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71xSJoU941L._AC_SL1500_.jpg'),
  ('01HHV1H3RCJYR25XJWX37NPCQJ', '01HHQ61P480SRGXG5130R22B53',1, 2, 'Figure', null, 'https://www.amazon.com/ANIME-HEROES-Monkey-Renewal-Version/dp/B0CCXQ9H1W/ref=sr_1_2?keywords=luffy%2Bfigure&qid=1702895497&sr=8-2&th=1', 1, NULL, 1900, FALSE, true, '2023-12-12 20:22:54.04832', '2023-12-12 20:22:54.04832', 'https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71LRuKUEGRL._AC_SL1500_.jpg');

INSERT INTO user_address(id, user_id, address, is_primary) VALUES
 ('1','01HHMV7DKG4Z9JNT1P8DESHW8X','13/f, 11 kennedy Road',false),
 ('2','01HHP4GVCVN1P5FY8YYX9YXKN8','flat a,19/f, 8 Star Street',true),
 ('3','01HHMV7DKG4Z9JNT1P8DESHW8X','Rm B, house A, 17 Bowen Rd',false),
 ('5','01HHP4GVCVN1P5FY8YYX9YXKN8','Rm B ,5/f, Eva Court ,36 Macdonnell Rd',false),
 ('6','01HHMV7DKG4Z9JNT1P8DESHW8X','Rm C, Manly Mansion, Robinson Rd',false);

 INSERT INTO image (request_id, image_path, is_active) VALUES
 ('01HHKFYXSSTKGTSGJ4TPMC096E','https://www.uniqlo.com.hk/hmall/test/u0000000028366/main/other2/480/3.jpg',true),
 ('01HHKFYXSSTKGTSGJ4TPMC096E','https://www.uniqlo.com.hk/hmall/test/u0000000028366/main/other2/480/4.jpg',true),
 ('01HHKFYXSSTKGTSGJ4TPMC096E','https://www.uniqlo.com.hk/hmall/test/u0000000028366/main/other2/480/5.jpg',true),
 ('01HHKFYXST6C48MQ886K9EARM5','https://www.uniqlo.com.hk/hmall/test/u0000000027647/main/first/561/2.jpg',true),
 ('01HHKFYXST6C48MQ886K9EARM5','https://www.uniqlo.com.hk/hmall/test/u0000000027647/main/first/561/3.jpg',true),
 ('01HHKFYXST6C48MQ886K9EARM5','https://www.uniqlo.com.hk/hmall/test/u0000000027647/main/first/561/4.jpg',true),
 ('01HHKFYXSTSBKPZX0HYDZ85KDE','https://www.uniqlo.com.hk/hmall/test/u0000000027999/main/first/561/2.jpg',true),
 ('01HHKFYXSTSBKPZX0HYDZ85KDE','https://www.uniqlo.com.hk/hmall/test/u0000000027999/main/first/561/3.jpg',true),
 ('01HHKFYXSTSBKPZX0HYDZ85KDE','https://www.uniqlo.com.hk/hmall/test/u0000000027999/main/first/561/4.jpg',true),
 ('01HHKFYXSVSNSZ8BJ14YSCR16H','https://www.uniqlo.com.hk/hmall/test/u0000000028005/main/first/561/2.jpg',true),
 ('01HHKFYXSVSNSZ8BJ14YSCR16H','https://www.uniqlo.com.hk/hmall/test/u0000000028005/main/first/561/3.jpg',true),
 ('01HHKFYXSVSNSZ8BJ14YSCR16H','https://www.uniqlo.com.hk/hmall/test/u0000000028005/main/first/561/4.jpg',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71CWSymDYJL._AC_SL1500_.jpg',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71gKOUQyrDL._AC_SL1500_.jpg',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71delv70wyL._AC_SL1500_.jpg',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/81DzUtbKrYL._AC_SL1500_.jpg',true),
 ('01HHV1H3RCJYR25XJWX37NPCQJ','https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71Hf77Rs3DL._AC_SL1500_.jpg',true),
 ('01HHV1H3RCJYR25XJWX37NPCQJ','https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/71TkSqQa2FL._AC_SL1500_.jpg',true);

--INSERT INTO provider_verification (user_id, status_id,provider_id, id_document, address_document) VALUES
-- ('01HHMV7DKG4Z9JNT1P8DESHW8X', '4','1', 'A654321', '321 HAHA Street');

INSERT INTO provider (id, user_id, location_id, star_of_efficiency, star_of_attitude) VALUES
('01HHMV7DKG4Z9JNT1P8DESHW8R', '01HHMV7DKG4Z9JNT1P8DESHW8X', '1', '5','5');


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

INSERT INTO offer (id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark) VALUES
('01HHV1JK9C6RE53WM98WWZWEYX', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HHMV7DKG4Z9JNT1P8DESHW8R', 1, 20, 4000, 'Dec 17');
--pls give me the JWT token for this user to test
--user_id:01HHMV7DKG4Z9JNT1P8DESHW8X
--password: "1234"
--JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhNVjdES0c0WjlKTlQxUDhERVNIVzhYIiwiaWF0IjoxNzAyODcxOTEwLCJleHAiOjE3MDQxODgwNDR9.dvPhuAdwAusAvSQ6V9s87l6ZX1sYPLec2hrqyQcox4k