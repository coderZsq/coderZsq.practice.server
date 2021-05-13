/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : im_demo

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 07/05/2021 14:07:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) DEFAULT NULL COMMENT '帐号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `disablestate` int(11) DEFAULT NULL COMMENT '禁用状态（0 启用  1 禁用）',
  `isdel` int(11) DEFAULT NULL COMMENT '是否删除（0未删除1已删除）',
  `createdate` datetime DEFAULT NULL COMMENT '创建日期',
  `updatedate` datetime DEFAULT NULL COMMENT '修改日期',
  `updateuser` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户帐号';;

-- ----------------------------
-- Records of user_account
-- ----------------------------
BEGIN;
INSERT INTO `user_account` VALUES (3, '1', '1', 0, 0, '2021-04-27 15:08:37', '2021-04-27 15:08:37', NULL);
INSERT INTO `user_account` VALUES (4, '2', '2', 0, 0, '2021-04-27 18:00:14', '2021-04-27 18:00:14', NULL);
INSERT INTO `user_account` VALUES (5, '3', '3', 0, 0, '2021-04-27 18:06:20', '2021-04-27 18:06:20', NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `deptid` bigint(11) DEFAULT NULL COMMENT '部门',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` int(11) DEFAULT NULL COMMENT '性别（0女 1男）',
  `birthday` varchar(50) DEFAULT NULL COMMENT '生日',
  `cardid` varchar(20) DEFAULT NULL COMMENT '身份证',
  `signature` varchar(20) DEFAULT NULL COMMENT '签名',
  `school` varchar(50) DEFAULT NULL COMMENT '毕业院校',
  `education` int(11) DEFAULT NULL COMMENT '学历',
  `address` varchar(200) DEFAULT NULL COMMENT '现居住地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `remark` text COMMENT '备注',
  `profilephoto` varchar(256) DEFAULT NULL COMMENT '个人头像',
  `createdate` datetime DEFAULT NULL COMMENT '创建时间',
  `createuser` bigint(20) DEFAULT NULL COMMENT '创建人',
  `updatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `updateuser` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, 3, 1, '彭于晏', NULL, NULL, NULL, NULL, '帅还用说吗?', NULL, NULL, NULL, NULL, NULL, NULL, '/static/images/1.jpg', '2021-04-27 15:08:41', 3, '2021-04-27 15:08:41', 3);
INSERT INTO `user_info` VALUES (2, 4, 4, '包工头', NULL, NULL, NULL, NULL, '勤劳的打工人..', NULL, NULL, NULL, NULL, NULL, NULL, '/static/images/2.jpg', '2021-04-27 18:00:14', 4, '2021-04-27 18:00:14', 4);
INSERT INTO `user_info` VALUES (3, 5, 4, '大娃', NULL, NULL, NULL, NULL, '呔！蛇精哪里跑！！', NULL, NULL, NULL, NULL, NULL, NULL, '/static/images/3.jpg', '2021-04-27 18:06:20', 5, '2021-04-27 18:06:20', 5);
COMMIT;

-- ----------------------------
-- Table structure for user_message
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senduser` varchar(100) DEFAULT NULL COMMENT '发送人',
  `receiveuser` varchar(100) DEFAULT NULL COMMENT '接收人',
  `groupid` varchar(100) DEFAULT NULL COMMENT '群ID',
  `isread` int(11) DEFAULT NULL COMMENT '是否已读 0 未读  1 已读',
  `type` int(11) DEFAULT NULL COMMENT '类型 0 单聊消息  1 群消息',
  `content` text COMMENT '消息内容',
  `uuid` varchar(255) DEFAULT NULL COMMENT '唯一值',
  `createdate` datetime DEFAULT NULL,
  `updatedate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;;

SET FOREIGN_KEY_CHECKS = 1;
