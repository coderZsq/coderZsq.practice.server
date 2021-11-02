/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 07/07/2021 17:46:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lockt
-- ----------------------------
DROP TABLE IF EXISTS `lockt`;
CREATE TABLE `lockt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `col1` int(11) DEFAULT NULL,
  `col2` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1235 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of lockt
-- ----------------------------
BEGIN;
INSERT INTO `lockt` VALUES (1, 1, 1);
INSERT INTO `lockt` VALUES (2, 2, 3);
INSERT INTO `lockt` VALUES (5, 5, 5);
INSERT INTO `lockt` VALUES (6, 6, 9);
INSERT INTO `lockt` VALUES (10, 10, 1111);
INSERT INTO `lockt` VALUES (123, 123, 8);
INSERT INTO `lockt` VALUES (1007, 10077, 144);
INSERT INTO `lockt` VALUES (1008, 1008, 220);
INSERT INTO `lockt` VALUES (1019, 1019, 200);
INSERT INTO `lockt` VALUES (1020, 1020, 201);
INSERT INTO `lockt` VALUES (1111, 1111, 1111);
INSERT INTO `lockt` VALUES (1234, 1234, 33);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
