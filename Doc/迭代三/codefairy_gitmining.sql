/*
Navicat MySQL Data Transfer

Source Server         : qicq
Source Server Version : 50540
Source Host           : 115.159.161.96:3306
Source Database       : codefairy_gitmining

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2016-05-08 17:11:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_collabrator
-- ----------------------------
DROP TABLE IF EXISTS `tbl_collabrator`;
CREATE TABLE `tbl_collabrator` (
  `repo` bigint(20) NOT NULL,
  `collabrator` bigint(20) NOT NULL,
  `collabrator_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`collabrator_id`),
  KEY `INDEX_REPO` (`repo`) USING HASH,
  KEY `INDEX_COLLABRATOR` (`collabrator`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_commit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commit`;
CREATE TABLE `tbl_commit` (
  `commit_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sha` varchar(100) NOT NULL,
  `committer` bigint(20) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `num_add` int(8) DEFAULT '0',
  `num_delete` int(8) DEFAULT '0',
  `num_changed` int(8) DEFAULT '0',
  `repo_id` bigint(20) NOT NULL,
  PRIMARY KEY (`commit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_commit_file
-- ----------------------------
DROP TABLE IF EXISTS `tbl_commit_file`;
CREATE TABLE `tbl_commit_file` (
  `commit_id` bigint(20) NOT NULL,
  `commit_file` varchar(100) NOT NULL,
  `num_add` int(8) DEFAULT '0',
  `num_delete` int(8) DEFAULT '0',
  `num_changed` int(8) DEFAULT '0',
  `commit_file_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`commit_file_id`),
  KEY `INDEX_COMMIT` (`commit_id`),
  KEY `INDEX_FILE` (`commit_file`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_contributor
-- ----------------------------
DROP TABLE IF EXISTS `tbl_contributor`;
CREATE TABLE `tbl_contributor` (
  `repo` bigint(20) NOT NULL,
  `contributor` bigint(20) NOT NULL,
  PRIMARY KEY (`repo`,`contributor`),
  KEY `INDEX_REPO` (`repo`),
  KEY `INDEX_CONTRIBUTOR` (`contributor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_register
-- ----------------------------
DROP TABLE IF EXISTS `tbl_register`;
CREATE TABLE `tbl_register` (
  `user_id` bigint(20) NOT NULL,
  `login_name` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `INDEX_USER` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_repo
-- ----------------------------
DROP TABLE IF EXISTS `tbl_repo`;
CREATE TABLE `tbl_repo` (
  `repo_id` bigint(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  `size` int(8) NOT NULL,
  `language` tinyint(4) NOT NULL,
  `url` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `num_star` int(8) DEFAULT '0',
  `num_fork` int(8) DEFAULT '0',
  `num_subscriber` int(8) DEFAULT '0',
  `num_contributor` int(8) DEFAULT '0',
  `num_collaborator` int(8) DEFAULT '0',
  `num_commit` int(8) DEFAULT '0',
  `num_pull` int(8) DEFAULT '0',
  `num_issue` int(8) DEFAULT '0',
  PRIMARY KEY (`repo_id`),
  KEY `IDNEX_OWNER` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_subscriber
-- ----------------------------
DROP TABLE IF EXISTS `tbl_subscriber`;
CREATE TABLE `tbl_subscriber` (
  `repo` bigint(20) NOT NULL,
  `subscriber` bigint(20) NOT NULL,
  `repo_subscriber_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`repo_subscriber_id`),
  KEY `INDEX_REPO` (`repo`),
  KEY `INDEX_SUBSCRIBER` (`subscriber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `user_id` bigint(20) NOT NULL,
  `login_name` varchar(40) NOT NULL,
  `avatar_url` varchar(100) NOT NULL,
  `blog` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `company` varchar(100) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `public_repo` int(8) DEFAULT '0',
  `public_gist` int(8) DEFAULT '0',
  `follower` int(8) DEFAULT '0',
  `following` int(8) DEFAULT '0',
  `email` varchar(100) NOT NULL,
  `bio` varchar(100) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `num_subscriber` int(8) DEFAULT '0',
  `num_contributor` int(8) DEFAULT '0',
  `num_collabrator` int(8) DEFAULT '0',
  `state` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
