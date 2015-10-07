DROP TABLE IF EXISTS `cd_information`;
CREATE TABLE `cd_information` (
  `ID` int(11) NOT NULL auto_increment,
  `Cdname` varchar(50) default NULL COMMENT '商品名字',
  `Cdprice` varchar(30) COMMENT '商品价格',
  `Cdcategories` varchar(30) COMMENT '商品大类',
  `Cdsmallclass` varchar(30) COMMENT '商品小类',
  `Cdintroduction` longtext COMMENT '商品简介',
  `Shelfnumber` varchar(30) COMMENT '货架号',
  `Stockr` varchar(30) COMMENT '库存',
  `Cpbh` varchar(30) COMMENT '产品编号',
  `Cdpicture` varchar(255)  COMMENT '商品图片',
  `Cdpicturech` varchar(255)  COMMENT '商品图片中文名',
   PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `cd_categories`;  
CREATE TABLE `cd_categories` (
  `ID` int(11) NOT NULL auto_increment,
  `Categoriesdm` varchar(50) default NULL COMMENT '商品大类代码',
  `Categoriesmc` varchar(30) COMMENT '商品大类名称',
   PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `mb_commission`;  
CREATE TABLE `mb_commission` (
  `ID` int(11) NOT NULL auto_increment,
  `Mbtype` varchar(50) default NULL COMMENT '用户类型',
  `Commission` varchar(30) COMMENT '提成',
   PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `cd_smallclass`;
CREATE TABLE `cd_smallclass` (
  `ID` int(11) NOT NULL auto_increment,
  `Categoriesdm` varchar(50) default NULL COMMENT '商品大类代码',
  `Casmallclassdm` varchar(50) default NULL COMMENT '商品小类代码',
  `Casmallclassmc` varchar(30) COMMENT '商品小类名称',
   PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mb_member`;  
CREATE TABLE `mb_member` (
  `ID` int(11) NOT NULL auto_increment,
  `Mbname` varchar(50) default NULL COMMENT '会员名',  
  `Openid` varchar(50) COMMENT '微信公众号唯一id',
  `Mbtype` varchar(50) COMMENT '用户类型',
  `Wxname` varchar(50) COMMENT '微信名',
   PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(200) default NULL COMMENT '标题',
  `content` longtext COMMENT '文档内容',
  `author` varchar(50) default NULL COMMENT '作者',
  `realTime` datetime default NULL COMMENT '文档原始时间',
  `createUser` varchar(100) default NULL COMMENT '创建用户',
  `clicks` int(11) default '0' COMMENT '阅读次数',
  `cateCode` varchar(255) default NULL COMMENT '分类',
  `filePath` varchar(255) default NULL COMMENT '附件路径',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='资讯';

DROP TABLE IF EXISTS `news_category`;
CREATE TABLE `news_category` (
  `id` int(11) NOT NULL auto_increment,
  `cateName` varchar(255) default NULL COMMENT '类别名称',
  `cateCode` varchar(255) default NULL COMMENT '代号',
  `remark` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='资讯类型';

-- ----------------------------
-- Records of news_category
-- ----------------------------
INSERT INTO `news_category` VALUES ('1', '组织架构', 'park_frame', '园区风貌');
INSERT INTO `news_category` VALUES ('2', '园区介绍', 'park_intro', '园区风貌');
INSERT INTO `news_category` VALUES ('3', '园区位置', 'park_site', '园区风貌');
INSERT INTO `news_category` VALUES ('4', '创业环境', 'park_envir', '园区风貌');
INSERT INTO `news_category` VALUES ('5', '国家政策', 'policy_regu', '政策法规');
INSERT INTO `news_category` VALUES ('6', '省市政策', 'policy_province', '政策法规');
INSERT INTO `news_category` VALUES ('7', '园区政策', 'policy_park', '政策法规');
INSERT INTO `news_category` VALUES ('8', '最新公告', 'notice_lastest', '最新公告');
INSERT INTO `news_category` VALUES ('11', '招商信息', 'service_business', '企业服务');
INSERT INTO `news_category` VALUES ('19', '入园导向>入园指南', 'service_inpark_guide', '企业服务');
INSERT INTO `news_category` VALUES ('20', '出园导向>出园指南', 'service_outpark_guide', '企业服务');
INSERT INTO `news_category` VALUES ('21', '基础服务', 'service_base', '企业服务');
INSERT INTO `news_category` VALUES ('22', '人力资源', 'service_human', '企业服务');
INSERT INTO `news_category` VALUES ('23', '项目申报', 'service_apply', '企业服务');
INSERT INTO `news_category` VALUES ('24', '入园导向>入园流程', 'service_inpark_process', '企业服务');
INSERT INTO `news_category` VALUES ('25', '出园导向>出园流程', 'service_outpark_process', '企业服务');
INSERT INTO `news_category` VALUES ('26', '培训通知', 'train_notice', '交流培训');
INSERT INTO `news_category` VALUES ('27', '培训课件', 'train_file', '交流培训');
INSERT INTO `news_category` VALUES ('28', '联系我们', 'contact_us', '联系我们');




