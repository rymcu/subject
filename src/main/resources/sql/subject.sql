/*
 Navicat Premium Data Transfer

 Source Server         : rymcu_test_subject_online
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 120.26.175.127:3306
 Source Schema         : subject

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 29/06/2021 16:44:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for subject_answer_record
-- ----------------------------
DROP TABLE IF EXISTS `subject_answer_record`;
CREATE TABLE `subject_answer_record`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_QUESTION_ID` bigint(20) NOT NULL COMMENT '题目编号',
  `ANSWER` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '我的答案 如abcd，填空值，or简答题的答案',
  `CREATED_TIME` datetime(0) NOT NULL COMMENT '答题时间',
  `CREATED_BY` bigint(20) NULL DEFAULT NULL COMMENT '答题人',
  `EVERYDAY_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  `ANSWER_FLAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '0-错误,1-正确',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_id`(`SUBJECT_QUESTION_ID`) USING BTREE,
  CONSTRAINT `subject_answer_record_ibfk_1` FOREIGN KEY (`SUBJECT_QUESTION_ID`) REFERENCES `subject_question` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 265 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '答题记录表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_collection
-- ----------------------------
DROP TABLE IF EXISTS `subject_collection`;
CREATE TABLE `subject_collection`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_QUESTION_ID` bigint(20) NOT NULL COMMENT '题目编号',
  `CREATED_BY` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收藏者',
  `CREATED_TIME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_id`(`SUBJECT_QUESTION_ID`) USING BTREE,
  CONSTRAINT `subject_collection_ibfk_1` FOREIGN KEY (`SUBJECT_QUESTION_ID`) REFERENCES `subject_question` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目收藏表 题目收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_knowledge_points
-- ----------------------------
DROP TABLE IF EXISTS `subject_knowledge_points`;
CREATE TABLE `subject_knowledge_points`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `CONTENT` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '知识点内容',
  `TITLE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '知识点标题',
  `SRC_TYPE` int(11) NOT NULL DEFAULT 0 COMMENT '来源类型 0-未知，1-系统脚本，2-管理员，3-用户',
  `FlAG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '审核过关 0-不过关，1-过关',
  `CREATED_BY` bigint(20) NOT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '知识点 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_knowledge_relation
-- ----------------------------
DROP TABLE IF EXISTS `subject_knowledge_relation`;
CREATE TABLE `subject_knowledge_relation`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_QUESTION_ID` bigint(20) NULL DEFAULT NULL COMMENT '题目表ID',
  `SUBJECT_KNOWLEDGE_POINTS_ID` bigint(20) NULL DEFAULT NULL COMMENT '知识点表ID',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_id`(`SUBJECT_QUESTION_ID`) USING BTREE,
  CONSTRAINT `subject_knowledge_relation_ibfk_1` FOREIGN KEY (`SUBJECT_QUESTION_ID`) REFERENCES `subject_question` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '知识点关联表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_option
-- ----------------------------
DROP TABLE IF EXISTS `subject_option`;
CREATE TABLE `subject_option`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SUBJECT_QUESTION_ID` bigint(20) NULL DEFAULT NULL COMMENT '题目表编号 题目表编号',
  `OPTION_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '选项名 选项：ABCDEFG，or答案',
  `OPTION_CONTENT` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选项内容',
  `IS_ANSWER` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否是答案 1-正确选项，0-正常选项',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_id`(`SUBJECT_QUESTION_ID`) USING BTREE,
  CONSTRAINT `subject_option_ibfk_1` FOREIGN KEY (`SUBJECT_QUESTION_ID`) REFERENCES `subject_question` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11525 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目选项表 题目选项表、答案表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_question
-- ----------------------------
DROP TABLE IF EXISTS `subject_question`;
CREATE TABLE `subject_question`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `QUESTION_TYPE` int(11) NOT NULL COMMENT '题目类型 1-单选；2-多选;3-判断;4-填空;5-问答;0-未知',
  `QUESTION_LEVEL` int(11) NOT NULL COMMENT '难易程度 1-100',
  `REMARK` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `QUESTION_URL` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源网站 网址',
  `QUESTION_URL_REMARK` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '试卷网址',
  `SRC_TYPE` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源类型 0-默认，1-系统，2-管理员，3-用户，4-脚本',
  `CREATED_BY` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `QUESTION_CONTENT` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ERROR_FLAG` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有误',
  `SHOW_FLAG` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否展示',
  `ANSWER` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '答案',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3436 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目表 题目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_sign_err
-- ----------------------------
DROP TABLE IF EXISTS `subject_sign_err`;
CREATE TABLE `subject_sign_err`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `sq_id` bigint(20) NOT NULL COMMENT '题目编号',
  `err_content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误描述',
  `err_dec` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '错误简述',
  `fixed` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '0-未修复，1-已修复',
  `fixed_time` timestamp(0) NULL DEFAULT NULL COMMENT '修复时间',
  `fixed_by` bigint(20) NULL DEFAULT NULL COMMENT '修复用户编号',
  `fixed_summary` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修复摘要',
  `created_by` bigint(20) NULL DEFAULT NULL COMMENT '用户编号',
  `created_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_id`(`sq_id`) USING BTREE,
  CONSTRAINT `subject_sign_err_ibfk_1` FOREIGN KEY (`sq_id`) REFERENCES `subject_question` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for subject_tag
-- ----------------------------
DROP TABLE IF EXISTS `subject_tag`;
CREATE TABLE `subject_tag`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TAG_NAME` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名',
  `TAG_DESCRIPTION` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签描述',
  `TAG_URI` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签URI',
  `CREATED_BY` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '题目标签表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for subject_tag_knowledge_points_id
-- ----------------------------
DROP TABLE IF EXISTS `subject_tag_knowledge_points_id`;
CREATE TABLE `subject_tag_knowledge_points_id`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `SUBJECT_TAG_ID` bigint(20) NULL DEFAULT NULL COMMENT '标签表主键',
  `SUBJECT_KNOWLEDGE_POINTS_ID` bigint(20) NULL DEFAULT NULL COMMENT '知识点表主键',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签与知识点关联表 ' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
