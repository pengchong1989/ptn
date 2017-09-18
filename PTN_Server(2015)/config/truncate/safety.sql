TRUNCATE user_lock;
TRUNCATE user_field;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inst
-- ----------------------------
INSERT INTO `user_inst` VALUES ('1', 'admin', 'admin', '1', '1', null, null, null, null, '0.0.0.0', '255.255.255.254');

