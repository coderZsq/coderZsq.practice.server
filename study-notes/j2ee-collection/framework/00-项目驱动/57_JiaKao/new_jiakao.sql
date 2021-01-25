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

 Date: 25/01/2021 23:15:06
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='数据字典条目';

-- ----------------------------
-- Records of dict_item
-- ----------------------------
BEGIN;
INSERT INTO `dict_item` VALUES (2, '教师', 'teacher', 0, 1, 13);
INSERT INTO `dict_item` VALUES (3, '司机', 'driver', 6, 0, 13);
INSERT INTO `dict_item` VALUES (4, '教练', 'coach', 1, 1, 14);
INSERT INTO `dict_item` VALUES (5, '学员', 'student', 666, 0, 14);
INSERT INTO `dict_item` VALUES (7, '课程合集', '0', 0, 0, 110);
INSERT INTO `dict_item` VALUES (8, '科目2', '2', 0, 0, 110);
INSERT INTO `dict_item` VALUES (9, '科目3', '3', 0, 0, 110);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='数据字典类型';

-- ----------------------------
-- Records of dict_type
-- ----------------------------
BEGIN;
INSERT INTO `dict_type` VALUES (13, '职业', 'job', '一份工作');
INSERT INTO `dict_type` VALUES (14, '用户类型', 'user_type', '12');
INSERT INTO `dict_type` VALUES (15, '哈哈13', 'haha13', '13');
INSERT INTO `dict_type` VALUES (16, '哈哈14', 'haha14', '14');
INSERT INTO `dict_type` VALUES (17, '哈哈15', 'haha15', '15');
INSERT INTO `dict_type` VALUES (19, '哈哈17', 'haha17', '17');
INSERT INTO `dict_type` VALUES (20, '哈哈18', 'haha18', '18');
INSERT INTO `dict_type` VALUES (21, '哈哈19', 'haha19', '19');
INSERT INTO `dict_type` VALUES (22, '哈哈20', 'haha20', '20');
INSERT INTO `dict_type` VALUES (23, '哈哈21', 'haha21', '21');
INSERT INTO `dict_type` VALUES (24, '哈哈22', 'haha22', '22');
INSERT INTO `dict_type` VALUES (25, '哈哈23', 'haha23', '23');
INSERT INTO `dict_type` VALUES (26, '哈哈24', 'haha24', '24');
INSERT INTO `dict_type` VALUES (27, '哈哈25', 'haha25', '25');
INSERT INTO `dict_type` VALUES (28, '哈哈26', 'haha26', '26');
INSERT INTO `dict_type` VALUES (29, '哈哈27', 'haha27', '27');
INSERT INTO `dict_type` VALUES (30, '哈哈28', 'haha28', '28');
INSERT INTO `dict_type` VALUES (31, '哈哈29', 'haha29', '29');
INSERT INTO `dict_type` VALUES (32, '哈哈30', 'haha30', '30');
INSERT INTO `dict_type` VALUES (33, '哈哈31', 'haha31', '31');
INSERT INTO `dict_type` VALUES (34, '哈哈32', 'haha32', '32');
INSERT INTO `dict_type` VALUES (35, '哈哈33', 'haha33', '33');
INSERT INTO `dict_type` VALUES (36, '哈哈34', 'haha34', '34');
INSERT INTO `dict_type` VALUES (37, '哈哈35', 'haha35', '35');
INSERT INTO `dict_type` VALUES (38, '哈哈36', 'haha36', '36');
INSERT INTO `dict_type` VALUES (39, '哈哈37', 'haha37', '37');
INSERT INTO `dict_type` VALUES (40, '哈哈38', 'haha38', '38');
INSERT INTO `dict_type` VALUES (41, '哈哈39', 'haha39', '39');
INSERT INTO `dict_type` VALUES (42, '哈哈40', 'haha40', '40');
INSERT INTO `dict_type` VALUES (43, '哈哈41', 'haha41', '41');
INSERT INTO `dict_type` VALUES (44, '哈哈42', 'haha42', '42');
INSERT INTO `dict_type` VALUES (45, '哈哈43', 'haha43', '43');
INSERT INTO `dict_type` VALUES (46, '哈哈44', 'haha44', '44');
INSERT INTO `dict_type` VALUES (47, '哈哈45', 'haha45', '45');
INSERT INTO `dict_type` VALUES (48, '哈哈46', 'haha46', '46');
INSERT INTO `dict_type` VALUES (49, '哈哈47', 'haha47', '47');
INSERT INTO `dict_type` VALUES (50, '哈哈48', 'haha48', '48');
INSERT INTO `dict_type` VALUES (51, '哈哈49', 'haha49', '49');
INSERT INTO `dict_type` VALUES (52, '哈哈50', 'haha50', '50');
INSERT INTO `dict_type` VALUES (53, '哈哈51', 'haha51', '51');
INSERT INTO `dict_type` VALUES (54, '哈哈52', 'haha52', '52');
INSERT INTO `dict_type` VALUES (55, '哈哈53', 'haha53', '53');
INSERT INTO `dict_type` VALUES (56, '哈哈54', 'haha54', '54');
INSERT INTO `dict_type` VALUES (57, '哈哈55', 'haha55', '55');
INSERT INTO `dict_type` VALUES (58, '哈哈56', 'haha56', '56');
INSERT INTO `dict_type` VALUES (59, '哈哈57', 'haha57', '57');
INSERT INTO `dict_type` VALUES (60, '哈哈58', 'haha58', '58');
INSERT INTO `dict_type` VALUES (61, '哈哈59', 'haha59', '59');
INSERT INTO `dict_type` VALUES (62, '哈哈60', 'haha60', '60');
INSERT INTO `dict_type` VALUES (63, '哈哈61', 'haha61', '61');
INSERT INTO `dict_type` VALUES (64, '哈哈62', 'haha62', '62');
INSERT INTO `dict_type` VALUES (65, '哈哈63', 'haha63', '63');
INSERT INTO `dict_type` VALUES (66, '哈哈64', 'haha64', '64');
INSERT INTO `dict_type` VALUES (67, '哈哈65', 'haha65', '65');
INSERT INTO `dict_type` VALUES (68, '哈哈66', 'haha66', '66');
INSERT INTO `dict_type` VALUES (69, '哈哈67', 'haha67', '67');
INSERT INTO `dict_type` VALUES (70, '哈哈68', 'haha68', '68');
INSERT INTO `dict_type` VALUES (71, '哈哈69', 'haha69', '69');
INSERT INTO `dict_type` VALUES (72, '哈哈70', 'haha70', '70');
INSERT INTO `dict_type` VALUES (73, '哈哈71', 'haha71', '71');
INSERT INTO `dict_type` VALUES (74, '哈哈72', 'haha72', '72');
INSERT INTO `dict_type` VALUES (75, '哈哈73', 'haha73', '73');
INSERT INTO `dict_type` VALUES (76, '哈哈74', 'haha74', '74');
INSERT INTO `dict_type` VALUES (77, '哈哈75', 'haha75', '75');
INSERT INTO `dict_type` VALUES (78, '哈哈76', 'haha76', '76');
INSERT INTO `dict_type` VALUES (79, '哈哈77', 'haha77', '77');
INSERT INTO `dict_type` VALUES (80, '哈哈78', 'haha78', '78');
INSERT INTO `dict_type` VALUES (81, '哈哈79', 'haha79', '79');
INSERT INTO `dict_type` VALUES (82, '哈哈80', 'haha80', '80');
INSERT INTO `dict_type` VALUES (83, '哈哈81', 'haha81', '81');
INSERT INTO `dict_type` VALUES (84, '哈哈82', 'haha82', '82');
INSERT INTO `dict_type` VALUES (85, '哈哈83', 'haha83', '83');
INSERT INTO `dict_type` VALUES (86, '哈哈84', 'haha84', '84');
INSERT INTO `dict_type` VALUES (87, '哈哈85', 'haha85', '85');
INSERT INTO `dict_type` VALUES (88, '哈哈86', 'haha86', '86');
INSERT INTO `dict_type` VALUES (89, '哈哈87', 'haha87', '87');
INSERT INTO `dict_type` VALUES (90, '哈哈88', 'haha88', '88');
INSERT INTO `dict_type` VALUES (91, '哈哈89', 'haha89', '89');
INSERT INTO `dict_type` VALUES (92, '哈哈90', 'haha90', '90');
INSERT INTO `dict_type` VALUES (93, '哈哈91', 'haha91', '91');
INSERT INTO `dict_type` VALUES (94, '哈哈92', 'haha92', '92');
INSERT INTO `dict_type` VALUES (95, '哈哈93', 'haha93', '93');
INSERT INTO `dict_type` VALUES (96, '哈哈94', 'haha94', '94');
INSERT INTO `dict_type` VALUES (97, '哈哈95', 'haha95', '95');
INSERT INTO `dict_type` VALUES (98, '哈哈96', 'haha96', '96');
INSERT INTO `dict_type` VALUES (99, '哈哈97', 'haha97', '97');
INSERT INTO `dict_type` VALUES (100, '哈哈98', 'haha98', '98');
INSERT INTO `dict_type` VALUES (101, '哈哈99', 'haha99', '99');
INSERT INTO `dict_type` VALUES (110, '科2科3课程类型', 'course_type', '科2科3课程类型科2科3课程类型科2科3课程类型科2科3课程类型科2科3课程类型');
COMMIT;

