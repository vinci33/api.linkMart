INSERT INTO offer
(id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark)
VALUES
('01HHV1JK9C6RE53WM98WWZWEYX', '01HJ2TJ3TQ92ARVXF32HTTYAK0', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 1, 20, 9000, 'With Invoice and Box'),
('01HJ5NFBVKSTGP7CK48WSQ2EPG', '01HJ2TJ3TQ92ARVXF32HTTYAK0', '01HJ017JH7A2J9XYPTY64YG5EK', 1, 20, 4000, 'Can deliver on 17 Dec'),
('01HJQZNGVM6EH1BEANZP1QM72J', '01HJ2TJ3TQ92ARVXF32HTTYAK0', '01HJQZJTFE9RFC380RYZDM101T', 1, 20, 4000, 'Can do it asap');

--pls give me the JWT token for this user to test
--user: fredy@user.com
--JWT: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhZG1pbiIsInVzZXJJZCI6IjAxSEhaWU5KOERSUEtWUlQxWVkwWjlUUkhOIiwiaWF0IjoxNzAyOTU1NjYyLCJleHAiOjE3MDQyNzE3OTZ9.vaWwQnGff24gRZNuhRGfVkLenk3P8VTa8GUQkSaAFw4

INSERT INTO offer
(id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark)
VALUES
('01HJ3KPBMGYR4E6M5Z4JHR4ZFS', '01HJ0PV70HPFMH9F6ZVJK3FNVW', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 1, 20, 1500, 'With Invoice and Box'),
('01HJ3KQG1E1RMQYY94TJMXMQPW', '01HJ0PV70HPFMH9F6ZVJK3FNVW', '01HJ017JH7A2J9XYPTY64YG5EK', 1, 20, 4000, 'Can deliver on 17 Dec');

--All completed orders
INSERT INTO offer (id, request_id, provider_id, offer_status_id, estimated_process_time, price, offer_remark) VALUES
('01HJ5JTWYB7T88R4408446WA1C', '01HJ3J1AERETM84PD78FQPMGZM', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 14, 4000, 'Accepted Offer'),
('01HJ5JXT718C04ZFCTPN8HKW0M', '01HJ3J1AERETM84PD78FQPMGZM', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 4, 14, 4000, 'Rejected Offer'),
('01HHZXTEBHEH1H55WTEZX7YTKV', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 14, 4000, 'Accepted Offer'),
('01HJ5JMHYR0DZSCXRTJXVS6YAS', '01HHV1H3RCJYR25XJWX37NPCQJ', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 4, 14, 4000, 'Rejected Offer'),
('01HJQX6PKE66X014HNP52HCFY9', '01HJQKDRHSKW4AB740KTS8MSED', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 14, 2000, 'Accepted Offer')
,('01HJQXE4DJ064A247C4HFH8046', '01HJQK73PET7DHTQ2G86FDG87D', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 20, 3000, 'Accepted Offer')
,('01HJQY2PBWNWFMCMFCYYVPCHQH', '01HJQK52S123F1F6EQPGPSD4JY', '01HJ017JH7A2J9XYPTY64YG5EK', 8, 20, 3000, 'Accepted Offer')
,('01HJQYMFPZR95P98XK003T1YQ1', '01HJQK2WN05Y0HTF816064965J', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 8, 20, 3000, 'Accepted Offer')
,('01HJQYR680VE9XFRC6S0RWN64H', '01HJQK0YZH9QGJWT9G2B2A1M9H', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 8, 20, 3000, 'Accepted Offer')
,('01HJQYTX88GMYHW8MFKSVWAC1P', '01HJQJYANHBJP2CWD721C5J5KP', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 8, 20, 3000, 'Accepted Offer');
--user: fredy@user.com  ,  pw: "123"
--provider : elaine@provider.com    ,    pw: "123"

