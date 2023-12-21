INSERT INTO offer
(id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark)
VALUES
('01HHV1JK9C6RE53WM98WWZWEYX', '01HJ2TJ3TQ92ARVXF32HTTYAK0', '01HJ017JH7A2J9XYPTY64YG5EK', 1, 20, 4000, 'Dec 17');
--pls give me the JWT token for this user to test
--user: fredy@user.com
--JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhaWU5KOERSUEtWUlQxWVkwWjlUUkhOIiwiaWF0IjoxNzAyOTU1NjYyLCJleHAiOjE3MDQyNzE3OTZ9.vaWwQnGff24gRZNuhRGfVkLenk3P8VTa8GUQkSaAFw4

INSERT INTO offer
(id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark)
VALUES
('01HJ3KPBMGYR4E6M5Z4JHR4ZFS', '01HJ0PV70HPFMH9F6ZVJK3FNVW', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 1, 20, 1500, 'Dec 17'),
('01HJ3KQG1E1RMQYY94TJMXMQPW', '01HJ0PV70HPFMH9F6ZVJK3FNVW', '01HJ017JH7A2J9XYPTY64YG5EK', 1, 20, 4000, 'Dec 17');


--All completed orders
INSERT INTO offer (id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark) VALUES
('01HJ5JTWYB7T88R4408446WA1C', '01HJ3J1AERETM84PD78FQPMGZM', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 14, 4000, 'Accepted Offer'),
('01HJ5JXT718C04ZFCTPN8HKW0M', '01HJ3J1AERETM84PD78FQPMGZM', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 4, 14, 4000, 'Accepted Offer'),
('01HHZXTEBHEH1H55WTEZX7YTKV', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 14, 4000, 'Accepted Offer'),
('01HJ5JMHYR0DZSCXRTJXVS6YAS', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 4, 14, 4000, 'Rejected Offer');
--user: fredy@user.com  ,  pw: "123"
--provider : elaine@provider.com    ,    pw: "123"

--Order Done
INSERT INTO Orders (id, offer_id, user_address_id, order_status_id, logistic_company_id, shipping_order_no) VALUES
('01HJ5JTHWJVYAS651YNCGNE4ZJ', '01HJ5JTWYB7T88R4408446WA1C', 4, 2, 1, '1234567890'),
('01HHZY2AREHH036SRJNY23N3QX', '01HHZXTEBHEH1H55WTEZX7YTKV', 4, 4, 1, '1234567890');
--user: fredy@user.com  ,  pw: "123"
--provider : elaine@provider.com    ,    pw: "123"