-- ----------------------------
-- Table structure for exam_place
-- ----------------------------
DROP TABLE IF EXISTS `exam_place`;
CREATE TABLE `exam_place` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '名称',
  `province_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '考场是哪个省份的',
  `city_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '考场是哪个城市的',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '考场的具体地址',
  `latitude` decimal(10,7) NOT NULL DEFAULT '0.0000000' COMMENT '纬度',
  `longitude` decimal(10,7) NOT NULL DEFAULT '0.0000000' COMMENT '经度',
  PRIMARY KEY (`id`),
  UNIQUE KEY `exam_place_city_id_name_uindex` (`city_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='考场';

-- ----------------------------
-- Records of exam_place
-- ----------------------------
BEGIN;
INSERT INTO `exam_place` VALUES (1, '梅县考场', 3, 5, '广东省梅州市梅县', 0.0000000, 0.0000000);
INSERT INTO `exam_place` VALUES (2, '电瓶车考场', 6, 8, '很多电瓶车的考场2', 2.0000001, 1.0000001);
INSERT INTO `exam_place` VALUES (3, '天河考场', 3, 5, '', 0.0000000, 0.0000000);
COMMIT;

-- ----------------------------
-- Table structure for exam_place_course
-- ----------------------------
DROP TABLE IF EXISTS `exam_place_course`;
CREATE TABLE `exam_place_course` (
  `id` mediumint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '课程类型: 0是课程类型, 2是科目2, 3是科目3',
  `intro` varchar(100) NOT NULL DEFAULT '',
  `video` varchar(100) NOT NULL DEFAULT '' COMMENT '视频',
  `cover` varchar(100) NOT NULL DEFAULT '' COMMENT '封面',
  `place_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '考场',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='考场课程';

-- ----------------------------
-- Records of exam_place_course
-- ----------------------------
BEGIN;
INSERT INTO `exam_place_course` VALUES (1, '2021-01-13 14:57:54', '倒车入库', 99.99, 0, '这是一门比较难的课程', '', '', 2);
INSERT INTO `exam_place_course` VALUES (2, '2021-01-13 15:03:44', '直角转弯', 88.88, 3, '这门很简单', '', '', 3);
COMMIT;

-- ----------------------------
-- Table structure for plate_region
-- ----------------------------
DROP TABLE IF EXISTS `plate_region`;
CREATE TABLE `plate_region` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '名称',
  `plate` char(1) NOT NULL DEFAULT '' COMMENT '车牌',
  `pinyin` varchar(50) DEFAULT '' COMMENT '拼音',
  `parent_id` smallint unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `plate_region_parent_id_name_uindex` (`parent_id`,`name`),
  KEY `plate_region_parent_id_plate_index` (`parent_id`,`plate`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='带有车牌的区域';

-- ----------------------------
-- Records of plate_region
-- ----------------------------
BEGIN;
INSERT INTO `plate_region` VALUES (3, '广东', '粤', 'GUANG_DONG', 0);
INSERT INTO `plate_region` VALUES (4, '福建', '闽', 'FU_JIAN', 0);
INSERT INTO `plate_region` VALUES (5, '广州', 'A', 'GUANG_ZHOU', 3);
INSERT INTO `plate_region` VALUES (6, '四川', '川', 'SI_CHUAN', 0);
INSERT INTO `plate_region` VALUES (7, '贵州', '贵', 'GUI_ZHOU', 0);
INSERT INTO `plate_region` VALUES (8, '成都', 'A', 'CHENG_DOU', 6);
INSERT INTO `plate_region` VALUES (9, '广西', '桂', 'GUANG_XI', 0);
INSERT INTO `plate_region` VALUES (10, '汕头', 'B', 'SHAN_TOU', 3);
INSERT INTO `plate_region` VALUES (11, '茂名', 'K', 'MAO_MING', 3);
COMMIT;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '名称',
  `uri` varchar(100) NOT NULL DEFAULT '' COMMENT '链接地址',
  `permission` varchar(100) NOT NULL DEFAULT '' COMMENT '权限标识',
  `type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '资源类型 (0是目录, 1是菜单, 2是按钮)',
  `icon` varchar(100) NOT NULL DEFAULT '' COMMENT '图标',
  `sn` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '序号',
  `parent_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '父资源id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_resource_parent_id_name_uindex` (`parent_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(15) NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_name_uindex` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `resource_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '资源id',
  PRIMARY KEY (`resource_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-资源';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(15) NOT NULL DEFAULT '' COMMENT '昵称',
  `username` varchar(15) NOT NULL DEFAULT '' COMMENT '登录用的用户名',
  `password` char(32) NOT NULL DEFAULT '' COMMENT '登录用的密码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后一次登录的时间',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '账号的状态, 0是正常, 1是锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_user_username_uindex` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户 (可以登录后台系统的)';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_id` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