--Order Done
INSERT INTO Orders (id, offer_id, user_address_id, order_status_id, logistic_company_id, shipping_order_no) VALUES
('01HJ5JTHWJVYAS651YNCGNE4ZJ', '01HJ5JTWYB7T88R4408446WA1C', 4, 2, 1, '1234567890'),
('01HHZY2AREHH036SRJNY23N3QX', '01HHZXTEBHEH1H55WTEZX7YTKV', 6, 4, 1, '1234567890'),
('01HJQX7N9YMJJ6BSS3J0XQ559G', '01HJQX6PKE66X014HNP52HCFY9', 6, 4, 1, '1234567890'),
('01HJQXF8ZMM3GQK69R0TZ6WWM9', '01HJQXE4DJ064A247C4HFH8046', 6, 4, 1, '1234567890'),
('01HJQY20V7WRP36C3T1VWZY7C5', '01HJQY2PBWNWFMCMFCYYVPCHQH', 6, 4, 1, '1234567890')
,
('01HJQYKS8RB485A4WP7EPS666V', '01HJQYMFPZR95P98XK003T1YQ1', 6, 4, 1, '1234567890')
,
('01HJQYS0QFXBWZWMBHB7C4BJ06', '01HJQYR680VE9XFRC6S0RWN64H', 6, 4, 1, '1234567890')
,
('01HJQYVVS5FTBEN9W0GJN5FC6G', '01HJQYTX88GMYHW8MFKSVWAC1P', 6, 4, 1, '1234567890');
--user: fredy@user.com  ,  pw: "123"
--provider : elaine@provider.com    ,    pw: "123"

INSERT INTO Review (orders_id, provider_id, review_efficiency, review_attitude, review_remark) VALUES
('01HHZY2AREHH036SRJNY23N3QX', '01HJ017JH7A2J9XYPTY64YG5EK', 4.5, 5, 'Good'),
('01HJQX7N9YMJJ6BSS3J0XQ559G', '01HJ017JH7A2J9XYPTY64YG5EK', 5, 5, 'Nice Provider'),
('01HJQXF8ZMM3GQK69R0TZ6WWM9', '01HJ017JH7A2J9XYPTY64YG5EK', 4.5, 5, 'Good Provider'),
('01HJQY20V7WRP36C3T1VWZY7C5', '01HJ017JH7A2J9XYPTY64YG5EK', 5, 5, 'Good Provider')
,
('01HJQYKS8RB485A4WP7EPS666V', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 1, 4, 'Bad Provider')
,
('01HJQYS0QFXBWZWMBHB7C4BJ06', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 2, 1, 'Bad Provider')
,
('01HJQYVVS5FTBEN9W0GJN5FC6G', '01HJ016MJHEPFWWTA19JQ7Q2Q8', 1, 3, 'Bad Provider');

