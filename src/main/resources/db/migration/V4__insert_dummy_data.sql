INSERT INTO users(password,username,user_email,id) VALUES('123','testing','testing@gmail.com','1');
INSERT INTO users(id,username,user_email,password) VALUES('01HHAWPJ6068YT0685RZ5AY3XF','su','test@gmail.com','$2a$10$xoRl/KKuoriNmgsoZwsrMOwm.5I02Wr/apjqn6hIpI0fhanUBMEwS');

INSERT INTO status(status_name) VALUES ('in-progress'),('aborted'),('rejected'),('completed');

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
  ('01HHKFYXSV27CNWMTV5QDDFWCB', '1',1, 1, 'clothes', '{"size": "S", "color": "blue", "gender": "Men", "apparelType": "T-shirt"}', 'https://www.lululemon.com.hk/en-hk/p/metal-vent-tech-short-sleeve-shirt-2.0/prod140003.html?dwvar_prod140003_color=59331', 1, '無縫羽絨連帽外套', 1900, FALSE, true, '2023-12-12 20:22:54.04832', '2023-12-12 20:22:54.04832', 'http://dummyimage.com/175x100.png/cc0000/ffffff');

 INSERT INTO user_address(id, user_id, address, is_primary) VALUES
 ('1','1','13/f, 11 kennedy Road',true),
 ('2','1','flat a,19/f, 8 Star Street',false),
 ('3','1','Rm B, house A, 17 Bowen Rd',false);

 INSERT INTO image (request_id, image_path, is_active) VALUES
 ('01HHKFYXSSTKGTSGJ4TPMC096E','http://dummyimage.com/225x100.png/5fa2dd/ffffff',true),
 ('01HHKFYXST6C48MQ886K9EARM5','http://dummyimage.com/210x100.png/cc0000/ffffff',true),
 ('01HHKFYXSTSBKPZX0HYDZ85KDE','http://dummyimage.com/165x100.png/dddddd/000000',true),
 ('01HHKFYXSVSNSZ8BJ14YSCR16H','http://dummyimage.com/135x100.png/cc0000/ffffff',true),
 ('01HHKFYXSV27CNWMTV5QDDFWCB','http://dummyimage.com/175x100.png/cc0000/ffffff',true);