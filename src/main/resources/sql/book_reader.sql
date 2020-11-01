/*
SQLyog Community v13.1.2 (64 bit)
MySQL - 8.0.16 : Database - book_reader
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`book_reader` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `book_reader`;

/*Table structure for table `b_barrage` */

DROP TABLE IF EXISTS `b_barrage`;

CREATE TABLE `b_barrage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL COMMENT '书籍id',
  `chapter_id` int(11) DEFAULT NULL COMMENT '章节id',
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '弹幕内容',
  `content_code` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '章节内容编码',
  `level` int(11) DEFAULT NULL COMMENT '弹幕级别',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `barrage_book_id` (`book_id`),
  KEY `barrage_chapter_id` (`chapter_id`),
  KEY `barrage_content_code` (`content_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `b_book` */

DROP TABLE IF EXISTS `b_book`;

CREATE TABLE `b_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '书名',
  `book_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '书籍类型',
  `book_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '下载地址',
  `book_img_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `book_writer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作者',
  `book_update_time` datetime DEFAULT NULL COMMENT '数据更新时间',
  `book_click_count` int(11) NOT NULL DEFAULT '0' COMMENT '点击次数',
  `favorite_count` int(11) NOT NULL DEFAULT '0' COMMENT '收藏数',
  `like_count` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `book_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '文件位置',
  `img_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '书籍地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '记录更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26909 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `b_chapter` */

DROP TABLE IF EXISTS `b_chapter`;

CREATE TABLE `b_chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL COMMENT '书籍id',
  `chapter_num` int(11) DEFAULT NULL COMMENT '章节号',
  `chapter_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `chapter_book_id` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31937 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `b_user` */

DROP TABLE IF EXISTS `b_user`;

CREATE TABLE `b_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户手机',
  `user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `b_user_authority` */

DROP TABLE IF EXISTS `b_user_authority`;

CREATE TABLE `b_user_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `write_authority` int(11) DEFAULT NULL COMMENT '写作权限',
  `read_authority` int(11) DEFAULT NULL COMMENT '阅读权限（指弹幕）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `user_authority_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `b_user_favorite` */

DROP TABLE IF EXISTS `b_user_favorite`;

CREATE TABLE `b_user_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `book_id` int(11) DEFAULT NULL COMMENT '书籍id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_favorite_user_id` (`user_id`),
  KEY `user_favorite_book_id` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `b_weight` */

DROP TABLE IF EXISTS `b_weight`;

CREATE TABLE `b_weight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `click` int(11) NOT NULL DEFAULT '1' COMMENT '点击权重',
  `favorite` int(11) NOT NULL DEFAULT '1' COMMENT '收藏权重',
  `like` int(11) NOT NULL DEFAULT '1' COMMENT '喜欢权重',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
