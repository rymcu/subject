/*
 Navicat Premium Data Transfer

 Source Server         : rymcu_subject
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : rymcu_subject

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/10/2020 17:12:29
*/
-- 创建数据库
CREATE DATABASE `rymcu_subject` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

-- 创建用户
CREATE USER 'rymcu_subject' @'%' IDENTIFIED BY '1234';

-- 用户授权
GRANT ALL ON rymcu_subject.* TO 'rymcu_subject' @'%';

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for subject_answer_record
-- ----------------------------
DROP TABLE IF EXISTS `subject_answer_record`;
CREATE TABLE `subject_answer_record`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_QUESTION_ID` bigint NOT NULL COMMENT '题目编号',
  `ANSWER` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '我的答案 如abcd，填空值，or简答题的答案',
  `CREATED_TIME` datetime(0) NOT NULL COMMENT '答题时间',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '答题人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '答题记录表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_collection
-- ----------------------------
DROP TABLE IF EXISTS `subject_collection`;
CREATE TABLE `subject_collection`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_QUESTION_ID` bigint NOT NULL COMMENT '题目编号',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收藏者',
  `CREATED_TIME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目收藏表 题目收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_important_points
-- ----------------------------
DROP TABLE IF EXISTS `subject_important_points`;
CREATE TABLE `subject_important_points`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `CONTENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '知识点内容',
  `TITLE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '知识点标题',
  `SRC_TYPE` int NOT NULL DEFAULT 0 COMMENT '来源类型 0-未知，1-系统脚本，2-管理员，3-用户',
  `FlAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '审核过关 0-不过关，1-过关',
  `CREATED_BY` bigint NOT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '知识点 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_important_relation
-- ----------------------------
DROP TABLE IF EXISTS `subject_important_relation`;
CREATE TABLE `subject_important_relation`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_QUESTION_ID` bigint NULL DEFAULT NULL COMMENT '题目表ID',
  `SUBJECT_KNOWLEDGE_POINTS_ID` bigint NULL DEFAULT NULL COMMENT '知识点表ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目要点关联表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_option
-- ----------------------------
DROP TABLE IF EXISTS `subject_option`;
CREATE TABLE `subject_option`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SUBJECT_QUESTION_ID` bigint NULL DEFAULT NULL COMMENT '题目表编号 题目表编号',
  `OPTION_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选项名 选项：ABCDEFG，or答案',
  `OPTION_CONTENT` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选项内容',
  `IS_ANSWER` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否是答案 1-正确选项，0-正常选项',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目选项表 题目选项表、答案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_question
-- ----------------------------
DROP TABLE IF EXISTS `subject_question`;
CREATE TABLE `subject_question`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `QUESTION_TYPE` int NOT NULL COMMENT '题目类型 1-单选；2-多选;3-填空;4-问答',
  `QUESTION_LEVEL` int NOT NULL COMMENT '难易程度 1-100',
  `REMARK` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '备注',
  `QUESTION_URL` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源网站 网址',
  `QUESTION_URL_REMARK` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源描述 如：牛客网',
  `SRC_TYPE` int NOT NULL COMMENT '来源类型 0-默认，1-系统，2-管理员，3-用户，4-脚本',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `QUESTION_CONTENT` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目表 题目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_tag
-- ----------------------------
DROP TABLE IF EXISTS `subject_tag`;
CREATE TABLE `subject_tag`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TAG_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `TAG_DESCRIPTION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  `TAG_URI` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签URI',
  `CREATED_BY` bigint NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目标签表 ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_tag_knowledge_points_id
-- ----------------------------
DROP TABLE IF EXISTS `subject_tag_knowledge_points_id`;
CREATE TABLE `subject_tag_knowledge_points_id`  (
  `ID` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_TAG_ID` bigint NULL DEFAULT NULL COMMENT '标签表主键',
  `SUBJECT_KNOWLEDGE_POINTS_ID` bigint NULL DEFAULT NULL COMMENT '知识点表主键',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签与知识点关联表 ' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
