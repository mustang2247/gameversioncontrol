/*
Navicat MySQL Data Transfer

Source Server         : db_fishingonline
Source Server Version : 50628
Source Host           : 58c9e9da49932.gz.cdb.myqcloud.com:12187
Source Database       : versionservice

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-01-10 10:29:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hotupdatecheck
-- ----------------------------
DROP TABLE IF EXISTS `hotupdatecheck`;
CREATE TABLE `hotupdatecheck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(64) NOT NULL COMMENT '应用appid',
  `appname` varchar(255) NOT NULL COMMENT '应用名称',
  `channelid` varchar(100) NOT NULL COMMENT '渠道id',
  `channelname` varchar(255) NOT NULL COMMENT '渠道名称',
  `appversion` varchar(64) NOT NULL COMMENT '当前应用版本',
  `updatestrategy` int(2) NOT NULL DEFAULT '2' COMMENT '更新策略 1:提示更新 2:强制更新 3:不更新',
  `jumpappmarket` int(2) NOT NULL DEFAULT '0' COMMENT '是否跳转应用市场 0:不跳转应用内下载 1:跳转应用市场下载',
  `baseurl` varchar(100) NOT NULL DEFAULT '' COMMENT '热更baseUrl',
  `apkurl` varchar(200) NOT NULL DEFAULT '' COMMENT 'apk跟新地址',
  `promptcollection` varchar(500) DEFAULT NULL COMMENT '提示更新集合',
  `forcecollection` varchar(500) DEFAULT NULL COMMENT '强制更新集合 例如：1.0,1.1',
  `excludecollection` varchar(500) DEFAULT NULL COMMENT '排除更新集合',
  `updateinfo` varchar(500) DEFAULT NULL COMMENT '更新信息',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_appid_channelid` (`appid`,`channelid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='热更新检查';

-- ----------------------------
-- Table structure for hotupdatecheckonline
-- ----------------------------
DROP TABLE IF EXISTS `hotupdatecheckonline`;
CREATE TABLE `hotupdatecheckonline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(64) NOT NULL COMMENT '应用appid',
  `appname` varchar(255) NOT NULL COMMENT '应用名称',
  `channelid` varchar(100) NOT NULL COMMENT '渠道id',
  `channelname` varchar(255) NOT NULL COMMENT '渠道名称',
  `appversion` varchar(64) NOT NULL COMMENT '当前应用版本',
  `updatestrategy` int(2) NOT NULL DEFAULT '2' COMMENT '更新策略 1:提示更新 2:强制更新 3:不更新',
  `jumpappmarket` int(2) NOT NULL DEFAULT '0' COMMENT '是否跳转应用市场 0:不跳转应用内下载 1:跳转应用市场下载',
  `baseurl` varchar(100) NOT NULL DEFAULT '' COMMENT '热更baseUrl',
  `apkurl` varchar(200) NOT NULL DEFAULT '' COMMENT 'apk跟新地址',
  `promptcollection` varchar(500) DEFAULT NULL COMMENT '提示更新集合',
  `forcecollection` varchar(500) DEFAULT NULL COMMENT '强制更新集合 例如：1.0,1.1',
  `excludecollection` varchar(500) DEFAULT NULL COMMENT '排除更新集合',
  `updateinfo` varchar(500) DEFAULT NULL COMMENT '更新信息',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_appid_channelid` (`appid`,`channelid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='热更新检查线上表';

-- ----------------------------
-- Table structure for hotupdateconfig
-- ----------------------------
DROP TABLE IF EXISTS `hotupdateconfig`;
CREATE TABLE `hotupdateconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(64) NOT NULL COMMENT '应用appid',
  `appname` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `channelid` varchar(100) NOT NULL COMMENT '渠道id',
  `channelname` varchar(255) DEFAULT NULL COMMENT '渠道名称',
  `appversion` varchar(64) NOT NULL COMMENT '当前应用版本',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `serverip` varchar(200) DEFAULT NULL COMMENT '服务器IP或者域名',
  `serverport` varchar(64) DEFAULT NULL COMMENT '服务器端口',
  `hotfix` varchar(2000) DEFAULT NULL COMMENT '热更新信息',
  `shields` varchar(2000) DEFAULT NULL COMMENT '屏蔽信息',
  `define1` varchar(500) DEFAULT NULL COMMENT '用户自定义',
  `define2` varchar(500) DEFAULT NULL COMMENT '用户自定义',
  `params` varchar(500) DEFAULT NULL COMMENT '热更新信息',
  PRIMARY KEY (`id`),
  KEY `index_app` (`appid`,`channelid`,`appversion`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='版本配置文件';

-- ----------------------------
-- Table structure for hotupdatenotice
-- ----------------------------
DROP TABLE IF EXISTS `hotupdatenotice`;
CREATE TABLE `hotupdatenotice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(64) NOT NULL COMMENT '应用appid',
  `appname` varchar(255) NOT NULL COMMENT '应用名称',
  `appversion` varchar(64) NOT NULL COMMENT '当前应用版本',
  `updatemessage` varchar(5000) NOT NULL COMMENT '更新信息',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_appid` (`appid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='更新提示';

-- ----------------------------
-- Table structure for syspermission
-- ----------------------------
DROP TABLE IF EXISTS `syspermission`;
CREATE TABLE `syspermission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `parentid` bigint(20) DEFAULT NULL COMMENT '父编号',
  `parentids` varchar(255) DEFAULT NULL COMMENT '父编号列表',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `resourcetype` enum('menu','button') DEFAULT NULL COMMENT '资源类型，[menu|button]',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='权限实体类';

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用,如果不可用将不会添加给用户',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `role` varchar(255) DEFAULT NULL COMMENT '角色标识 程序中判断使用,如"admin",这个是唯一的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色实体类';

-- ----------------------------
-- Table structure for sysrolepermission
-- ----------------------------
DROP TABLE IF EXISTS `sysrolepermission`;
CREATE TABLE `sysrolepermission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pn90qffgw1e6lo1xhw964qadf` (`roleid`),
  KEY `FK_qr3wmwfxapktvdv5g6d4mbtta` (`permissionId`),
  CONSTRAINT `FK_pn90qffgw1e6lo1xhw964qadf` FOREIGN KEY (`roleid`) REFERENCES `sysrole` (`id`),
  CONSTRAINT `FK_qr3wmwfxapktvdv5g6d4mbtta` FOREIGN KEY (`permissionId`) REFERENCES `syspermission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysuserrole
-- ----------------------------
DROP TABLE IF EXISTS `sysuserrole`;
CREATE TABLE `sysuserrole` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `roleid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_io5ssq2ol6uqcx9nll8gfnm4n` (`uid`),
  KEY `FK_suwqmd7mystg1lwv8o4ffxaag` (`roleid`),
  CONSTRAINT `FK_io5ssq2ol6uqcx9nll8gfnm4n` FOREIGN KEY (`uid`) REFERENCES `userinfo` (`uid`),
  CONSTRAINT `FK_suwqmd7mystg1lwv8o4ffxaag` FOREIGN KEY (`roleid`) REFERENCES `sysrole` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称（昵称或者真实姓名，不同系统不同定义）',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '加密密码的盐',
  `state` tinyint(4) NOT NULL COMMENT '用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `UK_45fvrme4q2wy85b1vbf55hm6s` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息';
