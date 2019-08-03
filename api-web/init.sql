--share
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `symbol` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `current` double(11, 2) NULL DEFAULT NULL COMMENT '现价',
  `start_price` double(11, 2) NULL DEFAULT NULL COMMENT '开盘价',
  `percent` double(11, 2) NULL DEFAULT NULL COMMENT '涨跌幅',
  `volume` double(18, 0) NULL DEFAULT NULL COMMENT '成交量',
  `amount` double(18, 0) NULL DEFAULT NULL COMMENT '成交额',
  `turnover_rate` double(11, 2) NULL DEFAULT NULL COMMENT '换手率',
  `market_capital` double(18, 2) NULL DEFAULT NULL COMMENT '市值',
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
