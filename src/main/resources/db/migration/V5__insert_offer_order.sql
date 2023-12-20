INSERT INTO offer
(id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark)
VALUES
('01HHV1JK9C6RE53WM98WWZWEYX', '01HJ2TJ3TQ92ARVXF32HTTYAK0', '01HJ017JH7A2J9XYPTY64YG5EK', 1, 20, 4000, 'Dec 17');
--pls give me the JWT token for this user to test
--user: fredy@user.com
--JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhaWU5KOERSUEtWUlQxWVkwWjlUUkhOIiwiaWF0IjoxNzAyOTU1NjYyLCJleHAiOjE3MDQyNzE3OTZ9.vaWwQnGff24gRZNuhRGfVkLenk3P8VTa8GUQkSaAFw4

--All completed orders
INSERT INTO offer (id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark) VALUES
('01HHZXTEBHEH1H55WTEZX7YTKV', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 14, 4000, 'Can do it in 14 days');
--user: fredy@user.com  ,  pw: "123"
--provider : elaine@provider.com    ,    pw: "123"

--Order Done
INSERT INTO Orders (id, offer_id, user_address_id, order_status_id, logistic_company_id, shipping_order_no) VALUES
('01HHZY2AREHH036SRJNY23N3QX', '01HHZXTEBHEH1H55WTEZX7YTKV', 4, 4, 1, '1234567890');
--user: fredy@user.com  ,  pw: "123"
--provider : elaine@provider.com    ,    pw: "123"