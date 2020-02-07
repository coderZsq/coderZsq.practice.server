/*
MySQL Data Transfer
Source Host: localhost
Source Database: mysqltest
Target Host: localhost
Target Database: mysqltest
Date: 2015-3-12 20:41:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for productstock
-- ----------------------------
DROP TABLE IF EXISTS `productstock`;
CREATE TABLE `productstock` (
  `id` bigint(11) NOT NULL auto_increment,
  `product_id` bigint(11) default NULL,
  `storeNum` int(10) default NULL,
  `lastIncomeDate` datetime default NULL,
  `lastOutcomeDate` datetime default NULL,
  `warningNum` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `productstock` VALUES ('1', '1', '182', '2015-03-12 20:33:00', '2015-03-12 20:33:04', '20');
INSERT INTO `productstock` VALUES ('2', '2', '27', '2015-03-02 20:33:28', '2015-03-09 20:33:40', '20');
INSERT INTO `productstock` VALUES ('3', '3', '89', '2015-02-28 20:34:13', '2015-03-12 20:34:19', '20');
INSERT INTO `productstock` VALUES ('4', '5', '19', '2015-03-01 20:34:43', '2015-03-12 20:34:48', '20');
INSERT INTO `productstock` VALUES ('5', '6', '3', '2015-02-01 20:35:12', '2015-03-02 20:35:16', '5');
INSERT INTO `productstock` VALUES ('6', '7', '2', '2015-02-02 20:35:59', '2015-02-27 20:36:05', '3');
INSERT INTO `productstock` VALUES ('7', '8', '120', '2015-03-12 20:36:31', '2015-03-12 20:36:33', '20');
INSERT INTO `productstock` VALUES ('8', '9', '58', '2015-03-02 20:36:50', '2015-03-12 20:36:53', '20');
INSERT INTO `productstock` VALUES ('9', '11', '28', '2015-03-02 20:37:12', '2015-03-12 20:37:15', '20');
INSERT INTO `productstock` VALUES ('10', '12', '8', '2015-03-02 20:37:35', '2015-03-09 20:37:38', '5');
INSERT INTO `productstock` VALUES ('11', '13', '3', '2015-03-02 20:37:58', '2015-03-12 20:38:01', '5');
INSERT INTO `productstock` VALUES ('12', '14', '6', '2015-03-02 20:38:20', '2015-03-07 20:38:23', '5');
INSERT INTO `productstock` VALUES ('13', '15', '2', '2015-02-02 20:38:38', '2015-02-24 20:38:44', '5');
INSERT INTO `productstock` VALUES ('14', '16', '3', '2015-02-02 20:39:05', '2015-02-06 20:39:09', '3');
INSERT INTO `productstock` VALUES ('15', '17', '49', '2015-03-02 20:39:36', '2015-03-12 20:39:40', '20');
INSERT INTO `productstock` VALUES ('16', '18', '14', '2015-03-02 20:39:57', '2015-03-09 20:40:01', '10');
INSERT INTO `productstock` VALUES ('17', '20', '7', '2015-03-02 20:40:22', '2015-03-03 20:40:25', '5');
