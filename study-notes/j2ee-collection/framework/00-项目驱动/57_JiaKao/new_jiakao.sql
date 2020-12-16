/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : new_jiakao

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 16/12/2020 11:30:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_item
-- ----------------------------
DROP TABLE IF EXISTS `dict_item`;
CREATE TABLE `dict_item` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(15) NOT NULL DEFAULT '' COMMENT '值',
  `sn` smallint unsigned NOT NULL DEFAULT '0' COMMENT '排列顺序, 默认0. 值越大, 就排在越前面',
  `disabled` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否禁用. 0代表不禁用(启用), 1代表禁用',
  `type_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '所属的类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_item_name_type_id_uindex` (`name`,`type_id`),
  UNIQUE KEY `dict_item_value_type_id_uindex` (`value`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典条目';

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(15) NOT NULL DEFAULT '' COMMENT '值',
  `intro` varchar(100) NOT NULL DEFAULT '' COMMENT '简介',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type_name_uindex` (`name`),
  UNIQUE KEY `dict_type_value_uindex` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典类型';

SET FOREIGN_KEY_CHECKS = 1;
