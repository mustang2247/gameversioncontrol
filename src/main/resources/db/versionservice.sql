/*
 Navicat Premium Data Transfer

 Source Server         : db_aliyun
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : rm-2ze1y349f445d88nqo.mysql.rds.aliyuncs.com
 Source Database       : versionservice

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : utf-8

 Date: 10/17/2017 19:37:05 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `versioncontrol`
-- ----------------------------
DROP TABLE IF EXISTS `versioncontrol`;
CREATE TABLE `versioncontrol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(64) NOT NULL COMMENT '应用appid',
  `appname` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `channelid` varchar(100) DEFAULT NULL COMMENT '渠道id',
  `channelname` varchar(255) DEFAULT NULL COMMENT '渠道名称',
  `appversion` varchar(64) DEFAULT NULL COMMENT '当前应用版本',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `serverip` varchar(64) DEFAULT NULL COMMENT '服务器IP或者域名',
  `serverport` varchar(64) DEFAULT NULL COMMENT '服务器端口',
  `hotfix` varchar(200) DEFAULT NULL COMMENT '热更新信息',
  `shields` varchar(500) DEFAULT NULL COMMENT '屏蔽信息',
  `define1` varchar(200) DEFAULT NULL COMMENT '用户自定义',
  `define2` varchar(200) DEFAULT NULL COMMENT '用户自定义',
  `params` varchar(200) DEFAULT NULL COMMENT '热更新信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `versioncontrol`
-- ----------------------------
BEGIN;
INSERT INTO `versioncontrol` VALUES ('1', '1', '测试', '1', '测试', '1.9.0', '2017-10-17 19:01:02', '2017-10-17 19:01:02', 'test.poker.hulai.com', '7684', '{\"version\":\"1.9.0\",\"downloadPage\":\"http://fish.poker.hulai.com/download/fish.html?v=3\",\"detail\":\"1、游戏大厅优化\\n2、新增竞技场\\n3、新增世界BOSS\\n4、新增VIP核弹场\\n5、新增炮台“幻影”\\n6、新增翅膀“凯旋之翼”\",\"hotupdate_head_url\":\"https://hule', '{\"isUpdateRes\":false,\"iostishen\":false,\"notice\":true,\"persionInfo\":true,\"persionInfo_other\":true,\"sign\":true,\"setting\":true,\"shouchong\":true,\"yueka\":true,\"yueka_data\":true,\"fuli\":true,\"fuli_update\":true,\"fuli_tehui\":true,\"fuli_shuangbei\":false,\"fuli_leiji\":true,\"fuli_jiaqun\":true,\"fuli_shouji\":true,\"fuli_libaoduihuan\":true,\"xuyuan\":true,\"wingslibao\":true,\"gongcechoujiang\":false,\"bag\":true,\"bag_give\":true,\"task\":true,\"wings\":true,\"wings_give\":true,\"wings_data\":true,\"mingwen_data\":false,\"paotai\":t', null, null, '{\"iosPayCheckoutIp\":\"http://test.poker.hulai.com/callback/iosPay/pay\",\"tvPayCheckoutIp\":\"http://114.255.24.29/callback/newgamePay/pay\",\"iosHaveIpHttp\":\"http://ip.tyk.nu\",\"BIAddress\":\"http://binewcolle');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
