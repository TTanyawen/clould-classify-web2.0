/*
 Navicat Premium Data Transfer

 Source Server         : ForSpring
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : clouddb2.0

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 06/02/2025 08:48:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cloudtype
-- ----------------------------
DROP TABLE IF EXISTS `cloudtype`;
CREATE TABLE `cloudtype`  (
  `typeid` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `typename` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `typenameen` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `typeinfo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `imgmax` decimal(10, 0) NOT NULL,
  PRIMARY KEY (`typeid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cloudtype
-- ----------------------------
INSERT INTO `cloudtype` VALUES ('C001', '卷云', 'cirrus', '卷云是一种位于高空的云，通常在6,000到12,000米的高度形成，主要由冰晶组成。卷云通常呈现细长、丝状或羽毛状，往往伴随着风的方向而排列。它们在晴朗的天空中显得十分纤细、透明，是天气即将变化的预兆。尽管卷云本身不会带来降水，但它们的出现通常预示着天气将变得不稳定。', 10);
INSERT INTO `cloudtype` VALUES ('C002', '卷积云', 'cirrocumulus', '卷积云通常形成于高空，主要由小冰晶和过冷水滴组成。它们的外观呈现为白色的小片云，排列成波浪或条纹状，给天空增添了丰富的纹理。卷积云常预示着天气即将转变，特别是在夏季的高温天气中，卷积云可能预示着雷暴的来临。', 10);
INSERT INTO `cloudtype` VALUES ('C003', '卷层云', 'cirrostratus', '卷层云是广泛分布的高层云，通常形成在大气层的高处，由冰晶组成。它们呈现出一种薄纱般的外观，有时会导致太阳或月亮形成光晕。卷层云覆盖大片天空，常预示着温暖锋面或天气系统的到来，通常预示着未来24小时内将有降水。', 10);
INSERT INTO `cloudtype` VALUES ('C004', '高积云', 'altocumulus', '高积云是一种中层云，通常位于2,000到7,000米之间。它们呈现出白色或灰色的块状云，常排列成波浪状或条纹状。高积云常标志着天气的变化，特别是在夏季的清晨出现时，预示着当天可能会发生雷暴或其他不稳定天气。', 10);
INSERT INTO `cloudtype` VALUES ('C005', '高层云', 'altostratus', '高层云是一种广泛的中层云，通常覆盖整个天空，呈现灰色或蓝灰色。它们通常出现在温暖锋面前，预示着降雨或降雪即将来临。高层云的厚度不一，厚的部分可能使太阳完全看不见，而薄的部分则可能让太阳显得模糊不清。', 10);
INSERT INTO `cloudtype` VALUES ('C006', '雨层云', 'nimbostratus', '雨层云是一种厚重的低层云，通常带来持续的降雨或降雪。它们呈现灰色或深灰色，覆盖天空大部分区域，使得天空显得阴沉。雨层云常与温暖锋面相关，通常持续数小时甚至更长时间，导致连续的降水，是典型的锋面天气云。', 10);
INSERT INTO `cloudtype` VALUES ('C007', '积雨云', 'cumulonimbus', '积雨云是极其发达的垂直云，通常带来强烈的雷暴、暴雨、冰雹或龙卷风。积雨云通常在不稳定的天气条件下发展，从低层一直延伸到高空，有时高度可达12,000米或更高。它们的顶部呈现砧状，预示着强烈天气即将到来，是雷暴的主要特征云。', 10);
INSERT INTO `cloudtype` VALUES ('C008', '积云', 'cumulus', '积云是白色或灰白色的块状云，常见于晴朗的天气中。它们的底部通常是平坦的，而顶部则呈现为隆起的圆顶状，像棉花一样蓬松。积云通常在早晨开始形成，在日照充足时逐渐发展。当积云发展较大时，可能会带来局部阵雨或雷暴。', 10);
INSERT INTO `cloudtype` VALUES ('C009', '层云', 'stratus', '层云是一种低层云，覆盖整个天空，呈现灰色或白色，给人一种阴沉的感觉。层云通常出现在稳定的大气条件下，尤其是在冷空气流动较弱时，常带来细雨、毛毛雨或细雪，是典型的冬季或湿冷天气的标志。', 10);
INSERT INTO `cloudtype` VALUES ('C010', '层积云', 'stratocumulus', '层积云是一种低层的块状云，通常呈现为白色或灰色，云块较大，排列成条纹状或波状。它们常出现在冷锋或温暖锋面之后，层积云通常不会带来显著的降水，但可能带来零星小雨或毛毛雨。', 10);

-- ----------------------------
-- Table structure for tb_cloudtype
-- ----------------------------
DROP TABLE IF EXISTS `tb_cloudtype`;
CREATE TABLE `tb_cloudtype`  (
  `type_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '云彩类型中文名',
  `type_name_en` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '云彩类型英文名',
  `type_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '云彩类型描述',
  `img_max` bigint NOT NULL COMMENT '云彩某类型图片数量',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cloudtype
-- ----------------------------
INSERT INTO `tb_cloudtype` VALUES (1, '卷云', 'cirrus', '卷云是一种位于高空的云，通常在6,000到12,000米的高度形成，主要由冰晶组成。卷云通常呈现细长、丝状或羽毛状，往往伴随着风的方向而排列。它们在晴朗的天空中显得十分纤细、透明，是天气即将变化的预兆。尽管卷云本身不会带来降水，但它们的出现通常预示着天气将变得不稳定。', 10);
INSERT INTO `tb_cloudtype` VALUES (2, '卷积云', 'cirrocumulus', '卷积云通常形成于高空，主要由小冰晶和过冷水滴组成。它们的外观呈现为白色的小片云，排列成波浪或条纹状，给天空增添了丰富的纹理。卷积云常预示着天气即将转变，特别是在夏季的高温天气中，卷积云可能预示着雷暴的来临。', 10);
INSERT INTO `tb_cloudtype` VALUES (3, '卷层云', 'cirrostratus', '卷层云是广泛分布的高层云，通常形成在大气层的高处，由冰晶组成。它们呈现出一种薄纱般的外观，有时会导致太阳或月亮形成光晕。卷层云覆盖大片天空，常预示着温暖锋面或天气系统的到来，通常预示着未来24小时内将有降水。', 10);
INSERT INTO `tb_cloudtype` VALUES (4, '高积云', 'altocumulus', '高积云是一种中层云，通常位于2,000到7,000米之间。它们呈现出白色或灰色的块状云，常排列成波浪状或条纹状。高积云常标志着天气的变化，特别是在夏季的清晨出现时，预示着当天可能会发生雷暴或其他不稳定天气。', 10);
INSERT INTO `tb_cloudtype` VALUES (5, '高层云', 'altostratus', '高层云是一种广泛的中层云，通常覆盖整个天空，呈现灰色或蓝灰色。它们通常出现在温暖锋面前，预示着降雨或降雪即将来临。高层云的厚度不一，厚的部分可能使太阳完全看不见，而薄的部分则可能让太阳显得模糊不清。', 10);
INSERT INTO `tb_cloudtype` VALUES (6, '雨层云', 'nimbostratus', '雨层云是一种厚重的低层云，通常带来持续的降雨或降雪。它们呈现灰色或深灰色，覆盖天空大部分区域，使得天空显得阴沉。雨层云常与温暖锋面相关，通常持续数小时甚至更长时间，导致连续的降水，是典型的锋面天气云。', 10);
INSERT INTO `tb_cloudtype` VALUES (7, '积雨云', 'cumulonimbus', '积雨云是极其发达的垂直云，通常带来强烈的雷暴、暴雨、冰雹或龙卷风。积雨云通常在不稳定的天气条件下发展，从低层一直延伸到高空，有时高度可达12,000米或更高。它们的顶部呈现砧状，预示着强烈天气即将到来，是雷暴的主要特征云。', 10);
INSERT INTO `tb_cloudtype` VALUES (8, '积云', 'cumulus', '积云是白色或灰白色的块状云，常见于晴朗的天气中。它们的底部通常是平坦的，而顶部则呈现为隆起的圆顶状，像棉花一样蓬松。积云通常在早晨开始形成，在日照充足时逐渐发展。当积云发展较大时，可能会带来局部阵雨或雷暴。', 10);
INSERT INTO `tb_cloudtype` VALUES (9, '层云', 'stratus', '层云是一种低层云，覆盖整个天空，呈现灰色或白色，给人一种阴沉的感觉。层云通常出现在稳定的大气条件下，尤其是在冷空气流动较弱时，常带来细雨、毛毛雨或细雪，是典型的冬季或湿冷天气的标志。', 10);
INSERT INTO `tb_cloudtype` VALUES (10, '层积云', 'stratocumulus', '层积云是一种低层的块状云，通常呈现为白色或灰色，云块较大，排列成条纹状或波状。它们常出现在冷锋或温暖锋面之后，层积云通常不会带来显著的降水，但可能带来零星小雨或毛毛雨。', 10);

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment`  (
  `comment_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '外键',
  `post_id` bigint NOT NULL COMMENT '外键',
  `comment_text` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_like` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建用户',
  `update_user` bigint NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `post_id`(`post_id` ASC) USING BTREE,
  CONSTRAINT `tb_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_comment_ibfk_2` FOREIGN KEY (`post_id`) REFERENCES `tb_post` (`post_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
INSERT INTO `tb_comment` VALUES (1, 1, 1, 'Great post! Really enjoyed it.', 59, '2024-01-01 10:00:00', '2025-01-18 14:54:14', 1, 24);
INSERT INTO `tb_comment` VALUES (2, 2, 1, 'Thanks for sharing!', 37, '2024-01-01 11:00:00', '2025-01-18 14:42:55', 2, 2);
INSERT INTO `tb_comment` VALUES (3, 3, 1, 'Amazing picture!', 63, '2024-01-01 11:15:00', '2025-01-18 14:59:09', 3, 24);
INSERT INTO `tb_comment` VALUES (4, 4, 1, 'Well said!', 26, '2024-01-01 11:30:00', '2025-01-18 14:44:21', 4, 4);
INSERT INTO `tb_comment` VALUES (5, 5, 1, 'This is so inspiring!', 71, '2024-01-01 12:00:00', '2025-01-18 14:59:11', 5, 24);
INSERT INTO `tb_comment` VALUES (6, 2, 1, 'Love the vibes!', 35, '2024-01-01 12:15:00', '2024-01-01 14:30:00', 2, 2);
INSERT INTO `tb_comment` VALUES (7, 3, 1, 'Beautiful scenery!', 51, '2024-01-01 12:30:00', '2025-01-19 18:58:05', 3, 1);
INSERT INTO `tb_comment` VALUES (8, 8, 4, 'Such a great shot!', 41, '2024-01-01 13:00:00', '2025-01-18 14:45:22', 8, 8);
INSERT INTO `tb_comment` VALUES (9, 1, 2, 'Looks like fun!', 59, '2024-01-02 10:00:00', '2025-01-18 14:46:12', 1, 1);
INSERT INTO `tb_comment` VALUES (10, 2, 2, 'Wish I was there!', 42, '2024-01-02 11:00:00', '2025-01-18 14:59:12', 2, 24);
INSERT INTO `tb_comment` VALUES (11, 3, 2, 'Perfect beach day!', 65, '2024-01-02 11:15:00', '2024-01-02 13:00:00', 3, 3);
INSERT INTO `tb_comment` VALUES (12, 4, 2, 'So relaxing!', 45, '2024-01-02 11:30:00', '2024-01-02 13:30:00', 4, 4);
INSERT INTO `tb_comment` VALUES (13, 5, 2, 'The ocean looks amazing!', 81, '2024-01-02 12:00:00', '2025-01-18 14:44:24', 5, 5);
INSERT INTO `tb_comment` VALUES (14, 1, 2, 'Great memories!', 35, '2024-01-02 12:15:00', '2024-01-02 14:30:00', 1, 1);
INSERT INTO `tb_comment` VALUES (15, 2, 2, 'Thanks for the share!', 29, '2024-01-02 12:30:00', '2025-02-06 08:40:50', 2, 25);
INSERT INTO `tb_comment` VALUES (16, 3, 2, 'Can’t wait to go there!', 60, '2024-01-02 13:00:00', '2024-01-02 15:30:00', 3, 3);
INSERT INTO `tb_comment` VALUES (17, 1, 3, 'Adventure awaits!', 70, '2024-01-03 10:00:00', '2024-01-03 12:00:00', 1, 1);
INSERT INTO `tb_comment` VALUES (18, 2, 3, 'Exciting times ahead!', 55, '2024-01-03 11:00:00', '2024-01-03 12:30:00', 2, 2);
INSERT INTO `tb_comment` VALUES (19, 3, 3, 'Safe travels!', 45, '2024-01-03 11:15:00', '2024-01-03 13:00:00', 3, 3);
INSERT INTO `tb_comment` VALUES (20, 4, 3, 'Have fun!', 50, '2024-01-03 11:30:00', '2024-01-03 13:30:00', 4, 4);
INSERT INTO `tb_comment` VALUES (21, 5, 3, 'Looking forward to the stories!', 65, '2024-01-03 12:00:00', '2024-01-03 14:00:00', 5, 5);
INSERT INTO `tb_comment` VALUES (22, 1, 3, 'New beginnings!', 35, '2024-01-03 12:15:00', '2024-01-03 14:30:00', 1, 1);
INSERT INTO `tb_comment` VALUES (23, 2, 3, 'This is so cool!', 40, '2024-01-03 12:30:00', '2024-01-03 15:00:00', 2, 2);
INSERT INTO `tb_comment` VALUES (24, 3, 3, 'Best of luck!', 55, '2024-01-03 13:00:00', '2024-01-03 15:30:00', 3, 3);
INSERT INTO `tb_comment` VALUES (25, 1, 4, 'Looks amazing!', 61, '2024-01-04 10:00:00', '2025-01-18 14:59:15', 1, 24);
INSERT INTO `tb_comment` VALUES (26, 2, 4, 'Can’t wait to visit!', 45, '2024-01-04 11:00:00', '2024-01-04 12:30:00', 2, 2);
INSERT INTO `tb_comment` VALUES (27, 3, 4, 'So beautiful!', 75, '2024-01-04 11:15:00', '2024-01-04 13:00:00', 3, 3);
INSERT INTO `tb_comment` VALUES (28, 4, 4, 'You’re in paradise!', 65, '2024-01-04 11:30:00', '2024-01-04 13:30:00', 4, 4);
INSERT INTO `tb_comment` VALUES (29, 5, 4, 'Living the dream!', 85, '2024-01-04 12:00:00', '2024-01-04 14:00:00', 5, 5);
INSERT INTO `tb_comment` VALUES (30, 1, 4, 'Take me there!', 55, '2024-01-04 12:15:00', '2024-01-04 14:30:00', 1, 1);
INSERT INTO `tb_comment` VALUES (31, 2, 4, 'Absolutely stunning!', 50, '2024-01-04 12:30:00', '2024-01-04 15:00:00', 2, 2);
INSERT INTO `tb_comment` VALUES (32, 3, 4, 'I’m so jealous!', 40, '2024-01-04 13:00:00', '2024-01-04 15:30:00', 3, 3);
INSERT INTO `tb_comment` VALUES (33, 1, 5, 'What’s the book?', 70, '2024-01-05 10:00:00', '2024-01-05 12:00:00', 1, 1);
INSERT INTO `tb_comment` VALUES (34, 2, 5, 'Sounds interesting!', 60, '2024-01-05 11:00:00', '2024-01-05 12:30:00', 2, 2);
INSERT INTO `tb_comment` VALUES (35, 3, 5, 'I need to read it too!', 55, '2024-01-05 11:15:00', '2024-01-05 13:00:00', 3, 3);
INSERT INTO `tb_comment` VALUES (36, 4, 5, 'Would love to hear more!', 45, '2024-01-05 11:30:00', '2024-01-05 13:30:00', 4, 4);
INSERT INTO `tb_comment` VALUES (37, 5, 5, 'Books are the best!', 75, '2024-01-05 12:00:00', '2024-01-05 14:00:00', 5, 5);
INSERT INTO `tb_comment` VALUES (38, 1, 5, 'Can you recommend it?', 65, '2024-01-05 12:15:00', '2024-01-05 14:30:00', 1, 1);
INSERT INTO `tb_comment` VALUES (39, 2, 5, 'Looks like a good read!', 50, '2024-01-05 12:30:00', '2024-01-05 15:00:00', 2, 2);
INSERT INTO `tb_comment` VALUES (40, 3, 5, 'Adding it to my list!', 60, '2024-01-05 13:00:00', '2024-01-05 15:30:00', 3, 3);
INSERT INTO `tb_comment` VALUES (41, 1, 1, 'yes！good cloud! bro', 1, '2025-01-19 18:57:59', '2025-01-19 18:58:04', 1, 1);

-- ----------------------------
-- Table structure for tb_post
-- ----------------------------
DROP TABLE IF EXISTS `tb_post`;
CREATE TABLE `tb_post`  (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '外键',
  `post_img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '帖子的图片相对路径',
  `post_text` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '帖子的文字部分',
  `post_like` bigint NULL DEFAULT NULL COMMENT '帖子的点赞数',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `tb_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_post
-- ----------------------------
INSERT INTO `tb_post` VALUES (1, 1, '/posts/post1.jpg', 'Enjoying the sunny day!', 169);
INSERT INTO `tb_post` VALUES (2, 2, '/posts/post2.jpg', 'Had a great time at the beach.', 110);
INSERT INTO `tb_post` VALUES (3, 3, '/posts/post3.jpg', 'New adventure begins!', 152);
INSERT INTO `tb_post` VALUES (4, 4, '/posts/post4.jpg', 'Loving this new place!', 202);
INSERT INTO `tb_post` VALUES (5, 5, '/posts/post5.jpg', 'Just finished a great book.', 95);
INSERT INTO `tb_post` VALUES (6, 1, '/posts/post8.jpg', 'fine~`~~', 13);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `user_profile_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像存放的相对地址',
  `user_points` bigint NULL DEFAULT 0 COMMENT '用户积分',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'Alice', '482c811da5d5b4bc6d497ffa98491e38', '/profiles/alice.jpg', 98);
INSERT INTO `tb_user` VALUES (2, 'Bob', 'securepass456', '/profiles/bob.png', 88);
INSERT INTO `tb_user` VALUES (3, 'Charlie', 'charlie789', '/profiles/charlie.jpeg', 98);
INSERT INTO `tb_user` VALUES (4, 'David', 'davidpass321', NULL, 9);
INSERT INTO `tb_user` VALUES (5, 'Eve', 'evepassword', '/profiles/eve.png', 10);
INSERT INTO `tb_user` VALUES (6, '123', '123', NULL, 0);
INSERT INTO `tb_user` VALUES (7, '1234', '123', NULL, 0);
INSERT INTO `tb_user` VALUES (8, '1235', '123', NULL, 0);
INSERT INTO `tb_user` VALUES (9, '333', '111', NULL, 0);
INSERT INTO `tb_user` VALUES (10, '44', '44', NULL, 0);
INSERT INTO `tb_user` VALUES (11, '54', '54', NULL, 0);
INSERT INTO `tb_user` VALUES (12, '1111', '1111', NULL, 0);
INSERT INTO `tb_user` VALUES (13, '999', '999', NULL, 0);
INSERT INTO `tb_user` VALUES (14, '000', '000', NULL, 0);
INSERT INTO `tb_user` VALUES (15, '444', '444', NULL, 0);
INSERT INTO `tb_user` VALUES (16, 'bbb', 'bbbb', NULL, 0);
INSERT INTO `tb_user` VALUES (17, '222', '222', NULL, 0);
INSERT INTO `tb_user` VALUES (18, '345ttt', '123456', NULL, 0);
INSERT INTO `tb_user` VALUES (19, 'Alice5', '123456', NULL, 0);
INSERT INTO `tb_user` VALUES (20, '111', '123456', NULL, 0);
INSERT INTO `tb_user` VALUES (21, '12357', '123123', NULL, 0);
INSERT INTO `tb_user` VALUES (22, '12399', '123999', NULL, 0);
INSERT INTO `tb_user` VALUES (23, 'tanyawen4', 'e10adc3949ba59abbe56e057f20f883e', NULL, 0);
INSERT INTO `tb_user` VALUES (24, 'tttttt', 'bcc720f2981d1a68dbd66ffd67560c37', NULL, 0);
INSERT INTO `tb_user` VALUES (25, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
