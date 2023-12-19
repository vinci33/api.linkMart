INSERT INTO offer
(id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark)
VALUES
('01HHV1JK9C6RE53WM98WWZWEYX', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HJ017JH7A2J9XYPTY64YG5EK', 1, 20, 4000, 'Dec 17');
--pls give me the JWT token for this user to test
--user: fredyU
--user_id: 01HHZYNJ8DRPKVRT1YY0Z9TRHN
--JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhaWU5KOERSUEtWUlQxWVkwWjlUUkhOIiwiaWF0IjoxNzAyOTU1NjYyLCJleHAiOjE3MDQyNzE3OTZ9.vaWwQnGff24gRZNuhRGfVkLenk3P8VTa8GUQkSaAFw4

--All completed orders
INSERT INTO offer (id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark) VALUES
('01HHZXTEBHEH1H55WTEZX7YTKV', '01HHKFYXSVSNSZ8BJ14YSCR16H', '01HHZZ37FZAVTMGZ6AYFV5RPP7', 5, 14, 4000, 'Can do it in 14 days');
--requestId: 01HHKFYXSVSNSZ8BJ14YSCR16H, user: testingForUser2   ,  pw: "123"
--providerId: 01HHZZ37FZAVTMGZ6AYFV5RPP7, user : FredyP    ,    pw: "123"

INSERT INTO Orders (id, offer_id, user_address_id, order_status_id, logistic_company_id, shipping_order_no) VALUES
('01HHZY2AREHH036SRJNY23N3QX', '01HHZXTEBHEH1H55WTEZX7YTKV', 4, 1, 1, '1234567890');
--requestId: 01HHKFYXSVSNSZ8BJ14YSCR16H, user: testingForUser2   ,  pw: "123"
--providerId: 01HHZZ37FZAVTMGZ6AYFV5RPP7, user : FredyP    ,    pw: "123"
--offerId: 01HHZY2AREHH036SRJNY23N3QX