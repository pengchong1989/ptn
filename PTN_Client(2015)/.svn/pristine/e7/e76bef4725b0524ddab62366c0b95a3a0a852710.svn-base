/*
Navicat MySQL Data Transfer

Source Server         : 工程版本
Source Server Version : 50520
Source Host           : 10.18.1.62:3306
Source Database       : ptn

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2014-12-12 11:05:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `acbuffer`
-- ----------------------------
DROP TABLE IF EXISTS `acbuffer`;
CREATE TABLE `acbuffer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acId` int(11) DEFAULT NULL,
  `bufferEnable` int(11) DEFAULT NULL,
  `vlanId` int(11) DEFAULT NULL,
  `sourceMac` varchar(200) DEFAULT NULL,
  `targetMac` varchar(200) DEFAULT NULL,
  `eightIp` int(11) DEFAULT NULL,
  `sourceIp` varchar(200) DEFAULT NULL,
  `targetIp` varchar(200) DEFAULT NULL,
  `ipDscp` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `cir` int(11) DEFAULT NULL,
  `pir` int(11) DEFAULT NULL,
  `cm` int(11) DEFAULT NULL,
  `cbs` int(11) DEFAULT NULL,
  `pbs` int(11) DEFAULT NULL,
  `strategy` int(11) DEFAULT NULL,
  `phb` int(11) DEFAULT NULL,
  `portId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `clientVlanIdValue` varchar(200) DEFAULT NULL,
  `clientVlanIdMask` varchar(200) DEFAULT NULL,
  `operatorVlanIdValue` varchar(200) DEFAULT NULL,
  `operatorVlanIdMask` varchar(200) DEFAULT NULL,
  `clientVlanPriorityValue` varchar(200) DEFAULT NULL,
  `clientVlanPriorityMask` varchar(200) DEFAULT NULL,
  `operatorVlanPriorityValue` varchar(200) DEFAULT NULL,
  `operatorVlanPriorityMask` varchar(200) DEFAULT NULL,
  `ethernetTypeValue` varchar(200) DEFAULT NULL,
  `ethernetTypeMask` varchar(200) DEFAULT NULL,
  `sourceMacLabelMask` varchar(200) DEFAULT NULL,
  `sinkMacLabelMask` varchar(200) DEFAULT NULL,
  `iPTypeValue` varchar(200) DEFAULT NULL,
  `iPTypeMask` varchar(200) DEFAULT NULL,
  `sourceIpLabelMask` varchar(200) DEFAULT NULL,
  `sinkIpLabelMask` varchar(200) DEFAULT NULL,
  `appendBufferName` varchar(200) DEFAULT NULL,
  `qosName` varchar(200) DEFAULT NULL,
  `qosType` varchar(200) DEFAULT NULL,
  `simpleQosId` int(11) DEFAULT NULL,
  `ebs` int(11) DEFAULT NULL,
  `eir` int(11) DEFAULT NULL,
  `sourceTcpPort` int(11) DEFAULT NULL,
  `endTcpPort` int(11) DEFAULT NULL,
  `IPToS` int(11) DEFAULT NULL,
  `portType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acbuffer
-- ----------------------------

-- ----------------------------
-- Table structure for `acinfo`
-- ----------------------------
DROP TABLE IF EXISTS `acinfo`;
CREATE TABLE `acinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL COMMENT '关联网元表主键',
  `portId` int(11) DEFAULT NULL COMMENT '关联端口表主键，当此条AC是port下时，此字段有值，否则是0',
  `lagId` int(11) DEFAULT NULL COMMENT '关联lag表主键，当此条AC是lag下时，此字段有值，否则是0',
  `portModel` int(11) DEFAULT NULL COMMENT '端口模式，关联code表主键',
  `operatorVlanId` varchar(255) DEFAULT NULL COMMENT '运营商VLANID',
  `clientVlanId` varchar(255) DEFAULT NULL COMMENT '客户运营商ID',
  `managerEnable` int(11) DEFAULT NULL COMMENT '管理状态，关联code表主键 = TMC流量监管使能',
  `exitRule` int(11) DEFAULT NULL COMMENT '出口规则，关联code表主键 = 下话TAG行为',
  `vlanId` varchar(255) DEFAULT NULL COMMENT 'vlanid=下话增加VLAN ID',
  `vlancri` varchar(255) DEFAULT NULL COMMENT 'vlancri',
  `vlanpri` varchar(255) DEFAULT NULL COMMENT 'vlanpri=下话增加VLAN PRI',
  `horizontalDivision` int(11) DEFAULT NULL COMMENT '水平分割 关联code表主键',
  `macAddressLearn` int(11) DEFAULT NULL COMMENT 'mac地址学习 关联code表主键',
  `tagAction` int(11) DEFAULT NULL COMMENT '上话tag行为',
  `jobStatus` varchar(255) DEFAULT NULL COMMENT '工作状态 ',
  `acBusinessId` int(11) DEFAULT NULL COMMENT '设备ID 从businessid表中取',
  `bufType` int(11) DEFAULT NULL COMMENT '流类型，关联code表主键',
  `name` varchar(255) DEFAULT NULL COMMENT 'ac名称',
  `isUser` int(11) DEFAULT NULL COMMENT '是否被使用 1=true 0=false',
  `model` int(11) DEFAULT NULL,
  `acStatus` int(11) DEFAULT NULL,
  `macCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `acl_inst`
-- ----------------------------
DROP TABLE IF EXISTS `acl_inst`;
CREATE TABLE `acl_inst` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteid` int(8) DEFAULT NULL,
  `act` int(8) DEFAULT NULL,
  `direction` int(8) DEFAULT NULL,
  `portNumber` int(8) DEFAULT NULL,
  `vlanId` int(8) DEFAULT NULL,
  `isSourceMac` int(8) DEFAULT NULL,
  `sourceMac` varchar(200) DEFAULT NULL,
  `isPurposeMac` int(8) DEFAULT NULL,
  `purposeMac` varchar(200) DEFAULT NULL,
  `isSourceIp` int(8) DEFAULT NULL,
  `sourceIp` varchar(200) DEFAULT NULL,
  `isPurposeIp` int(8) DEFAULT NULL,
  `purposeIp` varchar(200) DEFAULT NULL,
  `isMatchCos` int(8) DEFAULT NULL,
  `cosValue` int(8) DEFAULT NULL,
  `isMatchDSCP` int(8) DEFAULT NULL,
  `dscpValue` int(8) DEFAULT NULL,
  `isMatchTOS` int(8) DEFAULT NULL,
  `tosValue` int(8) DEFAULT NULL,
  `isSourcePort` int(8) DEFAULT NULL,
  `sourcePort` int(8) DEFAULT NULL,
  `isPurposePort` int(8) DEFAULT NULL,
  `purposePort` int(8) DEFAULT NULL,
  `ruleObject` int(8) DEFAULT NULL,
  `treatyType` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of acl_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `alarm_filter`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_filter`;
CREATE TABLE `alarm_filter` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteid` int(8) DEFAULT NULL,
  `cardid` int(8) DEFAULT NULL,
  `objectid` int(8) DEFAULT NULL,
  `objecttype` int(8) DEFAULT NULL,
  `alarmcode` int(8) DEFAULT NULL,
  `alarmlevel` int(8) DEFAULT NULL,
  `begintime` varchar(200) DEFAULT NULL,
  `endtime` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_filter
-- ----------------------------

-- ----------------------------
-- Table structure for `alarm_voice`
-- ----------------------------
DROP TABLE IF EXISTS `alarm_voice`;
CREATE TABLE `alarm_voice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `switch` int(11) DEFAULT NULL,
  `alarmSoundPath` varchar(200) DEFAULT NULL,
  `alarmColorRGB` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alarm_voice
-- ----------------------------
INSERT INTO `alarm_voice` VALUES ('40', '1', '60', '1', 'config/sounds/warning.wav', '-1564897');
INSERT INTO `alarm_voice` VALUES ('41', '2', '60', '1', 'config/sounds/minor.wav', '-26368');
INSERT INTO `alarm_voice` VALUES ('42', '3', '60', '1', 'config/sounds/major.wav', '-1447651');
INSERT INTO `alarm_voice` VALUES ('43', '4', '60', '1', 'config/sounds/critical.wav', '-7368720');

-- ----------------------------
-- Table structure for `all_config`
-- ----------------------------
DROP TABLE IF EXISTS `all_config`;
CREATE TABLE `all_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `singleMACAddress` varchar(200) DEFAULT NULL,
  `addressAgeSwitch` int(11) DEFAULT NULL,
  `macAddressAgeDate` int(11) DEFAULT NULL,
  `throwWrapDateGap` int(11) DEFAULT NULL,
  `fdiStatus` int(11) DEFAULT NULL,
  `fidMel` int(11) DEFAULT NULL,
  `apsRecoverTime` int(11) DEFAULT NULL,
  `crcVerify` int(11) DEFAULT NULL,
  `throwWrap` int(11) DEFAULT NULL,
  `receiveBadWrap` int(11) DEFAULT NULL,
  `align` int(11) DEFAULT NULL,
  `mirrorModel` int(11) DEFAULT NULL,
  `mirrorByPort` int(11) DEFAULT NULL,
  `mirrorPort` int(11) DEFAULT NULL,
  `mplsTPControl` int(11) DEFAULT NULL,
  `channelType` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `tmsWorsen` int(11) DEFAULT NULL,
  `tmsLose` int(11) DEFAULT NULL,
  `tmcfdi` int(11) DEFAULT NULL,
  `tmcmel` int(11) DEFAULT NULL,
  `apsModel` int(11) DEFAULT NULL,
  `roundEnable` int(11) DEFAULT NULL,
  `vlanMAC` int(11) DEFAULT NULL,
  `vlanValue` int(11) DEFAULT NULL,
  `macNumber` int(11) DEFAULT NULL,
  `lacp` int(11) DEFAULT NULL,
  `equipmentPriority` int(11) DEFAULT NULL,
  `dhcpModel` int(11) DEFAULT NULL,
  `loopCheck` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of all_config
-- ----------------------------

-- ----------------------------
-- Table structure for `blacklistmac`
-- ----------------------------
DROP TABLE IF EXISTS `blacklistmac`;
CREATE TABLE `blacklistmac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) NOT NULL,
  `vlanId` int(11) DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `portId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blacklistmac
-- ----------------------------

-- ----------------------------
-- Table structure for `blackwhitemacnamelist`
-- ----------------------------
DROP TABLE IF EXISTS `blackwhitemacnamelist`;
CREATE TABLE `blackwhitemacnamelist` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteId` int(8) DEFAULT NULL,
  `vplsServiceName` varchar(200) DEFAULT NULL,
  `elanBussinessId` int(8) DEFAULT NULL,
  `nameList` int(8) DEFAULT NULL,
  `mac` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blackwhitemacnamelist
-- ----------------------------

-- ----------------------------
-- Table structure for `businessid`
-- ----------------------------
DROP TABLE IF EXISTS `businessid`;
CREATE TABLE `businessid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteid` int(11) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  `idvalue` int(11) DEFAULT NULL,
  `idStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of businessid
-- ----------------------------

-- ----------------------------
-- Table structure for `capability`
-- ----------------------------
DROP TABLE IF EXISTS `capability`;
CREATE TABLE `capability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CapabilityType` varchar(20) DEFAULT NULL,
  `CapabilityName` varchar(50) DEFAULT NULL,
  `CapabilityDesc` varchar(200) DEFAULT NULL,
  `CapabilityDisp` varchar(200) DEFAULT NULL,
  `CapabilityProlate` varchar(200) DEFAULT NULL,
  `CapabilityCode` int(12) DEFAULT NULL,
  `CapabilityCounter` varchar(20) DEFAULT NULL,
  `Manufacturer` int(11) DEFAULT NULL,
  `UnitName` varchar(200) DEFAULT NULL,
  `CapabilityDesc_en` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=261 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of capability
-- ----------------------------
INSERT INTO `capability` VALUES ('1', 'PORT', 'RX_PACKS', '接收总包数', null, '93', '16', null, '1', '个', 'number of received packet sum');
INSERT INTO `capability` VALUES ('2', 'PORT', 'TX_PACKS', '发送总包数', null, '', '17', null, '1', '个', 'number of sended packet sum');
INSERT INTO `capability` VALUES ('3', 'PORT', 'RX_BYTES', '接收总字节数', null, '', '18', null, '1', 'KB', 'number of received byte sum');
INSERT INTO `capability` VALUES ('4', 'PORT', 'TX_BYTES', '发送总字节数', null, '', '19', null, '1', 'KB', 'number of sended byte sum');
INSERT INTO `capability` VALUES ('19', 'PORT', 'TX_BDPK', '发送坏包数', null, null, '21', '', '1', '个', 'number of bad packets sent');
INSERT INTO `capability` VALUES ('23', 'PORT', 'RX_127BYTES', '收到的包(65-127字节)', null, null, '24', '', '1', '个', 'received packets (65-127 bytes)');
INSERT INTO `capability` VALUES ('24', 'PORT', 'RX_255BYTES', '收到的包(128-255字节)', null, null, '26', '', '1', '个', 'received packets (128-255 bytes)');
INSERT INTO `capability` VALUES ('25', 'PORT', 'RX_511BYTES', '收到的包(256-511字节)', null, null, '28', '', '1', '个', 'received packets (256-511 bytes)');
INSERT INTO `capability` VALUES ('27', 'PORT', 'RX_1518BYTES', '收到的包(1024-1518字节)', null, null, '32', '', '1', '个', 'received packets (1024-1518 bytes)');
INSERT INTO `capability` VALUES ('28', 'PORT', 'RX_MAXBYTES', '收到的包(1519-MAX字节)', null, null, '34', '', '1', '个', 'received packets (1519-Max bytes)');
INSERT INTO `capability` VALUES ('29', 'PORT', 'TX_64BYTES', '发送的包(64字节)', null, null, '23', '', '1', '个', 'sended packet (64 bytes)');
INSERT INTO `capability` VALUES ('30', 'PORT', 'TX_127BYTES', '发送的包(65-127字节)', null, null, '25', '', '1', '个', 'sended packet (65-127 bytes)');
INSERT INTO `capability` VALUES ('31', 'PORT', 'TX_255BYTES', '发送的包(128-255字节)', null, null, '27', '', '1', '个', 'sended packet (128-255 bytes)');
INSERT INTO `capability` VALUES ('32', 'PORT', 'TX_511BYTES', '发送的包(256-511字节)', null, null, '29', '', '1', '个', 'sended packet (256-511 bytes)');
INSERT INTO `capability` VALUES ('33', 'PORT', 'TX_1023BYTES', '发送的包(512-1023字节)', null, null, '31', '', '1', '个', 'sended packet (512-1023 bytes)');
INSERT INTO `capability` VALUES ('34', 'PORT', 'TX_1518BYTES', '发送的包(1024-1518字节)', null, null, '33', '', '1', '个', 'sended packet (1024-1518 bytes)');
INSERT INTO `capability` VALUES ('35', 'PORT', 'TX_MAXBYTES', '发送的包(1519-MAX字节)', null, null, '35', '', '1', '个', 'sended packet (1519-Max bytes)');
INSERT INTO `capability` VALUES ('38', 'PORT', 'CRC_ERR', 'CRC校验错', null, '93', '36', '', '1', '个', 'CRC error');
INSERT INTO `capability` VALUES ('39', 'PORT', 'STAT_USZ', '短包数统计', null, '93', '37', '', '1', '个', 'short packet statistics');
INSERT INTO `capability` VALUES ('40', 'PORT', 'STAT_OSZ', '长包数统计', null, '93', '38', '', '1', '个', 'long packet statistics');
INSERT INTO `capability` VALUES ('41', 'PORT', 'FRAGMENT', '碎片包数', null, '93', '39', '', '1', '个', 'number of fragments packet');
INSERT INTO `capability` VALUES ('42', 'PORT', 'JABBER', 'Jabbers包统计', null, '93', '40', '', '1', '个', 'Jabbers packet statistics');
INSERT INTO `capability` VALUES ('43', 'PORT', 'ALIGNMENT', 'ALIGNMENT包统计', null, '93', '41', '', '1', '个', 'ALIGNMENT packet statistics');
INSERT INTO `capability` VALUES ('44', 'PORT', 'RX_BCAST', '接收广播包数', null, '93', '42', '', '1', '个', 'received broadcast packet number');
INSERT INTO `capability` VALUES ('45', 'PORT', 'RX_MCAST', '接收组播包数', null, '93', '44', '', '1', '个', 'received multicast packets number');
INSERT INTO `capability` VALUES ('46', 'PORT', 'RX_PAUSE', '接收的PAUSE帧数', null, '93', '46', '', '1', '个', 'received PAUSE frames number');
INSERT INTO `capability` VALUES ('47', 'PORT', 'RX_DROP', '接收丢包数', null, '93', '48', '', '1', '个', 'received loss packets number');
INSERT INTO `capability` VALUES ('48', 'PORT', 'TX_BCAST', '发送广播包数', null, '93', '43', '', '1', '个', 'sended broadcast packets number');
INSERT INTO `capability` VALUES ('49', 'PORT', 'TX_MCAST', '发送组播包数', null, '93', '45', '', '1', '个', 'sended multicast packets number');
INSERT INTO `capability` VALUES ('50', 'PORT', 'TX_PAUSE', '发送的PAUSE帧数', null, '93', '47', '', '1', '个', 'sended PAUSE frames number');
INSERT INTO `capability` VALUES ('51', 'MPLS', 'TPALL', '发送包总数', null, null, '1', null, '2', '个', 'transmited totoal packet');
INSERT INTO `capability` VALUES ('52', 'MPLS', 'TOALL', '发送字节总数', null, null, '2', null, '2', '个', 'transmited totoal octet');
INSERT INTO `capability` VALUES ('53', 'MPLS', 'RPALL', '接收包总数', null, null, '3', null, '2', '个', 'received totoal packet');
INSERT INTO `capability` VALUES ('54', 'MPLS', 'ROALL', '接收字节总数', null, null, '4', null, '2', '个', 'received totoal octet');
INSERT INTO `capability` VALUES ('55', 'MPLS', 'DROPRATIO', '丢包率', null, null, '5', null, '2', '个', 'packet loss ratio');
INSERT INTO `capability` VALUES ('56', 'MPLS', 'LATENCY', '时延', null, null, '6', null, '2', '个', 'packet latency');
INSERT INTO `capability` VALUES ('57', 'MPLS', 'JITTER', '时延变化', null, null, '7', null, '2', '个', 'packet latency jitter');
INSERT INTO `capability` VALUES ('58', 'ETH', 'TP64', '发送包数长度64字节', null, null, '8', null, '2', '个', 'transmited packet of length 64');
INSERT INTO `capability` VALUES ('59', 'ETH', 'TP65TO127', '发送包数长度65-127字节', null, null, '9', null, '2', '个', 'transmited packet of length 65-127');
INSERT INTO `capability` VALUES ('60', 'ETH', 'TP128TO255', '发送包数长度128-255字节', null, null, '10', null, '2', '个', 'transmited packet of length 128-255');
INSERT INTO `capability` VALUES ('61', 'ETH', 'TP256TO511', '发送包数长度256-511字节', null, null, '11', null, '2', '个', 'transmited packet of length 256-511');
INSERT INTO `capability` VALUES ('62', 'ETH', 'TP512TO1023', '发送包数长度512-1023字节', null, null, '12', null, '2', '个', 'transmited packet of length 512-1023');
INSERT INTO `capability` VALUES ('63', 'ETH', 'TP1024TO1518', '发送包数长度1024-1518字节', null, null, '13', null, '2', '个', 'transmited packet of length 1024-1518');
INSERT INTO `capability` VALUES ('64', 'ETH', 'TPOVERSIZE', '发送包数长度大于1518', null, null, '14', null, '2', '个', 'transmited packet of length more than 1518');
INSERT INTO `capability` VALUES ('65', 'ETH', 'RP64', '接收包数长度64字节', null, null, '15', null, '2', '个', 'received packet of length 64');
INSERT INTO `capability` VALUES ('66', 'ETH', 'RP65TO127', '接收包数长度65-127字节', null, null, '16', null, '2', '个', 'received packet of length 65-127');
INSERT INTO `capability` VALUES ('67', 'ETH', 'RP128TO255', '接收包数长度128-255字节', null, null, '17', null, '2', '个', 'received packet of length 128-255');
INSERT INTO `capability` VALUES ('68', 'ETH', 'RP256TO511', '接收包数长度256-511字节', null, null, '18', null, '2', '个', 'received packet of length 256-511');
INSERT INTO `capability` VALUES ('69', 'ETH', 'RP512TO1023', '接收包数长度512-1023字节', null, null, '19', null, '2', '个', 'received packet of length 512-1023');
INSERT INTO `capability` VALUES ('70', 'ETH', 'RP1024TO1518', '接收包数长度1024-1518字节', null, null, '20', null, '2', '个', 'received packet of length 1024-1518');
INSERT INTO `capability` VALUES ('71', 'ETH', 'RPOVERSIZE', '接收包数长度大于1518', null, null, '21', null, '2', '个', 'received packet of length more than 1518');
INSERT INTO `capability` VALUES ('72', 'ETH', 'TPUC', '发送单播包数', null, null, '22', null, '2', '个', 'transmited packet of unicast');
INSERT INTO `capability` VALUES ('73', 'ETH', 'TPMC', '发送组播包数', null, null, '23', null, '2', '个', 'transmited packet of multicast');
INSERT INTO `capability` VALUES ('74', 'ETH', 'TPBC', '发送广播包数', null, null, '24', null, '2', '个', 'transmited packet of broadcast');
INSERT INTO `capability` VALUES ('75', 'ETH', 'RPUC', '接收单播包数', null, null, '25', null, '2', '个', 'received packet of unicast');
INSERT INTO `capability` VALUES ('76', 'ETH', 'RPMC', '接收组播包数', null, null, '26', null, '2', '个', 'received packet of multicast');
INSERT INTO `capability` VALUES ('77', 'ETH', 'RPBC', '接收广播包数', null, null, '27', null, '2', '个', 'received packet of broadcast');
INSERT INTO `capability` VALUES ('78', 'ETH', 'TPOK', '发送的好包数', null, null, '28', null, '2', '个', 'transmited packet ok');
INSERT INTO `capability` VALUES ('79', 'ETH', 'TOOK', '发送的好包字节总数', null, null, '29', null, '2', '个', 'transmited octet ok');
INSERT INTO `capability` VALUES ('80', 'ETH', 'RPOK', '接收的好包数', null, null, '30', null, '2', '个', 'received packet ok');
INSERT INTO `capability` VALUES ('81', 'ETH', 'ROOK', '接收的好包字节总数', null, null, '31', null, '2', '个', 'received octet ok');
INSERT INTO `capability` VALUES ('82', 'ETH', 'ROBAD', '接收的坏包字节总数', null, null, '32', null, '2', '个', 'received octet bad');
INSERT INTO `capability` VALUES ('83', 'ETH', 'TOBAD', '发送的坏包字节总数', null, null, '33', null, '2', '个', 'transmited octet bad');
INSERT INTO `capability` VALUES ('84', 'ETH', 'FCSERR', '校验错误数', null, null, '34', null, '2', '个', 'fcs error');
INSERT INTO `capability` VALUES ('85', 'VC12', 'LPES', '低阶通道误码秒', null, null, '35', null, '2', '个', 'lp error second');
INSERT INTO `capability` VALUES ('86', 'VC12', 'LPSES', '低阶通道严重误码秒', null, null, '36', null, '2', '个', 'lp serious error second');
INSERT INTO `capability` VALUES ('87', 'VC12', 'LPBBE', '低阶通道背景块误码', null, null, '37', null, '2', '个', 'lp background block error');
INSERT INTO `capability` VALUES ('88', 'VC12', 'LPUAS', '低阶通道不可用秒', null, null, '38', null, '2', '个', 'lp unavailable second');
INSERT INTO `capability` VALUES ('89', 'STM1', 'RSES', '再生段误码秒', null, null, '39', null, '2', '个', 'rs error second');
INSERT INTO `capability` VALUES ('90', 'STM1', 'RSSES', '再生段严重误码秒', null, null, '40', null, '2', '个', 'rs serious error second');
INSERT INTO `capability` VALUES ('91', 'STM1', 'RSBBE', '再生段背景块误码', null, null, '41', null, '2', '个', 'rs background block error');
INSERT INTO `capability` VALUES ('92', 'STM1', 'RSUAS', '再生段不可用秒', null, null, '42', null, '2', '个', 'rs unavailable second');
INSERT INTO `capability` VALUES ('93', 'STM1', 'MSES', '复用段误码秒', null, null, '43', null, '2', '个', 'ms error second');
INSERT INTO `capability` VALUES ('94', 'STM1', 'MSSES', '复用段严重误码秒', null, null, '44', null, '2', '个', 'ms serious error second');
INSERT INTO `capability` VALUES ('95', 'STM1', 'MSBBE', '复用段背景块误码', null, null, '45', null, '2', '个', 'ms background block error');
INSERT INTO `capability` VALUES ('96', 'STM1', 'MSUAS', '复用段不可用秒', null, null, '46', null, '2', '个', 'ms unavailable second');
INSERT INTO `capability` VALUES ('97', 'STM1', 'HPES', '高阶通道误码秒', null, null, '47', null, '2', '个', 'hp error second');
INSERT INTO `capability` VALUES ('98', 'STM1', 'HPSES', '高阶通道严重误码秒', null, null, '48', null, '2', '个', 'hp serious error second');
INSERT INTO `capability` VALUES ('99', 'STM1', 'HPBBE', '高阶通道背景块误码', null, null, '49', null, '2', '个', 'hp background block error');
INSERT INTO `capability` VALUES ('100', 'STM1', 'HPUAS', '高阶通道不可用秒', null, null, '50', null, '2', '个', 'hp unavailable second');
INSERT INTO `capability` VALUES ('101', 'PHY', 'IOP', '光接受功率', null, null, '51', null, '2', '个', 'input optical power');
INSERT INTO `capability` VALUES ('102', 'PHY', 'OOP', '光发送功率', null, null, '52', null, '2', '个', 'output optical power');
INSERT INTO `capability` VALUES ('103', 'PHY', 'LB', '激光器偏置电流', null, null, '53', null, '2', '个', 'laser BIAS');
INSERT INTO `capability` VALUES ('104', 'PHY', 'LT', '激光器温度', null, null, '54', null, '2', '个', 'laser temperature');
INSERT INTO `capability` VALUES ('105', 'PWTDM', 'PWTDMMISSINGPKTS', '通过接收到的控制字的序列号发现的丢失分组个数。', null, null, '55', null, '2', '个', 'missing packets');
INSERT INTO `capability` VALUES ('106', 'PWTDM', 'PWTDMJTRBFRUNDERRUNS', 'jitter buffer的underrun的次数', null, null, '56', null, '2', '个', 'jitter buffer underrun');
INSERT INTO `capability` VALUES ('107', 'PWTDM', 'PWTDMMALFORMEDPKTS', '收到的长度错误或者首部错误的分组个数。', null, null, '57', null, '2', '个', 'malformed packets');
INSERT INTO `capability` VALUES ('108', 'PWTDM', 'PWTDMJTRBFROVERRUNS', '抖动缓冲区的上溢次数', null, null, '58', null, '2', '个', 'tdm emulation jitter buffer overrun');
INSERT INTO `capability` VALUES ('109', 'PDH', 'PDHCV', 'E1端口码违例', null, null, '59', null, '2', '个', 'pdh code violation');
INSERT INTO `capability` VALUES ('110', 'PON', 'MPCP', 'pon口MPCP帧统计', null, null, '60', null, '2', '个', 'pon MPCP frame');
INSERT INTO `capability` VALUES ('111', 'PON', 'OAMPDU', 'pon口OAMPDU帧统计', null, null, '61', null, '2', '个', 'pon OAMPDU frame');
INSERT INTO `capability` VALUES ('112', 'PON', 'RPKT63', 'pon口接收包数长度0-63', null, null, '62', null, '2', '个', 'received packet of length 0-63 in pon');
INSERT INTO `capability` VALUES ('113', 'PON', 'RPKT64TO127', 'pon口接收包数长度64-127', null, null, '63', null, '2', '个', 'received packet of length 64-127 in pon');
INSERT INTO `capability` VALUES ('114', 'PON', 'RPKT128TO255', 'pon口接收包数长度128-255', null, null, '64', null, '2', '个', 'received packet of length 128-255 in pon');
INSERT INTO `capability` VALUES ('115', 'PON', 'RPKT256TO511', 'pon口接收包数长度256-511', null, null, '65', null, '2', '个', 'received packet of length 256-511 in pon');
INSERT INTO `capability` VALUES ('116', 'PON', 'RPKT512TO1023', 'pon口接收包数长度512-1023', null, null, '66', null, '2', '个', 'received packet of length 512-1023 in pon');
INSERT INTO `capability` VALUES ('117', 'PON', 'RPKT1024TO1535', 'pon口接收包数长度1024-1535', null, null, '67', null, '2', '个', 'received packet of length 1024-1535 in pon');
INSERT INTO `capability` VALUES ('118', 'PON', 'RPKTFCSERR', 'pon口接收FCS错包计数', null, null, '68', null, '2', '个', 'received packet of fcs err in pon');
INSERT INTO `capability` VALUES ('119', 'PON', 'RPKTSUPERLEN', 'pon口接收超长包计数', null, null, '69', null, '2', '个', 'received packet of super length in pon');
INSERT INTO `capability` VALUES ('120', 'PON', 'RPKTLLIDERR', 'pon口接收LLID错误的包计数', null, null, '70', null, '2', '个', 'received packet of llid error in pon');
INSERT INTO `capability` VALUES ('121', 'PON', 'RBYTEOK', 'pon口接收正确包字节计数', null, null, '71', null, '2', '个', 'received octet ok in pon');
INSERT INTO `capability` VALUES ('122', 'PON', 'RBYTEERR', '接收错误包字节计数', null, null, '72', null, '2', '个', 'received octet bad in pon');
INSERT INTO `capability` VALUES ('123', 'PON', 'RPKTKNUC', 'pon口接收到的正确的已知单播包计数', null, null, '73', null, '2', '个', 'received packet of know unicast in pon');
INSERT INTO `capability` VALUES ('124', 'PON', 'RPKTUNKNUC', 'pon口接收到的正确的未知单播包计数', null, null, '74', null, '2', '个', 'received packet of unknow unicast in pon');
INSERT INTO `capability` VALUES ('125', 'PON', 'RPKTKNMC', 'pon口接收到的正确的已知组播包计数', null, null, '75', null, '2', '个', 'received packet of know multicast in pon');
INSERT INTO `capability` VALUES ('126', 'PON', 'RPKTUNKNMC', 'pon口接收到的正确的未知组播包计数', null, null, '76', null, '2', '个', 'received packet of unknow multicast in pon');
INSERT INTO `capability` VALUES ('127', 'PON', 'RPKTBC', 'pon口接收到的正确的广播包计数', null, null, '77', null, '2', '个', 'received packet of broadcast in pon');
INSERT INTO `capability` VALUES ('128', 'PON', 'RPKTNOVLAN', 'pon口接收正确但被FWE_VLAN/Trunk丢弃 丢弃的包计数', null, null, '78', null, '2', '个', 'received packet but droped by vlan in pon');
INSERT INTO `capability` VALUES ('129', 'PON', 'RPKTNOMAC', 'pon口接收正确但被FWE_MAC过滤丢弃的包计数', null, null, '79', null, '2', '个', 'received packet but droped by mac in pon');
INSERT INTO `capability` VALUES ('130', 'PON', 'RPKTNOP2P', 'pon口接收正确但被FWE_P2P/VLAN成员/本地交换过滤丢弃的包计数', null, null, '80', null, '2', '个', 'received packet but droped by p2p in pon');
INSERT INTO `capability` VALUES ('131', 'PON', 'RPKTNOACL', 'pon口接收正确但被ACL丢弃的包计数', null, null, '81', null, '2', '个', 'received packet but droped by acl in pon');
INSERT INTO `capability` VALUES ('132', 'PON', 'RPKTNOPOL', 'pon口接收正确但被POLICING丢弃的包计数', null, null, '82', null, '2', '个', 'received packet but droped by policy in pon');
INSERT INTO `capability` VALUES ('133', 'PON', 'RPKTNODYWRED', 'pon口接收正确但 被动态水线丢弃 的包计数', null, null, '83', null, '2', '个', 'received packet but droped by dynamic wred in pon');
INSERT INTO `capability` VALUES ('134', 'PON', 'RPKTNOWRED', 'pon口接收正确但 被wred丢弃 的包计数', null, null, '84', null, '2', '个', 'received packet but droped by wred in pon');
INSERT INTO `capability` VALUES ('135', 'PON', 'RBYTENOPOL', 'pon口接收正确，但被POLICING丢弃/FWE无法转发而丢弃/ACL判断为丢弃包的字节计数', null, null, '85', null, '2', '个', 'received octet but droped by wred in pon');
INSERT INTO `capability` VALUES ('136', 'PON', 'TPKT63', 'pon口发送包数长度0-63', null, null, '86', null, '2', '个', 'transmited packet of length 0-63 in pon');
INSERT INTO `capability` VALUES ('137', 'PON', 'TPKT64TO127', 'pon口发送包数长度64-127', null, null, '87', null, '2', '个', 'transmited packet of length 64-127 in pon');
INSERT INTO `capability` VALUES ('138', 'PON', 'TPKT128TO255', 'pon口发送包数长度128-255', null, null, '88', null, '2', '个', 'transmited packet of length 128-255 in pon');
INSERT INTO `capability` VALUES ('139', 'PON', 'TPKT256TO511', 'pon口发送包数长度256-511', null, null, '89', null, '2', '个', 'transmited packet of length 256-511 in pon');
INSERT INTO `capability` VALUES ('140', 'PON', 'TPKT512TO1023', 'pon口发送包数长度512-1023', null, null, '90', null, '2', '个', 'transmited packet of length 512-1023 in pon');
INSERT INTO `capability` VALUES ('141', 'PON', 'TPKT1024TO1535', 'pon口发送包数长度1024-1535', null, null, '91', null, '2', '个', 'transmited packet of length 1024-1535 in pon');
INSERT INTO `capability` VALUES ('142', 'PON', 'TBYTEOK', 'pon口发送正确包的字节计数', null, null, '92', null, '2', '个', 'transmited octet ok in pon');
INSERT INTO `capability` VALUES ('143', 'PON', 'TPKTMC', 'pon口发送组播包计数', null, null, '93', null, '2', '个', 'transmited packet of multicast');
INSERT INTO `capability` VALUES ('144', 'PON', 'TPKTUC', 'pon口发送单播包计数', null, null, '94', null, '2', '个', 'transmited packet of uniicast');
INSERT INTO `capability` VALUES ('145', 'PON', 'TPMPCPDIS', 'pon口发送discoveryb包计数', null, null, '95', null, '2', '个', 'transmited packet of mpcp discovery gate message');
INSERT INTO `capability` VALUES ('146', 'PON', 'TPMPCPREG', 'pon口发送register包计数', null, null, '96', null, '2', '个', 'transmited packet of mpcp register message');
INSERT INTO `capability` VALUES ('147', 'PON', 'TPMPCPREREG', 'pon口发送reregister包计数', null, null, '97', null, '2', '个', 'transmited packet of mpcp reregister message');
INSERT INTO `capability` VALUES ('148', 'PON', 'TPMPCPDEREG', 'pon口发送deregister包计数', null, null, '98', null, '2', '个', 'transmited packet of mpcp deregister message');
INSERT INTO `capability` VALUES ('149', 'PON', 'RPMPCPDIS', 'pon口接收discoveryb包计数', null, null, '99', null, '2', '个', 'received packet of mpcp discovery gate message');
INSERT INTO `capability` VALUES ('150', 'PON', 'RPMPCPREG', 'pon口接收register包计数', null, null, '100', null, '2', '个', 'received packet of mpcp register message');
INSERT INTO `capability` VALUES ('151', 'PON', 'RPMPCPREREG', 'pon口接收reregister包计数', null, null, '101', null, '2', '个', 'received packet of mpcp reregister message');
INSERT INTO `capability` VALUES ('152', 'PON', 'RPMPCPDEREG', 'pon口接收deregister包计数', null, null, '102', null, '2', '个', 'received packet of mpcp deregister message');
INSERT INTO `capability` VALUES ('153', 'PHY', 'LV', '激光器偏置电压', null, null, '103', null, '2', '个', 'laser voltage');
INSERT INTO `capability` VALUES ('154', 'LLID', 'RPDISCARD', '接收正确但被丢弃的包计数', null, null, '104', null, '2', '个', 'received packet but drop by policy or fwe in llid');
INSERT INTO `capability` VALUES ('155', 'LLID', 'RBYTEDISCARD', '接收正确但被丢弃的字节计数', null, null, '105', null, '2', '个', 'received octet but drop by policy or fwe in llid');
INSERT INTO `capability` VALUES ('156', 'LLID', 'RPOAM', 'llid接口接收正确oam包计数', null, null, '106', null, '2', '个', 'received oam packet of oam in llid');
INSERT INTO `capability` VALUES ('157', 'LLID', 'TPNORMALGATE', 'llid接口发送normal gate 的包字节计数', null, null, '107', null, '2', '个', 'transmited byte of normal gate in llid');
INSERT INTO `capability` VALUES ('158', 'LLID', 'RPREPORT', 'llid接口接收到的report 的包字节计数', null, null, '108', null, '2', '个', 'received octet of report in llid');
INSERT INTO `capability` VALUES ('159', 'ETH', 'TPBAD', '发送的坏包数', null, null, '109', null, '2', '个', 'transmited packet bad');
INSERT INTO `capability` VALUES ('160', 'ETH', 'RPBAD', '接收的坏包数', null, null, '110', null, '2', '个', 'received packet bad');
INSERT INTO `capability` VALUES ('161', 'PORT', 'RX_BDPK', '接收坏包数', null, null, '20', null, '1', '个', 'received bad packets number');
INSERT INTO `capability` VALUES ('169', 'PORT', 'TX_FLOW', '发送流量', null, '93', '54', null, '1', 'bps', 'sended traffic');
INSERT INTO `capability` VALUES ('170', 'PORT', 'RX_FLOW', '接收流量', null, '93', '53', null, '1', 'bps', 'received traffic');
INSERT INTO `capability` VALUES ('171', 'PORT', 'RX_64BYTES', '收到的包(64字节)', null, '', '22', '', '1', '个', 'received packet (64 bytes)');
INSERT INTO `capability` VALUES ('172', 'TMP/TMC', 'TMP_RX_CV', '通路接收CV包统计', null, '93', '49', '', '1', '个', 'channel received CV packet statistics');
INSERT INTO `capability` VALUES ('173', 'TMP/TMC', 'TMP_TX_CV', '通路发送CV包统计', null, '93', '50', '', '1', '个', 'channel sended CV packet statistics');
INSERT INTO `capability` VALUES ('174', 'TMS', 'TMS_RX_CV', '段接收CV包统计', null, '93', '51', '', '1', '个', 'segment received the CV packet statistics');
INSERT INTO `capability` VALUES ('175', 'TMS', 'TMS_TX_CV', '段发送CV包统计', null, '93', '52', '', '1', '个', 'segment sended the CV packet statistics');
INSERT INTO `capability` VALUES ('178', 'TMP/TMC', 'TMC_RX_CV', 'PW接收CV包统计', null, '93', '55', '', '1', '个', 'pw received the CV packet statistics');
INSERT INTO `capability` VALUES ('179', 'TMP/TMC', 'TMC_TX_CV', 'PW发送CV包统计', null, '93', '56', '', '1', '个', 'pw sended the CV packet statistics');
INSERT INTO `capability` VALUES ('180', 'TMP/TMC', 'TMP_PACKLOSR_NEAR', 'TMP层LM近端丢包率', null, null, '57', null, '1', '%', 'TMP LM proximal loss packet rate');
INSERT INTO `capability` VALUES ('181', 'TMP/TMC', 'TMP_PACKLOSR_FAR', 'TMP层LM远端丢包率', null, null, '58', null, '1', '%', 'TMP LM remote loss packet rate');
INSERT INTO `capability` VALUES ('184', 'TMS', 'TMS_PACKLOSR_NEAR', 'TMS层LM近端丢包率', null, null, '61', null, '1', '%', 'TMS LM proximal loss packet rate');
INSERT INTO `capability` VALUES ('185', 'TMS', 'TMS_PACKLOSR_FAR', 'TMS层LM远端丢包率', null, null, '62', null, '1', '%', 'TMS LM remote loss packet rate');
INSERT INTO `capability` VALUES ('186', 'TMS', 'TMS_PMPACKDELAY_S', 'TMS层时延秒', null, null, '63', null, '1', 's', 'TMS delay seconds');
INSERT INTO `capability` VALUES ('187', 'TMP/TMC', 'TMP_ACTIVE_PKTLOSE_NEAR', 'TMP层主动LM近端丢包率', null, null, '67', null, '1', '%', 'TMP active proximal LM packet loss rate');
INSERT INTO `capability` VALUES ('188', 'TMP/TMC', 'TMP_ACTIVE_PKTLOSE_FAR', 'TMP层主动LM远端丢包率', null, null, '68', null, '1', '%', 'TMP active remote LM packet loss rate');
INSERT INTO `capability` VALUES ('189', 'TMS', 'TMS_PMPACKDELAY_NS', 'TMS层时延纳秒', null, null, '64', null, '1', 'ns', 'TMS delay nanosecond');
INSERT INTO `capability` VALUES ('190', 'TMP/TMC', 'TMC_PACKLOSR_NEAR', 'TMC层LM近端丢包率', null, null, '65', null, '1', '%', 'TMC LM proximal loss packet rate');
INSERT INTO `capability` VALUES ('191', 'TMP/TMC', 'TMC_PACKLOSR_FAR', 'TMC层LM远端丢包率', null, '', '66', '', '1', '%', 'TMC LM remote loss packet rate');
INSERT INTO `capability` VALUES ('192', 'TMP/TMC', 'TMC_PMPACKDELAY_S', 'TMC时延秒', null, null, '69', null, '1', 's', 'TMC delay second');
INSERT INTO `capability` VALUES ('193', 'TMP/TMC', 'TMC_PMPACKDELAY_NS', 'TMC时延纳秒', null, null, '70', null, '1', 'ns', 'TMC delay nanosecond');
INSERT INTO `capability` VALUES ('194', 'PORT', 'RX_10243BYTES', '收到的包（512-1023字节）', null, null, '30', null, '1', '个', 'received packet (512-1023 bytes)');
INSERT INTO `capability` VALUES ('195', 'TMP/TMC', 'TMPACKDELAY_S', 'TMP层时延秒', null, null, '59', null, '1', 's', 'TMP delay second');
INSERT INTO `capability` VALUES ('196', 'TMP/TMC', 'TMPACKDELAY_NS', 'TMP层时延纳秒', null, null, '60', null, '1', 'ns', 'TMP delay nanosecond');
INSERT INTO `capability` VALUES ('197', 'PORT', 'SFPTxPOW', '光模块发射功率', null, null, '80', null, '1', 'dbm', 'optical module transmittion power');
INSERT INTO `capability` VALUES ('198', 'PORT', 'SFPRxPOW', '光模块接收功率', null, null, '81', null, '1', 'dbm', 'optical module received power');
INSERT INTO `capability` VALUES ('199', 'PORT', 'SFPTemp', '光模块温度', null, null, '82', null, '1', 'C', 'optical module temperature');
INSERT INTO `capability` VALUES ('201', 'PORT', 'SFPVcc', '光模块工作电压', null, null, '83', null, '1', 'V', 'optical module working voltage');
INSERT INTO `capability` VALUES ('202', 'PORT', 'SFPLaserBias', '激光器偏流', null, null, '84', null, '1', 'mA', 'laser bias');
INSERT INTO `capability` VALUES ('203', 'PORT', 'CiTemperature', '设备温度（C）', null, null, '85', null, '1', 'C', 'equipment temperature (C)');
INSERT INTO `capability` VALUES ('204', 'PORT', 'SFPLength', '光模块传输距离', null, '', '87', null, '1', 'km', 'optical module transmission distance');
INSERT INTO `capability` VALUES ('205', 'PORT', 'SFPSpeed', '光模块速率', null, '', '86', null, '1', 'MB/s', 'optical module rate');
INSERT INTO `capability` VALUES ('206', 'PORT', 'SFPSensitivity', '光模块接收灵敏度', null, '93', '89', null, '1', 'dbm', 'optical module receiving sensitivity');
INSERT INTO `capability` VALUES ('207', 'PORT', 'SFPWavelength', '光模块波长', null, null, '88', null, '1', 'nm', 'optical module wavelength');
INSERT INTO `capability` VALUES ('208', 'PORT', 'SFPAlarmTemp', '光模块告警温度', null, null, '90', null, '1', 'C', 'optical module alarm temperature');
INSERT INTO `capability` VALUES ('209', 'PORT', 'RxRatio', '接收带宽利用率', null, null, '96', null, '1', '%', 'received bandwidth utilization');
INSERT INTO `capability` VALUES ('210', 'PORT', 'TxRatio', '发送带宽利用率', null, null, '97', null, '1', '%', 'sended bandwidth utilization');
INSERT INTO `capability` VALUES ('211', 'PORT', 'SFPModel', '光模块型号', null, null, '98', null, '1', ' ', 'optical module type');
INSERT INTO `capability` VALUES ('212', 'PORT', 'SFPConnectorType', '连接器类型', null, null, '99', null, '1', ' ', 'connector type');
INSERT INTO `capability` VALUES ('213', 'PORT', 'SFPTransMedia', '传输介质', null, null, '100', null, '1', ' ', 'transmission medium');
INSERT INTO `capability` VALUES ('214', 'PORT', 'RX_UNICAST', '接收单播包数', null, null, '101', null, '1', '个', 'received unicast packet number');
INSERT INTO `capability` VALUES ('215', 'PORT', 'TX_UNICAST', '发送单播包数', null, null, '102', null, '1', '个', 'sended unicast packet number');
INSERT INTO `capability` VALUES ('216', 'PORT', 'RX_BYTEUNICAST', '接收单播字节数', null, null, '103', null, '1', 'KB', 'received unicast bytes');
INSERT INTO `capability` VALUES ('217', 'PORT', 'TX_BYTEUNICAST', '发送单播字节数', null, null, '104', null, '1', 'KB', 'sended unicast bytes');
INSERT INTO `capability` VALUES ('218', 'TMP/TMC', 'TMP_RX_PACKS', 'TMP接收包数', null, null, '105', null, '1', '个', 'TMP received packets number');
INSERT INTO `capability` VALUES ('219', 'TMP/TMC', 'TMP_TX_PACKS', 'TMP发送包数', null, null, '106', null, '1', '个', 'TMP sended packets number');
INSERT INTO `capability` VALUES ('220', 'TMP/TMC', 'TMP_RX_BYTES', 'TMP接收字节数', null, null, '107', null, '1', 'KB', 'TMP received bytes number');
INSERT INTO `capability` VALUES ('221', 'TMP/TMC', 'TMP_TX_BYTES', 'TMP发送字节数', null, null, '108', null, '1', 'KB', 'TMP sended bytes number');
INSERT INTO `capability` VALUES ('222', 'TMP/TMC', 'TMC_RX_PACKS', 'TMC接收包数', null, null, '109', null, '1', '个', 'TMC received packets number');
INSERT INTO `capability` VALUES ('223', 'TMP/TMC', 'TMC_TX_PACKS', 'TMC发送包数', null, null, '110', null, '1', '个', 'TMC sended packets number');
INSERT INTO `capability` VALUES ('224', 'TMP/TMC', 'TMC_RX_BYTES', 'TMC接收字节数', null, null, '111', null, '1', 'KB', 'TMC received bytes number');
INSERT INTO `capability` VALUES ('225', 'TMP/TMC', 'TMC_TX_BYTES', 'TMC发送字节数', null, null, '112', null, '1', 'KB', 'TMC sended bytes number');
INSERT INTO `capability` VALUES ('226', 'PORT', '2M_ES', '2M误码秒', null, null, '128', null, '1', 's', '2M error seconds\r\n\r\n2M error seconds\r\n\r\n2M error seconds');
INSERT INTO `capability` VALUES ('227', 'PORT', '2M_SES', '2M严重误码秒', null, null, '129', null, '1', 's', '2M severely errored seconds');
INSERT INTO `capability` VALUES ('228', 'PORT', '2M_BBE', '背景误码块', null, null, '130', null, '1', 's', 'background block error');
INSERT INTO `capability` VALUES ('229', 'PORT', '2M_UAS', '不可用秒', null, null, '131', null, '1', 's', 'unavailable seconds');
INSERT INTO `capability` VALUES ('230', 'TMS', 'TMS_TX_LBM', 'TMS层LBM发送计数', null, null, '132', null, '1', '个', 'TMS LBM send count');
INSERT INTO `capability` VALUES ('231', 'TMS', 'TMS_RX_LBM', 'TMS层LBM接收计数', null, null, '133', null, '1', '个', 'TMS LBM receive count');
INSERT INTO `capability` VALUES ('232', 'TMS', 'TMS_TX_LBR', 'TMS层LBR发送计数', null, null, '134', null, '1', '个', 'TMS LBR send count');
INSERT INTO `capability` VALUES ('233', 'TMS', 'TMS_RX_LBR', 'TMS层LBR接收计数', null, null, '135', null, '1', '个', 'TMS LBR receive count');
INSERT INTO `capability` VALUES ('234', 'TMP/TMC', 'TMP_TX_LBM', 'TMP层LBM发送计数', null, null, '136', null, '1', '个', 'TMP LBM send count');
INSERT INTO `capability` VALUES ('235', 'TMP/TMC', 'TMP_RX_LBM', 'TMP层LBM接收计数', null, null, '137', null, '1', '个', 'TMP LBM receive count');
INSERT INTO `capability` VALUES ('236', 'TMP/TMC', 'TMP_TX_LBR', 'TMP层LBR发送计数', null, null, '138', null, '1', '个', 'TMP LBR send count');
INSERT INTO `capability` VALUES ('237', 'TMP/TMC', 'TMP_RX_LBR', 'TMP层LBR接收计数', null, null, '139', null, '1', '个', 'TMP LBR receive count');
INSERT INTO `capability` VALUES ('238', 'TMP/TMC', 'TMC_TX_LBM', 'TMC层LBM发送计数', null, null, '140', null, '1', '个', 'TMC LBM send count');
INSERT INTO `capability` VALUES ('239', 'TMP/TMC', 'TMC_RX_LBM', 'TMC层LBM接收计数', null, null, '141', null, '1', '个', 'TMC LBM receive count');
INSERT INTO `capability` VALUES ('240', 'TMP/TMC', 'TMC_TX_LBR', 'TMC层LBR发送计数', null, null, '142', null, '1', '个', 'TMC LBR send count');
INSERT INTO `capability` VALUES ('241', 'TMP/TMC', 'TMC_RX_LBR', 'TMC层LBR接收计数', null, null, '143', null, '1', '个', 'TMC LBR receive count');
INSERT INTO `capability` VALUES ('242', '1731', 'Eth_PackDelay_ms', '以太网oam时延', null, null, '144', null, '1', 'ms', 'ETH OAM delay');
INSERT INTO `capability` VALUES ('243', '1731', 'EthPackDelay_Shake_ms', '以太网oam时延抖动', null, null, '145', null, '1', 'ms', 'ETH OAM jitter');
INSERT INTO `capability` VALUES ('244', 'TMS', 'TMS_DMVAR_S', 'TMS层时延变化秒', null, null, '146', null, '1', 's', 'TMS delay variation seconds');
INSERT INTO `capability` VALUES ('245', 'TMS', 'TMS_DMVAR_NS', 'TMS层时延变化纳秒', null, null, '152', null, '1', 'ns', 'TMS delay variation nanosecond');
INSERT INTO `capability` VALUES ('246', 'TMP/TMC', 'TMP_DMVAR_S', 'TMP层时延变化秒', '', null, '148', null, '1', 's', 'TMP delay variation second');
INSERT INTO `capability` VALUES ('247', 'TMP/TMC', 'TMP_DMVAR_NS', 'TMP层时延变化纳秒', null, null, '149', null, '1', 'ns', 'TMP delay variation nanosecond');
INSERT INTO `capability` VALUES ('248', 'TMP/TMC', 'TMC_DMVAR_S', 'TMC层时延变化秒', null, null, '150', null, '1', 's', 'TMC delay variation second');
INSERT INTO `capability` VALUES ('249', 'TMP/TMC', 'TMC_DMVAR_NS', 'TMC层时延变化纳秒', null, null, '151', null, '1', 'ns', 'TMC delay variation nanosecond');
INSERT INTO `capability` VALUES ('250', 'PORT', 'Voltage', '设备电压', null, null, '153', null, '1', 'v', 'device voltage');
INSERT INTO `capability` VALUES ('251', '1731', 'Eth_PackDelay_s', '以太网oam时延秒', null, null, '154', null, '1', 's', 'ETH OAM delay seconds');
INSERT INTO `capability` VALUES ('252', '1731', 'Eth_PackDelay_ns', '以太网oam时延纳秒', null, null, '155', null, '1', 'ns', 'ETH OAM delay nanosecond');
INSERT INTO `capability` VALUES ('253', '1731', 'Eth_PackDelay_Shake_s', '以太网oam时延抖动秒', null, null, '156', null, '1', 's', 'ETH OAM delay jitter seconds');
INSERT INTO `capability` VALUES ('254', '1731', 'Eth_PackDelay_Shake_ns', '以太网oam时延抖动纳秒', null, null, '157', null, '1', 'ns', 'ETH OAM delay jitter nanosecond');
INSERT INTO `capability` VALUES ('255', '1731', 'Eth_Pack_Los_Near', '以太网oam近端丢包率', null, null, '159', null, '1', '%', 'ETH OAM proximal loss packets rate');
INSERT INTO `capability` VALUES ('256', '1731', 'Eth_Pack_Los_Far', '以太网oam远端丢包率', null, null, '158', null, '1', '%', 'ETH OAM remote loss packets rate');
INSERT INTO `capability` VALUES ('257', 'PORT', '2M_E1_HDB3_ERR', 'HDB3误码统计', null, null, '113', null, '1', '个', 'HDB3 error statistics');
INSERT INTO `capability` VALUES ('258', 'PORT', 'CardSlot2Temp', '槽位2支路卡温度（C）', '', '', '114', '', '1', 'C', 'slot 2 branch card temperature (C)');
INSERT INTO `capability` VALUES ('259', 'PORT', 'CardSlot3Temp', '槽位3支路卡温度（C）', '', '', '115', '', '1', 'C', 'slot 3 branch card temperature (C)');
INSERT INTO `capability` VALUES ('260', 'PORT', 'CpuRatio', 'cpu利用率', '', '', '116', '', '1', '%', 'CPU utilization');

-- ----------------------------
-- Table structure for `card_inst`
-- ----------------------------
DROP TABLE IF EXISTS `card_inst`;
CREATE TABLE `card_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `equipId` int(11) DEFAULT NULL,
  `slotId` int(11) DEFAULT NULL,
  `cardName` varchar(200) DEFAULT NULL,
  `cardType` varchar(200) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  `cardx` int(11) DEFAULT NULL,
  `cardy` int(11) DEFAULT NULL,
  `snmpName` varchar(200) DEFAULT NULL,
  `installedSerialNumber` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `client`
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `adress` varchar(200) DEFAULT NULL,
  `phoneNumber` varchar(200) DEFAULT NULL,
  `linkMan` varchar(200) DEFAULT NULL,
  `area` varchar(200) DEFAULT NULL,
  `grade` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------

-- ----------------------------
-- Table structure for `clock_freque`
-- ----------------------------
DROP TABLE IF EXISTS `clock_freque`;
CREATE TABLE `clock_freque` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `workMode` int(11) DEFAULT NULL,
  `qlEnable` int(11) DEFAULT NULL,
  `clockPRI` varchar(255) DEFAULT '',
  `outInselect` int(11) DEFAULT NULL,
  `outOutselect1` int(11) DEFAULT NULL,
  `outOutselect2` int(11) DEFAULT NULL,
  `outSelect` varchar(255) DEFAULT NULL,
  `smOuter` int(11) DEFAULT NULL,
  `smSystem` int(11) DEFAULT NULL,
  `qlIn` int(11) DEFAULT NULL,
  `qlOut` int(11) DEFAULT NULL,
  `inQLout` int(11) DEFAULT NULL,
  `inQLvalue` varchar(255) DEFAULT NULL,
  `outQLvalue` varchar(255) DEFAULT NULL,
  `InResumeTime` int(11) DEFAULT NULL,
  `waitResumeTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clock_freque
-- ----------------------------

-- ----------------------------
-- Table structure for `code`
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codeGroupId` int(11) DEFAULT NULL,
  `codeValue` varchar(200) DEFAULT NULL,
  `codeName` varchar(200) DEFAULT NULL,
  `orderby` int(11) DEFAULT NULL,
  `codeENName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=718 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('22', '7', null, 'ComboBox', '0', 'ComboBox');
INSERT INTO `code` VALUES ('23', '7', null, 'CheckBox', '0', 'CheckBox');
INSERT INTO `code` VALUES ('24', '7', null, 'TextArea', '0', 'TextArea');
INSERT INTO `code` VALUES ('27', '9', '0', '未使能', '0', 'Disable');
INSERT INTO `code` VALUES ('28', '9', '1', '使能', '0', 'Enable');
INSERT INTO `code` VALUES ('30', '10', null, 'Data Trans 700B', '0', 'Data Trans 700B');
INSERT INTO `code` VALUES ('32', '10', null, 'Data Trans 700D', '0', 'Data Trans 700D');
INSERT INTO `code` VALUES ('33', '12', '0', '700+系列', '0', '700+Series');
INSERT INTO `code` VALUES ('34', '12', '1', '700系列', '0', '700 Series');
INSERT INTO `code` VALUES ('35', '13', null, 'GMT+08:00', '0', 'GMT+08:00');
INSERT INTO `code` VALUES ('43', '24', '0', '不使能', '0', 'Disable');
INSERT INTO `code` VALUES ('44', '15', null, 'admin', '0', 'Admin');
INSERT INTO `code` VALUES ('45', '15', null, 'guest', '0', 'Guest');
INSERT INTO `code` VALUES ('46', '16', null, '1MB', '0', '1MB');
INSERT INTO `code` VALUES ('47', '16', null, '5MB', '0', '5MB');
INSERT INTO `code` VALUES ('48', '16', null, '10MB', '0', '10MB');
INSERT INTO `code` VALUES ('50', '18', '0', '不关联', '0', 'Non-Associate');
INSERT INTO `code` VALUES ('51', '18', '1', '关联', '0', 'Associate');
INSERT INTO `code` VALUES ('54', '19', '0', '端口', '0', 'Port');
INSERT INTO `code` VALUES ('55', '19', '1', '端口+运营商VLAN模式', '0', 'Port+Operator VLAN Mode');
INSERT INTO `code` VALUES ('57', '20', '0', '指配PHB', '0', 'Assign PHB');
INSERT INTO `code` VALUES ('58', '20', '1', '基于用户优先级到PHB映射', '0', 'Based on user priority to PHB mapping');
INSERT INTO `code` VALUES ('59', '21', '0', 'BE', '0', 'BE');
INSERT INTO `code` VALUES ('60', '21', '1', 'AF1', '0', 'AF1');
INSERT INTO `code` VALUES ('61', '21', '2', 'AF2', '0', 'AF2');
INSERT INTO `code` VALUES ('62', '21', '3', 'AF3', '0', 'AF3');
INSERT INTO `code` VALUES ('63', '21', '4', 'AF4', '0', 'AF4');
INSERT INTO `code` VALUES ('64', '21', '5', 'EF', '0', 'EF');
INSERT INTO `code` VALUES ('65', '21', '6', 'CS6', '0', 'CS6');
INSERT INTO `code` VALUES ('66', '21', '7', 'CS7', '0', 'CS7');
INSERT INTO `code` VALUES ('68', '22', '0', 'color-blind', '0', 'Color-blind');
INSERT INTO `code` VALUES ('69', '22', '1', 'color-aware', '0', 'Color-aware');
INSERT INTO `code` VALUES ('70', '23', '0', '自动协商', '0', 'Auto-negotiation');
INSERT INTO `code` VALUES ('71', '23', '1', '1000M全双工', '0', '1000M Full Duplex');
INSERT INTO `code` VALUES ('72', '24', '1', '使能', '0', 'Enable');
INSERT INTO `code` VALUES ('74', '6', '1', 'lag', '0', 'Lag');
INSERT INTO `code` VALUES ('76', '25', '0', 'NOP', '0', 'NOP');
INSERT INTO `code` VALUES ('77', '25', '1', '增加VLAN', '0', 'Add');
INSERT INTO `code` VALUES ('78', '25', '2', '删除VLAN', '0', 'Delete');
INSERT INTO `code` VALUES ('79', '26', '0', '不识别', '0', 'Unknown');
INSERT INTO `code` VALUES ('80', '26', '1', '识别', '0', 'Known');
INSERT INTO `code` VALUES ('81', '27', '0', '禁止', '0', 'Forbid');
INSERT INTO `code` VALUES ('82', '27', '1', '使能', '0', 'Enable');
INSERT INTO `code` VALUES ('85', '29', '0', '关', '0', 'Off');
INSERT INTO `code` VALUES ('86', '29', '1', '开', '0', 'On');
INSERT INTO `code` VALUES ('87', '30', '0', '3.33', '0', '3.33');
INSERT INTO `code` VALUES ('88', '30', '1', '10', '0', '10');
INSERT INTO `code` VALUES ('89', '30', '2', '100', '0', '100');
INSERT INTO `code` VALUES ('90', '30', '3', '1000', '0', '1000');
INSERT INTO `code` VALUES ('93', '32', '0', 'Agent', '0', 'A');
INSERT INTO `code` VALUES ('94', '32', '1', 'Manage', '0', 'M');
INSERT INTO `code` VALUES ('97', '33', '0', 'L2', '0', 'L2');
INSERT INTO `code` VALUES ('100', '34', 'NONE', 'NONE', '0', 'NONE');
INSERT INTO `code` VALUES ('101', '34', 'UNI', 'UNI', '0', 'UNI');
INSERT INTO `code` VALUES ('102', '34', 'NNI', 'NNI', '0', 'NNI');
INSERT INTO `code` VALUES ('103', '35', '0', '支路1', '0', 'Branch 1');
INSERT INTO `code` VALUES ('104', '35', '1', '支路2', '0', 'Branch 2');
INSERT INTO `code` VALUES ('105', '35', '2', '支路3', '0', 'Branch 3');
INSERT INTO `code` VALUES ('106', '35', '3', '支路4', '0', 'Branch 4');
INSERT INTO `code` VALUES ('107', '35', '4', '支路5', '0', 'Branch 5');
INSERT INTO `code` VALUES ('108', '35', '5', '支路6', '0', 'Branch 6');
INSERT INTO `code` VALUES ('109', '35', '6', '支路7', '0', 'Branch 7');
INSERT INTO `code` VALUES ('110', '35', '7', '支路8', '0', 'Branch 8');
INSERT INTO `code` VALUES ('111', '35', '8', '支路9', '0', 'Branch 9');
INSERT INTO `code` VALUES ('112', '35', '9', '支路10', '0', 'Branch 10');
INSERT INTO `code` VALUES ('113', '35', '10', '支路11', '0', 'Branch 11');
INSERT INTO `code` VALUES ('114', '35', '11', '支路12', '0', 'Branch 12');
INSERT INTO `code` VALUES ('115', '35', '12', '支路13', '0', 'Branch 13');
INSERT INTO `code` VALUES ('116', '35', '13', '支路14', '0', 'Branch 14');
INSERT INTO `code` VALUES ('117', '35', '14', '支路15', '0', 'Branch 15');
INSERT INTO `code` VALUES ('118', '35', '15', '支路16', '0', 'Branch 16');
INSERT INTO `code` VALUES ('119', '36', '1', '自适应时钟恢复', '0', 'Adaptive Clock Recovery');
INSERT INTO `code` VALUES ('120', '36', '2', '差分时钟恢复', '0', 'Differential Clock Recovery');
INSERT INTO `code` VALUES ('121', '36', '3', '系统时钟恢复', '0', 'System Clock Recovery');
INSERT INTO `code` VALUES ('124', '38', 'Unequipped', 'Unequipped', '0', 'Unequipped');
INSERT INTO `code` VALUES ('125', '38', 'Equipped', 'Equipped', '0', 'Equipped');
INSERT INTO `code` VALUES ('126', '38', 'Async', 'Async', '0', 'Async');
INSERT INTO `code` VALUES ('127', '38', 'Bit Sync', 'Bit Sync', '0', 'Bit Sync');
INSERT INTO `code` VALUES ('128', '38', 'Byte Sync', 'Byte Sync', '0', 'Byte Sync');
INSERT INTO `code` VALUES ('129', '38', 'Reserved', 'Reserved', '0', 'Reserved');
INSERT INTO `code` VALUES ('130', '38', 'O.181 Mapping', 'O.181 Mapping', '0', 'O.181 Mapping');
INSERT INTO `code` VALUES ('131', '38', 'VC-AIS', 'VC-AIS', '0', 'VC-AIS');
INSERT INTO `code` VALUES ('132', '39', '1', '速率适配', '0', 'Rate Adaptation');
INSERT INTO `code` VALUES ('133', '39', '1', '未知', '0', 'Unknown');
INSERT INTO `code` VALUES ('134', '39', '1', '未装载', '0', 'Not Loaded');
INSERT INTO `code` VALUES ('135', '39', '1', 'S_1_1', '0', 'S_1_1');
INSERT INTO `code` VALUES ('136', '39', '1', 'L_1_1', '0', 'L_1_1');
INSERT INTO `code` VALUES ('137', '39', '1', 'L_1_2', '0', 'L_1_2');
INSERT INTO `code` VALUES ('138', '40', '0', 'HDB3', '0', 'HDB3');
INSERT INTO `code` VALUES ('139', '40', '2', 'AMI', '0', 'AMI');
INSERT INTO `code` VALUES ('140', '41', '3', 'SDH', '0', 'SDH');
INSERT INTO `code` VALUES ('141', '41', '5', 'SDH-PDH', '0', 'SDH-PDH');
INSERT INTO `code` VALUES ('142', '41', '2', 'PDH', '0', 'PDH');
INSERT INTO `code` VALUES ('143', '41', '4', 'PDH-SDH', '0', 'PDH-SDH');
INSERT INTO `code` VALUES ('144', '33', '1', 'L3', '0', 'L3');
INSERT INTO `code` VALUES ('145', '33', '2', 'vlanpri', '0', 'vlanpri');
INSERT INTO `code` VALUES ('146', '42', 'SEGMENT', '光纤拓扑视图', '0', 'Togplogy View Of Fiber');
INSERT INTO `code` VALUES ('147', '42', 'TUNNEL', 'Tunnel服务路径拓扑视图', '0', 'Topology View Of Tunnel Trail');
INSERT INTO `code` VALUES ('148', '42', 'PW', 'PW服务路径拓扑视图', '0', 'Topology View Of PW Trail');
INSERT INTO `code` VALUES ('149', '42', 'ELINE', 'ELine业务拓扑视图', '0', 'Topology View Of ELine Service');
INSERT INTO `code` VALUES ('150', '42', 'ETREE', 'ETree业务拓扑视图', '0', 'Topology View Of ETree Service');
INSERT INTO `code` VALUES ('151', '42', 'ELAN', 'ELan业务拓扑视图', '0', 'Topology View Of ELan Service');
INSERT INTO `code` VALUES ('152', '42', 'CES', 'CES业务拓扑视图', '0', 'Topology View Of CES Service');
INSERT INTO `code` VALUES ('153', '43', 'standard', 'standard', '0', 'Standard');
INSERT INTO `code` VALUES ('154', '43', 'ibm', 'ibm', '0', 'IBM');
INSERT INTO `code` VALUES ('155', '43', 'cisco', 'cisco', '0', 'Cisco');
INSERT INTO `code` VALUES ('156', '43', 'shortcut', 'shortcut', '0', 'Shortcut');
INSERT INTO `code` VALUES ('157', '44', '0', 'NONE', '0', 'NONE');
INSERT INTO `code` VALUES ('158', '45', 'ingress', 'INGRESS', '0', 'INGRESS');
INSERT INTO `code` VALUES ('159', '45', 'egress', 'EGRESS', '0', 'EGRESS');
INSERT INTO `code` VALUES ('160', '45', 'xc', 'XC', '0', 'XC');
INSERT INTO `code` VALUES ('161', '46', '1', 'PWETH', '0', 'PWETH');
INSERT INTO `code` VALUES ('162', '46', '2', 'PWPDH', '0', 'PWPDH');
INSERT INTO `code` VALUES ('163', '46', '3', 'PWSDH', '0', 'PWSDH');
INSERT INTO `code` VALUES ('164', '47', '2', 'PDH', '0', 'PDH');
INSERT INTO `code` VALUES ('165', '47', '3', 'SDH', '0', 'SDH');
INSERT INTO `code` VALUES ('166', '48', 'mcn', 'MCN', '0', 'MCN');
INSERT INTO `code` VALUES ('167', '48', 'static', 'STATIC', '0', 'STATIC');
INSERT INTO `code` VALUES ('168', '48', 'default', 'DEFAULT', '0', 'DEFAULT');
INSERT INTO `code` VALUES ('169', '49', 'rib', 'RIB', '0', 'RIB');
INSERT INTO `code` VALUES ('170', '49', 'always', 'ALWAYS', '0', 'ALWAYS');
INSERT INTO `code` VALUES ('171', '50', 'type1', 'TYPE1', '0', 'TYPE1');
INSERT INTO `code` VALUES ('172', '50', 'type2', 'TYPE2', '0', 'TYPE2');
INSERT INTO `code` VALUES ('173', '51', 'root', '根', '0', 'Root');
INSERT INTO `code` VALUES ('174', '51', 'branch', '叶子', '0', 'Leaf');
INSERT INTO `code` VALUES ('175', '52', 'ptp', '点对点', '0', 'Point-to-point');
INSERT INTO `code` VALUES ('176', '52', 'broadcast', '广播', '0', 'Broadcast');
INSERT INTO `code` VALUES ('177', '53', '0', 'NONE', '0', 'NONE');
INSERT INTO `code` VALUES ('178', '53', '1', 'SIMPLE', '0', 'SIMPLE');
INSERT INTO `code` VALUES ('179', '53', '2', 'MD5', '0', 'MD5');
INSERT INTO `code` VALUES ('180', '6', '1', '名称', '0', 'Name');
INSERT INTO `code` VALUES ('185', '17', '1', '普通', '0', 'Normal');
INSERT INTO `code` VALUES ('186', '17', '2', '1:1', '0', '1:1');
INSERT INTO `code` VALUES ('187', '57', '0', '1:1保护', '0', '1:1 protection');
INSERT INTO `code` VALUES ('188', '57', '1', '依据源MAC地址方法报文', '0', 'Packets Based on source MAC address');
INSERT INTO `code` VALUES ('189', '57', '2', '依据目的MAC地址方法报文', '0', 'Packets based on the destination MAC address');
INSERT INTO `code` VALUES ('190', '57', '3', '依据源和目的MAC地址方法报文', '0', 'Packets based on source and destination MAC addresses');
INSERT INTO `code` VALUES ('191', '57', '4', '依据源IP地址方法报文', '0', 'Packets based on source IP address');
INSERT INTO `code` VALUES ('192', '57', '5', '依据目的IP地址方法报文', '0', 'Packets based on the destination IP address');
INSERT INTO `code` VALUES ('193', '57', '6', '依据源和目的IP地址方法报文', '0', 'Packets based on source and destination IP address');
INSERT INTO `code` VALUES ('194', '58', '1', '1q', '0', '1Q');
INSERT INTO `code` VALUES ('195', '58', '2', '1ad', '0', '1AD');
INSERT INTO `code` VALUES ('196', '59', '0', '0x8100', '0', '0x8100');
INSERT INTO `code` VALUES ('197', '59', '1', '0x88a8', '0', '0x88a8');
INSERT INTO `code` VALUES ('198', '59', '3', '0x9100', '0', '0x9100');
INSERT INTO `code` VALUES ('199', '60', '1', '0x88a8', '0', '0x88a8');
INSERT INTO `code` VALUES ('200', '60', '3', '0x9100', '0', '0x9100');
INSERT INTO `code` VALUES ('201', '61', '1', 'ACCESS', '0', 'ACCESS');
INSERT INTO `code` VALUES ('202', '61', '2', 'TRUNK', '0', 'TRUNK');
INSERT INTO `code` VALUES ('203', '61', '3', 'HYBRID', '0', 'HYBRID');
INSERT INTO `code` VALUES ('204', '62', '0', '优先级', '0', 'Priority');
INSERT INTO `code` VALUES ('205', '62', '1', 'SSM', '0', 'SSM');
INSERT INTO `code` VALUES ('206', '63', '0', '自动', '0', 'auto');
INSERT INTO `code` VALUES ('207', '63', '1', '自由振荡', '0', 'Free Oscillations');
INSERT INTO `code` VALUES ('208', '63', '2', '保持', '0', 'keep');
INSERT INTO `code` VALUES ('209', '64', '0', 'G_811时钟', '0', 'G_811 Clock');
INSERT INTO `code` VALUES ('210', '64', '1', 'G_812转接时钟', '0', 'G_812 TransitClock');
INSERT INTO `code` VALUES ('211', '64', '2', 'G_812本地时钟', '0', 'G_812 Local Clock');
INSERT INTO `code` VALUES ('212', '64', '3', 'G_813时钟(SETS)', '0', 'G_813 Clock（SETS）');
INSERT INTO `code` VALUES ('213', '65', '0', 'G_811时钟', '0', 'G_811 Clock');
INSERT INTO `code` VALUES ('214', '65', '1', 'G_812转接时钟', '0', 'G_812 TransitClock');
INSERT INTO `code` VALUES ('215', '65', '2', 'G_812本地时钟', '0', 'G_812 Local Clock');
INSERT INTO `code` VALUES ('216', '65', '3', 'G_813时钟(SETS)', '0', 'G_813 Clock（SETS）');
INSERT INTO `code` VALUES ('217', '65', '4', '不可用作同步', '0', 'Non Synchronous');
INSERT INTO `code` VALUES ('218', '66', '0', 'SETG', '0', 'SETG');
INSERT INTO `code` VALUES ('219', '66', '1', '选择器', '0', 'Selector');
INSERT INTO `code` VALUES ('220', '67', '0', 'G_811时钟', '0', 'G_811 Clock');
INSERT INTO `code` VALUES ('221', '67', '1', 'G_812转接时钟', '0', 'G_812 TransitClock');
INSERT INTO `code` VALUES ('222', '67', '2', 'G_812本地时钟', '0', 'G_812 Local Clock');
INSERT INTO `code` VALUES ('223', '67', '3', 'G_813时钟(SETS)', '0', 'G_813 Clock（SETS）');
INSERT INTO `code` VALUES ('224', '67', '4', '不可用作同步', '0', 'Non Synchronous');
INSERT INTO `code` VALUES ('225', '68', '1', 'TDM信号中恢复', '0', 'Restore From TDM');
INSERT INTO `code` VALUES ('226', '68', '2', '仿真业务中恢复', '0', 'EmRestore From CES');
INSERT INTO `code` VALUES ('227', '69', '255', '实际线路接受值', '0', 'The acceptable value of actual line');
INSERT INTO `code` VALUES ('228', '69', '1', 'G_811时钟', '0', 'G_811 Clock');
INSERT INTO `code` VALUES ('229', '69', '2', 'G_812转接时钟', '0', 'G_812 TransitClock');
INSERT INTO `code` VALUES ('230', '69', '3', 'G_812本地时钟', '0', 'G_812 Local Clock');
INSERT INTO `code` VALUES ('231', '69', '4', 'G_813时钟(SETS)', '0', 'G_813 Clock（SETS）');
INSERT INTO `code` VALUES ('232', '69', '5', '不可用做同步', '0', 'Can Not Synchronous');
INSERT INTO `code` VALUES ('233', '70', '1', '优先级', '0', 'Priority');
INSERT INTO `code` VALUES ('234', '70', '1', 'BMC', '0', 'BMC');
INSERT INTO `code` VALUES ('235', '71', '1', '端到端', '0', 'End To End');
INSERT INTO `code` VALUES ('236', '71', '2', '点到点', '0', 'Point To Point');
INSERT INTO `code` VALUES ('237', '72', '1', 'Not Slave Only', '0', 'Not Slave Only');
INSERT INTO `code` VALUES ('238', '72', '2', 'Slave Only', '0', 'Slave Only');
INSERT INTO `code` VALUES ('239', '73', '1', '时间信息/消息', '0', 'Time Info/Message');
INSERT INTO `code` VALUES ('240', '73', '2', '时间信息/状态信息', '0', 'Time Info/Status Info');
INSERT INTO `code` VALUES ('241', '74', '1', '透传', '0', 'Pass Through');
INSERT INTO `code` VALUES ('242', '74', '2', '非透传', '0', 'Non Pass Through');
INSERT INTO `code` VALUES ('243', '75', '1', 'One Step', '0', 'One Step');
INSERT INTO `code` VALUES ('244', '75', '2', 'Two Step', '0', 'Two Step');
INSERT INTO `code` VALUES ('245', '76', '1', '端到端', '0', 'End to End');
INSERT INTO `code` VALUES ('246', '76', '2', '点到点', '0', 'Point to Point');
INSERT INTO `code` VALUES ('247', '78', '1', '被动状态', '0', 'Passive Status');
INSERT INTO `code` VALUES ('248', '78', '2', '主状态', '0', 'Master Status');
INSERT INTO `code` VALUES ('249', '78', '3', '从状态', '0', 'Slave Status');
INSERT INTO `code` VALUES ('250', '78', '4', '自动选源', '0', 'Automatic Source Selection');
INSERT INTO `code` VALUES ('251', '79', '-4', '1/16', '0', '1/16');
INSERT INTO `code` VALUES ('252', '79', '-3', '1/8', '0', '1/8');
INSERT INTO `code` VALUES ('253', '79', '-2', '1/4', '0', '1/4');
INSERT INTO `code` VALUES ('254', '79', '-1', '1/2', '0', '1/2');
INSERT INTO `code` VALUES ('255', '79', '0', '1', '0', '1');
INSERT INTO `code` VALUES ('256', '79', '1', '2', '0', '2');
INSERT INTO `code` VALUES ('257', '79', '2', '4', '0', '4');
INSERT INTO `code` VALUES ('258', '79', '3', '8', '0', '8');
INSERT INTO `code` VALUES ('259', '79', '4', '16', '0', '16');
INSERT INTO `code` VALUES ('264', '83', '1g', '1G', '0', '1G');
INSERT INTO `code` VALUES ('265', '83', 'neg1g', '自协商1G', '0', 'Auto-negotiation 1G');
INSERT INTO `code` VALUES ('266', '80', '0', '未知', '0', 'Unknown');
INSERT INTO `code` VALUES ('267', '80', '1', '未装载', '0', 'Not Loaded');
INSERT INTO `code` VALUES ('268', '80', '2', '速率适配', '0', 'Rate Adaptation');
INSERT INTO `code` VALUES ('269', '80', '6', '1000BASE_SX', '0', '1000BASE_SX');
INSERT INTO `code` VALUES ('270', '80', '7', '1000BASE_LX', '0', '1000BASE_LX');
INSERT INTO `code` VALUES ('271', '80', '8', '1000BASE_VX', '0', '1000BASE_VX');
INSERT INTO `code` VALUES ('272', '80', '9', '1000BASE_ZX', '0', '1000BASE_ZX');
INSERT INTO `code` VALUES ('273', '80', '10', '1000BASE_T', '0', '1000BASE_T');
INSERT INTO `code` VALUES ('274', '83', '2g5', '2.5G', '0', '2.5G');
INSERT INTO `code` VALUES ('275', '84', '0', '未校时', '0', 'No Correction tTime');
INSERT INTO `code` VALUES ('276', '84', '1', '已校时', '0', 'Corrected Time');
INSERT INTO `code` VALUES ('277', '85', '0', '未配置', '0', 'Not Configured');
INSERT INTO `code` VALUES ('278', '85', '1', '已配置', '0', 'Configured');
INSERT INTO `code` VALUES ('279', '86', '0', '未配置', '0', 'Not configured');
INSERT INTO `code` VALUES ('280', '86', '1', '已配置', '0', 'Configured');
INSERT INTO `code` VALUES ('281', '87', '0', '端口', '0', 'Port');
INSERT INTO `code` VALUES ('282', '87', '1', '端口+运营商VLAN', '0', 'Port+ Operators Vlan');
INSERT INTO `code` VALUES ('283', '87', '2', '端口+运营商VLAN+客户VLAN', '0', 'Port+ Operators Vlan+ Customers Vlan');
INSERT INTO `code` VALUES ('284', '88', '0', '不处理', '0', 'Does Not Deal');
INSERT INTO `code` VALUES ('285', '88', '1', '添加', '0', 'Insert');
INSERT INTO `code` VALUES ('286', '88', '2', '修改', '0', 'Update');
INSERT INTO `code` VALUES ('287', '88', '3', '删除', '0', 'Delete');
INSERT INTO `code` VALUES ('288', '88', '4', '添加+修改', '0', 'Insert And Update');
INSERT INTO `code` VALUES ('289', '89', '1', '输出', '0', 'Export');
INSERT INTO `code` VALUES ('291', '90', '1', 'ELSP模式', '0', 'ELSP Mode');
INSERT INTO `code` VALUES ('292', '90', '2', 'EELSP模式', '0', 'EELSP Mode');
INSERT INTO `code` VALUES ('301', '91', '0', '0', '0', '0');
INSERT INTO `code` VALUES ('302', '91', '1', '1', '0', '1');
INSERT INTO `code` VALUES ('303', '91', '2', '2', '0', '2');
INSERT INTO `code` VALUES ('304', '91', '3', '3', '0', '3');
INSERT INTO `code` VALUES ('305', '91', '4', '4', '0', '4');
INSERT INTO `code` VALUES ('306', '91', '5', '5', '0', '5');
INSERT INTO `code` VALUES ('307', '91', '6', '6', '0', '6');
INSERT INTO `code` VALUES ('308', '91', '7', '7', '0', '7');
INSERT INTO `code` VALUES ('309', '92', '1', 'BE', '0', 'BE');
INSERT INTO `code` VALUES ('310', '92', '1', 'AF1', '0', 'AF1');
INSERT INTO `code` VALUES ('311', '92', '1', 'AF2', '0', 'AF2');
INSERT INTO `code` VALUES ('312', '92', '1', 'AF3', '0', 'AF3');
INSERT INTO `code` VALUES ('313', '92', '1', 'AF4', '0', 'AF4');
INSERT INTO `code` VALUES ('314', '92', '1', 'EF', '0', 'EF');
INSERT INTO `code` VALUES ('315', '92', '1', 'CS6', '0', 'CS6');
INSERT INTO `code` VALUES ('316', '92', '1', 'CS7', '0', 'CS7');
INSERT INTO `code` VALUES ('317', '92', '1', 'AF11', '0', 'AF11');
INSERT INTO `code` VALUES ('318', '92', '1', 'AF12', '0', 'AF12');
INSERT INTO `code` VALUES ('319', '92', '1', 'AF21', '0', 'AF21');
INSERT INTO `code` VALUES ('320', '92', '1', 'AF22', '0', 'AF22');
INSERT INTO `code` VALUES ('322', '94', '0', '指配EXP', '0', 'Assign EXP');
INSERT INTO `code` VALUES ('323', '94', '1', '基于PHB到TMC/TMP EXP映射表', '0', 'Based PHB To TMC / TMP EXP Mapping Table');
INSERT INTO `code` VALUES ('324', '95', '0', '指配PHB', '0', 'Assign PHB');
INSERT INTO `code` VALUES ('325', '95', '1', '基于TMP EXP 到PHB映射表', '0', 'Based TMP EXP To PHB Mapping Table');
INSERT INTO `code` VALUES ('334', '97', '0', '0', '0', '0');
INSERT INTO `code` VALUES ('335', '97', '1', '1', '0', '1');
INSERT INTO `code` VALUES ('336', '97', '2', '2', '0', '2');
INSERT INTO `code` VALUES ('337', '97', '3', '3', '0', '3');
INSERT INTO `code` VALUES ('338', '97', '4', '4', '0', '4');
INSERT INTO `code` VALUES ('339', '97', '5', '5', '0', '5');
INSERT INTO `code` VALUES ('340', '97', '6', '6', '0', '6');
INSERT INTO `code` VALUES ('341', '97', '7', '7', '0', '7');
INSERT INTO `code` VALUES ('342', '98', '1', '1', '0', '1');
INSERT INTO `code` VALUES ('343', '98', '2', '2', '0', '2');
INSERT INTO `code` VALUES ('344', '98', '3', '3', '0', '3');
INSERT INTO `code` VALUES ('345', '98', '4', '4', '0', '4');
INSERT INTO `code` VALUES ('346', '98', '5', '5', '0', '5');
INSERT INTO `code` VALUES ('347', '98', '6', '6', '0', '6');
INSERT INTO `code` VALUES ('348', '98', '7', '7', '0', '7');
INSERT INTO `code` VALUES ('349', '98', '8', '8', '0', '8');
INSERT INTO `code` VALUES ('350', '98', '9', '9', '0', '9');
INSERT INTO `code` VALUES ('351', '98', '10', '10', '0', '10');
INSERT INTO `code` VALUES ('352', '98', '11', '11', '0', '11');
INSERT INTO `code` VALUES ('353', '98', '12', '12', '0', '12');
INSERT INTO `code` VALUES ('354', '98', '13', '13', '0', '13');
INSERT INTO `code` VALUES ('355', '98', '14', '14', '0', '14');
INSERT INTO `code` VALUES ('356', '98', '15', '15', '0', '15');
INSERT INTO `code` VALUES ('358', '100', '0', '输入模式', '0', 'Input Mode');
INSERT INTO `code` VALUES ('359', '100', '1', '输出模式', '0', 'Output Mode');
INSERT INTO `code` VALUES ('360', '101', '0', '2Mbit', '0', '2Mbit');
INSERT INTO `code` VALUES ('361', '101', '1', '2MHz', '0', '2MHz');
INSERT INTO `code` VALUES ('362', '102', '0', '75', '0', '75');
INSERT INTO `code` VALUES ('363', '102', '1', '120', '0', '120');
INSERT INTO `code` VALUES ('364', '103', '0', 'SA4', '0', 'SA4');
INSERT INTO `code` VALUES ('365', '103', '1', 'SA5', '0', 'SA5');
INSERT INTO `code` VALUES ('366', '103', '2', 'SA6', '0', 'SA6');
INSERT INTO `code` VALUES ('367', '103', '3', 'SA7', '0', 'SA7');
INSERT INTO `code` VALUES ('368', '103', '4', 'SA8', '0', 'SA8');
INSERT INTO `code` VALUES ('369', '104', '0', '在线网元', '0', 'RealSite');
INSERT INTO `code` VALUES ('370', '104', '1', '虚拟网元', '0', 'VirtualSite');
INSERT INTO `code` VALUES ('371', '23', '2', '100M全双工', '0', '100M Full Duplex');
INSERT INTO `code` VALUES ('372', '23', '3', '10M全双工', '0', '10M Full Duplex');
INSERT INTO `code` VALUES ('373', '105', '0', '100M', '0', '100M');
INSERT INTO `code` VALUES ('374', '105', '3', '自协商100M', '0', '100M Auto-negotiation');
INSERT INTO `code` VALUES ('375', '106', '0', '100M', '0', '100M');
INSERT INTO `code` VALUES ('376', '106', '1', '1G', '0', '1G');
INSERT INTO `code` VALUES ('377', '106', '3', '自协商100M', '0', '100M Auto-negotiation');
INSERT INTO `code` VALUES ('378', '106', '4', '自协商1G', '0', '1G Auto-negotiation');
INSERT INTO `code` VALUES ('379', '107', '2', '10G', '0', '10G');
INSERT INTO `code` VALUES ('380', '107', '5', '自协商10G', '0', '10G Auto-negotiation');
INSERT INTO `code` VALUES ('381', '60', '0', '0x8100', '0', '0x8100');
INSERT INTO `code` VALUES ('382', '108', '1', 'A-LOCK已自动锁定', '0', 'A-LOCK is automatically locked');
INSERT INTO `code` VALUES ('383', '108', '2', 'A-ACQ捕捉跟踪之中', '0', 'A-ACQ being track ');
INSERT INTO `code` VALUES ('384', '108', '3', 'A-HOLD自动进入HOLD状态', '0', 'A-HOLD automatically changes to HOLD status');
INSERT INTO `code` VALUES ('385', '108', '4', 'A-FREE自动进入FREE状态', '0', 'F-HOLD forced changes to HOLD status');
INSERT INTO `code` VALUES ('386', '108', '5', 'F-HOLD强制进入HOLD状态', '0', 'A-FREE automatically changes to FREE status');
INSERT INTO `code` VALUES ('387', '108', '6', 'F-FREE强制进入FREE状态', '0', 'F-HOID forced changes to FREE status');
INSERT INTO `code` VALUES ('388', '109', '0', 'EO-DISABLE', '0', 'EO-DISABLE');
INSERT INTO `code` VALUES ('389', '109', '1', 'EO-EABLE', '0', 'EO-EABLE');
INSERT INTO `code` VALUES ('390', '109', '2', 'EO-AIS', '0', 'EO-AIS');
INSERT INTO `code` VALUES ('391', '109', '255', '无', '0', 'No');
INSERT INTO `code` VALUES ('392', '110', '0', '通PLL锁相环', '0', 'Through PLL Phase Locked Loop');
INSERT INTO `code` VALUES ('393', '110', '1', '通外时钟', '0', 'Connecting External Clock');
INSERT INTO `code` VALUES ('394', '110', '2', '通GE1.1', '0', 'Through GE1.1');
INSERT INTO `code` VALUES ('395', '110', '3', '通GE1.2', '0', 'Through GE1.2');
INSERT INTO `code` VALUES ('396', '110', '4', '通GE1.3', '0', 'Through GE1.3');
INSERT INTO `code` VALUES ('397', '110', '5', '通GE1.4', '0', 'Through GE1.4');
INSERT INTO `code` VALUES ('398', '110', '6', '通GE1.5', '0', 'Through GE1.5');
INSERT INTO `code` VALUES ('399', '110', '7', '通GE1.6', '0', 'Through GE1.6');
INSERT INTO `code` VALUES ('400', '110', '8', '通GE2.1', '0', 'Through GE2.1');
INSERT INTO `code` VALUES ('401', '110', '9', '通GE2.2', '0', 'Through GE2.2');
INSERT INTO `code` VALUES ('402', '110', '10', '通GE2.3', '0', 'Through GE2.3');
INSERT INTO `code` VALUES ('403', '110', '11', '通GE2.4', '0', 'Through GE2.4');
INSERT INTO `code` VALUES ('404', '110', '12', '通GE2.5', '0', 'Through GE2.5');
INSERT INTO `code` VALUES ('405', '110', '13', '通GE2.6', '0', 'Through GE2.6');
INSERT INTO `code` VALUES ('406', '110', '14', '通GE2.7', '0', 'Through GE2.7');
INSERT INTO `code` VALUES ('407', '110', '15', '通GE2.8', '0', 'Through GE2.8');
INSERT INTO `code` VALUES ('408', '110', '16', '通GE3.1', '0', 'Through GE3.1');
INSERT INTO `code` VALUES ('409', '110', '17', '通GE3.2', '0', 'Through GE3.2');
INSERT INTO `code` VALUES ('410', '110', '18', '通GE3.3', '0', 'Through GE3.3');
INSERT INTO `code` VALUES ('411', '110', '19', '通GE3.4', '0', 'Through GE3.4');
INSERT INTO `code` VALUES ('412', '110', '20', '通GE3.5', '0', 'Through GE3.5');
INSERT INTO `code` VALUES ('413', '110', '21', '通GE3.6', '0', 'Through GE3.6');
INSERT INTO `code` VALUES ('414', '110', '22', '通GE3.7', '0', 'Through GE3.7');
INSERT INTO `code` VALUES ('415', '110', '23', '通GE3.8', '0', 'Through GE3.8');
INSERT INTO `code` VALUES ('416', '110', '255', '无', '0', 'No');
INSERT INTO `code` VALUES ('417', '111', '0', 'Hz', '0', 'Hz');
INSERT INTO `code` VALUES ('418', '111', '1', 'HDB3', '0', 'HDB3');
INSERT INTO `code` VALUES ('419', '111', '255', '无', '0', 'No');
INSERT INTO `code` VALUES ('421', '112', '0', 'SA4', '0', 'SA4');
INSERT INTO `code` VALUES ('422', '112', '1', 'SA5', '0', 'SA5');
INSERT INTO `code` VALUES ('423', '112', '2', 'SA6', '0', 'SA6');
INSERT INTO `code` VALUES ('424', '112', '3', 'SA7', '0', 'SA7');
INSERT INTO `code` VALUES ('425', '112', '4', 'SA8', '0', 'SA8');
INSERT INTO `code` VALUES ('426', '112', '255', '无', '0', 'No');
INSERT INTO `code` VALUES ('427', '113', '0', '外时钟', '0', 'External Clock');
INSERT INTO `code` VALUES ('428', '113', '1', 'GE1.1', '0', 'GE1.1');
INSERT INTO `code` VALUES ('429', '113', '2', 'GE1.2', '0', 'GE1.2');
INSERT INTO `code` VALUES ('430', '113', '3', 'GE1.3', '0', 'GE1.3');
INSERT INTO `code` VALUES ('431', '113', '4', 'GE1.4', '0', 'GE1.4');
INSERT INTO `code` VALUES ('432', '113', '5', 'GE1.5', '0', 'GE1.5');
INSERT INTO `code` VALUES ('433', '113', '6', 'GE1.6', '0', 'GE1.6');
INSERT INTO `code` VALUES ('434', '113', '7', 'GE2.1', '0', 'GE2.1');
INSERT INTO `code` VALUES ('435', '113', '8', 'GE2.2', '0', 'GE2.2');
INSERT INTO `code` VALUES ('436', '113', '9', 'GE2.3', '0', 'GE2.3');
INSERT INTO `code` VALUES ('437', '113', '10', 'GE2.4', '0', 'GE2.4');
INSERT INTO `code` VALUES ('438', '113', '11', 'GE2.5', '0', 'GE2.5');
INSERT INTO `code` VALUES ('439', '113', '12', 'GE2.6', '0', 'GE2.6');
INSERT INTO `code` VALUES ('440', '113', '13', 'GE2.7', '0', 'GE2.7');
INSERT INTO `code` VALUES ('441', '113', '14', 'GE2.8', '0', 'GE2.8');
INSERT INTO `code` VALUES ('442', '113', '15', 'GE3.1', '0', 'GE3.1');
INSERT INTO `code` VALUES ('443', '113', '16', 'GE3.2', '0', 'GE3.2');
INSERT INTO `code` VALUES ('444', '113', '17', 'GE3.3', '0', 'GE3.3');
INSERT INTO `code` VALUES ('445', '113', '18', 'GE3.4', '0', 'GE3.4');
INSERT INTO `code` VALUES ('446', '113', '19', 'GE3.5', '0', 'GE3.5');
INSERT INTO `code` VALUES ('447', '113', '20', 'GE3.6', '0', 'GE3.6');
INSERT INTO `code` VALUES ('448', '113', '21', 'GE3.7', '0', 'GE3.7');
INSERT INTO `code` VALUES ('449', '113', '22', 'GE3.8', '0', 'GE3.8');
INSERT INTO `code` VALUES ('450', '114', '0', '质量未知', '0', 'Quality Unknown');
INSERT INTO `code` VALUES ('451', '114', '2', 'G811时钟', '0', 'G_811 Clock');
INSERT INTO `code` VALUES ('452', '114', '4', 'G812转送时钟', '0', 'G_812 Transfer Clock');
INSERT INTO `code` VALUES ('453', '114', '8', 'G812本地时钟', '0', 'G_812 Local Clock');
INSERT INTO `code` VALUES ('454', '114', '11', '同步G813时钟', '0', 'G_813 Clock（SETS）');
INSERT INTO `code` VALUES ('455', '114', '15', '不用于同步', '0', 'can not Synchronous');
INSERT INTO `code` VALUES ('456', '114', '255', '无', '0', 'No');
INSERT INTO `code` VALUES ('457', '115', '0', '正常', '0', 'Normal');
INSERT INTO `code` VALUES ('458', '115', '1', '频偏超限', '0', 'Frequency Offset Over Limit');
INSERT INTO `code` VALUES ('459', '115', '255', '无源或源中断', '0', 'No Input or Break Off');
INSERT INTO `code` VALUES ('460', '117', '0', '正常', '0', 'Normal');
INSERT INTO `code` VALUES ('461', '117', '1', '中断', '0', 'Break Off');
INSERT INTO `code` VALUES ('462', '117', '2', 'AIS', '0', 'AIS');
INSERT INTO `code` VALUES ('463', '117', '3', 'LOF', '0', 'LOF');
INSERT INTO `code` VALUES ('464', '117', '4', '暂停', '0', 'Pause');
INSERT INTO `code` VALUES ('465', '117', '255', '无', '0', 'No');
INSERT INTO `code` VALUES ('466', '116', '0', '0', '0', '0');
INSERT INTO `code` VALUES ('467', '116', '1', '1', '0', '1');
INSERT INTO `code` VALUES ('468', '116', '2', '2', '0', '2');
INSERT INTO `code` VALUES ('469', '116', '3', '3', '0', '3');
INSERT INTO `code` VALUES ('470', '116', '4', '4', '0', '4');
INSERT INTO `code` VALUES ('471', '116', '5', '5', '0', '5');
INSERT INTO `code` VALUES ('472', '116', '6', '6', '0', '6');
INSERT INTO `code` VALUES ('473', '116', '7', '7', '0', '7');
INSERT INTO `code` VALUES ('474', '118', '0', '不使能', '0', 'No Enabled');
INSERT INTO `code` VALUES ('475', '118', '1', 'trTCM', '0', 'TrTCM');
INSERT INTO `code` VALUES ('476', '118', '2', 'Modified trTCM', '0', 'Modified TrTCM');
INSERT INTO `code` VALUES ('477', '119', '0', '正常', '0', 'NR-W');
INSERT INTO `code` VALUES ('478', '119', '1', '锁定主用', '0', 'NR-P');
INSERT INTO `code` VALUES ('479', '119', '2', '锁定备用', '0', 'LP');
INSERT INTO `code` VALUES ('480', '119', '3', '人工倒换', '0', 'SF');
INSERT INTO `code` VALUES ('481', '119', '4', '强制倒换', '0', 'MS');
INSERT INTO `code` VALUES ('482', '119', '5', '等待恢复', '0', 'FS');
INSERT INTO `code` VALUES ('489', '121', '3', '近24个小时', '0', 'The Latest 24 Hours');
INSERT INTO `code` VALUES ('490', '121', '4', '近7个天', '0', 'The Latest 7 Days');
INSERT INTO `code` VALUES ('491', '121', '5', '近30天', '0', 'The Latest 30 Days');
INSERT INTO `code` VALUES ('492', '121', '6', '自定义', '0', 'Custom Range');
INSERT INTO `code` VALUES ('493', '17', '3', 'SNCP', '0', 'SNCP');
INSERT INTO `code` VALUES ('494', '122', '0', 'PWTDM64', '0', 'PWTDM64');
INSERT INTO `code` VALUES ('495', '122', '1', 'PWTDM128', '0', 'PWTDM128');
INSERT INTO `code` VALUES ('496', '122', '2', 'PWTDM256', '0', 'PWTDM256');
INSERT INTO `code` VALUES ('497', '122', '3', 'PWTDM512', '0', 'PWTDM512');
INSERT INTO `code` VALUES ('498', '123', '1', '一级', '0', 'Level1');
INSERT INTO `code` VALUES ('499', '123', '2', '二级', '0', 'Level2');
INSERT INTO `code` VALUES ('500', '123', '3', '三级', '0', 'Level3');
INSERT INTO `code` VALUES ('501', '123', '4', '四级', '0', 'Level4');
INSERT INTO `code` VALUES ('502', '123', '5', '五级', '0', 'Level5');
INSERT INTO `code` VALUES ('503', '123', '6', '六级', '0', 'Level6');
INSERT INTO `code` VALUES ('504', '123', '7', '七级', '0', 'Level7');
INSERT INTO `code` VALUES ('505', '125', '0', 'MEP', '0', 'MEP');
INSERT INTO `code` VALUES ('506', '125', '1', 'MIP', '0', 'MIP');
INSERT INTO `code` VALUES ('507', '125', '2', 'MEP和MIP', '0', 'Mep and Mip');
INSERT INTO `code` VALUES ('508', '127', '3', '100ms', '0', '100ms');
INSERT INTO `code` VALUES ('509', '127', '4', '1s', '0', '1s');
INSERT INTO `code` VALUES ('510', '127', '5', '10s', '0', '10s');
INSERT INTO `code` VALUES ('511', '127', '6', '1min', '0', '1min');
INSERT INTO `code` VALUES ('512', '127', '7', '10min', '0', '10min');
INSERT INTO `code` VALUES ('513', '128', '0', 'down', '0', 'Down');
INSERT INTO `code` VALUES ('514', '128', '1', 'up', '0', 'Up');
INSERT INTO `code` VALUES ('515', '129', '0', '全0', '0', 'All Zero');
INSERT INTO `code` VALUES ('516', '129', '1', '伪随机序列', '0', 'Pseudo-random Sequence');
INSERT INTO `code` VALUES ('517', '130', '0', '不可丢弃', '0', 'Can Not Be Discarded');
INSERT INTO `code` VALUES ('518', '130', '1', '可丢弃', '0', 'Can Be Discarded');
INSERT INTO `code` VALUES ('519', '131', '0', '1s', '0', '1s');
INSERT INTO `code` VALUES ('520', '131', '1', '1min', '0', '1min');
INSERT INTO `code` VALUES ('521', '132', '0', '在线', '0', 'On Line');
INSERT INTO `code` VALUES ('522', '132', '1', '离线', '0', 'Off Line');
INSERT INTO `code` VALUES ('523', '133', '0', 'invalid', '0', 'Invalid');
INSERT INTO `code` VALUES ('524', '133', '1', 'valid', '0', 'Valid');
INSERT INTO `code` VALUES ('525', '134', '0', '100ms', '0', '100ms');
INSERT INTO `code` VALUES ('526', '134', '1', '1s', '0', '1s');
INSERT INTO `code` VALUES ('527', '135', '0', '系统时钟', '0', 'SystemClock');
INSERT INTO `code` VALUES ('528', '135', '1', '导出时钟', '0', 'ExportClock');
INSERT INTO `code` VALUES ('529', '136', '1', '1+1', '0', '1+1');
INSERT INTO `code` VALUES ('530', '136', '2', '1:1', '0', '1:1');
INSERT INTO `code` VALUES ('531', '137', '1', '可恢复', '0', 'Recoverable');
INSERT INTO `code` VALUES ('532', '137', '2', '不可恢复', '0', 'Unrecoverable');
INSERT INTO `code` VALUES ('533', '138', '1', '高', '0', 'High');
INSERT INTO `code` VALUES ('534', '138', '2', '低', '0', 'Low');
INSERT INTO `code` VALUES ('535', '139', '0', '默认模式', '0', 'The Default Mode');
INSERT INTO `code` VALUES ('536', '139', '1', '标准返回式', '0', 'Standard Return Mode');
INSERT INTO `code` VALUES ('537', '139', '2', '标准不返回式', '0', 'Standard Not Return Mode');
INSERT INTO `code` VALUES ('540', '141', '0', '0x8100', '0', '0x8100');
INSERT INTO `code` VALUES ('541', '141', '1', '0x9100', '0', '0x9100');
INSERT INTO `code` VALUES ('542', '141', '2', '0x88a8', '0', '0x88a8');
INSERT INTO `code` VALUES ('543', '142', '0', '否', '0', 'No');
INSERT INTO `code` VALUES ('544', '142', '1', '是', '0', 'Yes');
INSERT INTO `code` VALUES ('547', '144', '1', '主动', '0', 'Initiative');
INSERT INTO `code` VALUES ('548', '144', '0', '被动', '0', 'Passive');
INSERT INTO `code` VALUES ('549', '145', '1', '1s', '0', '1s');
INSERT INTO `code` VALUES ('550', '145', '0', '100s', '0', '100s');
INSERT INTO `code` VALUES ('551', '146', '1', '工作', '0', 'Work');
INSERT INTO `code` VALUES ('552', '146', '0', '保护', '0', 'Protect');
INSERT INTO `code` VALUES ('553', '147', '0', '关闭端口', '0', 'ClosePort');
INSERT INTO `code` VALUES ('554', '147', '1', '发送3AH事件', '0', 'Send3AH');
INSERT INTO `code` VALUES ('555', '148', '0', 'LLSP模式', '0', 'LLSP Model');
INSERT INTO `code` VALUES ('556', '148', '1', 'ELSP模式', '0', 'ELSP Model');
INSERT INTO `code` VALUES ('557', '149', '0', '输出', '0', 'Export');
INSERT INTO `code` VALUES ('558', '149', '1', '输入', '0', 'Import');
INSERT INTO `code` VALUES ('559', '150', '0', 'VLANPRI0', '0', 'VLANPRI0');
INSERT INTO `code` VALUES ('560', '150', '1', 'VLANPRI1', '0', 'VLANPRI1');
INSERT INTO `code` VALUES ('561', '150', '2', 'VLANPRI2', '0', 'VLANPRI2');
INSERT INTO `code` VALUES ('562', '150', '3', 'VLANPRI3', '0', 'VLANPRI3');
INSERT INTO `code` VALUES ('563', '150', '4', 'VLANPRI4', '0', 'VLANPRI4');
INSERT INTO `code` VALUES ('564', '150', '5', 'VLANPRI5', '0', 'VLANPRI5');
INSERT INTO `code` VALUES ('565', '150', '6', 'VLANPRI6', '0', 'VLANPRI6');
INSERT INTO `code` VALUES ('566', '150', '7', 'VLANPRI7', '0', 'VLANPRI7');
INSERT INTO `code` VALUES ('567', '25', '3', '替换VLAN', '0', 'Replace VID');
INSERT INTO `code` VALUES ('568', '25', '4', '替换VLAN和PRI', null, 'Replace VID And PRI');
INSERT INTO `code` VALUES ('569', '151', '0', '未使能', '0', 'Disable');
INSERT INTO `code` VALUES ('570', '151', '1', '使能', '0', 'Enable');
INSERT INTO `code` VALUES ('571', '152', '0', 'Invalid', '0', 'Invalid');
INSERT INTO `code` VALUES ('572', '152', '1', 'RlyHit', '0', 'RlyHit');
INSERT INTO `code` VALUES ('573', '152', '2', 'RlyFDB', '0', 'RlyFDB');
INSERT INTO `code` VALUES ('574', '152', '3', 'RlyMPDB', '0', 'RlyMPDB');
INSERT INTO `code` VALUES ('575', '153', '0', 'Invalid', '0', 'Invalid');
INSERT INTO `code` VALUES ('576', '153', '1', 'Egrok', '0', 'Egrok');
INSERT INTO `code` VALUES ('577', '153', '2', 'Egrdown', '0', 'Egrdown');
INSERT INTO `code` VALUES ('578', '153', '3', 'Egrblocked', '0', 'Egrblocked');
INSERT INTO `code` VALUES ('579', '153', '4', 'Egrokvid', '0', 'Egrokvid');
INSERT INTO `code` VALUES ('580', '154', '1', '百兆光模块', '0', '100M Optical Module');
INSERT INTO `code` VALUES ('581', '154', '2', '千兆光模块', '0', '1000M Optical Module');
INSERT INTO `code` VALUES ('582', '154', '3', '万兆光模块', '0', '10000M Optical Module');
INSERT INTO `code` VALUES ('583', '154', '4', '万兆电模块', '0', '10000M Electric Module');
INSERT INTO `code` VALUES ('584', '154', '5', '百兆电模块', '0', '100M Electric Module');
INSERT INTO `code` VALUES ('585', '155', '7', 'LC', '0', 'LC');
INSERT INTO `code` VALUES ('586', '155', '34', 'RJ45', '0', 'RJ45');
INSERT INTO `code` VALUES ('587', '156', '7', '光纤', '0', 'Optical Fiber');
INSERT INTO `code` VALUES ('588', '156', '34', '电缆', '0', 'Cable');
INSERT INTO `code` VALUES ('589', '157', '-8', '1/256', null, '1/256');
INSERT INTO `code` VALUES ('590', '157', '-7', '1/128', null, '1/128');
INSERT INTO `code` VALUES ('591', '157', '-6', '1/64', null, '1/64');
INSERT INTO `code` VALUES ('592', '157', '-5', '1/32', null, '1/32');
INSERT INTO `code` VALUES ('593', '157', '-4', '1/16', null, '1/16');
INSERT INTO `code` VALUES ('594', '157', '-3', '1/8', null, '1/8');
INSERT INTO `code` VALUES ('595', '157', '-2', '1/4', null, '1/4');
INSERT INTO `code` VALUES ('596', '157', '-1', '1/2', null, '1/2');
INSERT INTO `code` VALUES ('597', '157', '0', '1', null, '1');
INSERT INTO `code` VALUES ('598', '157', '1', '2', null, '2');
INSERT INTO `code` VALUES ('599', '158', '0', 'up', null, 'up');
INSERT INTO `code` VALUES ('600', '158', '1', 'linkdown', null, 'linkdown');
INSERT INTO `code` VALUES ('601', '158', '2', 'admindown', null, 'admindown');
INSERT INTO `code` VALUES ('602', '158', '3', 'notPresent', null, 'notPresent');
INSERT INTO `code` VALUES ('603', '158', '5', 'datalinkdown', null, 'datalinkdown');
INSERT INTO `code` VALUES ('604', '44', '1', 'STUB', null, 'STUB');
INSERT INTO `code` VALUES ('605', '44', '2', 'NSSA', null, 'NSSA');
INSERT INTO `code` VALUES ('606', '159', '0', '端口环回 ', '0', 'PORTLOOP');
INSERT INTO `code` VALUES ('607', '159', '1', 'vlan环回', '0', 'VLANLOOP');
INSERT INTO `code` VALUES ('608', '159', '2', '源MAC环回', '0', 'SOURCEMACLOOP');
INSERT INTO `code` VALUES ('609', '159', '3', '目的MAC环回', '0', 'PURPOSEMACLOOP');
INSERT INTO `code` VALUES ('610', '159', '4', '源IP环回', '0', 'SOURCEIPLOOP');
INSERT INTO `code` VALUES ('611', '159', '5', '目的IP环回', '0', 'PURPOSEIPLOOP');
INSERT INTO `code` VALUES ('612', '160', '1', '拒绝', '0', 'refuse');
INSERT INTO `code` VALUES ('613', '160', '0', '允许', '0', 'permit');
INSERT INTO `code` VALUES ('614', '161', '0', '入方向', '0', 'enterDirection');
INSERT INTO `code` VALUES ('615', '161', '1', '出方向', '0', 'outDirection');
INSERT INTO `code` VALUES ('616', '162', '0', '端口', '0', 'PORT');
INSERT INTO `code` VALUES ('617', '162', '1', 'vlan', '0', 'VLAN');
INSERT INTO `code` VALUES ('618', '162', '2', '端口+vlan', '0', 'PORTANDVLAN');
INSERT INTO `code` VALUES ('619', '163', '0', '无限制', null, 'No Limite');
INSERT INTO `code` VALUES ('620', '163', '1', '基于端口', null, 'Based on Port');
INSERT INTO `code` VALUES ('622', '164', '1', '黑名单模式', '0', 'BLACKMODEL');
INSERT INTO `code` VALUES ('623', '164', '2', '白名单模式', '0', 'WHITEMODEL');
INSERT INTO `code` VALUES ('624', '165', '0', '否', '0', 'NO');
INSERT INTO `code` VALUES ('625', '165', '1', '是', '0', 'YES');
INSERT INTO `code` VALUES ('626', '166', '0', '被动', '0', 'Passive');
INSERT INTO `code` VALUES ('627', '166', '1', '主动', '0', 'Initiative');
INSERT INTO `code` VALUES ('633', '168', '0', '未连接', '0', 'NOLINK');
INSERT INTO `code` VALUES ('634', '168', '1', '连接', '0', 'LINK');
INSERT INTO `code` VALUES ('635', '169', '0', 'none', '0', 'none');
INSERT INTO `code` VALUES ('636', '169', '1', '环回', '0', 'LOOP');
INSERT INTO `code` VALUES ('637', '169', '2', '丢弃', '0', 'Discard ');
INSERT INTO `code` VALUES ('638', '167', '1', 'DISABLED', '0', 'DISABLED');
INSERT INTO `code` VALUES ('639', '167', '2', 'LINK_FAULT', '0', 'LINK_FAULT');
INSERT INTO `code` VALUES ('640', '167', '3', 'WAIE', '0', 'WAIE');
INSERT INTO `code` VALUES ('641', '167', '4', 'ACTIVE', '0', 'ACTIVE');
INSERT INTO `code` VALUES ('642', '167', '5', 'SEND_LOCAL_REMOTE', '0', 'SEND_LOCAL_REMOTE');
INSERT INTO `code` VALUES ('643', '167', '6', 'SEND', '0', 'SEND');
INSERT INTO `code` VALUES ('644', '167', '7', 'ANY', '0', 'ANY');
INSERT INTO `code` VALUES ('645', '170', '0', '普通', '0', 'Ordinary');
INSERT INTO `code` VALUES ('646', '170', '1', '多段', '0', 'Multi Segment');
INSERT INTO `code` VALUES ('647', '171', '1', 'fault', '0', 'fault');
INSERT INTO `code` VALUES ('648', '171', '2', 'ok', '0', 'ok');
INSERT INTO `code` VALUES ('649', '172', '0', '普通', null, 'Ordinary');
INSERT INTO `code` VALUES ('650', '172', '1', '多段', null, 'Multi Segment');
INSERT INTO `code` VALUES ('651', '173', '0', '手工聚合', null, 'Manual Polymerization');
INSERT INTO `code` VALUES ('652', '173', '1', '静态负载分担', null, 'Static Load Sharing');
INSERT INTO `code` VALUES ('653', '173', '2', '静态非负载分担', null, 'Static Non-load Sharing');
INSERT INTO `code` VALUES ('654', '173', '3', '动态负载分担', null, 'Dynamic Protocol Aggregation');
INSERT INTO `code` VALUES ('655', '175', '0', '0x8100', null, '0x8100');
INSERT INTO `code` VALUES ('656', '175', '1', '0x9100', null, '0x9100');
INSERT INTO `code` VALUES ('657', '25', '5', '替换PRI', null, 'Replace PRI');
INSERT INTO `code` VALUES ('658', '25', '6', '增加VLAN和PRI', null, 'Increase In VLAN And PRI');
INSERT INTO `code` VALUES ('659', '174', '0', '700+系列', '0', '700+Series');
INSERT INTO `code` VALUES ('660', '176', '0', '跟踪V.35定时', null, 'Tracking V.35 Timing');
INSERT INTO `code` VALUES ('661', '176', '1', '跟踪E1定时', null, 'Tracking E1 Timing');
INSERT INTO `code` VALUES ('662', '176', '2', '主定时', null, 'Master Timing');
INSERT INTO `code` VALUES ('663', '177', '0', '未使能', '0', 'Disable');
INSERT INTO `code` VALUES ('664', '177', '1', '使能', '0', 'Enable');
INSERT INTO `code` VALUES ('665', '177', '2', '测试', '0', 'TEST');
INSERT INTO `code` VALUES ('666', '178', '0', '请选择结束时间', '0', 'Anytime');
INSERT INTO `code` VALUES ('667', '178', '1', '近1个小时', '0', 'The Latest 1 Hours');
INSERT INTO `code` VALUES ('668', '178', '2', '近12个小时', '0', 'The Latest 12 Hours');
INSERT INTO `code` VALUES ('669', '178', '3', '近24个小时', '0', 'The Latest 24 Hours');
INSERT INTO `code` VALUES ('670', '178', '4', '近7个天', '0', 'The Latest 7 Days');
INSERT INTO `code` VALUES ('671', '178', '5', '近30天', '0', 'The Latest 30 Days');
INSERT INTO `code` VALUES ('672', '178', '6', '自定义', '0', 'Custom Range');
INSERT INTO `code` VALUES ('673', '119', '6', '主用告警', '0', 'WTR');
INSERT INTO `code` VALUES ('674', '119', '7', '备用告警', '0', 'SF-P');
INSERT INTO `code` VALUES ('675', '179', '0', '不环回', '0', 'NO LOOP');
INSERT INTO `code` VALUES ('676', '179', '1', '内环回', '0', 'INSIDELOOP');
INSERT INTO `code` VALUES ('677', '179', '2', '外环回', '0', 'OUT LOOP');
INSERT INTO `code` VALUES ('678', '104', '2', '离线网元', '0', 'OfflineSite');
INSERT INTO `code` VALUES ('679', '180', '0', 'SAToP', '0', 'SAToP');
INSERT INTO `code` VALUES ('680', '180', '1', 'CESoPSN', '0', 'CESoPSN');
INSERT INTO `code` VALUES ('681', '23', '0', '1000M半双工', '0', '1000M Half Duplex');
INSERT INTO `code` VALUES ('682', '23', '0', '100M半双工', '0', '100M Half Duplex');
INSERT INTO `code` VALUES ('683', '23', '0', '10M半双工', '0', '10M Half Duplex');
INSERT INTO `code` VALUES ('684', '181', '0', 'PCM30公共信令通道', '0', 'PCM30 Common Singnaling channel');
INSERT INTO `code` VALUES ('685', '181', '1', 'PCM31', '0', 'PCM31');
INSERT INTO `code` VALUES ('686', '182', '0', '不成复帧', '0', 'No Complex Frame');
INSERT INTO `code` VALUES ('687', '182', '1', 'CRC-4复帧', '0', 'CRC-4 Complex Frame');
INSERT INTO `code` VALUES ('688', '183', '0', '无', '0', 'no');
INSERT INTO `code` VALUES ('689', '183', '1', 'TCP', '0', 'TCP');
INSERT INTO `code` VALUES ('690', '183', '2', 'UDP', '0', 'UDP');
INSERT INTO `code` VALUES ('691', '184', '0', 'HDB3', '0', 'HDB3');
INSERT INTO `code` VALUES ('692', '184', '1', 'Hz', '0', 'Hz');
INSERT INTO `code` VALUES ('693', '184', '255', '未知', '0', 'Unknown');
INSERT INTO `code` VALUES ('694', '185', '0', '无', '0', 'NONE');
INSERT INTO `code` VALUES ('695', '185', '1', 'TCP', '0', 'TCP');
INSERT INTO `code` VALUES ('696', '185', '2', 'UDP', '0', 'UDP');
INSERT INTO `code` VALUES ('697', '186', '0', 'dhcp off', '0', 'dhcp off');
INSERT INTO `code` VALUES ('698', '186', '1', 'dhcp server', '0', 'dhcp server');
INSERT INTO `code` VALUES ('699', '186', '2', 'dhcp relay', '0', 'dhcp relay');
INSERT INTO `code` VALUES ('700', '187', '0', '透传', '0', 'thoroughSend');
INSERT INTO `code` VALUES ('701', '187', '1', 'CPU处理', '0', 'CPU dispose');
INSERT INTO `code` VALUES ('702', '187', '2', '丢弃', '0', 'discard');
INSERT INTO `code` VALUES ('703', '119', '7', '备用告警', '0', 'Backup Alarm');
INSERT INTO `code` VALUES ('704', '119', '8', '不返回态', '0', 'State Does Not Return');
INSERT INTO `code` VALUES ('705', '119', '9', '远端锁定主用', '0', 'Remote Locking Master');
INSERT INTO `code` VALUES ('706', '119', '10', '远端锁定备用', '0', 'Remote Locking Backup');
INSERT INTO `code` VALUES ('707', '119', '11', '远端人工倒换', '0', 'Remote Manual Switching');
INSERT INTO `code` VALUES ('708', '119', '12', '远端强制倒换', '0', 'Remote Forced Switching');
INSERT INTO `code` VALUES ('709', '119', '13', '无请求工作备用', '0', 'No Request Work Backup');
INSERT INTO `code` VALUES ('710', '120', '0', '返回', '0', 'Return');
INSERT INTO `code` VALUES ('711', '120', '1', '不返回', '0', 'Do Not Return');
INSERT INTO `code` VALUES ('712', '188', '0', '正常', '0', 'Normal');
INSERT INTO `code` VALUES ('713', '188', '1', '倒换', '0', 'Switching');
INSERT INTO `code` VALUES ('714', '188', '2', '桥接', '0', 'Bridging');
INSERT INTO `code` VALUES ('715', '188', '3', '通过', '0', 'Through');
INSERT INTO `code` VALUES ('716', '188', '4', '等待恢复', '0', 'Waiting For Recovery');
INSERT INTO `code` VALUES ('717', '188', '5', '节点失效', null, 'Node Failure');

-- ----------------------------
-- Table structure for `codegroup`
-- ----------------------------
DROP TABLE IF EXISTS `codegroup`;
CREATE TABLE `codegroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codeGroupName` varchar(200) DEFAULT NULL,
  `codeIdentily` varchar(200) DEFAULT NULL,
  `codeDesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of codegroup
-- ----------------------------
INSERT INTO `codegroup` VALUES ('6', 'UDA类型', 'UDATYPE', 'uda的类型');
INSERT INTO `codegroup` VALUES ('7', 'UDA属性类型', 'UDAATTRTYPE', 'UDA属性类型');
INSERT INTO `codegroup` VALUES ('9', '端口使能状态', 'ENABLEDSTATUE', '');
INSERT INTO `codegroup` VALUES ('10', 'UDA设备类型', 'UDACELLTYPE', '');
INSERT INTO `codegroup` VALUES ('12', '版本类型', 'EDITION', '');
INSERT INTO `codegroup` VALUES ('13', '时区', 'TIMEZONE', '');
INSERT INTO `codegroup` VALUES ('15', '用户组', 'USERGROUP', '');
INSERT INTO `codegroup` VALUES ('16', '带宽', 'DAIKUAN', '');
INSERT INTO `codegroup` VALUES ('17', '保护类型', 'PROTECTTYPE', '');
INSERT INTO `codegroup` VALUES ('18', '是否关联', 'GUANLIAN', '');
INSERT INTO `codegroup` VALUES ('19', 'UNI端口模式', 'UNIPORTMODE', '');
INSERT INTO `codegroup` VALUES ('20', '策略模式', 'STRATEGYMODE', '');
INSERT INTO `codegroup` VALUES ('21', '指配PHB', 'CONRIRMPHB', '');
INSERT INTO `codegroup` VALUES ('22', 'CM', 'BUFFERCM', '');
INSERT INTO `codegroup` VALUES ('23', '工作模式', 'WORKMODE', '');
INSERT INTO `codegroup` VALUES ('25', '端口Tag行为', 'PORTTAGBEHAVIOR', '');
INSERT INTO `codegroup` VALUES ('26', '端口Tag识别', 'TAGRECOGNITION', '');
INSERT INTO `codegroup` VALUES ('27', '端口MAC地址学习', 'MACLEARN', '');
INSERT INTO `codegroup` VALUES ('29', 'VC流量监管使能', 'VCTRAFFICPOLICING', '');
INSERT INTO `codegroup` VALUES ('30', 'oam连通性检测周期', 'OAMCVCYCLE', '以毫秒为单位(ms)');
INSERT INTO `codegroup` VALUES ('32', '管理状态', 'MANAGENESTATUS', '网元管理状态');
INSERT INTO `codegroup` VALUES ('33', '流类型', 'BUFTYPE', 'pmap的流类型');
INSERT INTO `codegroup` VALUES ('34', '端口类型', 'PORTTYPE', '端口类型：uni和nni');
INSERT INTO `codegroup` VALUES ('35', 'E1支路输出时钟源', 'E1LEGOUTPUTCLOCKSRC', '');
INSERT INTO `codegroup` VALUES ('36', 'TDM时钟源', 'TDMCLOCKSRC', '');
INSERT INTO `codegroup` VALUES ('38', '发送V5字节', 'SENDV5', '');
INSERT INTO `codegroup` VALUES ('39', 'SFP期望类型', 'sfptype', '');
INSERT INTO `codegroup` VALUES ('40', '线路编码', 'LINECODE', '线路编码');
INSERT INTO `codegroup` VALUES ('41', 'CES业务类型', 'CESSERVICETYPE', '创建CES业务是的4种业务类型');
INSERT INTO `codegroup` VALUES ('42', '拓扑类型', 'TOPOTYPE', '');
INSERT INTO `codegroup` VALUES ('43', 'OSPF边界路由器兼容类型', 'OSPF边界路由器兼容类型', 'OSPF边界路由器兼容类型');
INSERT INTO `codegroup` VALUES ('44', 'OSPFAREA区域类型', 'OSPFAREA区域类型', 'OSPFAREA区域类型');
INSERT INTO `codegroup` VALUES ('45', 'tunnel角色', 'TUNNELROLE', '');
INSERT INTO `codegroup` VALUES ('46', 'pw单网元类型', 'PWTYPESITE', '');
INSERT INTO `codegroup` VALUES ('47', 'CES单网元业务类型', 'CESNODETYPE', '');
INSERT INTO `codegroup` VALUES ('48', '重分发类型', 'REDISTRIBUTIONTYPE', '');
INSERT INTO `codegroup` VALUES ('49', '重分发获取来源', 'DISTRIBUTESOURCE', '');
INSERT INTO `codegroup` VALUES ('50', '路由类型', 'ROUTETYPE', '');
INSERT INTO `codegroup` VALUES ('51', 'ETREE业务类型', 'ETREETYPE', '');
INSERT INTO `codegroup` VALUES ('52', 'OSPFInterface类型', 'OSPFINTERFACETYPE', '');
INSERT INTO `codegroup` VALUES ('53', 'OSPFInterface认证类型', 'AUYHENTICATIONTYPE', '');
INSERT INTO `codegroup` VALUES ('57', '链路聚合端口报文方法算法', 'LAGMODE', '');
INSERT INTO `codegroup` VALUES ('58', '以太网封装', 'LAGNETWRAP', '');
INSERT INTO `codegroup` VALUES ('59', '运营商VLAN_TPID', 'LAGVLANTPID', '');
INSERT INTO `codegroup` VALUES ('60', 'OUTER VLAN的TPID', 'LAGOUTERTPID', '');
INSERT INTO `codegroup` VALUES ('61', '以太网VLAN模式', 'LAGNETVLANMODE', '');
INSERT INTO `codegroup` VALUES ('62', '启用选择协议', 'enableSelectedProtocol', '');
INSERT INTO `codegroup` VALUES ('63', '操作模式', 'operationMode', '');
INSERT INTO `codegroup` VALUES ('64', '自由振荡等级', 'freeOscillationLevel', '');
INSERT INTO `codegroup` VALUES ('65', '质量未知映射等级', 'qualityUnknownReflectLevel', '');
INSERT INTO `codegroup` VALUES ('66', '导出时钟模式', 'exportClockMode', '');
INSERT INTO `codegroup` VALUES ('67', '外时钟压制门限', 'externalClockSupressThreshold', '');
INSERT INTO `codegroup` VALUES ('68', '系统时钟恢复模式', 'systemClockRecoveryMode', '');
INSERT INTO `codegroup` VALUES ('69', '收SSM设置值', 'receiveSSMSettingValue', '');
INSERT INTO `codegroup` VALUES ('70', '模式', 'mode', '');
INSERT INTO `codegroup` VALUES ('71', '透传时钟延时机制', 'passThroughClockDelayMechanism', '');
INSERT INTO `codegroup` VALUES ('72', '跟随模式', 'followUpMode', '');
INSERT INTO `codegroup` VALUES ('73', 'tod发送时间类型', 'todTransmissionTimeType', '');
INSERT INTO `codegroup` VALUES ('74', '接口类型', 'interfaceType', '');
INSERT INTO `codegroup` VALUES ('75', '时间戳模式', 'timeStampMode', '');
INSERT INTO `codegroup` VALUES ('76', '延时机制', 'delayMechanism', '');
INSERT INTO `codegroup` VALUES ('78', '操作模式', 'operationModeT', '');
INSERT INTO `codegroup` VALUES ('79', 'PTP时间端口', 'PTPTimePort', '');
INSERT INTO `codegroup` VALUES ('80', '端口SFP期望类型', 'portSfpType', '');
INSERT INTO `codegroup` VALUES ('83', '端口设置速率', 'portSetRate', '');
INSERT INTO `codegroup` VALUES ('84', '校时状态', 'CURRTIME', '');
INSERT INTO `codegroup` VALUES ('85', '配置管理状态', 'MANAGESTATUS', '');
INSERT INTO `codegroup` VALUES ('87', '端口模式', 'portModel', '');
INSERT INTO `codegroup` VALUES ('88', '出口规则', 'exitRule', '');
INSERT INTO `codegroup` VALUES ('89', '方向', 'direction', '');
INSERT INTO `codegroup` VALUES ('90', '类型', 'model', '');
INSERT INTO `codegroup` VALUES ('92', '级别', 'grade', '');
INSERT INTO `codegroup` VALUES ('94', 'PHB策略', 'Policy_PHB', '');
INSERT INTO `codegroup` VALUES ('95', 'EXP策略', 'Policy_EXP', '');
INSERT INTO `codegroup` VALUES ('97', '指配EXP', 'Distribute_PHB', '');
INSERT INTO `codegroup` VALUES ('98', 'PHBAndEXPId', 'PHBAndEXPId', '');
INSERT INTO `codegroup` VALUES ('100', '接口模式', 'intefaceModel', '');
INSERT INTO `codegroup` VALUES ('101', '接口模式two', 'intefaceModelTwo', '');
INSERT INTO `codegroup` VALUES ('102', '输入阻抗', 'inputImpedance', '');
INSERT INTO `codegroup` VALUES ('103', 'SAN比特', 'SANByte', '');
INSERT INTO `codegroup` VALUES ('104', 'siteType', 'siteType', '');
INSERT INTO `codegroup` VALUES ('105', '端口设置速率FE', 'portSetRateFE', null);
INSERT INTO `codegroup` VALUES ('106', '端口设置速率GE', 'portSetRateGE', null);
INSERT INTO `codegroup` VALUES ('107', '端口设置速率XG', 'portSetRateXG', null);
INSERT INTO `codegroup` VALUES ('108', '时钟工作状态', 'CLOCKSTATUS', '');
INSERT INTO `codegroup` VALUES ('109', '外时钟输出状态', 'OUTCLOCKSTATUS', '');
INSERT INTO `codegroup` VALUES ('110', '外时钟输出方式', 'OUTCLOCKMODEL', '');
INSERT INTO `codegroup` VALUES ('111', '外时钟输出类型', 'OUTCLOCKTYPE', '');
INSERT INTO `codegroup` VALUES ('112', 'QL使用SA选择', 'QLSACHOOSE', null);
INSERT INTO `codegroup` VALUES ('113', '当前锁定源', 'LOCKSOURCE', '');
INSERT INTO `codegroup` VALUES ('114', '当前锁定源S1值', 'LOCKSOURCES1', '');
INSERT INTO `codegroup` VALUES ('115', '当前锁定源状态', 'LCOKSOURCESTATUS', '');
INSERT INTO `codegroup` VALUES ('116', 'TC', 'TC', null);
INSERT INTO `codegroup` VALUES ('117', '输入源状态', 'SOURCESTATUS', '');
INSERT INTO `codegroup` VALUES ('118', '模式', 'MODEL', null);
INSERT INTO `codegroup` VALUES ('119', '倒换状态', 'RORATESTATUS', '');
INSERT INTO `codegroup` VALUES ('120', '返回类型', 'BACKTYPE', '');
INSERT INTO `codegroup` VALUES ('121', '自定时间', 'endTime', '');
INSERT INTO `codegroup` VALUES ('122', '负载净荷', 'PAYLOAD', '');
INSERT INTO `codegroup` VALUES ('123', '客户名称', 'clientGrade', '');
INSERT INTO `codegroup` VALUES ('124', '收SSM设置值', 'SSMvalue', '');
INSERT INTO `codegroup` VALUES ('125', 'MP属性', 'MPattribute', '');
INSERT INTO `codegroup` VALUES ('127', 'CCM发送间隔', 'ccmsend', '');
INSERT INTO `codegroup` VALUES ('128', 'MEP类型', 'MEPTYPE', '');
INSERT INTO `codegroup` VALUES ('129', 'LBM数据TLV类型', 'LBMDATATLVTYPE', '');
INSERT INTO `codegroup` VALUES ('130', 'LBM丢弃适用性', 'LBMDISCARD', '');
INSERT INTO `codegroup` VALUES ('131', 'AIS/LCK发送周期', 'AISLCKSENDCYCLE', '');
INSERT INTO `codegroup` VALUES ('132', '发送方式', 'SENDWAY', '');
INSERT INTO `codegroup` VALUES ('133', 'MIP有效性', 'MIPCREATE', '');
INSERT INTO `codegroup` VALUES ('134', '发送周期', 'sendcycle', '');
INSERT INTO `codegroup` VALUES ('135', '时钟类型', 'clockType', '');
INSERT INTO `codegroup` VALUES ('136', 'MSP保护类型', 'MSPPROTECTTYPE', '1:1和1+1   user-kk');
INSERT INTO `codegroup` VALUES ('137', 'MSP恢复模式', 'RECOVERYMODE', '可恢复和不可恢复   user-kk');
INSERT INTO `codegroup` VALUES ('138', '高或低', 'HIGHLOW', '高和低  user--kk');
INSERT INTO `codegroup` VALUES ('139', 'APS模式', 'APSMODEL', '');
INSERT INTO `codegroup` VALUES ('141', 'TPID', 'TPID', '');
INSERT INTO `codegroup` VALUES ('142', '基于内层VlanId', 'basedInVlanId', '');
INSERT INTO `codegroup` VALUES ('144', 'OAM工作模式', 'WORKMODELOFOAM', '');
INSERT INTO `codegroup` VALUES ('145', '发包周期', 'CONTRACTPERIOD', '');
INSERT INTO `codegroup` VALUES ('146', '角色', 'ROLE', '');
INSERT INTO `codegroup` VALUES ('147', '倒换方式', 'ROTATEWAY', '');
INSERT INTO `codegroup` VALUES ('148', 'EXP类型', 'EXPTYPE', null);
INSERT INTO `codegroup` VALUES ('149', '方向', 'EXPDIRECTION', null);
INSERT INTO `codegroup` VALUES ('150', 'VLANPRI', 'VLANPRI', 'vlanpri优先级');
INSERT INTO `codegroup` VALUES ('151', '以太网OAM', 'ENABLEDSTATUEOAM', '');
INSERT INTO `codegroup` VALUES ('152', '转发动作', 'TransmitAction', null);
INSERT INTO `codegroup` VALUES ('153', '出口动作', 'outAction', null);
INSERT INTO `codegroup` VALUES ('154', '光模块类型', 'SFPModel', null);
INSERT INTO `codegroup` VALUES ('155', '连接器类型', 'SFPConnectorType', null);
INSERT INTO `codegroup` VALUES ('156', '传输介质', 'SFPTransMedia', null);
INSERT INTO `codegroup` VALUES ('157', 'Sync报文周期', 'SyncPtpPort', null);
INSERT INTO `codegroup` VALUES ('158', '工作状态', 'workStatus', null);
INSERT INTO `codegroup` VALUES ('159', '以太网环回', 'LOOPRULE', '以太网环回');
INSERT INTO `codegroup` VALUES ('160', 'ACL动作', 'ACLACT', '');
INSERT INTO `codegroup` VALUES ('161', 'ACL入口方向', 'ACLDIRECTION', '');
INSERT INTO `codegroup` VALUES ('162', 'ACL规则应用对象', 'ACLRULEOBJECT', '');
INSERT INTO `codegroup` VALUES ('163', 'MAC学习限制模式 ', 'MACLEARNMODEL', null);
INSERT INTO `codegroup` VALUES ('164', '名单模式', 'NAMELIST', '');
INSERT INTO `codegroup` VALUES ('165', '本端OAM使能', 'OAMEnabled', '');
INSERT INTO `codegroup` VALUES ('166', '本端OAM模式', 'OAMMODEL', '');
INSERT INTO `codegroup` VALUES ('167', '本端OAM协商态', 'OAMNegotiatedState', '');
INSERT INTO `codegroup` VALUES ('168', '本端OAM链路状态', 'OAMlinkStatus', '');
INSERT INTO `codegroup` VALUES ('169', '本端环回解析状态', 'localAnaysisLoopStatus', '');
INSERT INTO `codegroup` VALUES ('170', '业务类型', 'BUSINESSTYPE', null);
INSERT INTO `codegroup` VALUES ('171', '本端OAM端口fault状态', 'OAMPORTFAULTSTATUS', null);
INSERT INTO `codegroup` VALUES ('172', '业务类型', 'BUSINESSTYPE', null);
INSERT INTO `codegroup` VALUES ('173', '聚合工作组模式', 'WORKMODEL', null);
INSERT INTO `codegroup` VALUES ('174', '皖通设备类型', 'ZTEEDITION', '');
INSERT INTO `codegroup` VALUES ('175', '外层TP_ID', 'TP_ID', null);
INSERT INTO `codegroup` VALUES ('176', '定时模式', 'TIMEMODEL', null);
INSERT INTO `codegroup` VALUES ('177', '晨晓端口使能状态', 'ENABLEDSTATUECX', '');
INSERT INTO `codegroup` VALUES ('178', '历史性能统计', 'hisPerformCount', null);
INSERT INTO `codegroup` VALUES ('179', '业务端口环回状态', 'SERVICELOOPSTATE', '');
INSERT INTO `codegroup` VALUES ('180', '封装类型', 'FZTYPE', null);
INSERT INTO `codegroup` VALUES ('181', '成帧格式', 'frameformat', null);
INSERT INTO `codegroup` VALUES ('182', '复帧格式', 'complexFrame', null);
INSERT INTO `codegroup` VALUES ('183', '传输层协议类型', 'TREATYTYPE', '');
INSERT INTO `codegroup` VALUES ('184', '当前锁定源类型', 'CURRCLOCKTYPE', null);
INSERT INTO `codegroup` VALUES ('185', '端口类型', 'TCPPORTTYPE', null);
INSERT INTO `codegroup` VALUES ('186', 'DHCP模式', 'DHCPMODEL', null);
INSERT INTO `codegroup` VALUES ('187', '协议类型', 'TRAPYTYPE', null);
INSERT INTO `codegroup` VALUES ('188', '环网状态', 'WRAPPINGSTATUS', null);

-- ----------------------------
-- Table structure for `current_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `current_alarm`;
CREATE TABLE `current_alarm` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteid` int(8) DEFAULT NULL,
  `slotId` int(8) DEFAULT NULL,
  `objectid` int(8) DEFAULT NULL,
  `objecttype` int(8) DEFAULT NULL,
  `objectname` varchar(200) DEFAULT NULL,
  `alarmcode` int(8) DEFAULT NULL,
  `alarmlevel` int(8) DEFAULT NULL,
  `happenedtime` varchar(200) DEFAULT NULL,
  `confirmtime` varchar(200) DEFAULT NULL,
  `clearedtime` varchar(200) DEFAULT NULL,
  `ackuser` varchar(200) DEFAULT NULL,
  `comments` varchar(1000) DEFAULT NULL,
  `capabilityCode` int(11) DEFAULT NULL,
  `capabilityIdentity` varchar(255) DEFAULT NULL,
  `alarmlevel_temp` int(8) DEFAULT NULL,
  `isCleared` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of current_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for `dualprotect`
-- ----------------------------
DROP TABLE IF EXISTS `dualprotect`;
CREATE TABLE `dualprotect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protectType` int(11) DEFAULT NULL,
  `regainModel` int(11) DEFAULT NULL,
  `apsEnable` int(11) DEFAULT NULL,
  `waitTime` int(11) DEFAULT NULL,
  `lagId` int(11) DEFAULT NULL,
  `rotateWay` int(11) DEFAULT NULL,
  `dualRelevanceGroupId` int(11) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  `dualStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dualprotect
-- ----------------------------

-- ----------------------------
-- Table structure for `dualrelevance`
-- ----------------------------
DROP TABLE IF EXISTS `dualrelevance`;
CREATE TABLE `dualrelevance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objId` int(11) DEFAULT NULL,
  `dualGroupId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `objType` varchar(200) DEFAULT NULL,
  `tunnelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dualrelevance
-- ----------------------------

-- ----------------------------
-- Table structure for `e1leg_inst`
-- ----------------------------
DROP TABLE IF EXISTS `e1leg_inst`;
CREATE TABLE `e1leg_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `E1Id` int(11) NOT NULL,
  `legShield` int(11) NOT NULL,
  `legEnable` int(11) NOT NULL,
  `prestoreTimeEnable` int(11) NOT NULL,
  `prestoreTime` int(11) NOT NULL,
  `pinCount` int(11) NOT NULL,
  `pwLable` int(11) NOT NULL,
  `legId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e1leg_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `e1_inst`
-- ----------------------------
DROP TABLE IF EXISTS `e1_inst`;
CREATE TABLE `e1_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `outputClockSrc` int(11) DEFAULT NULL,
  `tdmClocksrc` int(11) DEFAULT NULL,
  `rtpEnable` int(11) DEFAULT NULL,
  `E1Id` int(11) DEFAULT NULL,
  `legShield` int(11) DEFAULT NULL,
  `legEnable` int(11) DEFAULT NULL,
  `prestoreTimeEnable` int(11) DEFAULT NULL,
  `prestoreTime` int(11) DEFAULT NULL,
  `pinCount` int(11) DEFAULT NULL,
  `pwLable` int(11) DEFAULT NULL,
  `legId` int(11) DEFAULT NULL,
  `portName` varchar(200) DEFAULT NULL,
  `modle` varchar(200) DEFAULT NULL,
  `linecoding` varchar(200) DEFAULT NULL,
  `impedance` int(11) DEFAULT NULL,
  `portid` int(11) DEFAULT NULL,
  `cardid` int(11) DEFAULT NULL,
  `alarmReversalEnabled` int(11) DEFAULT NULL,
  `fzType` int(11) DEFAULT NULL,
  `frameFormat` int(11) DEFAULT NULL,
  `complexFrame` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e1_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `ecn_ccn`
-- ----------------------------
DROP TABLE IF EXISTS `ecn_ccn`;
CREATE TABLE `ecn_ccn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `neId` varchar(11) DEFAULT NULL,
  `ccnName` varchar(10) DEFAULT NULL,
  `admin` varchar(10) DEFAULT NULL,
  `oper` varchar(10) DEFAULT NULL,
  `ip` varchar(25) DEFAULT NULL,
  `peer` varchar(25) DEFAULT NULL,
  `mtu` varchar(10) DEFAULT NULL,
  `portname` varchar(200) DEFAULT NULL COMMENT '承载端口名称',
  `status` int(11) DEFAULT NULL COMMENT '激活状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ecn_ccn
-- ----------------------------

-- ----------------------------
-- Table structure for `ecn_interface`
-- ----------------------------
DROP TABLE IF EXISTS `ecn_interface`;
CREATE TABLE `ecn_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `neId` varchar(20) DEFAULT NULL,
  `interfaceName` varchar(20) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `hello_interval` int(11) DEFAULT NULL,
  `dead_interval` int(11) DEFAULT NULL,
  `retransmit_interval` int(11) DEFAULT NULL,
  `transmit_delay` int(11) DEFAULT NULL,
  `passive` tinyint(4) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `prioriy` int(11) DEFAULT NULL,
  `authentication_type` varchar(20) DEFAULT NULL,
  `activeStatus` int(11) DEFAULT NULL,
  `OSPFStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ecn_interface
-- ----------------------------

-- ----------------------------
-- Table structure for `ecn_mcn`
-- ----------------------------
DROP TABLE IF EXISTS `ecn_mcn`;
CREATE TABLE `ecn_mcn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `neId` varchar(20) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `mtu` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ecn_mcn
-- ----------------------------

-- ----------------------------
-- Table structure for `equip_inst`
-- ----------------------------
DROP TABLE IF EXISTS `equip_inst`;
CREATE TABLE `equip_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  `equipx` int(11) DEFAULT NULL,
  `equipy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of equip_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `ethernetoam`
-- ----------------------------
DROP TABLE IF EXISTS `ethernetoam`;
CREATE TABLE `ethernetoam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `thernetOAMEnabl` int(11) DEFAULT NULL,
  `mdMLevel` varchar(200) DEFAULT NULL,
  `mpLable` int(11) DEFAULT NULL,
  `mdName` varchar(200) DEFAULT NULL,
  `mdLevel` varchar(200) DEFAULT NULL,
  `maName` varchar(200) DEFAULT NULL,
  `ccmsend` int(11) DEFAULT NULL,
  `vlan` varchar(200) DEFAULT NULL,
  `mepId` varchar(200) DEFAULT NULL,
  `mepType` int(11) DEFAULT NULL,
  `port` varchar(200) DEFAULT NULL,
  `ccmSendEnable` int(11) DEFAULT NULL,
  `ccmReceiveEnable` int(11) DEFAULT NULL,
  `ccmPriority` varchar(200) DEFAULT NULL,
  `lbmTlvType` int(11) DEFAULT NULL,
  `lbmTlvTypeLength` varchar(200) DEFAULT NULL,
  `lbmPriority` varchar(200) DEFAULT NULL,
  `lbmDiscard` int(11) DEFAULT NULL,
  `ltmPriority` varchar(200) DEFAULT NULL,
  `aisSendEnabel` int(11) DEFAULT NULL,
  `clientMdLevel` varchar(200) DEFAULT NULL,
  `aisPriority` varchar(200) DEFAULT NULL,
  `lckSendEnabel` int(11) DEFAULT NULL,
  `lckPriority` varchar(200) DEFAULT NULL,
  `aisLckSendCycle` int(11) DEFAULT NULL,
  `tstSendEnabel` int(11) DEFAULT NULL,
  `tstSendLevel` varchar(200) DEFAULT NULL,
  `tstPurposeMepMac` varchar(200) DEFAULT NULL,
  `tstPriority` varchar(200) DEFAULT NULL,
  `sendWay` int(11) DEFAULT NULL,
  `tstDiscard` int(11) DEFAULT NULL,
  `tstTlvType` int(11) DEFAULT NULL,
  `tstTlvLength` varchar(200) DEFAULT NULL,
  `tstSendCycle` int(11) DEFAULT NULL,
  `lmENable` int(11) DEFAULT NULL,
  `lmPriority` varchar(200) DEFAULT NULL,
  `lmSendCycle` int(11) DEFAULT NULL,
  `dmENable` int(11) DEFAULT NULL,
  `dmPriority` varchar(200) DEFAULT NULL,
  `dmSendCycle` int(11) DEFAULT NULL,
  `remoteMepId1` varchar(200) DEFAULT NULL,
  `macAddress1` varchar(200) DEFAULT NULL,
  `remoteMepId2` varchar(200) DEFAULT NULL,
  `macAddress2` varchar(200) DEFAULT NULL,
  `remoteMepId3` varchar(200) DEFAULT NULL,
  `macAddress3` varchar(200) DEFAULT NULL,
  `remoteMepId4` varchar(200) DEFAULT NULL,
  `macAddress4` varchar(200) DEFAULT NULL,
  `mipCreate` int(11) DEFAULT NULL,
  `mipPort` varchar(200) DEFAULT NULL,
  `slot` int(11) DEFAULT NULL,
  `mipSlot` int(11) DEFAULT NULL,
  `itemNumber` int(11) DEFAULT NULL,
  `PortName` varchar(200) DEFAULT NULL,
  `mipPortName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ethernetoam
-- ----------------------------

-- ----------------------------
-- Table structure for `ethloop`
-- ----------------------------
DROP TABLE IF EXISTS `ethloop`;
CREATE TABLE `ethloop` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteid` int(8) DEFAULT NULL,
  `loopEnable` int(8) DEFAULT NULL,
  `portNumber` int(8) DEFAULT NULL,
  `loopRule` int(8) DEFAULT NULL,
  `vlanVaue` varchar(200) DEFAULT NULL,
  `ip` varchar(200) DEFAULT NULL,
  `macAddress` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ethloop
-- ----------------------------

-- ----------------------------
-- Table structure for `ethservice_inst`
-- ----------------------------
DROP TABLE IF EXISTS `ethservice_inst`;
CREATE TABLE `ethservice_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `vlanId` int(11) DEFAULT NULL,
  `portLine1` int(11) DEFAULT NULL,
  `portLine2` int(11) DEFAULT NULL,
  `portLine3` int(11) DEFAULT NULL,
  `portLine4` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ethservice_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `externalclockinterface`
-- ----------------------------
DROP TABLE IF EXISTS `externalclockinterface`;
CREATE TABLE `externalclockinterface` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `interfaceName` varchar(200) DEFAULT NULL,
  `managingStatus` int(11) DEFAULT NULL,
  `workingStatus` varchar(200) DEFAULT NULL,
  `interfaceMode` int(11) DEFAULT NULL,
  `inputImpedance` int(11) DEFAULT NULL,
  `sanBits` int(11) DEFAULT NULL,
  `timeOffsetCompensation` varchar(200) DEFAULT NULL,
  `activeStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of externalclockinterface
-- ----------------------------

-- ----------------------------
-- Table structure for `field`
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FieldName` varchar(200) DEFAULT NULL,
  `FieldX` int(11) DEFAULT NULL,
  `FieldY` int(11) DEFAULT NULL,
  `mSiteId` int(11) DEFAULT NULL,
  `objectType` varchar(200) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  `workIP` varchar(200) DEFAULT NULL,
  `netWorkId` int(11) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field
-- ----------------------------

-- ----------------------------
-- Table structure for `frequencymanageclock`
-- ----------------------------
DROP TABLE IF EXISTS `frequencymanageclock`;
CREATE TABLE `frequencymanageclock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `clockType` int(11) DEFAULT NULL,
  `systemPriorLevel` int(11) DEFAULT NULL,
  `exportPriorLevel` int(11) DEFAULT NULL,
  `physicsState` varchar(200) DEFAULT NULL,
  `logicState` varchar(200) DEFAULT NULL,
  `receiveSSMValue` int(11) DEFAULT NULL,
  `receiveSSMRealityValue` int(11) DEFAULT NULL,
  `SSMSend` int(11) DEFAULT NULL,
  `selectQuelityLevel` varchar(200) DEFAULT NULL,
  `DNUGroup` int(11) DEFAULT NULL,
  `recoverModel` int(11) DEFAULT NULL,
  `externalOrder` varchar(200) DEFAULT NULL,
  `manageState` varchar(200) DEFAULT NULL,
  `jobState` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frequencymanageclock
-- ----------------------------

-- ----------------------------
-- Table structure for `frequencymanage_ne`
-- ----------------------------
DROP TABLE IF EXISTS `frequencymanage_ne`;
CREATE TABLE `frequencymanage_ne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `startTreaty` int(11) DEFAULT NULL,
  `oscillationLevel` int(11) DEFAULT NULL,
  `systemRecoverTime` int(11) DEFAULT NULL,
  `qualityLevel` int(11) DEFAULT NULL,
  `exportRecoverTime` int(11) DEFAULT NULL,
  `exportClockModel` int(11) DEFAULT NULL,
  `handleModel` int(11) DEFAULT NULL,
  `clockSuppress` int(11) DEFAULT NULL,
  `systemJobStatus` varchar(200) DEFAULT NULL,
  `systemSourcce` varchar(200) DEFAULT NULL,
  `exportJobStatus` varchar(200) DEFAULT NULL,
  `exportSourcce` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frequencymanage_ne
-- ----------------------------

-- ----------------------------
-- Table structure for `group_spread`
-- ----------------------------
DROP TABLE IF EXISTS `group_spread`;
CREATE TABLE `group_spread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `smId` int(11) DEFAULT NULL,
  `vsId` int(11) DEFAULT NULL,
  `portChoose` varchar(200) DEFAULT NULL,
  `macAddress` varchar(200) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_spread
-- ----------------------------

-- ----------------------------
-- Table structure for `history_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `history_alarm`;
CREATE TABLE `history_alarm` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteid` int(8) DEFAULT NULL,
  `slotId` int(8) DEFAULT NULL,
  `objectid` int(8) DEFAULT NULL,
  `objecttype` int(8) DEFAULT NULL,
  `objectname` varchar(200) DEFAULT NULL,
  `alarmcode` int(8) DEFAULT NULL,
  `alarmlevel` int(8) DEFAULT NULL,
  `happenedtime` varchar(200) DEFAULT NULL,
  `confirmtime` varchar(200) DEFAULT NULL,
  `clearedtime` varchar(200) DEFAULT NULL,
  `ackuser` varchar(200) DEFAULT NULL,
  `comments` varchar(1000) DEFAULT NULL,
  `alarmlevel_temp` int(8) DEFAULT NULL,
  `isCleared` int(11) DEFAULT NULL,
  `alarmID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history_alarm
-- ----------------------------

-- ----------------------------
-- Table structure for `history_performance`
-- ----------------------------
DROP TABLE IF EXISTS `history_performance`;
CREATE TABLE `history_performance` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteId` int(8) DEFAULT NULL,
  `slotId` int(8) DEFAULT NULL,
  `objectid` int(8) DEFAULT NULL,
  `objectname` varchar(200) DEFAULT NULL,
  `objecttype` int(8) DEFAULT NULL,
  `performancecode` int(8) DEFAULT NULL,
  `performancevalue` float(20,0) DEFAULT NULL,
  `performancetime` varchar(200) DEFAULT NULL,
  `performanceEndtime` varchar(200) DEFAULT NULL,
  `taskId` int(8) DEFAULT NULL,
  `isCardOrSite` int(8) DEFAULT NULL,
  `startTime` varchar(200) DEFAULT NULL,
  `monitor` int(11) DEFAULT NULL COMMENT '粒度周期，1 ：15分钟，2 ：24小时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history_performance
-- ----------------------------

-- ----------------------------
-- Table structure for `l2cp_inst`
-- ----------------------------
DROP TABLE IF EXISTS `l2cp_inst`;
CREATE TABLE `l2cp_inst` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteid` int(8) DEFAULT NULL,
  `l2cpEnable` int(8) DEFAULT NULL,
  `bpduTreaty` int(8) DEFAULT NULL,
  `lldpTreaty` int(8) DEFAULT NULL,
  `lacpTreaty` int(8) DEFAULT NULL,
  `ieeeTreaty` int(8) DEFAULT NULL,
  `macAddress` varchar(200) DEFAULT NULL,
  `treatyNumber` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l2cp_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `labelinfo`
-- ----------------------------
DROP TABLE IF EXISTS `labelinfo`;
CREATE TABLE `labelinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lsrId` int(11) DEFAULT NULL,
  `labelValue` int(11) DEFAULT NULL,
  `labelStatus` int(11) DEFAULT NULL,
  `siteid` int(11) DEFAULT NULL COMMENT '网元表主键',
  `type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of labelinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `lagport_inst`
-- ----------------------------
DROP TABLE IF EXISTS `lagport_inst`;
CREATE TABLE `lagport_inst` (
  `lagid` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `lagMode` int(11) DEFAULT NULL,
  PRIMARY KEY (`lagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lagport_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `login_log`
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `loginTime` varchar(255) DEFAULT NULL,
  `outTime` varchar(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `loginState` int(11) DEFAULT NULL,
  `logoutState` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `loop_protect`
-- ----------------------------
DROP TABLE IF EXISTS `loop_protect`;
CREATE TABLE `loop_protect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protectType` int(11) DEFAULT NULL,
  `westSlot` int(11) DEFAULT NULL,
  `westPort` int(11) DEFAULT NULL,
  `eastSlot` int(11) DEFAULT NULL,
  `eastPort` int(11) DEFAULT NULL,
  `loopNodeNumber` int(11) DEFAULT NULL,
  `nodeId` int(11) DEFAULT NULL,
  `logicId` int(11) DEFAULT NULL,
  `waittime` int(11) DEFAULT NULL,
  `delaytime` int(11) DEFAULT NULL,
  `apsenable` int(11) DEFAULT NULL,
  `backType` int(11) DEFAULT NULL,
  `loopId` int(11) DEFAULT NULL,
  `targetNodeId` int(11) DEFAULT NULL,
  `westLspId` int(11) DEFAULT NULL,
  `eastLspId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `createUser` varchar(200) DEFAULT NULL,
  `createTime` varchar(200) DEFAULT NULL,
  `isSingle` int(11) DEFAULT NULL,
  `activeStatus` int(11) DEFAULT NULL,
  `westNodeId` int(11) DEFAULT NULL,
  `loopBusinessId` int(11) DEFAULT NULL,
  `eastNodeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loop_protect
-- ----------------------------

-- ----------------------------
-- Table structure for `lspinfo`
-- ----------------------------
DROP TABLE IF EXISTS `lspinfo`;
CREATE TABLE `lspinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) DEFAULT NULL,
  `pathStatus` int(11) DEFAULT NULL,
  `aSiteId` int(11) DEFAULT NULL,
  `zSiteId` int(11) DEFAULT NULL,
  `aPortId` int(11) DEFAULT NULL,
  `zPortId` int(11) DEFAULT NULL,
  `frontLabelValue` int(11) DEFAULT NULL,
  `backLabelValue` int(11) DEFAULT NULL,
  `alspbusinessid` int(11) DEFAULT NULL,
  `zlspbusinessid` int(11) DEFAULT NULL,
  `atunnelbusinessid` int(11) DEFAULT NULL,
  `ztunnelbusinessid` int(11) DEFAULT NULL,
  `segmentId` int(11) DEFAULT NULL,
  `aoppositeId` varchar(200) DEFAULT NULL COMMENT 'A端对端网元ID',
  `zoppositeId` varchar(200) DEFAULT NULL COMMENT 'Z端对端网元ID',
  `sourceMac` varchar(200) DEFAULT NULL,
  `targetMac` varchar(200) DEFAULT NULL,
  `frontTtl` int(8) DEFAULT NULL,
  `backTtl` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lspinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `maclearninglimit`
-- ----------------------------
DROP TABLE IF EXISTS `maclearninglimit`;
CREATE TABLE `maclearninglimit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) NOT NULL,
  `portId` int(11) NOT NULL,
  `model` int(11) DEFAULT NULL,
  `vlanId` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maclearninglimit
-- ----------------------------

-- ----------------------------
-- Table structure for `mappingrelation`
-- ----------------------------
DROP TABLE IF EXISTS `mappingrelation`;
CREATE TABLE `mappingrelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tableId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `portId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mappingrelation
-- ----------------------------

-- ----------------------------
-- Table structure for `mappingtemplate`
-- ----------------------------
DROP TABLE IF EXISTS `mappingtemplate`;
CREATE TABLE `mappingtemplate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `mappingtype` int(11) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `value` varchar(200) DEFAULT NULL,
  `groupid` int(11) DEFAULT NULL,
  `color` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mappingtemplate
-- ----------------------------

-- ----------------------------
-- Table structure for `mspprotect`
-- ----------------------------
DROP TABLE IF EXISTS `mspprotect`;
CREATE TABLE `mspprotect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessId` int(11) DEFAULT NULL,
  `protectType` int(11) DEFAULT NULL,
  `recoveryMode` int(11) DEFAULT NULL,
  `workPortId` int(11) DEFAULT NULL,
  `ProtectPortId` int(11) DEFAULT NULL,
  `WaitTime` int(11) DEFAULT NULL,
  `delayTime` int(11) DEFAULT NULL,
  `sfPriority` int(11) DEFAULT NULL,
  `sdPriority` int(11) DEFAULT NULL,
  `apsEnable` int(11) DEFAULT NULL,
  `sdEnable` int(11) DEFAULT NULL,
  `ProtectStatus` varchar(255) DEFAULT NULL,
  `NowWorkPortId` int(11) DEFAULT NULL,
  `rotateOrder` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `mspStatus` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mspprotect
-- ----------------------------

-- ----------------------------
-- Table structure for `mspwinfo`
-- ----------------------------
DROP TABLE IF EXISTS `mspwinfo`;
CREATE TABLE `mspwinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) NOT NULL,
  `pwId` int(11) DEFAULT NULL,
  `frontTunnelId` int(11) DEFAULT NULL,
  `backTunnelId` int(11) DEFAULT NULL,
  `frontInlabel` int(11) DEFAULT NULL,
  `frontOutlabel` int(11) DEFAULT NULL,
  `backInlabel` int(11) DEFAULT NULL,
  `backOutlabel` int(11) DEFAULT NULL,
  `mipId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mspwinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `network`
-- ----------------------------
DROP TABLE IF EXISTS `network`;
CREATE TABLE `network` (
  `netWorkId` int(11) NOT NULL AUTO_INCREMENT,
  `netX` int(11) DEFAULT NULL,
  `netY` int(11) DEFAULT NULL,
  `isDeleteTopo` int(11) DEFAULT NULL,
  `netWorkName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`netWorkId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of network
-- ----------------------------

-- ----------------------------
-- Table structure for `oamlinkinfo`
-- ----------------------------
DROP TABLE IF EXISTS `oamlinkinfo`;
CREATE TABLE `oamlinkinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `objId` int(11) DEFAULT NULL,
  `objType` varchar(8) DEFAULT NULL,
  `oamEnable` int(11) DEFAULT '0',
  `mode` int(11) DEFAULT '0',
  `mib` int(11) DEFAULT '0',
  `errorFrameEvent` int(11) DEFAULT '0',
  `lpb` int(11) DEFAULT '0',
  `unDirection` int(11) DEFAULT '0',
  `reserve1` int(11) DEFAULT NULL,
  `reserve2` int(11) DEFAULT NULL,
  `maxFrameLength` int(11) DEFAULT NULL,
  `responseOutTimeThreshold` int(11) DEFAULT NULL,
  `errorSymboEventCycle` int(11) DEFAULT NULL,
  `errorSymboEventThreshold` int(11) DEFAULT NULL,
  `errorFrameEventCycle` int(11) DEFAULT NULL,
  `errorFrameEventThreshold` int(11) DEFAULT NULL,
  `errorFrameCycleEventCycle` int(11) DEFAULT NULL,
  `errorFrameCycleEventThreshold` int(11) DEFAULT NULL,
  `errorFrameSecondEventCycle` int(11) DEFAULT NULL,
  `errorFrameSecondEventThreshold` int(11) DEFAULT NULL,
  `reserve3` int(11) DEFAULT NULL,
  `remoteLoop` int(11) DEFAULT NULL,
  `linkEvent` int(11) DEFAULT NULL,
  `organicId` int(11) DEFAULT NULL,
  `factoryInfo` int(11) DEFAULT NULL,
  `sendCycle` int(11) DEFAULT NULL,
  `linkfailCycle` int(11) DEFAULT NULL,
  `oamFrame` int(11) DEFAULT NULL,
  `equipExit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oamlinkinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `oammepinfo`
-- ----------------------------
DROP TABLE IF EXISTS `oammepinfo`;
CREATE TABLE `oammepinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `objType` varchar(20) DEFAULT NULL,
  `objId` int(11) DEFAULT NULL,
  `megIcc` varchar(8) DEFAULT NULL,
  `megUmc` varchar(8) DEFAULT NULL,
  `mel` int(11) DEFAULT '5',
  `reserve1` int(11) DEFAULT '0',
  `reserve2` int(11) DEFAULT '0',
  `localMepId` int(11) DEFAULT NULL,
  `remoteMepId` int(11) DEFAULT NULL,
  `lpbOutTime` double DEFAULT NULL,
  `targetMacAdd` varchar(16) DEFAULT NULL,
  `lck` int(11) DEFAULT '0',
  `lm` int(11) DEFAULT '0',
  `lmCycle` int(11) DEFAULT NULL,
  `lmReserve1` int(11) DEFAULT NULL,
  `dm` int(11) DEFAULT '0',
  `dmCycle` int(11) DEFAULT NULL,
  `dmReserve1` int(11) DEFAULT NULL,
  `reserve3` int(11) DEFAULT NULL,
  `reserve4` int(11) DEFAULT NULL,
  `reserve5` int(11) DEFAULT NULL,
  `cv` int(11) DEFAULT '0',
  `cvCycle` int(11) DEFAULT NULL,
  `cvReserve1` int(11) DEFAULT NULL,
  `aps` int(11) DEFAULT '0',
  `ssm` int(11) DEFAULT '0',
  `sccTest` int(11) DEFAULT '0',
  `fdi` int(11) DEFAULT '0',
  `ringEnable` int(11) DEFAULT NULL,
  `ringCycle` int(11) DEFAULT NULL,
  `ringTestWay` int(11) DEFAULT NULL,
  `offLineTestTLV` int(11) DEFAULT NULL,
  `ringTLVLength` int(11) DEFAULT NULL,
  `ringTLVInfo` int(11) DEFAULT NULL,
  `tstEnable` int(11) DEFAULT NULL,
  `tstCycle` int(11) DEFAULT NULL,
  `tstTLVType` int(11) DEFAULT NULL,
  `tstTLVLength` int(11) DEFAULT NULL,
  `lspTc` int(11) DEFAULT NULL,
  `csfEnable` int(11) DEFAULT NULL,
  `pwTc` int(11) DEFAULT NULL,
  `megid` int(11) DEFAULT NULL,
  `ltEXP` int(11) DEFAULT NULL,
  `ltTTL` int(11) DEFAULT NULL,
  `ltEnable` int(11) DEFAULT NULL,
  `lbTTL` int(11) DEFAULT NULL,
  `vlanEnable` int(11) DEFAULT NULL,
  `outVlanValue` int(11) DEFAULT NULL,
  `tpId` int(11) DEFAULT NULL,
  `sourceMac` varchar(200) DEFAULT NULL,
  `endMac` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oammepinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `oammipinfo`
-- ----------------------------
DROP TABLE IF EXISTS `oammipinfo`;
CREATE TABLE `oammipinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `objType` varchar(8) DEFAULT NULL,
  `objId` int(11) DEFAULT NULL,
  `megIcc` varchar(8) DEFAULT NULL,
  `megUmc` varchar(8) DEFAULT NULL,
  `mipId` int(11) DEFAULT NULL,
  `aMId` int(11) DEFAULT NULL,
  `zMId` int(11) DEFAULT NULL,
  `megid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oammipinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `offlinesitebusi`
-- ----------------------------
DROP TABLE IF EXISTS `offlinesitebusi`;
CREATE TABLE `offlinesitebusi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operId` int(11) DEFAULT NULL,
  `operType` int(11) DEFAULT NULL,
  `Action` int(11) DEFAULT NULL,
  `SiteId` int(11) DEFAULT NULL,
  `actionIdentify` varchar(200) DEFAULT NULL,
  `parentBusiId` varchar(200) DEFAULT NULL,
  `portId` int(11) DEFAULT NULL,
  `acId` int(11) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of offlinesitebusi
-- ----------------------------

-- ----------------------------
-- Table structure for `operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `overTime` varchar(255) DEFAULT NULL,
  `operationType` int(11) DEFAULT NULL,
  `operationResult` int(255) DEFAULT NULL,
  `IP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for `ospf_area`
-- ----------------------------
DROP TABLE IF EXISTS `ospf_area`;
CREATE TABLE `ospf_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `neId` varchar(255) DEFAULT NULL,
  `area_range` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `summary` int(11) DEFAULT NULL,
  `metric` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '同步设备时，做标记使用。0 激活与设备相同，1 为激活，库有设备没有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ospf_area
-- ----------------------------

-- ----------------------------
-- Table structure for `ospf_info`
-- ----------------------------
DROP TABLE IF EXISTS `ospf_info`;
CREATE TABLE `ospf_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `neId` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `defmetric` int(255) DEFAULT NULL COMMENT '默认代价值',
  `abr` varchar(255) DEFAULT NULL COMMENT '区域边界路由器兼容类型',
  `refbandwidth` int(255) DEFAULT NULL COMMENT '带宽代价计算权衡值',
  `distance` int(255) DEFAULT NULL COMMENT 'ospf路由距离值',
  `spf_holdtime` int(255) DEFAULT NULL COMMENT '路由计算的计时器(毫妙 )',
  `spf_maxholdtime` int(255) DEFAULT NULL COMMENT '路由计算的最大计时时间(毫妙 )',
  `spf_delay` int(255) DEFAULT NULL COMMENT '路由计算的延时间隔(毫妙 )',
  `refresh_time` int(255) DEFAULT NULL COMMENT '设置生成链路状态通告的间隔(秒)',
  `version` varchar(255) DEFAULT NULL COMMENT 'OSPF 软件版本',
  `compatiblerfc1583` varchar(255) DEFAULT NULL,
  `ospf_area_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ospf_info
-- ----------------------------

-- ----------------------------
-- Table structure for `ospf_redistribute`
-- ----------------------------
DROP TABLE IF EXISTS `ospf_redistribute`;
CREATE TABLE `ospf_redistribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `neId` varchar(11) DEFAULT NULL,
  `redistribute_type` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `metrictype` varchar(50) DEFAULT NULL,
  `metric` int(11) DEFAULT NULL,
  `enable` tinyint(4) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '激活状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ospf_redistribute
-- ----------------------------

-- ----------------------------
-- Table structure for `performanceraminfo`
-- ----------------------------
DROP TABLE IF EXISTS `performanceraminfo`;
CREATE TABLE `performanceraminfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `timeSelect` varchar(11) DEFAULT NULL,
  `timeValue` varchar(11) DEFAULT NULL,
  `ramPerformanceSelect` varchar(11) DEFAULT NULL,
  `ramValue` varchar(200) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of performanceraminfo
-- ----------------------------
INSERT INTO `performanceraminfo` VALUES ('1', 'true', '1', 'true', '1', 'admin');

-- ----------------------------
-- Table structure for `performance_task`
-- ----------------------------
DROP TABLE IF EXISTS `performance_task`;
CREATE TABLE `performance_task` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `siteId` int(8) DEFAULT NULL,
  `objectid` int(8) DEFAULT NULL,
  `objecttype` varchar(8) DEFAULT NULL,
  `threadname` varchar(400) DEFAULT NULL,
  `taskname` varchar(400) DEFAULT NULL,
  `perfortype` varchar(200) DEFAULT NULL,
  `monitorcycle` int(8) DEFAULT NULL,
  `createtime` varchar(200) DEFAULT NULL,
  `endtime` varchar(200) DEFAULT NULL,
  `runstates` int(8) DEFAULT NULL,
  `creater` varchar(200) DEFAULT NULL,
  `taskDescribe` varchar(1000) DEFAULT NULL,
  `slotCard` varchar(1000) DEFAULT NULL,
  `performanceCount` int(8) DEFAULT NULL,
  `performanceBeginCount` int(8) DEFAULT NULL,
  `performanceType` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of performance_task
-- ----------------------------

-- ----------------------------
-- Table structure for `pmlimit`
-- ----------------------------
DROP TABLE IF EXISTS `pmlimit`;
CREATE TABLE `pmlimit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `HighTemp` int(11) DEFAULT NULL,
  `CRCError` int(11) DEFAULT NULL,
  `packetLose` int(11) DEFAULT NULL,
  `tmsWorsen` int(11) DEFAULT NULL,
  `tmsLose` int(11) DEFAULT NULL,
  `receiveBadWrap` int(11) DEFAULT NULL,
  `align` int(11) DEFAULT NULL,
  `lowTemp` int(11) DEFAULT NULL,
  `tmpWorsen` int(11) DEFAULT NULL,
  `tmpLose` int(11) DEFAULT NULL,
  `tmcWorsen` int(11) DEFAULT NULL,
  `tmcLose` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `es` int(11) DEFAULT NULL,
  `SES` int(11) DEFAULT NULL,
  `BBE` int(11) DEFAULT NULL,
  `CSES` int(11) DEFAULT NULL,
  `SFPLaserBias` int(11) DEFAULT NULL,
  `SFPTxPOW` int(11) DEFAULT NULL,
  `SFPRxPOW` int(11) DEFAULT NULL,
  `SFPTemp` int(11) DEFAULT NULL,
  `SFPAlarmTemp` int(11) DEFAULT NULL,
  `FEES` int(11) DEFAULT NULL,
  `FESES` int(11) DEFAULT NULL,
  `FEUAS` int(11) DEFAULT NULL,
  `FECSES` int(11) DEFAULT NULL,
  `cipReBandwidth` int(11) DEFAULT NULL,
  `cipSeBandwidth` int(11) DEFAULT NULL,
  `EthPackLosNear` int(11) DEFAULT NULL,
  `EthPackLosFar` int(11) DEFAULT NULL,
  `EthPackDelayMs` int(11) DEFAULT NULL,
  `TmsRxCv` int(11) DEFAULT NULL,
  `TmsTxCv` int(11) DEFAULT NULL,
  `FeJOT2BadDatagram` int(11) DEFAULT NULL,
  `FeReBadPaPerecent` int(11) DEFAULT NULL,
  `FeSeBadPaPerecent` int(11) DEFAULT NULL,
  `SendLong` int(11) DEFAULT NULL,
  `FeRxRatio` int(11) DEFAULT NULL,
  `RxAlErr` int(11) DEFAULT NULL,
  `RxErr` int(11) DEFAULT NULL,
  `TxErr` int(11) DEFAULT NULL,
  `RxLost` int(11) DEFAULT NULL,
  `RxErrAir` int(11) DEFAULT NULL,
  `RxErrKb` int(11) DEFAULT NULL,
  `TxErrKb` int(11) DEFAULT NULL,
  `RxJabber` int(11) DEFAULT NULL,
  `TxJabber` int(11) DEFAULT NULL,
  `GeJOT2BadDatagram` int(11) DEFAULT NULL,
  `GeReBadPaPerecent` int(11) DEFAULT NULL,
  `GeSeBadPaPerecent` int(11) DEFAULT NULL,
  `GeSendLong` int(11) DEFAULT NULL,
  `GeRxRatio` int(11) DEFAULT NULL,
  `GeRxAlErr` int(11) DEFAULT NULL,
  `GeRxErr` int(11) DEFAULT NULL,
  `GeTxErr` int(11) DEFAULT NULL,
  `GeRxLost` int(11) DEFAULT NULL,
  `GeRxErrAir` int(11) DEFAULT NULL,
  `GeRxErrKb` int(11) DEFAULT NULL,
  `GeTxErrKb` int(11) DEFAULT NULL,
  `GeRxJabber` int(11) DEFAULT NULL,
  `GeTxJabber` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pmlimit
-- ----------------------------

-- ----------------------------
-- Table structure for `portattr`
-- ----------------------------
DROP TABLE IF EXISTS `portattr`;
CREATE TABLE `portattr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL COMMENT '网元表外键',
  `portId` int(11) DEFAULT NULL COMMENT '对应端口表主键',
  `portSpeed` int(11) DEFAULT NULL COMMENT '设置端口速率，对应code表主键',
  `actualSpeed` varchar(255) DEFAULT NULL COMMENT '实际速率',
  `workModel` int(11) DEFAULT NULL COMMENT '工作模式，对应code表主键',
  `maxFrameSize` varchar(255) DEFAULT NULL COMMENT '最大帧长=武汉MTU',
  `fluidControl` int(11) DEFAULT NULL COMMENT '流控=武汉802.3流控',
  `exitLimit` varchar(255) DEFAULT NULL COMMENT '出口限速',
  `entranceLimit` varchar(255) DEFAULT NULL COMMENT '入口限速',
  `slowAgreement` int(11) DEFAULT NULL COMMENT '启动慢协议 0=false 1=true',
  `tenWan` int(11) DEFAULT NULL COMMENT '启动10G WAN 0=false 1=true',
  `messageLoopback` int(11) DEFAULT NULL COMMENT '是否启动报文环回 0=false 1=true',
  `ethernetPackaging` int(11) DEFAULT NULL COMMENT '以太网封装 对应code表主键',
  `vlanTpId` int(11) DEFAULT NULL COMMENT '运营商vlantpid 对应code表主键',
  `outerVlanTpId` int(11) DEFAULT NULL COMMENT 'outer vlan tp id，对应code表主键',
  `vlanMode` int(11) DEFAULT NULL COMMENT '以太网vlan模式，对应code表主键',
  `isBroadcast` int(11) DEFAULT NULL COMMENT '广播包抑制开关 0=false 1=true',
  `broadcast` varchar(255) DEFAULT NULL COMMENT '广播报文抑制=武汉 广播包抑制',
  `isUnicast` int(11) DEFAULT NULL COMMENT '洪泛包抑制开关 0=false 1=true',
  `unicast` varchar(255) DEFAULT NULL COMMENT '单播报文抑制=洪泛包抑制',
  `isMulticast` int(11) DEFAULT NULL COMMENT '组播包抑制开关 0=false 1=true',
  `multicast` varchar(255) DEFAULT NULL COMMENT '组播报文抑制=组播包抑制',
  `vlanId` varchar(255) DEFAULT NULL COMMENT '缺省vlanid',
  `vlanPri` varchar(255) DEFAULT NULL COMMENT '缺省vlan优先级',
  `vlanRelevance` int(11) DEFAULT NULL COMMENT 'VLAN关联设置 0=false 1=true',
  `eightIpRelevance` int(11) DEFAULT NULL COMMENT '802.iP关联设置 0=false 1=true',
  `sourceMacRelevance` int(11) DEFAULT NULL COMMENT '源MAC地址关联设置 0=false 1=true',
  `destinationMacRelevance` int(11) DEFAULT NULL COMMENT '目的MAC地址关联设置 0=false 1=true',
  `sourceIpRelevance` int(11) DEFAULT NULL COMMENT '源IP地址关联设置 0=false 1=true',
  `destinationIpRelevance` int(11) DEFAULT NULL COMMENT '目的IP地址关联设置 0=false 1=true',
  `pwRelevance` int(11) DEFAULT NULL COMMENT 'pw关联设置 0=false 1=true',
  `dscpRelevance` int(11) DEFAULT NULL COMMENT 'dscp关联设置 0=false 1=true',
  `staticMac` varchar(255) DEFAULT NULL COMMENT '静态MAC地址',
  `neighbourId` varchar(255) DEFAULT NULL COMMENT '邻居网元ID',
  `operMac` varchar(255) DEFAULT NULL COMMENT '对端接口mac地址',
  `operId` varchar(255) DEFAULT NULL COMMENT '对端接口ID',
  `neighbourFind` int(11) DEFAULT NULL COMMENT '邻居发现状态 对应code表主键',
  `ccnEnable` int(11) DEFAULT NULL COMMENT 'ccn承载使能 0=false 1=true',
  `nniVlanid` varchar(255) DEFAULT NULL COMMENT '缺省vlanid',
  `nniVlanpri` varchar(255) DEFAULT NULL COMMENT '缺省vlan优先级',
  `sfpExpectType` int(11) DEFAULT NULL COMMENT 'SFP期望类型 对应code表主键',
  `sfpActual` varchar(255) DEFAULT NULL COMMENT 'SFP实际类型',
  `workWavelength` varchar(255) DEFAULT NULL COMMENT '工作波长',
  `sfpVender` varchar(255) DEFAULT NULL COMMENT 'sfp厂家信息',
  `mappingEnable` int(11) DEFAULT NULL,
  `stat` int(11) DEFAULT NULL,
  `sourceTcpPortRelevance` int(11) DEFAULT NULL,
  `endTcpPortRelevance` int(11) DEFAULT NULL,
  `toSRelevance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portattr
-- ----------------------------

-- ----------------------------
-- Table structure for `portchildattr`
-- ----------------------------
DROP TABLE IF EXISTS `portchildattr`;
CREATE TABLE `portchildattr` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `pwId` int(11) DEFAULT NULL,
  `portId` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `TagRecognition` int(11) DEFAULT NULL,
  `TagBehavior` int(11) DEFAULT NULL,
  `TagValnId` int(11) DEFAULT NULL,
  `TagValnPri` int(11) DEFAULT NULL,
  `macAddresslearn` int(11) DEFAULT NULL,
  `portSplitHorizon` int(11) DEFAULT NULL,
  `VCTrafficPolicing` int(11) DEFAULT NULL,
  `cir` int(11) DEFAULT NULL,
  `pir` int(11) DEFAULT NULL,
  `cm` int(11) DEFAULT NULL,
  `cbs` int(11) DEFAULT NULL,
  `pbs` int(11) DEFAULT NULL,
  `isused` int(11) DEFAULT NULL,
  `buftype` int(11) DEFAULT NULL,
  `acServiceId` int(11) DEFAULT NULL,
  `controlEnabl` int(11) DEFAULT NULL,
  `lagid` int(11) DEFAULT NULL COMMENT 'lag表外键。如果创建AC承载端口是lag 此字段粗放lag主键',
  `jobstatus` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portchildattr
-- ----------------------------

-- ----------------------------
-- Table structure for `portdiscard_inst`
-- ----------------------------
DROP TABLE IF EXISTS `portdiscard_inst`;
CREATE TABLE `portdiscard_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `portLine1` int(11) DEFAULT NULL,
  `portLine2` int(11) DEFAULT NULL,
  `portLine3` int(11) DEFAULT NULL,
  `portLine4` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portdiscard_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `port_inst`
-- ----------------------------
DROP TABLE IF EXISTS `port_inst`;
CREATE TABLE `port_inst` (
  `portId` int(11) NOT NULL AUTO_INCREMENT,
  `cardId` int(11) DEFAULT NULL,
  `equipId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `portName` varchar(200) DEFAULT NULL,
  `portType` varchar(200) DEFAULT NULL,
  `portStatus` varchar(200) DEFAULT NULL,
  `bandwidth` varchar(200) DEFAULT NULL,
  `manageStatus` int(11) DEFAULT NULL,
  `jobStatus` varchar(200) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  `portx` int(11) DEFAULT NULL,
  `porty` int(11) DEFAULT NULL,
  `isOccupy` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `slotnumber` varchar(200) DEFAULT NULL,
  `isEnabled_code` int(11) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `lagId` int(11) DEFAULT NULL,
  `lagNumber` int(11) DEFAULT NULL,
  `linecoding` varchar(200) DEFAULT NULL,
  `impedance` int(11) DEFAULT NULL,
  `macAddress` varchar(200) DEFAULT NULL,
  `qinqEnabled` int(11) DEFAULT NULL,
  `expMappingLLspInput` int(11) DEFAULT NULL,
  `expMappingLLspOutput` int(11) DEFAULT NULL,
  `expMappingELspInput` int(11) DEFAULT NULL,
  `expMappingELspOutput` int(11) DEFAULT NULL,
  `mappingVlanpriToColor` int(11) DEFAULT NULL,
  `mappingPriorityToVlanpri` int(11) DEFAULT NULL,
  `laserEnabled` int(11) DEFAULT NULL,
  `moduleType` varchar(200) DEFAULT NULL,
  `snmpName` varchar(200) DEFAULT NULL,
  `alarmReversalEnabled` int(11) DEFAULT NULL,
  `servicePort` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`portId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of port_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `port_lag`
-- ----------------------------
DROP TABLE IF EXISTS `port_lag`;
CREATE TABLE `port_lag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `lagId` int(11) NOT NULL,
  `lagModel` int(11) DEFAULT NULL,
  `portMember` varchar(200) DEFAULT NULL,
  `portStatus` int(11) DEFAULT NULL,
  `buffer` int(11) DEFAULT NULL,
  `mtu` int(11) DEFAULT NULL,
  `vlanRelevance` int(11) DEFAULT NULL,
  `ip` int(11) DEFAULT NULL,
  `sourceMAC` int(11) DEFAULT NULL,
  `targetMAC` int(11) DEFAULT NULL,
  `sourceIP` int(11) DEFAULT NULL,
  `targetIP` int(11) DEFAULT NULL,
  `pw` int(11) DEFAULT NULL,
  `dscp` int(11) DEFAULT NULL,
  `exportQueue` varchar(200) DEFAULT NULL,
  `portLimitation` int(11) DEFAULT NULL,
  `broadcastBate` int(11) DEFAULT NULL,
  `broadcastFlux` int(11) DEFAULT NULL,
  `groupBroadcastBate` int(11) DEFAULT NULL,
  `groupBroadcastFlux` int(11) DEFAULT NULL,
  `floodBate` int(11) DEFAULT NULL,
  `floodFlux` int(11) DEFAULT NULL,
  `isUsed` int(11) DEFAULT NULL,
  `maxFrameLength` int(11) DEFAULT NULL,
  `vlanIC` int(11) DEFAULT NULL,
  `vlanPriority` int(11) DEFAULT NULL,
  `msgLoop` int(11) DEFAULT NULL,
  `resumeModel` int(11) DEFAULT NULL,
  `inportLimitation` int(11) DEFAULT NULL,
  `meangerStatus` int(11) DEFAULT NULL,
  `sMac` varchar(200) DEFAULT NULL,
  `lblNetWrap` int(11) DEFAULT NULL,
  `lblVlanTpId` int(11) DEFAULT NULL,
  `lblouterTpid` int(11) DEFAULT NULL,
  `lblNetVlanMode` int(11) DEFAULT NULL,
  `statusActive` int(11) DEFAULT NULL,
  `admin` varchar(20) DEFAULT '',
  `portid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `expMappingLLspInput` int(11) DEFAULT NULL,
  `expMappingLLspOutput` int(11) DEFAULT NULL,
  `expMappingELspInput` int(11) DEFAULT NULL,
  `expMappingELspOutput` int(11) DEFAULT NULL,
  `mappingVlanpriToColor` int(11) DEFAULT NULL,
  `mappingPriorityToVlanpri` int(11) DEFAULT NULL,
  `lagStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of port_lag
-- ----------------------------

-- ----------------------------
-- Table structure for `port_stm`
-- ----------------------------
DROP TABLE IF EXISTS `port_stm`;
CREATE TABLE `port_stm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `portid` int(11) DEFAULT NULL,
  `jobwavelength` int(11) DEFAULT NULL,
  `sfpexpect` varchar(200) DEFAULT NULL,
  `sfpreality` varchar(200) DEFAULT NULL,
  `sfpvender` varchar(200) DEFAULT NULL,
  `siteid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of port_stm
-- ----------------------------

-- ----------------------------
-- Table structure for `port_stm_timeslot`
-- ----------------------------
DROP TABLE IF EXISTS `port_stm_timeslot`;
CREATE TABLE `port_stm_timeslot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `portid` int(11) DEFAULT NULL,
  `portstmid` int(11) DEFAULT NULL,
  `timeslotnumber` varchar(200) DEFAULT NULL,
  `timeslotname` varchar(200) DEFAULT NULL,
  `managerStatus` int(11) DEFAULT NULL,
  `jobstatus` varchar(200) DEFAULT NULL,
  `expectjtwo` varchar(200) DEFAULT NULL,
  `sendjtwo` varchar(200) DEFAULT NULL,
  `realityjtwo` varchar(200) DEFAULT NULL,
  `lptim` int(11) DEFAULT NULL,
  `expectvfive` varchar(200) DEFAULT NULL,
  `sendvfive` varchar(200) DEFAULT NULL,
  `realityvfive` varchar(200) DEFAULT NULL,
  `checkvfive` int(11) DEFAULT NULL,
  `isUsed` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of port_stm_timeslot
-- ----------------------------

-- ----------------------------
-- Table structure for `protectioninfo`
-- ----------------------------
DROP TABLE IF EXISTS `protectioninfo`;
CREATE TABLE `protectioninfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `objProtectType` varchar(8) DEFAULT NULL,
  `protectionType` int(11) DEFAULT NULL,
  `mainSlot` int(11) DEFAULT NULL,
  `mainPort` int(11) DEFAULT NULL,
  `mainTunnelId` int(11) DEFAULT NULL,
  `standbySlot` int(11) DEFAULT NULL,
  `standbyPort` int(11) DEFAULT NULL,
  `standbyTunnelId` int(11) DEFAULT NULL,
  `protractedTime` varchar(256) DEFAULT NULL,
  `returnType` int(11) DEFAULT NULL,
  `reserve` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of protectioninfo
-- ----------------------------

-- ----------------------------
-- Table structure for `protect_rorate`
-- ----------------------------
DROP TABLE IF EXISTS `protect_rorate`;
CREATE TABLE `protect_rorate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recoverMain` int(11) DEFAULT NULL,
  `forceStand` int(11) DEFAULT NULL,
  `lockMain` int(11) DEFAULT NULL,
  `manpowerStand` int(11) DEFAULT NULL,
  `clear` int(11) DEFAULT NULL,
  `roratePractise` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `tunnelbusinessid` int(11) DEFAULT NULL,
  `tunnelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of protect_rorate
-- ----------------------------

-- ----------------------------
-- Table structure for `pwbuffer`
-- ----------------------------
DROP TABLE IF EXISTS `pwbuffer`;
CREATE TABLE `pwbuffer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acId` int(11) DEFAULT NULL,
  `bufferEnable` int(11) DEFAULT NULL,
  `vlanId` int(11) DEFAULT NULL,
  `sourceMac` varchar(200) DEFAULT NULL,
  `targetMac` varchar(200) DEFAULT NULL,
  `eightIp` int(11) DEFAULT NULL,
  `sourceIp` varchar(200) DEFAULT NULL,
  `targetIp` varchar(200) DEFAULT NULL,
  `ipDscp` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `cir` int(11) DEFAULT NULL,
  `pir` int(11) DEFAULT NULL,
  `cm` int(11) DEFAULT NULL,
  `cbs` int(11) DEFAULT NULL,
  `pbs` int(11) DEFAULT NULL,
  `strategy` int(11) DEFAULT NULL,
  `phb` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pwbuffer
-- ----------------------------

-- ----------------------------
-- Table structure for `pwinfo`
-- ----------------------------
DROP TABLE IF EXISTS `pwinfo`;
CREATE TABLE `pwinfo` (
  `pwId` int(11) NOT NULL AUTO_INCREMENT,
  `pwName` varchar(200) DEFAULT NULL,
  `pwStatus` int(11) DEFAULT NULL,
  `tunnelId` int(11) DEFAULT NULL,
  `inlabelValue` int(11) DEFAULT NULL,
  `outlabelValue` int(11) DEFAULT NULL,
  `aSiteId` int(11) DEFAULT NULL,
  `zSiteId` int(11) DEFAULT NULL,
  `direction` varchar(1000) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createUser` varchar(200) DEFAULT NULL,
  `apwServiceId` int(11) DEFAULT NULL,
  `zpwServiceId` int(11) DEFAULT NULL,
  `rserviceId` int(11) DEFAULT NULL,
  `rserviceType` int(11) DEFAULT NULL,
  `aPortConfigId` int(11) DEFAULT NULL,
  `zPortConfigId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `isSingle` int(11) DEFAULT '0' COMMENT '是否为单网元配置：0否 1是',
  `aoppositeId` varchar(200) DEFAULT NULL COMMENT 'A端对端网元ID',
  `zoppositeId` varchar(200) DEFAULT NULL COMMENT 'Z端对端网元ID',
  `jobstatus` varchar(200) DEFAULT NULL,
  `expStrategy` int(11) DEFAULT NULL,
  `expAssignment` int(11) DEFAULT NULL,
  `phbToExpId` int(11) DEFAULT NULL,
  `phbStrategy` int(11) DEFAULT NULL,
  `phbAssignment` int(11) DEFAULT NULL,
  `expTophbId` int(11) DEFAULT NULL,
  `payload` int(11) DEFAULT NULL,
  `aVlanEnable` int(11) DEFAULT NULL,
  `aOutVlanValue` int(11) DEFAULT NULL,
  `aSourceMac` varchar(200) DEFAULT NULL,
  `atargetMac` varchar(200) DEFAULT NULL,
  `zVlanEnable` int(11) DEFAULT NULL,
  `zOutVlanValue` int(11) DEFAULT NULL,
  `zSourceMac` varchar(200) DEFAULT NULL,
  `ztargetMac` varchar(200) DEFAULT NULL,
  `businessType` varchar(200) DEFAULT NULL,
  `backInlabel` int(11) DEFAULT NULL,
  `backOutlabel` int(11) DEFAULT NULL,
  `atp_id` int(11) DEFAULT NULL,
  `ztp_id` int(11) DEFAULT NULL,
  `pri` int(11) DEFAULT NULL,
  `qosModel` int(11) DEFAULT NULL,
  `snmpPwName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pwId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pwinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `pwnnibuffer`
-- ----------------------------
DROP TABLE IF EXISTS `pwnnibuffer`;
CREATE TABLE `pwnnibuffer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `pwId` int(11) DEFAULT NULL,
  `pwBusinessId` int(11) DEFAULT NULL,
  `exitRule` int(11) DEFAULT NULL,
  `svlan` varchar(255) DEFAULT NULL,
  `tpid` int(11) DEFAULT NULL,
  `vlanpri` varchar(255) DEFAULT NULL,
  `horizontalDivision` int(11) DEFAULT NULL,
  `macAddressLearn` int(11) DEFAULT NULL,
  `tagAction` int(11) DEFAULT NULL,
  `ControlEnable` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pwnnibuffer
-- ----------------------------

-- ----------------------------
-- Table structure for `pwprotect`
-- ----------------------------
DROP TABLE IF EXISTS `pwprotect`;
CREATE TABLE `pwprotect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protectType` int(11) DEFAULT NULL,
  `mainSlot` int(11) DEFAULT NULL,
  `mainPort` int(11) DEFAULT NULL,
  `mainTunnelId` int(11) DEFAULT NULL,
  `mainPwId` int(11) DEFAULT NULL,
  `standSlot` int(11) DEFAULT NULL,
  `standPort` int(11) DEFAULT NULL,
  `standTunnelId` int(11) DEFAULT NULL,
  `standPwId` int(11) DEFAULT NULL,
  `delayTime` int(11) DEFAULT NULL,
  `backType` int(11) DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `serviceId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pwprotect
-- ----------------------------

-- ----------------------------
-- Table structure for `qinqchildinst`
-- ----------------------------
DROP TABLE IF EXISTS `qinqchildinst`;
CREATE TABLE `qinqchildinst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qinqId` int(11) DEFAULT NULL,
  `aSiteId` int(11) DEFAULT NULL,
  `zSiteId` int(11) DEFAULT NULL,
  `aPortId` int(11) DEFAULT NULL,
  `zPortId` int(11) DEFAULT NULL,
  `aServiceId` int(11) DEFAULT NULL,
  `zServiceId` int(11) DEFAULT NULL,
  `segmentId` int(11) DEFAULT NULL,
  `pathStatus` int(11) DEFAULT NULL,
  `aSiteVlanIdRule` int(11) DEFAULT NULL,
  `zSiteVlanIdRule` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qinqchildinst
-- ----------------------------

-- ----------------------------
-- Table structure for `qinqinst`
-- ----------------------------
DROP TABLE IF EXISTS `qinqinst`;
CREATE TABLE `qinqinst` (
  `qinqId` int(11) NOT NULL AUTO_INCREMENT,
  `qinqName` varchar(255) DEFAULT NULL,
  `tpId` varchar(255) DEFAULT NULL,
  `aSiteId` int(11) DEFAULT NULL,
  `zSiteId` int(11) DEFAULT NULL,
  `aPortId` int(11) DEFAULT NULL,
  `zPortId` int(11) DEFAULT NULL,
  `aAcPortId` int(11) DEFAULT NULL,
  `zAcPortId` int(11) DEFAULT NULL,
  `acVlanIdRule` int(11) DEFAULT NULL,
  `minVlanId` int(11) DEFAULT NULL,
  `maxVlanId` int(11) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `vlanId` int(11) DEFAULT NULL,
  `qinqStatus` int(11) DEFAULT NULL,
  `basedInVlanId` int(11) DEFAULT NULL,
  PRIMARY KEY (`qinqId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qinqinst
-- ----------------------------

-- ----------------------------
-- Table structure for `qosinfo`
-- ----------------------------
DROP TABLE IF EXISTS `qosinfo`;
CREATE TABLE `qosinfo` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL COMMENT '自动生成，一组qos，此ID相同',
  `qosType` varchar(16) DEFAULT NULL,
  `qosname` varchar(200) DEFAULT NULL COMMENT 'qos类型+关联businessId。类似于“llsp1、elsp1” 每个类型是1-64',
  `seq` int(11) DEFAULT '0',
  `cos` int(11) DEFAULT '5',
  `direction` varchar(1000) DEFAULT NULL,
  `cir` int(11) DEFAULT '0',
  `cbs` int(11) DEFAULT '-1',
  `eir` int(11) DEFAULT '0',
  `ebs` int(11) DEFAULT '-1',
  `pir` int(11) DEFAULT '-1',
  `pbs` int(11) DEFAULT NULL,
  `strategy` int(11) DEFAULT NULL,
  `colorSense` int(4) DEFAULT '0',
  `status` int(11) DEFAULT NULL COMMENT '状态，0=设备不存在  1=设备存在',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qosinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `qosmodel`
-- ----------------------------
DROP TABLE IF EXISTS `qosmodel`;
CREATE TABLE `qosmodel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `typeName` varchar(200) DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qosmodel
-- ----------------------------

-- ----------------------------
-- Table structure for `qosqueue`
-- ----------------------------
DROP TABLE IF EXISTS `qosqueue`;
CREATE TABLE `qosqueue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `objType` varchar(32) DEFAULT NULL,
  `objId` int(11) DEFAULT NULL,
  `queueType` varchar(32) DEFAULT NULL,
  `cos` int(11) DEFAULT '0',
  `cir` int(11) DEFAULT '0',
  `weight` int(11) DEFAULT '16',
  `greenLowThresh` int(11) DEFAULT '96',
  `greenHighThresh` int(11) DEFAULT '128',
  `greenProbability` int(11) DEFAULT '100',
  `yellowLowThresh` int(11) DEFAULT '64',
  `yellowHighThresh` int(11) DEFAULT '96',
  `yellowProbability` int(11) DEFAULT '100',
  `wredEnable` int(11) DEFAULT '1',
  `mostBandwidth` varchar(32) DEFAULT NULL,
  `discard` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qosqueue
-- ----------------------------

-- ----------------------------
-- Table structure for `qosrelevance`
-- ----------------------------
DROP TABLE IF EXISTS `qosrelevance`;
CREATE TABLE `qosrelevance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objType` varchar(200) DEFAULT NULL COMMENT '业务类型，tunnel、pw、ac',
  `objId` int(11) DEFAULT NULL COMMENT '业务主键',
  `siteId` int(11) DEFAULT NULL COMMENT '网元ID',
  `qosGroupId` int(11) DEFAULT NULL COMMENT '关联qosinfo表中的groupId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qosrelevance
-- ----------------------------

-- ----------------------------
-- Table structure for `qostable`
-- ----------------------------
DROP TABLE IF EXISTS `qostable`;
CREATE TABLE `qostable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qosModelId` int(11) DEFAULT NULL,
  `grade` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `color` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qostable
-- ----------------------------

-- ----------------------------
-- Table structure for `qostemplateinfo`
-- ----------------------------
DROP TABLE IF EXISTS `qostemplateinfo`;
CREATE TABLE `qostemplateinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `templateName` varchar(32) DEFAULT NULL,
  `qosType` varchar(16) DEFAULT NULL,
  `seq` int(11) DEFAULT '0',
  `cos` int(11) DEFAULT '5',
  `direction` varchar(8) DEFAULT NULL,
  `cir` int(11) DEFAULT '0',
  `cbs` int(11) DEFAULT '-1',
  `eir` int(11) DEFAULT '0',
  `ebs` int(11) DEFAULT '-1',
  `pir` int(11) DEFAULT '-1',
  `pbs` int(11) DEFAULT NULL,
  `strategy` int(11) DEFAULT NULL,
  `colorSense` int(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qostemplateinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `roleinfo`
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `roleEnName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('1', '系统管理员', 'default', 'System Administrator');
INSERT INTO `roleinfo` VALUES ('2', '系统维护员', 'default', 'System Maintenance');
INSERT INTO `roleinfo` VALUES ('3', '系统操作员', 'default', 'System Operator');
INSERT INTO `roleinfo` VALUES ('4', '系统监控员', 'default', 'System Monitor');

-- ----------------------------
-- Table structure for `rolemanage`
-- ----------------------------
DROP TABLE IF EXISTS `rolemanage`;
CREATE TABLE `rolemanage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `name_en` varchar(255) DEFAULT NULL,
  `label` int(255) DEFAULT NULL,
  `parentId` int(11) NOT NULL DEFAULT '0',
  `isVisible` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolemanage
-- ----------------------------
INSERT INTO `rolemanage` VALUES ('1', '系统模块', 'systemModu', '1', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('2', '拓扑模块', 'topologyModu', '2', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('3', '配置模块', 'deployModu', '3', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('4', '核心模块', 'coreModu', '4', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('5', '告警模块', 'alarmModu', '5', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('6', '性能模块', 'proformanceModu', '6', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('7', '统计模块', 'countModu', '7', '0', '00000000001');
INSERT INTO `rolemanage` VALUES ('8', '安全模块', 'satyModu', '8', '0', '00000000000');
INSERT INTO `rolemanage` VALUES ('9', '系统管理', 'systemManage', '9', '1', '00000000001');
INSERT INTO `rolemanage` VALUES ('10', '系统查看', 'systemSelect', '10', '1', '00000000001');
INSERT INTO `rolemanage` VALUES ('11', '拓扑管理', 'topologyManage', '11', '2', '00000000001');
INSERT INTO `rolemanage` VALUES ('12', '拓扑查看', 'topologySelect', '12', '2', '00000000001');
INSERT INTO `rolemanage` VALUES ('13', '配置管理', 'deployManage', '13', '3', '00000000001');
INSERT INTO `rolemanage` VALUES ('14', '配置查看', 'deploySelect', '14', '3', '00000000001');
INSERT INTO `rolemanage` VALUES ('15', '核心模块管理', 'coreManage', '15', '4', '00000000001');
INSERT INTO `rolemanage` VALUES ('16', '核心模块查看', 'coreSelect', '16', '4', '00000000001');
INSERT INTO `rolemanage` VALUES ('17', '告警管理', 'alarmManage', '17', '5', '00000000001');
INSERT INTO `rolemanage` VALUES ('18', '告警查看', 'alarmSelect', '18', '5', '00000000001');
INSERT INTO `rolemanage` VALUES ('19', '性能管理', 'proforManage', '19', '6', '00000000001');
INSERT INTO `rolemanage` VALUES ('20', '性能查看', 'proforSelect', '20', '6', '00000000001');
INSERT INTO `rolemanage` VALUES ('21', '统计查看', 'countSelect', '21', '7', '00000000001');
INSERT INTO `rolemanage` VALUES ('22', '安全模块管理', 'satyManage', '22', '8', '00000000000');
INSERT INTO `rolemanage` VALUES ('23', '安全模块查看', 'satySelect', '23', '8', '00000000000');

-- ----------------------------
-- Table structure for `rolerelevance`
-- ----------------------------
DROP TABLE IF EXISTS `rolerelevance`;
CREATE TABLE `rolerelevance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `infoId` int(11) DEFAULT NULL,
  `manageId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolerelevance
-- ----------------------------
INSERT INTO `rolerelevance` VALUES ('1', '1', '1');
INSERT INTO `rolerelevance` VALUES ('2', '1', '2');
INSERT INTO `rolerelevance` VALUES ('3', '1', '3');
INSERT INTO `rolerelevance` VALUES ('4', '1', '4');
INSERT INTO `rolerelevance` VALUES ('5', '1', '5');
INSERT INTO `rolerelevance` VALUES ('6', '1', '6');
INSERT INTO `rolerelevance` VALUES ('7', '1', '7');
INSERT INTO `rolerelevance` VALUES ('8', '1', '8');
INSERT INTO `rolerelevance` VALUES ('9', '1', '9');
INSERT INTO `rolerelevance` VALUES ('10', '1', '10');
INSERT INTO `rolerelevance` VALUES ('11', '1', '11');
INSERT INTO `rolerelevance` VALUES ('12', '1', '12');
INSERT INTO `rolerelevance` VALUES ('13', '1', '13');
INSERT INTO `rolerelevance` VALUES ('14', '1', '14');
INSERT INTO `rolerelevance` VALUES ('15', '1', '15');
INSERT INTO `rolerelevance` VALUES ('16', '1', '16');
INSERT INTO `rolerelevance` VALUES ('17', '1', '17');
INSERT INTO `rolerelevance` VALUES ('18', '1', '18');
INSERT INTO `rolerelevance` VALUES ('19', '1', '19');
INSERT INTO `rolerelevance` VALUES ('20', '1', '20');
INSERT INTO `rolerelevance` VALUES ('21', '1', '21');
INSERT INTO `rolerelevance` VALUES ('22', '1', '22');
INSERT INTO `rolerelevance` VALUES ('23', '1', '23');
INSERT INTO `rolerelevance` VALUES ('82', '2', '21');
INSERT INTO `rolerelevance` VALUES ('83', '2', '7');
INSERT INTO `rolerelevance` VALUES ('84', '2', '20');
INSERT INTO `rolerelevance` VALUES ('85', '2', '19');
INSERT INTO `rolerelevance` VALUES ('86', '2', '6');
INSERT INTO `rolerelevance` VALUES ('87', '2', '18');
INSERT INTO `rolerelevance` VALUES ('88', '2', '17');
INSERT INTO `rolerelevance` VALUES ('89', '2', '5');
INSERT INTO `rolerelevance` VALUES ('90', '2', '16');
INSERT INTO `rolerelevance` VALUES ('91', '2', '15');
INSERT INTO `rolerelevance` VALUES ('92', '2', '4');
INSERT INTO `rolerelevance` VALUES ('93', '2', '14');
INSERT INTO `rolerelevance` VALUES ('94', '2', '13');
INSERT INTO `rolerelevance` VALUES ('95', '2', '3');
INSERT INTO `rolerelevance` VALUES ('96', '2', '12');
INSERT INTO `rolerelevance` VALUES ('97', '2', '11');
INSERT INTO `rolerelevance` VALUES ('98', '2', '2');
INSERT INTO `rolerelevance` VALUES ('99', '2', '10');
INSERT INTO `rolerelevance` VALUES ('100', '2', '9');
INSERT INTO `rolerelevance` VALUES ('101', '2', '1');
INSERT INTO `rolerelevance` VALUES ('102', '3', '21');
INSERT INTO `rolerelevance` VALUES ('103', '3', '7');
INSERT INTO `rolerelevance` VALUES ('104', '3', '20');
INSERT INTO `rolerelevance` VALUES ('105', '3', '19');
INSERT INTO `rolerelevance` VALUES ('106', '3', '6');
INSERT INTO `rolerelevance` VALUES ('107', '3', '18');
INSERT INTO `rolerelevance` VALUES ('108', '3', '17');
INSERT INTO `rolerelevance` VALUES ('109', '3', '5');
INSERT INTO `rolerelevance` VALUES ('110', '3', '16');
INSERT INTO `rolerelevance` VALUES ('111', '3', '4');
INSERT INTO `rolerelevance` VALUES ('112', '3', '14');
INSERT INTO `rolerelevance` VALUES ('113', '3', '13');
INSERT INTO `rolerelevance` VALUES ('114', '3', '3');
INSERT INTO `rolerelevance` VALUES ('115', '3', '12');
INSERT INTO `rolerelevance` VALUES ('116', '3', '2');
INSERT INTO `rolerelevance` VALUES ('117', '3', '10');
INSERT INTO `rolerelevance` VALUES ('118', '3', '9');
INSERT INTO `rolerelevance` VALUES ('119', '3', '1');
INSERT INTO `rolerelevance` VALUES ('120', '4', '21');
INSERT INTO `rolerelevance` VALUES ('121', '4', '7');
INSERT INTO `rolerelevance` VALUES ('122', '4', '20');
INSERT INTO `rolerelevance` VALUES ('123', '4', '6');
INSERT INTO `rolerelevance` VALUES ('124', '4', '18');
INSERT INTO `rolerelevance` VALUES ('125', '4', '5');
INSERT INTO `rolerelevance` VALUES ('126', '4', '16');
INSERT INTO `rolerelevance` VALUES ('127', '4', '4');
INSERT INTO `rolerelevance` VALUES ('128', '4', '14');
INSERT INTO `rolerelevance` VALUES ('129', '4', '3');
INSERT INTO `rolerelevance` VALUES ('130', '4', '12');
INSERT INTO `rolerelevance` VALUES ('131', '4', '2');
INSERT INTO `rolerelevance` VALUES ('132', '4', '10');
INSERT INTO `rolerelevance` VALUES ('133', '4', '9');
INSERT INTO `rolerelevance` VALUES ('134', '4', '1');

-- ----------------------------
-- Table structure for `segment`
-- ----------------------------
DROP TABLE IF EXISTS `segment`;
CREATE TABLE `segment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) DEFAULT NULL,
  `BANDWIDTH` varchar(200) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  `ASITEID` int(11) DEFAULT NULL,
  `speed` varchar(200) DEFAULT NULL,
  `ZSITEID` int(11) DEFAULT NULL,
  `APORTID` int(11) DEFAULT NULL,
  `ZPORTID` int(11) DEFAULT NULL,
  `CREATUSER` varchar(200) DEFAULT NULL,
  `CREATTIME` datetime DEFAULT NULL,
  `ASLOT` int(11) DEFAULT NULL,
  `ZSLOT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of segment
-- ----------------------------

-- ----------------------------
-- Table structure for `serviceinfo`
-- ----------------------------
DROP TABLE IF EXISTS `serviceinfo`;
CREATE TABLE `serviceinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` int(11) DEFAULT NULL,
  `pwId` int(11) DEFAULT NULL,
  `serviceType` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `aXcId` int(11) DEFAULT NULL,
  `zXcId` int(11) DEFAULT NULL,
  `activeStatus` int(11) DEFAULT NULL,
  `rootSite` int(11) DEFAULT NULL,
  `branchSite` int(11) DEFAULT NULL,
  `aAcId` int(11) DEFAULT NULL,
  `zAcId` int(11) DEFAULT NULL,
  `createUser` varchar(200) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `aSiteId` int(11) DEFAULT NULL,
  `zSiteId` int(11) DEFAULT NULL,
  `isSingle` int(11) DEFAULT '0' COMMENT '是否为单网元配置：0否 1是',
  `cestype` int(11) DEFAULT NULL,
  `jobstatus` varchar(200) DEFAULT NULL,
  `clientId` int(11) DEFAULT NULL,
  `branchMainSite` int(11) DEFAULT NULL,
  `branchProtectSite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of serviceinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `setnamerule`
-- ----------------------------
DROP TABLE IF EXISTS `setnamerule`;
CREATE TABLE `setnamerule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `namerule` varchar(500) DEFAULT NULL,
  `nameexample` varchar(500) DEFAULT NULL,
  `sourcename` varchar(500) DEFAULT NULL,
  `rowcount` int(100) DEFAULT NULL,
  `isUsed` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setnamerule
-- ----------------------------

-- ----------------------------
-- Table structure for `single_spread`
-- ----------------------------
DROP TABLE IF EXISTS `single_spread`;
CREATE TABLE `single_spread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `suId` int(11) DEFAULT NULL,
  `vsId` int(11) DEFAULT NULL,
  `singlePortChoose` int(11) DEFAULT NULL,
  `macAddress` varchar(200) DEFAULT NULL,
  `siteId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of single_spread
-- ----------------------------

-- ----------------------------
-- Table structure for `sitelock`
-- ----------------------------
DROP TABLE IF EXISTS `sitelock`;
CREATE TABLE `sitelock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `lockType` varchar(200) DEFAULT NULL,
  `lockStatus` int(11) DEFAULT NULL,
  `lockUser` varchar(200) DEFAULT NULL,
  `lockDate` datetime DEFAULT NULL,
  `clearUser` varchar(200) DEFAULT NULL,
  `clearDate` datetime DEFAULT NULL,
  `isForceClear` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sitelock
-- ----------------------------

-- ----------------------------
-- Table structure for `site_inst`
-- ----------------------------
DROP TABLE IF EXISTS `site_inst`;
CREATE TABLE `site_inst` (
  `site_inst_id` int(11) NOT NULL AUTO_INCREMENT,
  `site_Hum_id` varchar(200) DEFAULT NULL,
  `CellDescribe` varchar(2000) DEFAULT NULL,
  `CellId` varchar(200) DEFAULT NULL,
  `CellType` varchar(200) DEFAULT NULL,
  `CellEditon` varchar(200) DEFAULT NULL,
  `CellIcccode` varchar(200) DEFAULT NULL,
  `CellTPoam` varchar(200) DEFAULT NULL,
  `CellTimeZone` varchar(200) DEFAULT NULL,
  `CellTime` varchar(200) DEFAULT NULL,
  `CellTimeServer` varchar(200) DEFAULT NULL,
  `CellIdentifier` varchar(200) DEFAULT NULL,
  `FieldID` int(11) DEFAULT NULL,
  `SiteX` int(11) DEFAULT NULL,
  `SiteY` int(11) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  `Swich` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `userpwd` varchar(200) DEFAULT NULL,
  `loginstatus` int(11) DEFAULT NULL,
  `versions` varchar(200) DEFAULT NULL,
  `isGateway` int(11) DEFAULT NULL COMMENT '是否为网关网元，0否 1是',
  `manufacturer` int(11) DEFAULT NULL,
  `siteType` varchar(200) DEFAULT NULL,
  `createTime` varchar(200) DEFAULT NULL,
  `isCreateDiscardFlow` int(11) DEFAULT NULL,
  `createUser` varchar(200) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `alarmReversalModel` int(11) DEFAULT NULL,
  `sn` varchar(200) DEFAULT NULL,
  `rootIP` varchar(200) DEFAULT NULL,
  `isAlarmReversal` int(11) DEFAULT NULL,
  `isDelayAlarmTrap` int(11) DEFAULT NULL,
  `delayTime` varchar(200) DEFAULT NULL,
  `rack` int(11) DEFAULT NULL,
  `shelf` int(11) DEFAULT NULL,
  PRIMARY KEY (`site_inst_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `site_roate`
-- ----------------------------
DROP TABLE IF EXISTS `site_roate`;
CREATE TABLE `site_roate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `typeId` int(255) DEFAULT NULL COMMENT '对应类型的id(tunnel 存tunnelId)',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `roate` int(11) DEFAULT NULL COMMENT '倒换命令（[1,6]）',
  `siteRoate` int(11) DEFAULT NULL COMMENT '武汉的 网元倒换',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of site_roate
-- ----------------------------

-- ----------------------------
-- Table structure for `slot_inst`
-- ----------------------------
DROP TABLE IF EXISTS `slot_inst`;
CREATE TABLE `slot_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) NOT NULL,
  `equipId` int(11) DEFAULT NULL,
  `cardId` int(11) DEFAULT NULL,
  `imagePath` varchar(200) DEFAULT NULL,
  `slotType` varchar(200) DEFAULT NULL,
  `slotx` int(11) DEFAULT NULL,
  `sloty` int(11) DEFAULT NULL,
  `bestCardName` varchar(200) DEFAULT NULL,
  `number` varchar(200) DEFAULT NULL,
  `masterCardAddress` varchar(200) DEFAULT NULL,
  `snmpName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of slot_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `smartfan`
-- ----------------------------
DROP TABLE IF EXISTS `smartfan`;
CREATE TABLE `smartfan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `workType` int(11) DEFAULT NULL,
  `gearLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of smartfan
-- ----------------------------

-- ----------------------------
-- Table structure for `timeclockinterfac`
-- ----------------------------
DROP TABLE IF EXISTS `timeclockinterfac`;
CREATE TABLE `timeclockinterfac` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `rate` varchar(200) DEFAULT NULL,
  `ssmSendingEnabled` int(11) DEFAULT NULL,
  `dnuGroup` varchar(200) DEFAULT NULL,
  `portName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timeclockinterfac
-- ----------------------------

-- ----------------------------
-- Table structure for `timeneptp`
-- ----------------------------
DROP TABLE IF EXISTS `timeneptp`;
CREATE TABLE `timeneptp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `model` int(11) DEFAULT NULL,
  `ClockType` int(11) DEFAULT NULL,
  `clockPrecision` int(11) DEFAULT NULL,
  `clockVariance` int(11) DEFAULT NULL,
  `priorOne` int(11) DEFAULT NULL,
  `priorTwo` int(11) DEFAULT NULL,
  `manufacturerOUI` int(11) DEFAULT NULL,
  `clockRegion` int(11) DEFAULT NULL,
  `clockRegionOne` int(11) DEFAULT NULL,
  `clockRegionTwoJbox` int(11) DEFAULT NULL,
  `clockRegionTwo` int(11) DEFAULT NULL,
  `clockRegionThreeJbox` int(11) DEFAULT NULL,
  `clockRegionThree` int(11) DEFAULT NULL,
  `clockRegionFourJbox` int(11) DEFAULT NULL,
  `clockRegionFour` int(11) DEFAULT NULL,
  `clockRegionDelay` int(11) DEFAULT NULL,
  `followModel` int(11) DEFAULT NULL,
  `todsendTime` int(11) DEFAULT NULL,
  `timeID` varchar(200) DEFAULT NULL,
  `timeType` varchar(200) DEFAULT NULL,
  `ftimeID` varchar(200) DEFAULT NULL,
  `ftimePort` varchar(200) DEFAULT NULL,
  `leapNumber` varchar(200) DEFAULT NULL,
  `systemVarianceValue` varchar(200) DEFAULT NULL,
  `todState` varchar(200) DEFAULT NULL,
  `zTimeID` varchar(200) DEFAULT NULL,
  `zTimeTpye` varchar(200) DEFAULT NULL,
  `zTimePrecision` varchar(200) DEFAULT NULL,
  `zTimeVariance` varchar(200) DEFAULT NULL,
  `zTimePriorOne` varchar(200) DEFAULT NULL,
  `zTimePriorTwo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timeneptp
-- ----------------------------

-- ----------------------------
-- Table structure for `timeportdisposition`
-- ----------------------------
DROP TABLE IF EXISTS `timeportdisposition`;
CREATE TABLE `timeportdisposition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `portEnable` int(11) DEFAULT NULL,
  `clockModel` varchar(200) DEFAULT NULL,
  `delayMechanism` int(11) DEFAULT NULL,
  `vlanID` varchar(200) DEFAULT NULL,
  `operationMode` int(11) DEFAULT NULL,
  `portStatus` varchar(200) DEFAULT NULL,
  `timeStampMode` int(11) DEFAULT NULL,
  `AnncPacketsInterval` int(11) DEFAULT NULL,
  `AnncTimeoutSetting` varchar(200) DEFAULT NULL,
  `SyncPacketsInterval` int(11) DEFAULT NULL,
  `Delay_ReqPacketsInterval` int(11) DEFAULT NULL,
  `Pdel_ReqPacketsInterval` int(11) DEFAULT NULL,
  `lineDelayCompensation` varchar(200) DEFAULT NULL,
  `delayCompensationMeasure` varchar(200) DEFAULT NULL,
  `portMapping` varchar(200) DEFAULT NULL,
  `interfaceType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timeportdisposition
-- ----------------------------

-- ----------------------------
-- Table structure for `timeportdispositiontod`
-- ----------------------------
DROP TABLE IF EXISTS `timeportdispositiontod`;
CREATE TABLE `timeportdispositiontod` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `port` varchar(200) DEFAULT NULL,
  `interfaceType` int(11) DEFAULT NULL,
  `physicalStatus` varchar(200) DEFAULT NULL,
  `logicalStatus` varchar(200) DEFAULT NULL,
  `priority1` varchar(200) DEFAULT NULL,
  `clockType` varchar(200) DEFAULT NULL,
  `clockAccuracy` varchar(200) DEFAULT NULL,
  `clockVariance` varchar(200) DEFAULT NULL,
  `priority2` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of timeportdispositiontod
-- ----------------------------

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
  PRIMARY KEY (`tunnelId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tunnel
-- ----------------------------

-- ----------------------------
-- Table structure for `udaattr`
-- ----------------------------
DROP TABLE IF EXISTS `udaattr`;
CREATE TABLE `udaattr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupId` int(11) DEFAULT NULL,
  `attrName` varchar(200) DEFAULT NULL,
  `attrENName` varchar(200) DEFAULT NULL,
  `attrType` varchar(200) DEFAULT NULL,
  `isNeedText` varchar(200) DEFAULT NULL,
  `codeGroupId` varchar(200) DEFAULT NULL,
  `defaultValue` varchar(200) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `distanceLeft` int(11) DEFAULT NULL,
  `distanceTop` int(11) DEFAULT NULL,
  `isTableShow` varchar(200) DEFAULT NULL,
  `isMustFill` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of udaattr
-- ----------------------------

-- ----------------------------
-- Table structure for `udagroup`
-- ----------------------------
DROP TABLE IF EXISTS `udagroup`;
CREATE TABLE `udagroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(200) DEFAULT NULL,
  `groupType` varchar(200) DEFAULT NULL,
  `parentId` int(11) DEFAULT '-1',
  `parentName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of udagroup
-- ----------------------------

-- ----------------------------
-- Table structure for `unload_manager`
-- ----------------------------
DROP TABLE IF EXISTS `unload_manager`;
CREATE TABLE `unload_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unloadType` int(255) DEFAULT NULL,
  `cellType` int(11) DEFAULT NULL,
  `unloadLimit` int(11) DEFAULT NULL,
  `spillEntry` int(11) DEFAULT NULL,
  `holdEntry` int(11) DEFAULT NULL,
  `unloadMod` int(11) DEFAULT NULL,
  `fileWay` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unload_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `userdesgin`
-- ----------------------------
DROP TABLE IF EXISTS `userdesgin`;
CREATE TABLE `userdesgin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isLockSelect` int(11) DEFAULT NULL,
  `timeValue` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userdesgin
-- ----------------------------

-- ----------------------------
-- Table structure for `user_field`
-- ----------------------------
DROP TABLE IF EXISTS `user_field`;
CREATE TABLE `user_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `field_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_field
-- ----------------------------

-- ----------------------------
-- Table structure for `user_inst`
-- ----------------------------
DROP TABLE IF EXISTS `user_inst`;
CREATE TABLE `user_inst` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) NOT NULL,
  `user_pass` varchar(200) NOT NULL,
  `roleInfo_id` varchar(200) DEFAULT NULL,
  `isAll` int(1) DEFAULT NULL,
  `user_inface` varchar(255) DEFAULT NULL COMMENT '用户详细信息',
  `pswExpires` varchar(200) DEFAULT NULL,
  `beforeRemind` int(11) DEFAULT NULL,
  `deadTime` varchar(200) DEFAULT NULL,
  `startip` varchar(200) DEFAULT NULL,
  `endip` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inst
-- ----------------------------
INSERT INTO `user_inst` VALUES ('1', 'admin', 'admin', '1', '1', null, null, null, null, '0.0.0.0', '255.255.255.254');

-- ----------------------------
-- Table structure for `user_lock`
-- ----------------------------
DROP TABLE IF EXISTS `user_lock`;
CREATE TABLE `user_lock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `lockTime` varchar(255) DEFAULT NULL,
  `clearTime` varchar(255) DEFAULT NULL,
  `lockType` int(11) DEFAULT NULL,
  `clearType` int(11) DEFAULT NULL,
  `lockUsername` varchar(255) DEFAULT NULL,
  `clearUsername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_lock
-- ----------------------------

-- ----------------------------
-- Table structure for `v35port_inst`
-- ----------------------------
DROP TABLE IF EXISTS `v35port_inst`;
CREATE TABLE `v35port_inst` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `siteId` int(11) DEFAULT NULL,
  `fourthLeg` int(11) DEFAULT NULL,
  `timeModel` int(11) DEFAULT NULL,
  `frame` int(11) DEFAULT NULL,
  `time1` int(11) DEFAULT NULL,
  `time2` int(11) DEFAULT NULL,
  `time3` int(11) DEFAULT NULL,
  `time4` int(11) DEFAULT NULL,
  `time5` int(11) DEFAULT NULL,
  `time6` int(11) DEFAULT NULL,
  `time7` int(11) DEFAULT NULL,
  `time8` int(11) DEFAULT NULL,
  `time9` int(11) DEFAULT NULL,
  `time10` int(11) DEFAULT NULL,
  `time11` int(11) DEFAULT NULL,
  `time12` int(11) DEFAULT NULL,
  `time13` int(11) DEFAULT NULL,
  `time14` int(11) DEFAULT NULL,
  `time15` int(11) DEFAULT NULL,
  `time16` int(11) DEFAULT NULL,
  `time17` int(11) DEFAULT NULL,
  `time18` int(11) DEFAULT NULL,
  `time19` int(11) DEFAULT NULL,
  `time20` int(11) DEFAULT NULL,
  `time21` int(11) DEFAULT NULL,
  `time22` int(11) DEFAULT NULL,
  `time23` int(11) DEFAULT NULL,
  `time24` int(11) DEFAULT NULL,
  `time25` int(11) DEFAULT NULL,
  `time26` int(11) DEFAULT NULL,
  `time27` int(11) DEFAULT NULL,
  `time28` int(11) DEFAULT NULL,
  `time29` int(11) DEFAULT NULL,
  `time30` int(11) DEFAULT NULL,
  `time31` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of v35port_inst
-- ----------------------------

-- ----------------------------
-- Table structure for `warninglevel`
-- ----------------------------
DROP TABLE IF EXISTS `warninglevel`;
CREATE TABLE `warninglevel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `WarningName` varchar(200) DEFAULT NULL,
  `WarningNote` varchar(2000) DEFAULT NULL,
  `WarningLevel_temp` int(11) DEFAULT NULL,
  `WarningLevel` int(11) DEFAULT NULL,
  `WarningCode` int(11) DEFAULT NULL,
  `WarningObject` varchar(200) DEFAULT NULL,
  `WarningType` int(11) DEFAULT NULL,
  `WarningDescribe` varchar(2000) DEFAULT NULL,
  `WaringEffect` varchar(2000) DEFAULT NULL,
  `WaringRemark` varchar(2000) DEFAULT NULL,
  `Manufacturer` int(11) DEFAULT NULL,
  `WarningMayReason` varchar(2000) DEFAULT NULL,
  `WarningAdvice` varchar(2000) DEFAULT NULL,
  `WarningEnNote` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warninglevel
-- ----------------------------
INSERT INTO `warninglevel` VALUES ('1', 'FAIL', '设备故障', '5', '5', '1', null, '6', '设备故障', '', '', '1', 'FAIL', null, 'Equipment Failure');
INSERT INTO `warninglevel` VALUES ('2', 'POWERALM', '电源故障', '5', '5', '2', null, '1', '电源故障', '业务误码或中断', null, '1', 'powerProblem', '检查电源输出电压', 'Power Failure');
INSERT INTO `warninglevel` VALUES ('3', 'APS_MISMATCH', 'APS失配', '4', '4', '3', null, '3', 'APS失配', '业务只能工作在主用', '', '1', 'APS报文不符合规范', '检查APS模式是否正确', 'APS Mismatch');
INSERT INTO `warninglevel` VALUES ('4', 'SW_FAIL', '倒换失败', '4', '4', '4', null, '1', '倒换失败', '业务只能工作在主用', null, '1', 'FOP', '检查备用链路的OAM配置以及各相关端口是否LINK等', 'Switching Failure');
INSERT INTO `warninglevel` VALUES ('5', 'PTP_LOS', '精确时间同步丢失', '5', '5', '5', null, '1', '精确时间同步丢失', null, null, '1', null, null, 'Precise Time Synchronization Loss');
INSERT INTO `warninglevel` VALUES ('6', 'TMC_LOC', '通路连接确认信号丢失', '5', '5', '6', null, '1', '通路连接确认信号丢失', '业务可能中断', null, '1', 'LOC', '检查链路连接是否正常，配置是否正确', 'Path Connection Confirmation Signal Loss');
INSERT INTO `warninglevel` VALUES ('7', 'TMP_LOC', '通道连接确认信号丢失', '5', '5', '7', null, '1', '通道连接确认信号丢失', '业务可能中断', null, '1', 'LOC', '检查链路连接是否正常，配置是否正确', 'Channel Connection Confirmation Signal Loss');
INSERT INTO `warninglevel` VALUES ('8', 'ETH_LOC', '以太网连接确认信号丢失', '5', '5', '8', null, '1', '以太网连接确认信号丢失', '业务可能中断', null, '1', 'LOC', '检查本地和对端OAM配置是否正确', 'ETH Connection Confirmation Signal Loss');
INSERT INTO `warninglevel` VALUES ('9', 'TMP_SSF', '通道服务层信号失效', '5', '5', '9', null, '1', '通道服务层信号失效', '业务可能中断', null, '1', '单盘做线路盘时，某条线路link_los或者线路收无光，如果某条入tunnel转发表的入端口是此线路号，那么这条TMP会上报TMP_SSF告警。', '检查链路连接是否正常', 'Channel Service Layer Signal Failure');
INSERT INTO `warninglevel` VALUES ('10', 'TMS_LOC', '段连接确认信号丢失', '5', '5', '10', null, '1', '段连接确认信号丢失', '业务可能中断', null, '1', 'LOC', '检查链路连接是否正常，配置是否正确', 'Segment Connection Confirmation Signal Loss');
INSERT INTO `warninglevel` VALUES ('11', 'LINK_LOS', '连接信号丢失', '5', '5', '11', null, '1', '连接信号丢失', '该端口相关的业务中断\r\n', '', '1', 'Link Fault', '检查连纤和光模块等', 'Connect the signal loss');
INSERT INTO `warninglevel` VALUES ('12', 'PK_LOS', '丢包率过限', '4', '4', '12', null, '2', '丢包率过限', '业务丢包或误码', null, '1', 'Threshold Crossed', '看是否拥塞或者限速', 'Packet Loss Rate Over The Limit');
INSERT INTO `warninglevel` VALUES ('13', 'CLKI_LOS', '外时钟中断', '5', '5', '13', null, '1', '外时钟源坏了；接外时钟的连线断了；时钟盘坏了', '对业务没有影响', null, '1', '外时钟端口down掉', '检查外时钟源工作是否正常；检查外时钟源的连线是否断了或者解除不好；若时钟盘坏了，更换一张时钟盘', 'External Clock Interrupt');
INSERT INTO `warninglevel` VALUES ('14', 'E1_LOS', '2M信号丢失', '5', '5', '14', null, '1', '2M信号丢失', 'E1接口没有信号输入', null, '1', 'LOS', '检测该设备是否连接2M线', '2M Signal Loss');
INSERT INTO `warninglevel` VALUES ('15', 'PTP_PORT_ALM', 'PTP端口异常告警', '4', '4', '65', null, '1', 'PTP端口异常告警', '业务中断', '', '1', 'PTP端口LinkDown', '检查PTP端口状态是否为Down', 'PTP Port Exception Alarm');
INSERT INTO `warninglevel` VALUES ('16', 'TMS_SD', '段信号劣化', '4', '4', '66', null, '1', '可能是接收光功率太小或者太大，在接收机灵敏度附近或者过载；可能是光接头脏了使接收光功率不在正常范围内；光纤转接插头损伤或者解除不良，或者光纤断裂；可能是光路盘有故障', '业务可能中断', '', '1', '复用段信号劣化,暂时没有实现', '用光功率计测量接收光功率；检查接收光功率是否正常；检查光接头断面是否清洁，对端发送光功率是否正常；当接收功率过大时需加衰减器；接收光功率过小时可更换发送光功率大的机盘', 'Segment Signal Degradation');
INSERT INTO `warninglevel` VALUES ('17', 'TMS_SF', '段信号失效', '4', '4', '67', null, '1', '当某端设备的群路盘接收侧出线SPI-LOS、RS-LF等故障时，会向下游站传送一个对告信号MS-FERF或MS-RDI；当被测设备出线AU-LOP、TU-LOP…等故障时，会向对端设备发HP-FERF，对端会出现HE-FERF或HP-RDI告警，此告警信号通过发送侧传送到对端设备，对端就检测出了对告指示，产生远端告警，让发送端知道接收端已经收到了发来的高阶通道AIS或者检测到高阶通道失效，并回送一个高阶通道远端接收失效；通道未配置', '业务可能中断', null, '1', null, '由于此告警一般是由对端收不好产生或者本端发不好产生，应处理上游站的故障才能消除失效告警', 'Segment Signal Failure');
INSERT INTO `warninglevel` VALUES ('18', 'SWR', '倒收', '3', '3', '97', null, '1', '由于主用信道上存在故障，不能正常工作，电路由主用信道倒换到备用信道', '无影响\r\n对业务没有影响', null, '1', '电路由主用信道倒换到备用信道', '检查光纤是否连接好；检查主用链路上是否有告警', 'Reverse Charge');
INSERT INTO `warninglevel` VALUES ('19', 'BRIDGE', '桥接', '3', '3', '98', null, '1', '如果设备采用共享复用段保护环，当环上光纤中断或断裂光路盘出现R-LOS告警或出现R-LOF、MS-AIS、MS-SD(信号劣化)、MS-EXC(复用段误码率突破）。当设备出现上述告警时，设备上相邻两站会发生桥接告警，通过这两个站的电路都会由工作信道桥接到保护信道。', '对业务没有影响', '', '1', '线路自动倒换', '通过网管查找告警类型，根据不同的告警故障采用不同的方法排除故障，当所有群路告警消失后等待几分钟，桥接告警就会消失。电路由保护信道返回到工作信道', 'Bridging');
INSERT INTO `warninglevel` VALUES ('20', 'SWTR', '等待恢复', '3', '3', '99', null, '1', '等待电路恢复到主用信道', '对业务没有影响', null, '1', '保护工作在备用，且主备用均无告警，在等待恢复时间内', '等待电路恢复到主用信道', 'Wait For Recovery');
INSERT INTO `warninglevel` VALUES ('21', 'ETH_MMG', '以太网不期望的维护实体组', '5', '5', '100', null, '1', '以太网不期望的维护实体组', '对业务没有影响', null, '1', 'MMG', '检查以太网两端MEG信息是否匹配', 'ETH Undesired Maintenance Entity Group');
INSERT INTO `warninglevel` VALUES ('22', 'ETH_UNM', '以太网不期望的维护实体组端点', '5', '5', '101', null, '1', '以太网不期望的维护实体组端点', '对业务没有影响', null, '1', 'UNM', '检查以太网两端MEP是否匹配', 'ETH Undesired Maintenance Entity Group Endpoint ');
INSERT INTO `warninglevel` VALUES ('23', 'ETH_UNP', '以太网时间间隔失配', '5', '5', '102', null, '1', '以太网时间间隔失配', '对业务没有影响', null, '1', 'UNP', '检查以太网两端CCM报文周期是否匹配', 'ETH Interval Mismatch');
INSERT INTO `warninglevel` VALUES ('24', 'ETH_RDI', '以太网远端故障指示', '4', '4', '103', null, '1', '以太网远端故障指示', '业务可能中断', '', '1', 'RDI', '检查对端是否存在以太网LOC/MMG/UNM等告警，先消除对端告警', 'ETH Remote Fault Indication');
INSERT INTO `warninglevel` VALUES ('25', 'TMP_UNP', '通道时间间隔失配', '5', '5', '104', null, '1', '通道时间间隔失配', '对业务没有影响', null, '1', 'UNP', '检查LSP两端CV帧周期是否匹配', 'Channel Interval Mismatch');
INSERT INTO `warninglevel` VALUES ('26', 'TMC_UNP', '通路时间间隔失配', '5', '5', '105', null, '1', '通路时间间隔失配', '对业务没有影响', null, '1', 'UNP', '检查PW两端CV帧周期是否匹配', 'Path Interval Mismatch');
INSERT INTO `warninglevel` VALUES ('27', 'TMP_RDI', '通道远端故障指示', '4', '4', '106', null, '1', '通道远端故障指示', '业务可能中断', null, '1', 'RDI', '检查对端是否存在LSP层LOC/MMG/UNM等告警，先消除对端告警', 'Channel Remote Fault Indication');
INSERT INTO `warninglevel` VALUES ('28', 'TMC_RDI', '通路远端故障指示', '4', '4', '107', null, '1', '通路远端故障指示', '业务可能中断', null, '1', 'RDI', '检查对端是否存在PW层LOC/MMG/UNM等告警，先消除对端告警', 'Path Remote Fault Indication');
INSERT INTO `warninglevel` VALUES ('29', 'TMP_AIS', '通道告警指示信号', '4', '4', '108', null, '1', '通道告警指示信号', null, null, '1', 'AIS', null, 'Channel Alarm Indication Signal');
INSERT INTO `warninglevel` VALUES ('30', 'TMC_AIS', '通路告警指示信号', '4', '4', '109', null, '1', '通路告警指示信号', null, null, '1', 'AIS', null, 'Path Alarm Indication Signal');
INSERT INTO `warninglevel` VALUES ('31', 'TMP_MMG', '通道不期望的维护实体组', '5', '5', '110', null, '1', '通道不期望的维护实体组', '对业务没有影响', null, '1', 'MMG', '检查LSP两端MEG信息是否匹配', 'Channel Undesired Maintenance Entity Group');
INSERT INTO `warninglevel` VALUES ('32', 'TMC_MMG', '通路不期望的维护实体组', '5', '5', '111', null, '1', '通路不期望的维护实体组', '对业务没有影响', null, '1', 'MMG', '检查PW两端MEG信息是否匹配', 'Path Undesired Maintenance Entity Group');
INSERT INTO `warninglevel` VALUES ('33', 'TMP_UNM', '通道不期望的维护实体组端点', '5', '5', '112', null, '1', '通道不期望的维护实体组端点', '对业务没有影响', null, '1', 'UNM', '检查LSP两端MEP是否匹配', 'Channel Undesired Maintenance Entity Group Endpoint');
INSERT INTO `warninglevel` VALUES ('34', 'TMC_UNM', '通路不期望的维护实体组端点', '5', '5', '113', null, '1', '通路不期望的维护实体组端点', '对业务没有影响', null, '1', 'UNM', '检查PW两端MEP是否匹配', 'Path Undesired Maintenance Entity Group Endpoint');
INSERT INTO `warninglevel` VALUES ('35', 'TMC_CSF', '通路客户信号失效', '4', '4', '114', null, '1', '通路客户信号失效', '业务中断', null, '1', '本端客户层信号失效', '检查UNI口光纤是否连接好', 'Path client signal failure');
INSERT INTO `warninglevel` VALUES ('36', 'CFG_MISMATCH', '配置失配', '3', '3', '115', null, '1', '配置失配', '业务中断', null, '1', '网管配置数据不正确', '检查网管配置', 'Configuration Mismatch');
INSERT INTO `warninglevel` VALUES ('37', 'TMS_UNP', '段时间间隔失配', '5', '5', '116', null, '1', '段时间间隔失配', '对业务没有影响', null, '1', 'UNP', '检查段层两端CV帧周期是否匹配', 'Segment Interval Mismatch');
INSERT INTO `warninglevel` VALUES ('38', 'TMS_RDI', '段远端故障指示', '4', '4', '117', null, '1', '段远端故障指示', '业务可能中断', null, '1', 'RDI', '检查对端是否存在段层连通性检测相关告警，先解决对端告警', 'Segment Remote fault indication');
INSERT INTO `warninglevel` VALUES ('39', 'TMS_AIS', '段告警指示信号', '3', '3', '118', null, '1', '段告警指示信号', null, null, '1', null, null, 'Segment Alarm Indication Signal');
INSERT INTO `warninglevel` VALUES ('40', 'TMS_MMG', '段不期望的维护实体组', '5', '5', '119', null, '1', '段不期望的维护实体组', '对业务没有影响', null, '1', 'MMG', '检查段两端MEG信息是否匹配', 'Segment Undesired Maintenance Entity Group');
INSERT INTO `warninglevel` VALUES ('41', 'TMS_UNM', '段不期望的维护实体组端点', '5', '5', '120', null, '1', '段不期望的维护实体组端点', '对业务没有影响', null, '1', 'UNM', '检查段两端MEP是否匹配', 'Segment Undesired Maintenance Entity Group Endpoint');
INSERT INTO `warninglevel` VALUES ('42', 'CRC_ERR', 'CRC校验错', '4', '4', '121', '端口', '2', 'CRC校验错', '业务有误码', null, '1', 'Threshold Crossed ', '检查光纤光模块及该端口是否异常', 'CRC Error');
INSERT INTO `warninglevel` VALUES ('43', 'RX_ERR', '收坏包过限', '4', '4', '122', null, '2', '收坏包过限', '业务有误码', null, '1', 'Threshold Crossed ', '检查光纤光模块及该端口是否异常', 'Receive Bad Packets Over Limit');
INSERT INTO `warninglevel` VALUES ('44', 'ALN_ERR', '对齐错误过限', '4', '4', '123', null, '2', '对齐错误过限', '业务有误码', null, '1', 'Threshold Crossed ', '检查光纤光模块及该端口是否异常', 'Alignment Error Over Limit');
INSERT INTO `warninglevel` VALUES ('45', 'CLKI_AIS', '外时钟输入源AIS', '3', '3', '124', null, '1', '外时钟输入源AIS', '对业务没有影响', null, '1', '远端信号丢失', '检查光纤光模块及该端口是否异常', 'External Clock Input Source AIS');
INSERT INTO `warninglevel` VALUES ('46', 'E1_AIS', '2M告警指示信号', '4', '4', '125', null, '1', '2M告警指示信号', '远端2M业务丢失', null, '1', 'AIS', '检测上游设备2M业务是否正常，是否E1_LOS', '2M Alarm Indication Signal');
INSERT INTO `warninglevel` VALUES ('47', 'ETHLB_LOS', '以太网环回信号超时', '2', '2', '197', null, '1', '以太网环回信号超时', '业务中断', null, '1', '以太网OAM收环回帧超时', '链路异常，检查链路报文收发情况', 'ETH Loopback Signal Timeout');
INSERT INTO `warninglevel` VALUES ('48', 'CUR_LOCK', '当前状态锁定', '2', '2', '177', null, '3', '当前状态锁定', '对业务没有影响影响', null, '1', '设备处于时钟锁定状态', '提示告警，无需检查', 'Current State Locked');
INSERT INTO `warninglevel` VALUES ('49', 'FORCE_SWITCH', '强制倒换', '2', '2', '178', null, '1', '强制倒换', '对业务没有影响', null, '1', '下强制倒换控制命令', '提示告警，无需检查', 'Forced Switching');
INSERT INTO `warninglevel` VALUES ('50', 'MANUAL_SWITCH', '人工倒换', '2', '2', '179', null, '1', '人工倒换', '对业务没有影响', null, '1', '下人工倒换控制命令', '提示告警，无需检查', 'Manual Switching');
INSERT INTO `warninglevel` VALUES ('51', 'REF_SWITCH', '时钟参考源切换', '2', '2', '181', null, '1', '时钟参考源切换', '对业务没有影响', null, '1', '时钟参考源人工或者链路问题致切换', '提示告警，无需检查', 'Clock Reference Source Switch');
INSERT INTO `warninglevel` VALUES ('52', 'LOCK_MAIN', '锁定到主用', '2', '2', '182', null, '4', '锁定到主用', '对业务没有影响', null, '1', '下锁定到主用控制命令', '提示告警，无需检查', 'Locked To The Master');
INSERT INTO `warninglevel` VALUES ('53', 'TMP_LCK', '通道锁定', '4', '4', '183', null, '1', '通道锁定', '业务中断', null, '1', 'LCK', '对端设备发送了含有此告警的信号，检查对端设备输入业务是否正常', 'Channel Lock');
INSERT INTO `warninglevel` VALUES ('54', 'TMC_LCK', '通路锁定', '4', '4', '184', null, '1', '通路锁定', '业务中断', null, '1', 'LCK', '对端设备发送了含有此告警的信号，检查对端设备输入业务是否正常', 'Path Lock');
INSERT INTO `warninglevel` VALUES ('55', 'TMS_LCK', '段锁定', '4', '4', '185', null, '1', '段锁定', '业务中断', null, '1', 'LCK', '对端设备发送了含有此告警的信号，检查对端设备输入业务是否正常', 'Segment Lock');
INSERT INTO `warninglevel` VALUES ('56', 'ETH_LCK', '以太网锁定', '4', '4', '186', null, '1', '以太网锁定', '业务中断', null, '1', 'LCK', '对端设备发送了含有此告警的信号，检查对端设备输入业务是否正常', 'ETH Lock');
INSERT INTO `warninglevel` VALUES ('57', 'TMPLB_LOS', '通道环回信号超时', '5', '5', '187', null, '1', '通道环回信号超时', '业务可能中断', null, '1', 'Link Fault', '链路异常，检查链路报文收发情况', 'Channel Loopback Signal Timeout');
INSERT INTO `warninglevel` VALUES ('58', 'TMCLB_LOS', '通路环回信号超时', '5', '5', '188', null, '1', '通路环回信号超时', '业务可能中断', null, '1', 'Link Fault', '链路异常，检查链路报文收发情况', 'Path Loopback Signal Timeout');
INSERT INTO `warninglevel` VALUES ('59', 'TMSLB_LOS', '段环回信号超时', '5', '5', '189', null, '1', '段环回信号超时', '业务可能中断', null, '1', 'Link Fault', '链路异常，检查链路报文收发情况', 'Segment Loopback Signal Timeout');
INSERT INTO `warninglevel` VALUES ('60', 'LOOP', '设备环回', '2', '2', '190', null, '4', '设备环回', '对非相关端口的业务无影响', null, '1', 'configurationorCustomizingError ', '提示告警，无需检查', 'Equipment Loopback');
INSERT INTO `warninglevel` VALUES ('61', 'LOOPL', '线路环回', '2', '2', '191', null, '4', '线路环回', '对非相关端口的业务无影响', null, '1', 'configurationorCustomizingError', '提示告警，无需检查', 'Line Loopback');
INSERT INTO `warninglevel` VALUES ('62', 'PORT_MIRROR', '端口镜像', '2', '2', '192', null, '4', '端口镜像', '对非相关端口的业务无影响', null, '1', 'configurationorCustomizingError', '提示告警，无需检查', 'Port Mirroring');
INSERT INTO `warninglevel` VALUES ('63', 'PORT_OFF', '端口关断', '4', '4', '193', null, '1', '端口关断', '对非相关端口的业务无影响', null, '1', 'dCCFailure', '提示告警，无需检查', 'Port Shutdown');
INSERT INTO `warninglevel` VALUES ('64', 'ES_LIMIT  ', '误码秒超限', '2', '2', '194', null, '1', '误码秒超限', null, null, '1', 'ES_LIMIT  ', null, 'Errored Seconds Overrun');
INSERT INTO `warninglevel` VALUES ('65', 'SYSLTI', '系统同步源丢失', '4', '4', '1', null, '3', '系统同步源丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('66', 'SYSLTO', '导出同步源丢失', '4', '4', '2', null, '3', '导出同步源丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('67', 'SYNC_LOS', '同步源信号丢失', '4', '4', '3', null, '3', '同步源信号丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('68', 'SYNC_LOF', '同步源帧丢失', '3', '3', '4', null, '3', '同步源帧丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('69', 'SYNC_AIS', '同步源AIS', '3', '3', '5', null, '3', '同步源AIS', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('70', 'SYNC_BAD', '同步源信号劣化', '3', '3', '6', null, '3', '同步源信号劣化', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('71', 'SYNC_SSM_MisMatch', 'SSM失配', '3', '3', '7', null, '3', 'SSM失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('72', 'PTP_LOT', '时间源丢失', '4', '4', '8', null, '3', '时间源丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('73', 'PTP_LOS', '1588信号丢失', '4', '4', '9', null, '3', '1588信号丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('74', 'PowerFail', '电源失效', '5', '5', '20', null, '3', '电源失效', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('75', 'HighTemp', '温度高越限告警', '5', '5', '21', null, '3', '温度高越限告警', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('76', 'LowTemp', '温度低越限告警', '5', '5', '22', null, '3', '温度低越限告警', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('77', 'FAN_FAIL', '风扇失效', '4', '4', '23', null, '3', '风扇失效', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('78', 'PKG_REMOVED', '单板不在位', '4', '4', '27', null, '3', '单板不在位', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('79', 'PKG_TYPE', '物理板和逻辑板不一致;或物理板刚刚插上,未启动好', '4', '4', '28', null, '3', '物理板和逻辑板不一致;或物理板刚刚插上,未启动好', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('80', 'PKG_FAIL', '单板故障', '5', '5', '29', null, '3', '单板故障', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('81', 'LOS', '信号丢失', '5', '5', '30', null, '1', '信号丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('82', 'AIS', '告警指示信号', '4', '4', '40', null, '1', '告警指示信号', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('83', 'RDI', '远端缺陷指示', '3', '3', '41', null, '1', '远端缺陷指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('84', 'LOC', '连续性丢失', '5', '5', '42', null, '1', '连续性丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('85', 'LCK', '锁定', '4', '4', '43', null, '1', '锁定', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('86', 'Mismerge', '未期望的MEG', '5', '5', '44', null, '1', '未期望的MEG', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('87', 'UnexpMEP', '未期望的MEP', '5', '5', '45', null, '1', '未期望的MEP', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('88', 'UnexpPrd', '未期望的时间间隔', '3', '3', '46', null, '1', '未期望的时间间隔', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('89', 'UnexpMel', '未期望的MEG层次', '4', '4', '47', null, '1', '未期望的MEG层次', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('90', 'CSF', '客户侧信号失效', '4', '4', '49', null, '1', '客户侧信号失效', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('91', 'APSMismatch', 'APS字节失配', '4', '4', '50', null, '1', 'APS字节失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('92', 'LOF', '帧丢失', '5', '5', '60', null, '1', '帧丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('93', 'OOF', '帧失步', '5', '5', '61', null, '1', '帧失步', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('94', 'RS_TIM', '再生段跟踪标识(J0)失配', '3', '3', '62', null, '1', '再生段跟踪标识(J0)失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('95', 'RS_EXC', '再生段误码率越限', '4', '4', '63', null, '1', '再生段误码率越限', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('96', 'RS_DEG', '再生段信号劣化', '3', '3', '64', null, '1', '再生段信号劣化', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('97', 'MS_AIS', '复用段告警指示', '4', '4', '65', null, '1', '复用段告警指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('98', 'MS_RDI', '复用段远端缺陷指示', '3', '3', '66', null, '1', '复用段远端缺陷指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('99', 'MS_EXC', '复用段误码率越限', '4', '4', '67', null, '1', '复用段误码率越限', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('100', 'MS_DEG', '复用段信号劣化', '3', '3', '68', null, '1', '复用段信号劣化', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('101', 'AU_LOP', '管理单元指针丢失', '4', '4', '69', null, '1', '管理单元指针丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('102', 'AU_AIS', '管理单元告警指示', '4', '4', '70', null, '1', '管理单元告警指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('103', 'HP_TIM', '高阶通道跟踪标识(J1)失配', '3', '3', '71', null, '1', '高阶通道跟踪标识(J1)失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('104', 'HP_UNEQ', '高阶通道未装载', '3', '3', '72', null, '1', '高阶通道未装载', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('105', 'HP_PLMF', '高阶通道净负荷失配', '3', '3', '73', null, '1', '高阶通道净负荷失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('106', 'HP_RDI', '高阶通道远端缺陷指示', '3', '3', '74', null, '1', '高阶通道远端缺陷指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('107', 'HP_EXC', '高阶通道误码率越限', '4', '4', '75', null, '1', '高阶通道误码率越限', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('108', 'HP_DEG', '高阶通道信号劣化', '3', '3', '76', null, '1', '高阶通道信号劣化', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('109', 'TU_LOP', '支路单元指针丢失', '4', '4', '77', null, '1', '支路单元指针丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('110', 'TU_LOM', '支路单元复帧丢失', '4', '4', '78', null, '1', '支路单元复帧丢失', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('111', 'TU_AIS', '低阶通道告警指示', '4', '4', '79', null, '1', '低阶通道告警指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('112', 'LP_TIM', '低阶通道跟踪标识失配', '3', '3', '80', null, '1', '低阶通道跟踪标识失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('113', 'LP_UNEQ', '低阶通道未装载', '3', '3', '81', null, '1', '低阶通道未装载', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('114', 'LP_PLMF', '低阶通道净负荷失配', '3', '3', '82', null, '1', '低阶通道净负荷失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('115', 'LP_RDI', '低阶通道远端缺陷指示', '3', '3', '83', null, '1', '低阶通道远端缺陷指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('116', 'LP_EXC', '低阶通道误码率越限', '4', '4', '84', null, '1', '低阶通道误码率越限', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('117', 'LP_DEG', '低阶通道误码率劣化', '3', '3', '85', null, '1', '低阶通道误码率劣化', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('118', 'KByteMisMatch', 'K字节失配', '4', '4', '86', null, '1', 'K字节失配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('119', 'LsrOffline', '激光器不在位', '5', '5', '100', null, '3', '激光器不在位', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('120', 'LsrMisMatch', '激光器不匹配', '4', '4', '104', null, '3', '激光器不匹配', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('121', 'ELinkFail', '以太网物理接口链路失效', '5', '5', '110', null, '1', '以太网物理接口链路失效', '', '', '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('122', 'LOPS', '分组丢失状态', '4', '4', '120', null, '1', '分组丢失状态', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('123', 'CES_AIS', 'SATop PW AIS', '4', '4', '124', null, '1', 'SATop PW AIS', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('124', 'CES_RDI', 'SATop PW RDI', '4', '4', '125', null, '1', 'SATop PW RDI', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('125', 'P_AIS', 'PDH端口告警指示', '4', '4', '126', null, '1', 'PDH端口告警指示', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('126', 'TSF', '承载层故障', '4', '4', '127', null, '1', '承载层故障', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('127', 'OUTSIDEALM', '外部告警', '4', '4', '128', null, '5', '外部告警', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('128', 'BUSERROR', '总线告警', '5', '5', '129', null, '3', '总线告警', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('129', 'OUTSIDEALM1', '开关量告警1', '4', '4', '131', null, '5', '开关量告警1', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('130', 'OUTSIDEALM2', '开关量告警2', '4', '4', '132', null, '5', '开关量告警2', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('131', 'OUTSIDEALM3', '开关量告警3', '4', '4', '133', null, '5', '开关量告警3', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('132', 'OUTSIDEALM4', '开关量告警4', '4', '4', '134', null, '5', '开关量告警4', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('133', 'OUTSIDEALM5', '开关量告警5', '4', '4', '135', null, '5', '开关量告警5', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('134', 'OUTSIDEALM6', '开关量告警6', '4', '4', '136', null, '5', '开关量告警6', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('135', 'OUTSIDEALM7', '开关量告警7', '4', '4', '137', null, '5', '开关量告警7', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('136', 'OUTSIDEALM8', '开关量告警8', '4', '4', '138', null, '5', '开关量告警8', null, null, '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('137', 'ARP_REACH_MAX', 'ARP达到系统最大数', '4', '4', '140', null, '4', 'ARP达到系统最大数', 'henyanzhong', '111', '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('138', 'ONU_MISMATCH', 'onu认证完成但没有匹配完成', '5', '5', '143', null, '4', 'onu认证完成但没有匹配完成', '', '', '2', null, null, null);
INSERT INTO `warninglevel` VALUES ('139', 'LRSOFFLINE', '光模块不在位', '4', '4', '16', null, '1', '光模块不在位', '端口没有插入光模块', null, '1', 'dCCFailure', '检测该端口是否插入光模块', 'Optical Module Not In Position');
INSERT INTO `warninglevel` VALUES ('140', 'SFP_NOLIGH', '光模块无光', '4', '4', '17', null, '1', '光模块无光', '端口没有插入光模块', null, '1', 'dCCFailure', '检测该端口的光纤是否正常', 'Optical Module Without Light');
INSERT INTO `warninglevel` VALUES ('141', 'SFP_FAULT', '光模块故障', '4', '4', '18', null, '1', '光模块故障', '光模块发生故障，业务断', null, '1', 'dCCFailure', '更换光模块', 'Optical Module Fault');
INSERT INTO `warninglevel` VALUES ('142', 'E1_LOOPL', 'E1线路环回', '2', '2', '195', null, '4', 'E1线路环回', '本地E1业务环回', null, '1', 'configurationorCustomizingError', '下发控制命令的关E1线路环回', 'E1 Line Loopback');
INSERT INTO `warninglevel` VALUES ('143', 'E1_LOOPD', 'E1设备环回', '2', '2', '196', null, '4', 'E1设备环回', '远端进来的E1业务环回回去', null, '1', 'configurationorCustomizingError', '下发控制命令的关E1设备环回', 'Loop Of E1 Equipment');
INSERT INTO `warninglevel` VALUES ('144', 'E1_LOF ', '2M帧失步告警', '5', '5', '19', null, '1', '2M帧失步告警', null, null, '1', 'LOF', null, '2M Frame Stall Alarm');
INSERT INTO `warninglevel` VALUES ('145', 'E1_LOM', '2M复帧失步告警', '5', '5', '20', null, '1', '2M复帧失步告警', null, null, '1', 'LOM', null, '2M Complex Frame Stall Alarm');
INSERT INTO `warninglevel` VALUES ('146', 'BACKUP_FAULT', '备用故障', '2', '2', '180', null, '1', '备用故障', '主用链路正常工作的情况下，对业务没有影响；主用链路异常则业务中断', null, '1', 'BACKUP_FAULT', '根据备用链路告警信息处理备用链路故障', 'Reserve Fault');
INSERT INTO `warninglevel` VALUES ('147', 'E1_CRC', '2M CRC校验错', '3', '3', '126', null, '1', '2M CRC校验错', null, null, '1', 'CRC', null, '2M CRC Checksum Error');
INSERT INTO `warninglevel` VALUES ('148', 'E1_RDI', '2M远端缺陷指示', '3', '3', '127', null, '1', '2M远端缺陷指示', null, null, '1', 'RDI', null, '2M Remote Defect Indication');
INSERT INTO `warninglevel` VALUES ('149', 'E1_MRDI', 'E1 MRDI', '3', '3', '128', null, '1', 'E1 MRDI', null, null, '1', 'MRDI', null, 'E1 MRDI');
INSERT INTO `warninglevel` VALUES ('150', 'SFP_TempHigh', '光模块温度过高', '3', '3', '22', null, '5', '机房空调工作异常；设备风扇工作异常；设备过滤网被灰尘堵塞；盘纤盒内光线过多或者过长，堵塞了通风；2M线和电源线布线不规范，阻挡了散热通道', '对业务无影响', null, '1', 'highTemperature', '检修机房空调；更换故障风扇；清洗设备防尘网；整理盘纤盒内光线，在盘纤盒内光线不要预留过长，过长部分尽量在ODF架内进行盘纤；规范布线，布线应当不得影响设备通风散热', 'Optical Module Temperature Too High');
INSERT INTO `warninglevel` VALUES ('151', 'SFP_TempLow', '光模块温度过低', '3', '3', '23', null, '5', '光模块温度过低', '对业务无影响', null, '1', 'lowTemperature', '检测设备的温度，查看模块的温度数值', 'Optical Module Temperature Too Low');
INSERT INTO `warninglevel` VALUES ('152', 'SFP_VccHigh', '光模块工作电压过高', '5', '5', '24', null, '1', '光模块工作电压过高', null, null, '1', 'VccHigh', null, 'Optical Modules Operating Voltage Too High');
INSERT INTO `warninglevel` VALUES ('153', 'SFP_VccLow', '光模块工作电压过低', '5', '5', '25', null, '1', '光模块工作电压过低', null, null, '1', 'VccLow', null, 'Optical Modules Operating Voltage Too Low');
INSERT INTO `warninglevel` VALUES ('154', 'SFP_TxBiasHigh', '光模块发射偏流过高', '5', '5', '26', null, '1', '光模块发射偏流过高', null, null, '1', 'TxBiasHigh', null, 'Optical Module Transmitter Bias Current Too High');
INSERT INTO `warninglevel` VALUES ('155', 'SFP_TxBiasLow', '光模块发射偏流过低', '5', '5', '27', null, '1', '光模块发射偏流过低', null, null, '1', 'TxBiasLow', null, 'Optical Module Transmitter Bias Current Too Low');
INSERT INTO `warninglevel` VALUES ('156', 'SFP_TxPowHigh', '光模块发射光功率过高', '4', '4', '28', null, '1', '光模块发射光功率过高', null, null, '1', 'Excessive transmitter output power', null, 'Optical Module Transmit Optical Power Too High');
INSERT INTO `warninglevel` VALUES ('157', 'SFP_TxPowLow', '光模块发射光功率过低', '4', '4', '29', null, '3', '光模块发射光功率过低', null, null, '1', 'Reduced transmitter output power', null, 'Optical Module Transmit Optical Power Too Low');
INSERT INTO `warninglevel` VALUES ('158', 'SFP_RxPowHigh', '光模块接收光功率过高', '4', '4', '30', null, '3', '光模块接收光功率过高', null, null, '1', 'Excessive transmitter input power', null, 'Optical Module Receives Optical Power Too High');
INSERT INTO `warninglevel` VALUES ('159', 'SFP_RxPowLow', '光模块接收光功率过低', '4', '4', '32', null, '3', '光模块接收光功率过低', null, null, '1', 'Reduced transmitter input power', null, 'Optical Module Receives Optical Power Too Low');
INSERT INTO `warninglevel` VALUES ('160', 'Ci_TempHigh', '设备温度过高告警', '4', '4', '33', null, '2', '机房空调工作异常；设备风扇工作异常；设备过滤网被灰尘堵塞；盘纤盒内光线过多或者过长，堵塞了通风；2M线和电源线布线不规范，阻挡了散热通道', '可能导致业务误码', null, '1', 'Threshold Crossed ', '检修机房空调；更换故障风扇；清洗设备防尘网；整理盘纤盒内光线，在盘纤盒内光线不要预留过长，过长部分尽量在ODF架内进行盘纤；规范布线，布线应当不得影响设备通风散热', 'Device Temperature High  Alarm');
INSERT INTO `warninglevel` VALUES ('161', 'Ci_TempLow', '设备温度过低告警', '4', '4', '34', null, '2', '外界温度低于设备正常工作的最低温度', '可能导致业务误码', null, '1', 'Threshold Crossed', '关闭风扇；调节机房温度到设备正常工作温度范围内', 'Device Temperature Low  Alarm');
INSERT INTO `warninglevel` VALUES ('162', 'OAM_PEER_DYING_GASP', '对端dying gasp事件', '5', '5', '35', null, '3', '对端dying gasp事件', '业务中断', null, '1', 'Dying Gasp', '对端设备上电', 'Peer Dying Gasp Event');
INSERT INTO `warninglevel` VALUES ('163', 'OAM_PEER_LINK_FAULT', '对端link fault', '5', '5', '36', null, '1', '对端link fault', '业务中断', null, '1', 'Link Fault', '检查光纤连接是否正常', 'Peer Link Fault Event');
INSERT INTO `warninglevel` VALUES ('164', 'OAM_PEER_CRITICAL', '对端critical事件', '5', '5', '37', null, '1', '对端critical事件', null, null, '1', 'PEER_CRITICAL', null, 'Peer Critical Event');
INSERT INTO `warninglevel` VALUES ('165', 'OAM_PEER_ERR_SYMBOL', '对端错误符号门限事件', '4', '4', '68', null, '1', '对端错误符号门限事件', '对业务没有影响', null, '1', 'PEER_ERR_SYMBOL', '检查链路是否异常', 'Peer Error Symbol Threshold Event');
INSERT INTO `warninglevel` VALUES ('166', 'OAM_PEER_ERR_FRAME', '对端错误帧门限事件', '4', '4', '69', null, '1', '对端错误帧门限事件', '对业务没有影响', null, '1', 'PEER_ERR_FRAME', '检查链路是否异常', 'Peer Error Frame Threshold Event');
INSERT INTO `warninglevel` VALUES ('167', 'OAM_PEER_ERR_FRAME_PERIOD', '对端错误帧周期门限事件', '4', '4', '70', null, '1', '对端错误帧周期门限事件', '对业务没有影响', null, '1', 'PEER_ERR_FRAME_PERIOD', '检查链路是否异常', 'Peer Error Frame Period Threshold Event');
INSERT INTO `warninglevel` VALUES ('168', 'OAM_PEER_ERR_FRAME_SECOND', '对端错误帧秒门限事件', '4', '4', '71', null, '1', '对端错误帧秒门限事件', '对业务没有影响', null, '1', 'PEER_ERR_FRAME_SECOND', '检查链路是否异常', 'Peer Error Frame Second Threshold Event');
INSERT INTO `warninglevel` VALUES ('169', 'ETH_UNL', '以太网不期望的维护域级别', '5', '5', '132', null, '1', '以太网不期望的维护域级别', '对业务没有影响', null, '1', 'UNL', '检查以太网两端维护域级别是否匹配', 'ETH Unexpect To Maintain Domain Level');
INSERT INTO `warninglevel` VALUES ('170', 'ETH_AIS', '以太网告警指示信号', '3', '3', '131', null, '1', '以太网告警指示信号', '对业务没有影响', null, '1', 'AIS', '检查其服务层告警', 'ETH Alarm Indication Signal');
INSERT INTO `warninglevel` VALUES ('171', 'ETH_XCON_CCM', '以太网维护域交叉连接错误', '3', '3', '130', null, '1', '以太网维护域交叉连接错误', '对业务没有影响', null, '1', 'XCON_CCM', '检查两端配置mep级别或者ma名字是否匹配', 'ETH Maintenance Domain Cross Connect Error');
INSERT INTO `warninglevel` VALUES ('172', 'ETH_ERROR_CCM', '以太网维护域时间间隔错误', '3', '3', '129', null, '1', '以太网维护域时间间隔错误', '对业务没有影响', null, '1', 'ERROR_CCM', '检查两端周期是否一致、mep是否匹配', 'ETH Maintenance Domain Time Interval Error');
INSERT INTO `warninglevel` VALUES ('173', 'ETH_REMOTE_CCM', '以太网远端信号丢失', '4', '4', '21', null, '1', '以太网远端信号丢失', '对业务没有影响', null, '1', 'remoteCCMDefect', '检查链路或者对端配置', 'ETH Distal Signal Loss');
INSERT INTO `warninglevel` VALUES ('174', 'ETH_MAC_STATUS', '以太网维护端点端口错误', '2', '2', '198', null, '1', '以太网维护端点端口错误', '', null, '1', 'MAC_STATUS', '', 'ETH Port Maintenance Endpoint Error');
INSERT INTO `warninglevel` VALUES ('175', 'ETH_RDI_CCM ', '以太网远端错误指示', '4', '4', '199', '', '1', '以太网远端错误指示', '对业务没有影响', '', '1', 'RDI', '检查链路或者对端配置', 'ETH Remote Error Indication');
INSERT INTO `warninglevel` VALUES ('176', 'Ci_TempThreshHigh', '设备温度过高门限越限', '4', '4', '38', null, '2', '机房空调工作异常；设备风扇工作异常；设备过滤网被灰尘堵塞；盘纤盒内光线过多或者过长，堵塞了通风；2M线和电源线布线不规范，阻挡了散热通道', '对业务没有影响', null, '1', 'Threshold Crossed', '检修机房空调；更换故障风扇；清洗设备防尘网；整理盘纤盒内光线，在盘纤盒内光线不要预留过长，过长部分尽量在ODF架内进行盘纤；规范布线，布线应当不得影响设备通风散热', 'Equipment Temperature Too High Threshold Limit');
INSERT INTO `warninglevel` VALUES ('177', 'Ci_TempThreshLow', '设备温度过低门限越限', '4', '4', '39', null, '2', '外界温度低于设备正常工作的最低温度', '对业务没有影响', null, '1', 'Threshold Crossed', '关闭风扇；调节机房温度到设备正常工作温度范围内', 'Equipment Temperature Too Low Threshold Limit');
INSERT INTO `warninglevel` VALUES ('178', 'OAM_PEER_DISCOVERY', 'OAM 发现对端', '2', '2', '211', null, '1', 'OAM_PEER_DISCOVERY', '对业务没有影响', null, '1', 'PEER_DISCOVERY', '无需处理', 'OAM Discovery Peer');
INSERT INTO `warninglevel` VALUES ('179', 'OAM_LOOPBACK_TIMEOUT', 'OAM环回超时', '2', '2', '212', null, '1', 'OAM_LOOPBACK_TIMEOUT', '业务可能中断', null, '1', 'LOOPBACK_TIMEOUT', '检查链路或者对端配置', 'OAM Loopback Timeout');
INSERT INTO `warninglevel` VALUES ('180', 'LBD_DISCOVERY', '以太网发现环路', '4', '4', '72', null, '1', 'LBD_DISCOVERY', '业务可能中断', null, '1', 'LBD_DISCOVERY', '检查设备连接拓扑', 'ETH Discovery Loop');
INSERT INTO `warninglevel` VALUES ('181', 'VoltageHign', '设备电压过高(>14V)', '5', '5', '40', null, '6', 'VoltageHign', '机房电源供电系统异常；机房停电，电池供电时间过长', null, '1', 'powerProblem', '检查机房电源供电系统；检查机房是否停电，在电池供电电压下降时及时进行发电，避免设备停电', 'Equipment Voltage Too High (> 14V)');
INSERT INTO `warninglevel` VALUES ('182', 'VoltageLow', '设备电压过低(<9V)', '5', '5', '41', null, '6', 'VoltageLow', '机房电源供电系统异常；机房停电，电池供电时间过长', null, '1', 'powerProblem', '检查机房电源供电系统；检查机房是否停电，在电池供电电压下降时及时进行发电，避免设备停电', 'Equipment Voltage Too Low (< 9V)');
INSERT INTO `warninglevel` VALUES ('183', 'FAN_FAULT', '风扇故障或不在位', '5', '5', '48', null, '1', '风扇故障或不在位', '业务可能因温度过高而误码', null, '1', 'FAN_FAULT', '检查风扇', 'Fan Fail Or Not In Place');
INSERT INTO `warninglevel` VALUES ('184', 'FAN_ERROR_SPEED', '风扇转速过缓', '5', '5', '49', null, '1', '风扇转速过缓', '业务可能因温度过高而误码', null, '1', 'ERROR_SPEED', '检查风扇和网管配置', 'Fan Speed Too Slow');
INSERT INTO `warninglevel` VALUES ('185', 'REMOTE_DEVICE_POWER_ON', '远端设备上电', '2', '2', '213', null, '1', '远端设备上电', '业务恢复', null, '1', 'REMOTE_DEVICE_POWER_ON', '无需处理', 'Remote Equipment Power On');
INSERT INTO `warninglevel` VALUES ('186', 'REMOTE_DEVICE_POWER_DOWN', '远端设备掉电', '2', '2', '214', null, '1', '远端设备掉电', '业务中断', null, '1', 'REMOTE_DEVICE_POWER_DOWN', '设备加电', 'Remote Equipment Power Off');
INSERT INTO `warninglevel` VALUES ('187', 'CONTEXT_PACKET_LOS', '本端链路包丢失', '5', '5', '15', '', '1', '本端链路包丢失', '', '', '1', 'CONTEXT_PACKET_LOS', null, 'End Link Packet Loss');
INSERT INTO `warninglevel` VALUES ('188', 'RCONTEXT_PACKET_LOS', '远端链路包丢失', '3', '3', '133', '', '1', '远端链路包丢失', '', '', '1', 'RCONTEXT_PACKET_LOS', null, 'Remote Link Packet Loss');
INSERT INTO `warninglevel` VALUES ('189', 'USER_LOCK', '用户被锁定', '2', '2', '1000', null, '1', '用户被锁定', '对业务没有影响', null, '1', 'LOCK', '输入正确密码', 'User Locked');
INSERT INTO `warninglevel` VALUES ('190', 'USERPASSWORERROR', '用户登录密码输入错误', '2', '2', '1050', null, '1', '用户登录密码输入错误', '对业务没有影响', null, '1', 'PASSWORERROR', '输入正确密码', 'User Login Password Input Error');
INSERT INTO `warninglevel` VALUES ('191', 'PERFORMANCETIME', '性能文件超存储时间', '2', '2', '1002', null, '1', '性能文件超存储时间', '对业务没有影响', null, '1', 'PERFORMANCETIME', '设置性能文件的存储时间', 'PM File Over Storage Time');
INSERT INTO `warninglevel` VALUES ('192', 'PERFORMANCERAMERROR', '性能文件存储内存溢出错误', '2', '2', '1001', null, '1', '性能文件存储内存溢出错误', '对业务没有影响', null, '1', 'PERFORMANCERAMERROR', '重新设置性能文件的存储内存', 'PM File Storage Memory Overflow Error');
INSERT INTO `warninglevel` VALUES ('193', 'USERNAMEERROR', '用户登录名输入错误', '2', '2', '1051', null, '1', '用户登录名输入错误', '对业务没有影响', null, '1', 'userNameERROR', '输入正确用户名', 'User Login Name Input Errors');

-- ----------------------------
-- Table structure for `workips`
-- ----------------------------
DROP TABLE IF EXISTS `workips`;
CREATE TABLE `workips` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileId` int(11) DEFAULT NULL,
  `workIp1` varchar(20) DEFAULT NULL,
  `workIp2` varchar(20) DEFAULT NULL,
  `workIp3` varchar(20) DEFAULT NULL,
  `workIp4` varchar(20) DEFAULT NULL,
  `workIp5` varchar(20) DEFAULT NULL,
  `workIp6` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workips
-- ----------------------------
