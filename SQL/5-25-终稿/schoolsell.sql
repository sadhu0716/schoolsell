/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.15 : Database - schoolsell
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`schoolsell` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `schoolsell`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `Name` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `administrator` */

insert  into `administrator`(`Name`,`Password`) values ('root','123');

/*Table structure for table `bigkind` */

DROP TABLE IF EXISTS `bigkind`;

CREATE TABLE `bigkind` (
  `bigKindID` int(4) NOT NULL AUTO_INCREMENT,
  `bigKindName` varchar(20) NOT NULL,
  `bigKindPicture` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`bigKindID`),
  KEY `bigKindID` (`bigKindID`),
  KEY `bigKindID_2` (`bigKindID`),
  KEY `bigKindID_3` (`bigKindID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `bigkind` */

insert  into `bigkind`(`bigKindID`,`bigKindName`,`bigKindPicture`) values (1,'学习',NULL),(2,'运动',NULL),(3,'电子',NULL);

/*Table structure for table `chatreport` */

DROP TABLE IF EXISTS `chatreport`;

CREATE TABLE `chatreport` (
  `chatID` int(4) NOT NULL AUTO_INCREMENT,
  `sendID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `acceptID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(1024) DEFAULT NULL,
  `date` varchar(30) NOT NULL,
  `isbrowse` tinyint(1) NOT NULL,
  PRIMARY KEY (`chatID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `chatreport` */

insert  into `chatreport`(`chatID`,`sendID`,`acceptID`,`context`,`date`,`isbrowse`) values (12,'tom','Joel','df','2018-08-17 14:16:54',0),(13,'tom','James','我是james','2018-08-17 14:18:43',0),(14,'tom','Jimmy','我是jimmy','2018-08-17 14:22:23',0),(15,'tom','John','123','2018-08-17 14:37:40',0),(16,'1122@qq.com','1122@qq.com','你好啊','2019/03/29 15:23:15',1),(17,'1122@qq.com','1122@qq.com','我是','2019/03/29 15:23:21',1),(18,'3344@qq.com','1122@qq.com','nihao','2019/03/29 15:35:12',1),(19,'3344@qq.com','1122@qq.com','nihao','2019/03/29 15:40:29',1),(20,'3344@qq.com','1122@qq.com','nihao','2019/03/29 15:42:08',1),(21,'1122@qq.com','3344@qq.com','我是1122','2019/03/29 15:44:47',1),(22,'1122@qq.com','3344@qq.com','你是谁？','2019/03/29 15:45:28',1),(23,'1122@qq.com','3344@qq.com','亲','2019/03/29 15:45:43',1),(24,'1122@qq.com','3344@qq.com','还在吗','2019/03/29 15:45:50',1),(25,'-498702880@qq.com','-498702880@qq.com','123','2019/05/16 22:06:54',1),(26,'-1151093719@qq.com','-1151093719@qq.com','本地测试521','2019/05/21 16:37:39',1),(27,'-1151093719@qq.com','1122@qq.com','聊天测试123','2019/05/21 22:32:27',1),(28,'1122@qq.com','-1151093719@qq.com','你好啊','2019/05/21 22:32:35',1),(29,'-1151093719@qq.com','1122@qq.com','liao','2019/05/21 22:37:52',1),(30,'-1151093719@qq.com','1122@qq.com','324','2019/05/21 22:37:55',1),(31,'1122@qq.com','-1151093719@qq.com','89','2019/05/21 22:38:00',1),(32,'1122@qq.com','-1151093719@qq.com','qqq','2019/05/22 14:16:03',1),(33,'1122@qq.com','-1151093719@qq.com','llll','2019/05/22 14:22:51',1),(34,'1122@qq.com','-1151093719@qq.com','456','2019/05/22 14:26:02',1),(35,'1122@qq.com','-1151093719@qq.com','456','2019/05/22 14:27:24',1),(36,'1122@qq.com','3344@qq.com','我是1122','2019/05/22 14:30:16',1),(37,'1122@qq.com','3344@qq.com','111','2019/05/22 14:34:50',1),(38,'3344@qq.com','1122@qq.com','kunkun','2019/05/22 14:37:44',1),(39,'3344@qq.com','1122@qq.com','kunkun','2019/05/22 14:41:18',1),(40,'1122@qq.com','3344@qq.com','是，我是坤坤','2019/05/22 14:41:38',1);

/*Table structure for table `commodity` */

DROP TABLE IF EXISTS `commodity`;

CREATE TABLE `commodity` (
  `cID` int(4) NOT NULL AUTO_INCREMENT,
  `cPrice` decimal(10,0) NOT NULL,
  `bargain` tinyint(1) NOT NULL,
  `useTime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cDescription` varchar(1024) DEFAULT NULL,
  `isChecked` int(1) NOT NULL,
  `sellerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cName` varchar(20) NOT NULL,
  `kName` varchar(20) NOT NULL,
  `thumbnail` varchar(1024) NOT NULL,
  `cCount` int(4) NOT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

/*Data for the table `commodity` */

insert  into `commodity`(`cID`,`cPrice`,`bargain`,`useTime`,`cDescription`,`isChecked`,`sellerID`,`cName`,`kName`,`thumbnail`,`cCount`) values (49,'14',1,'四成新','3344的第一份商品',2,'3344@qq.com','一风景','编程','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\49.jpg',6),(50,'56',1,'四成新','3344的第二份商品',2,'3344@qq.com','二风景','英语','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\50.jpg',7),(51,'45',1,'六成新','3344的第三份商品',2,'3344@qq.com','三风景','篮球','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\51.jpg',5),(52,'76',1,'四成新','3344的第四份商品',1,'3344@qq.com','四风景','足球','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\52.jpg',6),(58,'78',1,'四成新','1122的',1,'1122@qq.com','七风景','手机','H:\\Code\\Schoolsell\\target\\classes\\static\\thumbnail\\58.jpg',7),(61,'78',1,'五成新','第九张',1,'1122@qq.com','九风景','英语','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\61.jpg',9),(62,'56',1,'四成新','第十一张',1,'1122@qq.com','十一风景','篮球','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\62.jpg',7),(63,'34',1,'四成新','第十二张',1,'1122@qq.com','八风景','编程','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\63.jpg',17),(64,'57',1,'四成新','第十三章',1,'1122@qq.com','十二风景','笔记本','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\64.jpg',4),(65,'89',1,'四成新','第十三张',1,'1122@qq.com','十三风景','笔记本','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\65.jpg',8),(66,'56',1,'四成新','第一章风景',1,'-498702880@qq.com','后+第一张','足球','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\66.jpg',3),(67,'45',1,'四成新','第二张图片',1,'-498702880@qq.com','后_第二张','篮球','H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\67.jpg',3),(70,'45',1,'五成新','123',1,'1122@qq.com','聊天测试1','篮球','H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\thumbnail\\70.jpg',6);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedbackID` int(4) NOT NULL AUTO_INCREMENT,
  `feedbackDate` datetime(5) NOT NULL,
  `feedbackerID` varchar(50) NOT NULL,
  `description` varchar(1024) NOT NULL,
  PRIMARY KEY (`feedbackID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`feedbackID`,`feedbackDate`,`feedbackerID`,`description`) values (2,'2018-08-06 08:52:37.00000','1','系统出现了bug'),(3,'2018-08-21 15:24:17.00000','891993651@qq.com','你好啊谢谢');

/*Table structure for table `jubao` */

DROP TABLE IF EXISTS `jubao`;

CREATE TABLE `jubao` (
  `juBaoID` int(4) NOT NULL AUTO_INCREMENT,
  `juBaoDate` datetime(5) NOT NULL,
  `juBaoUserID` varchar(50) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `ocID` int(4) DEFAULT NULL,
  PRIMARY KEY (`juBaoID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `jubao` */

insert  into `jubao`(`juBaoID`,`juBaoDate`,`juBaoUserID`,`description`,`ocID`) values (2,'2018-08-06 09:08:24.00000','123','该商品质量很差',NULL),(3,'2019-05-22 21:37:23.00000','-1151093719@qq.com','345',20),(4,'2019-05-22 21:42:53.00000','-1151093719@qq.com','678',20),(5,'2019-05-22 21:43:30.00000','-1151093719@qq.com','546',20),(6,'2019-05-22 21:50:06.00000','-1151093719@qq.com','453',20);

/*Table structure for table `jubaopicture` */

DROP TABLE IF EXISTS `jubaopicture`;

CREATE TABLE `jubaopicture` (
  `jubaoPictureID` int(10) NOT NULL AUTO_INCREMENT,
  `jubaoID` int(10) NOT NULL,
  `photo` varchar(1024) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`jubaoPictureID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `jubaopicture` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `orderID` int(4) NOT NULL AUTO_INCREMENT,
  `orderDate` timestamp(5) NOT NULL,
  `state` int(1) NOT NULL COMMENT '是否完成',
  `endDate` timestamp(5) NULL DEFAULT NULL,
  `isOnline` tinyint(1) NOT NULL,
  `buyerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sellerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `buyerName` varchar(20) NOT NULL,
  `sellerName` varchar(20) NOT NULL,
  `buyerPhone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`orderID`,`orderDate`,`state`,`endDate`,`isOnline`,`buyerID`,`sellerID`,`buyerName`,`sellerName`,`buyerPhone`) values (3,'2019-04-11 17:07:11.00000',-1,NULL,0,'1122@qq.com','3344@qq.com','小王','小李',NULL),(4,'2019-04-11 17:11:30.00000',-1,NULL,0,'1122@qq.com','3344@qq.com','小王','小李',NULL),(6,'2019-04-11 18:20:06.00000',-1,NULL,0,'3344@qq.com','3344@qq.com','小李','小李',NULL),(21,'2019-05-22 21:10:27.00000',2,NULL,0,'-1151093719@qq.com','3344@qq.com','小睿子','李丽丽','3453464567'),(23,'2019-05-23 18:50:20.00000',2,NULL,0,'-1151093719@qq.com','3344@qq.com','小睿子','李丽丽','789789342789'),(24,'2019-05-23 18:51:35.00000',-1,NULL,0,'-1151093719@qq.com','1122@qq.com','小睿子','王小小','13256768789'),(25,'2019-05-23 21:45:40.00000',1,NULL,0,'-1151093719@qq.com','3344@qq.com','小睿子','李丽丽','13567897678'),(26,'2019-05-23 21:48:07.00000',-1,NULL,0,'-1151093719@qq.com','3344@qq.com','小睿子','李丽丽','12365786776'),(27,'2019-05-23 22:42:07.00000',-1,NULL,0,'-1151093719@qq.com','3344@qq.com','小睿子','李丽丽','12345678911'),(28,'2019-05-23 22:44:16.00000',-1,NULL,0,'-1151093719@qq.com','3344@qq.com','小睿子','李丽丽','');

/*Table structure for table `ordercommodity` */

DROP TABLE IF EXISTS `ordercommodity`;

CREATE TABLE `ordercommodity` (
  `ocID` int(10) NOT NULL AUTO_INCREMENT,
  `orderID` int(10) NOT NULL,
  `cID` int(10) NOT NULL,
  `amount` int(10) NOT NULL,
  `cPrice` decimal(10,0) NOT NULL,
  `cName` varchar(20) COLLATE utf8_bin NOT NULL,
  `bargain` tinyint(1) NOT NULL,
  PRIMARY KEY (`ocID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ordercommodity` */

insert  into `ordercommodity`(`ocID`,`orderID`,`cID`,`amount`,`cPrice`,`cName`,`bargain`) values (1,2,49,1,'14','一风景',0),(2,3,49,1,'14','一风景',0),(3,4,49,1,'14','一风景',0),(4,5,49,1,'14','一风景',0),(5,6,50,1,'56','二风景',0),(6,7,49,1,'14','一风景',0),(7,8,50,1,'56','二风景',0),(8,9,49,1,'14','一风景',0),(9,10,63,1,'34','八风景',0),(10,11,50,1,'56','二风景',0),(11,12,65,1,'89','十三风景',0),(12,13,50,1,'56','二风景',0),(13,14,52,1,'76','四风景',0),(14,15,50,1,'56','二风景',0),(15,16,61,1,'78','九风景',0),(16,17,61,1,'78','九风景',0),(17,18,51,1,'45','三风景',0),(18,19,51,1,'45','三风景',0),(19,20,50,1,'56','二风景',0),(20,21,49,1,'14','一风景',0),(24,23,50,1,'56','二风景',0),(25,23,51,1,'45','三风景',0),(26,23,58,3,'78','七风景',0),(27,24,64,1,'57','十二风景',0),(28,24,65,1,'89','十三风景',0),(29,25,50,3,'56','二风景',0),(30,25,51,1,'45','三风景',0),(31,25,58,3,'78','七风景',0),(32,25,64,1,'57','十二风景',0),(33,25,65,1,'89','十三风景',0),(34,26,52,1,'76','四风景',0),(35,27,51,1,'45','三风景',0),(36,28,50,1,'56','二风景',0),(37,28,51,2,'45','三风景',0);

/*Table structure for table `picture` */

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `pictureID` int(4) NOT NULL AUTO_INCREMENT,
  `cID` int(4) NOT NULL,
  `picture` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`pictureID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

/*Data for the table `picture` */

insert  into `picture`(`pictureID`,`cID`,`picture`) values (42,40,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.Wg4C7jCHHCHWfca28140d7b201d86c208196c177d9f4.jpg'),(43,40,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.GExuGFZcHQZ6d4adef7c0fa987872884674aba886049.jpg'),(44,41,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.3bOYzfX9jqs79694c9e0b59d92c6296ef95763e77a8f.jpg'),(45,41,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.yLqCjudfFATt43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(46,42,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..x3ZTM3myfMAy9694c9e0b59d92c6296ef95763e77a8f.jpg'),(47,42,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..OzG6benm6lTG43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(48,43,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..95kDSoOpZedafca28140d7b201d86c208196c177d9f4.jpg'),(49,43,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..dRTafAHVB0ATd4adef7c0fa987872884674aba886049.jpg'),(50,44,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..mCjKzYm6Sbdwbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(51,44,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..HSfgPz83UsPyfca28140d7b201d86c208196c177d9f4.jpg'),(52,45,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..5Ggd9uRDHzoLc783bc2d8324d880d206224fe7c4c1e8.jpg'),(53,45,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..EEvpXCMDV0Zo7662f60ffa6dc8d85aad9df21b0fbc1c.jpg'),(54,46,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..4QtVetLt4fQA4bffa30b5fa60ff404d0a12fc15ac6fb.jpg'),(55,46,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..KAvBMdlOvEY29694c9e0b59d92c6296ef95763e77a8f.jpg'),(56,46,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..dD2XAL94Qo1j43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(57,47,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..OoTQc2YnsSXVbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(58,47,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..6843oLuwQgsmfca28140d7b201d86c208196c177d9f4.jpg'),(59,47,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..zknW0wD27Exxd4adef7c0fa987872884674aba886049.jpg'),(60,48,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..g75L76dqoB1P1d4d9f8e062650fbb8a98714697bf735.jpg'),(61,48,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a..FrnlfX9lQIDY92a14dd5352e6348371dc34808b5e6b7.jpg'),(62,49,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.BrLi4jKIp32ufca28140d7b201d86c208196c177d9f4.jpg'),(63,49,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.QSKdtgeBHIe8d4adef7c0fa987872884674aba886049.jpg'),(64,50,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.oFw5sbgFfsjLbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(65,50,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.WYvzk2K1bCwRd4adef7c0fa987872884674aba886049.jpg'),(66,51,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.B0ed3m3YV7hLd4adef7c0fa987872884674aba886049.jpg'),(67,51,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.YwGA0LPx7SSL1d4d9f8e062650fbb8a98714697bf735.jpg'),(68,52,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.XtPTUSD9Qh2x43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(69,52,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.5EqbtSsdHcBS003d29ca7549f3efa335363a10934ee6.jpg'),(70,53,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.5RiODe9HVJsAbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(71,53,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.o8KfC11Ei0n0fca28140d7b201d86c208196c177d9f4.jpg'),(72,54,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.T5fHRnHsEAwe4bffa30b5fa60ff404d0a12fc15ac6fb.jpg'),(73,54,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.YfMwL5BdDqBf9694c9e0b59d92c6296ef95763e77a8f.jpg'),(74,55,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.d1csLE0IdhT292a14dd5352e6348371dc34808b5e6b7.jpg'),(75,55,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.0k2Evrr142ooc783bc2d8324d880d206224fe7c4c1e8.jpg'),(76,56,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.xxDMxiQj26Zobc9b2cb605df5c5315614c4a2bc1859a.jpg'),(77,56,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.kRIzrZk5K0Dufca28140d7b201d86c208196c177d9f4.jpg'),(78,57,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.z5XQ0EN0tDPS43fa00ddcfa0e42fd5ee72b4f7329862.jpg'),(79,57,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.BKD0l8UjCg3P003d29ca7549f3efa335363a10934ee6.jpg'),(80,58,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.2CgiACus9Fnzc783bc2d8324d880d206224fe7c4c1e8.jpg'),(81,58,'H:\\Code\\Schoolsell\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.RV4osuNJsYSj7662f60ffa6dc8d85aad9df21b0fbc1c.jpg'),(82,59,'H:\\Code\\isRunning\\server\\jar\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.KuBvWsuYxq0Gbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(83,59,'H:\\Code\\isRunning\\server\\jar\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.CpyNS7fvpTltfca28140d7b201d86c208196c177d9f4.jpg'),(84,59,'H:\\Code\\isRunning\\server\\jar\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.lLqjU3c7XUbzd4adef7c0fa987872884674aba886049.jpg'),(85,60,'H:\\Code\\isRunning\\server\\jar\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.gUTIVJlZgp6Pd4adef7c0fa987872884674aba886049.jpg'),(86,60,'H:\\Code\\isRunning\\server\\jar\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.FWXA713EIsQo1d4d9f8e062650fbb8a98714697bf735.jpg'),(87,61,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.is7DwGnJhMEsbc9b2cb605df5c5315614c4a2bc1859a.jpg'),(88,61,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.SKUs7UfEOV7cfca28140d7b201d86c208196c177d9f4.jpg'),(89,61,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.FefJvaCiMPCQd4adef7c0fa987872884674aba886049.jpg'),(90,62,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.w9IHM5BGCnPcc783bc2d8324d880d206224fe7c4c1e8.jpg'),(91,62,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.ww3IS500ok0A7662f60ffa6dc8d85aad9df21b0fbc1c.jpg'),(92,63,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.iI1eJVZ4N0kZ92a14dd5352e6348371dc34808b5e6b7.jpg'),(93,63,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.xKpUqgiqupcBc783bc2d8324d880d206224fe7c4c1e8.jpg'),(94,64,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.A17WH2UH67DFc783bc2d8324d880d206224fe7c4c1e8.jpg'),(95,64,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.kDTs2Nj6BlzW7662f60ffa6dc8d85aad9df21b0fbc1c.jpg'),(96,65,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.gHvD4bpbxs5bfca28140d7b201d86c208196c177d9f4.jpg'),(97,65,'H:\\GitHub\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.gqyxb9NCWzcNd4adef7c0fa987872884674aba886049.jpg'),(98,68,'H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.No0nsBIEiqYYd4adef7c0fa987872884674aba886049.jpg'),(99,68,'H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.ga407K9NAh841d4d9f8e062650fbb8a98714697bf735.jpg'),(100,69,'H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.7EZjIh9T4IPs7662f60ffa6dc8d85aad9df21b0fbc1c.jpg'),(101,69,'H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.3kloOmZMjJtyb2dfbccf4f7fed9e4184aa9be480a76b.jpg'),(102,70,'H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.nIfezeIDtMeSfca28140d7b201d86c208196c177d9f4.jpg'),(103,70,'H:\\backup_schoolsell\\schoolsell\\Schoolsell-local\\target\\classes\\static\\commodityImg/wx31174c6b66d0721a.o6zAJsz2ACsFCLynD3x-Azx1VlwE.eL2EvzzIPY5nd4adef7c0fa987872884674aba886049.jpg');

/*Table structure for table `shopcar` */

DROP TABLE IF EXISTS `shopcar`;

CREATE TABLE `shopcar` (
  `cID` int(4) NOT NULL,
  `shopCarID` int(4) NOT NULL AUTO_INCREMENT,
  `userID` varchar(50) NOT NULL,
  `amount` int(4) DEFAULT NULL,
  `isFinish` int(4) NOT NULL,
  PRIMARY KEY (`shopCarID`),
  KEY `FK_shopcar` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `shopcar` */

insert  into `shopcar`(`cID`,`shopCarID`,`userID`,`amount`,`isFinish`) values (41,1,'1122@qq.com',1,0),(48,2,'1122@qq.com',2,0),(51,6,'-498702880@qq.com',1,0),(52,7,'-498702880@qq.com',1,0),(53,8,'-498702880@qq.com',1,0),(58,11,'-1151093719@qq.com',3,0),(58,15,'-1151093719@qq.com',1,0);

/*Table structure for table `smallkind` */

DROP TABLE IF EXISTS `smallkind`;

CREATE TABLE `smallkind` (
  `smallKindID` int(4) NOT NULL AUTO_INCREMENT,
  `smallKindName` varchar(20) NOT NULL,
  `bigKindID` int(4) NOT NULL,
  `smallKindPicture` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`smallKindID`),
  KEY `FK_smallkind` (`bigKindID`),
  CONSTRAINT `FK_smallkind` FOREIGN KEY (`bigKindID`) REFERENCES `bigkind` (`bigKindID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `smallkind` */

insert  into `smallkind`(`smallKindID`,`smallKindName`,`bigKindID`,`smallKindPicture`) values (5,'编程',1,NULL),(6,'英语',1,NULL),(7,'篮球',2,NULL),(8,'足球',2,NULL),(9,'手机',3,NULL),(10,'笔记本',3,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(50) NOT NULL,
  `filePath` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isOrdinary` int(1) NOT NULL COMMENT '0为微信登录，1为普通登录',
  PRIMARY KEY (`userID`),
  KEY `userID` (`userID`),
  KEY `userID_2` (`userID`),
  KEY `userID_3` (`userID`),
  KEY `userID_4` (`userID`),
  KEY `userID_5` (`userID`),
  KEY `userID_6` (`userID`),
  KEY `userID_7` (`userID`),
  KEY `userID_8` (`userID`),
  KEY `userID_9` (`userID`),
  KEY `userID_10` (`userID`),
  KEY `userID_11` (`userID`),
  KEY `userID_12` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`filePath`,`gender`,`userName`,`isOrdinary`) values ('-1151093719@qq.com','https://wx.qlogo.cn/mmopen/vi_32/ouuic4cavauKd5tsl3LjOwBRqic2KLIkibNyeXHURwhzRccROMJclyKW0y3O2pn51FhDuLztlkhAnibNJr2PkhMvmA/132','男','小睿子',0),('1122@qq.com','123','男','王小小',1),('3344@qq.com','123','女','李丽丽',1),('5566@qq.com','123','女','刘潇潇',1),('7788@qq.com','456','男','测试1',1);

/*Table structure for table `wxuser` */

DROP TABLE IF EXISTS `wxuser`;

CREATE TABLE `wxuser` (
  `wxID` int(10) NOT NULL AUTO_INCREMENT,
  `userID` varchar(50) COLLATE utf8_bin NOT NULL,
  `openID` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sessionKey` varbinary(255) NOT NULL,
  PRIMARY KEY (`wxID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `wxuser` */

insert  into `wxuser`(`wxID`,`userID`,`openID`,`sessionKey`) values (13,'-1151093719@qq.com','oRyko40CSJIIk8KH_CiHqyfx3-GI','oy2QIWT54wwDxQxqsnepWA==');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
