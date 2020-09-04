/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : resume

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 04/09/2020 11:29:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resume_articles
-- ----------------------------
DROP TABLE IF EXISTS `resume_articles`;
CREATE TABLE `resume_articles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column_id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `href` varchar(500) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_articles
-- ----------------------------
BEGIN;
INSERT INTO `resume_articles` VALUES (1, 1, 'Hybird 搭建零耦合架构从MVC开始', 'http://www.jianshu.com/p/5a03995a6ce1');
INSERT INTO `resume_articles` VALUES (3, 4, 'iOS 通俗易懂的HTTP网络', 'http://www.jianshu.com/p/f1aadc9f5dc3');
INSERT INTO `resume_articles` VALUES (4, 4, 'iOS 核心动画的应用及内存泄漏', 'http://www.jianshu.com/p/733a006a3da1');
INSERT INTO `resume_articles` VALUES (5, 1, 'Hybird 搭建后端Koa.js并过度到MVVM', 'http://www.jianshu.com/p/846b9f181cb7');
INSERT INTO `resume_articles` VALUES (6, 1, 'Hybird 搭建前端Vue.js并升级至MVP', 'http://www.jianshu.com/p/8d4a84e3ddaa');
INSERT INTO `resume_articles` VALUES (7, 1, 'Hybird 搭建路由Router实现组件化', 'http://www.jianshu.com/p/36314d0c0032');
INSERT INTO `resume_articles` VALUES (8, 1, 'Hybird 搭建客户端实时降级架构', 'http://www.jianshu.com/p/7054a694cfeb');
INSERT INTO `resume_articles` VALUES (9, 1, 'iOS 执行.py脚本生成解耦架构', 'http://www.jianshu.com/p/47d565bf200e');
INSERT INTO `resume_articles` VALUES (10, 1, 'iOS 执行.py脚本生成UI层结构', 'http://www.jianshu.com/p/d15379908582');
INSERT INTO `resume_articles` VALUES (11, 1, 'iOS 移动端面向文档开发', 'http://www.jianshu.com/p/b35d06cf189a');
INSERT INTO `resume_articles` VALUES (12, 1, 'iOS 移动端生成工具开发', 'http://www.jianshu.com/p/cb36b36f90dd');
INSERT INTO `resume_articles` VALUES (13, 2, 'iOS 做好开工前的准备', 'http://www.jianshu.com/p/a3e1b54c73d6');
INSERT INTO `resume_articles` VALUES (14, 2, 'iOS 集成Reveal UI调试利器', 'http://www.jianshu.com/p/861c9c916b2a');
INSERT INTO `resume_articles` VALUES (15, 2, 'UI/UX 产品原型 从Axure开始', 'http://www.jianshu.com/p/440bdc425c02');
INSERT INTO `resume_articles` VALUES (16, 2, 'UI/UX 使用Ps 进行视觉设计', 'http://www.jianshu.com/p/56eb4917f956');
INSERT INTO `resume_articles` VALUES (17, 2, 'iOS 投机流实现 无限轮播图', 'http://www.jianshu.com/p/e42db267d5f1');
INSERT INTO `resume_articles` VALUES (18, 2, 'iOS 会跳舞的TabbarController', 'http://www.jianshu.com/p/c1a0cd2a348f');
INSERT INTO `resume_articles` VALUES (19, 2, 'iOS 超Easy实现 渐变导航栏', 'http://www.jianshu.com/p/bba27212de69');
INSERT INTO `resume_articles` VALUES (20, 2, 'iOS 狂霸酷炫拽之Button动效', 'http://www.jianshu.com/p/6106f5a08ec3');
INSERT INTO `resume_articles` VALUES (21, 2, 'iOS 5行代码搞定全屏Pop转场', 'http://www.jianshu.com/p/992cb9f01eb3');
INSERT INTO `resume_articles` VALUES (22, 3, 'Web 是时候用前端写个简历了!', 'http://www.jianshu.com/p/d1497da0f9ab');
INSERT INTO `resume_articles` VALUES (23, 3, 'Web 前端项目要从基本布局开始', 'http://www.jianshu.com/p/5c4788c0389d');
INSERT INTO `resume_articles` VALUES (24, 3, 'Web 简历一定要设计的美美的', 'http://www.jianshu.com/p/b3389f66f539');
INSERT INTO `resume_articles` VALUES (25, 3, 'Web 前端使用Vue代替JQuery', 'http://www.jianshu.com/p/85d95723edfb');
INSERT INTO `resume_articles` VALUES (26, 3, 'Web Vue项目速转.htm静态网页', 'http://www.jianshu.com/p/f1fd09628b43');
INSERT INTO `resume_articles` VALUES (27, 3, 'Web 使用gulp构建前端项目', 'http://www.jianshu.com/p/ff54340f05a3');
INSERT INTO `resume_articles` VALUES (28, 3, 'Web 使用webpack构建前端项目', 'http://www.jianshu.com/p/ab873869b3dd');
INSERT INTO `resume_articles` VALUES (29, 3, 'Web 将项目快速迁移至React', 'http://www.jianshu.com/p/1803bfac1527');
INSERT INTO `resume_articles` VALUES (30, 3, 'Web PC项目快速适配移动端', 'http://www.jianshu.com/p/404ddf018a84');
INSERT INTO `resume_articles` VALUES (31, 3, 'Server 入门后端你要学什么', 'http://www.jianshu.com/p/ec78451ed312');
INSERT INTO `resume_articles` VALUES (32, 3, 'Server 使用Spring来构建服务', 'http://www.jianshu.com/p/ffc5fa6a376a');
INSERT INTO `resume_articles` VALUES (33, 4, '[SceneKit] 不会 Unity3D 的另一种选择', 'http://www.jianshu.com/p/f54eb416f8f1');
INSERT INTO `resume_articles` VALUES (34, 4, '[CoreData] SQL写烦了? 试试亲儿子!', 'http://www.jianshu.com/p/859b4d29e7c0');
INSERT INTO `resume_articles` VALUES (35, 4, '[SpriteKit] 瓦片地图什么的还挺好玩儿', 'http://www.jianshu.com/p/da0f9c0cdcff');
INSERT INTO `resume_articles` VALUES (36, 4, '[SpriteKit] 通过检测掩码进行物理识别', 'http://www.jianshu.com/p/5b4e09037337');
INSERT INTO `resume_articles` VALUES (37, 4, '[SpriteKit] 系统框架中Cocos2d-x的怨念', 'http://www.jianshu.com/p/09bb44d46080');
INSERT INTO `resume_articles` VALUES (38, 4, '[Animations] 快速上手 iOS10 属性动画', 'http://www.jianshu.com/p/0e24330302f5');
INSERT INTO `resume_articles` VALUES (39, 4, '[Animations] 自定义转场现已加入豪华午餐', 'http://www.jianshu.com/p/732e4c9b410a');
INSERT INTO `resume_articles` VALUES (40, 4, '[Animations] 核心动画什么的要研究透!', 'http://www.jianshu.com/p/2802dedb587d');
INSERT INTO `resume_articles` VALUES (41, 4, '[Animations] 你真的会用View的动画吗?', 'http://www.jianshu.com/p/85877d2ddcb8');
INSERT INTO `resume_articles` VALUES (42, 4, '[RxSwift] 函数式组合运算符实操', 'http://www.jianshu.com/p/71c815f1d4de');
INSERT INTO `resume_articles` VALUES (43, 4, '[RxSwift] 函数式映射运算符实操', 'http://www.jianshu.com/p/6b80a0db56bd');
INSERT INTO `resume_articles` VALUES (44, 4, '[RxSwift] 函数式过滤运算符实操', 'http://www.jianshu.com/p/04349d324a6f');
INSERT INTO `resume_articles` VALUES (45, 4, '[RxSwift] 大神们都在看的响应式', 'http://www.jianshu.com/p/79010cca3b9c');
INSERT INTO `resume_articles` VALUES (46, 4, 'Hybird 说说与Web交互的那些事儿', 'http://www.jianshu.com/p/555786f35357');
COMMIT;

-- ----------------------------
-- Table structure for resume_column
-- ----------------------------
DROP TABLE IF EXISTS `resume_column`;
CREATE TABLE `resume_column` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_column
-- ----------------------------
BEGIN;
INSERT INTO `resume_column` VALUES (1, 'SQTemplate Column');
INSERT INTO `resume_column` VALUES (2, 'SQExtension Column');
INSERT INTO `resume_column` VALUES (3, 'Web Column');
INSERT INTO `resume_column` VALUES (4, 'iOS Column');
COMMIT;

-- ----------------------------
-- Table structure for resume_contact
-- ----------------------------
DROP TABLE IF EXISTS `resume_contact`;
CREATE TABLE `resume_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wechat_url` varchar(500) COLLATE utf8_bin NOT NULL,
  `wechat` varchar(100) COLLATE utf8_bin NOT NULL,
  `mobile` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `qq` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_contact
-- ----------------------------
BEGIN;
INSERT INTO `resume_contact` VALUES (1, 'http://upload-images.jianshu.io/upload_images/1229762-453920a3f4eedcd8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'Aquas008', '+86 13701777868', 'a13701777868@sina.com / a13701777868@gmail.com', '120709684');
COMMIT;

-- ----------------------------
-- Table structure for resume_experience
-- ----------------------------
DROP TABLE IF EXISTS `resume_experience`;
CREATE TABLE `resume_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(100) COLLATE utf8_bin NOT NULL,
  `job` varchar(100) COLLATE utf8_bin NOT NULL,
  `corp` varchar(100) COLLATE utf8_bin NOT NULL,
  `href` varchar(100) COLLATE utf8_bin NOT NULL,
  `desc1` varchar(100) COLLATE utf8_bin NOT NULL,
  `desc2` varchar(100) COLLATE utf8_bin NOT NULL,
  `desc3` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_experience