INSERT INTO image (request_id, image_path, is_active, is_primary) VALUES
('01HJQ5B9XR4GMD6NQ5JP1XTB1W','https://static.nike.com.hk/resources/product/BV2667-010/BV2667-010_BL1.png',true, true),
('01HJQ5B9XR4GMD6NQ5JP1XTB1W','https://static.nike.com.hk/resources/product/BV2667-010/BV2667-010_BL2.png',true, false),
('01HJQ5B9XR4GMD6NQ5JP1XTB1W','https://static.nike.com.hk/resources/product/BV2667-010/BV2667-010_BL3.png',true, false),
('01HJQ5KE1CR86B8FK8AS2S1Z7V','https://static.nike.com.hk/resources/product/DQ4845-063/DQ4845-063_BL1.png',true, true),
('01HJQ5KE1CR86B8FK8AS2S1Z7V','https://static.nike.com.hk/resources/product/DQ4845-063/DQ4845-063_BL2.png',true, false),
('01HJQ5KE1CR86B8FK8AS2S1Z7V','https://static.nike.com.hk/resources/product/DQ4845-063/DQ4845-063_BL3.png',true, false),
('01HJQ5WJXABCNR9TYG8E7Q5Y8S','https://static.nike.com.hk/resources/product/FB7414-060/FB7414-060_BL1.png',true, true),
('01HJQ5WJXABCNR9TYG8E7Q5Y8S','https://static.nike.com.hk/resources/product/FB7414-060/FB7414-060_BL2.png',true, false),
('01HJQ5WJXABCNR9TYG8E7Q5Y8S','https://static.nike.com.hk/resources/product/FB7414-060/FB7414-060_BL3.png',true, false),
('01HJQ5WJXABCNR9TYG8E7Q5Y8S','https://static.nike.com.hk/resources/product/FB7414-060/FB7414-060_BL4.png',true, false),
('01HJQ60GMKWWK9JPS6X550CTNQ','https://static.nike.com.hk/resources/product/FJ4450-091/FJ4450-091_BL1.png',true, true),
('01HJQ60GMKWWK9JPS6X550CTNQ','https://static.nike.com.hk/resources/product/FJ4450-091/FJ4450-091_BL2.png',true, false),
('01HJQ60GMKWWK9JPS6X550CTNQ','https://static.nike.com.hk/resources/product/FJ4450-091/FJ4450-091_BL3.png',true, false),
('01HJQ60GMKWWK9JPS6X550CTNQ','https://static.nike.com.hk/resources/product/FJ4450-091/FJ4450-091_BL4.png',true, false),
('01HJQ65F3JNEBA275XY9GZ6JJH','https://static.nike.com.hk/resources/product/DV3646-410/DV3646-410_BL1.png',true, true),
('01HJQ65F3JNEBA275XY9GZ6JJH','https://static.nike.com.hk/resources/product/DV3646-410/DV3646-410_BL2.png',true, false),
('01HJQ65F3JNEBA275XY9GZ6JJH','https://static.nike.com.hk/resources/product/DV3646-410/DV3646-410_BL3.png',true, false),
('01HJQ65F3JNEBA275XY9GZ6JJH','https://static.nike.com.hk/resources/product/DV3646-410/DV3646-410_BL4.png',true, false),
('01HJQ81X93KRJMB725J0VXGMQ5','https://static.nike.com.hk/resources/product/FB5639-536/FB5639-536_BL1.png',true, true),
('01HJQ81X93KRJMB725J0VXGMQ5','https://static.nike.com.hk/resources/product/FB5639-536/FB5639-536_BL2.png',true, false),
('01HJQ81X93KRJMB725J0VXGMQ5','https://static.nike.com.hk/resources/product/FB5639-536/FB5639-536_BL3.png',true, false),
('01HJQ81X93KRJMB725J0VXGMQ5','https://static.nike.com.hk/resources/product/FB5639-536/FB5639-536_BL4.png',true, false),
('01HJQ8EVMV005GVFQTR0TKBR4V','https://static.nike.com.hk/resources/product/DQ5861-716/DQ5861-716_BL1.png',true, true),
('01HJQ8EVMV005GVFQTR0TKBR4V','https://static.nike.com.hk/resources/product/DQ5861-716/DQ5861-716_BL2.png',true, false),
('01HJQ8EVMV005GVFQTR0TKBR4V','https://static.nike.com.hk/resources/product/DQ5861-716/DQ5861-716_BL3.png',true, false),
('01HJQ8EVMV005GVFQTR0TKBR4V','https://static.nike.com.hk/resources/product/DQ5861-716/DQ5861-716_BL4.png',true, false),
('01HJQ9E73JQ2PNK7PXD8W8B9YM','https://en.up-next.com.hk/cdn/shop/files/1_cc66f8f0-1c51-4ce0-9d06-e90209cc92c5_1024x1024.jpg?v=1702974120',true, true),
('01HJQ9E73JQ2PNK7PXD8W8B9YM','https://en.up-next.com.hk/cdn/shop/files/3_8a8db30e-4f30-4ab4-9776-10e61bf9bb55_1024x1024.jpg?v=1702974120',true, false),
('01HJQ9E73JQ2PNK7PXD8W8B9YM','https://en.up-next.com.hk/cdn/shop/files/4_5d885292-0e0b-4abc-ad26-8a3714067d74_1024x1024.jpg?v=1702974120',true, false),
('01HJQ9E73JQ2PNK7PXD8W8B9YM','https://en.up-next.com.hk/cdn/shop/files/7_525f1768-924b-41de-8d40-93897aa85238_1024x1024.jpg?v=1702974121',true, false),
('01HJQ9E73JQ2PNK7PXD8W8B9YM','https://en.up-next.com.hk/cdn/shop/files/11_e93bbfed-027f-41e0-a84c-c148b6ea5058_1024x1024.jpg?v=1702974121',true, false),
('01HJQ9YWA68GDTZKYSVNGNYM65','https://en.up-next.com.hk/cdn/shop/files/Layer2_990e6937-0c23-415a-8111-4a3539917641_1024x1024.jpg?v=1702958471',true, true),
('01HJQ9YWA68GDTZKYSVNGNYM65','https://en.up-next.com.hk/cdn/shop/files/Layer1_63d94f40-e808-4e29-9fa5-1284fdd83364_1024x1024.jpg?v=1702958470',true, false),
('01HJQ9YWA68GDTZKYSVNGNYM65','https://en.up-next.com.hk/cdn/shop/files/Layer6_b569f5bf-517c-411e-89bc-a9445ba5d4e3_1024x1024.jpg?v=1702958471',true, false),
('01HJQ9YWA68GDTZKYSVNGNYM65','https://en.up-next.com.hk/cdn/shop/files/Layer3_a5a036b3-d1cc-4e54-9ab3-eba6207c5270_1024x1024.jpg?v=1702958472',true, false)
,
('01HJQA2WDSDFVGXSYSGQ95ZJGV','https://en.up-next.com.hk/cdn/shop/files/1_df40d6b7-9822-46a0-a6b6-2e0d5c34bb75_1024x1024.jpg?v=1702448630',true, true)
,
('01HJQA2WDSDFVGXSYSGQ95ZJGV','https://en.up-next.com.hk/cdn/shop/files/2_7ed4e449-943c-4e6b-8d26-38e7a2a0a9d2_1024x1024.jpg?v=1702448630',true, false)
,
('01HJQABKG3P5RS5CBW6YE2BTMW','https://en.up-next.com.hk/cdn/shop/files/Layer2_033064ca-43ff-4a83-b8d6-38b14a3261a4_1024x1024.jpg?v=1702437384',true, true)
,
('01HJQABKG3P5RS5CBW6YE2BTMW','https://en.up-next.com.hk/cdn/shop/files/Layer0_783c5edb-af82-4e20-b960-c24da88c7182_1024x1024.jpg?v=1702437385',true, false)
,
('01HJQABKG3P5RS5CBW6YE2BTMW','https://en.up-next.com.hk/cdn/shop/files/Layer3_c4fd3893-925a-472b-ab90-3e514d9e65cd_1024x1024.jpg?v=1702437385',true, false)
,
('01HJQAGA1JSHKR94A8HSJGCCV6','https://en.up-next.com.hk/cdn/shop/files/0_cee717d2-50e0-4ba4-8179-cd0bc6d96904_1024x1024.jpg?v=1701828028',true, true)
,
('01HJQAGA1JSHKR94A8HSJGCCV6','https://en.up-next.com.hk/cdn/shop/files/2_0055cb12-10a3-4bf4-b6b0-fcd07ce31fe7_1024x1024.jpg?v=1701828029',true, false)
,
('01HJQAGA1JSHKR94A8HSJGCCV6','https://en.up-next.com.hk/cdn/shop/files/6_641a0110-eee0-4dfd-b964-5972938336f6_1024x1024.jpg?v=1701828028',true, false)
,
('01HJQAGA1JSHKR94A8HSJGCCV6','https://en.up-next.com.hk/cdn/shop/files/7_9dea6152-33ba-427d-a3c9-28cd237d14ee_1024x1024.jpg?v=1701828028',true, false)
,
('01HJQB2BPC4P97X5PGW6VSED6W','https://en.up-next.com.hk/cdn/shop/files/0_d593fac8-ece8-449f-a09e-ef0936d59658_1024x1024.jpg?v=1700718174',true, true)
,
('01HJQB2BPC4P97X5PGW6VSED6W','https://en.up-next.com.hk/cdn/shop/files/1_0c741fcd-aadd-4a79-b606-8ee8aceab9da_1024x1024.jpg?v=1700718176',true, false)
,
('01HJQB2BPC4P97X5PGW6VSED6W','https://en.up-next.com.hk/cdn/shop/files/2_e01da879-e132-461b-9f38-a1f40dd5a6f2_1024x1024.jpg?v=1700718176',true, false)
,
('01HJQB2BPC4P97X5PGW6VSED6W','https://en.up-next.com.hk/cdn/shop/files/3_914a95ea-69c6-48f6-b91e-c8c4bd0d1022_1024x1024.jpg?v=1700718175',true, false)
,
('01HJQB7J39YH390RDS74ZCAJVN','https://en.up-next.com.hk/cdn/shop/files/2_18912c6e-4aa4-4e08-960e-4e08fdb3d3c5_1024x1024.jpg?v=1700706824',true, true)
,
('01HJQAQM04W7VHQJ7HS2QS1AJP','https://en.up-next.com.hk/cdn/shop/files/1_e31158e2-b39b-48a7-9a24-b66db4677fbe_1024x1024.jpg?v=1701828117',true, true)
,
('01HJQBA5R79SDD1V1P78TKE4PD','https://en.up-next.com.hk/cdn/shop/files/1_ab890cf5-c5d4-4249-b567-fa2119ffbf0f_1024x1024.jpg?v=1700645582',true, true)
,
('01HJQJ1PPRS532F2N7CPCX5XJ6','https://dynamic.zacdn.com/gq6WeCogGdqNifcshACYP89th7Q=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/calvin-klein-0019-5985556-3.jpg',true, true)
,
('01HJQJBY8T9KF8M7A2XK8W0TB3','https://dynamic.zacdn.com/kmaXk9k0J1xu7tONaQ9qmG3EKro=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/onitsuka-tiger-4925-8236575-1.jpg',true, true)
,
('01HJQJVPAK45S1BHPRWPRN3N6F','https://dynamic.zacdn.com/Mfctrln0_tOd7Qqb8em0HU4rPEA=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/vans-8049-4839356-1.jpg',true, true)
,
('01HJQJYANHBJP2CWD721C5J5KP','https://dynamic.zacdn.com/Mfctrln0_tOd7Qqb8em0HU4rPEA=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/vans-8049-4839356-1.jpg',true, true)
,
('01HJQK0YZH9QGJWT9G2B2A1M9H','https://dynamic.zacdn.com/NSw6QLI37tw0C40kgQbpCGOBrRw=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/converse-4464-5079346-1.jpg',true, true)
,
('01HJQK2WN05Y0HTF816064965J','https://dynamic.zacdn.com/3wenNJP38uoZH0GGNpj5puPKmVk=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/puma-6990-1309846-1.jpg',true, true)
,
('01HJQK52S123F1F6EQPGPSD4JY','https://dynamic.zacdn.com/8GU35andmQ4PD3KVvQ_zVwrLH1M=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/adidas-7178-8452036-1.jpg',true, true)
,
('01HJQK73PET7DHTQ2G86FDG87D','https://dynamic.zacdn.com/SQzeReMLEb-S0aG8qaTeiU6g09Y=/filters:quality(70):format(webp)/https://static-hk.zacdn.com/p/361-5261-8115446-1.jpg',true, true)
;

