/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : rbac

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-01-03 19:42:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '总经办2', 'General Deparment2');
INSERT INTO `department` VALUES ('2', '人力资源部', 'Human Resources Department');
INSERT INTO `department` VALUES ('3', '采购部', 'Order Department');
INSERT INTO `department` VALUES ('4', '仓储部', 'Warehousing Department');
INSERT INTO `department` VALUES ('6', '技术部', 'Technolog Department ');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', '1', 'admin@abc.com', '20', '', '2');
INSERT INTO `employee` VALUES ('2', '赵总1111', '1', 'zhaoz@wolfcode.cn', '35', '\0', '1');
INSERT INTO `employee` VALUES ('3', '赵一明', '1', 'zhaoym@wolfcode.cn', '25', '\0', '1');
INSERT INTO `employee` VALUES ('4', '钱总', '1', 'qianz@wolfcode.cn', '35', '\0', '2');
INSERT INTO `employee` VALUES ('5', '钱二明', '1', 'qianem@wolfcode.cn', '25', '\0', '2');
INSERT INTO `employee` VALUES ('6', '孙总', '1', 'sunz@wolfcode.cn', '35', '\0', '3');
INSERT INTO `employee` VALUES ('7', '孙三明', '1', 'sunsm@wolfcode.cn', '25', '\0', '3');
INSERT INTO `employee` VALUES ('9', '李四明', '1', 'lism@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('10', '周总', '1', 'zhouz@wolfcode.cn', '35', '\0', '5');
INSERT INTO `employee` VALUES ('11', '周五明', '1', 'zhouwm@wolfcode.cn', '25', '\0', '5');
INSERT INTO `employee` VALUES ('12', '吴总', '1', 'wuz@wolfcode.cn', '35', '\0', '6');
INSERT INTO `employee` VALUES ('13', '吴六明', '1', 'wulm@wolfcode.cn', '25', '\0', '6');
INSERT INTO `employee` VALUES ('14', '郑总', '1', 'zhengz@wolfcode.cn', '35', '\0', '7');
INSERT INTO `employee` VALUES ('15', '郑七明', '1', 'zhengqm@wolfcode.cn', '25', '\0', '7');
INSERT INTO `employee` VALUES ('16', '孙四明', '1', 'sunsim@wolfcode.cn', '25', '\0', '3');
INSERT INTO `employee` VALUES ('17', '孙五明2', '1', 'sunwm@wolfcode.cn', '25', '\0', '1');
INSERT INTO `employee` VALUES ('18', '李五明', '1', 'liwm@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('19', '李六明', '1', 'lilm@wolfcode.cn', '25', '\0', '4');
INSERT INTO `employee` VALUES ('24', '111', '1', '333', '1', '\0', null);
INSERT INTO `employee` VALUES ('25', '111', '1', '333', '2', '\0', null);

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES ('3', '12');
INSERT INTO `employee_role` VALUES ('5', '11');
INSERT INTO `employee_role` VALUES ('22', '11');
INSERT INTO `employee_role` VALUES ('22', '12');
INSERT INTO `employee_role` VALUES ('23', '3');
INSERT INTO `employee_role` VALUES ('23', '4');
INSERT INTO `employee_role` VALUES ('23', '11');
INSERT INTO `employee_role` VALUES ('23', '12');
INSERT INTO `employee_role` VALUES ('17', '1');
INSERT INTO `employee_role` VALUES ('17', '2');
INSERT INTO `employee_role` VALUES ('17', '3');
INSERT INTO `employee_role` VALUES ('17', '4');
INSERT INTO `employee_role` VALUES ('17', '11');
INSERT INTO `employee_role` VALUES ('17', '12');
INSERT INTO `employee_role` VALUES ('17', '13');
INSERT INTO `employee_role` VALUES ('2', '1');
INSERT INTO `employee_role` VALUES ('2', '11');
INSERT INTO `employee_role` VALUES ('2', '13');
INSERT INTO `employee_role` VALUES ('2', '3');
INSERT INTO `employee_role` VALUES ('2', '4');
INSERT INTO `employee_role` VALUES ('2', '12');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('4', '客户状态修改', 'customer:updateStatus');
INSERT INTO `permission` VALUES ('5', '潜在客户列表', 'customer:potentialList');
INSERT INTO `permission` VALUES ('6', '客户池列表', 'customer:poolList');
INSERT INTO `permission` VALUES ('7', '失败客户列表', 'customer:failList');
INSERT INTO `permission` VALUES ('8', '客户添加/更新', 'customer:saveOrUpdate');
INSERT INTO `permission` VALUES ('9', '客户报表列表', 'customerReport:list');
INSERT INTO `permission` VALUES ('10', '客户报表条形图', 'customerReport:chartByBar');
INSERT INTO `permission` VALUES ('11', '客户报表饼图', 'customerReport:chartByPie');
INSERT INTO `permission` VALUES ('12', '客户跟进历史列表', 'customerTraceHistory:list');
INSERT INTO `permission` VALUES ('13', '客户跟进历史添加/更新', 'customerTraceHistory:saveOrUpdate');
INSERT INTO `permission` VALUES ('14', '客户移交列表', 'customerTransfer:list');
INSERT INTO `permission` VALUES ('15', '客户吸纳', 'customerTransfer:absorb');
INSERT INTO `permission` VALUES ('16', '客户移交添加/更新', 'customerTransfer:saveOrUpdate');
INSERT INTO `permission` VALUES ('17', '部门列表', 'department:list');
INSERT INTO `permission` VALUES ('18', '部门编辑', 'department:input');
INSERT INTO `permission` VALUES ('19', '部门删除', 'department:delete');
INSERT INTO `permission` VALUES ('20', '部门添加/更新', 'department:saveOrUpdate');
INSERT INTO `permission` VALUES ('21', '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES ('22', '员工列表', 'employee:list');
INSERT INTO `permission` VALUES ('23', '员工编辑', 'employee:input');
INSERT INTO `permission` VALUES ('24', '员工导入', 'employee:importXls');
INSERT INTO `permission` VALUES ('25', '员工批量删除', 'employee:batchDelete');
INSERT INTO `permission` VALUES ('26', '员工导出', 'employee:exportXls');
INSERT INTO `permission` VALUES ('27', '员工添加/更新', 'employee:saveOrUpdate');
INSERT INTO `permission` VALUES ('28', '权限删除', 'permission:delete');
INSERT INTO `permission` VALUES ('29', '权限列表', 'permission:list');
INSERT INTO `permission` VALUES ('30', '权限加载', 'permission:reload');
INSERT INTO `permission` VALUES ('31', '角色删除', 'role:delete');
INSERT INTO `permission` VALUES ('32', '角色列表', 'role:list');
INSERT INTO `permission` VALUES ('33', '角色编辑', 'role:input');
INSERT INTO `permission` VALUES ('34', '角色添加/更新', 'role:saveOrUpdate');
INSERT INTO `permission` VALUES ('35', '数据字典字典列表', 'systemDictionary:list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '人事管理', 'HR_MGR');
INSERT INTO `role` VALUES ('2', '采购管理', 'ORDER_MGR');
INSERT INTO `role` VALUES ('3', '仓储管理', 'WAREHOUSING_MGR');
INSERT INTO `role` VALUES ('4', '行政部管理', 'Admin_MGR');
INSERT INTO `role` VALUES ('11', '市场经理', 'Market_Manager');
INSERT INTO `role` VALUES ('12', '市场专员', 'Market');
INSERT INTO `role` VALUES ('13', '2222222', '22222222');
INSERT INTO `role` VALUES ('14', '1231', '3132');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '5');
INSERT INTO `role_permission` VALUES ('1', '6');
INSERT INTO `role_permission` VALUES ('1', '7');
INSERT INTO `role_permission` VALUES ('1', '8');
INSERT INTO `role_permission` VALUES ('1', '11');
INSERT INTO `role_permission` VALUES ('14', '4');
INSERT INTO `role_permission` VALUES ('14', '5');
INSERT INTO `role_permission` VALUES ('14', '6');
INSERT INTO `role_permission` VALUES ('14', '7');
INSERT INTO `role_permission` VALUES ('14', '8');
INSERT INTO `role_permission` VALUES ('14', '9');
INSERT INTO `role_permission` VALUES ('14', '10');
INSERT INTO `role_permission` VALUES ('14', '11');
