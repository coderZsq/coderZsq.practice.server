/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : wolfcode_member

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 01/04/2020 00:57:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) DEFAULT NULL COMMENT '盐',
  `head` varchar(128) DEFAULT NULL COMMENT '头像',
  `register_date` datetime DEFAULT NULL COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录日期',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (13088889999, 'leon', 'e5d22cfc746c7da8da84e0a996e0fffa', '1a2d3c4d', NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