-- ----------------------------
BEGIN;
INSERT INTO `resume_experience` VALUES (1, 'Jul. 2015 ~ Now', 'Software Engineer', 'J1 Corp.', 'https://www.j1.com', 'iOS application development', 'Web application development', 'New employee skill training & judgement');
INSERT INTO `resume_experience` VALUES (2, 'Aug. 2013 ~ Feb. 2015', 'Freight Operator', 'TVL Group', 'http://www.tvlgroups.com/En/ugC_AboutUs.asp', 'Freight export operation', 'Freight import operation', 'Freight sales connection & quotation');
COMMIT;

-- ----------------------------
-- Table structure for resume_github
-- ----------------------------
DROP TABLE IF EXISTS `resume_github`;
CREATE TABLE `resume_github` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `description` varchar(500) COLLATE utf8_bin NOT NULL,
  `href` varchar(500) COLLATE utf8_bin NOT NULL,
  `color` varchar(50) COLLATE utf8_bin NOT NULL,
  `language` varchar(50) COLLATE utf8_bin NOT NULL,
  `star` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_github
-- ----------------------------
BEGIN;
INSERT INTO `resume_github` VALUES (1, 'coderZsq.project.oc', 'A lightweight and efficient application development tool set for iOS, and accelerating the developing speed.', 'https://github.com/coderZsq/coderZsq.project.oc', '#438eff', 'Objective-C', '142');
INSERT INTO `resume_github` VALUES (2, 'coderZsq.target.swift', 'For advanced Swift development technology pre-research & ideas & practice. ( Focus areas: App )', 'https://github.com/coderZsq/coderZsq.target.swift', '#ffac45', 'Swift', '9');
INSERT INTO `resume_github` VALUES (3, 'coderZsq.webpack.js', 'For advanced JavaScript development technology pre-research & ideas & practice. ( Focus areas: Web )', 'https://github.com/coderZsq/coderZsq.display.js', '#EFDF70', 'JavaScript', '2');
INSERT INTO `resume_github` VALUES (4, 'coderZsq.maven.java', 'For advanced Java development technology pre-research & ideas & practice. ( Focus areas: Server )', 'https://github.com/coderZsq/coderZsq.maven.java', '#A8732F', 'Java', '0');
COMMIT;

