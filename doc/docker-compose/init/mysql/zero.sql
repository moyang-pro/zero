/*
 Navicat Premium Data Transfer

 Source Server         : 墨阳
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : zero

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 08/02/2022 16:14:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `article_des` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章简要描述',
  `article_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章html内容',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'emy账号:作者',
  `article_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章原始内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_blog_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客文章信息主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES (29, '2022-02-06 22:49:21', '1542051400', '2022-02-06 23:29:06', '1542051400', 0, '更新文章信息', '发大幅度', '的顶顶顶顶顶多多多多多多多多多多多多多多多多多多多多多多多多', '<h2><a id=\"SDK_1\"></a>黑胡椒看哈SDK和客户就开始带回家卡号数据库的</h2>\n<p>想</p>\n<h2><a id=\"java_4\"></a>java</h2>\n', '1542051400', '\n## 黑胡椒看哈SDK和客户就开始带回家卡号数据库的\n想\n\n## java');

-- ----------------------------
-- Table structure for mnt_dept
-- ----------------------------
DROP TABLE IF EXISTS `mnt_dept`;
CREATE TABLE `mnt_dept`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `level` tinyint NULL DEFAULT NULL COMMENT '部门等级 1 2 3 4级部门',
  `is_parent` tinyint NULL DEFAULT NULL COMMENT '是否是一级部门',
  `parent_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级部门 编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门描述',
  `head_count` int NULL DEFAULT NULL COMMENT '编制',
  `manager_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `manager_eum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人墨阳eum',
  `org_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属企业编码',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属企业名称',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dept_id`(`id`) USING BTREE,
  INDEX `idex_dept_code`(`dept_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳集团----企业部门信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mnt_dept
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_mem_work
-- ----------------------------
DROP TABLE IF EXISTS `mnt_mem_work`;
CREATE TABLE `mnt_mem_work`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `eum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位编码',
  `position_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位名称',
  `main_flag` tinyint NULL DEFAULT 1 COMMENT '是否主职 0否 1是',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位描述',
  `join_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '入职时间',
  `dept_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门编码',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `manager_eum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人 eum 默认部门负责人',
  `manager_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_mem_id`(`id`) USING BTREE,
  INDEX `idx_mem_eum`(`eum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门成员职位关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mnt_mem_work
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_member
-- ----------------------------
DROP TABLE IF EXISTS `mnt_member`;
CREATE TABLE `mnt_member`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `eum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户墨阳账号 union',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门编码',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `state` tinyint NULL DEFAULT 0 COMMENT '状态  0 正式工人 1实习生 2试用期人员 3离职人员',
  `work_state` tinyint NULL DEFAULT 0 COMMENT '工作状态 0全职 1兼职 2双职',
  `work_degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '出生日期',
  `birthplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `nation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '民族',
  `degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历',
  `weight` double NULL DEFAULT NULL COMMENT '体重',
  `height` double NULL DEFAULT NULL COMMENT '身高',
  `marriage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '婚育情况',
  `current_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目前居住地址',
  `residence` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '户籍所在地',
  `professional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业',
  `create_at` datetime NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_id`(`id`) USING BTREE,
  INDEX `idx_eum`(`eum`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳集团---企业成员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mnt_member
-- ----------------------------

-- ----------------------------
-- Table structure for mnt_org
-- ----------------------------
DROP TABLE IF EXISTS `mnt_org`;
CREATE TABLE `mnt_org`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名',
  `regist_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `work_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `regist_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一信用代码',
  `telphone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业电话',
  `pay_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `start_date` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册日期',
  `legal_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人姓名',
  `lp_id_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证',
  `lp_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册资本',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_org_id`(`id`) USING BTREE,
  INDEX `idx_org_code`(`org_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳集团-----企业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mnt_org
-- ----------------------------

-- ----------------------------
-- Table structure for my_application
-- ----------------------------
DROP TABLE IF EXISTS `my_application`;
CREATE TABLE `my_application`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `app_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `my_plat_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `plat_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台名',
  `manager_eum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人eum',
  `manager_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_app_code`(`app_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳---应用信息信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of my_application
-- ----------------------------

-- ----------------------------
-- Table structure for my_code
-- ----------------------------
DROP TABLE IF EXISTS `my_code`;
CREATE TABLE `my_code`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint NULL DEFAULT NULL,
  `my_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `my_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_code_id`(`id`, `my_code`) USING BTREE,
  INDEX `idx_type_my`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳编码信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of my_code
-- ----------------------------

-- ----------------------------
-- Table structure for my_platform
-- ----------------------------
DROP TABLE IF EXISTS `my_platform`;
CREATE TABLE `my_platform`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `plat_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台名',
  `plat_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台编码 MYP001 ...',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_plat_id`(`id`) USING BTREE,
  INDEX `idx_plat_code`(`plat_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳空间---平台信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of my_platform
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户emy账号 平台上独一无二',
  `plat_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源平台 编码',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源ip',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印日志的方法名',
  `page` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面路径',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求的地址',
  `what` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户做了什么：描述',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0 info; 1 error; 2 warn; 3 debug',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者 emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者emy',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE,
  INDEX `index_e_account`(`emy`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳空间 ---平台---日志信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_member
-- ----------------------------
DROP TABLE IF EXISTS `sys_member`;
CREATE TABLE `sys_member`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `emy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户emy账号 平台上独一无二',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者 emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者emy',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标记信息',
  `eum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '墨阳空间 唯一账号',
  `plat_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属平台 编码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE,
  INDEX `index_user_emy`(`emy`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1488482087721836547 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳空间 ---平台---成员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_member
-- ----------------------------
INSERT INTO `sys_member` VALUES (1488482087721836546, '1542051400', 'moyang.5251', NULL, 'sun', '18811100899', NULL, '2022-02-01 19:59:19', '1542051400', '2022-02-04 02:22:36', '1542051400', 0, '墨阳空间新用户账号注册！', 'Eum(emy=1542051400, phone=18811100899, platCode=ZERO_CODE)', 'ZERO_CODE');

-- ----------------------------
-- Table structure for sys_member_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_member_role`;
CREATE TABLE `sys_member_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `my_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '墨阳编码',
  `role_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `emy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '墨阳平台账号',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_smr_id`(`id`) USING BTREE,
  INDEX `idx_emy`(`emy`) USING BTREE,
  INDEX `idx_my_code`(`my_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台、应用---用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_member_role
-- ----------------------------
INSERT INTO `sys_member_role` VALUES (1, 'ZERO_CODE', 'COMMON_USER', '普通用户', '1542051400', '2022-02-03 15:50:16', 'moyang', '2022-02-03 15:50:40', 'moyang', 0, '系统用户');

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `priv_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `priv_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前权限固定路径（组）',
  `my_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '墨阳平台/应用编码',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_priv_code`(`priv_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳平台、应用--权限信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES (1, 'BLOG_README', 'blog文章浏览', '可以查看自己的文章', '/blog/readme', 'ZERO_CODE', '2022-02-03 14:03:39', 'moyang', '2022-02-03 14:04:13', 'moyang', 0, '权限创建');
INSERT INTO `sys_privilege` VALUES (2, 'BLOG_READ', 'blog文章浏览', '可以查看任何开放的文章', '/blog/read', 'ZERO_CODE', '2022-02-03 14:03:39', 'moyang', '2022-02-03 14:19:08', 'moyang', 0, '权限创建');
INSERT INTO `sys_privilege` VALUES (3, 'BLOG_WRITE', 'blog文章撰写', '可以创建和编辑属于自己的文章', '/blog/write', 'ZERO_CODE', '2022-02-03 14:03:39', 'moyang', '2022-02-03 14:07:08', 'moyang', 0, '权限创建');
INSERT INTO `sys_privilege` VALUES (4, 'BLOG_PUBLISH', 'blog文章发布', '可以发布自己的文章让大家看到', '/blog/publish', 'ZERO_CODE', '2022-02-03 14:03:39', 'moyang', '2022-02-03 14:09:10', 'moyang', 0, '权限创建');
INSERT INTO `sys_privilege` VALUES (5, 'BLOG_REVIEW', 'blog文章审核', '审核和检查平台上发布的文章', '/blog/review', 'ZERO_CODE', '2022-02-03 14:03:39', 'moyang', '2022-02-03 14:10:48', 'moyang', 0, '权限创建');
INSERT INTO `sys_privilege` VALUES (6, 'BLOG_DELETE', 'blog文章删除', '删除自己发布的文章', '/blog/delete', 'ZERO_CODE', '2022-02-03 14:03:39', 'moyang', '2022-02-03 14:19:02', 'moyang', 0, '权限创建');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码 MYSR /MYPR',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色类型：空间角色 平台角色 应用角色',
  `my_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '墨阳编码 查看my_code表',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '普通用户', 'COMMON_USER', '基本权限', '平台角色', 'ZERO_CODE', '2022-02-03 13:55:11', 'moyang', '2022-02-03 13:55:44', 'moyang', 0, '角色创建');
INSERT INTO `sys_role` VALUES (3, '专业用户', 'PRO_USER', '升级版权限', '平台角色', 'ZERO_CODE', '2022-02-03 13:55:11', 'moyang', '2022-02-03 13:58:02', 'moyang', 0, '角色创建');
INSERT INTO `sys_role` VALUES (4, '超级用户', 'SUPER_USER', '升级版权限', '平台角色', 'ZERO_CODE', '2022-02-03 13:55:11', 'moyang', '2022-02-03 13:59:13', 'moyang', 0, '角色创建');

-- ----------------------------
-- Table structure for sys_role_priv
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_priv`;
CREATE TABLE `sys_role_priv`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `priv_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `priv_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_priv
-- ----------------------------
INSERT INTO `sys_role_priv` VALUES (1, 'COMMON_USER', '普通用户', 'BLOG_README', 'blog文章浏览', '2022-02-03 14:27:53', 'moyang', '2022-02-03 14:28:54', 'moyang', 0, '角色权限创建');
INSERT INTO `sys_role_priv` VALUES (2, 'COMMON_USER', '普通用户', 'BLOG_WRITE', 'blog文章撰写', '2022-02-03 14:27:53', 'moyang', '2022-02-03 14:29:54', 'moyang', 0, '角色权限创建');
INSERT INTO `sys_role_priv` VALUES (3, 'COMMON_USER', '普通用户', 'BLOG_DELETE', 'blog文章删除', '2022-02-03 14:27:53', 'moyang', '2022-02-03 14:30:33', 'moyang', 0, '角色权限创建');
INSERT INTO `sys_role_priv` VALUES (4, 'PRO_USER', '专业用户', 'BLOG_DELETE', 'blog文章删除', '2022-02-03 14:27:53', 'moyang', '2022-02-03 14:31:39', 'moyang', 0, '角色权限创建');
INSERT INTO `sys_role_priv` VALUES (5, 'PRO_USER', '专业用户', 'BLOG_WRITE', 'blog文章撰写', '2022-02-03 14:27:53', 'moyang', '2022-02-03 14:31:58', 'moyang', 0, '角色权限创建');
INSERT INTO `sys_role_priv` VALUES (6, 'PRO_USER', '专业用户', 'BLOG_READ', 'blog文章浏览', '2022-02-03 14:27:53', 'moyang', '2022-02-03 14:32:55', 'moyang', 0, '角色权限创建');

-- ----------------------------
-- Table structure for sys_third_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_auth`;
CREATE TABLE `sys_third_auth`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `e_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户emy账号 平台上独一无二',
  `plat_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源平台 编码',
  `identify_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录类型 登录类别，如：系统用户、邮箱、手机，或者第三方的QQ、微信、微博；',
  `identifier` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '识别标识 身份唯一标识，如：登录账号、邮箱地址、手机号码、QQ号码、微信号、微博号；',
  `credential` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权凭证 站内账号是密码、第三方登录是Token；',
  `is_verified` int NULL DEFAULT 0 COMMENT '是否验证 0 yes 1 no',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者 emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者emy',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE,
  INDEX `index_e_account`(`e_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '墨阳空间 ---平台---账号授权信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_third_auth
-- ----------------------------

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_at` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者emy账号',
  `update_at` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者 emy账号',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识 0 未删除 1已删除',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录说明',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of template
-- ----------------------------
INSERT INTO `template` VALUES (1, '2020-12-25 23:17:47', 'moyang1', '2020-12-25 23:17:47', NULL, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
