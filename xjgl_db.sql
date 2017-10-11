/*
Navicat MySQL Data Transfer

Source Server         : wjc
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : xjgl_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-11 11:19:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_chengji
-- ----------------------------
DROP TABLE IF EXISTS `t_chengji`;
CREATE TABLE `t_chengji` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chengji` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `kecheng` varchar(255) DEFAULT NULL,
  `laoshi` varchar(255) DEFAULT NULL,
  `xuefen` varchar(255) DEFAULT NULL,
  `studentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B0E4C6DBE04785` (`studentid`),
  CONSTRAINT `FK4B0E4C6DBE04785` FOREIGN KEY (`studentid`) REFERENCES `t_student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chengji
-- ----------------------------
INSERT INTO `t_chengji` VALUES ('2', '98', '2017-09-19 09:24:46', '0', 'java', '张三丰', '2', '3');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `banjimingchen` varchar(255) DEFAULT NULL,
  `beizhu` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `lianxidianhua` varchar(255) DEFAULT NULL,
  `lixiaoriqi` varchar(255) DEFAULT NULL,
  `lixiaoyuanyin` varchar(255) DEFAULT NULL,
  `pingyu` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `sfz` varchar(255) DEFAULT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `sno` varchar(255) DEFAULT NULL,
  `touxiang` varchar(255) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAEC90D50809B0D13` (`teacherid`),
  CONSTRAINT `FKAEC90D50809B0D13` FOREIGN KEY (`teacherid`) REFERENCES `t_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('3', '苏州', '软件1班', '学得不错', '0', '110', '2017-10-1', '毕业', null, '男', '1111', '张无忌', 'student01', '20170919092417.jpg', '3');

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bianhao` varchar(255) DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `sfz` varchar(255) DEFAULT NULL,
  `tname` varchar(255) DEFAULT NULL,
  `xingbie` varchar(255) DEFAULT NULL,
  `yuanxi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('3', 'teacher01', '0', '111', '张三丰', '男', '软件工程');
INSERT INTO `t_teacher` VALUES ('4', 'teacher02', '0', '222', '灭绝师太', '女', '软件工程');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `deletestatus` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `truename` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', null, '0', 'admin', '1', 'admin', 'admin');
INSERT INTO `t_user` VALUES ('6', '2017-09-19 09:20:06', '0', '111111', '2', '张三丰', 'teacher01');
INSERT INTO `t_user` VALUES ('7', '2017-09-19 09:20:31', '0', '111111', '2', '灭绝师太', 'teacher02');
INSERT INTO `t_user` VALUES ('8', '2017-09-19 09:24:17', '0', '111111', '3', '张无忌', 'student01');
