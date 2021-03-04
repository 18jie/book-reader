/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : book_reader

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 04/03/2021 23:25:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_message
-- ----------------------------
DROP TABLE IF EXISTS `b_message`;
CREATE TABLE `b_message`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '消息详情',
  `status` int(11) NULL DEFAULT NULL COMMENT '是否以读 0-未读 1-已读',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for b_recommend
-- ----------------------------
DROP TABLE IF EXISTS `b_recommend`;
CREATE TABLE `b_recommend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `deleted` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_recommend
-- ----------------------------
INSERT INTO `b_recommend` VALUES (1, 'https://book.qidian.com/info/1015176373', 'https://bossaudioandcomic-1252317822.image.myqcloud.com/activity/document/6071e3013ea6871691af31fb33781542.jpg', '2021-03-04 22:50:47', '2021-03-04 22:50:50', 0);
INSERT INTO `b_recommend` VALUES (2, 'https://book.qidian.com/info/1023586405', 'https://bossaudioandcomic-1252317822.image.myqcloud.com/activity/document/28ba9971dcf50191aa2db6464f6f9ed1.jpg', '2021-03-04 22:52:05', '2021-03-04 22:53:08', 0);
INSERT INTO `b_recommend` VALUES (3, 'https://book.qidian.com/info/1021693079', 'https://bossaudioandcomic-1252317822.image.myqcloud.com/activity/document/fbccab5938143c84f28e7dba483a1ad2.jpg', '2021-03-04 22:52:50', '2021-03-04 22:52:50', 0);
INSERT INTO `b_recommend` VALUES (4, 'https://book.qidian.com/info/1023422452', 'https://bossaudioandcomic-1252317822.image.myqcloud.com/activity/document/212d1086f654de8a33e1b590e3fef461.jpg', '2021-03-04 22:53:16', '2021-03-04 22:53:16', 0);

-- ----------------------------
-- Table structure for b_user
-- ----------------------------
DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户手机',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `login_count` int(11) NOT NULL DEFAULT 0 COMMENT '登录次数',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b_user
-- ----------------------------
INSERT INTO `b_user` VALUES (6, 25, '18866856479', '王越', 'e10adc3949ba59abbe56e057f20f883e', 2, '2021-03-02 00:00:00', '2020-11-17 23:32:47', '2021-03-02 21:58:49', 0);

SET FOREIGN_KEY_CHECKS = 1;