-- ----------------------------
-- Table structure for resume_profile
-- ----------------------------
DROP TABLE IF EXISTS `resume_profile`;
CREATE TABLE `resume_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_image` varchar(500) COLLATE utf8_bin NOT NULL,
  `profile_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile_career` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile_summary_title` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile_summary_description` varchar(500) COLLATE utf8_bin NOT NULL,
  `profile_interest_title` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile_education_title` varchar(50) COLLATE utf8_bin NOT NULL,
  `profile_location` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_profile
-- ----------------------------
BEGIN;
INSERT INTO `resume_profile` VALUES (1, 'https://avatars2.githubusercontent.com/u/19483268?s=400', 'Shuangquan Zhu', 'Designer / Developer', 'Summary', 'Shuangquan Zhu is a professional developer who focuses on iOS now. He has strong knowledge of Objective-C, Swift and Javascript. With these skills, he created quite a few quickly developer tools. He also leads the J1 iOS team to promote the project process.\\nHe crazy loves playing basketball with friends in spare time, He also loves traveling, writing and listening music. He is always willing to try new things, and keeping to learn from them.', 'Interest', 'Education', 'Shanghai');
COMMIT;

-- ----------------------------
-- Table structure for resume_profile_education
-- ----------------------------
DROP TABLE IF EXISTS `resume_profile_education`;
CREATE TABLE `resume_profile_education` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `major` varchar(100) COLLATE utf8_bin NOT NULL,
  `school` varchar(100) COLLATE utf8_bin NOT NULL,
  `year` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_profile_education
-- ----------------------------
BEGIN;
INSERT INTO `resume_profile_education` VALUES (1, 'Business Management', 'East China University of Science and Technology', '2016');
INSERT INTO `resume_profile_education` VALUES (2, 'Customs and International Freight', 'Shanghai Maritime Academy', '2013');
COMMIT;

-- ----------------------------
-- Table structure for resume_profile_interest
-- ----------------------------
DROP TABLE IF EXISTS `resume_profile_interest`;
CREATE TABLE `resume_profile_interest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `interest` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_profile_interest
-- ----------------------------
BEGIN;
INSERT INTO `resume_profile_interest` VALUES (1, 'Learn about high tech');
INSERT INTO `resume_profile_interest` VALUES (2, 'Play basketball');
INSERT INTO `resume_profile_interest` VALUES (3, 'Listen to music');
INSERT INTO `resume_profile_interest` VALUES (4, 'Apple products');
COMMIT;

