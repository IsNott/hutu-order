
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店信息表' ROW_FORMAT = Dynamic;

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
-- Table structure for biz_table_info
-- ----------------------------
DROP TABLE IF EXISTS `biz_table_info`;
CREATE TABLE `biz_table_info`  (
  `id` bigint(20) NOT NULL,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '桌面信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店桌台信息表' ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

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
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(20) NOT NULL,
  `origin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件原名',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件存储名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传路径',
  `biz_id` bigint(20) NULL DEFAULT NULL COMMENT '业务数据id',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统文件表' ROW_FORMAT = Dynamic;

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
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '系统用户id',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户-角色关系表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
