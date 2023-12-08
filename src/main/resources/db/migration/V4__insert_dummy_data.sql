INSERT INTO users(password,username,user_email,id) VALUES('123','testing','testing@gmail.com','1');

INSERT INTO status(status_name) VALUES ('in-progress'),('aborted'),('rejected'),('completed');

INSERT INTO provider_verification(user_id,status_id,id_document,address_document) VALUES ('1', '1', 'A123456', '123 Fake Street');

INSERT INTO location(location_name) VALUES   ('United States'),
                                             ('China'),
                                             ('Japan'),
                                             ('United Kingdom'),
                                             ('France'),
                                             ('Germany'),
                                             ('United Arab Emirates'),
                                             ('South Korea'),
                                             ('Italy'),
                                             ('Singapore');

INSERT INTO provider(id,user_id,location_id,provider_verification_id) VALUES ('1', '1', '1', '1');

INSERT INTO logistic_company(company_name, company_url) VALUES   ('DHL', 'https://www.dhl.com'),
                                                        ('FedEx', 'https://www.fedex.com'),
                                                        ('UPS', 'https://www.ups.com'),
                                                        ('TNT Express', 'https://www.tnt.com'),
                                                        ('SF Express', 'https://www.sf-express.com');

INSERT INTO category(category_name) VALUES ('Clothes'),('Figure');

INSERT INTO category_field(category_id, category_field_name) VALUES ('1','apparel_type'),
                                                                 ('1','gender'),
                                                                 ('1','size'),
                                                                 ('1','color');



INSERT INTO category_field_option(category_field_id, category_field_option) VALUES ('1','T-shirt'),
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

