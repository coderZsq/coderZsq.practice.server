/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : wolfcode_seckill

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2019-05-30 17:56:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order_info
-- ----------------------------
DROP TABLE IF EXISTS `t_order_info`;
CREATE TABLE `t_order_info` (
  `order_no` varchar(50) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `good_id` bigint(20) DEFAULT NULL,
  `good_img` varchar(255) DEFAULT NULL,
  `delivery_addr_id` bigint(20) DEFAULT NULL,
  `good_name` varchar(16) DEFAULT NULL,
  `good_count` int(11) DEFAULT NULL,
  `good_price` decimal(10,2) DEFAULT NULL,
  `seckill_price` decimal(10,2) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_seckill_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_goods`;
CREATE TABLE `t_seckill_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `good_id` bigint(20) DEFAULT NULL,
  `seckill_price` decimal(10,2) DEFAULT NULL,
  `stock_count` int(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seckill_goods
-- ----------------------------
INSERT INTO `t_seckill_goods` VALUES ('1', '1', '0.01', '10', '2019-02-26 23:51:22', '2019-02-26 23:52:24');
INSERT INTO `t_seckill_goods` VALUES ('2', '2', '0.01', '10', '2019-03-11 20:18:22', '2019-05-31 18:26:24');

-- ----------------------------
-- Table structure for t_seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `t_seckill_order`;
CREATE TABLE `t_seckill_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `order_no` varchar(50) DEFAULT NULL,
  `seckill_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seckill_order
-- ----------------------------
