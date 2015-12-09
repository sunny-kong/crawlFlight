/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50614
 Source Host           : localhost
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50614
 File Encoding         : utf-8

 Date: 12/10/2015 00:32:09 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `flightinfo`
-- ----------------------------
DROP TABLE IF EXISTS `flightinfo`;
CREATE TABLE `flightinfo` (
  `flightNo` varchar(255) NOT NULL,
  `parentname` varchar(255) NOT NULL,
  `departuretime` date NOT NULL,
  `landingtime` date NOT NULL,
  `price` double NOT NULL,
  `departurecity` varchar(255) NOT NULL,
  `landingcity` varchar(255) NOT NULL,
  PRIMARY KEY (`flightNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `flightinfo`
-- ----------------------------
BEGIN;
INSERT INTO `flightinfo` VALUES ('JD5119', '携程', '2016-02-06', '2016-02-06', '1325', '呼和浩特(HET)', '乌鲁木齐(URC)');
COMMIT;

