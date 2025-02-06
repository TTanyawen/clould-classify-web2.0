
### 原sql
```sql
-- DROP DATABASE cloudDB;
CREATE DATABASE IF NOT EXISTS cloudDB;

USE cloudDB;

-- DROP TABLE cloudtype;
CREATE TABLE cloudtype(
	typeid CHAR(10) PRIMARY KEY,
	typename VARCHAR(20) NOT NULL,
	typenameen VARCHAR(20) NOT NULL,
	typeinfo VARCHAR(200) NOT NULL,
	imgmax NUMERIC NOT NULL
);



-- INSERT INTO cloudtype(typeid, typename,typenameen,typeinfo,imgmax) VALUES
-- ('C001', '卷云', 'cirrus', 'Yes',10),
-- ('C002', '卷积云', 'cirrocumulus', 'No',10),
-- ('C003', '卷层云', 'cirrostratus', 'Yes',10),
-- ('C004', '高积云', 'altocumulus', 'No',10),
-- ('C005', '高层云', 'altostratus', 'Yes',10),
-- ('C006', '雨层云', 'nimbostratus', 'No',10),
-- ('C007', '积雨云', 'cumulonimbus', 'Yes',10),
-- ('C008', '积云', 'cumulus', 'No',10),
-- ('C009', '层云', 'stratus', 'Yes',10),
-- ('C010', '层积云', 'stratocumulus', 'No',10);
INSERT INTO cloudtype(typeid, typename, typenameen, typeinfo, imgmax) VALUES
('C001', '卷云', 'cirrus', '卷云是一种位于高空的云，通常在6,000到12,000米的高度形成，主要由冰晶组成。卷云通常呈现细长、丝状或羽毛状，往往伴随着风的方向而排列。它们在晴朗的天空中显得十分纤细、透明，是天气即将变化的预兆。尽管卷云本身不会带来降水，但它们的出现通常预示着天气将变得不稳定。', 10),
('C002', '卷积云', 'cirrocumulus', '卷积云通常形成于高空，主要由小冰晶和过冷水滴组成。它们的外观呈现为白色的小片云，排列成波浪或条纹状，给天空增添了丰富的纹理。卷积云常预示着天气即将转变，特别是在夏季的高温天气中，卷积云可能预示着雷暴的来临。', 10),
('C003', '卷层云', 'cirrostratus', '卷层云是广泛分布的高层云，通常形成在大气层的高处，由冰晶组成。它们呈现出一种薄纱般的外观，有时会导致太阳或月亮形成光晕。卷层云覆盖大片天空，常预示着温暖锋面或天气系统的到来，通常预示着未来24小时内将有降水。', 10),
('C004', '高积云', 'altocumulus', '高积云是一种中层云，通常位于2,000到7,000米之间。它们呈现出白色或灰色的块状云，常排列成波浪状或条纹状。高积云常标志着天气的变化，特别是在夏季的清晨出现时，预示着当天可能会发生雷暴或其他不稳定天气。', 10),
('C005', '高层云', 'altostratus', '高层云是一种广泛的中层云，通常覆盖整个天空，呈现灰色或蓝灰色。它们通常出现在温暖锋面前，预示着降雨或降雪即将来临。高层云的厚度不一，厚的部分可能使太阳完全看不见，而薄的部分则可能让太阳显得模糊不清。', 10),
('C006', '雨层云', 'nimbostratus', '雨层云是一种厚重的低层云，通常带来持续的降雨或降雪。它们呈现灰色或深灰色，覆盖天空大部分区域，使得天空显得阴沉。雨层云常与温暖锋面相关，通常持续数小时甚至更长时间，导致连续的降水，是典型的锋面天气云。', 10),
('C007', '积雨云', 'cumulonimbus', '积雨云是极其发达的垂直云，通常带来强烈的雷暴、暴雨、冰雹或龙卷风。积雨云通常在不稳定的天气条件下发展，从低层一直延伸到高空，有时高度可达12,000米或更高。它们的顶部呈现砧状，预示着强烈天气即将到来，是雷暴的主要特征云。', 10),
('C008', '积云', 'cumulus', '积云是白色或灰白色的块状云，常见于晴朗的天气中。它们的底部通常是平坦的，而顶部则呈现为隆起的圆顶状，像棉花一样蓬松。积云通常在早晨开始形成，在日照充足时逐渐发展。当积云发展较大时，可能会带来局部阵雨或雷暴。', 10),
('C009', '层云', 'stratus', '层云是一种低层云，覆盖整个天空，呈现灰色或白色，给人一种阴沉的感觉。层云通常出现在稳定的大气条件下，尤其是在冷空气流动较弱时，常带来细雨、毛毛雨或细雪，是典型的冬季或湿冷天气的标志。', 10),
('C010', '层积云', 'stratocumulus', '层积云是一种低层的块状云，通常呈现为白色或灰色，云块较大，排列成条纹状或波状。它们常出现在冷锋或温暖锋面之后，层积云通常不会带来显著的降水，但可能带来零星小雨或毛毛雨。', 10);




CREATE TABLE user_(
	userid CHAR(10) PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	userpw VARCHAR(20) NOT NULL,
	userprofilepath VARCHAR(100),
	useraccount NUMERIC
);


INSERT INTO user_ (userid, username, userpw, userprofilepath,useraccount) VALUES
('U001', 'Alice', 'password123', '/profiles/alice.jpg',98),
('U002', 'Bob', 'securepass456', '/profiles/bob.png',88),
('U003', 'Charlie', 'charlie789', '/profiles/charlie.jpeg',98),
('U004', 'David', 'davidpass321', NULL,9),
('U005', 'Eve', 'evepassword', '/profiles/eve.png',10);


-- DROP TABLE post;

CREATE TABLE post(
	postid CHAR(10) PRIMARY KEY,
	userid CHAR(10) NOT NULL,
	postimgpath VARCHAR(20) NOT NULL,
	posttext VARCHAR(100),
	postlike NUMERIC,
	FOREIGN KEY (userid) REFERENCES User_(userid)
);


INSERT INTO post (postid, userid, postimgpath, posttext, postlike) VALUES
('P001', 'U001', '/posts/post1.jpg', 'Enjoying the sunny day!', 120),
('P002', 'U002', '/posts/post2.jpg', 'Had a great time at the beach.', 85),
('P003', 'U003', '/posts/post3.jpg', 'New adventure begins!', 150),
('P004', 'U001', '/posts/post4.jpg', 'Loving this new place!', 200),
('P005', 'U005', '/posts/post5.jpg', 'Just finished a great book.', 95);

-- CREATE TABLE comment_(
-- 	commentid CHAR(10) PRIMARY KEY,
-- 	userid CHAR(10) NOT NULL,
-- 	commenttext VARCHAR(100),
-- 	commentlike NUMERIC,
-- 	FOREIGN KEY (userid) REFERENCES User_(userid)
-- );



CREATE TABLE comment_ (
    commentid CHAR(10) PRIMARY KEY,
    userid CHAR(10) NOT NULL,
    postid CHAR(10) NOT NULL,
    commenttext VARCHAR(100),
    commentlike NUMERIC,
    FOREIGN KEY (userid) REFERENCES User_(userid),
    FOREIGN KEY (postid) REFERENCES post(postid)
);

INSERT INTO comment_ (commentid, userid, postid, commenttext, commentlike) VALUES
('C001', 'U001', 'P001', 'Great post! Really enjoyed it.', 45),
('C002', 'U002', 'P001', 'Thanks for sharing!', 30),
('C003', 'U003', 'P001', 'Amazing picture!', 60),
('C004', 'U004', 'P001', 'Well said!', 25),
('C005', 'U005', 'P001', 'This is so inspiring!', 70),
('C006', 'U002', 'P001', 'Love the vibes!', 35),
('C007', 'U003', 'P001', 'Beautiful scenery!', 50),
('C008', 'U004', 'P001', 'Such a great shot!', 40),

('C009', 'U001', 'P002', 'Looks like fun!', 55),
('C010', 'U002', 'P002', 'Wish I was there!', 40),
('C011', 'U003', 'P002', 'Perfect beach day!', 65),
('C012', 'U004', 'P002', 'So relaxing!', 45),
('C013', 'U005', 'P002', 'The ocean looks amazing!', 80),
('C014', 'U001', 'P002', 'Great memories!', 35),
('C015', 'U002', 'P002', 'Thanks for the share!', 25),
('C016', 'U003', 'P002', 'Can’t wait to go there!', 60),

('C017', 'U001', 'P003', 'Adventure awaits!', 70),
('C018', 'U002', 'P003', 'Exciting times ahead!', 55),
('C019', 'U003', 'P003', 'Safe travels!', 45),
('C020', 'U004', 'P003', 'Have fun!', 50),
('C021', 'U005', 'P003', 'Looking forward to the stories!', 65),
('C022', 'U001', 'P003', 'New beginnings!', 35),
('C023', 'U002', 'P003', 'This is so cool!', 40),
('C024', 'U003', 'P003', 'Best of luck!', 55),

('C025', 'U001', 'P004', 'Looks amazing!', 60),
('C026', 'U002', 'P004', 'Can’t wait to visit!', 45),
('C027', 'U003', 'P004', 'So beautiful!', 75),
('C028', 'U004', 'P004', 'You’re in paradise!', 65),
('C029', 'U005', 'P004', 'Living the dream!', 85),
('C030', 'U001', 'P004', 'Take me there!', 55),
('C031', 'U002', 'P004', 'Absolutely stunning!', 50),
('C032', 'U003', 'P004', 'I’m so jealous!', 40),

('C033', 'U001', 'P005', 'What’s the book?', 70),
('C034', 'U002', 'P005', 'Sounds interesting!', 60),
('C035', 'U003', 'P005', 'I need to read it too!', 55),
('C036', 'U004', 'P005', 'Would love to hear more!', 45),
('C037', 'U005', 'P005', 'Books are the best!', 75),
('C038', 'U001', 'P005', 'Can you recommend it?', 65),
('C039', 'U002', 'P005', 'Looks like a good read!', 50),
('C040', 'U003', 'P005', 'Adding it to my list!', 60);



SELECT p.postid, p.userid, p.postimgpath, p.posttext, p.postlike, u.userprofilepath,u.username FROM post p,user_ u WHERE p.userid=u.userid;




SELECT c.commentid,c.userid,c.postid,c.commenttext,c.commentlike,u.username FROM comment_ c,user_ u WHERE c.userid=u.userid;
```





