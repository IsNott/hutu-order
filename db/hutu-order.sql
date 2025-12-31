/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : hutu-order

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_business_config
-- ----------------------------
DROP TABLE IF EXISTS `biz_business_config`;
CREATE TABLE `biz_business_config`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `biz_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务key',
  `biz_context` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '业务正文',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_business_config
-- ----------------------------

-- ----------------------------
-- Table structure for biz_comment
-- ----------------------------
DROP TABLE IF EXISTS `biz_comment`;
CREATE TABLE `biz_comment`  (
  `id` bigint(20) NOT NULL,
  `comment_rate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价打分',
  `comment_context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价正文',
  `comment_urls` json NULL COMMENT '评价图片',
  `comment_user_id` bigint(20) NULL DEFAULT NULL COMMENT '评价用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_comment
-- ----------------------------

-- ----------------------------
-- Table structure for biz_common_remark
-- ----------------------------
DROP TABLE IF EXISTS `biz_common_remark`;
CREATE TABLE `biz_common_remark`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_common_remark
-- ----------------------------
INSERT INTO `biz_common_remark` VALUES (1904105823253037056, '少冰', 0);
INSERT INTO `biz_common_remark` VALUES (1904105823257231360, '少糖', 0);
INSERT INTO `biz_common_remark` VALUES (1904105823257231361, '不加冰', 0);
INSERT INTO `biz_common_remark` VALUES (1904105823257231362, '不加糖', 0);

-- ----------------------------
-- Table structure for biz_coupon
-- ----------------------------
DROP TABLE IF EXISTS `biz_coupon`;
CREATE TABLE `biz_coupon`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `discount_fee` double NULL DEFAULT NULL COMMENT '优惠金额',
  `condition_id` bigint(20) NULL DEFAULT NULL COMMENT '条件id',
  `titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠券标题',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级标题',
  `creator_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人id',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户优惠券表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_coupon
-- ----------------------------
INSERT INTO `biz_coupon` VALUES (613298191621689345, 4, 613298191621689346, '测试优惠券', '无门槛4元券', NULL, 0, '2024-08-19 17:08:31', NULL);

-- ----------------------------
-- Table structure for biz_coupon_condition
-- ----------------------------
DROP TABLE IF EXISTS `biz_coupon_condition`;
CREATE TABLE `biz_coupon_condition`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `biz_id` bigint(20) NULL DEFAULT NULL COMMENT '关联业务id',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `is_enable` tinyint(4) NULL DEFAULT NULL COMMENT '是否启用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者id',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '0-时间 1-满减 2-关联业务 3-全部',
  `price_condition` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额条件',
  `biz_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务条件',
  `condition_expression` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件表达式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '优惠券规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_coupon_condition
-- ----------------------------
INSERT INTO `biz_coupon_condition` VALUES (613298191621689346, NULL, '2024-08-06 17:08:51', '2024-08-30 17:08:55', 1, '2024-08-19 17:09:02', NULL, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for biz_item
-- ----------------------------
DROP TABLE IF EXISTS `biz_item`;
CREATE TABLE `biz_item`  (
  `id` bigint(20) NOT NULL,
  `origin_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始价格',
  `actually_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现价',
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `special` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特殊tag',
  `item_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `item_tag` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品标签',
  `expect_make_time` int(20) NULL DEFAULT NULL COMMENT '预计制作时长，单位：分',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item
-- ----------------------------
INSERT INTO `biz_item` VALUES (1905153932640911360, '15.00', '12.00', '拿铁', '热', '优质咖啡豆搭配顶级牛奶', '热销,新品,多人推荐', 2, 0, '2024-05-24 09:00:00', '2024-05-24 09:00:00');
INSERT INTO `biz_item` VALUES (1905153932640911361, '18.00', '15.00', '卡布奇诺', '无糖', '意式浓缩咖啡与丝滑牛奶的完美结合', '推荐,经典,必点', 2, 0, '2024-05-24 09:15:00', '2024-05-24 09:15:00');
INSERT INTO `biz_item` VALUES (1905153932640911362, '20.00', '18.00', '摩卡', '甜', '香浓巧克力、浓郁咖啡与奶泡的完美融合', '新品,浓郁,香', 2, 0, '2024-05-24 09:30:00', '2024-05-24 09:30:00');
INSERT INTO `biz_item` VALUES (1905153932640911363, '25.00', '22.00', '美式咖啡', '打工人必备', '纯粹的咖啡香味', '冷热,多人爱点', 2, 0, '2024-05-24 09:45:00', '2024-05-24 09:45:00');
INSERT INTO `biz_item` VALUES (1905153932640911364, '22.00', '20.00', '浓缩咖啡', 'short', '浓郁的咖啡香味', '热', 3, 0, '2024-05-24 10:00:00', '2024-05-24 10:00:00');
INSERT INTO `biz_item` VALUES (1905153932640911366, '28.00', '25.00', '拿铁玛奇朵', NULL, '拿铁与玛奇朵的完美结合', '', 4, 0, '2024-05-24 10:15:00', '2024-05-24 10:15:00');
INSERT INTO `biz_item` VALUES (1905153932640911367, '30.00', '28.00', '焦糖玛奇朵', NULL, '香甜焦糖与浓郁咖啡的美妙组合', '', 5, 0, '2024-05-24 10:30:00', '2024-05-24 10:30:00');
INSERT INTO `biz_item` VALUES (1905153932640911368, '20.00', '18.00', '香草拿铁', NULL, '香草风味与顶级牛奶的完美搭配', '', 5, 0, '2024-05-24 10:45:00', '2024-05-24 10:45:00');
INSERT INTO `biz_item` VALUES (1905153932640911369, '22.00', '20.00', '焦糖拿铁', NULL, '浓郁咖啡与香甜焦糖的绝妙组合', '', 3, 0, '2024-05-24 11:00:00', '2024-05-24 11:00:00');
INSERT INTO `biz_item` VALUES (1905153932640911370, '25.00', '22.00', '冰美式', NULL, '清凉解渴的美式咖啡', '', 5, 0, '2024-05-24 11:15:00', '2024-05-24 11:15:00');
INSERT INTO `biz_item` VALUES (1905153932640911371, '30.00', '28.00', '冰摩卡', NULL, '浓郁巧克力与冰冻咖啡的美妙组合', '', 6, 0, '2024-05-24 11:30:00', '2024-05-24 11:30:00');
INSERT INTO `biz_item` VALUES (1905153932640911372, '28.00', '25.00', '冰拿铁', NULL, '清凉爽口的拿铁咖啡', '', 6, 0, '2024-05-24 11:45:00', '2024-05-24 11:45:00');
INSERT INTO `biz_item` VALUES (1905153932640911373, '18.00', '15.00', '冰卡布奇诺', NULL, '冰冻的卡布奇诺咖啡', '', 5, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_item` VALUES (1905153932640911374, '20.00', '18.00', '香草冰拿铁', NULL, '清凉解渴的香草拿铁', '', 7, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_item` VALUES (1905153932640911375, '22.00', '20.00', '香草冰卡布奇诺', NULL, '清凉解渴的香草卡布奇诺', '', 6, 0, '2024-05-24 12:30:00', '2024-05-24 12:30:00');

-- ----------------------------
-- Table structure for biz_item_copy1
-- ----------------------------
DROP TABLE IF EXISTS `biz_item_copy1`;
CREATE TABLE `biz_item_copy1`  (
  `id` bigint(20) NOT NULL,
  `origin_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始价格',
  `actually_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现价',
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `special` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特殊tag',
  `item_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `item_tag` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品标签',
  `expect_make_time` int(20) NULL DEFAULT NULL COMMENT '预计制作时长，单位：分',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item_copy1
-- ----------------------------
INSERT INTO `biz_item_copy1` VALUES (1905153932640911360, '15.00', '12.00', '拿铁', '热', '优质咖啡豆搭配顶级牛奶', '热销,新品,多人推荐', 2, 0, '2024-05-24 09:00:00', '2024-05-24 09:00:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911361, '18.00', '15.00', '卡布奇诺', '无糖', '意式浓缩咖啡与丝滑牛奶的完美结合', '推荐,经典,必点', 2, 0, '2024-05-24 09:15:00', '2024-05-24 09:15:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911362, '20.00', '18.00', '摩卡', '甜', '香浓巧克力、浓郁咖啡与奶泡的完美融合', '新品,浓郁,香', 2, 0, '2024-05-24 09:30:00', '2024-05-24 09:30:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911363, '25.00', '22.00', '美式咖啡', '打工人必备', '纯粹的咖啡香味', '冷热,多人爱点', 2, 0, '2024-05-24 09:45:00', '2024-05-24 09:45:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911364, '22.00', '20.00', '浓缩咖啡', 'short', '浓郁的咖啡香味', '热', 3, 0, '2024-05-24 10:00:00', '2024-05-24 10:00:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911366, '28.00', '25.00', '拿铁玛奇朵', NULL, '拿铁与玛奇朵的完美结合', '', 4, 0, '2024-05-24 10:15:00', '2024-05-24 10:15:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911367, '30.00', '28.00', '焦糖玛奇朵', NULL, '香甜焦糖与浓郁咖啡的美妙组合', '', 5, 0, '2024-05-24 10:30:00', '2024-05-24 10:30:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911368, '20.00', '18.00', '香草拿铁', NULL, '香草风味与顶级牛奶的完美搭配', '', 5, 0, '2024-05-24 10:45:00', '2024-05-24 10:45:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911369, '22.00', '20.00', '焦糖拿铁', NULL, '浓郁咖啡与香甜焦糖的绝妙组合', '', 3, 0, '2024-05-24 11:00:00', '2024-05-24 11:00:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911370, '25.00', '22.00', '冰美式', NULL, '清凉解渴的美式咖啡', '', 5, 0, '2024-05-24 11:15:00', '2024-05-24 11:15:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911371, '30.00', '28.00', '冰摩卡', NULL, '浓郁巧克力与冰冻咖啡的美妙组合', '', 6, 0, '2024-05-24 11:30:00', '2024-05-24 11:30:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911372, '28.00', '25.00', '冰拿铁', NULL, '清凉爽口的拿铁咖啡', '', 6, 0, '2024-05-24 11:45:00', '2024-05-24 11:45:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911373, '18.00', '15.00', '冰卡布奇诺', NULL, '冰冻的卡布奇诺咖啡', '', 5, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911374, '20.00', '18.00', '香草冰拿铁', NULL, '清凉解渴的香草拿铁', '', 7, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_item_copy1` VALUES (1905153932640911375, '22.00', '20.00', '香草冰卡布奇诺', NULL, '清凉解渴的香草卡布奇诺', '', 6, 0, '2024-05-24 12:30:00', '2024-05-24 12:30:00');

-- ----------------------------
-- Table structure for biz_item_sku_option
-- ----------------------------
DROP TABLE IF EXISTS `biz_item_sku_option`;
CREATE TABLE `biz_item_sku_option`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spec_id` bigint(20) NOT NULL COMMENT '规格ID',
  `option_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项名称',
  `option_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项code',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级ID',
  `additional_price` decimal(10, 2) NULL COMMENT '附加价格',
  `now_disabled` tinyint(1) NULL DEFAULT 0 COMMENT '目前是否禁用',
  `sort_order` int(11) NULL DEFAULT 0 COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SKU规格选项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item_sku_option
-- ----------------------------

-- ----------------------------
-- Table structure for biz_item_sku_relation
-- ----------------------------
DROP TABLE IF EXISTS `biz_item_sku_relation`;
CREATE TABLE `biz_item_sku_relation`  (
  `id` bigint(20) NOT NULL,
  `item_id` bigint(20) NOT NULL COMMENT '产品id',
  `sku_catalog_id` bigint(20) NOT NULL COMMENT 'sku分类id',
  `display_order` tinyint(4) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品-sku关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item_sku_relation
-- ----------------------------
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942720, 11, 585315952858497025, 3);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942721, 11, 585315952858497026, 1);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942722, 11, 585315952858497024, 2);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942723, 1905153932640911360, 585315952858497025, 3);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942724, 1905153932640911360, 585315952858497026, 1);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942725, 1905153932640911360, 585315952858497024, 2);