-- ----------------------------
-- Table structure for resume_profile_social
-- ----------------------------
DROP TABLE IF EXISTS `resume_profile_social`;
CREATE TABLE `resume_profile_social` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src` varchar(500) COLLATE utf8_bin NOT NULL,
  `href` varchar(500) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_profile_social
-- ----------------------------
BEGIN;
INSERT INTO `resume_profile_social` VALUES (1, 'http://upload-images.jianshu.io/upload_images/1229762-877e3e5c2260bcf1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://github.com/coderZsq');
INSERT INTO `resume_profile_social` VALUES (2, 'http://upload-images.jianshu.io/upload_images/1229762-f6525252f3e8387b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'http://www.jianshu.com/u/9d7fad1a4693');
INSERT INTO `resume_profile_social` VALUES (3, 'http://upload-images.jianshu.io/upload_images/1229762-a69614a97de93f36.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'http://upload-images.jianshu.io/upload_images/1229762-453920a3f4eedcd8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240');
INSERT INTO `resume_profile_social` VALUES (12, 'http://upload-images.jianshu.io/upload_images/1229762-91b0949aec719aab.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'http://coderzsq.github.io/');
COMMIT;

-- ----------------------------
-- Table structure for resume_projects
-- ----------------------------
DROP TABLE IF EXISTS `resume_projects`;
CREATE TABLE `resume_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src` varchar(500) COLLATE utf8_bin NOT NULL,
  `href` varchar(500) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `description` varchar(100) COLLATE utf8_bin NOT NULL,
  `text1` varchar(100) COLLATE utf8_bin NOT NULL,
  `text2` varchar(100) COLLATE utf8_bin NOT NULL,
  `text3` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resume_projects
-- ----------------------------
BEGIN;
INSERT INTO `resume_projects` VALUES (1, 'http://upload-images.jianshu.io/upload_images/1229762-e41470fb14d50dd0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://itunes.apple.com/us/app/%E5%81%A5%E4%B8%80%E7%BD%91-%E5%8D%8E%E6%B6%A6%E6%97%97%E4%B8%8B%E7%BD%91%E4%B8%8A%E8%8D%AF%E5%BA%97/id910027998?mt=8', 'J1 Mall', 'China Resources\'s online pharmacy', 'Developed by J1 iOS team', 'Developing version 4.0.1', 'Enterprise main project');
INSERT INTO `resume_projects` VALUES (2, 'http://upload-images.jianshu.io/upload_images/1229762-895d58180c1893e9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://itunes.apple.com/us/app/%E5%81%A5%E4%B8%80%E5%81%A5%E5%BA%B7/id994784918?mt=8', 'J1 Health', 'Professional patient consultation platform', 'Developed by J1 iOS team', 'Developing version 3.1.0', 'Enterprise self-operated project');
INSERT INTO `resume_projects` VALUES (3, 'http://upload-images.jianshu.io/upload_images/1229762-2aa0ac79354150f4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://itunes.apple.com/cn/app/hua-run-tong/id1175972491?mt=8', 'HuaRun Tong', 'China Resources Integrated business platform', 'Developed by J1 & CR Network', 'Developing version 1.7.3', 'Cooperation project with CR Network');
INSERT INTO `resume_projects` VALUES (4, 'http://upload-images.jianshu.io/upload_images/1229762-f819fb27af63f404.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240', 'https://itunes.apple.com/us/app/%E6%B9%96%E5%B7%9E%E4%B8%AD%E5%BF%83%E5%8C%BB%E9%99%A2-%E4%B8%93%E4%B8%9A%E7%89%88/id1098112284?mt=8', 'HuZhou Hospital', 'Simplify patient treatment and time', 'Developed by J1 iOS team', 'Developing version 1.0.0', 'Outsourcing project');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