### 苍穹外卖sql参考

```sql
CREATE DATABASE  IF NOT EXISTS `sky_take_out` ;
USE `sky_take_out`;

DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人',
  `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='地址簿';

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` int DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='菜品及套餐分类';

INSERT INTO `category` VALUES (11,1,'酒水饮料',10,1,'2022-06-09 22:09:18','2022-06-09 22:09:18',1,1);
INSERT INTO `category` VALUES (12,1,'传统主食',9,1,'2022-06-09 22:09:32','2022-06-09 22:18:53',1,1);
INSERT INTO `category` VALUES (13,2,'人气套餐',12,1,'2022-06-09 22:11:38','2022-06-10 11:04:40',1,1);
INSERT INTO `category` VALUES (15,2,'商务套餐',13,1,'2022-06-09 22:14:10','2022-06-10 11:04:48',1,1);
INSERT INTO `category` VALUES (16,1,'蜀味烤鱼',4,1,'2022-06-09 22:15:37','2022-08-31 14:27:25',1,1);
INSERT INTO `category` VALUES (17,1,'蜀味牛蛙',5,1,'2022-06-09 22:16:14','2022-08-31 14:39:44',1,1);
INSERT INTO `category` VALUES (18,1,'特色蒸菜',6,1,'2022-06-09 22:17:42','2022-06-09 22:17:42',1,1);
INSERT INTO `category` VALUES (19,1,'新鲜时蔬',7,1,'2022-06-09 22:18:12','2022-06-09 22:18:28',1,1);
INSERT INTO `category` VALUES (20,1,'水煮鱼',8,1,'2022-06-09 22:22:29','2022-06-09 22:23:45',1,1);
INSERT INTO `category` VALUES (21,1,'汤类',11,1,'2022-06-10 10:51:47','2022-06-10 10:51:47',1,1);

DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int DEFAULT '1' COMMENT '0 停售 1 起售',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='菜品';

INSERT INTO `dish` VALUES (46,'王老吉',11,6.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/41bfcacf-7ad4-4927-8b26-df366553a94c.png','',1,'2022-06-09 22:40:47','2022-06-09 22:40:47',1,1);
INSERT INTO `dish` VALUES (47,'北冰洋',11,4.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4451d4be-89a2-4939-9c69-3a87151cb979.png','还是小时候的味道',1,'2022-06-10 09:18:49','2022-06-10 09:18:49',1,1);
INSERT INTO `dish` VALUES (48,'雪花啤酒',11,4.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/bf8cbfc1-04d2-40e8-9826-061ee41ab87c.png','',1,'2022-06-10 09:22:54','2022-06-10 09:22:54',1,1);
INSERT INTO `dish` VALUES (49,'米饭',12,2.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/76752350-2121-44d2-b477-10791c23a8ec.png','精选五常大米',1,'2022-06-10 09:30:17','2022-06-10 09:30:17',1,1);
INSERT INTO `dish` VALUES (50,'馒头',12,1.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/475cc599-8661-4899-8f9e-121dd8ef7d02.png','优质面粉',1,'2022-06-10 09:34:28','2022-06-10 09:34:28',1,1);
INSERT INTO `dish` VALUES (51,'老坛酸菜鱼',20,56.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4a9cefba-6a74-467e-9fde-6e687ea725d7.png','原料：汤，草鱼，酸菜',1,'2022-06-10 09:40:51','2022-06-10 09:40:51',1,1);
INSERT INTO `dish` VALUES (52,'经典酸菜鮰鱼',20,66.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/5260ff39-986c-4a97-8850-2ec8c7583efc.png','原料：酸菜，江团，鮰鱼',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1);
INSERT INTO `dish` VALUES (53,'蜀味水煮草鱼',20,38.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a6953d5a-4c18-4b30-9319-4926ee77261f.png','原料：草鱼，汤',1,'2022-06-10 09:48:37','2022-06-10 09:48:37',1,1);
INSERT INTO `dish` VALUES (54,'清炒小油菜',19,18.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/3613d38e-5614-41c2-90ed-ff175bf50716.png','原料：小油菜',1,'2022-06-10 09:51:46','2022-06-10 09:51:46',1,1);
INSERT INTO `dish` VALUES (55,'蒜蓉娃娃菜',19,18.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png','原料：蒜，娃娃菜',1,'2022-06-10 09:53:37','2022-06-10 09:53:37',1,1);
INSERT INTO `dish` VALUES (56,'清炒西兰花',19,18.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/e9ec4ba4-4b22-4fc8-9be0-4946e6aeb937.png','原料：西兰花',1,'2022-06-10 09:55:44','2022-06-10 09:55:44',1,1);
INSERT INTO `dish` VALUES (57,'炝炒圆白菜',19,18.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/22f59feb-0d44-430e-a6cd-6a49f27453ca.png','原料：圆白菜',1,'2022-06-10 09:58:35','2022-06-10 09:58:35',1,1);
INSERT INTO `dish` VALUES (58,'清蒸鲈鱼',18,98.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png','原料：鲈鱼',1,'2022-06-10 10:12:28','2022-06-10 10:12:28',1,1);
INSERT INTO `dish` VALUES (59,'东坡肘子',18,138.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png','原料：猪肘棒',1,'2022-06-10 10:24:03','2022-06-10 10:24:03',1,1);
INSERT INTO `dish` VALUES (60,'梅菜扣肉',18,58.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/6080b118-e30a-4577-aab4-45042e3f88be.png','原料：猪肉，梅菜',1,'2022-06-10 10:26:03','2022-06-10 10:26:03',1,1);
INSERT INTO `dish` VALUES (61,'剁椒鱼头',18,66.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/13da832f-ef2c-484d-8370-5934a1045a06.png','原料：鲢鱼，剁椒',1,'2022-06-10 10:28:54','2022-06-10 10:28:54',1,1);
INSERT INTO `dish` VALUES (62,'金汤酸菜牛蛙',17,88.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png','原料：鲜活牛蛙，酸菜',1,'2022-06-10 10:33:05','2022-06-10 10:33:05',1,1);
INSERT INTO `dish` VALUES (63,'香锅牛蛙',17,88.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/f5ac8455-4793-450c-97ba-173795c34626.png','配料：鲜活牛蛙，莲藕，青笋',1,'2022-06-10 10:35:40','2022-06-10 10:35:40',1,1);
INSERT INTO `dish` VALUES (64,'馋嘴牛蛙',17,88.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png','配料：鲜活牛蛙，丝瓜，黄豆芽',1,'2022-06-10 10:37:52','2022-06-10 10:37:52',1,1);
INSERT INTO `dish` VALUES (65,'草鱼2斤',16,68.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/b544d3ba-a1ae-4d20-a860-81cb5dec9e03.png','原料：草鱼，黄豆芽，莲藕',1,'2022-06-10 10:41:08','2022-06-10 10:41:08',1,1);
INSERT INTO `dish` VALUES (66,'江团鱼2斤',16,119.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png','配料：江团鱼，黄豆芽，莲藕',1,'2022-06-10 10:42:42','2022-06-10 10:42:42',1,1);
INSERT INTO `dish` VALUES (67,'鮰鱼2斤',16,72.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/8cfcc576-4b66-4a09-ac68-ad5b273c2590.png','原料：鮰鱼，黄豆芽，莲藕',1,'2022-06-10 10:43:56','2022-06-10 10:43:56',1,1);
INSERT INTO `dish` VALUES (68,'鸡蛋汤',21,4.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c09a0ee8-9d19-428d-81b9-746221824113.png','配料：鸡蛋，紫菜',1,'2022-06-10 10:54:25','2022-06-10 10:54:25',1,1);
INSERT INTO `dish` VALUES (69,'平菇豆腐汤',21,6.00,'https://sky-itcast.oss-cn-beijing.aliyuncs.com/16d0a3d6-2253-4cfc-9b49-bf7bd9eb2ad2.png','配料：豆腐，平菇',1,'2022-06-10 10:55:02','2022-06-10 10:55:02',1,1);

DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '口味名称',
  `value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='菜品口味关系表';

