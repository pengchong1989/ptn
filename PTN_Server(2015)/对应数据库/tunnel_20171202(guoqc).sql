/*
Navicat MySQL Data Transfer

Source Server         : ptn
Source Server Version : 50531
Source Host           : localhost:3308
Source Database       : ptn

Target Server Type    : MYSQL
Target Server Version : 50531
File Encoding         : 65001

Date: 2017-12-01 18:37:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tunnel`
-- ----------------------------
DROP TABLE IF EXISTS `tunnel`;
CREATE TABLE `tunnel` (
  `tunnelId` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelName` varchar(200) DEFAULT NULL,
  `tunnelType` varchar(200) DEFAULT NULL,
  `tunnelStatus` int(11) DEFAULT NULL,
  `bandwidth` int(11) DEFAULT NULL,
  `aSiteId` int(11) DEFAULT NULL,
  `zSiteId` int(11) DEFAULT NULL,
  `aPortId` int(11) DEFAULT NULL,
  `zPortId` int(11) DEFAULT NULL,
  `direction` varchar(1000) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(200) DEFAULT NULL,
  `isReverse` int(11) DEFAULT NULL,
  `protectTunnelId` int(11) DEFAULT NULL,
  `protectType` int(11) DEFAULT NULL,
  `protectTunnelName` varchar(200) DEFAULT NULL,
  `isSingle` int(11) DEFAULT '0' COMMENT '是否为单网元配置：0否 1是',
  `jobStatus` varchar(200) DEFAULT NULL,
  `waittime` int(11) DEFAULT NULL COMMENT '等待恢复时间',
  `delaytime` int(11) DEFAULT NULL COMMENT '拖延时间',
  `apsenable` int(11) DEFAULT NULL COMMENT 'aps使能  1使能 0不使能',
  `position` int(11) DEFAULT NULL,
  `protectBack` int(11) DEFAULT NULL,
  `aprotectId` int(11) DEFAULT NULL,
  `zprotectId` int(11) DEFAULT NULL,
  `sncpIds` varchar(200) DEFAULT NULL,
  `inBandwidthControl` int(11) DEFAULT NULL,
  `outBandwidthControl` int(11) DEFAULT NULL,
  `aVlanEnable` int(11) DEFAULT NULL,
  `aOutVlanValue` int(11) DEFAULT NULL,
  `aTp_id` int(11) DEFAULT NULL,
  `zVlanEnable` int(11) DEFAULT NULL,
  `zOutVlanValue` int(11) DEFAULT NULL,
  `zTp_id` int(11) DEFAULT NULL,
  `sourceMac` varchar(200) DEFAULT NULL,
  `endMac` varchar(200) DEFAULT NULL,
  `snmpTunnelName` varchar(200) DEFAULT NULL,
  `rotateWay` varchar(200) DEFAULT NULL,
  `rotateLocation` varchar(200) DEFAULT NULL,
  `rotateMode` varchar(200) DEFAULT NULL,
  `tnpLayer` int(11) DEFAULT NULL,
  `rotateThreshold` int(11) DEFAULT NULL,
  PRIMARY KEY (`tunnelId`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

