/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735 (5.7.35)
 Source Host           : localhost:3306
 Source Schema         : hutu-order

 Target Server Type    : MySQL
 Target Server Version : 50735 (5.7.35)
 File Encoding         : 65001

 Date: 18/03/2025 16:27:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
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
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `is_enable` tinyint(4) NULL DEFAULT NULL COMMENT '是否启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
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
  `item_imge_urls` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片地址',
  `expect_make_time` int(20) NULL DEFAULT NULL COMMENT '预计制作时长，单位：分',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_item
-- ----------------------------
INSERT INTO `biz_item` VALUES (1, '15.00', '12.00', '拿铁', '热', '优质咖啡豆搭配顶级牛奶', '热销,新品,新品,新品', 'https://pic.imgdb.cn/item/67332173d29ded1a8c6801a8.jpg', 2, 0, '2024-05-24 09:00:00', '2024-05-24 09:00:00');
INSERT INTO `biz_item` VALUES (2, '18.00', '15.00', '卡布奇诺', '无糖', '意式浓缩咖啡与丝滑牛奶的完美结合', '推荐', '', 2, 0, '2024-05-24 09:15:00', '2024-05-24 09:15:00');
INSERT INTO `biz_item` VALUES (3, '20.00', '18.00', '摩卡', NULL, '香浓巧克力、浓郁咖啡与奶泡的完美融合', '新品', '', 2, 0, '2024-05-24 09:30:00', '2024-05-24 09:30:00');
INSERT INTO `biz_item` VALUES (4, '25.00', '22.00', '美式咖啡', NULL, '纯粹的咖啡香味', '', '', 2, 0, '2024-05-24 09:45:00', '2024-05-24 09:45:00');
INSERT INTO `biz_item` VALUES (5, '22.00', '20.00', '浓缩咖啡', NULL, '浓郁的咖啡香味', '', '', 3, 0, '2024-05-24 10:00:00', '2024-05-24 10:00:00');
INSERT INTO `biz_item` VALUES (6, '28.00', '25.00', '拿铁玛奇朵', NULL, '拿铁与玛奇朵的完美结合', '', '', 4, 0, '2024-05-24 10:15:00', '2024-05-24 10:15:00');
INSERT INTO `biz_item` VALUES (7, '30.00', '28.00', '焦糖玛奇朵', NULL, '香甜焦糖与浓郁咖啡的美妙组合', '', '', 5, 0, '2024-05-24 10:30:00', '2024-05-24 10:30:00');
INSERT INTO `biz_item` VALUES (8, '20.00', '18.00', '香草拿铁', NULL, '香草风味与顶级牛奶的完美搭配', '', '', 5, 0, '2024-05-24 10:45:00', '2024-05-24 10:45:00');
INSERT INTO `biz_item` VALUES (9, '22.00', '20.00', '焦糖拿铁', NULL, '浓郁咖啡与香甜焦糖的绝妙组合', '', '', 3, 0, '2024-05-24 11:00:00', '2024-05-24 11:00:00');
INSERT INTO `biz_item` VALUES (10, '25.00', '22.00', '冰美式', NULL, '清凉解渴的美式咖啡', '', '', 5, 0, '2024-05-24 11:15:00', '2024-05-24 11:15:00');
INSERT INTO `biz_item` VALUES (11, '30.00', '28.00', '冰摩卡', NULL, '浓郁巧克力与冰冻咖啡的美妙组合', '', '', 6, 0, '2024-05-24 11:30:00', '2024-05-24 11:30:00');
INSERT INTO `biz_item` VALUES (12, '28.00', '25.00', '冰拿铁', NULL, '清凉爽口的拿铁咖啡', '', '', 6, 0, '2024-05-24 11:45:00', '2024-05-24 11:45:00');
INSERT INTO `biz_item` VALUES (13, '18.00', '15.00', '冰卡布奇诺', NULL, '冰冻的卡布奇诺咖啡', '', '', 5, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_item` VALUES (14, '20.00', '18.00', '香草冰拿铁', NULL, '清凉解渴的香草拿铁', '', '', 7, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_item` VALUES (15, '22.00', '20.00', '香草冰卡布奇诺', NULL, '清凉解渴的香草卡布奇诺', '', '', 6, 0, '2024-05-24 12:30:00', '2024-05-24 12:30:00');
INSERT INTO `biz_item` VALUES (1243600983305486336, NULL, NULL, 'cs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-24 16:26:19', NULL);
INSERT INTO `biz_item` VALUES (1243601159365591040, NULL, NULL, 'cs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-24 16:27:01', NULL);
INSERT INTO `biz_item` VALUES (1243602284198232064, NULL, NULL, 'cs', NULL, NULL, NULL, NULL, NULL, NULL, '2024-05-24 16:31:30', NULL);

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
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942723, 1, 585315952858497025, 3);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942724, 1, 585315952858497026, 1);
INSERT INTO `biz_item_sku_relation` VALUES (585317148230942725, 1, 585315952858497024, 2);

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
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_menu
-- ----------------------------
INSERT INTO `biz_menu` VALUES (1, 1, '1', 593630146225766400, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_menu` VALUES (2, 1, '2', 593630146225766400, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_menu` VALUES (3, 2, '3', 593630146225766400, 0, '2024-05-24 12:30:00', '2024-05-24 12:30:00');
INSERT INTO `biz_menu` VALUES (4, 2, '4', 593630146225766400, 0, '2024-05-24 12:45:00', '2024-05-24 12:45:00');
INSERT INTO `biz_menu` VALUES (5, 3, '5', 593630146225766400, 0, '2024-05-24 13:00:00', '2024-05-24 13:00:00');
INSERT INTO `biz_menu` VALUES (6, 3, '6', 593630146225766400, 0, '2024-05-24 13:15:00', '2024-05-24 13:15:00');
INSERT INTO `biz_menu` VALUES (7, 4, '7', 593630146225766400, 0, '2024-05-24 13:30:00', '2024-05-24 13:30:00');
INSERT INTO `biz_menu` VALUES (8, 4, '8', 593630146225766400, 0, '2024-05-24 13:45:00', '2024-05-24 13:45:00');
INSERT INTO `biz_menu` VALUES (9, 5, '9', 593630146225766400, 0, '2024-05-24 14:00:00', '2024-05-24 14:00:00');
INSERT INTO `biz_menu` VALUES (10, 5, '10', 593630146225766400, 0, '2024-05-24 14:15:00', '2024-05-24 14:15:00');
INSERT INTO `biz_menu` VALUES (11, 1, '3', 593630146225766400, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_menu` VALUES (12, 1, '4', 593630146225766400, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_menu` VALUES (13, 1, '5', 593630146225766400, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');
INSERT INTO `biz_menu` VALUES (14, 1, '6', 593630146225766400, 0, '2024-05-24 12:15:00', '2024-05-24 12:15:00');
INSERT INTO `biz_menu` VALUES (15, 1, '7', 593630146225766400, 0, '2024-05-24 12:00:00', '2024-05-24 12:00:00');

-- ----------------------------
-- Table structure for biz_menu_catalog
-- ----------------------------
DROP TABLE IF EXISTS `biz_menu_catalog`;
CREATE TABLE `biz_menu_catalog`  (
  `id` bigint(20) NOT NULL,
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '门店id',
  `img_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预览图',
  `catalog_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `catalog_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '分类描述',
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店菜单分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_menu_catalog
-- ----------------------------
INSERT INTO `biz_menu_catalog` VALUES (1, 593630146225766400, 'https://pic.imgdb.cn/item/67331f2cd29ded1a8c654583.jpg', '咖啡', '各种口味的咖啡', '无', 0, '2024-05-24 09:00:00', '2024-05-24 09:00:00');
INSERT INTO `biz_menu_catalog` VALUES (2, 593630146225766400, 'https://pic.imgdb.cn/item/673320ded29ded1a8c67433c.jpg', '茶', '各种茶类饮品', '无', 0, '2024-05-24 09:15:00', '2024-05-24 09:15:00');
INSERT INTO `biz_menu_catalog` VALUES (3, 593630146225766400, NULL, '甜点', '各种甜品', '无', 0, '2024-05-24 09:30:00', '2024-05-24 09:30:00');
INSERT INTO `biz_menu_catalog` VALUES (4, 593630146225766400, NULL, '冰淇淋', '各种口味的冰淇淋', '无', 0, '2024-05-24 09:45:00', '2024-05-24 09:45:00');
INSERT INTO `biz_menu_catalog` VALUES (5, 593630146225766400, NULL, '果汁', '新鲜榨取的果汁', '无', 0, '2024-05-24 10:00:00', '2024-05-24 10:00:00');
INSERT INTO `biz_menu_catalog` VALUES (6, 593630146225766400, NULL, '饮料', '其他饮品', '无', 0, '2024-05-24 10:15:00', '2024-05-24 10:15:00');
INSERT INTO `biz_menu_catalog` VALUES (7, 593630146225766400, NULL, '特色饮品', '店内特色饮品', '无', 0, '2024-05-24 10:30:00', '2024-05-24 10:30:00');
INSERT INTO `biz_menu_catalog` VALUES (8, 593630146225766400, NULL, '热饮', '各种热饮', '无', 0, '2024-05-24 10:45:00', '2024-05-24 10:45:00');
INSERT INTO `biz_menu_catalog` VALUES (9, 593630146225766400, NULL, '冷饮', '各种冷饮', '无', 0, '2024-05-24 11:00:00', '2024-05-24 11:00:00');
INSERT INTO `biz_menu_catalog` VALUES (10, 593630146225766400, NULL, '小吃', '各种小吃', '无', 0, '2024-05-24 11:15:00', '2024-05-24 11:15:00');

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
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
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
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `shop_id` bigint(20) NULL DEFAULT NULL COMMENT '所属门店',
  `order_type` tinyint(4) NULL DEFAULT NULL COMMENT '0-单点 1-套餐',
  `pick_type` tinyint(4) NULL DEFAULT NULL COMMENT '0-堂食 1-打包 2-外送',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `origin_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '原金额',
  `item_info` json NULL COMMENT '菜品信息',
  `item_piece` int(11) NULL DEFAULT NULL COMMENT '菜品统计',
  `wait_time` int(11) NULL DEFAULT NULL COMMENT '预计等待时间',
  `payway_id` bigint(20) NULL DEFAULT NULL COMMENT '支付方式id',
  `out_trade_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外部交易流水号',
  `refund_out_trade_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款交易流水号',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_status` tinyint(4) NULL DEFAULT NULL COMMENT '0-初始化 1-进行中 2-支付 3-退单',
  `pay_notify_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付业务通知信息',
  `refund_notify_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款业务通知信息',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `user_del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '用户删除标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `settle_time` datetime NULL DEFAULT NULL COMMENT '结算时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_pay_order
-- ----------------------------

-- ----------------------------
-- Table structure for biz_pay_way
-- ----------------------------
DROP TABLE IF EXISTS `biz_pay_way`;
CREATE TABLE `biz_pay_way`  (
  `id` bigint(20) NOT NULL,
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
INSERT INTO `biz_pay_way` VALUES (593698122811576324, 'http://test.com', 'http://test.com', 'http://test.com', '北京工商银行分行', 1, 0, 'icon.tex', 1, 'WEB');
INSERT INTO `biz_pay_way` VALUES (593698122811579901, 'http://ali.pay.com', 'http://ali.pay.com', 'http://ali.pay.com', '支付宝小程序支付', 1, 0, NULL, NULL, 'MP-ALIPAY');
INSERT INTO `biz_pay_way` VALUES (593698122811579991, 'http://weixin.pay.com', 'http://weixin.pay.com', 'http://weixin.pay.com', '微信小程序支付', 1, 0, NULL, NULL, 'MP-WEIXIN');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_shop_info
-- ----------------------------
INSERT INTO `biz_shop_info` VALUES (593630146225766400, '糊涂餐馆（齐河路店）', '上海市齐河路28弄31号303室', '13092621512', '08:00', '18:00', 0, NULL, NULL, 1, 5, 1, '@/static/image/20250318102553.png', NULL, '	.body{\r\n		padding: 20px 22px;\r\n		padding-bottom: 10px;\r\n		background-size: 100% 30%;\r\n		background-attachment: fixed;\r\n	}\r\n	.header-card {\r\n		padding: 2px;\r\n	}\r\n	\r\n	.header-card text{\r\n		display: block;\r\n	}\r\n	\r\n	.shop-name{\r\n		fontSize: 16px;\r\n		fontWeight: 600;\r\n	}\r\n	\r\n	.shop-info-card{\r\n		display: flex;\r\n		flexDirection: row;\r\n		justifyContent: space-between;\r\n		width: 100%;\r\n	}\r\n	\r\n	.right text{\r\n		fontSize: 24px;\r\n		fontWeight: 500;\r\n	}\r\n	\r\n	.address{\r\n		marginTop: 40px;\r\n	}\r\n	\r\n	.address,.time{\r\n		/* color: grey; */\r\n	}\r\n	\r\n	text{\r\n		color: white;\r\n	}');
INSERT INTO `biz_shop_info` VALUES (593630146225766401, '糊涂餐馆（杨铭园店）', '赤岗冲红花坡杨铭园C栋', '13063855026', '09:00', '17:00', 0, NULL, NULL, 1, 5, NULL, NULL, NULL, NULL);
INSERT INTO `biz_shop_info` VALUES (593630146225766402, '糊涂餐馆（村水云居店）', '余家湖村水云居A栋2单元404室', '13013802811', '11:00', '20:00', 0, NULL, NULL, 1, 5, NULL, NULL, NULL, NULL);
INSERT INTO `biz_shop_info` VALUES (593630146225766403, '糊涂餐馆（中胜街店）', '中胜街54-2号392', '13092626359', '08:00', '16:00', 0, NULL, NULL, 1, 7, NULL, NULL, NULL, NULL);
INSERT INTO `biz_shop_info` VALUES (593630146225766404, '糊涂餐馆（秋景怡园店）', '秋景怡园5-3-6040', '13063726873', '09:30', '17:30', 0, NULL, NULL, 1, 7, NULL, NULL, NULL, NULL);
INSERT INTO `biz_shop_info` VALUES (593630146225766405, '糊涂餐馆（霄云路店）', '霄云路霄云里7号楼', '13092626359', '10:30', '19:30', 0, NULL, NULL, 1, 7, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for biz_sku_catalog
-- ----------------------------
DROP TABLE IF EXISTS `biz_sku_catalog`;
CREATE TABLE `biz_sku_catalog`  (
  `id` bigint(20) NOT NULL,
  `sku_catalog_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'sku分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_sku_catalog
-- ----------------------------
INSERT INTO `biz_sku_catalog` VALUES (585315952858497024, '甜度');
INSERT INTO `biz_sku_catalog` VALUES (585315952858497025, '就餐');
INSERT INTO `biz_sku_catalog` VALUES (585315952858497026, '温度');

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

-- ----------------------------
-- Table structure for biz_sku_item
-- ----------------------------
DROP TABLE IF EXISTS `biz_sku_item`;
CREATE TABLE `biz_sku_item`  (
  `id` bigint(20) NOT NULL,
  `sku_item_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sku项目正文',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
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
INSERT INTO `biz_sku_item` VALUES (585316296787234822, '堂食', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234823, '打包', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234824, '常温', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234825, '热', 0, '2024-06-03 11:58:21', NULL);
INSERT INTO `biz_sku_item` VALUES (585316296787234826, '冰', 0, '2024-06-03 11:58:21', NULL);

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
  `regist_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `last_log_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `actual_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际余额',
  `gift_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '赠送余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_user
-- ----------------------------
INSERT INTO `biz_user` VALUES (1350072554156457984, '测试人士', '13092626359', 1, 'blob:http://localhost:8000/dc011035-1a4f-4828-9fd9-b51f9e9416e7', NULL, 1, '2025-03-14 11:46:20', '2025-03-18 11:07:13', 288.88, 1.88);

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
INSERT INTO `biz_user_package` VALUES (585384179567427584, 585384223465017355, 11, 1, '三分糖');
INSERT INTO `biz_user_package` VALUES (1341442114751299584, 1275384651644403712, 6, 1, '');
INSERT INTO `biz_user_package` VALUES (1341442219713757184, 1275384651644403712, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1341442993155997696, 1275384651644403712, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1341443316578779136, 1275384651644403712, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1341445556953677824, 1275384651644403712, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1350056896429555712, 1350055123967016960, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1350057280212566016, 1350055123967016960, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1350059759474049024, 1350055123967016960, 1, 1, '全糖');
INSERT INTO `biz_user_package` VALUES (1350065096973352960, 1350055123967016960, 7, 1, '');
INSERT INTO `biz_user_package` VALUES (1351486651007762432, 1350072554156457984, 1, 1, '全糖');

-- ----------------------------
-- Table structure for biz_user_point
-- ----------------------------
DROP TABLE IF EXISTS `biz_user_point`;
CREATE TABLE `biz_user_point`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `point` bigint(20) NULL DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户积分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_user_point
-- ----------------------------

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
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
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
  `file_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储名称',
  `path` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '访问路径',
  `biz_id` bigint(20) NULL DEFAULT NULL COMMENT '业务id',
  `prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oss_file
-- ----------------------------

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
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
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
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (586717827931574273, 'admin', '管理员');

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
  `is_lock` tinyint(4) NULL DEFAULT NULL COMMENT '是否冻结',
  `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标识',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (586394601263202304, '糊涂', 'hutu', '$2a$10$JLvCWMPh9VPM6qHY1llwcOBOmzaWxdHiI/wd07RII7z2LKyDYNqUK', '14511357607', '123', NULL, 0, '2024-06-06 11:23:18', '2024-06-17 16:37:28');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '系统用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户-角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (588553637135912965, 586394601263202304, 586717827931574273);

SET FOREIGN_KEY_CHECKS = 1;