INSERT INTO `dish_flavor` VALUES (40,10,'甜味','[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (41,7,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (42,7,'温度','[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]');
INSERT INTO `dish_flavor` VALUES (45,6,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (46,6,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (47,5,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (48,5,'甜味','[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (49,2,'甜味','[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (50,4,'甜味','[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (51,3,'甜味','[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (52,3,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (86,52,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (87,52,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (88,51,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (89,51,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (92,53,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (93,53,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (94,54,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\"]');
INSERT INTO `dish_flavor` VALUES (95,56,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (96,57,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (97,60,'忌口','[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (101,66,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (102,67,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (103,65,'辣度','[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='员工信息';

INSERT INTO `employee` VALUES (1,'管理员','admin','123456','13812312312','1','110101199001010047',1,'2022-02-15 15:51:20','2022-02-17 09:16:20',10,1);

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='订单明细表';

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime DEFAULT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态 0未支付 1已支付 2退款',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `user_name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `consignee` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人',
  `cancel_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单取消原因',
  `rejection_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单拒绝原因',
  `cancel_time` datetime DEFAULT NULL COMMENT '订单取消时间',
  `estimated_delivery_time` datetime DEFAULT NULL COMMENT '预计送达时间',
  `delivery_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配送状态  1立即送出  0选择具体时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '送达时间',
  `pack_amount` int DEFAULT NULL COMMENT '打包费',
  `tableware_number` int DEFAULT NULL COMMENT '餐具数量',
  `tableware_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='订单表';

DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int DEFAULT '1' COMMENT '售卖状态 0:停售 1:起售',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='套餐';

DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品单价（冗余字段）',
  `copies` int DEFAULT NULL COMMENT '菜品份数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='套餐菜品关系';

DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='购物车';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '微信用户唯一标识',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin COMMENT='用户信息';
```


### tb_user 表 2.0
- 看了下苍穹外卖的
	- ![[Pasted image 20250112155741.png]]
	- 加个



- 调整数据库
	- 原
		- ![[Pasted image 20250112154348.png]]
	- 统一使用下滑线命名
	- 表名tb_user
	- user_id      bigint
		- 用户唯一标识
	- user_name  varchar(32)
		- 用户名
	- user_password varchar(64)
		- 用户密码
	- user_profile_path varchar(255)
		- 用户头像存放的相对地址
	- user_points bigint
		- 用户积分
```sql
DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user(
	user_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
	user_name VARCHAR(32) NOT NULL COMMENT '用户名',
	user_password VARCHAR(64) NOT NULL COMMENT '用户密码',
	user_profile_path VARCHAR(255) COMMENT '头像存放的相对地址',
	user_points BIGINT DEFAULT 0 COMMENT '用户积分'
);


INSERT INTO tb_user (user_name, user_password, user_profile_path,user_points) VALUES
('Alice', 'password123', '/profiles/alice.jpg',98),
('Bob', 'securepass456', '/profiles/bob.png',88),
('Charlie', 'charlie789', '/profiles/charlie.jpeg',98),
('David', 'davidpass321', NULL,9),
('Eve', 'evepassword', '/profiles/eve.png',10);
```


- 改之后
	- ![[Pasted image 20250112161535.png]]

### tb_user表对应的entity
### 苍穹参考
[[分析entity层java代码结构]]

```java
package com.sky.entity;  
  
import lombok.AllArgsConstructor;  
import lombok.Builder;  
import lombok.Data;  
import lombok.NoArgsConstructor;  
  
import java.io.Serializable;  
import java.time.LocalDate;  
import java.time.LocalDateTime;  
  
@Data  
@Builder  
@NoArgsConstructor  
@AllArgsConstructor  
public class User implements Serializable {  
  
    private static final long serialVersionUID = 1L;  
  
    private Long id;  
  
    //微信用户唯一标识  
    private String openid;  
  
    //姓名  
    private String name;  
  
    //手机号  
    private String phone;  
  
    //性别 0 女 1 男  
    private String sex;  
  
    //身份证号  
    private String idNumber;  
  
    //头像  
    private String avatar;  
  
    //注册时间  
    private LocalDateTime createTime;  
}
```


- 修改前
	- ![[Pasted image 20250112162340.png]]
- 修改
	- 保留@Data和@TableName("tb_user")
		- 因为类名和表名不一致所以需要MyBatis提供的@TableName来映射

- 改后
	- ![[Pasted image 20250112163246.png]]
	- 由于参数名和数据库列名不一致，在mapper映射文件需要用resultMap映射一下
		-

### tb_cloudtype
```sql
drop table if exists tb_cloudtype;
CREATE TABLE tb_cloudtype(
	type_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
	type_name VARCHAR(32) NOT NULL COMMENT '云彩类型中文名',
	type_name_en VARCHAR(32) NOT NULL COMMENT '云彩类型英文名',
	type_info VARCHAR(255) NOT NULL COMMENT '云彩类型描述',
	img_max BIGINT NOT NULL COMMENT '云彩某类型图片数量'
);

INSERT INTO tb_cloudtype(type_name, type_name_en, type_info, img_max) VALUES
( '卷云', 'cirrus', '卷云是一种位于高空的云，通常在6,000到12,000米的高度形成，主要由冰晶组成。卷云通常呈现细长、丝状或羽毛状，往往伴随着风的方向而排列。它们在晴朗的天空中显得十分纤细、透明，是天气即将变化的预兆。尽管卷云本身不会带来降水，但它们的出现通常预示着天气将变得不稳定。', 10),
( '卷积云', 'cirrocumulus', '卷积云通常形成于高空，主要由小冰晶和过冷水滴组成。它们的外观呈现为白色的小片云，排列成波浪或条纹状，给天空增添了丰富的纹理。卷积云常预示着天气即将转变，特别是在夏季的高温天气中，卷积云可能预示着雷暴的来临。', 10),
( '卷层云', 'cirrostratus', '卷层云是广泛分布的高层云，通常形成在大气层的高处，由冰晶组成。它们呈现出一种薄纱般的外观，有时会导致太阳或月亮形成光晕。卷层云覆盖大片天空，常预示着温暖锋面或天气系统的到来，通常预示着未来24小时内将有降水。', 10),
( '高积云', 'altocumulus', '高积云是一种中层云，通常位于2,000到7,000米之间。它们呈现出白色或灰色的块状云，常排列成波浪状或条纹状。高积云常标志着天气的变化，特别是在夏季的清晨出现时，预示着当天可能会发生雷暴或其他不稳定天气。', 10),
( '高层云', 'altostratus', '高层云是一种广泛的中层云，通常覆盖整个天空，呈现灰色或蓝灰色。它们通常出现在温暖锋面前，预示着降雨或降雪即将来临。高层云的厚度不一，厚的部分可能使太阳完全看不见，而薄的部分则可能让太阳显得模糊不清。', 10),
( '雨层云', 'nimbostratus', '雨层云是一种厚重的低层云，通常带来持续的降雨或降雪。它们呈现灰色或深灰色，覆盖天空大部分区域，使得天空显得阴沉。雨层云常与温暖锋面相关，通常持续数小时甚至更长时间，导致连续的降水，是典型的锋面天气云。', 10),
( '积雨云', 'cumulonimbus', '积雨云是极其发达的垂直云，通常带来强烈的雷暴、暴雨、冰雹或龙卷风。积雨云通常在不稳定的天气条件下发展，从低层一直延伸到高空，有时高度可达12,000米或更高。它们的顶部呈现砧状，预示着强烈天气即将到来，是雷暴的主要特征云。', 10),
( '积云', 'cumulus', '积云是白色或灰白色的块状云，常见于晴朗的天气中。它们的底部通常是平坦的，而顶部则呈现为隆起的圆顶状，像棉花一样蓬松。积云通常在早晨开始形成，在日照充足时逐渐发展。当积云发展较大时，可能会带来局部阵雨或雷暴。', 10),
( '层云', 'stratus', '层云是一种低层云，覆盖整个天空，呈现灰色或白色，给人一种阴沉的感觉。层云通常出现在稳定的大气条件下，尤其是在冷空气流动较弱时，常带来细雨、毛毛雨或细雪，是典型的冬季或湿冷天气的标志。', 10),
( '层积云', 'stratocumulus', '层积云是一种低层的块状云，通常呈现为白色或灰色，云块较大，排列成条纹状或波状。它们常出现在冷锋或温暖锋面之后，层积云通常不会带来显著的降水，但可能带来零星小雨或毛毛雨。', 10);
```
### tb_post
```sql
DROP TABLE if exists tb_post;

CREATE TABLE tb_post(
	post_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
	user_id BIGINT NOT NULL COMMENT '外键',
	post_img_path VARCHAR(255) NOT NULL COMMENT '帖子的图片相对路径',
	post_text VARCHAR(1024) COMMENT '帖子的文字部分',
	post_like BIGINT COMMENT '帖子的点赞数',
	FOREIGN KEY (user_id) REFERENCES tb_user(user_id)
);


INSERT INTO tb_post (user_id, post_img_path, post_text, post_like) VALUES
(1, '/posts/post1.jpg', 'Enjoying the sunny day!', 120),
(2, '/posts/post2.jpg', 'Had a great time at the beach.', 85),
(3, '/posts/post3.jpg', 'New adventure begins!', 150),
(4, '/posts/post4.jpg', 'Loving this new place!', 200),
(5, '/posts/post5.jpg', 'Just finished a great book.', 95);
```

### tb_comment


```sql
DROP TABLE IF EXISTS tb_comment;
CREATE TABLE tb_comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    user_id  BIGINT NOT NULL COMMENT '外键',
    post_id BIGINT NOT NULL COMMENT '外键',
    comment_text VARCHAR(1024),
    comment_like BIGINT,
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    FOREIGN KEY (post_id) REFERENCES tb_post(post_id)
);

INSERT INTO tb_comment (user_id, post_id, comment_text, comment_like) VALUES
(1, 1, 'Great post! Really enjoyed it.', 45),
(2, 1, 'Thanks for sharing!', 30),
(3, 1, 'Amazing picture!', 60),
(4, 1, 'Well said!', 25),
(5, 1, 'This is so inspiring!', 70),
(2, 1, 'Love the vibes!', 35),
(3, 1, 'Beautiful scenery!', 50),
(8, 4, 'Such a great shot!', 40),

(1, 2, 'Looks like fun!', 55),
(2, 2, 'Wish I was there!', 40),
(3, 2, 'Perfect beach day!', 65),
(4, 2, 'So relaxing!', 45),
(5, 2, 'The ocean looks amazing!', 80),
(1, 2, 'Great memories!', 35),
(2, 2, 'Thanks for the share!', 25),
(3, 2, 'Can’t wait to go there!', 60),

(1, 3, 'Adventure awaits!', 70),
(2, 3, 'Exciting times ahead!', 55),
(3, 3, 'Safe travels!', 45),
(4, 3, 'Have fun!', 50),
(5, 3, 'Looking forward to the stories!', 65),
(1, 3, 'New beginnings!', 35),
(2, 3, 'This is so cool!', 40),
(3, 3, 'Best of luck!', 55),

(1, 4, 'Looks amazing!', 60),
(2, 4, 'Can’t wait to visit!', 45),
(3, 4, 'So beautiful!', 75),
(4, 4, 'You’re in paradise!', 65),
(5, 4, 'Living the dream!', 85),
(1, 4, 'Take me there!', 55),
(2, 4, 'Absolutely stunning!', 50),
(3, 4, 'I’m so jealous!', 40),

(1, 5, 'What’s the book?', 70),
(2, 5, 'Sounds interesting!', 60),
(3, 5, 'I need to read it too!', 55),
(4, 5, 'Would love to hear more!', 45),
(5, 5, 'Books are the best!', 75),
(1, 5, 'Can you recommend it?', 65),
(2, 5, 'Looks like a good read!', 50),
(3, 5, 'Adding it to my list!', 60);
```

### 给所有的表加上创建时间和修改时间

  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',



- 先用tb_comment试一试

```sql
DROP TABLE IF EXISTS tb_comment;
CREATE TABLE tb_comment (
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    user_id  BIGINT NOT NULL COMMENT '外键',
    post_id BIGINT NOT NULL COMMENT '外键',
    comment_text VARCHAR(1024),
    comment_like BIGINT,
    create_time datetime DEFAULT NULL COMMENT '创建时间',
    update_time datetime DEFAULT NULL COMMENT '更新时间',
    FOREIGN KEY (user_id) REFERENCES tb_user(user_id),
    FOREIGN KEY (post_id) REFERENCES tb_post(post_id)
);

INSERT INTO tb_comment (user_id, post_id, comment_text, comment_like) VALUES
(1, 1, 'Great post! Really enjoyed it.', 45),
(2, 1, 'Thanks for sharing!', 30),
(3, 1, 'Amazing picture!', 60),
(4, 1, 'Well said!', 25),
(5, 1, 'This is so inspiring!', 70),
(2, 1, 'Love the vibes!', 35),
(3, 1, 'Beautiful scenery!', 50),
(8, 4, 'Such a great shot!', 40),

(1, 2, 'Looks like fun!', 55),
(2, 2, 'Wish I was there!', 40),
(3, 2, 'Perfect beach day!', 65),
(4, 2, 'So relaxing!', 45),
(5, 2, 'The ocean looks amazing!', 80),
(1, 2, 'Great memories!', 35),
(2, 2, 'Thanks for the share!', 25),
(3, 2, 'Can’t wait to go there!', 60),

(1, 3, 'Adventure awaits!', 70),
(2, 3, 'Exciting times ahead!', 55),
(3, 3, 'Safe travels!', 45),
(4, 3, 'Have fun!', 50),
(5, 3, 'Looking forward to the stories!', 65),
(1, 3, 'New beginnings!', 35),
(2, 3, 'This is so cool!', 40),
(3, 3, 'Best of luck!', 55),

(1, 4, 'Looks amazing!', 60),
(2, 4, 'Can’t wait to visit!', 45),
(3, 4, 'So beautiful!', 75),
(4, 4, 'You’re in paradise!', 65),
(5, 4, 'Living the dream!', 85),
(1, 4, 'Take me there!', 55),
(2, 4, 'Absolutely stunning!', 50),
(3, 4, 'I’m so jealous!', 40),

(1, 5, 'What’s the book?', 70),
(2, 5, 'Sounds interesting!', 60),
(3, 5, 'I need to read it too!', 55),
(4, 5, 'Would love to hear more!', 45),
(5, 5, 'Books are the best!', 75),
(1, 5, 'Can you recommend it?', 65),
(2, 5, 'Looks like a good read!', 50),
(3, 5, 'Adding it to my list!', 60);
```



### 修改后sql
- 见/static/sql/