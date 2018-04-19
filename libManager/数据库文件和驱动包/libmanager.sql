/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : libmanager

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-04-17 10:25:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `Id` int(20) NOT NULL AUTO_INCREMENT,
  `Bookname` varchar(255) NOT NULL,
  `Press` varchar(255) NOT NULL,
  `PressTime` date NOT NULL,
  `Status` varchar(255) NOT NULL DEFAULT '',
  `Borrower` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '平凡的世界', 'zptc出版社', '2018-04-15', '借出', 'lsz');
INSERT INTO `book` VALUES ('2', '深入理解Android内核', 'zptc出版社', '2018-04-03', '借出', 'lsz');
INSERT INTO `book` VALUES ('3', 'xxx', 'xxx', '2018-04-03', '借出', 'lsz');
INSERT INTO `book` VALUES ('4', 'sss', 'aaa', '2018-04-16', '借出', 'wzlsz');
INSERT INTO `book` VALUES ('5', 'lsz', 'lsz', '2018-04-16', '借出', 'wzlsz');
INSERT INTO `book` VALUES ('6', 'qweq', 'asdd', '2018-04-16', '借出', 'wzlsz');
INSERT INTO `book` VALUES ('7', 'lsz', 'lsz', '2018-04-16', '借出', 'lsz');
INSERT INTO `book` VALUES ('8', 'lsz', 'lsz', '2018-03-02', '借出', 'lsz');
INSERT INTO `book` VALUES ('9', 'lsz', 'lsz', '2006-05-12', '借出', 'lsz');
INSERT INTO `book` VALUES ('13', 'adfafsd', 'fasfasdf', '2015-05-25', '借出', 'lszs');
INSERT INTO `book` VALUES ('14', 'fdsafasf', 'adfdsf', '2015-06-01', '借出', 'lsz');
INSERT INTO `book` VALUES ('15', 'asfdsdf', 'adsfasdf', '2015-12-10', '借出', 'wzlsz');
INSERT INTO `book` VALUES ('16', 'afdfsd', 'asdfasdf', '2015-08-12', '未借出', '');
INSERT INTO `book` VALUES ('17', 'lszfdasf', 'adfasd', '2019-11-23', '未借出', '');
INSERT INTO `book` VALUES ('18', 'fasdfasddfs', 'afasfdsdf20', '2015-05-08', '未借出', '');
INSERT INTO `book` VALUES ('19', 'ldsaf', 'asfawsdfsdd', '2015-05-12', '未借出', '');
INSERT INTO `book` VALUES ('20', 'fadfas', 'adsfsfa', '2015-05-12', '未借出', '');
INSERT INTO `book` VALUES ('21', 'lszae', '123456', '2015-05-12', '未借出', '');
INSERT INTO `book` VALUES ('22', 'fsdfaf', '2323526++6', '2015-05-12', '借出', 'lsz');
INSERT INTO `book` VALUES ('23', 'sdflksldfwasd', 'fsdafdafs', '2015-04-03', '借出', 'lsz');
INSERT INTO `book` VALUES ('24', 'lszfadsfdsa', 'fdsgdsgfsd', '2015-05-12', '未借出', '');
INSERT INTO `book` VALUES ('25', 'lsz', 'fsdfsdfa', '2015-05-05', '未借出', '');
INSERT INTO `book` VALUES ('26', 'fdsafaf', 'fsdafas', '2115-05-15', '未借出', '');
INSERT INTO `book` VALUES ('27', 'fsdafasdf', 'fsddf', '2015-09-18', '借出', 'lsz');
INSERT INTO `book` VALUES ('28', 'fsdfsdaf', '4564adfsa', '2015-08-12', '未借出', '');
INSERT INTO `book` VALUES ('29', 'fdsadsfasdf', 'fasdfdsa', '2012-05-18', '未借出', '');
INSERT INTO `book` VALUES ('30', 'dsfsdfwe', 'fadsaf', '2017-08-13', '未借出', '');
INSERT INTO `book` VALUES ('31', 'lsz', '12345665dsfa', '2018-04-03', '未借出', '');
INSERT INTO `book` VALUES ('32', 'sldlfsllfsd', 'fsda2fsda', '2017-08-29', '未借出', '');
INSERT INTO `book` VALUES ('33', 'fssdfsfs', 'fsdfsdf', '2018-08-09', '借出', 'lsz');
INSERT INTO `book` VALUES ('34', 'erwer', '552fsa', '2016-05-13', '未借出', '');
INSERT INTO `book` VALUES ('35', 'lszsafs', '212f', '2016-08-17', '借出', 'lsz');
INSERT INTO `book` VALUES ('36', 'safdsdaf', 'afsdfsd', '2015-09-17', '未借出', '');
INSERT INTO `book` VALUES ('37', 'fsdfsdfa', '555sdfa5as', '2015-08-09', '未借出', '');
INSERT INTO `book` VALUES ('38', 'asdfasdf', '15231564adfsads', '2015-08-09', '未借出', '');
INSERT INTO `book` VALUES ('39', 'fsfdsa', 'fassfd', '2016-08-12', '未借出', '');
INSERT INTO `book` VALUES ('40', 'ldsfsdf', 'sfw', '2014-09-02', '未借出', '');
INSERT INTO `book` VALUES ('41', 'fsdfs', 'safsdf', '2015-05-20', '未借出', '');
INSERT INTO `book` VALUES ('42', 'fsdaf', '20easd', '2001-05-25', '未借出', '');
INSERT INTO `book` VALUES ('43', '212', '2351', '2015-05-25', '未借出', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `registerTime` varchar(255) NOT NULL DEFAULT '',
  `currentLoginTime` varchar(255) NOT NULL DEFAULT '',
  `lastLoginTime` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('17', '20180417', 'e10adc3949ba59abbe56e057f20f883e', '2018-04-17 10:17:30', '2018-04-17 10:17:48', '2018-04-17 10:17:48');
INSERT INTO `user` VALUES ('18', 'lsz', 'e10adc3949ba59abbe56e057f20f883e', '2018-04-17 10:18:51', '2018-04-17 10:18:55', '2018-04-17 10:18:55');
