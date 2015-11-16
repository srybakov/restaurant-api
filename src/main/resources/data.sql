INSERT INTO `users` (`id`, `name`, `password`) VALUES (1, 'admin', '$2a$10$KTtWeuuX74hDeFxPwXMeoOe2T3.mxb.97R/tamvWD70y08NHH4QQO');
INSERT INTO `user_roles` (`id`, `name`, `user_id`) VALUES (1, 'ADMIN', 1);

INSERT INTO `users` (`id`, `name`, `password`) VALUES (2, 'user1', '$2a$10$YuRnUOuudfRs2Zv2HMD3Ze0uo38KZB/xJVvMV4MGP8FLFpDwUSDtG');
INSERT INTO `user_roles` (`id`, `name`, `user_id`) VALUES (2, 'USER', 2);

INSERT INTO `users` (`id`, `name`, `password`) VALUES (3, 'user2', '$2a$10$Gk5DpdordfcX9f1bnnTYUuG.ziE.ePrr2YEUCRzPhP47O6XUfXEjW');
INSERT INTO `user_roles` (`id`, `name`, `user_id`) VALUES (3, 'USER', 3);

INSERT INTO `users` (`id`, `name`, `password`) VALUES (4, 'user3', '$2a$10$t8q9iKil7M2eG2nxIcgDO.4Wkd8jQLUJMD3otJ4bFIcLUgH.zLnKm');
INSERT INTO `user_roles` (`id`, `name`, `user_id`) VALUES (4, 'USER', 4);

INSERT INTO `restaurants` (`id`, `name`) VALUES (1, 'Dublis');
INSERT INTO `restaurants` (`id`, `name`) VALUES (2, 'Deveti');
INSERT INTO `restaurants` (`id`, `name`) VALUES (3, 'Boff');

INSERT INTO `menus` (`id`, `restaurant_id`, `actually_for_date`) VALUES (1, 1, now() - INTERVAL 1 DAY);
INSERT INTO `menus` (`id`, `restaurant_id`, `actually_for_date`) VALUES (2, 2, now() - INTERVAL 1 DAY);
INSERT INTO `menus` (`id`, `restaurant_id`, `actually_for_date`) VALUES (3, 3, now() - INTERVAL 1 DAY);
INSERT INTO `menus` (`id`, `restaurant_id`, `actually_for_date`) VALUES (4, 1, now());
INSERT INTO `menus` (`id`, `restaurant_id`, `actually_for_date`) VALUES (5, 2, now());
INSERT INTO `menus` (`id`, `restaurant_id`, `actually_for_date`) VALUES (6, 3, now());

INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (1, 1, 'Сhicken wings', 380);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (2, 1, 'Venison carpaccio', 450);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (3, 1, 'Garlic Croutons', 220);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (4, 2, 'Light-salted salmon', 480);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (5, 2, 'Chicken and mushrooms julienne', 270);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (6, 2, 'Seafood julienne', 420);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (7, 3, 'Carpaccio with veal tongue', 320);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (8, 3, 'Salmon Tartare', 480);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (9, 3, 'JMeat aspic', 290);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (10, 4, 'Pancakes with sour cream', 170);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (11, 4, 'Atlantic herring', 390);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (12, 4, 'Baked eggplant with cheese', 290);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (13, 5, 'Beef Tartaree', 550);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (14, 5, 'Beef Carpaccio with rucola', 590);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (15, 5, 'Seafood grill', 1200);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (16, 6, 'Cheese plate', 850);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (17, 6, 'House made salad', 290);
INSERT INTO `dishes` (`id`, `menu_id`, `name`, `cost`) VALUE (18, 6, 'Pumpkin salad', 330.12314);

INSERT INTO `votes` (`id`, `vote_date`, `restaurant_id`, `user_id`) VALUES (1, now() - INTERVAL 1 DAY, 2, 1);
INSERT INTO `votes` (`id`, `vote_date`, `restaurant_id`, `user_id`) VALUES (2, now() - INTERVAL 1 DAY, 2, 2);
INSERT INTO `votes` (`id`, `vote_date`, `restaurant_id`, `user_id`) VALUES (3, now() - INTERVAL 1 DAY, 2, 3);

INSERT INTO `votes` (`id`, `vote_date`, `restaurant_id`, `user_id`) VALUES (4, now(), 3, 1);
INSERT INTO `votes` (`id`, `vote_date`, `restaurant_id`, `user_id`) VALUES (5, now(), 3, 2);
INSERT INTO `votes` (`id`, `vote_date`, `restaurant_id`, `user_id`) VALUES (6, now(), 2, 3);