-- ----------------------------
-- Table structure for biz_item_sku_spec
-- ----------------------------
DROP TABLE IF EXISTS `biz_item_sku_spec`;
CREATE TABLE `biz_item_sku_spec`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL COMMENT '商品ID',
  `spec_label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格名称',
  `multi` tinyint(1) NULL DEFAULT 0 COMMENT '是否多选（0:单选，1:多选）',
  `sort_order` int(11) NULL DEFAULT 0 COMMENT '排序',
  `required` tinyint(1) NULL DEFAULT 1 COMMENT '是否必选',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_item_id`(`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品SKU规格表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item_sku_spec
-- ----------------------------

-- ----------------------------
-- Table structure for biz_item_sku_stock
-- ----------------------------
DROP TABLE IF EXISTS `biz_item_sku_stock`;
CREATE TABLE `biz_item_sku_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL COMMENT '商品ID',
  `sku_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SKU编码',
  `spec_options` json NULL COMMENT '规格选项组合（JSON String）',
  `price` decimal(10, 2) NOT NULL COMMENT 'SKU价格',
  `stock` int(11) NULL DEFAULT 0 COMMENT '库存',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态（1:正常，0:下架）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_item_sku`(`item_id`, `sku_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品SKU库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item_sku_stock
-- ----------------------------

-- ----------------------------
-- Table structure for biz_menu
-- ----------------------------
DROP TABLE IF EXISTS `biz_menu`;
CREATE TABLE `biz_menu`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `menu_catalog_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单分类id',
  `item_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '门店id',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_menu
-- ----------------------------
INSERT INTO `biz_menu` VALUES (1905180724781649920, 1905169953170915328, '1905153932640911360', 593630146225766400, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_menu` VALUES (1905180724781649921, 1905169953170915328, '1905153932640911361', 593630146225766400, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_menu` VALUES (1905180724781649922, 1905169953170915329, '1905153932640911362', 593630146225766400, 0, '2024-05-24 12:30:00', '2024-05-24 12:30:00');
INSERT INTO `biz_menu` VALUES (1905180724781649923, 1905169953170915329, '1905153932640911363', 593630146225766400, 0, '2024-05-24 12:45:00', '2024-05-24 12:45:00');
INSERT INTO `biz_menu` VALUES (1905180724781649924, 1905169953170915330, '1905153932640911364', 593630146225766400, 0, '2024-05-24 13:00:00', '2024-05-24 13:00:00');
INSERT INTO `biz_menu` VALUES (1905180724781649925, 1905169953170915330, '1905153932640911365', 593630146225766400, 0, '2024-05-24 13:15:00', '2024-05-24 13:15:00');
INSERT INTO `biz_menu` VALUES (1905180724781649926, 1905169953170915331, '1905153932640911366', 593630146225766400, 0, '2024-05-24 13:30:00', '2024-05-24 13:30:00');
INSERT INTO `biz_menu` VALUES (1905180724781649927, 1905169953170915331, '1905153932640911367', 593630146225766400, 0, '2024-05-24 13:45:00', '2024-05-24 13:45:00');

-- ----------------------------
-- Table structure for biz_menu_catalog
-- ----------------------------
DROP TABLE IF EXISTS `biz_menu_catalog`;
CREATE TABLE `biz_menu_catalog`  (
  `id` bigint(20) NOT NULL,
  `menu_catalog_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单分类名称',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '关联门店id',
  `show_index` int(11) NULL DEFAULT NULL COMMENT '排序',
  `show_side` tinyint(1) NULL DEFAULT NULL COMMENT '点单页显示',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店菜单分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_menu_catalog
-- ----------------------------
INSERT INTO `biz_menu_catalog` VALUES (585316296787234001, '猜你喜欢', 593630146225766401, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452611987358875648, '夏日饮', 593630146225766401, 2, 0, '2025-12-22 10:41:26', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452612020267384832, '冬日暖茶', 593630146225766401, 3, 0, '2025-12-22 10:41:34', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452612214916644864, '拿铁', 593630146225766401, 4, 1, '2025-12-22 10:42:20', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452612292418994176, '招牌咖啡', 593630146225766401, 5, 1, '2025-12-22 10:42:39', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452694356426227712, '猜你喜欢', 593630146225766400, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452694356744994816, '夏日饮', 593630146225766400, 2, 0, '2025-12-22 10:41:26', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452694356975681536, '冬日暖茶', 593630146225766400, 3, 0, '2025-12-22 10:41:34', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452694357160230912, '拿铁', 593630146225766400, 4, 1, '2025-12-22 10:42:20', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452694357265088512, '招牌咖啡', 593630146225766400, 5, 1, '2025-12-22 10:42:39', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452698947205201920, '猜你喜欢', 593630146225766402, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452698947343613952, '夏日饮', 593630146225766402, 2, 0, '2025-12-22 10:41:26', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452698947444277248, '冬日暖茶', 593630146225766402, 3, 0, '2025-12-22 10:41:34', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452698947561717760, '拿铁', 593630146225766402, 4, 1, '2025-12-22 10:42:20', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452698947767238656, '招牌咖啡', 593630146225766402, 5, 1, '2025-12-22 10:42:39', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452699933156048896, '猜你喜欢', 593630146225766403, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452699933302849536, '夏日饮', 593630146225766403, 2, 0, '2025-12-22 10:41:26', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452699933550313472, '冬日暖茶', 593630146225766403, 3, 0, '2025-12-22 10:41:34', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452699933659365376, '拿铁', 593630146225766403, 4, 1, '2025-12-22 10:42:20', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452699934103961600, '招牌咖啡', 593630146225766403, 5, 1, '2025-12-22 10:42:39', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452700136290385920, '猜你喜欢', 593630146225766404, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452700497029890048, '猜你喜欢', 593630146225766405, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452700546816278528, '夏日饮', 593630146225766405, 2, 0, '2025-12-22 10:41:26', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452700565300576256, '冬日暖茶', 593630146225766405, 3, 0, '2025-12-22 10:41:34', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452700587920457728, '拿铁', 593630146225766405, 4, 1, '2025-12-22 10:42:20', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452700606165680128, '招牌咖啡', 593630146225766405, 5, 1, '2025-12-22 10:42:39', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452701093250203648, '猜你喜欢', 1451946725248860160, 1, 1, '2025-12-20 23:49:05', '2025-12-21 00:43:27', 0);
INSERT INTO `biz_menu_catalog` VALUES (1452701093438947328, '夏日饮', 1451946725248860160, 2, 0, '2025-12-22 10:41:26', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452701093568970752, '冬日暖茶', 1451946725248860160, 3, 0, '2025-12-22 10:41:34', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452701093891932160, '拿铁', 1451946725248860160, 4, 1, '2025-12-22 10:42:20', NULL, 0);
INSERT INTO `biz_menu_catalog` VALUES (1452701094185533440, '招牌咖啡', 1451946725248860160, 5, 1, '2025-12-22 10:42:39', NULL, 0);

-- ----------------------------
-- Table structure for biz_package
-- ----------------------------
DROP TABLE IF EXISTS `biz_package`;
CREATE TABLE `biz_package`  (
  `id` bigint(20) NOT NULL,
  `items` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包含菜品id',
  `package_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `package_origin_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐原价',
  `package_actually_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐现价',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户购物袋表（过时）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_package
-- ----------------------------

-- ----------------------------
-- Table structure for biz_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `biz_pay_order`;
CREATE TABLE `biz_pay_order`  (
  `id` bigint(20) NOT NULL,
  `pay_order_id` bigint(20) NULL DEFAULT NULL COMMENT '支付订单id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '所属门店',
  `order_type` tinyint(4) NULL DEFAULT NULL COMMENT '0-单点 1-套餐',
  `pick_type` tinyint(4) NULL DEFAULT NULL COMMENT '0-堂食 1-打包 2-外送',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '订单类型 1-支付 2-退款',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `origin_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '原金额',
  `item_info` json NULL COMMENT '菜品信息',
  `item_piece` int(11) NULL DEFAULT NULL COMMENT '菜品统计',
  `wait_time` int(11) NULL DEFAULT NULL COMMENT '预计等待时间',
  `pay_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式code',
  `out_trade_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外部交易流水号',
  `refund_out_trade_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款交易流水号',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `refund_order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款订单号',
  `shop_order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店内用餐号',
  `order_status` tinyint(4) NULL DEFAULT NULL COMMENT '0-初始化 1-进行中 2-支付 3-退单 4-过期取消 ',
  `pay_notify_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付业务通知信息',
  `refund_notify_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款业务通知信息',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `user_del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '用户删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `settle_time` datetime(0) NULL DEFAULT NULL COMMENT '结算时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_pay_order
-- ----------------------------
INSERT INTO `biz_pay_order` VALUES (1355213837254524928, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1354863291515535360, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, 'H001', NULL, NULL, 4, NULL, NULL, NULL, 0, '2025-03-28 16:15:57', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355215244753895424, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1354863291515535360, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, 'H002', NULL, NULL, 4, NULL, NULL, NULL, 0, '2025-03-28 16:21:33', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355215797613494272, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1354863291515535360, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, 'H003', NULL, NULL, 4, NULL, NULL, NULL, 0, '2025-03-28 16:23:45', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355217180412608512, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 0.10, 12.00, '[{\"id\": 1355216034776219648, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, 'H004', NULL, NULL, 4, NULL, NULL, NULL, 0, '2025-03-28 16:29:15', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355217869444481024, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1355217826901655552, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, 'H005', NULL, NULL, 0, NULL, NULL, NULL, 0, '2025-03-28 16:31:59', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355228297297920000, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1354863291515535360, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1355228297277083648', NULL, 'H006', 0, NULL, NULL, NULL, 0, '2025-03-28 17:13:25', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355228738182184960, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1354863291515535360, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1355228738182320128', NULL, 'H007', 0, NULL, NULL, NULL, 0, '2025-03-28 17:15:10', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355229085273423872, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1355229066256449536, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1355229085269364736', NULL, 'H008', 4, NULL, NULL, NULL, 0, '2025-03-28 17:16:33', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1355233204067368960, NULL, 1350072554156457984, 593630146225766400, 0, 0, NULL, 12.00, 12.00, '[{\"id\": 1355231885017481216, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1355233204063309824', NULL, 'H009', 4, NULL, NULL, NULL, 0, '2025-03-28 17:32:55', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1359127843551838208, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1359127589469290496, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1359127843522613248', NULL, 'H001', 4, NULL, NULL, NULL, 0, '2025-04-08 11:28:49', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1359479535514419200, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 15.00, 15.00, '[{\"id\": 1359479500798164992, \"itemId\": 1905153932640911361, \"itemName\": \"卡布奇诺\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"\", \"singleActuallyAmount\": 15}]', 1, 2, 'P001', NULL, NULL, '1359479535384530944', NULL, 'H001', 4, NULL, NULL, NULL, 0, '2025-04-09 10:46:19', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1359487791276228608, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 15.00, 15.00, '[{\"id\": 1359487654097321984, \"itemId\": 1905153932640911361, \"itemName\": \"卡布奇诺\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"\", \"singleActuallyAmount\": 15}]', 1, 2, 'P001', NULL, NULL, '1359487791247003648', NULL, 'H002', 2, NULL, NULL, '{\"actualDispose\":13.12,\"giftDispose\":1.88}', 0, '2025-04-09 11:19:08', NULL, '2025-04-09 11:19:12');
INSERT INTO `biz_pay_order` VALUES (1359488948556005376, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1359488900766105600, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1359488948551946240', NULL, 'H003', 2, NULL, NULL, '{\"actualDispose\":12.00,\"giftDispose\":0}', 0, '2025-04-09 11:23:43', NULL, '2025-04-09 11:23:48');
INSERT INTO `biz_pay_order` VALUES (1359489379201974272, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1359489360671539200, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1359489379202109440', NULL, 'H004', 2, NULL, NULL, '{\"actualDispose\":12.00,\"giftDispose\":0}', 0, '2025-04-09 11:25:26', NULL, '2025-04-09 11:25:28');
INSERT INTO `biz_pay_order` VALUES (1359489815510253568, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1359489796287758336, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1359489815506194432', NULL, 'H005', 2, NULL, NULL, '{\"actualDispose\":12.00,\"giftDispose\":0}', 0, '2025-04-09 11:27:10', NULL, '2025-04-09 11:27:12');
INSERT INTO `biz_pay_order` VALUES (1359492395959320576, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1359492381170204672, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1359492395959455744', NULL, 'H006', 2, NULL, NULL, '{\"actualDispose\":12.00,\"giftDispose\":0}', 0, '2025-04-09 11:37:25', NULL, '2025-04-09 11:37:28');
INSERT INTO `biz_pay_order` VALUES (1359492566919151616, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1359492381170204672, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1359492566889926656', NULL, 'H007', 2, NULL, NULL, '{\"actualDispose\":12.00,\"giftDispose\":0}', 0, '2025-04-09 11:38:06', NULL, '2025-04-09 11:38:10');
INSERT INTO `biz_pay_order` VALUES (1361644104991113216, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1361643952368779264, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1361644104911556608', NULL, 'H001', 4, NULL, NULL, NULL, 0, '2025-04-15 10:07:33', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1387743464690548736, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1387742934744432640, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1387743464497745920', NULL, 'H001', 4, NULL, NULL, NULL, 0, '2025-06-26 10:37:05', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1387744065776254976, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1387743995970453504, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1387744065776390144', NULL, 'H002', 4, NULL, NULL, NULL, 0, '2025-06-26 10:39:28', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1387753512649097216, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1387743995970453504, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1387753512645038080', NULL, 'H001', 4, NULL, NULL, NULL, 0, '2025-06-26 11:17:01', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1387753864639283200, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1387743995970453504, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1387753864639418368', NULL, 'H002', 4, NULL, NULL, NULL, 0, '2025-06-26 11:18:25', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1387757446876889088, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1387757423174877184, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1387757446856052736', NULL, 'H003', 2, NULL, NULL, '2', 0, '2025-06-26 11:32:39', NULL, '2025-06-26 11:32:41');
INSERT INTO `biz_pay_order` VALUES (1387758201797083136, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1387758183656718336, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"全糖,多奶,热\", \"singleActuallyAmount\": 12}]', 1, 2, NULL, NULL, NULL, '1387758201797218304', NULL, 'H004', 0, NULL, NULL, NULL, 0, '2025-06-26 11:35:39', NULL, NULL);
INSERT INTO `biz_pay_order` VALUES (1388119747983310848, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1388118910657626112, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"少冰\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1388119747870199808', NULL, 'H001', 2, NULL, NULL, '2', 0, '2025-06-27 11:32:18', NULL, '2025-06-27 11:49:31');
INSERT INTO `biz_pay_order` VALUES (1388125276910649344, NULL, 1350072554156457984, 593630146225766400, 0, 0, 1, 12.00, 12.00, '[{\"id\": 1388118910657626112, \"itemId\": 1905153932640911360, \"itemName\": \"拿铁\", \"itemPiece\": 1, \"expectMakeTime\": 2, \"skuItemContents\": \"少冰\", \"singleActuallyAmount\": 12}]', 1, 2, 'P001', NULL, NULL, '1388125276885618688', NULL, 'H002', 6, NULL, NULL, '2', 0, '2025-06-27 11:54:16', '2025-06-27 12:00:33', '2025-06-27 11:54:26');

-- ----------------------------
-- Table structure for biz_pay_way
-- ----------------------------
DROP TABLE IF EXISTS `biz_pay_way`;
CREATE TABLE `biz_pay_way`  (
  `id` bigint(20) NOT NULL,
  `pay_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式编码',
  `pay_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易地址',
  `refund_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款地址',
  `query_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '查询地址',
  `payment_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交易系统名称',
  `display_order` tinyint(4) NULL DEFAULT NULL COMMENT '展示排序',
  `pack_up` tinyint(4) NULL DEFAULT 0 COMMENT '展示收起 0-不收起 1-收起',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标地址',
  `is_usable` tinyint(4) NULL DEFAULT NULL COMMENT '是否在用',
  `support_platform` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支持的平台（枚举）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付方式表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_pay_way
-- ----------------------------
INSERT INTO `biz_pay_way` VALUES (593698122811576324, 'P006', 'http://test.com', 'http://test.com', 'http://test.com', '北京工商银行分行', 3, 0, '/hutu-order/web/upload/static/1905087645869137921.png', 0, 'WEB');
INSERT INTO `biz_pay_way` VALUES (593698122811576325, 'P008', 'http://test.com', 'http://test.com', 'http://test.com', '北京农业银行分行', 3, 0, '/hutu-order/web/upload/static/1905087645869137921.png', 0, 'WEB');
INSERT INTO `biz_pay_way` VALUES (593698122811576326, 'P007', 'http://test.com', 'http://test.com', 'http://test.com', '北京交通银行分行', 3, 0, '/hutu-order/web/upload/static/1905087645869137921.png', 0, 'WEB');
INSERT INTO `biz_pay_way` VALUES (593698122811579901, 'P005', 'http://ali.pay.com', 'http://ali.pay.com', 'http://ali.pay.com', '支付宝小程序支付', 1, 0, '/hutu-order/web/upload/static/1905087708829835265.png', 1, 'MP-ALIPAY');
INSERT INTO `biz_pay_way` VALUES (593698122811579991, 'P004', 'http://weixin.pay.com', 'http://weixin.pay.com', 'http://weixin.pay.com', '微信小程序支付', 1, 0, '/hutu-order/web/upload/static/1905087738336763905.png', 1, 'MP-WEIXIN');
INSERT INTO `biz_pay_way` VALUES (593698122811579992, 'P003', 'http://weixin.pay.com', 'http://weixin.pay.com', 'http://weixin.pay.com', '微信支付', 1, 0, '/hutu-order/web/upload/static/1905087738336763905.png', 1, 'WEB');
INSERT INTO `biz_pay_way` VALUES (593698122811579993, 'P002', 'http://ali.pay.com', 'http://ali.pay.com', 'http://ali.pay.com', '支付宝支付', 2, 0, '/hutu-order/web/upload/static/1905087708829835265.png', 1, 'WEB');
INSERT INTO `biz_pay_way` VALUES (593698122811579994, 'P001', '', '', '', '余额支付', 0, 0, '/hutu-order/web/upload/static/1905087679075442689.png', 1, 'ALL');

-- ----------------------------
-- Table structure for biz_play_image
-- ----------------------------
DROP TABLE IF EXISTS `biz_play_image`;
CREATE TABLE `biz_play_image`  (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片相对地址',
  `navigation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `available_start_time` datetime(0) NULL DEFAULT NULL COMMENT '可用开始时间',
  `available_end_time` datetime(0) NULL DEFAULT NULL COMMENT '可用结束时间',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型 0-首页轮播 1-活动轮播 2-活动卡片',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_play_image
-- ----------------------------

-- ----------------------------
-- Table structure for biz_product
-- ----------------------------
DROP TABLE IF EXISTS `biz_product`;
CREATE TABLE `biz_product`  (
  `id` bigint(20) NOT NULL,
  `cover_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品封面',
  `item_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '价格',
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `show_index` int(11) NULL DEFAULT NULL COMMENT '排序',
  `item_description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `expect_make_time` int(20) NULL DEFAULT NULL COMMENT '预计制作时长，单位：分',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_product
-- ----------------------------
INSERT INTO `biz_product` VALUES (1453066698889363456, '/static/1453417441274101760.png', '1.024', 'Linux小蛋糕', NULL, '来自某个常来店里的程序员推荐，看起来很无聊的蛋糕。', 1024, 0, '2025-12-23 16:48:18', '2025-12-23 16:58:02');
INSERT INTO `biz_product` VALUES (1453420493280378880, '/static/1453420472531156992.png', '1', '123', NULL, '1', 1, 1, '2025-12-24 16:14:09', '2025-12-24 16:15:01');
INSERT INTO `biz_product` VALUES (1453420673350238208, '/static/1453420472531156992.png', '1', '123', NULL, '1', 1, 0, '2025-12-24 16:14:52', NULL);
INSERT INTO `biz_product` VALUES (1453696751528574976, '/static/1453696653209894912.png', '1', '下界合金小蛋糕', NULL, '某个热门游戏的同款周边蛋糕。', 9, 0, '2025-12-25 10:31:54', '2025-12-25 10:33:29');
INSERT INTO `biz_product` VALUES (1905153932640911360, '/static/1905157654465122306.png', '12.00', '拿铁', NULL, '优质咖啡豆搭配顶级牛奶', 2, 0, '2024-05-24 09:00:00', '2024-05-24 09:00:00');
INSERT INTO `biz_product` VALUES (1905153932640911361, '/static/1905157654465122306.png', '15.00', '卡布奇诺', NULL, '意式浓缩咖啡与丝滑牛奶的完美结合', 2, 0, '2024-05-24 09:15:00', '2024-05-24 09:15:00');
INSERT INTO `biz_product` VALUES (1905153932640911362, '/static/1905157654465122306.png', '18.00', '摩卡', NULL, '香浓巧克力、浓郁咖啡与奶泡的完美融合', 2, 0, '2024-05-24 09:30:00', '2024-05-24 09:30:00');
INSERT INTO `biz_product` VALUES (1905153932640911363, '/static/1905157654465122306.png', '22.00', '美式咖啡', NULL, '纯粹的咖啡香味', 2, 0, '2024-05-24 09:45:00', '2024-05-24 09:45:00');
INSERT INTO `biz_product` VALUES (1905153932640911364, '/static/1905157654465122306.png', '20.00', '浓缩咖啡', NULL, '浓郁的咖啡香味', 3, 0, '2024-05-24 10:00:00', '2024-05-24 10:00:00');
INSERT INTO `biz_product` VALUES (1905153932640911366, '/static/1905157654465122306.png', '25.00', '拿铁玛奇朵', NULL, '拿铁与玛奇朵的完美结合', 4, 0, '2024-05-24 10:15:00', '2024-05-24 10:15:00');
INSERT INTO `biz_product` VALUES (1905153932640911367, '/static/1905157654465122306.png', '28.00', '焦糖玛奇朵', NULL, '香甜焦糖与浓郁咖啡的美妙组合', 5, 0, '2024-05-24 10:30:00', '2024-05-24 10:30:00');
INSERT INTO `biz_product` VALUES (1905153932640911368, '/static/1905157500521582594.png', '18.00', '香草拿铁', NULL, '香草风味与顶级牛奶的完美搭配', 5, 0, '2024-05-24 10:45:00', '2024-05-24 10:45:00');
INSERT INTO `biz_product` VALUES (1905153932640911369, '/static/1905157500521582594.png', '20.00', '焦糖拿铁', NULL, '浓郁咖啡与香甜焦糖的绝妙组合', 3, 0, '2024-05-24 11:00:00', '2024-05-24 11:00:00');
INSERT INTO `biz_product` VALUES (1905153932640911370, '/static/1905157500521582594.png', '22.00', '冰美式', NULL, '清凉解渴的美式咖啡', 5, 0, '2024-05-24 11:15:00', '2024-05-24 11:15:00');
INSERT INTO `biz_product` VALUES (1905153932640911371, '/static/1905157500521582594.png', '28.00', '冰摩卡', NULL, '浓郁巧克力与冰冻咖啡的美妙组合', 6, 0, '2024-05-24 11:30:00', '2024-05-24 11:30:00');
INSERT INTO `biz_product` VALUES (1905153932640911372, '/static/1905157500521582594.png', '25.00', '冰拿铁', NULL, '清凉爽口的拿铁咖啡', 6, 0, '2024-05-24 11:45:00', '2024-05-24 11:45:00');
INSERT INTO `biz_product` VALUES (1905153932640911373, '/static/1905157500521582594.png', '15.00', '冰卡布奇诺', NULL, '冰冻的卡布奇诺咖啡', 5, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_product` VALUES (1905153932640911374, '/static/1905157500521582594.png', '18.00', '香草冰拿铁', NULL, '清凉解渴的香草拿铁', 7, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_product` VALUES (1905153932640911375, '/static/1905157500521582594.png', '20.00', '香草冰卡布奇诺', NULL, '清凉解渴的香草卡布奇诺', 6, 0, '2024-05-24 12:30:00', '2024-05-24 12:30:00');

-- ----------------------------
-- Table structure for biz_shop_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_shop_info`;
CREATE TABLE `biz_shop_info`  (
  `id` bigint(20) NOT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `start_business_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业时间',
  `end_business_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打烊时间',
  `close_now` tinyint(4) NULL DEFAULT 0 COMMENT '是否店休',
  `longitude` decimal(10, 10) NULL DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10, 10) NULL DEFAULT NULL COMMENT '纬度',
  `week_start_date` tinyint(4) NULL DEFAULT NULL COMMENT '每周营业开始日',
  `week_end_date` tinyint(4) NULL DEFAULT NULL COMMENT '每周营业结束日',
  `main_shop` tinyint(4) NULL DEFAULT NULL COMMENT '是否主店',
  `cover_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图',
  `swipe_image` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '轮播图',
  `page_style` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '点单页面显示样式',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_shop_info
-- ----------------------------
INSERT INTO `biz_shop_info` VALUES (593630146225766400, '糊涂餐馆（齐河路店）', '上海市齐河路28弄31号303室', '13092621512', '08:00', '18:00', 0, NULL, NULL, 4, 5, 1, '', NULL, '	.body{\r\n		padding: 20px 22px;\r\n		padding-bottom: 10px;\r\n		background-size: 100% 30%;\r\n		background-attachment: fixed;\r\n	}\r\n	.header-card {\r\n		padding: 2px;\r\n	}\r\n	\r\n	.header-card text{\r\n		display: block;\r\n	}\r\n	\r\n	.shop-name{\r\n		fontSize: 16px;\r\n		fontWeight: 600;\r\n	}\r\n	\r\n	.shop-info-card{\r\n		display: flex;\r\n		flexDirection: row;\r\n		justifyContent: space-between;\r\n		width: 100%;\r\n	}\r\n	\r\n	.right text{\r\n		fontSize: 24px;\r\n		fontWeight: 500;\r\n	}\r\n	\r\n	.address{\r\n		marginTop: 40px;\r\n	}\r\n	\r\n	.address,.time{\r\n		/* color: grey; */\r\n	}\r\n	\r\n	text{\r\n		color: white;\r\n	}', NULL, '2025-12-20 15:23:57', 0);
INSERT INTO `biz_shop_info` VALUES (593630146225766401, '糊涂餐馆（杨铭园店）', '赤岗冲红花坡杨铭园C栋', '13063855026', '09:00', '17:00', 1, NULL, NULL, 1, 5, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_shop_info` VALUES (593630146225766402, '糊涂餐馆（村水云居店）', '余家湖村水云居A栋2单元404室', '13013802811', '11:00', '20:00', 0, NULL, NULL, 1, 5, NULL, NULL, NULL, NULL, NULL, '2025-12-20 14:46:22', 0);
INSERT INTO `biz_shop_info` VALUES (593630146225766403, '糊涂餐馆（中胜街店）', '中胜街54-2号392', '13092626359', '08:00', '16:00', 0, NULL, NULL, 1, 7, NULL, NULL, NULL, NULL, NULL, '2025-12-20 14:46:26', 0);
INSERT INTO `biz_shop_info` VALUES (593630146225766404, '糊涂餐馆（秋景怡园店）', '秋景怡园5-3-6040', '13063726873', '09:30', '17:30', 0, NULL, NULL, 1, 7, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_shop_info` VALUES (593630146225766405, '糊涂餐馆（霄云路店）', '霄云路霄云里7号楼', '13092626359', '10:30', '19:30', 0, NULL, NULL, 1, 7, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `biz_shop_info` VALUES (1451941798258671616, '糊涂咖啡一分店', '香港特别行政区油尖旺区', '', '08:00', '14:00', 0, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, NULL, '2025-12-20 15:24:13', 1);
INSERT INTO `biz_shop_info` VALUES (1451946725248860160, '糊涂咖啡香港二店', '香港特别行政区屯门区艾迪森路101', '899435', '14:36', '14:00', 0, NULL, NULL, 1, 4, 0, NULL, NULL, NULL, '2025-12-20 14:37:55', '2025-12-20 14:46:34', 0);
INSERT INTO `biz_shop_info` VALUES (1452094697592848384, '测试1', '测试', '123', '00:25', '00:26', 0, NULL, NULL, 1, 3, 0, NULL, NULL, NULL, '2025-12-21 00:25:55', '2025-12-21 00:38:08', 1);

-- ----------------------------
-- Table structure for biz_sku_catalog
-- ----------------------------
DROP TABLE IF EXISTS `biz_sku_catalog`;
CREATE TABLE `biz_sku_catalog`  (
  `id` bigint(20) NOT NULL,
  `sku_catalog_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku分类名称',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '关联门店id',
  `show_index` int(11) NULL DEFAULT NULL COMMENT '排序',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_sku_catalog
-- ----------------------------
INSERT INTO `biz_sku_catalog` VALUES (585315952858497024, '甜度', 593630146225766401, 1, NULL, '2024-12-20 02:00:59', NULL, 0);
INSERT INTO `biz_sku_catalog` VALUES (585315952858497025, '就餐', 593630146225766401, 2, NULL, '2024-05-20 02:01:04', NULL, 0);
INSERT INTO `biz_sku_catalog` VALUES (585315952858497026, '温度', 593630146225766403, 3, NULL, '2024-10-20 02:01:09', NULL, 0);

-- ----------------------------
-- Table structure for biz_sku_catalog_relation
-- ----------------------------
DROP TABLE IF EXISTS `biz_sku_catalog_relation`;
CREATE TABLE `biz_sku_catalog_relation`  (
  `id` bigint(20) NOT NULL,
  `sku_catalog_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku分类id',
  `sku_item_id` bigint(20) NULL DEFAULT NULL COMMENT 'sku小项id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku-子项关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_sku_catalog_relation
-- ----------------------------
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230942999, 585315952858497024, 585316296787234817);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943000, 585315952858497024, 585316296787234818);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943001, 585315952858497024, 585316296787234819);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943002, 585315952858497024, 585316296787234820);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943003, 585315952858497025, 585316296787234822);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943004, 585315952858497025, 585316296787234823);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943007, 585315952858497026, 585316296787234825);
INSERT INTO `biz_sku_catalog_relation` VALUES (585317148230943008, 585315952858497026, 585316296787234826);

-- ----------------------------
-- Table structure for biz_sku_item
-- ----------------------------
DROP TABLE IF EXISTS `biz_sku_item`;
CREATE TABLE `biz_sku_item`  (
  `id` bigint(20) NOT NULL,
  `sku_item_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku项目正文',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku子项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_sku_item
-- ----------------------------
INSERT INTO `biz_sku_item` VALUES (585316296787234817, '全糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234818, '少糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234819, '五分糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234820, '三分糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234821, '无糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234822, '多奶', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234823, '少奶', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234824, '常温', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234825, '热', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234826, '冰', 0, '2024-06-03 11:58:21', NULL);

-- ----------------------------
-- Table structure for biz_sku_item_copy1
-- ----------------------------
DROP TABLE IF EXISTS `biz_sku_item_copy1`;
CREATE TABLE `biz_sku_item_copy1`  (
  `id` bigint(20) NOT NULL,
  `sku_item_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku项目正文',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku子项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_sku_item_copy1
-- ----------------------------
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234817, '全糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234818, '少糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234819, '五分糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234820, '三分糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234821, '无糖', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234822, '多奶', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234823, '少奶', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234824, '常温', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234825, '热', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item_copy1` VALUES (585316296787234826, '冰', 0, '2024-06-03 11:58:21', NULL);

-- ----------------------------
-- Table structure for biz_table_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_table_info`;
CREATE TABLE `biz_table_info`  (
  `id` bigint(20) NOT NULL,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桌面信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店桌台信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_table_info
-- ----------------------------

-- ----------------------------
-- Table structure for biz_user
-- ----------------------------
DROP TABLE IF EXISTS `biz_user`;
CREATE TABLE `biz_user`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信/支付宝 openid',
  `login_count` int(11) NULL DEFAULT NULL COMMENT '登录次数',
  `regist_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `last_log_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `actual_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际余额',
  `gift_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '赠送余额',
  `point` bigint(20) NULL DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_user
-- ----------------------------
INSERT INTO `biz_user` VALUES (1350072554156457984, '测试人士1', '13092626359', 1, '/hutu-order/web/upload/static/1905081305499164674.jpg', NULL, 1, '2025-03-14 11:46:20', '2025-06-27 11:24:05', 179.76, 0.00, 0);

-- ----------------------------
-- Table structure for biz_user_coupon_relation
-- ----------------------------
DROP TABLE IF EXISTS `biz_user_coupon_relation`;
CREATE TABLE `biz_user_coupon_relation`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `coupon_id` bigint(20) NULL DEFAULT NULL COMMENT '优惠券id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-优惠券关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_user_coupon_relation
-- ----------------------------
INSERT INTO `biz_user_coupon_relation` VALUES (613298625304334349, 1258018376433795072, 613298191621689345);

-- ----------------------------
-- Table structure for biz_user_package
-- ----------------------------
DROP TABLE IF EXISTS `biz_user_package`;
CREATE TABLE `biz_user_package`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `item_id` bigint(20) NULL DEFAULT NULL COMMENT '产品id',
  `item_piece` int(11) NULL DEFAULT NULL COMMENT '选购件数',
  `sku_item_contents` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选购规格，以逗号分隔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户购物袋表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_user_package
-- ----------------------------
INSERT INTO `biz_user_package` VALUES (1387759568808837120, 1350072554156457984, 1905153932640911360, 1, '全糖,多奶,热');

-- ----------------------------
-- Table structure for biz_user_point_log
-- ----------------------------
DROP TABLE IF EXISTS `biz_user_point_log`;
CREATE TABLE `biz_user_point_log`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `biz_fee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务金额',
  `point` bigint(20) NULL DEFAULT NULL COMMENT '添加积分',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '使用类型 0-主动 1-系统扣除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户积分日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_user_point_log
-- ----------------------------

-- ----------------------------
-- Table structure for oss_file
-- ----------------------------
DROP TABLE IF EXISTS `oss_file`;
CREATE TABLE `oss_file`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `origin_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `sort_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `file_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储名称',
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '访问路径',
  `biz_id` bigint(20) NULL DEFAULT NULL COMMENT '业务id',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oss_file
-- ----------------------------
INSERT INTO `oss_file` VALUES (1453070697562636288, 'wallpaper2you_179920.jpg', NULL, '1453070697562636288.jpg', '/static/1453070697562636288.jpg', NULL, 'jpg', '2025-12-23 17:04:11', NULL, 0);
INSERT INTO `oss_file` VALUES (1453070729464512512, 'wallpaper2you_179920.jpg', NULL, '1453070729464512512.jpg', '/static/1453070729464512512.jpg', NULL, 'jpg', '2025-12-23 17:04:19', NULL, 0);
INSERT INTO `oss_file` VALUES (1453070960453222400, 'wallpaper2you_179920.jpg', NULL, '1453070960453222400.jpg', '/static/1453070960453222400.jpg', NULL, 'jpg', '2025-12-23 17:05:14', NULL, 0);
INSERT INTO `oss_file` VALUES (1453071393825488896, 'wallpaper2you_179920.jpg', NULL, '1453071393825488896.jpg', '/static/1453071393825488896.jpg', NULL, 'jpg', '2025-12-23 17:06:57', NULL, 0);
INSERT INTO `oss_file` VALUES (1453071891395772416, 'wallpaper2you_179920.jpg', NULL, '1453071891395772416.jpg', '/static/1453071891395772416.jpg', NULL, 'jpg', '2025-12-23 17:08:56', NULL, 0);
INSERT INTO `oss_file` VALUES (1453071910836371456, 'wallpaper2you_179920.jpg', NULL, '1453071910836371456.jpg', '/static/1453071910836371456.jpg', NULL, 'jpg', '2025-12-23 17:09:00', NULL, 0);
INSERT INTO `oss_file` VALUES (1453071956520730624, 'wallpaper2you_179920.jpg', NULL, '1453071956520730624.jpg', '/static/1453071956520730624.jpg', NULL, 'jpg', '2025-12-23 17:09:11', NULL, 0);
INSERT INTO `oss_file` VALUES (1453417441274101760, 'minecraft_title.png', NULL, '1453417441274101760.png', '/static/1453417441274101760.png', NULL, 'png', '2025-12-24 16:02:01', NULL, 0);
INSERT INTO `oss_file` VALUES (1453420443779203072, '首页.png', NULL, '1453420443779203072.png', '/static/1453420443779203072.png', NULL, 'png', '2025-12-24 16:13:57', NULL, 0);
INSERT INTO `oss_file` VALUES (1453420457481994240, 'call.png', NULL, '1453420457481994240.png', '/static/1453420457481994240.png', NULL, 'png', '2025-12-24 16:14:00', NULL, 0);
INSERT INTO `oss_file` VALUES (1453420472531156992, '首页 (2).png', NULL, '1453420472531156992.png', '/static/1453420472531156992.png', NULL, 'png', '2025-12-24 16:14:04', NULL, 0);
INSERT INTO `oss_file` VALUES (1453423141953994752, '删除.png', NULL, '1453423141953994752.png', '/static/1453423141953994752.png', NULL, 'png', '2025-12-24 16:24:40', NULL, 0);
INSERT INTO `oss_file` VALUES (1453696653209894912, '64px-Netherite_Ingot_BE1.png', NULL, '1453696653209894912.png', '/static/1453696653209894912.png', NULL, 'png', '2025-12-25 10:31:31', NULL, 0);
INSERT INTO `oss_file` VALUES (1453697178802323456, 'wallpaper2you_179920.jpg', NULL, '1453697178802323456.jpg', '/static/1453697178802323456.jpg', NULL, 'jpg', '2025-12-25 10:33:36', NULL, 0);
INSERT INTO `oss_file` VALUES (1453697562132348928, '64px-Netherite_Ingot_BE1.png', NULL, '1453697562132348928.png', '/static/1453697562132348928.png', NULL, 'png', '2025-12-25 10:35:07', NULL, 0);
INSERT INTO `oss_file` VALUES (1453697667828809728, '64px-Netherite_Ingot_BE1.png', NULL, '1453697667828809728.png', '/static/1453697667828809728.png', NULL, 'png', '2025-12-25 10:35:32', NULL, 0);
INSERT INTO `oss_file` VALUES (1453697717816524800, '64px-Netherite_Ingot_BE1.png', NULL, '1453697717816524800.png', '/static/1453697717816524800.png', NULL, 'png', '2025-12-25 10:35:44', NULL, 0);
INSERT INTO `oss_file` VALUES (1453698181098373120, '64px-Netherite_Ingot_BE1.png', NULL, '1453698181098373120.png', '/static/1453698181098373120.png', NULL, 'png', '2025-12-25 10:37:35', NULL, 0);
INSERT INTO `oss_file` VALUES (1453698602839834624, '64px-Netherite_Ingot_BE1.png', 1, '1453698602839834624.png', '/static/1453698602839834624.png', 1453696751528574976, 'png', '2025-12-25 10:39:15', '2025-12-25 11:12:09', 1);
INSERT INTO `oss_file` VALUES (1453702436416913408, 'wallpaper2you_179920.jpg', 2, '1453702436416913408.jpg', '/static/1453702436416913408.jpg', 1453696751528574976, 'jpg', '2025-12-25 10:54:29', '2025-12-25 11:12:09', 1);
INSERT INTO `oss_file` VALUES (1453702630604800000, '64px-Netherite_Ingot_BE1.png', 3, '1453702630604800000.png', '/static/1453702630604800000.png', 1453696751528574976, 'png', '2025-12-25 10:55:16', '2025-12-25 11:12:09', 1);
INSERT INTO `oss_file` VALUES (1453702642269159424, 'wallpaper2you_179920.jpg', 4, '1453702642269159424.jpg', '/static/1453702642269159424.jpg', 1453696751528574976, 'jpg', '2025-12-25 10:55:18', '2025-12-25 11:12:09', 1);
INSERT INTO `oss_file` VALUES (1453707670006530048, 'wallpaper2you_179920.jpg', NULL, '1453707670006530048.jpg', '/static/1453707670006530048.jpg', NULL, 'jpg', '2025-12-25 11:15:17', NULL, 0);
INSERT INTO `oss_file` VALUES (1453707821076971520, 'wallpaper2you_179920.jpg', NULL, '1453707821076971520.jpg', '/static/1453707821076971520.jpg', NULL, 'jpg', '2025-12-25 11:15:53', NULL, 0);
INSERT INTO `oss_file` VALUES (1453707905902575616, 'wallpaper2you_179920.jpg', NULL, '1453707905902575616.jpg', '/static/1453707905902575616.jpg', NULL, 'jpg', '2025-12-25 11:16:13', NULL, 0);
INSERT INTO `oss_file` VALUES (1453708369255727104, '64px-Netherite_Ingot_BE1.png', NULL, '1453708369255727104.png', '/static/1453708369255727104.png', NULL, 'png', '2025-12-25 11:18:04', NULL, 0);
INSERT INTO `oss_file` VALUES (1453710296236425216, '64px-Netherite_Ingot_BE1.png', NULL, '1453710296236425216.png', '/static/1453710296236425216.png', NULL, 'png', '2025-12-25 11:25:43', NULL, 0);
INSERT INTO `oss_file` VALUES (1453710974199529472, '64px-Netherite_Ingot_BE1.png', NULL, '1453710974199529472.png', '/static/1453710974199529472.png', NULL, 'png', '2025-12-25 11:28:25', NULL, 0);
INSERT INTO `oss_file` VALUES (1453711348994146304, 'wallpaper2you_179920.jpg', NULL, '1453711348994146304.jpg', '/static/1453711348994146304.jpg', NULL, 'jpg', '2025-12-25 11:29:54', NULL, 0);
INSERT INTO `oss_file` VALUES (1453711490790981632, 'wallpaper2you_179920.jpg', NULL, '1453711490790981632.jpg', '/static/1453711490790981632.jpg', NULL, 'jpg', '2025-12-25 11:30:28', NULL, 0);
INSERT INTO `oss_file` VALUES (1453711706030080000, 'wallpaper2you_179920.jpg', NULL, '1453711706030080000.jpg', '/static/1453711706030080000.jpg', NULL, 'jpg', '2025-12-25 11:31:19', NULL, 0);
INSERT INTO `oss_file` VALUES (1453711830307307520, '64px-Netherite_Ingot_BE1.png', 1, '1453711830307307520.png', '/static/1453711830307307520.png', 1453696751528574976, 'png', '2025-12-25 11:31:49', '2025-12-25 11:32:05', 0);
INSERT INTO `oss_file` VALUES (1453775064729124864, '64px-Netherite_Ingot_BE1.png', NULL, '1453775064729124864.png', '/static/1453775064729124864.png', NULL, 'png', '2025-12-25 15:43:05', NULL, 0);
INSERT INTO `oss_file` VALUES (1453775211085168640, '64px-Netherite_Ingot_BE1.png', NULL, '1453775211085168640.png', '/static/1453775211085168640.png', NULL, 'png', '2025-12-25 15:43:40', NULL, 0);
INSERT INTO `oss_file` VALUES (1453775470788083712, '64px-Netherite_Ingot_BE1.png', NULL, '1453775470788083712.png', '/static/1453775470788083712.png', NULL, 'png', '2025-12-25 15:44:42', NULL, 0);

-- ----------------------------
-- Table structure for sys_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dict`;
CREATE TABLE `sys_data_dict`  (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  `index` int(11) NULL DEFAULT NULL COMMENT '排序',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID，0表示根菜单',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由路径（对应前端路由path）',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径（对应前端组件路径）',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单标题（对应前端meta.title）',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `visible` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示（1显示，0隐藏）',
  `keep_alive` tinyint(1) NULL DEFAULT 1 COMMENT '是否缓存（1缓存，0不缓存）',
  `type` tinyint(4) NULL DEFAULT 1 COMMENT '菜单类型（1目录 2菜单 3按钮）',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE,
  INDEX `idx_sort`(`sort`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'System', '/system', '', '系统管理', 'setting', 100, 1, 0, 1, NULL, 0, NULL, '2025-12-26 15:58:34');
INSERT INTO `sys_menu` VALUES (2, 1, 'User', '/user', '/user/index', '用户管理', 'user', 1, 1, 0, 2, 'system:user:list', 0, NULL, '2025-12-26 15:58:37');
INSERT INTO `sys_menu` VALUES (3, 1, 'Role', '/role', '/role/index', '角色管理', 'Avatar', 2, 1, 0, 2, 'system:role:list', 0, NULL, '2025-12-26 15:58:38');
INSERT INTO `sys_menu` VALUES (4, 1, 'Menu', '/menu', '/menu/index', '菜单管理', 'Operation', 3, 1, 0, 2, 'system:roleMenu:list', 0, NULL, '2025-12-26 15:58:38');
INSERT INTO `sys_menu` VALUES (5, 0, 'Store', '/store', '/store/index', '门店管理', 'Box', 90, 1, 0, 2, 'store:list', 0, NULL, '2025-12-26 15:58:39');
INSERT INTO `sys_menu` VALUES (6, 0, 'Goods', '/goods', '/goods/index', '商品管理', 'DishDot', 80, 1, 0, 2, 'goods:list', 0, NULL, '2025-12-26 15:58:39');
INSERT INTO `sys_menu` VALUES (7, 0, 'Orders', '/orders', '/orders/index', '订单管理', 'Handbag', 70, 1, 0, 2, 'orders:list', 0, NULL, '2025-12-26 15:58:40');
INSERT INTO `sys_menu` VALUES (9, 0, 'Catalog', '/catalog', '/catalog/index', '分类管理', 'FolderOpened', 2, 1, 1, 2, 'goods:catalog:list', 0, NULL, '2026-01-01 01:54:48');
INSERT INTO `sys_menu` VALUES (10, 0, 'PlayImage', '/playImage', '/play-image/index', '轮播图管理', 'picture', 60, 1, 0, 2, 'content:playimage:list', 0, NULL, '2025-12-26 15:58:40');
INSERT INTO `sys_menu` VALUES (11, 0, 'Dict', '/dict', '/data-dict/index', '字典管理', 'Tickets', 50, 1, 0, 2, 'system:dict:list', 0, NULL, '2025-12-26 15:58:41');
INSERT INTO `sys_menu` VALUES (12, 0, 'Home', '/home', '/home/index', '首页', 'House', 1, 1, 1, 2, NULL, 0, NULL, '2026-01-01 01:55:17');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL,
  `menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问地址',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (586722256965603329, NULL, '/sys/admin/test', NULL, 'admin.test', 0, '2024-06-07 09:05:15', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色值',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (586717827931574273, 'admin', '管理员', '2025-12-25 14:58:29', NULL, 0);
INSERT INTO `sys_role` VALUES (1453764639178358784, 'user', '操作员', '2025-12-25 15:01:40', '2025-12-25 15:02:34', 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `del_flag` tinyint(1) NULL DEFAULT 0 COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色-菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (97, 586717827931574273, 6, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (98, 586717827931574273, 1, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (99, 586717827931574273, 2, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (100, 586717827931574273, 3, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (101, 586717827931574273, 4, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (102, 586717827931574273, 5, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (103, 586717827931574273, 7, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (104, 586717827931574273, 8, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (105, 586717827931574273, 9, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (106, 586717827931574273, 10, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (107, 586717827931574273, 11, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (108, 586717827931574273, 12, 0, '2025-12-28 17:02:00', NULL);
INSERT INTO `sys_role_menu` VALUES (109, 586717827931574273, 13, 0, '2025-12-28 17:02:00', NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色-权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (586723677232435202, 586717827931574273, 586722256965603329);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `is_lock` tinyint(1) NULL DEFAULT NULL COMMENT '是否冻结',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (586394601263202304, '糊涂', 'hutu', '$2a$10$.Pjl2C6ewbiS0OMsZCobKu1ORINA51da7bFff1KsM7Baa3FUfRv96', '14511357607', '/static/20251210160851.jpg', 0, 0, '2024-06-06 11:23:18', '2024-06-17 16:37:28');
INSERT INTO `sys_user` VALUES (1453775980547014656, 'be_test', 'test', '$2a$10$Lwx85NFdPxazYjTKgaQhHeg3/mfZfbxWjAmgd9WMC0BkrdnpFpR1C', '123', '/static/1453775470788083712.png', 0, 0, '2025-12-25 15:46:44', '2025-12-25 15:46:50');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '系统用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  `del_flag` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1454639044515332096, 586394601263202304, 586717827931574273, 1, '2025-12-28 00:56:14', NULL);
INSERT INTO `sys_user_role` VALUES (1454639287495557120, 1453775980547014656, 586717827931574273, 1, '2025-12-28 00:57:12', NULL);
INSERT INTO `sys_user_role` VALUES (1454840868484677632, 1453775980547014656, 586717827931574273, 1, '2025-12-28 14:18:13', NULL);
INSERT INTO `sys_user_role` VALUES (1454840893839245312, 1453775980547014656, 586717827931574273, 0, '2025-12-28 14:18:19', NULL);
INSERT INTO `sys_user_role` VALUES (1454840893843439616, 586394601263202304, 586717827931574273, 0, '2025-12-28 14:18:19', NULL);

SET FOREIGN_KEY_CHECKS = 1;
