/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : ruoyi-openai222

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 08/01/2024 13:42:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 175 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (2, '1', 'user_id', '用户id', 'int(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (3, '1', 'title', '标题', 'text', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (4, '1', 'status', '删除 0显示  1删除', 'smallint(4)', 'Integer', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 4, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (5, '1', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (6, '1', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (7, '1', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (8, '1', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (9, '2', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (10, '2', 'c_id', '聊天记录表id', 'int(11)', 'Long', 'cId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (11, '2', 'user_id', '用户id', 'int(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (12, '2', 'problem', '问题', 'text', 'String', 'problem', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (13, '2', 'answer', '回答', 'text', 'String', 'answer', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (14, '2', 'status', '删除 0显示  1删除', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (15, '2', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (16, '2', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (17, '2', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (18, '2', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (19, '3', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (20, '3', 'type', '类型 （字典表的数据）', 'smallint(4)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (21, '3', 'title', '标题', 'varchar(255)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (22, '3', 'content', '内容 回答', 'longtext', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'summernote', '', 4, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (23, '3', 'sort', '排序', 'smallint(6)', 'Integer', 'sort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (24, '3', 'is_erased', '0:正常数据 1:已擦除数据', 'tinyint(4)', 'Integer', 'isErased', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (25, '3', 'create_time', '添加时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (26, '3', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (27, '3', 'create_by', '创建人', 'varchar(20)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (28, '3', 'update_by', '修改人', 'varchar(20)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-05-23 11:48:42', '', NULL);
INSERT INTO `gen_table_column` VALUES (29, '4', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (30, '4', 'free_time', '每天免费次数', 'int(11)', 'Long', 'freeTime', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (31, '4', 'user_id', '用户id', 'int(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (32, '4', 'recharge_time', '充值时间', 'datetime', 'Date', 'rechargeTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 8, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (33, '4', 'expiration_time', '到期时间', 'datetime', 'Date', 'expirationTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (34, '4', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 24, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (35, '4', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 25, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (36, '4', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 26, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (37, '4', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 27, 'admin', '2023-05-23 11:48:42', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (38, '5', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (39, '5', 'code', '邀请码', 'varchar(255)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (40, '5', 'dept_id', '部门id', 'int(11)', 'Long', 'deptId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (41, '5', 'status', '状态 0有效 1无效', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 4, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (42, '5', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (43, '5', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (44, '5', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (45, '5', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-06-01 11:18:04', '', NULL);
INSERT INTO `gen_table_column` VALUES (46, '6', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (47, '6', 'title', '标题', 'varchar(500)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (48, '6', 'prompts', '内容', 'varchar(2000)', 'String', 'prompts', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (49, '6', 'first_content', '第一句内容', 'varchar(500)', 'String', 'firstContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'summernote', '', 5, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (51, '6', 'status', '删除 0显示  1删除', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 15, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (52, '6', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (53, '6', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (54, '6', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (55, '6', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2023-06-30 15:09:16', NULL, '2023-07-06 14:38:55');
INSERT INTO `gen_table_column` VALUES (56, '7', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (57, '7', 'user_id', 'user_id', 'int(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (58, '7', 'pay_id', '订单id', 'varchar(50)', 'String', 'payId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (59, '7', 'order_price', '订单金额', 'decimal(10,2)', 'BigDecimal', 'orderPrice', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (60, '7', 'pay_price', '支付金额', 'decimal(10,2)', 'BigDecimal', 'payPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (61, '7', 'status', '状态 0:待支付  1:完成   2:过期', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (62, '7', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (63, '7', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (64, '7', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (65, '7', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-07-04 16:44:28', '', NULL);
INSERT INTO `gen_table_column` VALUES (66, '8', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (67, '8', 'name', '名称', 'varchar(255)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (68, '8', 'type', '0: 按天 1:次', 'smallint(2)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (69, '8', 'price', '价格', 'decimal(10,2)', 'BigDecimal', 'price', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (70, '8', 'time', '数值', 'int(11)', 'Long', 'time', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (71, '8', 'status', '删除 0显示  1删除', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (72, '8', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (73, '8', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (74, '8', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (75, '8', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (76, '8', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-07-04 17:54:40', NULL, '2023-07-10 16:21:45');
INSERT INTO `gen_table_column` VALUES (77, '9', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (78, '9', 'user_id', 'userId', 'int(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (79, '9', 'type', '类型 0: 按天  1:次', 'smallint(2)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (80, '9', 'pay_id', '订单id', 'varchar(50)', 'String', 'payId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (81, '9', 'pay_type', '支付方式 ：微信支付为1 支付宝支付为2', 'smallint(2)', 'Integer', 'payType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (82, '9', 'price', '订单金额', 'decimal(10,2)', 'BigDecimal', 'price', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (83, '9', 'reallyPrice', '实际支付金额', 'decimal(10,2)', 'BigDecimal', 'reallyPrice', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (84, '9', 'sign', '签名', 'varchar(255)', 'String', 'sign', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (85, '9', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (86, '9', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (87, '9', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (88, '9', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-07-05 17:37:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (89, '10', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (90, '10', 'name', '模型类型名称', 'varchar(50)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (91, '10', 'sort', '排序', 'int(11)', 'Long', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (92, '10', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (93, '10', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (94, '10', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (95, '10', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-06 13:38:01', '', NULL);
INSERT INTO `gen_table_column` VALUES (96, '6', 't_id', '类型', 'int(11)', 'Long', 'tId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (97, '6', 'input_placeholder', '发送的提示内容', 'varchar(500)', 'String', 'inputPlaceholder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (98, '6', 'icon', '模型图片', 'varchar(255)', 'String', 'icon', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (99, '6', 'model', 'ChatGPT 模型', 'varchar(255)', 'String', 'model', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (100, '6', 'max_tokens', 'max_tokens', 'int(11)', 'Long', 'maxTokens', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (101, '6', 'temperature', 'temperature', 'double(11,2)', 'BigDecimal', 'temperature', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (102, '6', 'top_p', 'top_p', 'double(11,2)', 'BigDecimal', 'topP', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (103, '6', 'presence_penalty', 'presence_penalty', 'double(11,2)', 'BigDecimal', 'presencePenalty', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (104, '6', 'frequency_penalty', 'frequency_penalty', 'double(11,2)', 'BigDecimal', 'frequencyPenalty', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (105, '6', 'stream', 'stream  0:true  1:false', 'tinyint(2)', 'Integer', 'stream', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (106, '6', 'sort', '排序', 'smallint(4)', 'Integer', 'sort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, '', '2023-07-06 14:38:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (107, '4', 'code', '邀请码', 'varchar(50)', 'String', 'code', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, '', '2023-07-08 17:19:27', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (108, '4', 'ip', '网络ip', 'varchar(50)', 'String', 'ip', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, '', '2023-07-08 17:19:27', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (109, '4', 'money_time', '充值后的剩余次数', 'int(11)', 'Long', 'moneyTime', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, '', '2023-07-08 17:19:27', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (110, '4', 'money_time_four', '充值后的剩余次数 ChatGPT4', 'int(11)', 'Long', 'moneyTimeFour', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, '', '2023-07-08 17:19:27', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (111, '4', 'recharge_time_four', '充值时间 ChatGPT4', 'datetime', 'Date', 'rechargeTimeFour', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 11, '', '2023-07-08 17:19:27', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (112, '4', 'expiration_time_four', '到期时间 ChatGPT4', 'datetime', 'Date', 'expirationTimeFour', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, '', '2023-07-08 17:19:27', NULL, '2023-11-28 16:26:53');
INSERT INTO `gen_table_column` VALUES (113, '11', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (114, '11', 'api_key', 'api_key', 'varchar(255)', 'String', 'apiKey', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (115, '11', 'status', '0：启用  1：禁用', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 3, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (116, '11', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (117, '11', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (118, '11', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (119, '11', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (120, '11', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-07-10 14:57:59', NULL, '2023-07-10 14:58:16');
INSERT INTO `gen_table_column` VALUES (121, '8', 'logo', '图片', 'varchar(255)', 'String', 'logo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, '', '2023-07-10 16:21:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (122, '12', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (123, '12', 'user_id', 'userId', 'int(11)', 'Long', 'userId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (124, '12', 'type', '类型 0:注册 1:充值 2:提现 3:兑换', 'smallint(4)', 'Integer', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (125, '12', 'money', '金额', 'decimal(10,2)', 'BigDecimal', 'money', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (126, '12', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 5, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (127, '12', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (128, '12', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (129, '12', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (130, '12', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-08-09 11:11:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (131, '13', 'id', 'id', 'bigint(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (132, '13', 'company_id', '公司id', 'int(11)', 'Long', 'companyId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (133, '13', 'user_id', '用户id', 'bigint(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (134, '13', 'task_id', '任务id', 'varchar(128)', 'String', 'taskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (135, '13', 'prompt', '魔咒', 'varchar(2048)', 'String', 'prompt', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (136, '13', 'task_url', '生成的url', 'varchar(255)', 'String', 'taskUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (137, '13', 'type', '0 - 普通 1 变换', 'int(11)', 'Long', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 7, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (138, '13', 'parent_id', '父亲id', 'varchar(128)', 'String', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (139, '13', 'parent_photo', '父亲照片', 'varchar(256)', 'String', 'parentPhoto', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (140, '13', 'parent_index', '父亲索引', 'int(4)', 'Integer', 'parentIndex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (141, '13', 'state', 'state', 'varchar(2048)', 'String', 'state', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 11, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (142, '13', 'action', 'action', 'varchar(128)', 'String', 'action', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (143, '13', 'status', '任务状态 0 -SUBMITTED 已提交,1-IN_PROGRESS 执行中,2-SUCCESS 完成,-1 -失败', 'int(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 13, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (144, '13', 'finish_time', '任务完成时间', 'datetime', 'Date', 'finishTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 14, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (145, '13', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (146, '13', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (147, '13', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (148, '13', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 18, 'admin', '2023-09-21 10:25:59', NULL, '2023-10-31 14:29:22');
INSERT INTO `gen_table_column` VALUES (149, '14', 'id', 'id', 'int(11)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (150, '14', 'company_id', '公司id', 'int(11)', 'Long', 'companyId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (151, '14', 'user_id', '用户id', 'bigint(11)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (152, '14', 'prompt', '关键词', 'varchar(500)', 'String', 'prompt', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (153, '14', 'model', '模型  1: DALL.E 2  2:DALL.E 3', 'varchar(50)', 'String', 'model', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (154, '14', 'size', '像素  1:256x256  2:512x512  3:1024x1024  4:1792x1024  5:1024x1792', 'varchar(50)', 'String', 'size', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (155, '14', 'n', '绘画数量', 'int(11)', 'Long', 'n', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (156, '14', 'style', '风格 1:vivid(生动的)  2:natural （自然的）', 'varchar(50)', 'String', 'style', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (157, '14', 'url', '返回的url', 'varchar(255)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (158, '14', 'status', '删除 0显示  1删除', 'smallint(4)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 10, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (159, '14', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (160, '14', 'update_time', '修改时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (161, '14', 'create_by', '创建者', 'varchar(50)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (162, '14', 'update_by', '修改者', 'varchar(50)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2023-11-27 11:41:48', '', NULL);
INSERT INTO `gen_table_column` VALUES (163, '4', 'company_id', '公司id', 'int(11)', 'Long', 'companyId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (164, '4', 'mj_free_time', 'mj每天免费次数', 'int(11)', 'Long', 'mjFreeTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (165, '4', 'mj_money_time', 'mj充值后剩余次数', 'int(11)', 'Long', 'mjMoneyTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (166, '4', 'recharge_time_mj', 'mj充值时间', 'datetime', 'Date', 'rechargeTimeMj', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 15, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (167, '4', 'dall_e_time', 'dallE剩余次数', 'int(11)', 'Long', 'dallETime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (168, '4', 'recharge_time_dall_e', 'dallE充值时间', 'datetime', 'Date', 'rechargeTimeDallE', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 17, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (169, '4', 'money', '可提现金额', 'decimal(10,2)', 'BigDecimal', 'money', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (170, '4', 'freeze_money', '冻结金额', 'decimal(10,2)', 'BigDecimal', 'freezeMoney', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (171, '4', 'total_money', '总金额(可提现金额 + 冻结金额)', 'decimal(10,2)', 'BigDecimal', 'totalMoney', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (172, '4', 'buy_count', '订单首次购买次数', 'int(11)', 'Long', 'buyCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (173, '4', 'invite_count', '邀请人数', 'int(11)', 'Long', 'inviteCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, '', '2023-11-28 16:26:53', '', NULL);
INSERT INTO `gen_table_column` VALUES (174, '4', 'read', '公告已读未读 0:未读  1:已读', 'int(4)', 'Integer', 'read', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 23, '', '2023-11-28 16:26:53', '', NULL);

-- ----------------------------
-- Table structure for mj_task_info
-- ----------------------------
DROP TABLE IF EXISTS `mj_task_info`;
CREATE TABLE `mj_task_info`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `task_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务id',
  `prompt` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '魔咒',
  `task_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '生成的url',
  `type` int(11) NOT NULL DEFAULT 0 COMMENT '0 - 普通 1 变换',
  `parent_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '父亲id',
  `parent_photo` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '父亲照片',
  `parent_index` int(4) DEFAULT NULL COMMENT '父亲索引',
  `state` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'state',
  `action` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'action',
  `status` int(4) NOT NULL COMMENT '任务状态 0 -SUBMITTED 已提交,1-IN_PROGRESS 执行中,2-SUCCESS 完成,-1 -失败',
  `finish_time` datetime(0) DEFAULT NULL COMMENT '任务完成时间',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'midjourney 绘画任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mj_task_info
-- ----------------------------
INSERT INTO `mj_task_info` VALUES (1, 1, 1, '1695348406127351', 'a cute cat', 'https://img.byabstudio.com/attachments/1146852997121441885/1154599788407423027/starxmate_bot1_a_cute_cat_553c2186-92a9-42b0-878d-5f28e58b489a.png', 0, '', '', 0, '', '', 2, '2023-09-22 10:52:44', '2023-09-22 10:45:03', '2023-09-22 10:52:44', NULL, NULL);

-- ----------------------------
-- Table structure for openai_api_key
-- ----------------------------
DROP TABLE IF EXISTS `openai_api_key`;
CREATE TABLE `openai_api_key`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `api_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'api_key',
  `status` smallint(4) NOT NULL DEFAULT 0 COMMENT '0：启用  1：禁用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'api_key 列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_api_key
-- ----------------------------
INSERT INTO `openai_api_key` VALUES (1, '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', 0, '3.5的', '2023-06-15 09:55:34', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (2, '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', 0, '3.5的', '2023-06-15 09:55:35', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (3, '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', 0, '3.5的', '2023-07-10 15:30:25', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (4, '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', 0, '3.5的', '2023-07-10 15:30:25', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (5, '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', 0, '3.5的', '2023-07-10 15:30:26', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (6, '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', 0, '3.5的', '2023-07-10 15:30:26', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (7, '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', 0, '3.5的', '2023-07-10 15:30:28', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (8, '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', 0, '3.5的', '2023-07-10 15:30:28', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (9, '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', 0, '3.5的', '2023-07-10 15:30:29', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (10, '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', 0, '3.5的', '2023-07-10 15:30:29', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (11, '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', 0, '3.5的', '2023-07-10 15:30:30', NULL, NULL, NULL);
INSERT INTO `openai_api_key` VALUES (12, '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', 0, '4.0的', '2023-07-10 15:30:30', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_chat
-- ----------------------------
DROP TABLE IF EXISTS `openai_chat`;
CREATE TABLE `openai_chat`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `m_id` int(11) DEFAULT NULL COMMENT '模型id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '标题',
  `status` smallint(4) DEFAULT 0 COMMENT '删除 0显示  1删除',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for openai_chat_detail
-- ----------------------------
DROP TABLE IF EXISTS `openai_chat_detail`;
CREATE TABLE `openai_chat_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `type` smallint(4) NOT NULL DEFAULT 1 COMMENT 'type 1:3.5  2:4.0  3:pdf  4:role',
  `m_id` int(11) DEFAULT NULL COMMENT '模型id',
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '聊天记录表uuid',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '问题 / 回答',
  `content2` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT 'pdf整理后的 问题 / 回答',
  `status` smallint(4) NOT NULL DEFAULT 0 COMMENT '删除 0显示  1删除',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天记录详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for openai_chat_order
-- ----------------------------
DROP TABLE IF EXISTS `openai_chat_order`;
CREATE TABLE `openai_chat_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `user_id` int(11) NOT NULL COMMENT 'user_id',
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '云端订单号，可用于查询订单是否支付成功',
  `plan_id` int(11) NOT NULL COMMENT '计划id',
  `pay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `type` smallint(2) NOT NULL DEFAULT 1 COMMENT '支付类型 1:微信  2:支付宝',
  `order_price` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `pay_price` decimal(10, 2) DEFAULT NULL COMMENT '支付金额',
  `status` smallint(4) NOT NULL DEFAULT 0 COMMENT '状态 0:待支付  1:完成   2:过期 3:关闭',
  `pay_time` datetime(0) DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_pay_id`(`pay_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_chat_order
-- ----------------------------
INSERT INTO `openai_chat_order` VALUES (1, 1, 1, '202307101701121658', 2, 'a7f02e13-975b-46fa-8ca5-e4cd6c16b3a4', 1, 0.01, NULL, 3, NULL, '2023-07-10 17:01:12', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_chat_plan
-- ----------------------------
DROP TABLE IF EXISTS `openai_chat_plan`;
CREATE TABLE `openai_chat_plan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `model` smallint(2) NOT NULL DEFAULT 0 COMMENT '0: 3.5模型  1:4.0   2:mj  3:xxx',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片',
  `type` smallint(2) NOT NULL DEFAULT 0 COMMENT '0: 按天 1:次',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '价格',
  `time` int(11) NOT NULL DEFAULT 0 COMMENT '数值',
  `experienced` smallint(4) DEFAULT 1 COMMENT '0:是体验  1:不是体验',
  `status` smallint(4) NOT NULL DEFAULT 0 COMMENT '删除 0显示  1删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_chat_plan
-- ----------------------------
INSERT INTO `openai_chat_plan` VALUES (1, 1, '免费计划', 0, '/profile/xxx.png', 0, 0.00, 5, 1, 1, '免费计划', 0, '2023-07-04 17:40:52', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_dalle
-- ----------------------------
DROP TABLE IF EXISTS `openai_dalle`;
CREATE TABLE `openai_dalle`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `prompt` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关键词',
  `model` smallint(4) DEFAULT 1 COMMENT '模型  1: DALL.E 2  2:DALL.E 3',
  `size` smallint(4) DEFAULT 1 COMMENT '像素  1:256x256  2:512x512  3:1024x1024  4:1792x1024  5:1024x1792',
  `n` int(11) DEFAULT NULL COMMENT '绘画数量',
  `style` smallint(4) DEFAULT NULL COMMENT '风格 1:vivid(生动的)  2:natural （自然的）',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '返回的url',
  `status` smallint(4) NOT NULL DEFAULT 0 COMMENT '删除 0显示  1删除',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 164 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'DALL.E' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_dalle
-- ----------------------------
INSERT INTO `openai_dalle` VALUES (1, 1, 1, '一只猫', 1, 1, 2, 1, 'https://xxx.xxx.com/profile/dalle3/202312011400157DNJH.jpg', 1, '2023-11-28 15:31:24', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_dept_code
-- ----------------------------
DROP TABLE IF EXISTS `openai_dept_code`;
CREATE TABLE `openai_dept_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邀请码',
  `user_id` bigint(20) NOT NULL COMMENT '用户userId',
  `status` smallint(4) NOT NULL DEFAULT 0 COMMENT '状态 0有效 1无效',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户邀请码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_dept_code
-- ----------------------------
INSERT INTO `openai_dept_code` VALUES (1, 1, '202307011311', 1, 0, '2023-05-30 17:45:09', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_faq
-- ----------------------------
DROP TABLE IF EXISTS `openai_faq`;
CREATE TABLE `openai_faq`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` smallint(4) NOT NULL COMMENT '类型 （字典表的数据）',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容 回答',
  `sort` smallint(6) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_erased` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0:正常数据 1:已擦除数据',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'FAQ 常用问题解答' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_faq
-- ----------------------------
INSERT INTO `openai_faq` VALUES (1, 0, '支持哪些平台使用？', '<p>本站电脑、手机、平板通用\r\n\r\n无需科学上网，无需梯子，使用方便快捷。</p>', 2, 0, '2023-03-14 18:02:18', '2023-03-24 14:50:12', NULL, NULL);
INSERT INTO `openai_faq` VALUES (2, 0, '如何在聊天室中和 AI 交流？', '<p>在聊天室中，点击聊天框左边机器人按钮即可与 AI 交流</p>', 0, 0, '2023-03-14 18:02:39', '2023-03-15 09:47:33', NULL, NULL);
INSERT INTO `openai_faq` VALUES (3, 0, '为何我感觉 AI 的回答不完整？', '<p>每次回答有字数限制，回复「继续」即可继续回答</p>', 1, 0, '2023-03-15 09:58:27', '2023-03-24 14:49:56', NULL, NULL);
INSERT INTO `openai_faq` VALUES (4, 0, 'AI 使用的是什么版本？', '<p>本站基于 OpenAI 官方 gpt-3.5-turbo API 搭建</p>', 4, 0, '2023-03-15 15:17:26', '2023-03-24 15:43:52', NULL, NULL);
INSERT INTO `openai_faq` VALUES (5, 0, '机器人能记忆上下文吗', '<p>点击联系我们，我们会进行上架该商品</p>', 1, 0, '2023-03-15 15:19:35', '2023-03-15 15:19:35', NULL, NULL);
INSERT INTO `openai_faq` VALUES (6, 0, '找不到订单号', '<p>机器人可记忆你和他之间的最近 20 条对话\r\n\r\n在聊天室中，上下文记忆保存时间 1 天，个人会话中永久保存</p>', 1, 0, '2023-03-15 15:21:03', '2023-03-15 15:21:02', NULL, NULL);

-- ----------------------------
-- Table structure for openai_img_detail
-- ----------------------------
DROP TABLE IF EXISTS `openai_img_detail`;
CREATE TABLE `openai_img_detail`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uuid` int(11) NOT NULL COMMENT 'uuid',
  `user_id` int(11) NOT NULL COMMENT 'userId',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容/本地url路径',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图片记录详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for openai_order_log
-- ----------------------------
DROP TABLE IF EXISTS `openai_order_log`;
CREATE TABLE `openai_order_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `user_id` int(11) NOT NULL COMMENT 'userId',
  `type` smallint(2) NOT NULL DEFAULT 0 COMMENT '类型 0: 按天  1:次',
  `pay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `pay_type` smallint(2) NOT NULL COMMENT '支付方式 ：微信支付为1 支付宝支付为2',
  `price` decimal(10, 2) NOT NULL COMMENT '订单金额',
  `reallyPrice` decimal(10, 2) NOT NULL COMMENT '实际支付金额',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '签名',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付成功回调' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_order_log
-- ----------------------------
INSERT INTO `openai_order_log` VALUES (1, 1, 1, 1, 'ba247207-d5c7-4e25-8c0c-73e6d206b5d7', 1, 0.02, 0.02, '27c8315516db24c2b5863fb81ca066d5', '2023-07-10 17:17:18', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_prompts
-- ----------------------------
DROP TABLE IF EXISTS `openai_prompts`;
CREATE TABLE `openai_prompts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `t_id` int(11) DEFAULT NULL COMMENT '类型',
  `title` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `prompts` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '内容',
  `first_content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第一句内容',
  `input_placeholder` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发送的提示内容',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模型图片',
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'ChatGPT 模型',
  `max_tokens` int(11) DEFAULT 10000 COMMENT 'max_tokens',
  `temperature` double(11, 2) DEFAULT 1.00 COMMENT 'temperature',
  `top_p` double(11, 2) DEFAULT 1.00 COMMENT 'top_p',
  `presence_penalty` double(11, 2) DEFAULT 0.00 COMMENT 'presence_penalty',
  `frequency_penalty` double(11, 2) DEFAULT 0.00 COMMENT 'frequency_penalty',
  `stream` tinyint(2) DEFAULT 0 COMMENT 'stream  0:true  1:false',
  `status` smallint(4) DEFAULT 0 COMMENT '删除 0显示  1删除',
  `sort` smallint(4) DEFAULT 1 COMMENT '排序',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'prompts' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_prompts
-- ----------------------------
INSERT INTO `openai_prompts` VALUES (1, 1, 1, 'AI-Chat 3.5', 'ChatGPT3.5', '我是 AI助手 3.5,有什么问题都可以向我提问哦 ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719113440A001.png', 'gpt-3.5-turbo-16k', 10000, 1.00, 1.00, 0.00, 0.00, 0, 0, 1, '2023-06-30 14:03:34', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (2, 1, 2, 'AI-Chat 4.0', 'ChatGPT4.0', '我是 我是 AI助手 4.0,如何验证是否ChatGPT4的可以问：</br>\r\n<p>1、鲁迅和周树人的关系？</p>\r\n<b>ChatGPT3.5回答</b>：鲁迅（1881年-1936年）和周树人（1897年-1972年）是中国现代文学史上两位重要的作家 ... 一本正经的胡说八道。</br>\r\n<b>ChatGPT4.0回答</b>：<span style=\"color:red\">鲁迅和周树人是同一人，鲁迅是周树人的笔名。</span></br>\r\n<p>2、父母结婚为什么不邀请我参加？</p>\r\n<b>ChatGPT3.5回答</b>：这个问题可能有很多原因。父母结婚的决定是他们自己的，可能是因为他们希望保持婚礼的规模较小，或者是因为其他一些个人原因。不被邀请参加婚礼可能会让人感到伤心或困惑，但是我们应该尊重父母的决定，并且可以与他们沟通，了解他们的想法和原因。</br>\r\n<b>ChatGPT4.0回答</b>：<span style=\"color:red\">这可能是因为你在他们结婚时还没有出生。父母的婚礼通常在他们决定成为家庭并有孩子之前举行。</span></br> ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719113528A002.png', 'gpt-4-1106-preview', 50000, 1.00, 1.00, 0.00, 0.00, 0, 1, 2, '2023-06-30 15:50:46', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (3, 1, 4, '小红书文案创作专家', '小红书的风格是：很吸引眼球的标题，每个段落都加emoji，最后加一些tag。请用小红书笔记风格，根据接下来我输入的主题写一篇笔记，多用点emoji，请用活泼可爱的语气。', '您好，我是小红书文案创作专家，帮助您撰写吸引人的小红书文案。注：由于我的单次回答有字数限制，如果您感觉我没说完，请回复「继续」 ', '请输入主题', '/profile/avatar/2023/07/19/blob_20230706142800A001.png', 'gpt-3.5-turbo', 500, 1.00, 1.00, 0.00, 0.00, 0, 0, 3, '2023-06-30 15:51:05', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (12, 1, 7, '程序员助手', '你是一名全栈开发工程师，精通各种编程语言，任何编程问题你都能回答', '您好，我是一名全栈工程师，通晓多种编程语言和技术，具备全面的软件工程能力和项目经验。\r\n      我擅长需求分析、架构设计、编码和测试等各个阶段的工作，能够帮助用户实现功能需求，并提供性能、安全、可扩展性等方面的优化建议。\r\n      您有任何软件开发方面的需求都可以向我提问，我将非常乐意为您提供帮助。 ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719114832A002.png', 'gpt-3.5-turbo', 500, 1.00, 1.00, 0.00, 0.00, 0, 0, 12, '2023-07-01 12:57:45', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (30, 1, 30, 'AI文件阅读', 'AI文件阅读', '①我是AI文件阅读，您只需要上传一份PDF文档给我，我能帮您生成总结并针对该文档进行回复指定内容，为您节约宝贵的时间</br>②我需要消耗4.0的次数哦</br>③上传PDF大小请控制在1MB以内,后续慢慢增大</br>④PDF里面的图片我还识别不了,请等待开发</br> ', '请输入内容', '/profile/avatar/2023/09/01/blob_20230901152644A001.png', 'gpt-4-1106-preview', 10000, 1.00, 1.00, 0.00, 0.00, 0, 0, 1, '2023-08-14 14:20:43', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (32, 2, 31, 'ChatGPT3.5', 'ChatGPT3.5', '我是 ChatGPT3.5,有什么问题都可以向我提问哦', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719113440A001.png', 'gpt-3.5-turbo-16k', 10000, 1.00, 1.00, 0.00, 0.00, 0, 0, 1, '2023-06-30 14:03:34', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (33, 2, 32, 'ChatGPT4.0', 'ChatGPT4.0', '我是 ChatGPT4.0,如何验证是否ChatGPT4的可以问：</br>\r\n<p>1、鲁迅和周树人的关系？</p>\r\n<b>ChatGPT3.5回答</b>：鲁迅（1881年-1936年）和周树人（1897年-1972年）是中国现代文学史上两位重要的作家、思想家和教育家，他们之间有一定的关系。鲁迅是中国现代文学的奠基人之一，他以其对社会现实的犀利批判和对人性的深刻触动而广为人知。而周树人则是中国现代文学和教育改革的先驱者之一，他致力于推动新文化运动和科学教育的发展。在早期，鲁迅曾是周树人的老师和引导者。1921年，周树人在北京大学任教，当时鲁迅也在北京大学担任教职。鲁迅对周树人的影响深远，他的思想和文学作品对周树人产生了重要的启发和影响。鲁迅的文学思想和对社会的批判精神激励了周树人加入新文化运动，并参与到对旧传统观念和制度的改革中。周树人倡导的科学教育和思想解放等理念也与鲁迅的文化观点相契合。虽然鲁迅和周树人在不同的时期具有不同的活动和影响范围，但他们共同关注社会现实、倡导新文化运动，以及对中国现代文学和教育产生了重要影响，因此两人在中国现代文化思潮的发展中有一定的联系。</br>\r\n<b>ChatGPT4.0回答</b>：<span style=\"color:red\">鲁迅和周树人是同一人，鲁迅是周树人的笔名。</span></br>\r\n<p>2、父母结婚为什么不邀请我参加？</p>\r\n<b>ChatGPT3.5回答</b>：这个问题可能有很多原因。父母结婚的决定是他们自己的，可能是因为他们希望保持婚礼的规模较小，或者是因为其他一些个人原因。不被邀请参加婚礼可能会让人感到伤心或困惑，但是我们应该尊重父母的决定，并且可以与他们沟通，了解他们的想法和原因。</br>\r\n<b>ChatGPT4.0回答</b>：<span style=\"color:red\">这可能是因为你在他们结婚时还没有出生。父母的婚礼通常在他们决定成为家庭并有孩子之前举行。</span></br>', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719113528A002.png', 'gpt-4', 800, 1.00, 1.00, 0.00, 0.00, 0, 1, 2, '2023-06-30 15:50:46', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (34, 2, 34, '小红书文案创作专家', '小红书的风格是：很吸引眼球的标题，每个段落都加emoji，最后加一些tag。请用小红书笔记风格，根据接下来我输入的主题写一篇笔记，多用点emoji，请用活泼可爱的语气。', '您好，我是小红书文案创作专家，帮助您撰写吸引人的小红书文案。注：由于我的单次回答有字数限制，如果您感觉我没说完，请回复「继续」', '请输入主题', '/profile/avatar/2023/07/19/blob_20230706142800A001.png', 'gpt-3.5-turbo', 500, 1.00, 1.00, 0.00, 0.00, 0, 0, 3, '2023-06-30 15:51:05', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (42, 2, 40, '汽车专家', '我希望你充当汽车专家，以提供关于汽车相关问题的专业建议和知识。作为汽车专家，你需要对汽车的各个方面有深入的了解，包括技术、性能、维护、保养和市场动态等。你可以提供关于购车指南、车型比较、汽车维修与保养、燃油经济性、驾驶技巧和汽车市场趋势等方面的专业建议。', '您好，我是汽车专家，任何关于汽车的问题都可以问我。我可以提供关于购车指南、车型比较、汽车维修与保养、燃油经济性、驾驶技巧和汽车市场趋势等方面的专业建议。此外，我还可以给您分享汽车行业的最新技术发展、环保趋势和未来发展方向等知识。', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719114832A002.png', 'gpt-3.5-turbo', 500, 1.00, 1.00, 0.00, 0.00, 0, 0, 11, '2023-07-01 11:02:37', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (43, 2, 37, '程序员助手', '你是一名全栈开发工程师，精通各种编程语言，任何编程问题你都能回答', '您好，我是一名全栈工程师，通晓多种编程语言和技术，具备全面的软件工程能力和项目经验。\r\n      我擅长需求分析、架构设计、编码和测试等各个阶段的工作，能够帮助用户实现功能需求，并提供性能、安全、可扩展性等方面的优化建议。\r\n      您有任何软件开发方面的需求都可以向我提问，我将非常乐意为您提供帮助。', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719114832A002.png', 'gpt-3.5-turbo', 500, 1.00, 1.00, 0.00, 0.00, 0, 0, 12, '2023-07-01 12:57:45', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (59, 2, 42, 'AI文件阅读', 'AI文件阅读', '①我是AI文件阅读，您只需要上传一份PDF文档给我，我能帮您生成总结并针对该文档进行回复指定内容，为您节约宝贵的时间</br>②我需要消耗4.0的次数哦</br>③上传PDF大小请控制在1MB以内,后续慢慢增大</br>④PDF里面的图片我还识别不了,请等待开发</br>', '请输入内容', '/profile/avatar/2023/09/01/blob_20230901152644A001.png', 'gpt-4-1106-preview', 10000, 1.00, 1.00, 0.00, 0.00, 0, 0, 1, '2023-08-14 14:20:43', NULL, 'admin', NULL);
INSERT INTO `openai_prompts` VALUES (62, 1, 43, 'AI识图', 'AI识图', '我是ChatGPT4.0，能够描述您上传的任何图片内容，不信就试试呗 ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719115009A004.png', 'gpt-4-all', 10000, 1.00, 1.00, 0.00, 0.00, 0, 0, 1, '2023-12-04 14:48:56', NULL, NULL, NULL);
INSERT INTO `openai_prompts` VALUES (63, 1, 44, 'AI联网', 'AI联网', '我已经打通联网功能，可以实时获取现在最新数据。 </br>例： 1、今天是多少号。</br> 2、今天北京市朝阳区天气怎么样？ </br>3、今天最新新闻有哪些？......</br> ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719115009A005.png', 'gpt-4-all', 10000, 1.00, 1.00, 0.00, 0.00, 0, 1, 1, '2023-12-04 14:48:56', NULL, NULL, NULL);
INSERT INTO `openai_prompts` VALUES (64, 2, 43, 'AI识图', 'AI识图', '我是ChatGPT4.0，能够描述您上传的任何图片内容，不信就试试呗 ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719115009A004.png', 'gpt-4-all', 10000, 1.00, 1.00, 0.00, 0.00, 0, 0, 1, '2023-12-04 14:48:56', NULL, NULL, NULL);
INSERT INTO `openai_prompts` VALUES (65, 2, 44, 'AI联网', 'AI联网', '我已经打通联网功能，可以实时获取现在最新数据。 </br>例： 1、今天是多少号。</br> 2、今天北京市朝阳区天气怎么样？ </br>3、今天最新新闻有哪些？......</br> ', '请输入内容', '/profile/avatar/2023/07/19/blob_20230719115009A005.png', 'gpt-4-all', 10000, 1.00, 1.00, 0.00, 0.00, 0, 1, 1, '2023-12-04 14:48:56', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for openai_prompts_type
-- ----------------------------
DROP TABLE IF EXISTS `openai_prompts_type`;
CREATE TABLE `openai_prompts_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` smallint(4) NOT NULL DEFAULT 1 COMMENT 'type 1:3.5  2:4.0  3:pdf  4:role',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型类型名称',
  `sort` int(11) DEFAULT 1 COMMENT '排序',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_prompts_type
-- ----------------------------
INSERT INTO `openai_prompts_type` VALUES (1, 1, 1, 'AI-Chat 3.5（我需要用3.5）', 999, '2023-07-06 12:40:34', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (2, 2, 1, 'ChatGPT4.0（<span style=\"color:red\">我需要用4.0</span>）', 998, '2023-07-24 17:22:39', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (4, 4, 1, '文案（我需要用3.5）', 9, '2023-07-06 12:40:47', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (7, 4, 1, '学习（我需要用3.5）', 4, '2023-07-17 15:39:38', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (30, 3, 1, 'AI文件阅读（<span style=\"color:red\">我需要用4.0</span>）', 997, '2023-08-14 14:21:09', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (31, 1, 2, 'ChatGPT3.5（我需要用3.5）2', 999, '2023-07-06 12:40:34', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (32, 2, 2, 'ChatGPT4.0（<span style=\"color:red\">我需要用4.0</span>）', 998, '2023-07-24 17:22:39', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (34, 4, 2, '文案（我需要用3.5）2', 9, '2023-07-06 12:40:47', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (37, 4, 2, '学习（我需要用3.5）2', 4, '2023-07-17 15:39:38', NULL, 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (42, 3, 2, 'AI文件阅读（我需要用4.0）2', 997, '2023-08-14 14:21:09', '2023-12-26 15:22:21', 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (43, 5, 1, 'AI识图（我需要用4.0）', 996, '2023-12-04 14:47:57', '2023-12-26 15:20:47', 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (44, 6, 1, 'AI联网（我需要用4.0）', 995, '2023-12-04 20:52:19', '2023-12-26 15:20:30', NULL, NULL);
INSERT INTO `openai_prompts_type` VALUES (45, 5, 2, 'AI识图（我需要用4.0）', 996, '2023-12-04 14:47:57', '2023-12-26 15:20:47', 'admin', NULL);
INSERT INTO `openai_prompts_type` VALUES (46, 6, 2, 'AI联网（我需要用4.0）', 995, '2023-12-04 20:52:19', '2023-12-26 15:20:30', NULL, NULL);

-- ----------------------------
-- Table structure for openai_user_account_log
-- ----------------------------
DROP TABLE IF EXISTS `openai_user_account_log`;
CREATE TABLE `openai_user_account_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `user_id` int(11) DEFAULT NULL COMMENT 'userId',
  `type` smallint(4) DEFAULT 0 COMMENT '类型 0:注册 1:充值 2:提现 3:兑换',
  `money` decimal(10, 2) DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账户日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_user_account_log
-- ----------------------------
INSERT INTO `openai_user_account_log` VALUES (1, 1, 1, 0, 0.01, '用户 【2****2】 注册了,佣金是:0.01', '2023-08-09 11:03:04', NULL, NULL, NULL);
INSERT INTO `openai_user_account_log` VALUES (2, 1, 1, 1, 3.50, '用户 【3****3】 充值了,充值金额是:14.99 本次佣金是:5.2465', '2023-08-09 11:03:32', NULL, NULL, NULL);
INSERT INTO `openai_user_account_log` VALUES (3, 1, 1, 2, -0.50, '用户 【2****2】 提现了0.5元', '2023-08-09 11:03:59', NULL, '1', NULL);
INSERT INTO `openai_user_account_log` VALUES (4, 1, 1, 3, -0.50, '用户 【2****2】 提现了0.5元', '2023-08-09 11:04:12', NULL, '1', NULL);

-- ----------------------------
-- Table structure for openai_user_info
-- ----------------------------
DROP TABLE IF EXISTS `openai_user_info`;
CREATE TABLE `openai_user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邀请码',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '网络ip',
  `free_time` int(11) NOT NULL DEFAULT 5 COMMENT '每天免费次数',
  `money_time` int(11) NOT NULL DEFAULT 0 COMMENT '充值后的剩余次数',
  `recharge_time` datetime(0) DEFAULT NULL COMMENT '充值时间',
  `expiration_time` datetime(0) DEFAULT NULL COMMENT '到期时间',
  `money_time_four` int(11) NOT NULL DEFAULT 0 COMMENT '充值后的剩余次数 ChatGPT4',
  `recharge_time_four` datetime(0) DEFAULT NULL COMMENT '充值时间 ChatGPT4',
  `expiration_time_four` datetime(0) DEFAULT NULL COMMENT '到期时间 ChatGPT4',
  `mj_free_time` int(11) DEFAULT 0 COMMENT 'mj每天免费次数',
  `mj_money_time` int(11) DEFAULT 0 COMMENT 'mj充值后剩余次数',
  `recharge_time_mj` datetime(0) DEFAULT NULL COMMENT 'mj充值时间',
  `dall_e_time` int(11) DEFAULT 0 COMMENT 'dallE剩余次数',
  `recharge_time_dall_e` datetime(0) DEFAULT NULL COMMENT 'dallE充值时间',
  `money` decimal(10, 2) DEFAULT 0.00 COMMENT '可提现金额',
  `freeze_money` decimal(10, 2) DEFAULT 0.00 COMMENT '冻结金额',
  `total_money` decimal(10, 2) DEFAULT 0.00 COMMENT '总金额(可提现金额 + 冻结金额)',
  `buy_count` int(11) DEFAULT 0 COMMENT '订单首次购买次数',
  `invite_count` int(11) DEFAULT 0 COMMENT '邀请人数',
  `read` int(4) NOT NULL DEFAULT 0 COMMENT '公告已读未读 0:未读  1:已读',
  `create_time` datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 255 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of openai_user_info
-- ----------------------------
INSERT INTO `openai_user_info` VALUES (1, 1, 1, '202305301745', '127.0.0.11', 5, 0, '2023-12-26 10:51:38', '2023-12-29 09:58:01', 9, '2023-05-24 16:53:35', '2023-05-24 16:53:35', 2, 0, '2023-12-01 13:58:46', 3, NULL, 8.81, 0.00, 8.81, 3, 7, 1, '2023-05-24 16:53:35', '2023-12-27 11:20:50', '1', NULL);
INSERT INTO `openai_user_info` VALUES (2, 1, 2, '202305301743', '127.0.0.12', 5, 0, '2023-05-24 16:53:40', '2023-05-24 16:53:40', 105, '2023-05-24 16:53:40', '2023-05-24 16:53:40', 2, 0, '2023-12-01 13:58:46', 3, NULL, 0.00, 0.00, 0.00, 0, 0, 0, '2023-05-24 16:53:40', '2023-11-10 21:52:24', '1', NULL);

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Blob类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Cron类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '已触发的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '暂停的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '调度器状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '简单触发器的信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13, 4) DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13, 4) DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '同步机制的行锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(13) DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(13) DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `end_time` bigint(13) DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(2) DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '触发器详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数名称',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 1, 'sys.index.skinName', 'skin-green', 'Y', 'admin', '2023-05-22 17:08:29', 'admin', '2023-05-31 11:15:04', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 1, 'sys.user.initPassword', '123456', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 1, 'sys.index.sideTheme', 'skin-blue theme-light', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 1, 'sys.account.registerUser', 'true', 'Y', 'admin', '2023-05-22 17:08:29', 'admin', '2023-05-31 11:17:10', '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '用户管理-密码字符范围', 1, 'sys.account.chrtype', '0', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）');
INSERT INTO `sys_config` VALUES (6, '用户管理-初始密码修改策略', 1, 'sys.account.initPasswordModify', '0', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (7, '用户管理-账号密码更新周期', 1, 'sys.account.passwordValidateDays', '0', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (8, '主框架页-菜单导航显示风格', 1, 'sys.index.menuStyle', 'default', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）');
INSERT INTO `sys_config` VALUES (9, '主框架页-是否开启页脚', 1, 'sys.index.footer', 'true', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '是否开启底部页脚显示（true显示，false隐藏）');
INSERT INTO `sys_config` VALUES (10, '主框架页-是否开启页签', 1, 'sys.index.tagsView', 'true', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '是否开启菜单多页签显示（true显示，false隐藏）');
INSERT INTO `sys_config` VALUES (11, '用户登录-黑名单列表', 1, 'sys.login.blackIPList', '', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
INSERT INTO `sys_config` VALUES (12, '每日免费次数', 1, 'free_time', '5', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '账号注册的时候，免费次数的默认值，定时任务每天更新');
INSERT INTO `sys_config` VALUES (13, '进群默认免费', 1, 'money_time', '25', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '注册账号默认送的次数');
INSERT INTO `sys_config` VALUES (14, '支付回调url', 1, 'vmq_url', 'http://xx.xx.cc', 'N', 'admin', '2023-05-22 17:08:29', '', NULL, '支付回调url');
INSERT INTO `sys_config` VALUES (15, '支付秘钥', 1, 'vmq_secret_key', '5d087514d393d5498eaf03998da979ss', 'N', 'admin', '2023-05-22 17:08:29', '', NULL, '支付秘钥');
INSERT INTO `sys_config` VALUES (16, 'ChatGPT4.0 ', 1, 'money_time_four', '2', 'N', 'admin', '2023-05-22 17:08:29', '', NULL, 'ChatGPT4.0');
INSERT INTO `sys_config` VALUES (17, '分享邀请用户注册成功返佣金开关', 1, 'register_invite_money_status', '0', 'N', '', NULL, '', NULL, '分享邀请用户注册成功返佣金开关  0:开启  1:关闭');
INSERT INTO `sys_config` VALUES (18, '分享邀请用户注册成功返佣金', 1, 'register_invite_money', '0.01', 'N', '', NULL, '', NULL, '分享邀请用户注册成功返佣金');
INSERT INTO `sys_config` VALUES (19, '佣金满多少可提现', 1, 'withdrawal_money', '20', 'N', '', NULL, '', NULL, '佣金满多少可提现');
INSERT INTO `sys_config` VALUES (20, '注册限制ip功能开关', 1, 'register_ip_status', '0', 'N', '', NULL, '', NULL, '注册限制ip功能开关  0:开启  1:关闭');
INSERT INTO `sys_config` VALUES (21, '分享邀请用户首次充值成功返佣金 35%', 1, 'register_invite_pay_money', '0.35', 'N', '', NULL, '', NULL, '分享邀请用户首次充值成功返佣金');
INSERT INTO `sys_config` VALUES (22, '项目url地址', 1, 'local_url', 'https://localhost:1234/login', 'N', '', NULL, '', NULL, '项目url地址');
INSERT INTO `sys_config` VALUES (23, 'chatgpt4Url', 1, 'chatgpt4_url', 'https://api.openai.com/', 'N', '', NULL, '', NULL, 'chatgpt4Url ');
INSERT INTO `sys_config` VALUES (24, 'chatgpt4SecretKey', 1, 'chatgpt4_secret_key', 'sk-XGBludyG5COsJEitEc4cB984Fa0c42E1B838BeC15bC3CbDb', 'N', '', NULL, '', NULL, 'chatgpt4SecretKey');
INSERT INTO `sys_config` VALUES (25, 'chatgpt35_url', 1, 'chatgpt35_url', 'https://api.openai.com/', 'N', '', NULL, '', NULL, NULL);
INSERT INTO `sys_config` VALUES (26, 'chatgpt35_secret_key', 1, 'chatgpt35_secret_key', 'sk-XGBludyG5COsJEitEc4cB984Fa0c42E1B838BeC15bC3CbDb', 'N', '', NULL, '', NULL, NULL);
INSERT INTO `sys_config` VALUES (27, 'PDF的上传的大小限制', 1, 'max_file_size', '2', 'N', '', NULL, '', NULL, 'PDF的上传的大小限制');
INSERT INTO `sys_config` VALUES (28, '邀请好友注册就送推广员免费次数', 1, 'register_invite_moneyTime', '5', 'N', '', NULL, '', NULL, NULL);
INSERT INTO `sys_config` VALUES (46, 'MidjourneyUrl', 1, 'midjourney_url', 'https://api.openai.com/', 'N', '', NULL, '', NULL, 'MidjourneyUrl');
INSERT INTO `sys_config` VALUES (47, 'MidjourneyKey', 1, 'midjourney_key', 'sk-6jLYjiuVY250vQK4104b4a78Be3e49Db88E89a01759a1d8b', 'N', '', NULL, '', NULL, 'MidjourneyKey');
INSERT INTO `sys_config` VALUES (48, '用户充值金额体验开关', 1, 'experienced', '0', 'N', '', NULL, '', NULL, '用户充值金额体验开关 0:开启 1:关闭');
INSERT INTO `sys_config` VALUES (49, 'chatgptDalleUrl', 1, 'chatgptDalleUrl', 'https://api.openai.com/', 'N', '', NULL, '', NULL, 'chatgptDalleUrl');
INSERT INTO `sys_config` VALUES (50, 'chatgptDalleSecretKey', 1, 'chatgptDalleSecretKey', 'sk-6jLYjiuVY250vQK4104b4a78Be3e49Db88E89a01759a1d8b', 'N', '', NULL, '', NULL, 'chatgptDalleSecretKey');
INSERT INTO `sys_config` VALUES (53, 'mjFreeTime', 1, 'mj_free_time', '2', 'N', '', NULL, '', NULL, 'mjFreeTime');
INSERT INTO `sys_config` VALUES (54, 'dallETime', 1, 'dall_e_time', '2', 'N', '', NULL, '', NULL, 'dallETime');
INSERT INTO `sys_config` VALUES (56, '每日免费次数', 2, 'free_time', '5', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '账号注册的时候，免费次数的默认值，定时任务每天更新');
INSERT INTO `sys_config` VALUES (57, '进群默认免费', 2, 'money_time', '25', 'Y', 'admin', '2023-05-22 17:08:29', '', NULL, '注册账号默认送的次数');
INSERT INTO `sys_config` VALUES (58, '支付回调url', 2, 'vmq_url', 'http://xx.xx.cc', 'N', 'admin', '2023-05-22 17:08:29', '', NULL, '支付回调url');
INSERT INTO `sys_config` VALUES (59, '支付秘钥', 2, 'vmq_secret_key', '5d087514d393d5498eaf03998da979ss', 'N', 'admin', '2023-05-22 17:08:29', '', NULL, '支付秘钥');
INSERT INTO `sys_config` VALUES (60, 'ChatGPT4.0', 2, 'money_time_four', '2', 'N', 'admin', '2023-05-22 17:08:29', '', NULL, 'ChatGPT4.0 ');
INSERT INTO `sys_config` VALUES (61, '分享邀请用户注册成功返佣金开关', 2, 'register_invite_money_status', '0', 'N', '', NULL, '', NULL, '分享邀请用户注册成功返佣金开关  0:开启  1:关闭');
INSERT INTO `sys_config` VALUES (62, '分享邀请用户注册成功返佣金', 2, 'register_invite_money', '0.01', 'N', '', NULL, '', NULL, '分享邀请用户注册成功返佣金');
INSERT INTO `sys_config` VALUES (63, '佣金满多少可提现', 2, 'withdrawal_money', '20', 'N', '', NULL, '', NULL, '佣金满多少可提现');
INSERT INTO `sys_config` VALUES (64, '注册限制ip功能开关', 2, 'register_ip_status', '0', 'N', '', NULL, '', NULL, '注册限制ip功能开关  0:开启  1:关闭');
INSERT INTO `sys_config` VALUES (65, '分享邀请用户首次充值成功返佣金 35%', 2, 'register_invite_pay_money', '0.35', 'N', '', NULL, '', NULL, '分享邀请用户首次充值成功返佣金');
INSERT INTO `sys_config` VALUES (66, '项目url地址', 2, 'local_url', 'https://localhost:1234/login', 'N', '', NULL, '', NULL, '项目url地址');
INSERT INTO `sys_config` VALUES (67, 'chatgpt4Url', 2, 'chatgpt4_url', 'https://api.openai.com/', 'N', '', NULL, '', NULL, 'chatgpt4Url ');
INSERT INTO `sys_config` VALUES (68, 'chatgpt4SecretKey', 2, 'chatgpt4_secret_key', 'sk-XGBludyG5COsJEitEc4cB984Fa0c42E1B838BeC15bC3CbDb', 'N', '', NULL, '', NULL, 'chatgpt4SecretKey');
INSERT INTO `sys_config` VALUES (69, 'chatgpt35_url', 2, 'chatgpt35_url', 'https://api.openai.com/', 'N', '', NULL, '', NULL, NULL);
INSERT INTO `sys_config` VALUES (70, 'chatgpt35_secret_key', 2, 'chatgpt35_secret_key', 'sk-XGBludyG5COsJEitEc4cB984Fa0c42E1B838BeC15bC3CbDb', 'N', '', NULL, '', NULL, NULL);
INSERT INTO `sys_config` VALUES (71, 'PDF的上传的大小限制', 2, 'max_file_size', '2', 'N', '', NULL, '', NULL, 'PDF的上传的大小限制');
INSERT INTO `sys_config` VALUES (72, '邀请好友注册就送推广员免费次数', 2, 'register_invite_moneyTime', '5', 'N', '', NULL, '', NULL, NULL);
INSERT INTO `sys_config` VALUES (73, 'MidjourneyUrl', 2, 'midjourney_url', 'https://api.openai.com/', 'N', '', NULL, '', NULL, 'MidjourneyUrl');
INSERT INTO `sys_config` VALUES (74, 'MidjourneyKey', 2, 'midjourney_key', 'sk-6jLYjiuVY250vQK4104b4a78Be3e49Db88E89a01759a1d8b', 'N', '', NULL, '', NULL, 'MidjourneyKey');
INSERT INTO `sys_config` VALUES (75, '用户充值金额体验开关', 2, 'experienced', '0', 'N', '', NULL, '', NULL, '用户充值金额体验开关 0:开启 1:关闭');
INSERT INTO `sys_config` VALUES (76, 'chatgptDalleUrl', 2, 'chatgptDalleUrl', 'https://api.openai.com/', 'N', '', NULL, '', NULL, 'chatgptDalleUrl');
INSERT INTO `sys_config` VALUES (77, 'chatgptDalleSecretKey', 2, 'chatgptDalleSecretKey', 'sk-6jLYjiuVY250vQK4104b4a78Be3e49Db88E89a01759a1d8b', 'N', '', NULL, '', NULL, 'chatgptDalleSecretKey');
INSERT INTO `sys_config` VALUES (78, 'mjFreeTime', 2, 'mj_free_time', '2', 'N', '', NULL, '', NULL, 'mjFreeTime');
INSERT INTO `sys_config` VALUES (79, 'dallETime', 2, 'dall_e_time', '2', 'N', '', NULL, '', NULL, 'dallETime');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', 'ChatGPT', 0, 'OpenAI', '15888888888', '1@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '刘玖玖', 1, 'ChatGPT', '15888888888', 'japhet@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', 'admin', '2023-05-31 14:14:42');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '吴先齐', 1, 'ChatGPT', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', 'admin', '2023-05-31 14:17:04');
INSERT INTO `sys_dept` VALUES (103, 100, '0,100', '李杰伟', 1, 'ChatGPT', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 100, '0,100', '谢惠强', 1, 'ChatGPT', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 100, '0,100', '胡蓉', 1, 'ChatGPT', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', 'japhet', 2, '推广者1', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 102, '0,100,102', 'wuqi', 2, '推广者1', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 103, '0,100,103', '班长', 2, '推广者1', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 104, '0,100,104', '强子', 2, '推广者1', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);
INSERT INTO `sys_dept` VALUES (110, 105, '0,100,105', '莽妹', 2, '推广者1', '15888888888', 'tgz@qq.com', '0', '0', 'admin', '2023-05-22 17:08:28', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `dict_sort` int(4) DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 1, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 1, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 1, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 1, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 1, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 1, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 1, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 1, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 1, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 1, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 1, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 1, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 1, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 1, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 1, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 1, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 1, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (37, 1, 0, '微信二维码', '/profile/avatar/2023/07/03/blob_20230703150939A001.png', 'kefu', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (38, 1, 1, 'QQ二维码', 'https://aa.jpg', 'kefu', '', '', 'N', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (39, 1, 2, '微信群', '/profile/avatar/2023/07/03/blob_20230703151104A002.png', 'kefu', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (40, 1, 3, 'QQ群', 'https://aa.jpg', 'kefu', '', '', 'N', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (41, 1, 0, 'localhost', '1', 'partner', '', '', 'N', '0', 'admin', '2023-09-12 16:20:03', '', NULL, '合伙人');
INSERT INTO `sys_dict_data` VALUES (42, 1, 1, '192.168.99.99', '2', 'partner', '', '', 'N', '0', 'admin', '2023-09-12 16:20:03', '', NULL, '合伙人');
INSERT INTO `sys_dict_data` VALUES (43, 2, 0, '微信二维码', 'https://aa.jpg', 'kefu', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (44, 2, 1, 'QQ二维码', 'https://bb.jpg', 'kefu', '', '', 'N', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (45, 2, 2, '微信群', 'https://cc.jpg', 'kefu', '', '', 'N', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');
INSERT INTO `sys_dict_data` VALUES (46, 2, 3, 'QQ群', 'https://dd.jpg', 'kefu', '', '', 'N', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '客服');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (13, '客服', 'kefu', '0', 'admin', '2023-07-03 10:17:18', '', NULL, '客服图片');
INSERT INTO `sys_dict_type` VALUES (14, '合伙人', 'partner', '0', 'admin', '2023-07-03 10:17:18', '', NULL, '合伙人');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_job` VALUES (4, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '0 0 1 * * ? *', '1', '1', '0', 'admin', '2023-06-01 14:25:51', 'admin', '2023-11-28 17:03:21', '');
INSERT INTO `sys_job` VALUES (5, 'gpt4', 'DEFAULT', 'ryTask.resettinggpg4(9)', '0 0 1 * * ? *', '1', '1', '0', 'admin', '2023-12-20 14:17:04', 'admin', '2023-12-20 14:18:20', '');
INSERT INTO `sys_job` VALUES (6, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '0 0 1 * * ? *', '1', '1', '0', 'admin', '2023-12-20 14:27:38', '', '2023-12-20 14:27:42', '');
INSERT INTO `sys_job` VALUES (7, 'dalle', 'DEFAULT', 'ryTask.dalleTime(3)', '0 0 1 * * ? *', '1', '1', '0', 'admin', '2023-12-20 15:12:52', '', '2023-12-20 15:15:59', '');
INSERT INTO `sys_job` VALUES (8, 'midjourney', 'DEFAULT', 'ryTask.mjTime(2)', '0 0 1 * * ? *', '1', '1', '0', 'admin', '2023-12-20 15:13:42', '', '2023-12-20 15:16:01', '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '系统默认（无参） 总共耗时：2毫秒', '0', '', '2023-06-01 13:48:43');
INSERT INTO `sys_job_log` VALUES (2, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：16毫秒', '0', '', '2023-06-01 14:26:46');
INSERT INTO `sys_job_log` VALUES (3, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：85毫秒', '0', '', '2023-06-06 01:00:00');
INSERT INTO `sys_job_log` VALUES (4, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：126毫秒', '0', '', '2023-06-07 01:00:00');
INSERT INTO `sys_job_log` VALUES (5, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：81毫秒', '0', '', '2023-06-07 01:00:00');
INSERT INTO `sys_job_log` VALUES (6, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：76毫秒', '0', '', '2023-06-08 01:00:00');
INSERT INTO `sys_job_log` VALUES (7, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：199毫秒', '0', '', '2023-06-08 01:00:00');
INSERT INTO `sys_job_log` VALUES (8, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：153毫秒', '0', '', '2023-06-15 01:00:00');
INSERT INTO `sys_job_log` VALUES (9, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：64毫秒', '0', '', '2023-06-21 17:51:20');
INSERT INTO `sys_job_log` VALUES (10, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：42毫秒', '0', '', '2023-06-26 01:00:00');
INSERT INTO `sys_job_log` VALUES (11, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：68毫秒', '0', '', '2023-07-04 01:00:00');
INSERT INTO `sys_job_log` VALUES (12, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：14毫秒', '0', '', '2023-07-18 01:00:00');
INSERT INTO `sys_job_log` VALUES (13, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：966毫秒', '0', '', '2023-08-08 01:00:02');
INSERT INTO `sys_job_log` VALUES (14, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：41毫秒', '0', '', '2023-08-12 01:00:00');
INSERT INTO `sys_job_log` VALUES (15, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：1805毫秒', '0', '', '2023-08-23 01:00:04');
INSERT INTO `sys_job_log` VALUES (16, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：245毫秒', '0', '', '2023-09-12 01:00:00');
INSERT INTO `sys_job_log` VALUES (17, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：75毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy88.selectOne(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:160)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:87)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy150.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.C', '2023-09-20 01:00:00');
INSERT INTO `sys_job_log` VALUES (18, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：1265毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy88.selectOne(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:160)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:87)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy228.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.C', '2023-09-21 01:00:03');
INSERT INTO `sys_job_log` VALUES (19, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：109毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.selectOne(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:160)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:87)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy154.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.C', '2023-10-12 01:00:00');
INSERT INTO `sys_job_log` VALUES (20, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：408毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.selectOne(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:160)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:87)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy153.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.C', '2023-11-14 01:00:01');
INSERT INTO `sys_job_log` VALUES (21, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting', '重置用户每日免费次数 总共耗时：689毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 2\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.selectOne(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:160)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:87)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy153.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.C', '2023-11-15 01:00:01');
INSERT INTO `sys_job_log` VALUES (22, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3\')', '重置用户每日免费次数 总共耗时：23110毫秒', '0', '', '2023-11-15 09:19:15');
INSERT INTO `sys_job_log` VALUES (23, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3\')', '重置用户每日免费次数 总共耗时：149毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.apache.ibatis.binding.BindingException: Mapper method \'com.ruoyi.quartz.mapper.SysJobMapper.selectFreeTime attempted to return null from a method with a primitive return type (int).\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:102)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy153.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.invokeMethod(CglibAopProxy.java:386)\r\n	at org.springframework.aop.framework.CglibAopProxy.access$000(CglibAopProxy.java:85)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:704)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$EnhancerBySpringCGLIB$$ea23feb4.selectFreeTime(<gener', '2023-11-15 09:19:50');
INSERT INTO `sys_job_log` VALUES (24, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3\')', '重置用户每日免费次数 总共耗时：152毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.apache.ibatis.binding.BindingException: Mapper method \'com.ruoyi.quartz.mapper.SysJobMapper.selectFreeTime attempted to return null from a method with a primitive return type (int).\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:102)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy153.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.invokeMethod(CglibAopProxy.java:386)\r\n	at org.springframework.aop.framework.CglibAopProxy.access$000(CglibAopProxy.java:85)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:704)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$EnhancerBySpringCGLIB$$ea23feb4.selectFreeTime(<gener', '2023-11-15 09:20:05');
INSERT INTO `sys_job_log` VALUES (25, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3\')', '重置用户每日免费次数 总共耗时：31516毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.apache.ibatis.binding.BindingException: Mapper method \'com.ruoyi.quartz.mapper.SysJobMapper.selectFreeTime attempted to return null from a method with a primitive return type (int).\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:102)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy153.selectFreeTime(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.selectFreeTime(SysJobServiceImpl.java:266)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.invokeMethod(CglibAopProxy.java:386)\r\n	at org.springframework.aop.framework.CglibAopProxy.access$000(CglibAopProxy.java:85)\r\n	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:704)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$EnhancerBySpringCGLIB$$ea23feb4.selectFreeTime(<gener', '2023-11-15 09:20:55');
INSERT INTO `sys_job_log` VALUES (26, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2\')', '重置用户每日免费次数 总共耗时：3406毫秒', '0', '', '2023-11-15 09:21:10');
INSERT INTO `sys_job_log` VALUES (27, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2\')', '重置用户每日免费次数 总共耗时：15毫秒', '0', '', '2023-11-15 09:21:15');
INSERT INTO `sys_job_log` VALUES (28, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2\')', '重置用户每日免费次数 总共耗时：105608毫秒', '0', '', '2023-11-15 09:27:17');
INSERT INTO `sys_job_log` VALUES (29, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2\')', '重置用户每日免费次数 总共耗时：6791毫秒', '0', '', '2023-11-15 09:28:13');
INSERT INTO `sys_job_log` VALUES (30, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2\')', '重置用户每日免费次数 总共耗时：1796毫秒', '0', '', '2023-11-24 01:00:05');
INSERT INTO `sys_job_log` VALUES (31, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：116毫秒', '0', '', '2023-11-28 17:03:28');
INSERT INTO `sys_job_log` VALUES (32, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：13109毫秒', '0', '', '2023-11-28 17:04:03');
INSERT INTO `sys_job_log` VALUES (33, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：925毫秒', '0', '', '2023-11-30 01:00:02');
INSERT INTO `sys_job_log` VALUES (34, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：195毫秒', '0', '', '2023-12-01 01:00:00');
INSERT INTO `sys_job_log` VALUES (35, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：58毫秒', '0', '', '2023-12-02 01:00:00');
INSERT INTO `sys_job_log` VALUES (36, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：243毫秒', '0', '', '2023-12-03 01:00:00');
INSERT INTO `sys_job_log` VALUES (37, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：12毫秒', '0', '', '2023-12-04 01:00:00');
INSERT INTO `sys_job_log` VALUES (38, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：306毫秒', '0', '', '2023-12-06 01:00:01');
INSERT INTO `sys_job_log` VALUES (39, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：20毫秒', '0', '', '2023-12-07 01:00:00');
INSERT INTO `sys_job_log` VALUES (40, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：22毫秒', '0', '', '2023-12-08 01:00:00');
INSERT INTO `sys_job_log` VALUES (41, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：56毫秒', '0', '', '2023-12-09 01:00:00');
INSERT INTO `sys_job_log` VALUES (42, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：106235毫秒', '0', '', '2023-12-20 11:31:46');
INSERT INTO `sys_job_log` VALUES (43, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：215毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter \'freeTime\' not found. Available parameters are [arg1, arg0, param1, param2]\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy155.resetting(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.resetting(SysJobServiceImpl.java:271)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.i', '2023-12-20 11:32:37');
INSERT INTO `sys_job_log` VALUES (44, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：7274毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter \'freeTime\' not found. Available parameters are [arg1, arg0, param1, param2]\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy155.resetting(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.resetting(SysJobServiceImpl.java:271)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.i', '2023-12-20 11:33:00');
INSERT INTO `sys_job_log` VALUES (45, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：17055毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter \'freeTime\' not found. Available parameters are [arg1, arg0, param1, param2]\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy155.resetting(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.resetting(SysJobServiceImpl.java:271)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.i', '2023-12-20 11:33:41');
INSERT INTO `sys_job_log` VALUES (46, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：5707毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.binding.BindingException: Parameter \'freeTime\' not found. Available parameters are [arg1, arg0, param1, param2]\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:86)\r\n	at com.sun.proxy.$Proxy155.resetting(Unknown Source)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl.resetting(SysJobServiceImpl.java:271)\r\n	at com.ruoyi.quartz.service.impl.SysJobServiceImpl$$FastClassBySpringCGLIB$$85c6f484.invoke(<generated>)\r\n	at org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n	at org.springframework.aop.framework.CglibAopProxy.i', '2023-12-20 11:46:53');
INSERT INTO `sys_job_log` VALUES (47, 'gpt4', 'DEFAULT', 'ryTask.resettinggpg4(\'9\')', 'gpt4 总共耗时：57毫秒', '1', 'java.lang.NoSuchMethodException: com.ruoyi.quartz.task.RyTask.resettinggpg4(java.lang.String)\r\n	at java.lang.Class.getMethod(Class.java:1786)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:55)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2023-12-20 14:17:17');
INSERT INTO `sys_job_log` VALUES (48, 'gpt4', 'DEFAULT', 'ryTask.resettinggpg4(9)', 'gpt4 总共耗时：5846毫秒', '0', '', '2023-12-20 14:17:51');
INSERT INTO `sys_job_log` VALUES (49, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：12毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property=\'companyId\', mode=IN, javaType=class java.lang.Integer, jdbcType=null, numericScale=null, resultMapId=\'null\', jdbcTypeName=\'null\', expression=\'null\'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #4 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invok', '2023-12-20 14:18:26');
INSERT INTO `sys_job_log` VALUES (50, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：8毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property=\'companyId\', mode=IN, javaType=class java.lang.Integer, jdbcType=null, numericScale=null, resultMapId=\'null\', jdbcTypeName=\'null\', expression=\'null\'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #4 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invok', '2023-12-20 14:18:54');
INSERT INTO `sys_job_log` VALUES (51, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：14263毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:56)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property=\'companyId\', mode=IN, javaType=class java.lang.Integer, jdbcType=null, numericScale=null, resultMapId=\'null\', jdbcTypeName=\'null\', expression=\'null\'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #4 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer\r\n	at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:96)\r\n	at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:441)\r\n	at com.sun.proxy.$Proxy90.update(Unknown Source)\r\n	at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:288)\r\n	at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:67)\r\n	at org.apache.ibatis.binding.MapperProxy$PlainMethodInvoker.invoke(MapperProxy.java:145)\r\n	at org.apache.ibatis.binding.MapperProxy.invok', '2023-12-20 14:19:32');
INSERT INTO `sys_job_log` VALUES (52, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：7077毫秒', '0', '', '2023-12-20 14:21:15');
INSERT INTO `sys_job_log` VALUES (53, 'gpt4', 'DEFAULT', 'ryTask.resettinggpg4(9)', 'gpt4 总共耗时：3毫秒', '0', '', '2023-12-20 14:21:19');
INSERT INTO `sys_job_log` VALUES (54, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：25毫秒', '0', '', '2023-12-20 14:21:50');
INSERT INTO `sys_job_log` VALUES (55, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：49毫秒', '0', '', '2023-12-20 14:22:06');
INSERT INTO `sys_job_log` VALUES (56, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '重置已读 总共耗时：148毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'read = 0 WHERE read = 1\' at line 1\r\n### The error may exist in com/ruoyi/quartz/mapper/SysJobMapper.java (best guess)\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE openai_user_info SET read = 0 WHERE read = 1\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'read = 0 WHERE read = 1\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'read = 0 WHERE read = 1\' at line 1\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:239)\r\n	at org.springframework.jdbc.suppor', '2023-12-20 14:27:45');
INSERT INTO `sys_job_log` VALUES (57, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '重置已读 总共耗时：66毫秒', '1', 'java.lang.reflect.InvocationTargetException\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:61)\r\n	at com.ruoyi.quartz.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.ruoyi.quartz.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:19)\r\n	at com.ruoyi.quartz.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:43)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\nCaused by: org.springframework.jdbc.BadSqlGrammarException: \r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'read = 1\' at line 1\r\n### The error may exist in com/ruoyi/quartz/mapper/SysJobMapper.java (best guess)\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: UPDATE openai_user_info SET `read` = 0 WHERE read = 1\r\n### Cause: java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'read = 1\' at line 1\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near \'read = 1\' at line 1\r\n	at org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:239)\r\n	at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.tr', '2023-12-20 14:28:29');
INSERT INTO `sys_job_log` VALUES (58, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '重置已读 总共耗时：12毫秒', '0', '', '2023-12-20 14:29:16');
INSERT INTO `sys_job_log` VALUES (59, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '重置已读 总共耗时：33毫秒', '0', '', '2023-12-20 14:40:12');
INSERT INTO `sys_job_log` VALUES (60, 'midjourney', 'DEFAULT', 'ryTask.mjTime(2)', 'midjourney 总共耗时：10毫秒', '0', '', '2023-12-20 15:16:31');
INSERT INTO `sys_job_log` VALUES (61, 'midjourney', 'DEFAULT', 'ryTask.mjTime(2)', 'midjourney 总共耗时：7毫秒', '0', '', '2023-12-20 15:16:57');
INSERT INTO `sys_job_log` VALUES (62, 'dalle', 'DEFAULT', 'ryTask.dalleTime(3)', 'dalle 总共耗时：30毫秒', '0', '', '2023-12-20 15:17:22');
INSERT INTO `sys_job_log` VALUES (63, 'dalle', 'DEFAULT', 'ryTask.dalleTime(3)', 'dalle 总共耗时：4毫秒', '0', '', '2023-12-20 15:17:50');
INSERT INTO `sys_job_log` VALUES (64, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '重置已读 总共耗时：2545毫秒', '0', '', '2023-12-26 01:00:08');
INSERT INTO `sys_job_log` VALUES (65, 'gpt4', 'DEFAULT', 'ryTask.resettinggpg4(9)', 'gpt4 总共耗时：2244毫秒', '0', '', '2023-12-26 01:00:08');
INSERT INTO `sys_job_log` VALUES (66, 'dalle', 'DEFAULT', 'ryTask.dalleTime(3)', 'dalle 总共耗时：2244毫秒', '0', '', '2023-12-26 01:00:08');
INSERT INTO `sys_job_log` VALUES (67, 'midjourney', 'DEFAULT', 'ryTask.mjTime(2)', 'midjourney 总共耗时：2244毫秒', '0', '', '2023-12-26 01:00:08');
INSERT INTO `sys_job_log` VALUES (68, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：4222毫秒', '0', '', '2023-12-26 01:00:08');
INSERT INTO `sys_job_log` VALUES (69, '重置已读', 'DEFAULT', 'ryTask.resettingUserNoRead', '重置已读 总共耗时：71毫秒', '0', '', '2023-12-27 01:00:00');
INSERT INTO `sys_job_log` VALUES (70, 'gpt4', 'DEFAULT', 'ryTask.resettinggpg4(9)', 'gpt4 总共耗时：58毫秒', '0', '', '2023-12-27 01:00:00');
INSERT INTO `sys_job_log` VALUES (71, 'midjourney', 'DEFAULT', 'ryTask.mjTime(2)', 'midjourney 总共耗时：54毫秒', '0', '', '2023-12-27 01:00:00');
INSERT INTO `sys_job_log` VALUES (72, 'dalle', 'DEFAULT', 'ryTask.dalleTime(3)', 'dalle 总共耗时：78毫秒', '0', '', '2023-12-27 01:00:00');
INSERT INTO `sys_job_log` VALUES (73, '重置用户每日免费次数', 'DEFAULT', 'ryTask.resetting(\'1,2,3,4,5,6,\')', '重置用户每日免费次数 总共耗时：232毫秒', '0', '', '2023-12-27 01:00:00');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2076 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '1', '', 'fa fa-gear', 'admin', '2023-05-22 17:08:29', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '1', '', 'fa fa-video-camera', 'admin', '2023-05-22 17:08:29', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '1', '', 'fa fa-bars', 'admin', '2023-05-22 17:08:29', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', 'menuBlank', 'C', '1', '1', '', 'fa fa-location-arrow', 'admin', '2023-05-22 17:08:29', 'admin', '2023-05-25 14:19:43', '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', '1', 'system:user:view', 'fa fa-user-o', 'admin', '2023-05-22 17:08:29', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', '1', 'system:role:view', 'fa fa-user-secret', 'admin', '2023-05-22 17:08:29', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', '1', 'system:menu:view', 'fa fa-th-list', 'admin', '2023-05-22 17:08:29', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', '1', 'system:dept:view', 'fa fa-outdent', 'admin', '2023-05-22 17:08:29', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', '1', 'system:post:view', 'fa fa-address-card-o', 'admin', '2023-05-22 17:08:29', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', '1', 'system:dict:view', 'fa fa-bookmark-o', 'admin', '2023-05-22 17:08:29', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', '1', 'system:config:view', 'fa fa-sun-o', 'admin', '2023-05-22 17:08:29', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', '1', 'system:notice:view', 'fa fa-bullhorn', 'admin', '2023-05-22 17:08:29', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '1', '', 'fa fa-pencil-square-o', 'admin', '2023-05-22 17:08:29', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', '1', 'monitor:online:view', 'fa fa-user-circle', 'admin', '2023-05-22 17:08:29', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', '', 'C', '0', '1', 'monitor:job:view', 'fa fa-tasks', 'admin', '2023-05-22 17:08:29', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', '1', 'monitor:data:view', 'fa fa-bug', 'admin', '2023-05-22 17:08:29', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, '/monitor/server', '', 'C', '0', '1', 'monitor:server:view', 'fa fa-server', 'admin', '2023-05-22 17:08:29', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, '/monitor/cache', '', 'C', '0', '1', 'monitor:cache:view', 'fa fa-cube', 'admin', '2023-05-22 17:08:29', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, '/tool/build', '', 'C', '0', '1', 'tool:build:view', 'fa fa-wpforms', 'admin', '2023-05-22 17:08:29', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', '1', 'tool:gen:view', 'fa fa-code', 'admin', '2023-05-22 17:08:29', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', '1', 'tool:swagger:view', 'fa fa-gg', 'admin', '2023-05-22 17:08:29', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', '2023-05-22 17:08:29', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', '1', 'monitor:logininfor:view', 'fa fa-file-image-o', 'admin', '2023-05-22 17:08:29', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', '1', 'system:user:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', '1', 'system:user:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', '1', 'system:user:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', '1', 'system:user:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', '1', 'system:user:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', '1', 'system:user:import', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', '1', 'system:user:resetPwd', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', '1', 'system:role:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', '1', 'system:role:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', '1', 'system:role:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', '1', 'system:role:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', '1', 'system:role:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', '1', 'system:menu:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', '1', 'system:menu:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', '1', 'system:menu:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', '1', 'system:menu:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', '1', 'system:dept:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', '1', 'system:dept:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', '1', 'system:dept:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', '1', 'system:dept:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', '1', 'system:post:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', '1', 'system:post:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', '1', 'system:post:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', '1', 'system:post:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', '1', 'system:post:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', '1', 'system:dict:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', '1', 'system:dict:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', '1', 'system:dict:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', '1', 'system:dict:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', '1', 'system:dict:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', '1', 'system:config:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', '1', 'system:config:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', '1', 'system:config:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', '1', 'system:config:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', '1', 'system:config:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', '1', 'system:notice:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', '1', 'system:notice:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', '1', 'system:notice:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', '1', 'system:notice:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', '1', 'monitor:operlog:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', '1', 'monitor:operlog:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', '1', 'monitor:operlog:detail', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', '1', 'monitor:operlog:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', '1', 'monitor:logininfor:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', '1', 'monitor:logininfor:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', '1', 'monitor:logininfor:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '账户解锁', 501, 4, '#', '', 'F', '0', '1', 'monitor:logininfor:unlock', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '在线查询', 109, 1, '#', '', 'F', '0', '1', 'monitor:online:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '批量强退', 109, 2, '#', '', 'F', '0', '1', 'monitor:online:batchForceLogout', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '单条强退', 109, 3, '#', '', 'F', '0', '1', 'monitor:online:forceLogout', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务查询', 110, 1, '#', '', 'F', '0', '1', 'monitor:job:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务新增', 110, 2, '#', '', 'F', '0', '1', 'monitor:job:add', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务修改', 110, 3, '#', '', 'F', '0', '1', 'monitor:job:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '任务删除', 110, 4, '#', '', 'F', '0', '1', 'monitor:job:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '状态修改', 110, 5, '#', '', 'F', '0', '1', 'monitor:job:changeStatus', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '任务详细', 110, 6, '#', '', 'F', '0', '1', 'monitor:job:detail', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '任务导出', 110, 7, '#', '', 'F', '0', '1', 'monitor:job:export', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成查询', 115, 1, '#', '', 'F', '0', '1', 'tool:gen:list', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '生成修改', 115, 2, '#', '', 'F', '0', '1', 'tool:gen:edit', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '生成删除', 115, 3, '#', '', 'F', '0', '1', 'tool:gen:remove', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '预览代码', 115, 4, '#', '', 'F', '0', '1', 'tool:gen:preview', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '生成代码', 115, 5, '#', '', 'F', '0', '1', 'tool:gen:code', '#', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, 'OpenAi', 0, 5, '#', 'menuItem', 'M', '0', '1', NULL, 'fa fa-paper-plane', 'admin', '2023-05-22 17:14:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, 'openai', 2000, 1, '/openai/openai', 'menuItem', 'C', '0', '1', 'openai:openai:view', 'fa fa-fighter-jet', 'admin', '2023-05-22 17:15:06', 'admin', '2023-05-22 17:19:00', '');
INSERT INTO `sys_menu` VALUES (2002, '聊天记录', 2000, 1, '/system/chat', 'menuItem', 'C', '0', '1', 'system:chat:view', '#', 'admin', '2023-05-23 13:44:28', 'admin', '2023-05-23 13:49:54', '聊天记录菜单');
INSERT INTO `sys_menu` VALUES (2003, '聊天记录查询', 2002, 1, '#', '', 'F', '0', '1', 'system:chat:list', '#', 'admin', '2023-05-23 13:44:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2004, '聊天记录新增', 2002, 2, '#', '', 'F', '0', '1', 'system:chat:add', '#', 'admin', '2023-05-23 13:44:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '聊天记录修改', 2002, 3, '#', '', 'F', '0', '1', 'system:chat:edit', '#', 'admin', '2023-05-23 13:44:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '聊天记录删除', 2002, 4, '#', '', 'F', '0', '1', 'system:chat:remove', '#', 'admin', '2023-05-23 13:44:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '聊天记录导出', 2002, 5, '#', '', 'F', '0', '1', 'system:chat:export', '#', 'admin', '2023-05-23 13:44:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2008, '聊天记录详情', 2000, 1, '/system/detail', 'menuItem', 'C', '0', '1', 'system:detail:view', '#', 'admin', '2023-05-23 13:44:46', 'admin', '2023-05-23 13:50:08', '聊天记录详情菜单');
INSERT INTO `sys_menu` VALUES (2009, '聊天记录详情查询', 2008, 1, '#', '', 'F', '0', '1', 'system:detail:list', '#', 'admin', '2023-05-23 13:44:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '聊天记录详情新增', 2008, 2, '#', '', 'F', '0', '1', 'system:detail:add', '#', 'admin', '2023-05-23 13:44:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '聊天记录详情修改', 2008, 3, '#', '', 'F', '0', '1', 'system:detail:edit', '#', 'admin', '2023-05-23 13:44:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '聊天记录详情删除', 2008, 4, '#', '', 'F', '0', '1', 'system:detail:remove', '#', 'admin', '2023-05-23 13:44:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '聊天记录详情导出', 2008, 5, '#', '', 'F', '0', '1', 'system:detail:export', '#', 'admin', '2023-05-23 13:44:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2014, 'FAQ 常用问题解答', 2000, 1, '/system/faq', 'menuItem', 'C', '0', '1', 'system:faq:view', '#', 'admin', '2023-05-23 13:44:54', 'admin', '2023-05-23 13:50:19', 'FAQ 常用问题解答菜单');
INSERT INTO `sys_menu` VALUES (2015, 'FAQ 常用问题解答查询', 2014, 1, '#', '', 'F', '0', '1', 'system:faq:list', '#', 'admin', '2023-05-23 13:44:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2016, 'FAQ 常用问题解答新增', 2014, 2, '#', '', 'F', '0', '1', 'system:faq:add', '#', 'admin', '2023-05-23 13:44:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2017, 'FAQ 常用问题解答修改', 2014, 3, '#', '', 'F', '0', '1', 'system:faq:edit', '#', 'admin', '2023-05-23 13:44:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2018, 'FAQ 常用问题解答删除', 2014, 4, '#', '', 'F', '0', '1', 'system:faq:remove', '#', 'admin', '2023-05-23 13:44:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2019, 'FAQ 常用问题解答导出', 2014, 5, '#', '', 'F', '0', '1', 'system:faq:export', '#', 'admin', '2023-05-23 13:44:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2020, '用户信息详情', 2000, 1, '/system/info', 'menuItem', 'C', '0', '1', 'system:info:view', '#', 'admin', '2023-05-23 13:45:03', 'admin', '2023-05-23 13:50:33', '用户信息详情菜单');
INSERT INTO `sys_menu` VALUES (2021, '用户信息详情查询', 2020, 1, '#', '', 'F', '0', '1', 'system:info:list', '#', 'admin', '2023-05-23 13:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '用户信息详情新增', 2020, 2, '#', '', 'F', '0', '1', 'system:info:add', '#', 'admin', '2023-05-23 13:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '用户信息详情修改', 2020, 3, '#', '', 'F', '0', '1', 'system:info:edit', '#', 'admin', '2023-05-23 13:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2024, '用户信息详情删除', 2020, 4, '#', '', 'F', '0', '1', 'system:info:remove', '#', 'admin', '2023-05-23 13:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '用户信息详情导出', 2020, 5, '#', '', 'F', '0', '1', 'system:info:export', '#', 'admin', '2023-05-23 13:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2026, 'prompts', 3, 1, '/system/prompts', '', 'C', '0', '1', 'system:prompts:view', '#', 'admin', '2023-06-30 15:10:21', '', NULL, 'prompts菜单');
INSERT INTO `sys_menu` VALUES (2027, 'prompts查询', 2026, 1, '#', '', 'F', '0', '1', 'system:prompts:list', '#', 'admin', '2023-06-30 15:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2028, 'prompts新增', 2026, 2, '#', '', 'F', '0', '1', 'system:prompts:add', '#', 'admin', '2023-06-30 15:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, 'prompts修改', 2026, 3, '#', '', 'F', '0', '1', 'system:prompts:edit', '#', 'admin', '2023-06-30 15:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, 'prompts删除', 2026, 4, '#', '', 'F', '0', '1', 'system:prompts:remove', '#', 'admin', '2023-06-30 15:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, 'prompts导出', 2026, 5, '#', '', 'F', '0', '1', 'system:prompts:export', '#', 'admin', '2023-06-30 15:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2032, '用户订单', 3, 1, '/system/order', '', 'C', '0', '1', 'system:order:view', '#', 'admin', '2023-07-04 16:45:10', '', NULL, '用户订单菜单');
INSERT INTO `sys_menu` VALUES (2033, '用户订单查询', 2032, 1, '#', '', 'F', '0', '1', 'system:order:list', '#', 'admin', '2023-07-04 16:45:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2034, '用户订单新增', 2032, 2, '#', '', 'F', '0', '1', 'system:order:add', '#', 'admin', '2023-07-04 16:45:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2035, '用户订单修改', 2032, 3, '#', '', 'F', '0', '1', 'system:order:edit', '#', 'admin', '2023-07-04 16:45:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '用户订单删除', 2032, 4, '#', '', 'F', '0', '1', 'system:order:remove', '#', 'admin', '2023-07-04 16:45:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2037, '用户订单导出', 2032, 5, '#', '', 'F', '0', '1', 'system:order:export', '#', 'admin', '2023-07-04 16:45:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2038, '套餐计划', 3, 1, '/system/plan', '', 'C', '0', '1', 'system:plan:view', '#', 'admin', '2023-07-04 17:55:11', '', NULL, '套餐计划菜单');
INSERT INTO `sys_menu` VALUES (2039, '套餐计划查询', 2038, 1, '#', '', 'F', '0', '1', 'system:plan:list', '#', 'admin', '2023-07-04 17:55:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2040, '套餐计划新增', 2038, 2, '#', '', 'F', '0', '1', 'system:plan:add', '#', 'admin', '2023-07-04 17:55:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2041, '套餐计划修改', 2038, 3, '#', '', 'F', '0', '1', 'system:plan:edit', '#', 'admin', '2023-07-04 17:55:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '套餐计划删除', 2038, 4, '#', '', 'F', '0', '1', 'system:plan:remove', '#', 'admin', '2023-07-04 17:55:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '套餐计划导出', 2038, 5, '#', '', 'F', '0', '1', 'system:plan:export', '#', 'admin', '2023-07-04 17:55:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2044, '支付成功回调', 3, 1, '/system/log', '', 'C', '0', '1', 'system:log:view', '#', 'admin', '2023-07-05 17:38:14', '', NULL, '支付成功回调菜单');
INSERT INTO `sys_menu` VALUES (2045, '支付成功回调查询', 2044, 1, '#', '', 'F', '0', '1', 'system:log:list', '#', 'admin', '2023-07-05 17:38:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2046, '支付成功回调新增', 2044, 2, '#', '', 'F', '0', '1', 'system:log:add', '#', 'admin', '2023-07-05 17:38:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2047, '支付成功回调修改', 2044, 3, '#', '', 'F', '0', '1', 'system:log:edit', '#', 'admin', '2023-07-05 17:38:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '支付成功回调删除', 2044, 4, '#', '', 'F', '0', '1', 'system:log:remove', '#', 'admin', '2023-07-05 17:38:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2049, '支付成功回调导出', 2044, 5, '#', '', 'F', '0', '1', 'system:log:export', '#', 'admin', '2023-07-05 17:38:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2050, '类型', 3, 1, '/system/type', '', 'C', '0', '1', 'system:type:view', '#', 'admin', '2023-07-06 13:38:47', '', NULL, '类型菜单');
INSERT INTO `sys_menu` VALUES (2051, '类型查询', 2050, 1, '#', '', 'F', '0', '1', 'system:type:list', '#', 'admin', '2023-07-06 13:38:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '类型新增', 2050, 2, '#', '', 'F', '0', '1', 'system:type:add', '#', 'admin', '2023-07-06 13:38:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2053, '类型修改', 2050, 3, '#', '', 'F', '0', '1', 'system:type:edit', '#', 'admin', '2023-07-06 13:38:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2054, '类型删除', 2050, 4, '#', '', 'F', '0', '1', 'system:type:remove', '#', 'admin', '2023-07-06 13:38:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2055, '类型导出', 2050, 5, '#', '', 'F', '0', '1', 'system:type:export', '#', 'admin', '2023-07-06 13:38:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2056, 'api_key 列', 3, 1, '/system/key', '', 'C', '0', '1', 'system:key:view', '#', 'admin', '2023-07-10 14:59:57', '', NULL, 'api_key 列菜单');
INSERT INTO `sys_menu` VALUES (2057, 'api_key 列查询', 2056, 1, '#', '', 'F', '0', '1', 'system:key:list', '#', 'admin', '2023-07-10 14:59:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2058, 'api_key 列新增', 2056, 2, '#', '', 'F', '0', '1', 'system:key:add', '#', 'admin', '2023-07-10 14:59:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2059, 'api_key 列修改', 2056, 3, '#', '', 'F', '0', '1', 'system:key:edit', '#', 'admin', '2023-07-10 14:59:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2060, 'api_key 列删除', 2056, 4, '#', '', 'F', '0', '1', 'system:key:remove', '#', 'admin', '2023-07-10 14:59:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2061, 'api_key 列导出', 2056, 5, '#', '', 'F', '0', '1', 'system:key:export', '#', 'admin', '2023-07-10 14:59:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2062, '账户日志', 3, 1, '/system/accountLog', '', 'C', '0', '1', 'system:accountLog:view', '#', 'admin', '2023-08-09 11:12:13', '', NULL, '账户日志菜单');
INSERT INTO `sys_menu` VALUES (2063, '账户日志查询', 2062, 1, '#', '', 'F', '0', '1', 'system:accountLog:list', '#', 'admin', '2023-08-09 11:12:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2064, '账户日志新增', 2062, 2, '#', '', 'F', '0', '1', 'system:accountLog:add', '#', 'admin', '2023-08-09 11:12:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2065, '账户日志修改', 2062, 3, '#', '', 'F', '0', '1', 'system:accountLog:edit', '#', 'admin', '2023-08-09 11:12:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2066, '账户日志删除', 2062, 4, '#', '', 'F', '0', '1', 'system:accountLog:remove', '#', 'admin', '2023-08-09 11:12:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2067, '账户日志导出', 2062, 5, '#', '', 'F', '0', '1', 'system:accountLog:export', '#', 'admin', '2023-08-09 11:12:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2068, 'midjourney 绘画任务', 3, 1, '/system/info', '', 'C', '0', '1', 'system:info:view', '#', 'admin', '2023-09-21 10:30:02', '', NULL, 'midjourney 绘画任务菜单');
INSERT INTO `sys_menu` VALUES (2069, 'midjourney 绘画任务查询', 2068, 1, '#', '', 'F', '0', '1', 'system:info:list', '#', 'admin', '2023-09-21 10:30:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2070, 'midjourney 绘画任务新增', 2068, 2, '#', '', 'F', '0', '1', 'system:info:add', '#', 'admin', '2023-09-21 10:30:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2071, 'midjourney 绘画任务修改', 2068, 3, '#', '', 'F', '0', '1', 'system:info:edit', '#', 'admin', '2023-09-21 10:30:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2072, 'midjourney 绘画任务删除', 2068, 4, '#', '', 'F', '0', '1', 'system:info:remove', '#', 'admin', '2023-09-21 10:30:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2073, 'midjourney 绘画任务导出', 2068, 5, '#', '', 'F', '0', '1', 'system:info:export', '#', 'admin', '2023-09-21 10:30:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2074, '合伙人', 2000, 2, '/partner', '', 'C', '0', '1', 'system:partner:view', 'fa fa-hand-grab-o', 'admin', '2023-11-08 17:18:55', '', NULL, '合伙人');
INSERT INTO `sys_menu` VALUES (2075, '合伙人', 2074, 1, '#', '', 'F', '0', '1', 'system:partner:list', '#', 'admin', '2023-11-08 17:28:33', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `company_id` int(11) NOT NULL DEFAULT 1 COMMENT '公司id',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (5, 1, '站点1的通知', '1', '站点1的通知', '0', 'admin', '2023-12-20 09:46:52', '', NULL, NULL);
INSERT INTO `sys_notice` VALUES (6, 2, '站点2的通知', '1', '站点2的通知', '0', 'admin', '2023-12-20 09:46:52', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 292 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '192.168.99.99', '内网IP', '{\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"OpenAi\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"5\"],\"icon\":[\"fa fa-paper-plane\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-22 17:14:19', 189);
INSERT INTO `sys_oper_log` VALUES (101, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '192.168.99.99', '内网IP', '{\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"openai\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"openai:openai:list\"],\"orderNum\":[\"1\"],\"icon\":[\"fa fa-fighter-jet\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-22 17:15:06', 16);
INSERT INTO `sys_oper_log` VALUES (102, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '192.168.99.99', '内网IP', '{\"menuId\":[\"2001\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"openai\"],\"url\":[\"/openai/openai\"],\"target\":[\"menuItem\"],\"perms\":[\"openai:openai:view\"],\"orderNum\":[\"1\"],\"icon\":[\"fa fa-fighter-jet\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-22 17:15:40', 20);
INSERT INTO `sys_oper_log` VALUES (103, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '192.168.99.99', '内网IP', '{\"menuId\":[\"2001\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"openai\"],\"url\":[\"/openai/index\"],\"target\":[\"menuItem\"],\"perms\":[\"openai:openai:view\"],\"orderNum\":[\"1\"],\"icon\":[\"fa fa-fighter-jet\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-22 17:16:53', 14);
INSERT INTO `sys_oper_log` VALUES (104, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '192.168.99.99', '内网IP', '{\"menuId\":[\"2001\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"openai\"],\"url\":[\"/openai/openai\"],\"target\":[\"menuItem\"],\"perms\":[\"openai:openai:view\"],\"orderNum\":[\"1\"],\"icon\":[\"fa fa-fighter-jet\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-22 17:19:00', 117);
INSERT INTO `sys_oper_log` VALUES (105, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"openai_faq,openai_chat_detail,openai_user_info,openai_chat\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-23 11:48:42', 184);
INSERT INTO `sys_oper_log` VALUES (106, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.batchGenCode()', 'GET', 1, 'admin', '研发部门', '/tool/gen/batchGenCode', '127.0.0.1', '内网IP', '{\"tables\":[\"openai_chat_detail,openai_faq,openai_user_info,openai_chat\"]}', NULL, 0, NULL, '2023-05-23 11:48:53', 501);
INSERT INTO `sys_oper_log` VALUES (107, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2002\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"聊天记录\"],\"url\":[\"/system/chat\"],\"target\":[\"menuItem\"],\"perms\":[\"system:chat:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-23 13:49:54', 140);
INSERT INTO `sys_oper_log` VALUES (108, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2008\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"聊天记录详情\"],\"url\":[\"/system/detail\"],\"target\":[\"menuItem\"],\"perms\":[\"system:detail:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-23 13:50:08', 10);
INSERT INTO `sys_oper_log` VALUES (109, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2014\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"FAQ 常用问题解答\"],\"url\":[\"/system/faq\"],\"target\":[\"menuItem\"],\"perms\":[\"system:faq:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-23 13:50:19', 15);
INSERT INTO `sys_oper_log` VALUES (110, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2020\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"用户信息详情\"],\"url\":[\"/system/info\"],\"target\":[\"menuItem\"],\"perms\":[\"system:info:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-23 13:50:33', 9);
INSERT INTO `sys_oper_log` VALUES (111, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"若依官网\"],\"url\":[\"http://ruoyi.vip\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"1\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-25 14:19:44', 276);
INSERT INTO `sys_oper_log` VALUES (112, '角色管理', 2, 'com.ruoyi.web.controller.system.SysRoleController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/role/edit', '127.0.0.1', '内网IP', '{\"roleId\":[\"2\"],\"roleName\":[\"普通角色\"],\"roleKey\":[\"common\"],\"roleSort\":[\"2\"],\"status\":[\"0\"],\"remark\":[\"普通角色\"],\"menuIds\":[\"2000,2001\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-30 14:51:06', 321);
INSERT INTO `sys_oper_log` VALUES (113, '账户解锁', 0, 'com.ruoyi.web.controller.monitor.SysLogininforController.unlock()', 'POST', 1, 'admin', '研发部门', '/monitor/logininfor/unlock', '127.0.0.1', '内网IP', '{\"loginName\":[\"admin\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-30 16:20:12', 13);
INSERT INTO `sys_oper_log` VALUES (114, '参数管理', 2, 'com.ruoyi.web.controller.system.SysConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/config/edit', '127.0.0.1', '内网IP', '{\"configId\":[\"1\"],\"configName\":[\"主框架页-默认皮肤样式名称\"],\"configKey\":[\"sys.index.skinName\"],\"configValue\":[\"skin-green\"],\"configType\":[\"Y\"],\"remark\":[\"蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 11:15:05', 265);
INSERT INTO `sys_oper_log` VALUES (115, '参数管理', 2, 'com.ruoyi.web.controller.system.SysConfigController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/config/edit', '127.0.0.1', '内网IP', '{\"configId\":[\"4\"],\"configName\":[\"账号自助-是否开启用户注册功能\"],\"configKey\":[\"sys.account.registerUser\"],\"configValue\":[\"true\"],\"configType\":[\"Y\"],\"remark\":[\"是否开启注册用户功能（true开启，false关闭）\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 11:17:10', 49);
INSERT INTO `sys_oper_log` VALUES (116, '岗位管理', 3, 'com.ruoyi.web.controller.system.SysPostController.remove()', 'POST', 1, 'admin', '研发部门', '/system/post/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"4\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:13:54', 236);
INSERT INTO `sys_oper_log` VALUES (117, '岗位管理', 3, 'com.ruoyi.web.controller.system.SysPostController.remove()', 'POST', 1, 'admin', '研发部门', '/system/post/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"3\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:13:56', 35);
INSERT INTO `sys_oper_log` VALUES (118, '岗位管理', 3, 'com.ruoyi.web.controller.system.SysPostController.remove()', 'POST', 1, 'admin', '研发部门', '/system/post/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"2\"]}', '{\"msg\":\"项目经理已分配,不能删除\",\"code\":500}', 0, NULL, '2023-05-31 14:13:59', 8);
INSERT INTO `sys_oper_log` VALUES (119, '部门管理', 2, 'com.ruoyi.web.controller.system.SysDeptController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\"deptId\":[\"101\"],\"parentId\":[\"100\"],\"parentName\":[\"japhet\"],\"deptName\":[\"japhet\"],\"orderNum\":[\"1\"],\"leader\":[\"japhet\"],\"phone\":[\"15888888888\"],\"email\":[\"japhet@qq.com\"],\"status\":[\"0\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:14:42', 445);
INSERT INTO `sys_oper_log` VALUES (120, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/107', '127.0.0.1', '内网IP', '107', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:14:49', 10);
INSERT INTO `sys_oper_log` VALUES (121, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/106', '127.0.0.1', '内网IP', '106', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:14:52', 32);
INSERT INTO `sys_oper_log` VALUES (122, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/105', '127.0.0.1', '内网IP', '105', '{\"msg\":\"部门存在用户,不允许删除\",\"code\":301}', 0, NULL, '2023-05-31 14:14:54', 10);
INSERT INTO `sys_oper_log` VALUES (123, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/104', '127.0.0.1', '内网IP', '104', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:14:58', 26);
INSERT INTO `sys_oper_log` VALUES (124, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/103', '127.0.0.1', '内网IP', '103', '{\"msg\":\"部门存在用户,不允许删除\",\"code\":301}', 0, NULL, '2023-05-31 14:15:00', 4);
INSERT INTO `sys_oper_log` VALUES (125, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/108', '127.0.0.1', '内网IP', '108', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:15:06', 15);
INSERT INTO `sys_oper_log` VALUES (126, '用户管理', 2, 'com.ruoyi.web.controller.system.SysUserController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/user/edit', '127.0.0.1', '内网IP', '{\"userId\":[\"2\"],\"deptId\":[\"103\"],\"userName\":[\"若依\"],\"dept.deptName\":[\"研发部门\"],\"phonenumber\":[\"15666666666\"],\"email\":[\"ry@qq.com\"],\"loginName\":[\"ry\"],\"sex\":[\"1\"],\"role\":[\"2\"],\"remark\":[\"测试员\"],\"status\":[\"0\"],\"roleIds\":[\"2\"],\"postIds\":[\"2\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:16:19', 124);
INSERT INTO `sys_oper_log` VALUES (127, '部门管理', 3, 'com.ruoyi.web.controller.system.SysDeptController.remove()', 'GET', 1, 'admin', '研发部门', '/system/dept/remove/105', '127.0.0.1', '内网IP', '105', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:16:27', 8);
INSERT INTO `sys_oper_log` VALUES (128, '部门管理', 2, 'com.ruoyi.web.controller.system.SysDeptController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/dept/edit', '127.0.0.1', '内网IP', '{\"deptId\":[\"102\"],\"parentId\":[\"100\"],\"parentName\":[\"japhet\"],\"deptName\":[\"推广者1\"],\"orderNum\":[\"2\"],\"leader\":[\"japhet\"],\"phone\":[\"15888888888\"],\"email\":[\"tgz@qq.com\"],\"status\":[\"0\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:17:05', 29);
INSERT INTO `sys_oper_log` VALUES (129, '用户管理', 3, 'com.ruoyi.web.controller.system.SysUserController.remove()', 'POST', 1, 'admin', '研发部门', '/system/user/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"2\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:19:16', 53);
INSERT INTO `sys_oper_log` VALUES (130, '用户管理', 3, 'com.ruoyi.web.controller.system.SysUserController.remove()', 'POST', 1, 'admin', '研发部门', '/system/user/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"3\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2023-05-31 14:19:27', 10);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2023-05-22 17:08:29', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2023-05-22 17:08:29', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2023-05-22 17:08:29', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2023-05-22 17:08:29', 'admin', '2023-05-30 14:51:06', '普通角色');
INSERT INTO `sys_role` VALUES (3, '合伙人', 'partner', 3, '1', '0', '0', 'admin', '2023-11-08 17:17:20', 'admin', '2023-11-08 17:34:11', '合伙人');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 2000);
INSERT INTO `sys_role_menu` VALUES (2, 2001);
INSERT INTO `sys_role_menu` VALUES (3, 2000);
INSERT INTO `sys_role_menu` VALUES (3, 2001);
INSERT INTO `sys_role_menu` VALUES (3, 2074);
INSERT INTO `sys_role_menu` VALUES (3, 2075);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) DEFAULT 106 COMMENT '部门ID',
  `up_id` int(11) DEFAULT NULL COMMENT '父级id (推广员)',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime(0) DEFAULT NULL COMMENT '密码最后更新时间',
  `permissions` smallint(4) DEFAULT NULL COMMENT '0:普通用户  1:合伙人',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `u_login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 268 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 100, NULL, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '/profile/avatar/2023/12/07/blob_20231207172359A004.png', '9608b446743f04eaf303eea181bcfece', '64fe6e', '0', '0', '192.168.99.99', '2024-01-02 14:27:08', '2023-07-05 10:13:00', 1, 'admin', '2023-05-22 17:08:28', '', '2024-01-02 14:27:08', '管理员');
INSERT INTO `sys_user` VALUES (2, 100, NULL, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2023-05-30 14:51:15', '2023-05-22 17:08:28', NULL, 'admin', '2023-05-22 17:08:28', 'admin', '2023-06-01 13:47:32', '测试员');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('7260523d-122f-4df7-bf3c-ee09b5b64ad4', 'admin', 'ChatGPT', '192.168.99.99', '内网IP', 'Chrome 12', 'Windows 10', 'on_line', '2024-01-02 14:23:03', '2024-01-02 14:28:48', 3600000);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (34, 2);
INSERT INTO `sys_user_role` VALUES (35, 2);
INSERT INTO `sys_user_role` VALUES (36, 2);
INSERT INTO `sys_user_role` VALUES (37, 2);
INSERT INTO `sys_user_role` VALUES (38, 2);
INSERT INTO `sys_user_role` VALUES (39, 2);
INSERT INTO `sys_user_role` VALUES (40, 2);
INSERT INTO `sys_user_role` VALUES (41, 2);
INSERT INTO `sys_user_role` VALUES (42, 2);
INSERT INTO `sys_user_role` VALUES (43, 2);
INSERT INTO `sys_user_role` VALUES (245, 2);
INSERT INTO `sys_user_role` VALUES (247, 2);
INSERT INTO `sys_user_role` VALUES (248, 2);
INSERT INTO `sys_user_role` VALUES (249, 2);
INSERT INTO `sys_user_role` VALUES (250, 2);
INSERT INTO `sys_user_role` VALUES (251, 2);
INSERT INTO `sys_user_role` VALUES (252, 2);
INSERT INTO `sys_user_role` VALUES (253, 2);
INSERT INTO `sys_user_role` VALUES (254, 2);
INSERT INTO `sys_user_role` VALUES (255, 2);
INSERT INTO `sys_user_role` VALUES (256, 2);
INSERT INTO `sys_user_role` VALUES (257, 2);
INSERT INTO `sys_user_role` VALUES (258, 2);
INSERT INTO `sys_user_role` VALUES (259, 2);
INSERT INTO `sys_user_role` VALUES (260, 2);
INSERT INTO `sys_user_role` VALUES (261, 2);
INSERT INTO `sys_user_role` VALUES (262, 2);
INSERT INTO `sys_user_role` VALUES (263, 3);
INSERT INTO `sys_user_role` VALUES (267, 2);

SET FOREIGN_KEY_CHECKS = 1;
