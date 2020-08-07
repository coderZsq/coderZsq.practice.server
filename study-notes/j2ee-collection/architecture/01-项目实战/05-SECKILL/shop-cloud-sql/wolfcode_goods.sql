/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : wolfcode_goods

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2019-05-30 17:56:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `good_name` varchar(16) DEFAULT NULL,
  `good_title` varchar(64) DEFAULT NULL,
  `good_img` varchar(64) DEFAULT NULL,
  `good_detail` longtext,
  `good_price` decimal(10,2) DEFAULT NULL,
  `good_stock` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', 'iPhoneXR', 'iPhoneXR', '/img/iphonexr.png', '苹果手机', '5179.00', '10');
INSERT INTO `t_goods` VALUES ('2', '小米手机', '小米手机', '/img/mi.png', '小米手机', '2799.00', '10');
